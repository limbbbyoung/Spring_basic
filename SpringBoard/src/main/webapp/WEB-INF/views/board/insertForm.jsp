<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="/resources/uploadAjax.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<style>
	.container { width: 400px;
	  			margin-top: 35px; }
</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		
		<div class="row">
			<div class="col-6">
				    <h1>파일 첨부</h1>
					
					<div class="uploadDiv">
						<input type="file" name="uploadFile" multiple>
					</div>
					
					<div class="uploadResult">
						<ul>
							<!-- 업로드된 파일들이 여기 나열됨. -->
						</ul>
					</div>
					
					<button id="uploadBtn">Upload</button>
			</div><!-- .col-6 -->
			<div class="col-6">
				 <form action="/board/insert" method="post">
			     글제목 : <input class="form-control" type="text" name="title" placeholder="제목을 입력해주세요." required><br/>
			     작성자 : <input class="form-control" type="text" name= "writer" placeholder="작성자" required><br/>
			     <p>글내용 : </p> <textarea class="form-control" cols="50" rows="12" name="content" required></textarea><br/>
			     	<input type="hidden" name="${_csrf.parameterName }"
											value="${_csrf.token }" />
			     <button type="submit" class="btn btn-success" id="submitBtn" >글 작성하기</button>
			     </form>
			</div><!-- .col-6 -->
		</div><!-- .row -->
   
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<script>
	
		$(document).ready(function(){
			
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
				console.log("--------------파일 적재 후 formData 태그 -------------");
				console.log(formData);
				
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
			
			let uploadResult = $(".uploadResult ul");
			
			function showUploadedFile(uploadResultArr){
				let str = "";
				
				$(uploadResultArr).each(function(i, obj){ // Json 데이터를 for문처럼 하나하나 풀어서 쓸 때 each를 활용, 
					// i는 시작변수 0~ , obj는 앞에 uploadResultArr의 자료를 하나하나 가져오는 것
					console.log(obj);
					console.log(obj.image);
					
					if(!obj.image){
						
						var fileCallPath = encodeURIComponent(
								obj.uploadPath + "/"
							 + obj.uuid + "_" + obj.fileName);
						
						str += `<li data-path='\${obj.uploadPath}' data-uuid='\${obj.uuid}'
									data-filename='\${obj.fileName}' data-type='\${obj.image}'>
								<a href='/download?fileName=\${fileCallPath}'>
									<img src='/resources/attach.png'>\${obj.fileName}
								</a>
								<span data-file='\${fileCallPath}' data-type='file'>X</span>
								</li>`;
					} else {
						//str += `<li>\${obj.fileName}</li>`;
						//  수정 후 코드
						// 썸네일이 파일이 아닌 그림파일 다운로드를 위한
						// fileCallPath 선언
						// 원본 파일인 fileCallPath
						var fileCallPath = encodeURIComponent(
								obj.uploadPath + "/"
							 + obj.uuid + "_" + obj.fileName);
						
						
						// 썸네일 파일을 보여줄 수 있도록 fileCallPath 설정
						// 썸네일 파일인 fileCallPath2
						let fileCallPath2 = encodeURIComponent(
								obj.uploadPath + "/s_" 
								+ obj.uuid + "_" + obj.fileName);
						
						console.log(fileCallPath2);
						
						str += `<li data-path='\${obj.uploadPath}' data-uuid='\${obj.uuid}'
								data-filename='\${obj.fileName}' data-type='\${obj.image}'>
									<a href='/download?fileName=\${fileCallPath}'>
										<img src='/display?fileName=\${fileCallPath2}'>
										\${obj.fileName}
									</a>
									<span data-file='\${fileCallPath2}' data-type='image'>X</span>
								</li>`;
					}
				});
				uploadResult.append(str);
			}// showUploadedFile
			
			$(".uploadResult").on("click", "span", function(e){
				// 파일이름을 span태그 내부의 data-file에서 얻어와서 저장
				var targetFile = $(this).data("file"); // .data를 쓰면 data 타입의 뒤쪽에 있는 정보들을 가져옴
				// 이미지 여부를 span태그 내부의 data-type에서 얻어와서 저장
				var type = $(this).data("type"); // ex. data-type의 정보, data-file의 정보
				
				// 클릭한 span태그와 엮어있는 li를 targetLi에 저장
				var targetLi = $(this).closest("li");
				
				$.ajax({
					url: '/deleteFile',
					beforeSend : function(xhr) {
						 xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
						 },
					data: {fileName : targetFile, type:type},
					dataType: 'text',
					type: 'POST',
					success: function(result){
						alert(result);
						// 클릭한 li요소를 화면에서 삭제함(파일삭제 후 화면에서도 삭제)
						targetLi.remove();
					}
				}); // ajax
			}); // click span
			
			$("#submitBtn").on("click", function(e){
				// 1. 버튼 기능 막습니다.
				e.preventDefault();
				
				// 2. 폼태그를 가져옵니다.
				let formObj = $("form");
				
				// 3. 첨부파일과 관련된 정보를 hidden태그들로 만들어 문자로 먼저 저장합니다.
				let str="";
				
				$(".uploadResult ul li").each(function(i, obj){
					
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
			
		});	// document ready
	</script>
</body>
</html>