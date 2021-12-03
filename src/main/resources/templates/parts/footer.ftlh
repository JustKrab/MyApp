<#import "/spring.ftl" as spring/>
<footer class="d-flex flex-wrap justify-content-between align-items-center mt-4 py-3 border-top">
        <p class="col-md-4 mb-0 text-muted">&copy; 2021 Review!ON!, Inc</p>


        <ul class="nav col-md-4 justify-content-end">
            <li class="nav-item"><a href="/" class="nav-link px-2 text-muted"><@spring.message "thome"/></a></li>
            <button class="btn btn-primary" onclick="myFunction()"><@spring.message "switch.theme"/></button>
            <li class="menu-item">
                <span><@spring.message "switch.lang"/></span>
                <select id="locales">
                    <option value=" "></option>
                    <option value="en">English</option>
                    <option value="ru">Russian(Русский)</option>
                </select>
            </li>
        </ul>
</footer>