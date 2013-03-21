<%@ include file="/JSP/common.jsp" %>

<jsp:include page="/JSP/header.jsp">
   <jsp:param name="title" value="edition profil" />
   <jsp:param name="currentTab" value="dropdown" />
</jsp:include>

<div class="row" style="padding: 10px;">
	<div class="span4">
		<img src="/myBooking/resources/img/spectacle.png" alt="easyBooking" class="img-rounded" style="margin: 5px;" />
	</div>
	
	<div class="span8">
		<form:form modelAttribute="registerForm" action="profile" method="post">

			<fieldset>
			    <legend>Editer mon profil</legend>
			    
			    <c:if test="${error}">
					<div class="alert alert-error">
		      			L'edition n'a pas fonctionn&eacute;. Merci de r&eacute;essayer.
					</div>
				</c:if>
				
				<span class="help-block">Merci de remplir le formulaire pour &eacute;diter vos informations.</span>

				<p>Nom d'utilisateur : <strong>${customer.username}</strong></p>

			    <label>
			    	<input name="password" type="text" value="${customer.password}" placeholder="Mot de passe" />
			   	</label>
			   	<label>
			    	<input name="email" type="text" value="${customer.email}" placeholder="E-mail" />
			    </label>
			    <label>
			    	<input name="firstName" type="text" value="${customer.firstName}" placeholder="Prenom" />
			    </label>
			    <label>
			    	<input name="lastName" type="text" value="${customer.lastName}" placeholder="Nom" />
			    </label>
			    <button type="submit" class="btn" data-loading-text="Loading..."><i class="icon-ok"></i> Valider</button>
			    <button type="reset" class="btn"><i class="icon-remove"></i> RAZ</button>
			</fieldset>
		</form:form>
	</div>
		
</div>

<%@ include file="/JSP/footer.jsp" %>
