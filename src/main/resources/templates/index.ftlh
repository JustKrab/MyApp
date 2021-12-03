<#import "parts/common_parts.ftlh" as h>
<#include "parts/know.ftlh">
<#import "/spring.ftl" as spring/>
<@h.hah>
    <div class="container mt-5" style="height: 80vh">
        <h2><@spring.message "review.populars"/></h2>
        <br>
        <table class="table table-bordered" aria-valuemax="3">
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
            <#list popular as reviewP>
                <tr>
                    <td>${reviewP.groups}</td>
                    <td><a class="d-inline-block text-truncate" style="max-width: 150px;">${reviewP.theme}</a></td>
                    <td>${reviewP.rating}/10</td>
                    <td><a class="d-inline-block text-truncate" style="max-width: 150px; color: dodgerblue" href="/review/${reviewP.id}">${reviewP.title}</a></td>
                    <td><a style="color: dodgerblue" href="/profile/${reviewP.authorName}">${reviewP.authorName}</a></td>
                </tr>
            <#else>
                <@spring.message "norev"/>
            </#list>
            </tbody>
        </table>
        <br>
        <h2><@spring.message "review.new"/> </h2>
        <br>
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
            <#list last as reviewL>
                <tr>
                    <td>${reviewL.groups}</td>
                    <td><a class="d-inline-block text-truncate" style="max-width: 150px;">${reviewL.theme}</a></td>
                    <td>${reviewL.rating}/10</td>
                    <td><a class="d-inline-block text-truncate" style="max-width: 150px; color: dodgerblue" href="/review/${reviewL.id}">${reviewL.title}</a></td>
                    <td><a style="color: dodgerblue" href="/profile/${reviewL.authorName}">${reviewL.authorName}</a></td>
                </tr>
            <#else>
                <@spring.message "norev"/>
            </#list>
            </tbody>
        </table>

    </div>
</@h.hah>
