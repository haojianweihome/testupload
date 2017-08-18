<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="upload.ht" enctype="multipart/form-data" method="post">
<table>
	<tr>
		<td>文件描述：</td>
		<td><intput type="test" name="description" ></td>
	</tr>
	<tr>
		<td>选择文件：</td>
		<td>
			<input type="file" name="file">
		</td>
	</tr>
	<tr>
		<td>
			<input type="submit" value="上传">
		</td>
	</tr>
</table>
</form>
</body>
</html>