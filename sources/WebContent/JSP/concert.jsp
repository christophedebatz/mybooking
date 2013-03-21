<%@ include file="/JSP/common.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/JSP/header.jsp">
   <jsp:param name="title" value="${concert.title}" />
   <jsp:param name="currentTab" value="search" />
</jsp:include>

<div class="row" style="padding: 10px;">
	<div class="span4">
		<img src="/myBooking/resources/img/spectacle.png" alt="myBooking" class="img-rounded" style="margin: 5px;" />
	</div>
	<div class="span8">
	
		<h3>${concert.title}</h3>
	
		<fieldset>
   			<legend>Informations g&eacute;n&eacute;rales</legend>
   				<div style="padding: 5px;"><span class="label">Dur&eacute;e</span> ${concert.duration} mn</div>
   				<div style="float: right;">
   					<button class="btn btn-mini btn-primary" type="button" onclick="window.open('http://www.google.fr/search?q=${concert.band.name}');">${concert.band.name} sur Google ></button>
   				</div>
				<div style="padding: 5px;"><span class="label">Groupe</span> <a href="<c:url value="/group/${concert.band.id}" />">${concert.band.name}</a></div>
				<div style="padding: 5px;"><span class="label">Style</span> ${concert.band.style}</div>
		</fieldset>
		
		<p>&nbsp;</p>
		
		<fieldset>
   			<legend>Repr&eacute;sentation(s) propos&eacute;e(s)</legend>
   				
   				<table class="table table-striped">
					<tr>
						<th>Date</th>
						<th>Salle</th>
						<th>Nb. places</th>
					</tr>
					
					<c:if test="${empty concert.representations}">
						<tr>
							<td align="center" colspan="3">Aucune repr&eacute;sentation n'est disponible pour ce concert...</td>
						</tr>
					</c:if>
					
					<c:forEach items="${concert.representations}" var="repr">
					
						<tr>
							<td><a href="<c:url value="/show/${repr.id}" />" title="Acceder a cette representation">
								<fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${repr.date}" />
							</a></td>
							<td><a href="<c:url value="/hall/${repr.salle.id}" />" title="Voir le lieu">${repr.salle.name}</a></td>
							<td>${fn:length(repr.places)}</td>
						</tr>
					
					</c:forEach>
							
				</table>
   				
		</fieldset>
	</div>
</div>

<%@ include file="/JSP/footer.jsp" %>