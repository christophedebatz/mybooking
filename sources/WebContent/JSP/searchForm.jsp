<%@ include file="/JSP/common.jsp" %>

<jsp:include page="/JSP/header.jsp">
   <jsp:param name="title" value="rechercher" />
   <jsp:param name="currentTab" value="search" />
</jsp:include>

<div class="row" style="padding: 10px;">
	<div class="span4">
		<img src="/myBooking/resources/img/spectacle.png" alt="easyBooking" class="img-rounded" style="margin: 5px;" />
	</div>
	<div class="span8">
		<form:form modelAttribute="searchForm" action="search" method="post">
			<div>
				<fieldset>
	    			<legend>Rechercher</legend>
	    			
	    			<span class="help-block">Vous pouvez chercher un concert, un groupe ou encore une salle. Le caract&egrave;re '%' peut-&ecirc;tre utilis&eacute; comme joker.</span>
	    			
				    <div class="input-append">
						<input name="search" type="text" placeholder="Recherche..."<c:if test="${result}"> value="<c:out value="${search}" />"</c:if> size="40" />
						<div class="btn-group">
							<button type="submit" class="btn" data-loading-text="Loading..."><i class="icon-search"></i> Chercher</button>
						</div>
					</div>
				</fieldset>
			</div>
		</form:form>
		
		<c:if test="${result}">
			<div>
				<fieldset>
		    			<legend>
		    				<c:choose>
		    					<c:when test="${empty search}">
		    						Tous les r&eacute;sultat(s)
		    					</c:when>
		    					<c:otherwise>
		    						R&eacute;sultat(s) pour "${search}"
		    					</c:otherwise>
		    				</c:choose>
		    			</legend>
		    			
		    			<c:if test="${error}">
							<div class="alert alert-error">
								Votre recherche n'a pas aboutie pour des raisons techniques.
							</div>
						</c:if>
						
		    			
						<h4>Concerts</h4>
						
						<table class="table table-striped">
							<tr>
								<th>Nom</th>
								<th>Groupe</th>
								<th>Dur&eacute;e</th>
							</tr>
							
							<c:if test="${empty concerts}">
								<tr>
									<td align="center" colspan="3">Aucun r&eacute;sultat de concerts...</td>
								</tr>
							</c:if>
							
							<c:forEach items="${concerts}" var="concert">
							
								<tr>
									<td><a href="<c:url value="concert/${concert.id}" />" title="Voir le concert">${concert.title}</a></td>
									<td><a href="<c:url value="group/${concert.band.id}" />" title="Voir le groupe">${concert.band.name}</a></td>
									<td>${concert.duration} mn</td>
								</tr>
							
							</c:forEach>
							
						</table>
						
						
						<h4>Groupes</h4>
						
						<table class="table table-striped">
							<tr>
								<th>Nom</th>
								<th>Description</th>
								<th>Style</th>
							</tr>
							
							<c:if test="${empty bands}">
								<tr>
									<td align="center" colspan="3">Aucun r&eacute;sultat de groupe...</td>
								</tr>
							</c:if>
							
							<c:forEach items="${bands}" var="band">
							
								<tr>
									<td><a href="<c:url value="group/${concert.band.id}" />" title="Voir le groupe">${band.name}</a></td>
									<td>${band.description}</td>
									<td>${band.style}</td>
								</tr>
							
							</c:forEach>
							
						</table>
						
						
						<h4>Salles</h4>
						
						<table class="table table-striped">
							<tr>
								<th>Nom</th>
								<th>Adresse</th>
								<th>Capacité</th>
							</tr>
							
							<c:if test="${empty halls}">
								<tr>
									<td align="center" colspan="3">Aucun r&eacute;sultat de salles de concert...</td>
								</tr>
							</c:if>
							
							<c:forEach items="${halls}" var="hall">
							
								<tr>
									<td><a href="<c:url value="hall/${hall.id}" />" title="Voir le lieu">${hall.name}</a></td>
									<td>${hall.road}, ${hall.zipCode} ${hall.city}, ${hall.country}</td>
									<td>${hall.maxCapacity} pers.</td>
								</tr>
							
							</c:forEach>
							
						</table>					
					</fieldset>
				</div>
			</c:if>
		
		
	</div>
</div>


<%@ include file="/JSP/footer.jsp" %>

