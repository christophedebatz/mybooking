<%@ include file="/JSP/common.jsp" %>

<jsp:include page="/JSP/header.jsp">
   <jsp:param name="title" value="bienvenue" />
   <jsp:param name="currentTab" value="" />
</jsp:include>

<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
    <h3 id="myModalLabel">Recherche</h3>
  </div>
  
  <form:form modelAttribute="searchForm" action="search" method="post">
  <div class="modal-body">
    <p>Merci d'entrer vos crit&egrave;res de recherche.</p>
    
    <div class="input-prepend">
		<span class="add-on"><i class="icon-search"></i></span>
		<input name="search" type="text" placeholder="Recherche..." size="40" />
	</div>
  </div>
  
  <div class="modal-footer">
  	<button class="btn" data-dismiss="modal" aria-hidden="true">Fermer</button>
    <button type="submit" class="btn btn-primary">Chercher</button>
  </div>
  
  </form:form>
</div>

<div class="hero-unit">
	<div style="float: right;">
		<img height="200" width="252" src="/myBooking/resources/img/spectacle.png" alt="easyBooking" class="img-rounded" style="margin: 5px;" />
	</div>
	
  <h1>Bienvenue,</h1>
  <p>Bienvenue sur myBooking, l'application de r&eacute;servation de concert de votre entreprise.<br />
  C'est simple : vous recherchez un concert puis vous r&eacute;servez votre place.</p>
  <p>
  	<a href="#myModal" data-toggle="modal" role="button" class="btn btn-primary btn-large">Rechercher</a> <strong>un concert, une salle ou un groupe</strong>.
  </p>
  
</div>


<div style="margin: 25px;">
  	
 	<fieldset>
 		<h3>Derniers concerts</h3>
 	
	  	<ul class="thumbnails">
	  	<c:forEach items="${last}" var="last">
		  	<li class="span3">
			  	<div class="thumbnail">
			    	<div class="caption">
			        	<h4>${last.title} [${last.duration} mn]</h4>
			        	<p>&Agrave; propos de <strong>${last.band.name}</strong>,<br />
			        		${last.band.description}</p>
			            <p><a href="#" class="btn btn-primary">Repr&eacute;sentations ></a> <a href="#" class="btn">Voir ></a></p>
			         </div>
			    </div>
			</li>
	  </c:forEach>
	  </ul>
	</fieldset>
  
</div>

<%@ include file="/JSP/footer.jsp" %>