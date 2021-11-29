<#import "parts/common_parts.ftlh" as h>

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
        .star-rating__input {
            display: none;
        }

        .star-rating__rotate {
            transform: scaleX(-1);
            float: left;
        }

        .star-rating__ico:hover:before,
        .star-rating__ico:hover ~ .star-rating__ico:before,
        .star-rating__input:checked ~ .star-rating__ico:before {
            content: "\f005";
            color: yellow;
        }
    </style>
    <div class="form-review-add container mt-5">
        ${message?ifExists}
        <form action="/review/add/new" method="post" enctype="multipart/form-data">
            <span style="font-size:15px; color: gray">Choose group: </span>
            <p><select name="group">
                    <option selected value="BOOKS">Books</option>
                    <option value="GAMES">Games</option>
                    <option value="FILMS">Films</option>
                    <option value="SERIALS">Serials</option>
                </select>
                <br>
                <br>
                <span style="font-size:15px; color: gray">Choose Theme: </span>
            <div class="form-review">
                <input type="text" class="form-control" name="theme" placeholder="Theme"/>
            </div>
            <br>
            <span style="font-size:15px; color: gray">Сoncoct a title: </span>
            <div class="form-review">
                <input type="text" class="form-control" name="title" placeholder="Add title"/>
            </div>
            <br>
            <span style="font-size:15px; color: gray">Your review: </span>
            <br>
            <div class="form-review" style="width: 420px">
                <textarea cols="200" class="form-control" name="text" placeholder="Write your review"></textarea>
            </div>
            <br>
            <span style="font-size:15px; color: gray">Rating: </span>


            <div class="star-rating clearfix" >
                <div class="star-rating__wrap">
                    <div class="star-rating__rotate">
                        <input class="star-rating__input" id="star-rating-10" type="radio" name="rating" value="10">
                        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-10"
                               title="10 out of 10 stars"></label>
                        <input class="star-rating__input" id="star-rating-9" type="radio" name="rating" value="9">
                        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-9"
                               title="9 out of 10 stars"></label>
                        <input class="star-rating__input" id="star-rating-8" type="radio" name="rating" value="8">
                        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-8"
                               title="8 out of 10 stars"></label>
                        <input class="star-rating__input" id="star-rating-7" type="radio" name="rating" value="7">
                        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-7"
                               title="7 out of 10 stars"></label>
                        <input class="star-rating__input" id="star-rating-6" type="radio" name="rating" value="6">
                        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-6"
                               title="6 out of 10 stars"></label>
                        <input class="star-rating__input" id="star-rating-5" type="radio" name="rating" value="5">
                        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-5"
                               title="5 out of 10 stars"></label>
                        <input class="star-rating__input" id="star-rating-4" type="radio" name="rating" value="4">
                        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-4"
                               title="4 out of 10 stars"></label>
                        <input class="star-rating__input" id="star-rating-3" type="radio" name="rating" value="3">
                        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-3"
                               title="3 out of 10 stars"></label>
                        <input class="star-rating__input" id="star-rating-2" type="radio" name="rating" value="2">
                        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-2"
                               title="2 out of 10 stars"></label>
                        <input class="star-rating__input" id="star-rating-1" type="radio" name="rating" value="1">
                        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-1"
                               title="1 out of 10 stars"></label>
                    </div>
                </div>
            </div>
            <br>
<#--            <div class="form-group">-->
<#--                <div class="file-preview ">-->
<#--                    <button type="button" class="close fileinput-remove" aria-label="Close">-->
<#--                        <span aria-hidden="true">×</span>-->
<#--                    </button>    <div class=" file-drop-zone"><div class="file-drop-zone-title">Drag &amp; drop files here …</div>-->
<#--                        <div class="file-preview-thumbnails">-->
<#--                        </div>-->
<#--                        <div class="clearfix"></div>    <div class="file-preview-status text-center text-success"></div>-->
<#--                        <div class="kv-fileinput-error file-error-message" style="display: none;"></div>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->
            <div class="file-loading">
                <input id="file-1" type="file" name="file" multiple class="file" data-overwrite-initial="false">
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <br>

            <div class="form-form-review">
                <button type="submit" class="btn btn-primary">Add review</button>
            </div>
        </form>
    </div>

    <script src="/code.js"></script>
</@h.hah>
