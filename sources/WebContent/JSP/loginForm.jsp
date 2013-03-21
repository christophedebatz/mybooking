<%@ include file="/JSP/common.jsp" %>

<jsp:include page="/JSP/header.jsp">
   <jsp:param name="title" value="se connecter" />
   <jsp:param name="currentTab" value="login" />
</jsp:include>

<div class="row" style="padding: 10px;">
	<div class="span4">
		<img src="/myBooking/resources/img/spectacle.png" alt="easyBooking" class="img-rounded" style="margin: 5px;" />
	</div>
	<div class="span4">
		<form:form modelAttribute="loginForm" action="login" method="post">
			<div>
				<fieldset>
	    			<legend>Se connecter</legend>
	    			
	    			<c:if test="${loginForm.next != null}">
	    				<div class="alert alert-info">
							Merci de vous identifier avant de continuer.
						</div>
					</c:if>
	    			
	    			<c:if test="${error}">
						<div class="alert alert-error">
							L'identification a échoué... Veuillez recommencer.
						</div>
					</c:if>
	    			
					<span class="help-block">Merci de remplir le formulaire pour vous connecter au site.</span>
					
					<div class="input-prepend">
						<span class="add-on"><i class="icon-user"></i></span>
						<input name="userName" value="${loginForm.userName}" type="text" placeholder="Nom d'utilisateur" size="30" />
					</div>
				
					<div class="input-prepend">
						<span class="add-on"><i class="icon-eye-open"></i></span>
						<input name="password" value="${loginForm.password}" type="password" placeholder="Mot de passe" size="30" />
					</div>
				
					<label class="">Se souvenir de moi 
					<input type="checkbox" name="memorize" value="1"></label>
					
					<input type="hidden" name="next" value="${loginForm.next}" />
	
					<p style="padding-top: 20px;">
						<button type="submit" class="btn" data-loading-text="Loading..."><i class="icon-ok"></i> Valider</button>
					</p>
				</fieldset>
			</div>
		</form:form>
	</div>
	<div class="span4">
		<fieldset>
			<legend>Plus d'options...</legend>
		
			<ul style="list-style-type: none;">
				<li><a href="<c:url value="/login/retrieve" />">
					<span class="icon-envelope"></span> Retrouver son mot de passe
				</a></li>
				<li><a href="<c:url value="/register" />">
					<span class="icon-plus"></span> Inscris toi en 1 minute
				</a></li>
				<li><a href="<c:url value="/search" />">
					<span class="icon-search"></span> Chercher un spectacle
				</a></li>
			</ul>
			
		</fieldset>
	</div>
</div>

<%@ include file="/JSP/footer.jsp" %>
