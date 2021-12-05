<#import "parts/common_parts.ftlh" as h>
<#include "parts/know.ftlh">
<#import "/spring.ftl" as spring/>
<@h.hah>

    <div class="form-review-show container mt-5">
        ${empty?ifExists}
        <div class="form-review">
            <span style="font-size:15px; color: gray">Review on: </span>
            <br>
            <strong style="font-size: 20px">${review.getTheme()}</strong>
            <br>
            <span style="font-size:15px; color: gray"><@spring.message "review.authr"/> </span>
            <br>
            <strong style="font-size: 25px">${review.getAuthorName()}</strong>
            <br>
            <span style="font-size:15px; color: gray"><@spring.message "review.titl.pl"/> </span>
            <br>
            <strong style="font-size: 25px;width: 300px" class="text-break">${review.getTitle()}</strong>
            <br>
            <span style="font-size:15px; color: gray"><@spring.message "review.rtng"/> </span>
            <br>
            <strong style="font-size: 25px;width: 300px">${review.getRating()}/10</strong>
            <br>
            <span style="font-size:15px; color: gray"><@spring.message "review.rv"/></span>
            <br>
            <div class="form-review" style="width: 420px">
                <a style="font-size: 18px;width: 150px" class="text-break">${review.getText()}</a>
            </div>
            <br>
            <div>
                <#list photos as photo>
                    <a class="first" href="http://res.cloudinary.com/myreviewapp/image/upload/${photo.filename}"><img style="
   top:50%;
   left:50%;
   width:300px;
   height:300px;
   object-fit:cover;" src="http://res.cloudinary.com/myreviewapp/image/upload/${photo.filename}"></a>
                    </#list>
            </div>
            <br>
            <div>
                <span><@spring.message "like"/></span>
                <span style="font-size: 20px;color: red"> ❤ ${likes} </span>
                <span><@spring.message "usr.rating"/></span>
                <span style="font-size: 20px;color: goldenrod"> ★ ${rating} (${counts})</span>
            </div>

            <br>
            <#if user??>

                <h3>U can rate this review</h3>
                <div class="form-review-estimate">
                    <form action="${review.getId()}" method="POST" id="estimate">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="liked" value="0"
                                   id="flexRadioDefault1">
                            <label class="form-check-label" for="flexRadioDefault1">
                                <@spring.message "inLike"/>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="liked" value="1"
                                   id="flexRadioDefault2"
                                   checked>
                            <label class="form-check-label" for="flexRadioDefault2">
                                <@spring.message "iLike"/>
                            </label>
                        </div>
                        <div class="star-rating clearfix">
                            <div class="star-rating__wrap">
                                <div class="star-rating__rotate">
                                    <input class="star-rating__input" id="star-rating-5" type="radio" name="rating"
                                           value="5">
                                    <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-5"
                                           title="5 out of 10 stars"></label>
                                    <input class="star-rating__input" id="star-rating-4" type="radio" name="rating"
                                           value="4">
                                    <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-4"
                                           title="4 out of 10 stars"></label>
                                    <input class="star-rating__input" id="star-rating-3" type="radio" name="rating"
                                           value="3">
                                    <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-3"
                                           title="3 out of 10 stars"></label>
                                    <input class="star-rating__input" id="star-rating-2" type="radio" name="rating"
                                           value="2">
                                    <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-2"
                                           title="2 out of 10 stars"></label>
                                    <input class="star-rating__input" id="star-rating-1" type="radio" name="rating"
                                           value="1">
                                    <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-1"
                                           title="1 out of 10 stars"></label>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <br>
                        <button class="btn btn-primary" id="est" type="submit">Estimate</button>
                    </form>
                </div>

                <br>
                <h3><@spring.message "comments"/></h3>
                <br>

                <div class="form-review-add-comment">
                    <form action="${review.getId()}" method="POST">
                        <label>
                            <textarea cols="70" rows="5" class="form-control" name="text"
                                      placeholder="<@spring.message "comments.add"/>"></textarea>
                        </label>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <br>
                        <button class="btn btn-primary" type="submit"><@spring.message "comments"/></button>
                    </form>
                </div>
                <br>
            </#if>
            <br>
            <#list comments as comment>
                <ol class="list-group list-group">
                    <li class="list-group-item d-flex justify-content-between align-items-start">
                        <div class="ms-2 me-auto">
                            <div class="fw-bold"><strong
                                        style="color: dodgerblue">${comment.getUser().getUsername()}</strong>
                            </div>
                            <a>${comment.text}</a>
                        </div>
                        <span class="badge bg-primary rounded-pill">${comment.getTime()}</span>
                    </li>
                </ol>
            </#list>
        </div>
    </div>


</@h.hah>
