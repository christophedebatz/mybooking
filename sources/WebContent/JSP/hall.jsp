<%@ include file="/JSP/common.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/JSP/header.jsp">
   <jsp:param name="title" value="${salle.name}" />
   <jsp:param name="currentTab" value="search" />
</jsp:include>

<div class="row" style="padding: 10px;">
	<div class="span4">
		<img src="/myBooking/resources/img/spectacle.png" alt="myBooking" class="img-rounded" style="margin: 5px;" />
	</div>
	<div class="span8">
	
		<h3>${salle.name}</h3>
	
		<fieldset>
   			<legend>Informations g&eacute;n&eacute;rales</legend>
   			<div style="float: right;">
   					<button class="btn btn-mini btn-primary" type="button" onclick="window.open('http://www.google.fr/search?q=${salle.name}');">${salle.name} sur Google ></button>
  			</div>
  			<div style="padding: 5px;"><span class="label">Nom</span> ${salle.name}</div>
			<div style="float: right;">
   					<button class="btn btn-mini btn-primary" type="button" onclick="window.open('https://maps.google.fr/maps?q=${salle.road},+${salle.zipCode}+${salle.city},+${salle.country}');">Localiser sur Google Maps ></button>
  			</div>
  			<div style="padding: 5px;"><span class="label">Adresse</span> ${salle.road}, ${salle.zipCode} ${salle.city}, ${salle.country}</div>
			<div style="padding: 5px;"><span class="label">Capacit&eacute;</span> ${salle.maxCapacity} pers.</div>
		</fieldset>
		
		<p>&nbsp;</p>
		
		<fieldset>
   			<legend>Repr&eacute;sentation(s) dans ce lieu</legend>
   				
   				<table class="table table-striped">
					<tr>
						<th>Date</th>
						<th>Concert</th>
						<th>Groupe</th>
						<th>&nbsp;</th>
					</tr>
					
					<c:if test="${empty salle.representations}">
						<tr>
							<td align="center" colspan="3">Aucune representation ne se donne dans cette salle.</td>
						</tr>
					</c:if>
					
					<c:forEach items="${salle.representations}" var="repr">
					
						<tr>
							<td><a href="<c:url value="/show/${repr.id}" />" title="Acceder a cette representation"><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${repr.date}" /></a></td>
							<td><a href="<c:url value="/concert/${repr.concert.id}" />" title="Acceder a ce concert">${repr.concert.title}</a></td>
							<td><a href="<c:url value="/group/${repr.concert.band.id}" />" title="Voir ce groupe">${repr.concert.band.name}</a></td>
							<td>
								<button class="btn btn-mini btn-primary" type="button" onclick="window.location.href='<c:url value="/show/${repr.id}" />';" title="Consulter les tarifs">Tarifs</button>
							</td>
						</tr>
					
					</c:forEach>
							
				</table>
   				
		</fieldset>

	</div>
</div>

<%@ include file="/JSP/footer.jsp" %>