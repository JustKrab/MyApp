<#import "parts/common_parts.ftlh" as h>

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
        <h5>${user.getUsername()}</h5>
        <br>
        <br>
        <div class="container mt-3">
            <h2>Reviews</h2>
            <input class="form-control" id="filter" type="text" placeholder="Search..">
            <br>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Theme</th>
                    <th>Rating</th>
                    <th>Title</th>
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
                    No reviews
                </#list>
                </tbody>
            </table>

        </div>
    </div>

<#--    <script>-->
<#--        $(document).ready(function(){-->
<#--            $("#filter").on("keyup", function() {-->
<#--                var value = $(this).val().toLowerCase();-->
<#--                $("#reviews tr").filter(function() {-->
<#--                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)-->
<#--                });-->
<#--            });-->
<#--        });-->
<#--    </script>-->

</@h.hah>
