<%@ include file="/common/taglibs.jsp"%>

<c:if test="${pageContext.request.locale.language ne 'en'}">
    <div id="switchLocale"><a href="<c:url value='/?locale=en'/>"><fmt:message key="webapp.name"/> in English</a></div>
</c:if>
<table border=0 align="center">
<tr>
<td>
    <a href="<c:url value='/'/>"><img src="<c:url value="/images/openmrs-logo.png"/>" alt="<fmt:message key="webapp.name"/>"/></a>
</td>
<td>
<div id="title"><h2>OpenMRS Metadata Repository</h2></div></td>
</tr>
</table>
<hr />

<%-- Put constants into request scope --%>
<appfuse:constants scope="request"/>