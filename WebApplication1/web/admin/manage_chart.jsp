<%-- 
    Document   : manage_category
    Created on : Dec 20, 2018, 4:28:22 PM
    Author     : AnhDuc
--%>

<%@page import="model.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.CategoryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thống kê</title>
        
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="${root}/css/mos-style.css" rel='stylesheet' type='text/css' />
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
           // Load the Visualization API and the piechart package.
           google.load('visualization', '1', {'packages': ['columnchart']});

           // Set a callback to run when the Google Visualization API is loaded.
           google.setOnLoadCallback(drawChart);

           // Callback that creates and populates a data table,
           // instantiates the pie chart, passes in the data and
           // draws it.
           function drawChart() {

               // Create the data table.    
               var data = google.visualization.arrayToDataTable([
                   ['Country', 'Area(square km)'],
            <c:forEach var="item" items="${listItem}">['${item.name}',${item.value}],</c:forEach>
                ]);
                // Set chart options
                var options = {
                    'title': 'Thống kê danh mục sách',
                    is3D: true,
                    pieSliceText: 'label',
                    tooltip: {showColorCode: true},
                    'width': 700,
                    'height': 300
                };

                // Instantiate and draw our chart, passing in some options.
                var chart = new google.visualization.PieChart(document.getElementById('chart'));
                chart.draw(data, options);
            }
        </script>
        
        
        <script type="text/javascript">
           // Load the Visualization API and the piechart package.
           google.load('visualization', '1', {'packages': ['columnchart']});

           // Set a callback to run when the Google Visualization API is loaded.
           google.setOnLoadCallback(drawChart);

           // Callback that creates and populates a data table,
           // instantiates the pie chart, passes in the data and
           // draws it.
           function drawChart() {

               // Create the data table.    
               var data = google.visualization.arrayToDataTable([
                   ['Country', 'Area(square km)'],
            <c:forEach var="item" items="${listItemBillDetail}">['${item.name}',${item.value}],</c:forEach>
                ]);
                // Set chart options
                var options = {
                    'title': 'Thống kê thể loại sách được order',
                    is3D: true,
                    pieSliceText: 'label',
                    tooltip: {showColorCode: true},
                    'width': 700,
                    'height': 300
                };

                // Instantiate and draw our chart, passing in some options.
                var chart2 = new google.visualization.PieChart(document.getElementById('chart2'));
                chart2.draw(data, options);
            }
        </script>
 
    </head>
    <body>
        
        <jsp:include page="header.jsp"></jsp:include>
        
        <div id="wrapper">

            <jsp:include page="menu1.jsp"></jsp:include>
            <div id="rightContent">
                <h3>Thống kê sách theo danh mục sách</h3>
               
                <table class="data">
                    <div id="chart"></div>
                    
                </table>
            </div>
            <div class="clear"></div>
            <div id="rightContent">
                <h3>Thống kê lượng mua sách theo danh mục</h3>
               
                <table class="data">
                    <div id="chart2"></div>
                    
                </table>
            </div>
            <div class="clear"></div>
            <jsp:include page="footer.jsp"></jsp:include>

        </div>
        
    </body>
</html>
