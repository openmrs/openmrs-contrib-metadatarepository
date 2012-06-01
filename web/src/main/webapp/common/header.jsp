<%@ include file="/common/taglibs.jsp"%>

<c:if test="${pageContext.request.locale.language ne 'en'}">
    <div id="switchLocale"><a href="<c:url value='/?locale=en'/>"><fmt:message key="webapp.name"/> in English</a></div>
</c:if>

<div id="branding">
    <div id="newpad">
    <a href="<c:url value='/'/>"><img src="<c:url value="/images/openmrs-logo.png"/>" alt="<fmt:message key="webapp.name"/>"/></a></div>
    <!-- <div id="hpad">
    <h1>Metadata&nbspRepository</h1>
    </div> -->
    <p><fmt:message key="webapp.tagline"/></p>
</div>
<hr />

<%-- Put constants into request scope --%>
<appfuse:constants scope="request"/>