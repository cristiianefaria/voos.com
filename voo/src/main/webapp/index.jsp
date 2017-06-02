<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="espacoTopo"></div>
	<div id="features" class="features">
		<div class="container">
			<div class="feature-text text-center">
				<h3>Conheça a VOOS.COM</h3>
			</div>
			<div class="features-section">
				<div class="col-md-4 feature-grid text-center">
					<i class="f1 wow bounceIn" data-wow-delay="0.5s"></i>
					<h3>A companhia</h3>
					<p>Com uma parcela de 80% do mercado, a VOOS.COM é, atualmente,
						a maior operadora de voos regionais na América do Sul.</p>
				</div>
				<div class="col-md-4 feature-grid text-center">
					<i class="f2 wow bounceIn" data-wow-delay="0.5s"></i>
					<h3>Serviço de bordo</h3>
					<p>O serviço de bordo da VOOS.COM é gratuito e relativo à
						distância percorrida. Em todos os casos conta com lanches ou
						refeições.</p>
				</div>
				<div class="col-md-4 feature-grid text-center">
					<i class="f3 wow bounceIn" data-wow-delay="0.5s"></i>
					<h3>Bagagem Expressa</h3>
					<p>Ganhe tempo. Agora você mesmo pode pesar, etiquetar e
						despachar a sua bagagem e agilizar ainda mais o embarque.</p>
				</div>

			</div>
		</div>
	</div>

	<div class="get-started">
		<div class="container">
			<h4 class="wow bounceInLeft" data-wow-delay="0.5s">Todas as
				informações que você precisa</h4>
			<h3 class="wow bounceInRight" data-wow-delay="0.5s">Viaje sem
				dúvidas</h3>
			<a href="#">Leia nosso guia</a>
		</div>
	</div>

	<div id="contact" class="contact">
		<div class="container">
			<div class="contact-text text-center">
				<h3>Fale Cosnosco</h3>
				<p>Preencha o formulário abaixo ou ligue (62) 3000-6302</p>
			</div>
			<div class="contact-form">
				<form>
					<div class="col-md-6 text-box">
						<form>
							<input class="wow fadeInLeft" data-wow-delay="0.5s" type="text"
								placeholder="Nome" required="required" /> <input
								class="wow fadeInLeft" data-wow-delay="0.5s" type="text"
								placeholder="Email" required="required"
								pattern="([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?" /> <input
								class="wow fadeInLeft" data-wow-delay="0.5s" type="text"
								placeholder="Assunto" required="required" />
						</form>
					</div>
					<div class="col-md-6 textarea">
						<form>
							<textarea class="wow fadeInRight" data-wow-delay="0.5s"
								required="required">Messagem</textarea>
							<input class="wow fadeInRight" data-wow-delay="0.5s"
								type="submit" value="ENVIAR" />
						</form>
					</div>
					<div class="clearfix"></div>
				</form>
			</div>

		</div>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>