<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<head>
<meta charset="UTF-8">
<title>boardList</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1 style="color: Green;">글 목록</h1>
			<table class="table table-success">
				<thead>
					<tr>
						<th>글번호</th>
						<th>글제목</th>
						<th>글쓴이</th>
						<th>글내용</th>
						<th>쓴 날짜</th>
						<th>수정 날짜</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach var="board" items="${boardList }">
							<tr>
							<td>${board.bno }</td>
							<td><a href="/board/detail?bno=${board.bno}&page=${pageMaker.cri.page}
							      &searchType=${pageMaker.cri.searchType}&keyword=${pageMaker.cri.keyword}">${board.title }</a></td>
							<td>${board.writer }</td>
							<td>${board.content }</td>
							<td>${board.regdate }</td>
							<td>${board.updatedate }</td>
							</tr>						
						</c:forEach>
					</tr>
				</tbody>
			</table>
			<a class="btn btn-primary" href="/board/insert">글 쓰기</a>
			<hr/>
			<ul class="pagination">
				<!-- Prev -->
				<c:if test="${pageMaker.prev }">
					<li class="page-item">
						<a class="page-link" href="/board/list?page=${pageMaker.startPage - 1 }
						&searchType=${pageMaker.cri.searchType }&keyword=${pageMaker.cri.keyword}" aria-lable="Prev">
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage }"
						end="${pageMaker.endPage }"
						var="pNum">
					<li class="page-item 
						<c:out value="${pageMaker.cri.page == pNum ? 'active' : '' }"/>">
						<a class="page-link"
							href="/board/list?page=${pNum }&searchType=${pageMaker.cri.searchType }&keyword=${pageMaker.cri.keyword}">
							${pNum }
						</a>
					</li>	
				</c:forEach>
				<!-- Next -->
				<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
					<li class="page-item">
						<a class="page-link"
							href="/board/list?page=${pageMaker.endPage + 1}&searchType=${pageMaker.cri.searchType }&keyword=${pageMaker.cri.keyword}">
							&raquo;	
						</a>
					</li>
				</c:if>
			</ul>
		</div><!-- .row 끝나는 지점 -->
		<div class="row">
			<div class="box-body">
				<select name="searchType" class="selectpicker">
					<option value="n"
					<c:out value="${pageMaker.cri.searchType == 'n' ? 'selected' : '' }"/>>
						-
					</option>
					<option value="t"
					<c:out value="${pageMaker.cri.searchType == 't' ? 'selected' : '' }"/>>
						제목
					</option>
					<option value="c"
					<c:out value="${pageMaker.cri.searchType == 'c' ? 'selected' : '' }"/>>
						본문내용
					</option>
					<option value="w"
					<c:out value="${pageMaker.cri.searchType == 'w' ? 'selected' : '' }"/>>
						글쓴이
					</option>
					<option value="tc"
					<c:out value="${pageMaker.cri.searchType == 'tc' ? 'selected' : '' }"/>>
						제목+본문내용
					</option>
					<option value="cw"
					<c:out value="${pageMaker.cri.searchType == 'cw' ? 'selected' : '' }"/>>
						글쓴이+본문내용
					</option>
					<option value="tcw"
					<c:out value="${pageMaker.cri.searchType == 'tcw' ? 'selected' : '' }"/>>
						모든내용
					</option>
				</select>
				<input type="text" name="keyword" id="keywordInput" 
					value="${pageMaker.cri.keyword }">
				<button class="btn btn-success" id="searchBtn">Search</button>
			</div><!-- .box-body  -->
		</div><!-- .row  -->
	</div><!-- .container  -->
	
	<script type="text/javascript">
	    // 검색버튼 작동
		$('#searchBtn').on("click", function(event){
			
			self.location = "list"
				+ "?page=1"
				+ "&searchType="
				+ $('select option:selected').val()
				+ "&keyword=" + $('#keywordInput').val();
		});
	</script>
	${pageMaker }
</body>
</html>