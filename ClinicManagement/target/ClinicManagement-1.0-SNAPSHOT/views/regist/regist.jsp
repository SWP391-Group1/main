<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Sign up</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
          integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
    <link
            href="<c:url value='../../static/css/login/style.css' />"
            rel="stylesheet" type="text/css" media="all"/>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div class="container h-100">
    <div class="d-flex justify-content-center" style="padding: 30px">
        <div class="user_regist_card">
            <div class="d-flex justify-content-center">
                <div>
                    <h2>Register Account</h2>
                </div>
            </div>
            <div class="d-flex justify-content-center">
                <form onsubmit="return checkFormRegist()" id="formLogin" action="<c:url value="/user/sign-up"/>" method="POST" enctype="multipart/form-data">
                    <c:if test="${not empty requestScope.messageParam}">
                        <div class="alert alert-${requestScope.alert}" role="alert">
                                ${requestScope.messageParam}
                        </div>
                    </c:if>
                    <div class="row">
                        <div class="form-group" style="margin: 10px">
                            <label for="username" class="col-form-label">User name</label>
                            <input required id="username" name="username" type="text" class="form-control" id="regisName"/>
                        </div>
                        <div class="form-group" style="margin: 10px">
                            <label for="firstName" class="col-form-label">First name</label>
                            <input required id="firstName" name="firstName" type="text" class="form-control"/>
                        </div>
                        <div class="form-group" style="margin: 10px">
                            <label for="lastName" class="col-form-label">Last name</label>
                            <input required id="lastName" name="lastName" type="text" class="form-control"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group" style="margin: 10px">
                            <label for="regisEmail" class="col-form-label">Email</label>
                            <input required name="txtEmail" type="email" class="form-control" id="regisEmail">
                        </div>
                        <div class="form-group" style="margin: 10px">
                            <label for="regisPhone" class="col-form-label">Phone number</label>
                            <input required name="txtPhone" type="text" class="form-control" id="regisPhone"/>
                            <p style="color: red" id="checkPhone"></p>
                        </div>
                        <div class="form-group" style="margin: 10px">
                            <label for="cardId" class="col-form-label">Card Id</label>
                            <input required id="cardId" name="cardId" type="text" class="form-control" id="cardId"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="dob" class="col-form-label">DOB</label><br/>
                        <input required id="dob" name="dob" type="date"/>
                    </div>
                    <div class="form-group">
                        <label class="form-check-label" for="exampleRadios1">
                            Gender
                        </label>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="gender" id="exampleRadios1" value="0"
                                   checked>
                            <label class="form-check-label" for="exampleRadios1">
                                Female
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="gender" id="exampleRadios2" value="1">
                            <label class="form-check-label" for="exampleRadios2">
                                Male
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="regisPass" class="col-form-label">Password</label>
                        <input required name="password" type="password" class="form-control" id="regisPass">
                    </div>
                    <div class="form-group">
                        <label for="regisCheckPass" class="col-form-label">Confirm Password</label>
                        <input required name="rePassword" type="password" class="form-control" id="regisCheckPass">
                        <p style="color: red" id="checkConfirm"></p>
                    </div>
                    <div class="form-group">
                        <label for="dob" class="col-form-label">Avatar</label><br/>
                        <input required id="avatar" onchange="readURL(this);" name="avatar" type="file" accept="image/*" />
                        <img id="blah" width="200" height="200" src="<c:url value="/static/image/image.png"/>" alt="your image" />
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Regist</button>
                    </div>
                </form>
            </div>

            <div class="mt-4">
                <div class="d-flex justify-content-center links">
                    Have an account? <a href="<c:url value="/user/sign-in"/>" class="ml-2">Sign
                    In</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#blah')
                    .attr('src', e.target.result)
                    .width(200)
                    .height(200);
            };
            reader.readAsDataURL(input.files[0]);
        }else{
            $('#blah')
                .attr('src',"/static/image/image.png")
                .width(200)
                .height(200);
        }
    }
    const PATTERN_TEXT = "[A-Za-z0-9]{1,100}";
    const PATTERN_EMAIL = "[A-Za-z0-9]{1,}@[A-Za-z0-9]{1,}";
    const PATTERN_PHONE = "[0-9]{10,10}";
    const checkFormRegist = () => {
        // document.getElementById("checkName").innerHTML = "";
        // document.getElementById("checkPass").innerHTML = "";
        document.getElementById("checkConfirm").innerHTML = "";
        document.getElementById("checkPhone").innerHTML = "";
        //var regisName = document.getElementById("regisName").value.trim();
        var regisPass = document.getElementById("regisPass").value.trim();
        var regisCheckPass = document.getElementById("regisCheckPass").value.trim();
        var regisPhone = document.getElementById("regisPhone").value.trim();
        var check = true;
        if(regisPhone.length!=10){
            document.getElementById("checkPhone").innerHTML = "invalid phone"
            check = false;
        }
        if (!regisPhone.match(PATTERN_PHONE)) {
            document.getElementById("checkPhone").innerHTML = "invalid phone"
            check = false;
        }
        if (check) {
            if (regisPass !== regisCheckPass) {
                document.getElementById("checkConfirm").innerHTML = "invalid confirm"
                check = false;
            }
        }
        return check;
    }

</script>
</html>