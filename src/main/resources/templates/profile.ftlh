<#import "parts/common_parts.ftlh" as h>
<#include "parts/know.ftlh">
<@h.hah>
    <div class="container mt-5">
        <div>
            <img style="

   width:150px;
   height:150px;
   object-fit:cover;" class="ui-icon-image" src="http://res.cloudinary.com/myreviewapp/image/upload/${user.getAvatar()}"
                 alt="Profile img">
        </div>

    <br>
    <div class="user-profile" style="margin: auto">
        <h5>Hello, ${user.getUsername()}</h5>
        <br>
        <h5>Your email: ${user.getEmail()}</h5>
        <br>
        <div class="editButton">
            <button  class="btn btn-primary" onclick="window.location.href = '/profile/${user.getId()}/edit';">Edit profile</button>
        </div>
        <br>
        <#if isAdmin>
        <div class="addButton">
            <button  class="btn btn-primary" onclick="window.location.href = '/userlist/${user.getUsername()}/addreview';">Add review</button>
        </div>
        </#if>
        <br>
    </div>

    <div class="container" >
        <h2>Reviews</h2>
        <input class="form-control" id="filter" type="text" placeholder="Search..">
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Theme</th>
                <th>Rating</th>
                <th>Title</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody id="reviews">
            <#list reviews as review>
                <tr>
                    <td><a class="d-inline-block text-truncate" style="max-width: 150px; color: #c1c1c1">${review.theme}</a></td>
                    <td>${review.rating}/10</td>
                    <td><a class="d-inline-block text-truncate" style="max-width: 150px; color: dodgerblue"
                           href="/review/${review.id}">${review.title}</a></td>
                    <td>
                        <button class="btn btn-primary" onclick="window.location.href = '/review/${review.id}/edit';">Edit</button>
                    </td>
                    <td>
                        <button class="btn btn-primary" onclick="window.location.href = '/review/${review.id}/delete';">Delete</button>
                    </td>
                </tr>
            <#else>
                No reviews
            </#list>
            </tbody>
        </table>

    </div>
    </div>


</@h.hah>
