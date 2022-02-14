<%--
  Created by IntelliJ IDEA.
  User: dinis
  Date: 10.02.2022
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.kpfu.itis.form.SongsForm" %>
<html>
<head>
    <title>Songs</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        <%@include file="css/song.css"%>
    </style>
</head>
<body> style="background-color: #bdf8f8">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<nav>
    <div class="row">
        <div class="navbar fixed-top navbar-expand-lg bg-light container-fluid">
            <div class="col-10">
                <div class="navbar-header">
                    <a class="navbar-brand" style="color: darkblue">SONG</a>
                </div>
            </div>
            <div class="col-2">
                <a class="navbar-brand" href="/list">
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" class="bi bi-list" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
                    </svg>
                </a>
                <a class="navbar-brand" href="/wishList">
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" class="bi bi-suit-heart" viewBox="0 0 16 16">
                        <path d="m8 6.236-.894-1.789c-.222-.443-.607-1.08-1.152-1.595C5.418 2.345 4.776 2 4 2 2.324 2 1 3.326 1 4.92c0 1.211.554 2.066 1.868 3.37.337.334.721.695 1.146 1.093C5.122 10.423 6.5 11.717 8 13.447c1.5-1.73 2.878-3.024 3.986-4.064.425-.398.81-.76 1.146-1.093C14.446 6.986 15 6.131 15 4.92 15 3.326 13.676 2 12 2c-.777 0-1.418.345-1.954.852-.545.515-.93 1.152-1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z"/>
                    </svg>
                </a>
            </div>
        </div>
    </div>
</nav>
<div class="container">
    <div class="card">
        <div class="card-body">
            <%
                SongsForm song = (SongsForm) request.getAttribute("song");
            %>
            <h3 class="card-title"><%=song.getName().toLowerCase(java.util.Locale.ROOT)%></h3>
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="white-box text-center">
                        <img src="/static/songs/<%=song.getPicture()%>" height="500px" width="500px" class="img-responsive">
                    </div>
                    <p> </p>
                    <form action="/songs?id=<%=song.getId()%>" method="post">
                        <div class="d-grid gap-2 col-6 mx-auto">
                            <button type="submit" name="action" class="btn btn-outline-info" value="addToWishList">
                                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" class="bi bi-suit-heart" viewBox="0 0 16 16">
                                    <path d="m8 6.236-.894-1.789c-.222-.443-.607-1.08-1.152-1.595C5.418 2.345 4.776 2 4 2 2.324 2 1 3.326 1 4.92c0 1.211.554 2.066 1.868 3.37.337.334.721.695 1.146 1.093C5.122 10.423 6.5 11.717 8 13.447c1.5-1.73 2.878-3.024 3.986-4.064.425-.398.81-.76 1.146-1.093C14.446 6.986 15 6.131 15 4.92 15 3.326 13.676 2 12 2c-.777 0-1.418.345-1.954.852-.545.515-.93 1.152-1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z"/>
                                </svg>
                            </button>
                        </div>
                    </form>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <h4 class="box-title mt-5">Аккорды: C G Am F</h4>
                    <p>    Всему несбывшемуся посвящается!</p>
                    <p>    У каждого здесь есть своя правда</p>
                    <p>    Как правильно здесь жить я не знаю</p>
                    <p>    Лишь хочу видить в глазах твоих много кайфа</p>
                    <p>    И на взлетке чтоб в прыжке от ног кросы отлетали</p>
                    <p>    Насмотреться друг на друга под утро</p>
                    <p>    Ведь нам не быть с тобою вместе, ты знаешь</p>
                    <p>    Во взрослой жизни очень много замутов</p>
                    <p>    Лишь просто дай тебя обниму и вместе полетаем</p>
                    <p>    В легких тает дым и над заливом</p>
                    <p>    Запомни ты меня таким, таким счастливым</p>
                    <p>    В легких тает дым, и тем же утром</p>
                    <p>    Запомни ты меня таким, таким придурком</p>
                    <p>    Нам не растить с тобой детей, не видеть старость</p>
                    <p>    Не заставлять квартиру новыми вещами</p>
                    <p>    Скорей всего, я не увижу тебя больше</p>
                    <p>    Так что жить в кайф и не унывать пообещай мне</p>
                    <p>    Ведь все, что в этой жизни происходит</p>
                    <p>    Не поддается никакому объяснению</p>
                    <p>    Ну пока у нас с тобой есть еще минут сорок</p>
                    <p>    Так давай друг друга вдохнём и остановим время</p>
                    <p>    В легких тает дым, и над заливом</p>
                    <p>    Запомни ты меня таким, таким счастливым</p>
                    <p>    В легких тает дым, и тем же утром</p>
                    <p>    Запомни ты меня таким, таким придурком</p>
                    <p>    Таким счастливым</p>
                    <p>    В легких тает дым и тем же утром</p>
                    <p>   Запомни ты меня таким, таким придурком.</p>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
