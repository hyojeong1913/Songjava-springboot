<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale">

    <title>Example Search</title>

    <link rel="stylesheet" href="<c:url value='/libs/bootstrap-4.4.1-dist/css/bootstrap.min.css' />">
</head>
<body>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <script src="<c:url value='/libs/bootstrap-4.4.1-dist/js/bootstrap.min.js'/>"></script>

    <div class="container">
        <h2>게시글 목록</h2>

        <form action="" method="get">
            <div class="mb-3 row">
                <label for="exampleFormControlInput1" class="col-sm-2 col-form-label">종류</label>

                <div class="col-sm-10">
                    <tag:bootstrap-checkbox items="${boardTypes}" values="${parameter.boardTypes}" />
                </div>
            </div>

            <div class="mb-3 text-center">
                <button type="submit" class="btn btn-primary">검색하기</button>
            </div>
        </form>

        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">First</th>
                    <th scope="col">Last</th>
                    <th scope="col">Handle</th>
                </tr>
            </thead>
        </table>
    </div>
</body>
</html>