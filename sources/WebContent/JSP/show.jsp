<%@ include file="/JSP/common.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/JSP/header.jsp">
   <jsp:param name="title" value="representation pour ${repr.concert.title}" />
   <jsp:param name="currentTab" value="search" />
</jsp:include>

<div class="row" style="padding: 10px;">
	<div class="span4">
		<img src="/myBooking/resources/img/spectacle.png" alt="myBooking" class="img-rounded" style="margin: 5px;" />
	</div>
	<div class="span8">
		<h3 style="padding-left: 30px; float: right;">
			<span class="icon-music"></span> ${repr.concert.title}
		</h3>
		<h3>
			Le <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${repr.date}" />
		</h3>
		
		<fieldset>
   			<legend>&Agrave; propos de cette repr&eacute;sentation...</legend>
   				<div style="padding: 5px;">
   					<span class="label">Concert</span> <a href="<c:url value="/concert/${repr.concert.id}" />" title="Voir ce concert">${repr.concert.title}</a>
   				</div>
   				<div style="padding: 5px;">
   					<span class="label">Date</span> 
   					<fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${repr.date}" />
   				</div>
   				<div style="padding: 5px;">
   					<span class="label">Lieu</span> <a href="<c:url value="/hall/${repr.salle.id}" />" title="Voir ce lieu">${repr.salle.name}</a>
   				</div>
				<div style="padding: 5px;">
					<span class="label">Groupe</span> 
					<a href="<c:url value="/group/${repr.concert.band.id}" />" title="Voir ce groupe">${repr.concert.band.name}</a>
					<span class="label">Style</span> ${repr.concert.band.style}
				</div>
		</fieldset>
		
		<p>&nbsp;</p>
		
		<fieldset>
   			<legend>Places et tarifs</legend>
   				
   				<table class="table table-striped">
					<tr>
						<th>
							<a href="<c:url value="/show/${repr.id}?oType=${oType}" />" title="Trier par type de place">
								Type de place 
								<c:choose>
									<c:when test="${oType == 1}"><span class="icon-chevron-up"></span></c:when>
									<c:otherwise><span class="icon-chevron-down"></span></c:otherwise>
								</c:choose>
							</a>
						</th>
						<th>
							<a href="<c:url value="/show/${repr.id}?oPrice=${oPrice}" />" title="Trier par prix">
								Prix unitaire TTC 
								<c:choose>
									<c:when test="${oPrice == 1}"><span class="icon-chevron-up"></span></c:when>
									<c:otherwise><span class="icon-chevron-down"></span></c:otherwise>
								</c:choose>
							</a>
						</th>
						<th>&nbsp;</th>
					</tr>
					
					<c:if test="${empty places}">
						<tr>
							<td align="center" colspan="3">Aucune place n'est disponible pour cette repr&eacute;sentation...</td>
						</tr>
					</c:if>
					
					<c:forEach items="${places}" var="place">
					
						<c:if test="${place.customerOrder == null}">
					
							<tr>
								<td>${place.type}</td>
								<td>${place.price} EUR.</td>
								<td>
									<a class="btn btn-success" type="button" href="<c:url value="/checkout/${place.id}" />" title="Acheter cette place">
										<i class="icon-ok icon-white"></i> Acheter
									</a>
								</td>
							</tr>
						
						</c:if>
					
					</c:forEach>
							
				</table>
   				
		</fieldset>

	</div>
</div>

<%@ include file="/JSP/footer.jsp" %>