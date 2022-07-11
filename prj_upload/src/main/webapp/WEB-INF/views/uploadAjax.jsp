<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>Upload with Ajax</h1>
	
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple> <!-- multiple 설정이 여러개의 파일을 올릴 수 있도록 해줌 -->
	</div>
	
	<button id="uploadBtn">Upload</button>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function(){
			
			// 정규표현식 : 예).com 끝나는 문장 등의 조건이 복잡한 문장을 컴퓨터에게 이해시키기 위한 구문
			var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
								// 파일이름 . exe|sh|zip|alz인 경우를 체크함
			var maxSize = 5242880; // 5MB
			
			function checkExtension(fileName, fileSize){
				// 파일크기 초과시 종료시킴
				if(fileSize >= maxSize){
					alert("파일 사이즈 초과");
					return false; // return이 있어서 아래쪽 구문은 실행 안됨
				}
				// regex에 표현해둔 정규식과 일치하는지 여부를 체크, 일치하면 true, 아니면 false
				if(regex.test(fileName)){
					alert("해당 종류의 파일은 업로드할 수 없습니다.");
					return false;
				}
				return true;
			}
			
			$('#uploadBtn').on("click", function(e){
				
				var formData = new FormData();
				console.log("----------빈 폼 생성------------");
				console.log(formData);
				
				var inputFile = $("input[name='uploadFile']");
				console.log("----------파일 목록 체크------------");
				console.log(inputFile);
				
				var files = inputFile[0].files;
				console.log("----------파일들만 뽑아서 체크------------");
				console.log(files);
				
				// 파일 데이터를 폼에 집어넣기
				for(var i = 0; i < files.length; i++){
					if(!checkExtension(files[i].name, files[i].size)){
						// 조건에 맞지 않은 파일 포함시 onclick 이벤트 자체를 종료시켜버림
						return false;   // 해당 if문은 업로드가 가능할 때 true를 리턴하게 되고 
										// true를 받으면 반전연산자를 통해 조건이 false가 되어서 
										// 해당 if문을 실행하지 않고 다음 작업을 넘어감
										// 하지만 업로드가 불가능해 false를 리턴하면 
										// 해당 if문의 조건이 true가 되어서 return false를
										// 하기 때문에 다음 동작인 데이터 집어넣기 자체를 실행하지 않음
					}
					
					formData.append("uploadFile", files[i]);
				}
				console.log("-----------파일 적재 후 formData 태그------------")
				console.log(formData);
				
				$.ajax({
					url: '/uploadAjaxAction',
					processData: false,
					contentType: false,
					data: formData,
					type: 'POST',
					success: function(result){
						alert("Uploaded");
					}
				}); // ajax
			}); // uploadBtn onclick
		}); // document ready
	</script>

</body>
</html>