<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VOOS.COM</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="espacoTopo"></div>

	<div class="banner-bg">
		<video autoplay="autoplay" muted="muted" poster="wall_icons/my.jpg" width="100%"  loop>
			<source src="app/assets/srix.mp4" type="video/mp4" type="video/mp4">
		</video>
	</div>

	<!--features-->
	<div id="features" class="features">
		<div class="container">
			<div class="feature-text text-center">
				<h3>Conheça a VOOS.COM</h3>
			</div>
			<div class="features-section">
				<div class="col-md-4 col-xs-12">
					<i class="fa fa-plane fa-3x"></i>
					<h3>A Companhia</h3>
					<p>Com uma parcela de 80% do mercado, a VOOS.COM é, atualmente,
						a maior operadora de voos regionais na América do Sul.</p>
				</div>
				<div class="col-md-4 col-xs-12">
					<i class="fa fa-credit-card-alt fa-3x"></i>
					<h3>Pague em parcelas</h3>
					<p>Reserve agora e pague em até 12 parcelas, trabalhamos com
						todos os cartões de crédito!</p>
				</div>
				<div class="col-md-4 col-xs-12">
					<i class="fa fa-check-square-o fa-3x"></i>
					<h3>VOOS.COM Fidelidade</h3>
					<p>O programa faz parte da Rede Multiplus, a maior e melhor
						rede de fidelização do país, você viaja e acumula pontos.</p>
				</div>

			</div>
		</div>
	</div>
	<!---->
	<!--banner-->
	<div class="get-started">
		<div class="container">
			<h4 class="wow bounceInLeft" data-wow-delay="0.5s">Todas as
				informações que você precisa</h4>
			<h3 class="wow bounceInRight" data-wow-delay="0.5s">Acesse nosso
				projeto</h3>
			<a href="https://github.com/Brunoroh/voos.com" target="_blank">Veja
				agora</a>
		</div>
	</div>
	<!---->
	<!--rotaçao de imagens-->
	<div id="screenshots" class="screenshots">
		<div class="container">
			<div class="screen-text text-center">
				<h3>Lugares Mais Visitados</h3>
				<p>Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
			</div>

			<div id="owl-demo" class="owl-carousel">
				<div class="item text-center guide-sliders">
					<div class="col-md-4 image-grid">
						<img src="app/images/sc1.jpg">
					</div>
					<div class="col-md-4 image-grid">
						<img src="app/images/sc2.jpg">
					</div>
					<div class="col-md-4 image-grid">
						<img src="app/images/sc3.jpg">
					</div>
				</div>
				<div class="item text-center guide-sliders">
					<div class="col-md-4 image-grid">
						<img src="app/images/sc4.jpg">
					</div>
					<div class="col-md-4 image-grid">
						<img src="app/images/sc5.jpg">
					</div>
					<div class="col-md-4 image-grid">
						<img src="app/images/sc1.jpg">
						<div class="caption">
							<p>Lorem ipsum...</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!---->

	<jsp:include page="footer.jsp" />
	<script src="app/js/carousel.js"></script>
</body>
</html>