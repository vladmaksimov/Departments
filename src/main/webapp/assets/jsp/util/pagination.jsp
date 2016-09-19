<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--@elvariable id="search" type="java.lang.String"--%>
<%--@elvariable id="requestPage" type="java.util.List"--%>
<%--@elvariable id="sortList" type="java.util.List"--%>
<%--@elvariable id="sizeList" type="java.util.List"--%>
<%--@elvariable id="urlForm" type="java.lang.String"--%>

<div>
    <div class="text-center pagination-tab">

        <form method="get" name="department" action="${urlForm}" class="navbar-form navbar-left">
            ${urlParams}
            <input type="hidden" name="size" value="${requestPage.pageSize}">
            <c:if test="${not empty search}">
                <input type="hidden" name="search" value="${search}">
            </c:if>

            <div class="pagination-left form-group">
                <span>Sort by:</span>
                <select class="form-control sort-selected" name="sort" title="Select sort field">
                    <c:forEach var="element" items="${sortList}">
                        <option <c:if test="${requestPage.sort eq element}">selected</c:if>>${element}</option>
                    </c:forEach>
                </select>
                <button class="btn btn-default" type="submit">Apply</button>
            </div>
        </form>

        <form method="get" action="${urlForm}" class="pagination-center navbar-form ">
            <div class="form-group">

                ${urlParams}
                <input type="hidden" name="sort" value="${requestPage.sort}">
                <input type="hidden" name="size" value="${requestPage.pageSize}">
                <c:if test="${not empty search}">
                    <input type="hidden" name="search" value="${search}">
                </c:if>

                <button class="btn btn-default" type="submit" name="page" value="${requestPage.pageNumber - 1}"
                        <c:if test="${!requestPage.hasPrevious()}">disabled</c:if>>
                    <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
                </button>
                <span>${requestPage.pageNumber}</span>
                <span>/</span>
                <span>${requestPage.totalPages}</span>
                <button class="btn btn-default" type="submit" name="page" value="${requestPage.pageNumber + 1}"
                        <c:if test="${!requestPage.hasNext()}">disabled</c:if>>
                    <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
                </button>
            </div>
        </form>

        <form action="${urlForm}" method="get" class="navbar-form navbar-right">
            ${urlParams}
            <input type="hidden" name="sort" value="${requestPage.sort}">
            <c:if test="${not empty search}">
                <input type="hidden" name="search" value="${search}">
            </c:if>
            <div class="pagination-right form-group">
                <span>On page:</span>
                <select class="form-control" name="size" title="Select page size">
                    <c:forEach var="element" items="${sizeList}">
                        <option <c:if test="${requestPage.pageSize eq element}">selected</c:if>>${element}</option>
                    </c:forEach>
                </select>
                <button class="btn btn-default" type="submit">Apply</button>
            </div>
        </form>

    </div>
</div>