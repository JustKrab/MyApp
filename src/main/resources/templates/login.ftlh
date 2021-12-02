<#import "parts/common_parts.ftlh" as h>

<@h.hah>
    <div class="form-signin">
        <form action="/login" method="POST">

            <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

            <div class="form-floating">
                <span style="color: grey;size: 15px">Username</span>
                <input type="text" name="username" class="form-control" id="floatingInput" placeholder="Username" />
            </div>
            <div class="form-floating">
                <span style="color: grey;size: 15px">Password</span>
                <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Password">
            </div>
            <br>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>

        </form>
    </div>
</@h.hah>