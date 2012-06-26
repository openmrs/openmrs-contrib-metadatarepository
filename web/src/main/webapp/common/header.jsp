<%@ include file="/common/taglibs.jsp"%>

<c:if test="${pageContext.request.locale.language ne 'en'}">
    <div id="switchLocale"><a href="<c:url value='/?locale=en'/>"><fmt:message key="webapp.name"/> in English</a></div>
</c:if>
<table border=0 align="center" width="100%">
<tr>
<td>
<div id="branding">
    <a href="<c:url value='/'/>"><img src="<c:url value="/images/openmrs-logo.png"/>" alt="<fmt:message key="webapp.name"/>"/></a>
    <p><fmt:message key="webapp.tagline"/></p>
</div></td>
<td>
<div style="font-size: medium;"><h2>Metadata Repository</h2></div></td>
</tr>
</table>
<hr />

<%-- Put constants into request scope --%>
<appfuse:constants scope="request"/>