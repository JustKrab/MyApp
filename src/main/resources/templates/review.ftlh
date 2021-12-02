<#import "parts/common_parts.ftlh" as h>
<#include "parts/know.ftlh">

<@h.hah>

    <div class="form-review-show container mt-5">
        ${empty?ifExists}
        <div class="form-review">
            <span style="font-size:15px; color: gray">Review on: </span>
            <br>
            <strong style="font-size: 20px">${review.getTheme()}</strong>
            <br>
            <span style="font-size:15px; color: gray">From: </span>
            <br>
            <strong style="font-size: 25px">${review.getAuthorName()}</strong>
            <br>
            <span style="font-size:15px; color: gray">Review title: </span>
            <br>
            <strong style="font-size: 25px;width: 300px" class="text-break">${review.getTitle()}</strong>
            <br>
            <span style="font-size:15px; color: gray">Rating: </span>
            <br>
            <strong style="font-size: 25px;width: 300px">${review.getRating()}/10</strong>
            <br>
            <span style="font-size:15px; color: gray">Review: </span>
            <br>
            <div class="form-review" style="width: 420px">
                <a style="font-size: 18px;width: 150px" class="text-break">${review.getText()}</a>
            </div>
            <br>
            <div>
                <#list photos as photo>

                    <img style="
   top:50%;
   left:50%;
   width:300px;
   height:300px;
   object-fit:cover;" src="http://res.cloudinary.com/myreviewapp/image/upload/${photo.filename}">

                </#list>
            </div>
            <br>
            <div>
                <span>Likes: </span>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="red" class="bi bi-suit-heart-fill"
                     viewBox="0 2 16 14">
                    <path d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z"/>
                </svg>

                <span style="font-size: 20px;color: red"> ${likes} </span>
                <span>User rating: </span>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="gold"
                     class="bi bi-star-fill" viewBox="0 4 16 13">
                    <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
                </svg>

                <span style="font-size: 20px;color: gold">${rating} (${counts})</span>
            </div>

            <br>
            <#if user??>
                <h3>U can rate this review</h3>
                <div class="form-review-estimate">
                    <form action="${review.getId()}" method="POST" id="estimate">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="liked" value="0" id="flexRadioDefault1">
                            <label class="form-check-label" for="flexRadioDefault1">
                                Dont like it
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="liked" value="1" id="flexRadioDefault2"
                                   checked>
                            <label class="form-check-label" for="flexRadioDefault2">
                                I liked it
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
                <h3>Add comment</h3>
                <br>

                <div class="form-review-add-comment">
                    <form action="${review.getId()}" method="POST">
                        <label>
                            <textarea cols="70" rows="5" class="form-control" name="text"
                                      placeholder="Write your comment here..."></textarea>
                        </label>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <br>
                        <button class="btn btn-primary" type="submit">Add comment</button>
                    </form>
                </div>
                <br>
            </#if>
            <br>
            <#list comments as comment>
                <ol class="list-group list-group">
                    <li class="list-group-item d-flex justify-content-between align-items-start">
                        <div class="ms-2 me-auto">
                            <div class="fw-bold"><strong style="color: dodgerblue">${comment.getUser().getUsername()}</strong>
                            </div>
                            <a>${comment.text}</a>
                        </div>
                        <br>
                    </li>
                </ol>
            </#list>
        </div>
    </div>
<#--        <script>-->
<#--            var value = ${review.rating}-->
<#--                console.log(value)-->
<#--            $("#star-rating-" + value)-->
<#--            $(".star-rating__rotate > label").attr("value")-->
<#--        </script>-->




</@h.hah>
