<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>공지사항</title>
		  <link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/main.css" />
	</head>
	
	
	<body>
		<center>
			<table border="0" cellpadding="0" cellspacing="0" width="900px" >
				<tr align="right" valign="middle">
					<td>
						${session_id} 님 환영합니다.
						<a href="${pageContext.request.contextPath}/member/MemberLogOut.me">로그아웃</a>
						<%-- <a href="${pageContext.request.contextPath}/member/MemberList.me">회원 리스트보기</a> --%>
					</td>
				</tr>
			</table>
			<br />
			<br />
			
			<!-- 게시판 등록 -->
			<form action="${pageContext.request.contextPath}/notice/NoticeWriteOk.no" method="post" enctype="multipart/form-data" name="noticeform">
				<table width="900px" border="0" cellpadding="0" cellspacing="0">
					<tr align="center" valign="middle">
						<td><h3>공지사항</h3></td>
					</tr>
				</table>
			
				<table  border="1" cellpadding="0" cellspacing="0" width="900px" >
					<tr height="30px">
						<td align="center" width="150px">
							<div align="center">제 목</div>
						</td>
						<td style="padding-left:10px;">
							<input name="notice_title" type="text" size="50" maxlength="100" value=""/>
						</td>
					</tr>
					<tr height="30px">
						<td align="center" width="150px">
							<div align="center">글쓴이</div>
						</td>
						<td style="padding-left:10px;">
							<input name="member_id" type="text" size="10" maxlength="10" value="${session_id}" readonly />
						</td>
					</tr>
					<tr height="200px">
						<td align="center" width="150px">
							<div align="center">내 용</div>
						</td>
						<td style="padding-left:10px;">
							<textarea name="notice_contents" style="width:700px; height:185px"></textarea>
						</td>
					</tr>
					<tr height="30px">
						<td align="center" width="150px">
							<div align="center">파일 첨부</div>
						</td>
						<td style="padding-left:10px;">
							<input name="notice_file1" type="file"/>
							<input type="button" onclick="cancleFile('board_file1')" value="첨부 삭제">
						</td>
					</tr>
					<tr height="30px">
						<td align="center" width="150px">
							<div align="center">파일 첨부</div>
						</td>
						<td style="padding-left:10px;">
							<input name="notice_file2" type="file"/>
							<input type="button" onclick="cancleFile('board_file2')" value="첨부 삭제">
						</td>
					</tr>
					<tr height="30px">
						<td align="center" width="150px">
							<div align="center">파일 첨부</div>
						</td>
						<td style="padding-left:10px;">
							<input name="notice_file3" type="file"/>
							<input type="button" onclick="cancleFile('board_file3')" value="첨부 삭제">
						</td>
					</tr>
				</table>
				
				<table border="0" cellpadding="0" cellspacing="0" width="900px">
					<tr align="right" valign="middle">
						<td>
							<a href="javascript:addnotice()">[등록]</a>&nbsp;&nbsp;
							<a href="${pageContext.request.contextPath}/notice/NoticeList.no">[목록]</a>&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
			<!-- 게시판 등록 -->
		</center>
	</body>
	<script>
		function addnotice(){
			noticeform.submit();
		}
		
		function cancleFile(fileTagName){
			//ie일 때
			if($.browser.msie){
				$("input[name='"+fileTagName+"']").replaceWith(("input[name='"+fileTagName+"']").clone(true));
			}else{
			//그 외 브라우저	
				$("input[name='"+fileTagName+"']").val("");
			}
		}
		
	</script>
</html>