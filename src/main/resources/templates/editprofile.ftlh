<#import "parts/common_parts.ftlh" as h>
<#import "/spring.ftl" as spring/>
<@h.hah>
    <style>
        @import url('https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css');
        @import url('https://cdn.jsdelivr.net/npm/@forevolve/bootstrap-dark@1.0.0/dist/css/toggle-bootstrap-dark.css');
        @import url('https://cdn.jsdelivr.net/npm/@forevolve/bootstrap-dark@1.0.0/dist/css/toggle-bootstrap.css');
        html {
            height: 100%;
        }

        body {
            min-height: 100%;
            display: grid;
            grid-template-rows: auto 1fr auto;
        }
    </style>
    <div class="container mt-5">
    <form method="post" action="/profile/${user.getId()}/edit" enctype="multipart/form-data">
        <div >
            <div >
                <label ><@spring.message "profile.username"/></label>
                <div >
                    <label>
                        <input type="text" name="username" class="form-control" placeholder="<@spring.message "profile.username"/>"/>
                    </label>
                </div>
            </div>
            <br>
            <div >
                <label ><@spring.message "profile.pass"/></label>
                <div >
                    <label>
                        <input type="password" name="password" class="form-control"/>
                    </label>
                </div>
            </div>
            <br>
            <div >
                <label ><@spring.message "profile.pass.conf"/></label>
                <div >
                    <label>
                        <input type="password" name="passwordConfirm" class="form-control"/>
                    </label>
                </div>
            </div>
            <br>
        <div >
            <label >Email:</label>
            <div >
                <label>
                    <input type="email" name="email" class="form-control" placeholder="Email" value="${user.getEmail()}"/>
                </label>
            </div>
            <br>
        </div>
            <div >
                <label><@spring.message "profile.avatar"/></label>
                <input type="file" name="file">
            </div>
        </div>
        <br>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button  class="btn btn-primary" type="submit"><@spring.message "review.edt"/></button>
    </form>
    </div>
    <script src="/code.js"></script>
</@h.hah>