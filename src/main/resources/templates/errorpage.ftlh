<#import "parts/common_parts.ftlh" as h>
<#import "/spring.ftl" as spring/>
<@h.hah>
    <div class="container mt-5">
        <h2><@spring.message "error"/></h2>
        <button style="margin-right: 30%" class="btn btn-primary" onclick="window.location.href = '/';"><@spring.message "error.main"/></button>
    </div>
</@h.hah>
