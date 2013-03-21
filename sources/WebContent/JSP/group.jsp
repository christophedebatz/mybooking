<%@ include file="/JSP/common.jsp" %>

<jsp:include page="/JSP/header.jsp">
   <jsp:param name="title" value="${band.name}" />
   <jsp:param name="currentTab" value="search" />
</jsp:include>

<div class="row" style="padding: 10px;">
	<div class="span4">
		<img src="/myBooking/resources/img/spectacle.png" alt="myBooking" class="img-rounded" style="margin: 5px;" />
	</div>
	<div class="span8">
	
		<h3>${band.name}</h3>
	
		<fieldset>
   			<legend>Informations g&eacute;n&eacute;rales</legend>
   			<div style="float: right;">
   					<button class="btn btn-mini btn-primary" type="button" onclick="window.open('http://www.google.fr/search?q=${band.name}');">${band.name} sur Google ></button>
   				</div>
   				<div style="padding: 5px;"><span class="label">Nom</span> ${band.name}</div>
   				<div style="float: right;">
   					<button class="btn btn-mini btn-primary" type="button" onclick="window.open('http://fr.wikipedia.org/wiki/${band.style}');">${band.style} sur Wikipedia ></button>
   				</div>
				<div style="padding: 5px;"><span class="label">Style</span> ${band.style}</div>
				<p style="padding: 5px;"><span class="label">Description</span>
					<blockquote>
	 					<p>${band.description}</p>
	  					<small>${band.name}, myBooking</small>
					</blockquote>
				</p>
		</fieldset>
		
		
		<p>&nbsp;</p>
		
		<fieldset>
   			<legend>Concerts de ${band.name}</legend>
   				
   				<table class="table table-striped">
					<tr>
						<th>Nom</th>
						<th>Dur&eacute;e</th>
					</tr>
					
					<c:if test="${empty band.concerts}">
						<tr>
							<td align="center" colspan="3">Aucun concert pour le groupe ${band.name} n'est disponible...</td>
						</tr>
					</c:if>
					
					<c:forEach items="${band.concerts}" var="concert">
					
						<tr>
							<td><a href="<c:url value="/concert/${concert.id}" />" titl="Acceder a ce concert">${concert.title}</a></td>
							<td>${concert.duration} mn</td>
						</tr>
					
					</c:forEach>
							
				</table>
   				
		</fieldset>

	</div>
</div>

<%@ include file="/JSP/footer.jsp" %>