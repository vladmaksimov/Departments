<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--@elvariable id="page" type="org.springframework.data.domain.PageImpl"--%>
<%--@elvariable id="sortList" type="java.util.List"--%>
<%--@elvariable id="sizeList" type="java.util.List"--%>

<c:set var="sort" value="#{page.sort.iterator().next().property}" scope="page"/>
<c:set var="prev" value="#{page.hasPrevious()}" scope="page"/>
<c:set var="next" value="#{page.hasNext()}" scope="page"/>

<div>
    <div class="text-center pagination-tab">

        <form method="get" name="department" action="${urlForm}" class="navbar-form navbar-left">
            ${urlParams}
            <input type="hidden" name="size" value="${page.size}">
            <c:if test="${not empty search}">
                <input type="hidden" name="search" value="${search}">
            </c:if>

            <div class="pagination-left form-group">
                <span>Sort by:</span>
                <select class="form-control sort-selected" name="sort" title="Select sort field">

                    <c:forEach var="element" items="${sortList}">
                        <option <c:if test="${sort eq element}">selected</c:if>>${element}</option>
                    </c:forEach>
                </select>
                <button class="btn btn-default" type="submit">Apply</button>
            </div>
        </form>

        <form method="get" action="${urlForm}" class="pagination-center navbar-form ">
            <div class="form-group">

                ${urlParams}
                <input type="hidden" name="sort" value="${sort}">
                <input type="hidden" name="size" value="${page.size}">
                <c:if test="${not empty search}">
                    <input type="hidden" name="search" value="${search}">
                </c:if>

                <button class="btn btn-default" type="submit" name="page" value="${page.number - 1}"
                        <c:if test="${!prev}">disabled</c:if>>
                    <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
                </button>
                <span>${page.number + 1}</span>
                <span>/</span>
                <span>${page.totalPages}</span>
                <button class="btn btn-default" type="submit" name="page" value="${page.number + 1}"
                        <c:if test="${!next}">disabled</c:if>>
                    <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
                </button>
            </div>
        </form>

        <form action="${urlForm}" method="get" class="navbar-form navbar-right">
            ${urlParams}
            <input type="hidden" name="sort" value="${sort}">
            <c:if test="${not empty search}">
                <input type="hidden" name="search" value="${search}">
            </c:if>
            <div class="pagination-right form-group">
                <span>On page:</span>
                <select class="form-control" name="size" title="Select page size">
                    <c:forEach var="element" items="${sizeList}">
                        <option <c:if test="${page.size eq element}">selected</c:if>>${element}</option>
                    </c:forEach>
                </select>
                <button class="btn btn-default" type="submit">Apply</button>
            </div>
        </form>

    </div>
</div>