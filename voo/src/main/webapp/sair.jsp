<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SAIR</title>

</head>
<body>

 	<form method="POST" action="Login" name="formSair" id="formSair">
		<input type="hidden" name="sair" id="sair" value="sair">
	</form>
	<script type="text/javascript">
	document.formSair.submit();	
	</script>
</body>
</html>