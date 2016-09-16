<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="requestPage" scope="request" type="com.maksimov.data.Page"/>

<div>
    <div class="text-center pagination-tab">


        <form method="get" name="department" action="/" class="navbar-form navbar-left">
            <div class="pagination-left form-group">
                <span>Sort by:</span>
                <select class="form-control sort-selected" name="sort">
                    <c:forEach var="element" items="${sortList}">
                        <option <c:if test="${requestPage.sort eq element}">selected</c:if>>${element}</option>
                    </c:forEach>
                </select>
                <button class="btn btn-default" type="submit">Apply</button>
            </div>
        </form>

        <form method="get" action="/" class="pagination-center">
            <div class="form-group">

                <input type="hidden" name="sort" value="${requestPage.sort}">
                <input type="hidden" name="size" value="${requestPage.pageSize}">

                <button class="btn btn-default" type="submit" name="page" value="${requestPage.pageNumber - 1}"
                        <c:if test="${!requestPage.hasPrevious()}">disabled</c:if>>
                    <span class="glyphicon glyphicon-menu-left" aria-hidden="true"/>
                </button>
                <span>${requestPage.pageNumber}</span>
                <span>/</span>
                <span>${requestPage.totalPages}</span>
                <button class="btn btn-default" type="submit" name="page" value="${requestPage.pageNumber + 1}"
                        <c:if test="${!requestPage.hasNext()}">disabled</c:if>>
                    <span class="glyphicon glyphicon-menu-right" aria-hidden="true"/>
                </button>
            </div>
        </form>

        <form action="/" method="get"  class="navbar-form navbar-right">
            <div class="pagination-right form-group">
                <span>On page:</span>
                <select class="form-control" name="size">
                    <c:forEach var="element" items="${sizeList}">
                        <option>${element}</option>
                    </c:forEach>
                </select>
                <button class="btn btn-default" type="submit">Apply</button>
            </div>
        </form>

    </div>
</div>