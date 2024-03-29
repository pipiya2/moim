package com.example.demo.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.vo.UserVo;

@Service
public class AccountService {
	
	@Autowired
	AccountMapper am;
	
	@Autowired
	SecurityService ss;
	
	@Autowired
	MailService ms;
	// 회원가입
	public Map<String,Object> insertAccount(Map<String, Object> responseData,UserVo uv) throws Exception{
		boolean isSuccess = false;
		String errorMessage = "";
		// TODO 
		try {
			// 이메일 중복체크
			int emailCheck = am.emailCheck(uv.getUserEmail());
			
			if(emailCheck != 0) {
				errorMessage = "email이 중복됩니다.";
			}else {
				String salt = ss.getSalt();
				String newPw = ss.getEncryptionPw(uv.getPassword(), salt);
				uv.setPassword(newPw);
				uv.setSalt(salt);
				isSuccess = am.insertAccount(uv) == 1 ? true : false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "SYSTEM ERROR";
		}
		
		
		responseData.put("ERRORMESSAGE", errorMessage);
		responseData.put("ISSUCCESS", isSuccess);
		return responseData;
	}

	// 로그인
	public Map<String, Object> signIn(Map<String, Object> responseData, UserVo uv,HttpServletRequest request) {
		boolean isSuccess = true;
		String errorMessage = "";
		String preUrl = "";
		try {
			String salt = am.getUserSalt(uv);
			String password = ss.getEncryptionPw(uv.getPassword(), salt);
			uv.setPassword(password);
			uv = am.signInCheck(uv);
			if(uv == null) {
				errorMessage = "이메일 혹은 암호를 잘못입력하셨습니다.\n 이메일 혹은 암호를 다시 입력해주세요";
				isSuccess = false;
			}else {
				uv = new UserVo(
						uv.getUserName(),
						uv.getUserBirth(),
						uv.getUserEmail(),
						"",
						"",
						""
				);
				request.getSession().setAttribute("log", uv);
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "SYSTEM ERROR";
		}
		responseData.put("ISSUCCESS", isSuccess);
		responseData.put("ERRORMESSAGE", errorMessage);
		responseData.put("PREURL", preUrl);
		return responseData;
	}

	public Map<String,Object> sendEmail(String userEmail,Map<String,Object> responseData) throws Exception {
		String errorMessage = "";
		boolean isSuccess = true;
		
		String token = ss.getSalt();
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyMMddHHmm");
		
		Date date = new Date(System.currentTimeMillis());
		token += "_" + formatter.format(date);
		
		token = token.replaceAll("&", "");
		token = token.replaceAll("\\+", "");
		token = token.replaceAll("=", "");
		token = token.replaceAll("\\/", "");
		
		
		boolean isExistEmail = setFINDPW_TOKEN(userEmail,token) == 1 ? true : false;
		
		if(!isExistEmail) {
			isSuccess = false;
			errorMessage = "존재하지 않는 이메일 입니다. 이메일을 확인해주세요";
			responseData.put("ISSUCCESS", isSuccess);
			responseData.put("ERRORMESSAGE", errorMessage);
			return responseData;
		}
		
		ms.sendEmail_CRTFC_NMBR(userEmail,token);
		return responseData;
	}

	public int setFINDPW_TOKEN(String userEmail, String token) {
		// TODO Auto-generated method stub
		return am.setFIND_TOKEN(userEmail,token);
	}
	
	public UserVo isExistToken(String token) {
		return am.isExistToken(token);
	}
	
	public boolean expirationCheck(UserVo uv) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyMMddHHmm");
		Date date = new Date(System.currentTimeMillis());
		// 현재시간
		long now = Long.parseLong(formatter.format(date));
		// 토큰을 발급받은 시간
		long exTime = Long.parseLong(uv.getFindPwToken()+"");
		
		if(now - exTime > 10) {
			return false;
		}
		
		return true;
	}

	public Map<String,Object> changePassword(UserVo uv,Map<String,Object> responseData) throws Exception{
		String newPw = uv.getPassword();
		String token = uv.getFindPwToken();
		String salt = "";
		// 요청받은 토큰이 테이터베이스에 있는지 확인
		uv = isExistToken(token);
		if(uv == null) {
			responseData.put("ISSUCCESS", false);
			responseData.put("EXCEPTIONMESSAGE","잘못된 요청입니다.");
			return responseData;
		}
		
		// 요청받은 토큰이 유효한지 확인
		if(!expirationCheck(uv)) {
			responseData.put("ISSUCCESS", false);
			responseData.put("EXCEPTIONMESSAGE","비밀번호 찾기 요청 시간이 만료되었습니다.");
			return responseData;
		}
		
		// 새로운 salt 생성
		salt = ss.getSalt();
		// 새로운 비밀번호 생성
		newPw = ss.getEncryptionPw(newPw, salt);
		// salt, newPw 저장
		uv.setPassword(newPw);
		uv.setSalt(salt);
		boolean isChanged = am.changePassword(uv) == 1 ? true : false;
		// 쿼리문제로 비밀번호가 변경되지 않았을 경우
		if(!isChanged) {
			responseData.put("ISSUCCESS", false);
			responseData.put("EXCEPTIONMESSAGE","SYSTEM ERROR 관리자 문의바람.(쿼리)");
			return responseData;
		}
		
		responseData.put("ISSUCCESS", true);
		return responseData;
	}
	
	// 로그인 완료되면 이동시켜줄 url 설정.
	public Map<String, Object> setPreUrl(HttpServletRequest request, Map<String, Object> responseData) {
		// TODO Auto-generated method stub
		String prevUrl = (String)request.getSession().getAttribute("prevUrl");
		request.getSession().removeAttribute("prevUrl");
		responseData.put("PREURL", prevUrl);
		return responseData;
	}
	
	// 로그인된 회원정보가져오기
	public Object getLoggedUserInfo(HttpServletRequest request) {
		return request.getSession().getAttribute("log");
	}
}