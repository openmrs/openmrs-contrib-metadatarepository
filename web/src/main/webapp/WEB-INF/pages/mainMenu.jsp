<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="mainMenu.title" /></title>
<meta name="heading" content="<fmt:message key='mainMenu.heading'/>" />
<meta name="menu" content="MainMenu" />
</head>
<div id="search">
	<form method="get" action="${ctx}/mainMenu" id="searchForm">
		<input type="text" size="20" name="q" id="query" value="${param.q}"
			placeholder="Enter search terms" /> <input type="submit"
			value="<fmt:message key="button.search"/>" />
	</form>
</div>
<c:if test="${!empty pageContext.request.remoteUser}">
	<p>
		<fmt:message key="mainMenu.message" />
	</p>
</c:if>

<div class="separator"></div>

<input type="button" style="margin-right: 5px"
	onclick="location.href='<c:url value="/packageupload"/>'"
	value="<fmt:message key="button.add"/>" />


<display:table name="packageList" cellspacing="0" cellpadding="0"
	requestURI="" defaultsort="1" id="packages" pagesize="25" class="table"
	export="true">
	<display:column property="name" escapeXml="true" sortable="true"
		titleKey="package.name" style="width: 25%" />
	<display:column property="description" escapeXml="true" sortable="true"
		titleKey="package.description" style="width: 34%" />
	<display:column property="version" sortable="true"
		titleKey="package.version" style="width: 25%" />
	<display:column property="version" titleKey="package.version"
		media="csv xml excel pdf" />
	<display:column titleKey="package.download"
		style="width: 16%; padding-left: 15px"
		url="/packageform/packagedownload?from=list" paramId="id"
		paramProperty="id" sortable="true">
		<img src="/images/download.png" alt="download" />
	</display:column>
	<display:column titleKey="Edit" style="width: 16%; padding-left: 15px"
		url="/packageform?from=list" paramId="id" paramProperty="id"
		sortable="true">
		<img src="/images/edit.png" alt="edit" />
	</display:column>
	<display:column property="username" sortable="true"
		titleKey="package.publisher" style="width: 25%" />
	<display:setProperty name="paging.banner.item_name" value="package" />
	<display:setProperty name="paging.banner.items_name" value="packages" />

	<display:setProperty name="export.excel.filename"
		value="Package List.xls" />
	<display:setProperty name="export.csv.filename"
		value="Package List.csv" />
	<display:setProperty name="export.pdf.filename"
		value="Package List.pdf" />
</display:table>


<input type="button" style="margin-right: 5px"
	onclick="location.href='<c:url value="/packageupload"/>'"
	value="<fmt:message key="button.add"/>" />

<script type="text/javascript">
	highlightTableRows("packages");
</script>



