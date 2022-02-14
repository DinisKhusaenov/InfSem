<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.kpfu.itis.form.SongsForm" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>WishList</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        <%@include file="css/list.css" %>
    </style>
    <script src="/js/jquery.js"></script>
    <script src="/js/Products.js"></script>
</head>
<body>
<nav>
    <div class="row">
        <div class="navbar fixed-top navbar-expand-lg bg-light container-fluid">
            <div class="col-10">
                <div class="navbar-header">
                    <a class="navbar-brand" style="color: darkblue">Song</a>
                </div>
            </div>
            <div class="col-2">
                <a class="navbar-brand" href="/list">
                    <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" class="bi bi-list" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
                    </svg>
                </a>
                <a class="navbar-brand" href="/profile">
                    <svg xmlns="http://www.w3.org/2000/svg" width="34" height="34" class="bi bi-person" viewBox="0 0 16 16">
                        <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                    </svg>
                </a>
            </div>
        </div>
    </div>
</nav>

<div class="catalog-list">
    <div class="row row-cols-1 row-cols-md-4 g-4">
        <%
            List<SongsForm> songs = (List<SongsForm>) request.getAttribute("songs");
            for (SongsForm song : songs) {
        %>
        <div class="col">
            <div class="card h-100" style="background-color: #fffa98">
                <div class="song-grid">
                    <div class="song-image">
                        <a href="/songs?id=<%=song.getId()%>" class="image">
                            <img class="pic-1" style="border-radius: 100px" src="/static/songs/<%=song.getPicture()%>">
                        </a>
                    </div>
                    <div class="song-content">
                        <h3 class="title"><a href="/songs?id=<%=song.getId()%>"><%=song.getName()%></a></h3>
                        <form action="/wishList?id=<%=song.getId()%>" method="post">
                            <div class="d-grid gap-2 col-6 mx-auto">
                                <button type="submit" name="action" class="btn btn-outline-info" value="dropSong">
                                    <i class="bi bi-trash"></i>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
