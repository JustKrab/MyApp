<#import "parts/common_parts.ftlh" as h>
<#import "/spring.ftl" as spring/>
<@h.hah>
<div class="container mt-5">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th><@spring.message "profile.username"/></th>
            <th>Email:</th>

        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td><a style="color: dodgerblue" href="/userlist/${user.username}">${user.username}</a></td>
                <td>${user.email}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
</@h.hah>
