<#import "parts/common_parts.ftlh" as h>

<@h.hah>

    <div class="container mt-3 ">
        <h2>All reviews: </h2>
        <input class="form-control" id="filter" type="text" placeholder="Search..">
        <br>
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
            <#list reviews as review>
                <tr>
                    <td>${review.groups}</td>
                    <td><a class="d-inline-block text-truncate" style="max-width: 150px;">${review.theme}</a></td>
                    <td>${review.rating}/10</td>
<#--                    <td>${review.getUserRating()}</td>-->
                    <td><a class="d-inline-block text-truncate" style="max-width: 150px; color: dodgerblue" href="/review/${review.id}">${review.title}</a></td>
                    <td><a style="color: dodgerblue" href="/profile/${review.authorName}">${review.authorName}</a></td>
                </tr>
            <#else>
                No reviews
            </#list>
            </tbody>
        </table>

    </div>


</@h.hah>