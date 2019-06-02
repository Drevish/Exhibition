<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Exhibition</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="header.language" />
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item"  href="?lang=en">EN</a>
                        <a class="dropdown-item"  href="?lang=ru">RU</a>
                    </div>
                </li>

                <c:if test="${sessionScope.user!=null}">

                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/tickets">
                            <fmt:message key="header.view_tickets"/>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/buy">
                            <fmt:message key="header.buy_ticket"/>
                        </a>
                    </li>

                    <c:if test="${sessionScope.user.role.toString() == 'ADMIN'}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin">
                                <fmt:message key="header.admin_panel" />
                            </a>
                        </li>
                    </c:if>
                </c:if>
            </ul>

            <form class="form-inline my-2 my-lg-0">
                <c:choose>
                    <c:when test="${sessionScope.user!=null}">
                        <span class="text-light mr-sm-2">${sessionScope.user.email}</span>

                        <a class="btn btn-danger mr-sm-2"
                           href="${pageContext.request.contextPath}/logout"><fmt:message key="header.log_out"/></a>
                        <br/>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-success mr-sm-2"
                           href="${pageContext.request.contextPath}/login"><fmt:message key="header.login"/></a>
                        <br/>

                        <a class="btn btn-primary mr-sm-2"
                           href="${pageContext.request.contextPath}/register"><fmt:message key="header.register"/></a>
                        <br/>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
    </nav>
</header>
