<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- .css, .js, 그림파일 등은 src/main/webapp/resources 폴더 아래에 저장한 다음
	/resources/ 경로 형식으로 적으면 가져올 수 있습니다.
	이렇게 경로가 자동으로 잡히는 이유는 servlet-context.xml에 설정이 잡혀있기 때문입니다. -->
	<link rel="stylesheet" href="/resources/restTest/modal.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>Ajax 테스트</h2>
	
	<ul id="replies">
	
	</ul>
	
	<div>
		<div>
			REPLYER <input type="text" name="replyer" id="newReplyWriter">
		</div>
		<div>
			REPLY TEXT <input type="text" name="reply" id="newReplyText">
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
	</div>
	
	<!-- jquery는 이곳에 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
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
	
	<script type="text/javascript">
		
		// List를 가져오는 로직
		var bno = 2100;
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
						str += `<li data-rno='\${this.rno}' class='replyLi'>
							\${this.rno} : \${this.reply}
								<button>수정/삭제</button></li>`;
					});
				console.log(str);
				$("#replies").html(str);
			});
		} 
		getAllList();
		
		<!-- reply insert JS코드 -->
		$("#replyAddBtn").on("click", function(){
			
			let replyer = $("#newReplyWriter").val();
			let reply = $("#newReplyText").val();
			
			$.ajax({
				type : 'post',
				url : '/replies',
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
		
		// 이벤트 위임 
		// 1. ul#replies가 이벤트를 걸고 싶은 버튼 전체의 집합이므로 먼저 집합 전체에 이벤트를 겁니다.
		// 2. #replies의 하위 항목 중 최종 목표 태그를 기입해줍니다.
		// 3. 단, 여기서 #replies와 button 사이에 다른 태그가 끼어있다면 경유하는 형식으로 호출해도 됩니다.
		$("#replies").on("click", ".replyLi button", function(){ // .on(동작, 지정 태그(ul->li->button), 기능(function))
			// 4. 콜백함수 내부의 this는 내가 클릭한 button이 됩니다.
			let reply = $(this).parent(); // this 키워드를 내부에서 쓸려면 화살표 함수를 쓰면 안됨
			// this는 해당 버튼을 지정, 따라서 버튼의 부모태그는 <li>이다.
			
			// attr() : attribute, attr("태그 내 속성명") => 해당 속성에 부여된 값을 가져옵니다.
			// ex) <li data-rno="33"> => rno에 33을 저장해줍니다. data-rno는 태그 내부에 rno 데이터를 저장한다는 의미.
			let rno = reply.attr("data-rno");
			let replytext = reply.text(); // .text()는 태그 안에 있는 모든 텍스트를 다 가져옴
			
			$(".modal-title").html(rno);
			$("#replyText").val(replytext);
			$("#modDiv").show("slow");
			// 화면 기능을 구성할 때 원하는 태그만을 골라서 디테일하게 기능을 구현하는데 있어서
			// 어려움을 느낄 것으로 예상, 그러므로 이 점에 집중해서 화면단 기능 구성
		}); 
		
		
	</script>
</body>
</html>