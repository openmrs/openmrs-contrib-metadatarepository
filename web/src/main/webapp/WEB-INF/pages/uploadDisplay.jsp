<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="display.title"/></title>
    <meta name="heading" content="<fmt:message key='display.heading'/>"/>
    <meta name="menu" content="AdminMenu"/>
</head>

<p>Below is a list of attributes that were gathered in PackageFormController.java.</p>

<div class="separator"></div>

<table class="detail" cellpadding="5">
    <tr>
        <th>Package Name:</th>
        <td><c:out value="${pkgName}"/></td>
    </tr>
    <tr>
        <th>Package Description:</th>
        <td><c:out value="${pkgDescription}"/></td>
    </tr>
     <tr>
        <th>Package Version:</th>
        <td><c:out value="${pkgVersion}"/></td>
    </tr>
    <tr>
        <th>Package Publisher:</th>
        <td><c:out value="${pkgPublisher}"/></td>
    </tr>
   
    <tr>
        <td></td>
        <td class="buttonBar">
            <input type="button" name="done" id="done" value="Done"
                onclick="location.href='mainMenu'" />
            <input type="button" style="width: 120px" value="Upload Another"
                onclick="location.href='packageupload'" />
        </td>
    </tr>
</table>


