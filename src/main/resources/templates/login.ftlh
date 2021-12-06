<#import "parts/common_parts.ftlh" as h>
<#import "/spring.ftl" as spring/>
<@h.hah>
    <div class="form-signin">
        <form action="/login" method="POST">

            <h1 class="h3 mb-3 fw-normal"><@spring.message "pls.sign"/></h1>

            <div class="form-floating">
                <span style="color: grey;size: 15px"><@spring.message "profile.username"/></span>
                <input type="text" name="username" class="form-control" id="floatingInput" placeholder="Username" />
            </div>
            <div class="form-floating">
                <span style="color: grey;size: 15px"> <@spring.message "main.pass"/></span>
                <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Password">
            </div>
            <br>
<#--            <#list urls as key,value>-->
<#--            <a class="btn btn-primary btn-lg btn-block" style="background-color: #3b5998; margin-top: 2%"-->
<#--               href="${value}" role="button">-->
<#--                <i class="fab me-1 "></i>${key}-->
<#--            </a>-->
<#--            </#list>-->
            <br>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="w-100 btn btn-lg btn-primary" type="submit"><@spring.message "main.login"/></button>

        </form>
    </div>
</@h.hah>