<#import "parts/common_parts.ftlh" as h>
<#include "parts/know.ftlh">
<@h.hah>
<div class="container mt-5">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Group</th>
            <th>Theme</th>
            <th>Rating</th>
            <#--                <th>User Rating</th>-->
            <th>Title</th>
            <th>Author</th>
        </tr>
        </thead>
        <tbody id="reviews">
        <#list search?ifExists as reviews>
            <tr>
                <td>${reviews.groups}</td>
                <td><a class="d-inline-block text-truncate" style="max-width: 150px;">${reviews.theme}</a></td>
                <td>${reviews.rating}/10</td>
                <#--                    <td>${review.getUserRating()}</td>-->
                <td><a class="d-inline-block text-truncate" style="max-width: 150px; color: dodgerblue" href="/review/${reviews.id}">${reviews.title}</a></td>
                <td><a style="color: dodgerblue" href="/profile/${reviews.authorName}">${reviews.authorName}</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

</@h.hah>
