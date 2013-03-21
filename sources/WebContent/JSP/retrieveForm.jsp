<%@ include file="/JSP/common.jsp" %>

<jsp:include page="/JSP/header.jsp">
   <jsp:param name="title" value="retrouver identifiants" />
   <jsp:param name="currentTab" value="login" />
</jsp:include>

<div class="row" style="padding: 10px;">
	<div class="span4">
		<img src="/myBooking/resources/img/spectacle.png" alt="easyBooking" class="img-rounded" style="margin: 5px;" />
	</div>
	<div class="span8">
		<form:form modelAttribute="retrieveForm" action="login/retrieve" method="post">
			<div>
				<fieldset>
	    			<legend>Se connecter > Identifiants</legend>
	    			
	    			<div class="alert alert-block">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
					 	<h4>Avertissement !</h4>
					  	L'envoi d'email avec JavaMailSpring n'a pas encore &eacute;t&eacute; r&eacute;alis&eacute;. L'envoi de ce formulaire n'aboutira pas. Merci.
					</div>
	    			
	    			<c:if test="${error}">
						<div class="alert alert-error">
							Cet email ne semble pas pr&eacute;sente dans la base de donn&eacute;es.
						</div>
					</c:if>
	    			
					<span class="help-block">Nous vous renverrons vos identifiants sur votre email.</span>
					
					<div class="input-prepend input-append">
						<span class="add-on"><i class="icon-envelope"></i></span>
						<input name="userName" value="${loginForm.userName}" type="text" placeholder="Adresse email" size="50" />
						<div class="btn-group">
							<button type="submit" class="btn" data-loading-text="Loading..."><i class="icon-search"></i> Chercher</button>
						</div>
					</div>
				</fieldset>
			</div>
		</form:form>
	</div>
</div>

<%@ include file="/JSP/footer.jsp" %>
