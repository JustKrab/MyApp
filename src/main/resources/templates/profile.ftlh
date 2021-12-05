<#import "parts/common_parts.ftlh" as h>
<#include "parts/know.ftlh">
<#import "/spring.ftl" as spring/>
<@h.hah>
    <div class="container mt-5">
        <a class="first" href="http://res.cloudinary.com/myreviewapp/image/upload/${usr.getAvatar()}"><img style="
   top:50%;
   left:50%;
   width:150px;
   height:150px;
   object-fit:cover;" src="http://res.cloudinary.com/myreviewapp/image/upload/${usr.getAvatar()}"></a>

    <br>
    <div class="user-profile" style="margin: auto">
        <h5><@spring.message "profile.hi"/> ${usr.getUsername()}</h5>
        <br>
        <h5><@spring.message "profile.email"/> ${usr.getEmail()}</h5>
        <br>
        <div class="editButton">
            <button  class="btn btn-primary" onclick="window.location.href = '/profile/${usr.getId()}/edit';"><@spring.message "profile.edt"/></button>
        </div>
        <br>
        <#if isAdmin>
        <div class="addButton">
            <button  class="btn btn-primary" onclick="window.location.href = '/userlist/${usr.getUsername()}/addreview';"><@spring.message "review.add"/></button>
        </div>
        </#if>
        <br>
    </div>

    <div class="container" >
        <h2><@spring.message "review.rvs"/></h2>
        <input class="form-control" id="filter" type="text" placeholder="<@spring.message "srch"/>">
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><@spring.message "review.th.pl"/></th>
                <th><@spring.message "review.rtng.pl"/></th>
                <th><@spring.message "review.titl.pl"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody id="reviews">
            <#list reviews as review>
                <tr>
                    <td><a class="d-inline-block text-truncate" style="max-width: 150px">${review.theme}</a></td>
                    <td>${review.rating}/10</td>
                    <td><a class="d-inline-block text-truncate" style="max-width: 150px; color: dodgerblue"
                           href="/review/${review.id}">${review.title}</a></td>
                    <td>
                        <button class="btn btn-primary" onclick="window.location.href = '/review/${review.id}/edit';"><@spring.message "review.edt"/></button>
                    </td>
                    <td>
                        <button class="btn btn-primary" onclick="window.location.href = '/review/${review.id}/delete';"><@spring.message "review.delt"/></button>
                    </td>
                </tr>
            <#else>
                <@spring.message "norev"/>
            </#list>
            </tbody>
        </table>

    </div>
    </div>

    <script>

        $(document).ready(function () {

            $("#locales").change(function () {
                var selectedOption = $("#locales").val();
                if (selectedOption !== "") {
                    window.location.replace('?lang=' + selectedOption);
                }
            })

        })
    </script>

</@h.hah>
