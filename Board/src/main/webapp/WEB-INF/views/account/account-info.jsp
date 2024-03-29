<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
	.slice-card{
		/* height : 80px; */
	}
	
	.slice-text{
		text-overflow : ellipsis;
		overflow : hidden;
		white-space : nowrap;
	}
</style>
<!-- Account Info wrap -->
<div class = "account-info-wrap mt-5">
	<a href= "/logout">logout</a>
	<div class="card mb-4">
	    <div class="card-body">
	      <h5 class="card-title">내정보</h5>
	      <p class="card-text">이름 : ${ logInfo.userName }</p>
	      <p class="card-text">이메일 : ${ logInfo.userEmail }</p>
	      <p class="card-text">생일 : ${ logInfo.userBirth }</p>
	    </div>
	</div>
	
	<div class="card mb-4">
	    <div class="card-body">
	      <h5 class="card-title">참여중인 모임</h5>
	      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
	    </div>
	</div>
	
	<div class="card mb-4">
	    <div class="card-body">
		    <h5 class="card-title px-1">내가만든 모임</h5>
		    <div class="swiper">
				<div class="swiper-wrapper">
					<div class = "swiper-slide px-1">
						<div class="card slice-card">
							<div class="card-body">
								<h6 class="card-title slice-text">aa</h6>
								<div class="card-text slice-text">Some quick example text to build on the card title and make up the bulk of the card's content.</div>
								<div class="card-text slice-text">Some quick example text to build on the card title and make up the bulk of the card's content.</div>
							</div>
						</div>
					</div>
					<div class = "swiper-slide px-1">
						<div class="card slice-card">
							<div class="card-body">
								<h6 class="card-title slice-text">aa</h6>
								<p class="card-text slice-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
							</div>
						</div>
					</div>
					<div class = "swiper-slide px-1">
						<div class="card slice-card">
							<div class="card-body">
								<h6 class="card-title slice-text">aa</h6>
								<p class="card-text slice-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
							</div>
						</div>
					</div>
					<div class = "swiper-slide px-1">
						<div class="card slice-card">
							<div class="card-body">
								<h6 class="card-title slice-text">aa</h6>
								<p class="card-text slice-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
							</div>
						</div>
					</div>
					<div class = "swiper-slide px-1">
						<div class="card slice-card">
							<div class="card-body">
								<h6 class="card-title slice-text">aa</h6>
								<p class="card-text slice-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
							</div>
						</div>
					</div>
					<div class = "swiper-slide px-1">
						<div class="card slice-card">
							<div class="card-body">
								<h6 class="card-title slice-text">aa</h6>
								<p class="card-text slice-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
							</div>
						</div>
					</div>
					<div class = "swiper-slide px-1">
						<div class="card slice-card">
							<div class="card-body">
								<h6 class="card-title slice-text">aa</h6>
								<p class="card-text slice-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
							</div>
						</div>
					</div>
					<div class = "swiper-slide px-1">
						<div class="card slice-card">
							<div class="card-body">
								<h6 class="card-title slice-text">aa</h6>
								<p class="card-text slice-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
							</div>
						</div>
					</div>
					<div class = "swiper-slide px-1">
						<div class="card slice-card">
							<div class="card-body">
								<h6 class="card-title slice-text">aa</h6>
								<p class="card-text slice-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
							</div>
						</div>
					</div>
					<div class = "swiper-slide px-1">
						<div class="card slice-card">
							<div class="card-body">
								<h6 class="card-title slice-text">aa</h6>
								<p class="card-text slice-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
							</div>
						</div>
					</div>
				</div>
				<div class="swiper-pagination" style = "position : relative;top : 1px;"></div>
			</div>
	    </div>
	</div>
	
	<div class="card">
	    <div class="card-body">
	      <h5 class="card-title">내가 작성한 댓글</h5>
	      <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
	      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
	    </div>
	</div>
</div>
