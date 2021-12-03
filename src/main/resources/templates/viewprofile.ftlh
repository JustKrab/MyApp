<#import "parts/common_parts.ftlh" as h>
<#import "/spring.ftl" as spring/>
<@h.hah>
    <div class="container mt-5">
        <div>
            <img style="

   width:150px;
   height:150px;
   object-fit:cover;" class="ui-icon-image" src="http://res.cloudinary.com/myreviewapp/image/upload/${usr.getAvatar()}"
                 alt="Profile img">
        </div>
        <div>
        <h5>${usr.getUsername()}</h5>
        </div>
        <br>
        <div class="container mt-3">
            <h2>Reviews</h2>
            <input class="form-control" id="filter" type="text" placeholder="<@spring.message "srch"/>..">
            <br>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th><@spring.message "review.th.pl"/></th>
                    <th><@spring.message "review.rtng.pl"/></th>
                    <th><@spring.message "review.titl.pl"/></th>
                </tr>
                </thead>
                <tbody id="reviews">
                <#list reviews as review>
                    <tr>
                        <td><a class="d-inline-block text-truncate" style="max-width: 150px; color: dodgerblue">${review.theme}</a></td>
                        <td>${review.rating}/10</td>
                        <td><a class="d-inline-block text-truncate" style="max-width: 150px; color: dodgerblue"
                               href="/review/${review.id}">${review.title}</a></td>
                    </tr>
                <#else>
                    <@spring.message "norev"/>
                </#list>
                </tbody>
            </table>

        </div>
    </div>
</@h.hah>
