<#import "parts/common_parts.ftlh" as h>
<#import "/spring.ftl" as spring/>
<@h.hah>

    <div class="container mt-3 ">
        <h2>  <@spring.message "review.books"/>: </h2>
        <input class="form-control" id="filter" type="text" placeholder="<@spring.message "srch"/>..">
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
            <#list reviews as review>
                <tr>
                    <td>${review.groups}</td>
                    <td><a class="d-inline-block text-truncate" style="max-width: 150px;">${review.theme}</a></td>
                    <td>${review.rating}/10</td>
                    <td><a class="d-inline-block text-truncate" style="max-width: 150px; color: dodgerblue" href="/review/${review.id}">${review.title}</a></td>
                    <td><a style="color: dodgerblue" href="/profile/${review.authorName}">${review.authorName}</a></td>
                </tr>
            <#else>
                <@spring.message "norev"/>
            </#list>
            </tbody>
        </table>

    </div>


</@h.hah>