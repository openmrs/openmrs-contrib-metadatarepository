<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="mainMenu.title"/></title>
    <meta name="heading" content="<fmt:message key='mainMenu.heading'/>"/>
    <meta name="menu" content="MainMenu"/>
</head>

<c:if test="${!empty pageContext.request.remoteUser}"><p><fmt:message key="mainMenu.message"/></p></c:if>

<div class="separator"></div>

<input type="button" style="margin-right: 5px"
    onclick="location.href='<c:url value="/packageupload"/>'"
    value="<fmt:message key="button.add"/>"/>
<input type="button" style="margin-right: 5px" value="Download"/>

<display:table name="packageList" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="packages" pagesize="25" class="table" export="true">
    <display:column property="name" escapeXml="true" sortable="true" titleKey="package.name" style="width: 25%"
        url="/packageform?from=list" paramId="id" paramProperty="id"/>
    <display:column property="description" escapeXml="true" sortable="true" titleKey="package.description" style="width: 34%"/>
    <display:column property="version" sortable="true" titleKey="package.version" style="width: 25%" />
    <display:column property="email" titleKey="package.version" media="csv xml excel pdf"/>
    <display:column sortProperty="selected" sortable="true" titleKey="package.selected" style="width: 16%; padding-left: 15px" media="html">
         <input type="checkbox" disabled="disabled" <c:if test="${package.selected}">checked="checked"</c:if>/>
    </display:column>
    <display:column property="selected" titleKey="package.selected" media="csv xml excel pdf"/>

    <display:setProperty name="paging.banner.item_name" value="package"/>
    <display:setProperty name="paging.banner.items_name" value="packages"/>

    <display:setProperty name="export.excel.filename" value="Package List.xls"/>
    <display:setProperty name="export.csv.filename" value="Package List.csv"/>
    <display:setProperty name="export.pdf.filename" value="Package List.pdf"/>
</display:table>

<input type="button" style="margin-right: 5px"
    onclick="location.href='<c:url value="/packageupload"/>'"
    value="<fmt:message key="button.add"/>"/>
<input type="button" style="margin-right: 5px" value="Download"/>

<script type="text/javascript">
    highlightTableRows("packages");
</script>



