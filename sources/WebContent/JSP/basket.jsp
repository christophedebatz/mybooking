<%@ include file="/JSP/common.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/JSP/header.jsp">
   <jsp:param name="title" value="votre panier" />
   <jsp:param name="currentTab" value="dropdown" />
</jsp:include>

<div class="row" style="padding: 10px;">
	<div class="span12">
	
		<h3>Votre panier de r&eacute;servation(s)</h3>
	
		<c:if test="${!empty error}">
			<div class="alert alert-error">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				Cette place est d&eacute;j&agrave; dans votre panier ou est indisponible d&eacute;sormais !
			</div>
		</c:if>
	
		<fieldset>
   			<legend>Informations sur ce panier</legend>
   			
   			<c:choose>
	   			<c:when test="${empty order.places}">
	   				<div class="alert alert-block">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						Votre panier est vide.
					</div>
				</c:when>
				<c:otherwise>
  					<div style="padding: 5px;"><span class="label">Statut</span> ${order.status}</div>
					<div style="padding: 5px;"><span class="label">Nb. place</span> ${fn:length(order.places)}</div>
				</c:otherwise>
			</c:choose>
		</fieldset>
		
		
		<p>&nbsp;</p>
		
		<fieldset>
   			<legend>Place(s) r&eacute;serv&eacute;e(s)</legend>
   				
   				<table class="table table-striped">
					<tr>
						<th>ID</th>
						<th>Date</th>
						<th>Concert</th>
						<th>Groupe / Style</th>
						<th>Type de place</th>
						<th>Prix TTC</th>
					</tr>

					<c:if test="${empty order.places}">
						<tr>
							<td align="center" colspan="3">Votre panier ne contier aucune r&eacute;servation pour le moment...</td>
						</tr>
					</c:if>
					
					<c:forEach items="${order.places}" var="place">
					
						<tr>
							<td>${place.id}</td>
							<td>
								<a href="<c:url value="/show/${place.representation.id}" />" title="Voir cette representation">
									<fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${place.representation.date}" />
								</a>
							</td>
							<td>
								<a href="<c:url value="/concert/${place.representation.concert.id}" />" title="Voir ce concert">
									${place.representation.concert.title}
								</a>
							</td>
							<td>
								<a href="<c:url value="/group/${place.representation.concert.band.id}" />" title="Voir ce groupe">
									${place.representation.concert.band.name}
								</a> / ${place.representation.concert.band.style}
							</td>
							<td>${place.type}</td>
							<td>${place.price} EUR.</td>
						</tr>
					
					</c:forEach>
		
					<tr>
						<td colspan="4"></td>
						<td>Frais de dossier</td>
						<td>${fees} EUR.</td>
					</tr>
					<tr>
						<td colspan="4"></d>
						<td><strong>Total NET</strong></td>
						<td><strong>${total} EUR.</strong></td>
					</tr>
					
				</table>
				
				<p align="right">
					<button type="button" class="btn btn-danger" data-loading-text="Loading..."><i class="icon-ok icon-white"></i> Vider le panier</button> &nbsp;
					<button type="button" class="btn btn-primary" data-loading-text="Loading..." onclick="window.location.href='/checkout/order';"><i class="icon-ok icon-white"></i> Valider ces r&eacute;servations !</button>
   				</p>
   				
   				<p>&nbsp;</p>
   				
		</fieldset>

	</div>
</div>

<%@ include file="/JSP/footer.jsp" %>