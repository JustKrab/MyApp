<#include "know.ftlh">
<nav class="navbar navbar-expand-lg navbar-themed">
    <a class="navbar-brand" href="/">Myapp</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse navbar-themed" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/profile">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/review/add/new">Add review</a>
                </li>
            </#if>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Reviews
                    </a>
                    <ul class="dropdown-menu dropdown-menu-light" aria-labelledby="navbarLightDropdownMenuLink">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="/review/all">All reviews</a></li>
                    </ul>
                </li>
            <#if isAdmin>
                <li class="nav-item"><a class="nav-link" href="/userlist"> User list</a></li>
            </#if>
            <button class="btn btn-primary" onclick="myFunction()">Switch theme</button>

        </ul>
        <#if user??>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-primary" type="submit">Sign Out</button>
        </form>
        </#if>
        <#if !user??>
        <form action="/login" method="get">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-primary" type="submit">Login</button>
        </form>
            <form action="/registration" method="get">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-success" type="submit">Register</button>
            </form>
        </#if>
    </div>


</nav>
