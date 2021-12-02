<#import "parts/common_parts.ftlh" as h>
<#include "parts/know.ftlh">
<@h.hah>
    <div class="container mt-5" style="height: 80vh">
<#--        <h2>Last added reviews: </h2>-->
<#--        <br>-->
<#--        <table class="table table-bordered" aria-valuemax="3">-->
<#--            <thead>-->
<#--            <tr>-->
<#--                <th>Group</th>-->
<#--                <th>Theme</th>-->
<#--                <th>Rating</th>-->
<#--                <th>Title</th>-->
<#--                <th>Author</th>-->
<#--            </tr>-->
<#--            </thead>-->
<#--            <tbody id="reviews">-->
<#--            <#list reviews as review>-->
<#--                <tr>-->
<#--                    <td>${review.groups}</td>-->
<#--                    <td><a class="d-inline-block text-truncate" style="max-width: 150px;">${review.theme}</a></td>-->
<#--                    <td>${review.rating}/10</td>-->
<#--                    <td><a class="d-inline-block text-truncate" style="max-width: 150px; color: dodgerblue" href="/review/${review.id}">${review.title}</a></td>-->
<#--                    <td><a style="color: dodgerblue" href="/profile/${review.authorName}">${review.authorName}</a></td>-->
<#--                </tr>-->
<#--            <#else>-->
<#--                No reviews-->
<#--            </#list>-->
<#--            </tbody>-->
<#--        </table>-->
        <br>
        <h2>Last added reviews: </h2>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Group</th>
                <th>Theme</th>
                <th>Rating</th>
                <th>Title</th>
                <th>Author</th>
            </tr>
            </thead>
            <tbody id="reviews">
            <#list last as reviewL>
                <tr>
                    <td>${reviewL.groups}</td>
                    <td><a class="d-inline-block text-truncate" style="max-width: 150px;">${reviewL.theme}</a></td>
                    <td>${reviewL.rating}/10</td>
                    <td><a class="d-inline-block text-truncate" style="max-width: 150px; color: dodgerblue" href="/review/${reviewL.id}">${reviewL.title}</a></td>
                    <td><a style="color: dodgerblue" href="/profile/${reviewL.authorName}">${reviewL.authorName}</a></td>
                </tr>
            <#else>
                No reviews
            </#list>
            </tbody>
        </table>

    </div>
</@h.hah>
