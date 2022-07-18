<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/resources/restTest/modal.css">
<link rel="stylesheet" type="text/css" href="/resources/uploadResultForDetail.css">
<style>
	form{padding-bottom:10px; }
</style>
<meta charset="UTF-8">
<title>boardDetail</title>
</head>
<body>
	 	${board} <br/>
	 	<hr>
	 	<h3>${board.title }</h3> <br/>
	 	글쓴이 : ${board.writer } <br/>
	 	글내용 : <textarea rows="20" cols="50">${board.content }</textarea>
	 	<form action="/board/delete" method="post">
		 	<input type="hidden" value="${board.bno }" name="bno">
		 	<input type="hidden" name="page" value="${param.page }">
			<input type="hidden" name="searchType" value="${param.searchType}">
			<input type="hidden" name="keyword" value="${param.keyword}">
				<input type="hidden" name="${_csrf.parameterName }"
									value="${_csrf.token }" />
		 	<button class="btn btn-primary" type="submit">글 삭제하기</button>
	 	</form>
	 	
	 	<form action="/board/updateForm" method="post">
		 	<input type="hidden" value="${board.bno }" name="bno">
		 	<input type="hidden" name="page" value="${param.page }">
		    <input type="hidden" name="searchType" value="${param.searchType}">
		    <input type="hidden" name="keyword" value="${param.keyword}">
		    	<input type="hidden" name="${_csrf.parameterName }"
									value="${_csrf.token }" />
		 	<button class="btn btn-primary" type="submit">글 수정하기</button>
	 	</form>
	 	<a class="btn btn-primary" href="/board/list?page=${param.page }&searchType=${param.searchType }&keyword=${param.keyword}">글 목록</a>
	 	<br/>
	 	<hr>
	 	
	 	<!-- 댓글 -->
	 	<div class="row">
	 		<h3 class="text-primary">댓글</h3>
	 		<div id="replies">
	 			<!-- 댓글이 들어갈 위치 -->
	 		</div>
	 	</div><!-- row -->
	 		
	 	<!-- 첨부 파일 -->
	 	<div class="row">
	 		<h3 class="text-primary">첨부파일</h3>
	 		<div id="uploadResult">
	 			<ul>
	 				<!-- 첨부파일이 들어갈 위치 -->
	 			</ul>
	 		</div>
	 	</div><!-- row -->
	 	<br>
	 	
		<hr>
		<div class="row box-box-success">
			<div class="box-header">
				<h2 class="text-primary">댓글 작성</h2>
			</div><!-- header -->
			<div class="box-body">
				<strong>Writer</strong>
				<input type="text" name="replyer" id="newReplyWriter" class="form-control">
				<strong>ReplyText</strong>
				<input type="text" name="reply" id="newReplyText" class="form-control">
			</div><!-- body -->
			<div class="box-footer">
		        <button type="button" id="replyAddBtn" class="btn btn-primary">ADD REPLY</button>
			</div><!-- footer -->
		</div>
 	
 	
	<!-- modal -->
	<div id="modDiv" style="display:none;">
		<div class="modal-title"></div>
		<div>
			<input type="text" id="replyText" style="width: 200px; height: 50px;">
		</div>
		<div>
			<button type="button" id="replyModBtn">Modify</button>
			<button type="button" id="replyDelBtn">Delete</button>
			<button type="button" id="closeBtn">Close</button>
		</div>
	</div>
	
 	<!-- jquery는 이곳에 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
 	<script type="text/javascript">
 		
			// List를 가져오는 로직
			let bno = ${board.bno };
			
			function getAllList(){
				// json 데이터를 얻어오는 로직 실행
				$.getJSON("/replies/all/" + bno, function(data){
					
					let str = "";
					console.log(data.length);
					
					$(data).each(
						function(){					
							// 백틱 문자열 사이에 변수를 넣고 싶다면 \${변수명}을 적습니다.
							// 원래는 \를 왼쪽에 붙일 필요는 없지만
							// jsp에서는 el표기문법이랑 겹치기 때문에 el이 아님을 보여주기 위해
							// 추가로 왼쪽에 \를 붙입니다.
							
							// UNIX 시간을 우리가 알고 있는 형식으로 바꿔보겠습니다.
							let timestamp = this.updateDate;
							// UNIX 시간이 저장된 timestamp를 Date 생성자로 변환합니다.
							let date = new Date(timestamp);
							// 변수 formattedTime에 변환된 시간을 저장해 출력해보겠습니다.
							let formattedTime = `게시일 : 
												\${date.getFullYear()}년
											    \${(date.getMonth()+1)}월
												\${date.getDate()}일`;
												
							str += `<div class='replyLi' data-rno='\${this.rno}'>
									<strong>@\${this.replyer}</strong> - \${formattedTime} <br/>
									<div class="replyText"> \${this.reply} </div> 
									<button type='button' class='btn btn-info'>수정/삭제</button>
									</div>`;
						});
					console.log(str);
					$("#replies").html(str);
				});
			}
			getAllList();
			
			// 이벤트 위임 
			// 1. ul#replies가 이벤트를 걸고 싶은 버튼 전체의 집합이므로 먼저 집합 전체에 이벤트를 겁니다.
			// 2. #replies의 하위 항목 중 최종 목표 태그를 기입해줍니다.
			// 3. 단, 여기서 #replies와 button 사이에 다른 태그가 끼어있다면 경유하는 형식으로 호출해도 됩니다.
			$("#replies").on("click", ".replyLi button", function(){ // .on(동작, 지정 태그(ul->li->button), 기능(function))
				// 4. 콜백함수 내부의 this는 내가 클릭한 button이 됩니다.
				// 1. prev().prev()... 등과 같이 연쇄적으로 prev, next를 걸어서 고르기
				// 2. prev("태그선택자")를 써서 뒤쪽이나 앞쪽 형제 중 조건에 맞는것만 선택
				// 3. siblings("태그선택자")는 next, prev 모두를 범위로 조회합니다.
				let reply = $(this).parent(); // this 키워드를 내부에서 쓸려면 화살표 함수를 쓰면 안됨
				// this는 해당 버튼을 지정, 따라서 버튼의 부모태그는 <li>이다.
				
				// attr() : attribute, attr("태그 내 속성명") => 해당 속성에 부여된 값을 가져옵니다.
				// ex) <li data-rno="33"> => rno에 33을 저장해줍니다. data-rno는 태그 내부에 rno 데이터를 저장한다는 의미.
				let rno = reply.attr("data-rno");
				reply = $(this).prev(".replyText");
				let replytext = reply.text(); // .text()는 태그 안에 있는 모든 텍스트를 다 가져옴
				
				$(".modal-title").html(rno);
				$("#replyText").val(replytext);
				$("#modDiv").show("slow");
				// 화면 기능을 구성할 때 원하는 태그만을 골라서 디테일하게 기능을 구현하는데 있어서
				// 어려움을 느낄 것으로 예상, 그러므로 이 점에 집중해서 화면단 기능 구성
			}); 
		
		<!-- reply insert JS코드 -->
		$("#replyAddBtn").on("click", function(){
			
			let replyer = $("#newReplyWriter").val();
			let reply = $("#newReplyText").val();
			
			var csrfHeaderName = "${_csrf.headerName}";
			var csrfTokenValue="${_csrf.token}";
			
			$.ajax({
				type : 'post',
				url : '/replies',
				beforeSend : function(xhr) {
					 xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
					 },
				headers: {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : 'text',
				data : JSON.stringify({
					bno : bno,
					replyer : replyer,
					reply : reply
				}),
				success : function(result){
					if(result == "SUCCESS"){
						
						alert("등록되었습니다.");
						getAllList();
						$("#newReplyWriter").val('');
						$("#newReplyText").val('');
					}
				}
				
			});
		});
			
		// REPLY DELETE
		$("#replyDelBtn").on("click", function(){
			
				    var rno = $(".modal-title").html();
				    var csrfHeaderName = "${_csrf.headerName}";
					var csrfTokenValue = "${_csrf.token}";
	
				    $.ajax({
				        type : 'delete',
				        url : '/replies/' + rno, 
				        beforeSend : function(xhr) {
						 xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
						 },
				        header : {
				            "Content-Type" : "application/json",
				            "X-HTTP-Method-Override" : "DELETE"
				        },
				        dataType : 'text',
				        success : function(result){
				            console.log("result: " + result);
				            if(result == 'SUCCESS'){
				                alert("삭제 되었습니다.");
				                // modal 닫기
				                $('#modDiv').hide("slow");
				                // 삭제된 이후 목록 가져와서 갱신하기
				                getAllList();
				            }
				        }
				    });
				});
			
		// 수정로직
		$("#replyModBtn").on("click", function(){
				    var rno = $(".modal-title").html();
				    console.log(rno);
				    var replytext = $("#replyText").val();
					// Spring Security를 위한 csrf 토큰 설정
					var csrfHeaderName = "${_csrf.headerName}";
					var csrfTokenValue = "${_csrf.token}";
				    $.ajax({
				        type : 'put',
				        url : '/replies/' + rno, 
				        beforeSend : function(xhr) {
							 xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
						    },
				        header : {
				            "Content-Type" : "application/json",
				            "X-HTTP-Method-Override" : "PUT"
				        },
				        contentType : "application/json",
				        data : JSON.stringify({reply:replytext}),
				        dataType : 'text',
				        success : function(result){
				            console.log("result: " + result);
				            if(result == 'SUCCESS'){
				                alert("수정 되었습니다.");
				                // modal 닫기
				                $('#modDiv').hide("slow");
				                // 삭제된 이후 목록 가져와서 갱신하기
				                getAllList();
				            }
				        }
				    });
				}); // 수정로직
				
				
				// 익명함수 선언 및 호출
				// 우선 함수이기 때문에 호출한다는 점을 명시하기 위해 마지막에 ()를 추가로 붙여준다.
				// 파일 첨부 후 해당 글의 첨부된 파일의 데이터 가져오기
				(function(){
					$.getJSON("/board/getAttachList", {bno:bno}, function(arr){
						console.log(arr);
						
						let str = "";
						
						$(arr).each(function(i, attach){
							// image
							if(attach.fileType){
								let fileCallPath = encodeURIComponent(attach.uploadPath + "/s_" +
										attach.uuid + "_" + attach.fileName);
								console.log(fileCallPath);
								
								str += `<li data-path='\${attach.uploadPath}' data-uuid='\${attach.uuid}'
											data-filename='\${attach.fileName}' data-type='\${attach.fileType}'>
											<div>
												<img src='/display?fileName=\${fileCallPath}'>
											</div>
										</li>`;
							} else { 
								str += `<li data-path='\${attach.uploadPath}' data-uuid='\${attach.uuid}'
											data-filename='\${attach.fileName}' data-type='\${attach.fileType}'>
											<div>
												<span>\${attach.fileName}</span><br>
												<img src='/resources/attach.png' width='100px' height='100px'>
											</div>
										</li>`;
							}
						}); // each 반복문 닫는 부분
						console.log(str);
						$("#uploadResult ul").html(str);
					}); // end getJSON
				})(); // end anonymous
				
				$("#uploadResult").on("click", "li", function(e){
					
					let liObj = $(this);
					
					let path = encodeURIComponent(liObj.data("path") + "/" + liObj.data("uuid")
							+ "_" + liObj.data("filename"));
					
						// download
						self.location = "/download?fileName=" + path;
				});
				
 	</script>
 	
 	<!-- modal 기능들 -->
 	<!-- delete 기능 -->
	<script src="/resources/restTest/delete.js"></script>
	<!-- update 기능 -->
	<script src="/resources/restTest/modify.js"></script>
	<!-- close 기능 -->
	<script src="/resources/restTest/modalclose.js"></script>
	
</body>
</html>