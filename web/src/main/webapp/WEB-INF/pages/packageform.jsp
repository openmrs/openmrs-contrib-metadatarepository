<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="packageDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='packageDetail.heading'/>"/>
    <script type="text/javascript" src="<c:url value='/scripts/selectbox.js'/>"></script>
</head>
<form:form commandName="metadataPackage" method="post" action="packageform" id="packageform">
<form:errors path="*" cssClass="error" element="div"/>
<form:hidden path="id"/>
<ul>
    <li>
        <appfuse:label styleClass="desc" key="package.name"/>
        <form:errors path="name" cssClass="fieldError"/>
        <form:input path="name" id="name" cssClass="text medium" cssErrorClass="text medium error" maxlength="50"/>
    </li>
    <li>
        <appfuse:label styleClass="desc" key="package.description"/>
        <form:errors path="description" cssClass="fieldError"/>
        <form:input path="description" id="description" cssClass="text medium" cssErrorClass="text medium error" maxlength="100"/>
    </li>
    <li>
        <appfuse:label styleClass="desc" key="package.version"/>
        <form:errors path="version" cssClass="fieldError"/>
        <form:input path="version" id="version" cssClass="text medium" cssErrorClass="text medium error" maxlength="10"/>
    </li>
 
    <li class="buttonBar bottom">
        <input type="submit" class="button" name="save" value="<fmt:message key="button.save"/>"/>
        <c:if test="${not empty package.id}">
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('package')"
            value="<fmt:message key="button.delete"/>" />
        </c:if>
        <input type="submit" class="button" name="cancel" value="<fmt:message key="button.cancel"/>" onclick="bCancel=true"/>
    </li>
</ul>
</form:form>
 
<script type="text/javascript">
    Form.focusFirstElement($('packageform'));
</script>