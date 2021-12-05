<#include "know.ftlh">
<#import "/spring.ftl" as spring/>
<nav class="navbar navbar-expand-lg navbar-themed">
    <a class="navbar-brand" href="/">Review!ON!</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse navbar-themed" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/profile"><@spring.message "review.prfl"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/review/add/new"><@spring.message "review.add"/></a>
                </li>
            </#if>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                   aria-expanded="false">
                    <@spring.message "review.rvs"/>
                </a>
                <ul class="dropdown-menu dropdown-menu-light" aria-labelledby="navbarLightDropdownMenuLink">
                    <li><a class="dropdown-item" href="/review/books"><@spring.message "review.books"/></a></li>
                    <li><a class="dropdown-item" href="/review/films"><@spring.message "review.films"/></a></li>
                    <li><a class="dropdown-item" href="/review/games"><@spring.message "review.games"/></a></li>
                    <li><a class="dropdown-item" href="/review/serials"><@spring.message "review.serials"/></a></li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>
                    <li><a class="dropdown-item" href="/review/all"><@spring.message "review.all"/></a></li>
                </ul>
            </li>

            <#if isAdmin>
                <li class="nav-item"><a class="nav-link" href="/userlist"><@spring.message "review.usrlist"/></a></li>
            </#if>

        </ul>
        <form action="/src" method="get" class="d-flex">
            <input class="form-control me-2" type="search" name="search" placeholder="<@spring.message "srch"/>"
                   aria-label="Search">
            <button class="btn btn-outline-success" type="submit">🔍</button>
        </form>

        <#if user??>
            <form action="/logout" method="post" class="ml-3">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary" type="submit"><@spring.message "main.logout"/></button>
            </form>
        </#if>
        <#if !user??>
            <form action="/login" method="get" class="ml-2">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary" type="submit"><@spring.message "main.login"/></button>
            </form>
            <form action="/registration" method="get" class="ml-2">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-success" type="submit"><@spring.message "main.register"/></button>
            </form>
        </#if>
    </div>


</nav>
