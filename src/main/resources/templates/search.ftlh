<#import "parts/common_parts.ftlh" as h>
<#include "parts/know.ftlh">
<#import "/spring.ftl" as spring/>
<@h.hah>
<div class="container mt-5">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th><@spring.message "review.grp.pl"/></th>
            <th><@spring.message "review.th.pl"/></th>
            <th><@spring.message "review.rtng.pl"/></th>
            <th><@spring.message "review.titl.pl"/></th>
            <th><@spring.message "review.authr"/></th>
        </tr>
        </thead>
        <tbody id="reviews">
        <#list search! as reviews>
            <tr>
                <td>${reviews.groups}</td>
                <td><a class="d-inline-block text-truncate" style="max-width: 150px;">${reviews.theme}</a></td>
                <td>${reviews.rating}/10</td>
                <td><a class="d-inline-block text-truncate" style="max-width: 150px; color: dodgerblue" href="/review/${reviews.id}">${reviews.title}</a></td>
                <td><a style="color: dodgerblue" href="/profile/${reviews.authorName}">${reviews.authorName}</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

</@h.hah>
