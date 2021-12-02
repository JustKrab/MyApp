<#import "parts/common_parts.ftlh" as h>
<#include "parts/know.ftlh">
<@h.hah>
    <#if !user??>
    <div class="container mt-5" style="height: 80vh">
        <h5>Hello, guest</h5>

        <a href="/registration">Registration</a>
        <a href="/login">Login</a>
        <#else>
<div class="container mt-5" style="height: 80vh">
    hi
    <form action="/src" method="get">
        <label for="search_input">Search:</label> <input name="search"
                                                         id="search">
        </input>
    </form>
</div>
    </#if>
    </div>
</@h.hah>
