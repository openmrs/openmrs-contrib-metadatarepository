<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="mainMenu.title"/></title>
    <meta name="heading" content="<fmt:message key='mainMenu.heading'/>"/>
    <meta name="menu" content="MainMenu"/>
</head>

<c:if test="${!empty pageContext.request.remoteUser}"><p><fmt:message key="mainMenu.message"/></p></c:if>

<div class="separator"></div>


