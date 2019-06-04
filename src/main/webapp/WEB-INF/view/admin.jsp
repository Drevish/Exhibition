<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title>Admin panel</title>

    <%@include file="template/styles.jsp" %>
    <link rel="stylesheet" href="/static/css/admin.css">
</head>
<body>

<c:if test="${requestScope.page < 1}">
    <c:set var="page" value="1" scope="request"/>
</c:if>
<c:if test="${requestScope.page > requestScope.pagesNumber}">
    <c:set var="page" value="${requestScope.pagesNumber}" scope="request"/>
</c:if>


<main>
    <div class="container">
        <h1><fmt:message key="admin.welcome"/></h1>

        <table class="table table-bordered table-hover">
            <thead class="thead-light">
            <tr>
                <td>Id</td>
                <td>Email</td>
                <td>Role</td>
                <td>Active</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${requestScope.users}">
                <tr>
                    <td>${user.id}</td>
                    <td>
                        <a href="/admin/${user.id}">${user.email}</a>
                    </td>
                    <td>${user.role}</td>
                    <td>${user.active}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <c:set var="pageLink" scope="page" value="/admin?page="/>
        <nav>
            <ul class="pagination justify-content-center">
                <li class="page-item <c:if test="${page < 2}">disabled</c:if>">
                    <a class="page-link" href="${pageLink}${page - 1}">Previous</a>
                </li>


                <c:if test="${(page == requestScope.pagesNumber) && (requestScope.pagesNumber > 2)}">
                    <li class="page-item">
                        <a class="page-link" href="${pageLink}${page-2}">${page-2}</a>
                    </li>
                </c:if>


                <c:if test="${(page > 1) && (requestScope.pagesNumber > 1)}">
                    <li class="page-item">
                        <a class="page-link" href="${pageLink}${page-1}">${page-1}</a>
                    </li>
                </c:if>

                <li class="page-item active">
                    <a class="page-link" href="#">${page}</a>
                </li>

                <c:if test="${(page < requestScope.pagesNumber) && (requestScope.pagesNumber > 1)}">
                    <li class="page-item">
                        <a class="page-link" href="${pageLink}${page+1}">${page+1}</a>
                    </li>
                </c:if>


                <c:if test="${(page == 1) && (requestScope.pagesNumber > 2)}">
                    <li class="page-item">
                        <a class="page-link" href="${pageLink}${page+2}">${page+2}</a>
                    </li>
                </c:if>


                <li class="page-item <c:if test="${page >= requestScope.pagesNumber}">disabled</c:if>">
                    <a class="page-link" href="${pageLink}${page + 1}">Next</a>
                </li>
            </ul>
        </nav>

        <a href="/" class="link-back"><fmt:message key="general.back_to_home"/></a>
    </div>
</main>

</body>
</html>
