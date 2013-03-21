<%@ include file="/JSP/common.jsp" %>

<jsp:include page="/JSP/header.jsp">
   <jsp:param name="title" value="inscription" />
   <jsp:param name="currentTab" value="register" />
</jsp:include>

<div class="row" style="padding: 10px;">
	<div class="span4">
		<img src="/myBooking/resources/img/spectacle.png" alt="easyBooking" class="img-rounded" style="margin: 5px;" />
	
		<p style="padding: 10px; font-size: 10px;text-align:justify;">
			L'application de la loi "Informatique et Libert&eacute;" vous autorise &agrave; modifier vos informations ult&egrave;rieurement via l'edition du profil ainsi qu'&agrave; supprimer votre compte de notre serveur. Toutes vos informations seront supprim&eacute;es de nos installations. En vous inscrivant sur le site, vous acceptez implicitement nos CGU.
		</p>
	</div>
	
	<c:choose>
		<c:when test="${customer != null}">
		
			<div class="span8 alert alert-success">
				<p>Bonjour ${customer.username},<br />
				Nous sommes heureux de vous accueillir sur myBooking, le service de réservation de concerts et spectacles dans toute la France. Vous pouvez désormais <a href="<c:url value="/login" />"></a>vous connecter en cliquant ici</a>.</p>
			</div>
		
		</c:when>
		<c:otherwise>
		
			<div class="span4">
				<form:form modelAttribute="registerForm" action="register" method="post">
	
					<fieldset>
					    <legend>S'inscrire</legend>
					    
					    <c:if test="${error}">
							<div class="alert alert-error">
								<c:choose>
				      				<c:when test="${errorNo == 1}">
				      					Ce nom d'utilisateur semble d&eacute;j&agrave; exister.
				      				</c:when>
				      				<c:when test="${errorNo == 2}">
				      					Vous devez remplir tous les champs.
				      				</c:when>
				      				<c:otherwise>
				      					L'inscription a &eacute;chou&eacute;. Veuillez recommencer.
				      				</c:otherwise>
								</c:choose>
							</div>
						</c:if>
						
						<span class="help-block">Merci de remplir le formulaire pour vous inscrire &agrave; l'espace.</span>
					
					    <label>
					    	<input name="userName" type="text" placeholder="Nom d'utilisateur"  />
					    </label>
					    <label>
					    	<input name="password" type="text" placeholder="Mot de passe" />
					   	</label>
					   	<label>
					    	<input name="email" type="text" placeholder="E-mail" />
					    </label>
					    <label>
					    	<input name="firstName" type="text" placeholder="Prenom" />
					    </label>
					    <label>
					    	<input name="lastName" type="text" placeholder="Nom" />
					    </label>
					    <button type="submit" class="btn" data-loading-text="Loading..."><i class="icon-ok"></i> Valider</button>
					    <button type="reset" class="btn"><i class="icon-remove"></i> RAZ</button>
					</fieldset>
					
				</form:form>
			</div>
			<div class="span4">
				<fieldset>
					<legend>Plus d'options...</legend>
				
					<ul style="list-style-type: none;">
						<li><a href="<c:url value="/login/retrieve" />">
							<span class="icon-envelope"></span> Retrouver son mot de passe
						</a></li>
						<li><a href="<c:url value="/login" />">
							<span class="icon-briefcase"></span> Se connecter
						</a></li>
						<li><a href="<c:url value="/search" />">
							<span class="icon-search"></span> Chercher un spectacle
						</a></li>
					</ul>
					
				</fieldset>
			</div>
		
		</c:otherwise>
	</c:choose>
	
	
</div>

<%@ include file="/JSP/footer.jsp" %>
