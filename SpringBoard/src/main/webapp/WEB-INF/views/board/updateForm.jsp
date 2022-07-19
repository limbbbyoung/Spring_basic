<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<head>
<style>
	.container { width: 400px;
	  			margin-top: 35px; }
	  			
	 /* uploadResult 결과물 css */
	 #uploadResult {
	 	width: 100%;
	 	background-color: gray;
	 }
	 
	 #uploadResult ul {
	 	display: flex;
	 	flex-flow: row;
	 	justify-content: center;
	 	align-items: center;
	 }
	 
	 #uploadResult ul li {
	 	list-style: none;
	 	padding: 10px;
	 	align-content: center;
	 	text-align: center;
	 }
	 
	 #uploadResult ul li img {
	 	width: 100px;
	 }
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${board }
	<div class="container">	
	 <form action="/board/update" method="post">
	     글제목 : <input class="form-control" type="text" name="title" value="${board.title }" required><br/>
	     작성자 : <input class="form-control" type="text" name= "writer" value="${board.writer}" required><br/>
	     <p>글내용 : </p> <textarea class="form-control" cols="50" rows="12" name="content" required>${board.content}</textarea><br/>
	     <input type="hidden" name="bno" value="${board.bno }">
	     <input type="hidden" name="page" value="${param.page }">
	     <input type="hidden" name="searchType" value="${param.searchType}">
	     <input type="hidden" name="keyword" value="${param.keyword}">
	     	<input type="hidden" name="${_csrf.parameterName }"
								value="${_csrf.token }" />
	     <button type="submit" class="btn btn-success" id="submitBtn">글 수정하기</button>
     </form>
     
     <div class="row">
     	<h3 class="text-primary">첨부파일</h3>
     	
     	<div class="form-group-uploadDiv">
     		<input type="file" name='uploadFile' multiple>
     		<button id="uploadBtn">등록</button>
     	</div>
     	
     	<div id="uploadResult">
     		<ul>
     			<!-- 첨부파일이 들어갈 위치 -->
     		</ul>
     	</div>
     </div><!-- row -->
     
    </div><!-- container -->
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript">
    	
     // 어떤 글의 첨부파일인지 확인하기 위해 bno를 선언해 받아 넣어주세요.
     let bno = ${board.bno};
     console.log(bno);
     
 	// csrf 토큰 설정
	let csrfHeaderName = "${_csrf.headerName}";
	let csrfTokenValue="${_csrf.token}";
     
 	// 정규표현식 : 예).com 끝나는 문장 등의 조건이 복잡한 문장을 컴퓨터에게 이해시키기 위한 구문
		var regex = new RegExp("(.*)\.(exe|sh|zip|alz)$");
							// 파일이름 .  exe|sh|zip|alz 인 경우를 체크함
		var maxSize =5242880; // 5Mb
		
		function checkExtension(fileName, fileSize){
			// 파일크기 초과시 종료시킴
			if(fileSize >= maxSize){
				alert("파일 사이즈 초과");
				return false;// return이 있어서 아래쪽 구문은 실행 안됨
			}
			// regex에 표현해둔 정규식과 일치하는지 여부를 체크, 일치하면 true, 아니면 false
			if(regex.test(fileName)){
				alert("해당 확장자를 가진 파일은 업로드할 수 없습니다.");
				return false;
			}
			return true;
		} // checkExtension end
		
		
		let cloneObj = $(".uploadDiv").clone();
		
		$('#uploadBtn').on("click", function(e){
		
			var formData = new FormData();
			
			var inputFile = $("input[name='uploadFile']");
			
			var files = inputFile[0].files;
			console.log(files);
			
			// 파일 데이터를 폼에 집어넣기
			for(var i = 0; i < files.length; i++){
				if(!checkExtension(files[i].name, files[i].size)){
					return false;// 조건에 맞지않은 파일 포함시 onclick 이벤트 함수자체를 종료시켜버림
				}
				
				formData.append("uploadFile", files[i]);
			}
			
			$.ajax({
				url: '/uploadFormAction', 
				beforeSend : function(xhr) {
					 xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
					 },
				processData : false,
				contentType: false,
				data : formData,
				dataType:'json',
				type : 'POST',
				success : function(result){
					alert("uploaded");
					
					console.log(result);
					
					showUploadedFile(result);
					
					$(".uploadDiv").html(cloneObj.html());
				}
			}); // ajax
		});// uploadBtn onclick
     
		let uploadResult = $("#uploadResult ul");
		
		function showUploadedFile(uploadResultArr){
			let str = "";
			
			$(uploadResultArr).each(function(i, obj){ // Json 데이터를 for문처럼 하나하나 풀어서 쓸 때 each를 활용, 
				// i는 시작변수 0~ , obj는 앞에 uploadResultArr의 자료를 하나하나 가져오는 것
				if(!obj.image){
					
					var fileCallPath = encodeURIComponent(
							obj.uploadPath + "/"
						 + obj.uuid + "_" + obj.fileName);
					
					str += `<li data-path='\${obj.uploadPath}' data-uuid='\${obj.uuid}'
								data-filename='\${obj.fileName}' data-type='\${obj.image}'>
								<div>
									<span>\${obj.fileName}</span>
									<button type='button' data-file='\${fileCallPath}'
										data-type='file' class='btn btn-warning btn-circle'>
										<i class='fa fa-times'>X</i>
									</button><br>
									<img src='/resources/attach.png' width='100px' height='100px'>
								</div>
							</li>`;
				} else {
					// 썸네일 파일
					let fileCallPath = encodeURIComponent(
							obj.uploadPath + "/s_" 
							+ obj.uuid + "_" + obj.fileName);
				
					str += `<li data-path='\${obj.uploadPath}' data-uuid='\${obj.uuid}'
							data-filename='\${obj.fileName}' data-type='\${obj.image}'>
								<div>
									<span>\${obj.fileName}</span>
									<button type='button' data-file='\${fileCallPath}'
										data-type='image' class='btn btn-warning btn-circle'>
										<i class='fa fa-times'>X</i>
									</button><br>
									<img src='/display?fileName=\${fileCallPath}'>
								</div>
							</li>`;
				}
			});
			uploadResult.append(str);
		}// showUploadedFile
		
		
	 // 익명함수 선언 및 호출
	 // 우선 함수이기 때문에 호출한다는 점을 명시하기 위해 마지막에 ()를 추가로 붙여준다.
     // 파일 첨부 후 해당 글의 첨부된 파일의 데이터 가져오기
	(function(){
		$.getJSON("/board/getAttachList", {bno:bno}, function(arr){
				console.log(arr);
				
				let str = "";
				
				$(arr).each(function(i, attach){
					if(attach.fileType){
						// image
						let fileCallPath = encodeURIComponent(attach.uploadPath + "/s_" +
								attach.uuid + "_" + attach.fileName);
						console.log(fileCallPath);
						
						str += `<li data-path='\${attach.uploadPath}' data-uuid='\${attach.uuid}'
									data-filename='\${attach.fileName}' data-type='\${attach.fileType}'>
									<div>
										<span> \${attach.fileName} </span>
										<button type='button' data-file= '\${fileCallPath}' 
											data-type='image' class='btn btn-warning btn-circle'>
											<i class='fa fa-times'>X</i>
										</button><br>
										<img src='/display?fileName=\${fileCallPath}'>
									</div>
								</li>`;
					} else { 
						// 이미지 파일이 아닌 일반 파일
						let fileCallPath = encodeURIComponent(attach.uploadPath + "/" +
								attach.uuid + "_" + attach.fileName);
						console.log(fileCallPath);
						
						str += `<li data-path='\${attach.uploadPath}' data-uuid='\${attach.uuid}'
									data-filename='\${attach.fileName}' data-type='\${attach.fileType}'>
									<div>
										<span>\${attach.fileName}</span>
										<button type='button' data-file='\${fileCallPath}' 
											data-type='file' class='btn btn-warning btn-circle'>
											<i class='fa fa-times'>X</i>
										</button><br>
										<img src='/resources/attach.png' width='100px' height='100px'>
									</div>
								</li>`;
					}
				}); // each 반복문 닫는 부분
				console.log(str);
				$("#uploadResult ul").html(str);
			}); // end getJSON
		})(); // end anonymous
		
		$("#uploadResult").on("click", "button", function(e){ // 여기서 e 변수가 내가 클릭한 버튼이 되는거임
			
			if(confirm("선택한 파일을 삭제하시겠습니까?")){
				let targetLi = $(this).closest("li");
				targetLi.remove();
			}
		}); // 파일 업로드시 생기는 버튼 클릭 로직

		$("#submitBtn").on("click", function(e){
			// 1. 버튼 기능 막습니다.
			e.preventDefault();
			
			// 2. 폼태그를 가져옵니다.
			let formObj = $("form");
			
			// 3. 첨부파일과 관련된 정보를 hidden태그들로 만들어 문자로 먼저 저장합니다.
			let str="";
			
			$("#uploadResult ul li").each(function(i, obj){
				
				// $(obj)에 대해서만 .data()를 활용해 데이터를 얻어올 수 있음
				let jobj = $(obj);
				
				str += `<input type='hidden' name='attachList[\${i}].fileName'
							value='\${jobj.data("filename")}'>
				        <input type='hidden' name='attachList[\${i}].uuid'
							value='\${jobj.data("uuid")}'>
				        <input type='hidden' name='attachList[\${i}].uploadPath'
							value='\${jobj.data("path")}'>
						<input type='hidden' name='attachList[\${i}].fileType'
							value='\${jobj.data("type")}'>`;
			}); 
			// str에 정보가 잘 들어왔는지에 대한 디버깅
			console.log(str);
			// form요소 마지막에 str 요소를 추가
			formObj.append(str);
			// 5. formObj.submit()을 이용해 제출기능이 실행되도록 합니다.
			formObj.submit();
		}); // click submitBtn end
		
		
    </script>
</body>
</html>