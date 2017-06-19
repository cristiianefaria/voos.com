<%
	if (session.getAttribute("usuarioLogado") == null) {
		%>
		<script>
		location.href="login.jsp";
		</script>
		<%
	}
%>