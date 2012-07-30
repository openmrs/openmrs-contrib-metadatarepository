<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="cssHorizontalMenu.vm" permissions="rolesAdapter">
<ul id="primary-nav" class="menuList">
    <li class="pad">&nbsp;</li>
    <c:if test="${empty pageContext.request.remoteUser}">
    <li class="pad">&nbsp;</li><li><a href="<c:url value="/mainMenu" />" class="current"><fmt:message key="mainMenu.title"/></a></li>
    <li class="pad">&nbsp;</li><li><a href="<c:url value="/packageupload"/>" ><fmt:message key="upload.title"/></a></li>
    <li><a href="<c:url value="/login"/>" ><fmt:message key="login.title"/></a></li>
     </c:if>
    <menu:displayMenu name="MainMenu" />
    <menu:displayMenu name="MyPackages"/>
    <menu:displayMenu name="FileUpload"/>
    <menu:displayMenu name="UserMenu"/>
    <menu:displayMenu name="AdminMenu"/>
    <menu:displayMenu name="Logout"/>
</ul>
</menu:useMenuDisplayer>


