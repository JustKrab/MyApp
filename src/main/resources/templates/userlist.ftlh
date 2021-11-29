<#import "parts/common_parts.ftlh" as h>

<@h.hah>
<div class="container mt-5">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Username</th>
            <th>User email</th>

        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td><a style="color: dodgerblue" href="/userlist/${user.username}">${user.username}</a></td>
                <td>${user.email}</td>

            </tr>
        <#else>
            No users
        </#list>
        </tbody>
    </table>
</div>
</@h.hah>
