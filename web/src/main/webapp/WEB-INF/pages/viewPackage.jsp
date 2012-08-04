<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="display.title" /></title>
<meta name="heading" content="<fmt:message key='display.heading'/>" />
<meta name="menu" content="AdminMenu" />
</head>

<p>Below is a list of attributes that were gathered in
	PackageFormController.java.</p>

<div class="separator"></div>

<table class="detail" cellpadding="5">
	<tr>
		<th>Package Name:</th>
		<td><c:out value="${pkgName}" /></td>
	</tr>
	<tr>
		<th>Package Description:</th>
		<td><c:out value="${pkgDescription}" /></td>
	</tr>
	<tr>
		<th>Package Version:</th>
		<td><c:out value="${pkgVersion}" /></td>
	</tr>

	<tr>
		<td></td>
		<td class="buttonBar"><input type="button" name="done" id="done"
			value="Done" onclick="location.href='mainMenu'" /> <c:if
				test="${packages.user.username eq pageContext.request.remoteUser}">
				<input type="button" style="width: 50px" value="Edit"
					onclick="location.href='<c:url value="/packageform?id=${packages.id}" />'" />
			</c:if></td>
	</tr>
</table>


