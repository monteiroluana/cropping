<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

        <title>Luana Monteiro</title>
        <style>
            html, body { margin:0; height: 100%;}

        </style>
    </head>
    <body>
        <div style="height:70%; background-color: #FFCC00;">  

            <div style="height:60%; line-height: 400px;text-align: center; font-size: 40pt;">
               Luana Monteiro Pereira
            </div>

            <form class="md-form" action="Cropping" method="GET" >
                <div class="file-field">
                    <div class="btn btn-primary btn-sm float-left" style=" margin-left: 31%">
                        <span>Selecionar Imagem</span>
                        <input type="file" id="file" name="file">
                    </div>
                </div>
                <div>
                    <input type="submit" value="Enviar">
                </div>
            </form>
            ${msg}

        </div >

        <div style="height: 30%;">


        </div>

    </body>
</html>
