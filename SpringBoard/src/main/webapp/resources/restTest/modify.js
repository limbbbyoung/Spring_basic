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
					});