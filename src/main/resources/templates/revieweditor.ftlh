<#import "parts/common_parts.ftlh" as h>
<#import "/spring.ftl" as spring/>
<@h.hah>
    <div class="container mt-5">
        ${message?ifExists}
    <div class="form-review-add">
        <form action="/review/${review.id}/edit" method="post" enctype="multipart/form-data">
            <input type="hidden" class="form-control" name="theme" value="${review.getTheme()}"/>
            <input type="hidden" class="form-control" name="group" value="${review.getGroups()}"/>
            <span style="font-size:15px; color: gray"><@spring.message "edit.title"/> </span>
            <br>
            <div class="form-review">
                <input type="text" name="title" class="form-control" value="${review.getTitle()}">
            </div>
            <br>
            <span style="font-size:15px; color: gray"><@spring.message "edit.rv"/>  </span>
            <br>
            <div class="form-review">

                <textarea class="form-control" name="text"
                          placeholder="Write your review">${review.getText()}</textarea>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <br>
            <br>
            <span style="font-size:15px; color: gray"><@spring.message "review.rtng"/> </span>
            <div class="star-rating clearfix">
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
            <div>
                <#list photos as photo>
                <a class="first" href="http://res.cloudinary.com/myreviewapp/image/upload/${photo.filename}">
                    <img style="
   top:50%;
   left:50%;
   width:300px;
   height:300px;
   object-fit:cover;" src="http://res.cloudinary.com/myreviewapp/image/upload/${photo.filename}">

                </#list>
            </div>
            <br>
            <br>
            <span style="font-size:15px; color: gray"><@spring.message "photo"/>  </span>
            <div class="file-loading" style="  .fileinput-upload{
            display: none;
        }">
                <input id="file-1" type="file" name="file" multiple class="file" data-overwrite-initial="false">
            </div>
            <br>
            <div class="form-form-review">
                <button type="submit" class="btn btn-primary">Edit</button>
            </div>
        </form>
    </div>
    </div>

</@h.hah>
