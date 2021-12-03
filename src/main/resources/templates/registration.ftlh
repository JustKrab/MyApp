<#import "parts/common_parts.ftlh" as h>
<#import "/spring.ftl" as spring/>
<@h.hah>

    <div class="container mt-5">
        ${userExists?ifExists}
<form action="/registration" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"><@spring.message "profile.username"/></label>
        <div class="col-sm-6">
            <input type="text" name="username" class="form-control" placeholder="<@spring.message "profile.username"/>" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Email:</label>
        <div class="col-sm-6">
            <input type="text" name="email" class="form-control" placeholder="Email" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"><@spring.message "profile.username"/></label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="<@spring.message "main.pass"/>" />
        </div>
    </div>
    <button class="btn btn-primary" type="submit"><@spring.message "create"/></button>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
</form>
    </div>
</@h.hah>