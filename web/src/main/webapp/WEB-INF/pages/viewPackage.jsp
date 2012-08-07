<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="display.title" /></title>
<meta name="heading" content="<fmt:message key='display.heading'/>" />
<meta name="menu" content="AdminMenu" />
</head>



<div class="separator"></div>

<table class="detail" cellpadding="5">
	<tr>
		<th>Name:</th>
		<td><c:out value="${metadataPackage.name}" /></td>
	</tr>
	<tr>
		<th>Description:</th>
		<td><c:out value="${metadataPackage.description}" /></td>
	</tr>
	<tr>
		<th>Publisher:</th>
		<td><c:out value="${metadataPackage.user.username}" /></td>
	</tr>
	<tr>
		<th>Version:</th>
		<td><c:out value="${metadataPackage.version}" /></td>
	</tr>
	<tr>
		<th>OpenMRS Version:</th>
		<td><c:out value="${metadataPackage.openmrsVersion}" /></td>
	</tr>
	<tr>
		<th>Date Created:</th>
		<td><c:out value="${metadataPackage.dateCreated}" /></td>
	</tr>
	<tr>
		<th>Group UUID:</th>
		<td><c:out value="${metadataPackage.groupUuid}" /></td>
	</tr>
	<tr>
		<th>Subscription URL:</th>
		<td><c:out value="${metadataPackage.subscriptionUrl}" /></td>
	</tr>

	<tr>
		<td></td>
		<td class="buttonBar"><input type="button" name="back" id="back"
			value="Back" onclick="location.href='<c:url value="/mainMenu"/>'" /> 
			<c:if
				test="${metadataPackage.user.username eq pageContext.request.remoteUser}">
				<input type="button" style="width: 50px" value="Edit"
					onclick="location.href='<c:url value="/packageform?id=${packages.id}" />'" />
			</c:if></td>
	</tr>
</table>


