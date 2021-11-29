<#--<#assign-->
<#--known = Session.SPRING_SECURITY_CONTEXT??-->
<#-->-->

<#--<#if known>-->
<#--    <#assign-->
<#--    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal-->
<#--    name = user.getUsername()-->
<#--    isUser = user.isUser()-->
<#--    isAdmin=user.isAdmin()-->
<#--    >-->
<#--<#else>-->
<#--    <#assign-->
<#--    name = "unknown"-->
<#--    isUser = false-->
<#--    >-->
<#--</#if>-->
<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isAdmin = user.isAdmin()
    >
<#else>
    <#assign
    name = "unknown"
    isAdmin = false
    >
</#if>
