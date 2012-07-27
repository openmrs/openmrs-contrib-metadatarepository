<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="mypackages.title" /></title>
<meta name="menu" content="MyPackages" />
</head>
<div id="message">
	<c:if test="${!empty pageContext.request.remoteUser}">
		<p>
			<fmt:message key="mypackages.message" />
		</p>
	</c:if>
</div>
<div id="search">
	<form method="get" action="${ctx}/mypackages" id="searchForm"
		align="center">
		<table>
			<tr>
				<td><input type="text" size="40" name="q" id="query"
					value="${param.q}" placeholder="Enter search terms" /></td>
				<td><input type="submit"
					value="<fmt:message key="button.search"/>" /></td>
			</tr>
		</table>
	</form>
</div>

<div class="separator"></div>

<input type="button" style="margin-right: 5px"
	onclick="location.href='<c:url value="/packageupload"/>'"
	value="<fmt:message key="button.add"/>" />


<display:table name="packageList" cellspacing="0" cellpadding="0"
	requestURI="" defaultsort="1" id="packages" pagesize="25" class="table"
	export="false">
	<display:column property="name" escapeXml="true" sortable="true"
		titleKey="package.name" style="width: 25%" />
	<display:column property="description" escapeXml="true" sortable="true"
		titleKey="package.description" style="width: 34%" />
	<display:column property="version" sortable="true"
		titleKey="package.version" style="width: 25%" />
	<display:column property="version" titleKey="package.version"
		media="csv xml excel pdf" />
	<display:column property="user.username" sortable="true"
		titleKey="package.publisher" style="width: 25%" />
	<display:column property="downloadCount" sortable="true"
		titleKey="package.downloadcount" style="width: 25%" />
	<display:column titleKey="Actions" sortable="true" style="width:34%"
		paramId="id" paramProperty="id">
		<table border="0">
			<tr>
				<td border="0" style="border: solid transparent"><img
					src="/images/edit.png" alt="edit" align="left"
					onclick="location.href='<c:url value="/packageform?id=${packages.id}" />'" /></td>
				<td border="0" style="border: solid transparent"><img
					src="/images/download.png" alt="download"
					onclick="location.href='<c:url value="/packageform/packagedownload?id=${packages.id}" />'" /></td>
			</tr>
		</table>

	</display:column>

</display:table>


<input type="button" style="margin-right: 5px"
	onclick="location.href='<c:url value="/packageupload"/>'"
	value="<fmt:message key="button.add"/>" />

<script type="text/javascript">
	highlightTableRows("packages");
</script>



