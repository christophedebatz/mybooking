<%@ include file="/JSP/common.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/JSP/header.jsp">
   <jsp:param name="title" value="vos commandes" />
   <jsp:param name="currentTab" value="dropdown" />
</jsp:include>

<div class="row" style="padding: 10px;">
	<div class="span4">
		<img src="/myBooking/resources/img/spectacle.png" alt="myBooking" class="img-rounded" style="margin: 5px;" />
	</div>
	<div class="span8">
	
		<h3>Vos commandes</h3>

		<fieldset>
   			<legend>Liste des commandes</legend>
   				
   				<table class="table table-striped">
					<tr>
						<th>ID</th>
						<th>Date</th>
						<th>Concert</th>
						<th>Prix (avec frais)</th>
					</tr>

					<c:if test="${empty orders}">
						<tr>
							<td align="center" colspan="3">Aucune commande pour le moment.</td>
						</tr>
					</c:if>
					
					<c:forEach items="${orders}" var="order" varStatus="loop">
					
						<tr>
							<td>${order.id}</td>
							<td><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${order.date}" /></td>
							<td>${order.representation.concert.title}</td>
							<td>Frais : ${ordersFees[loop.index]} EUR.<br />
								Total : ${ordersTotal[loop.index]} EUR.</td>
						</tr>
					
					</c:forEach>
				</table>
   				
   				<p>&nbsp;</p>
   				
		</fieldset>

	</div>
</div>

<%@ include file="/JSP/footer.jsp" %>