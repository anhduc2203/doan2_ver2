<%-- 
    Document   : upload
    Created on : Jan 9, 2019, 9:55:43 AM
    Author     : AnhDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload file</title>
    </head>
    <body>
<!--        Tệp upload được lưu trong thư mục sau khi build-->
        <center>
            <form method="post" action="UploadServlet" enctype="multipart/form-data">
                Select file to upload:
                <input type="file" name="uploadFile" />
                <br/><br/>
                <input type="submit" value="Upload" />
            </form>
        </center>
    </body>
</html>
