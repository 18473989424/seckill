<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>秒杀列表页</title>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body>
    <!-- 页面显示部分 -->
    <div class="container">
    	<div class="panel panel-default">
    		<div class="panel-heading text-center">
    			<h2>秒杀列表</h2>
    		</div>
    		<div class="panel-body">
    			<table class="table table-hover">
    				<thead>
    					<tr>
    						<th>名称</th>
    						<th>库存</th>
    						<th>开始时间</th>
    						<th>结束时间</th>
    						<th>创建时间</th>
    					</tr>
    				</thead>
    				<tbody>
    					<c:forEach items="${list}" var="sk">
    						<tr>
	    						<td>${sk.name}</td>
	    						<td>${sk.number}</td>
	    						<td>
	    							<fmt:formatDate value="${sk.startTime}" pattern="yyyy-mm-dd hh:mm:ss"/>
	    						</td>
	    						<td>
	    							<fmt:formatDate value="${sk.endTime}" pattern="yyyy-mm-dd hh:mm:ss"/>
	    						</td>
	    						<td>
	    							<fmt:formatDate value="${sk.createTime}" pattern="yyyy-mm-dd hh:mm:ss"/>
	    						</td>
	    						<td>
	    							<a class="btn btn-info" href="${sk.seckillId}/detail" target="_blank"><span style="font-size: 14px">秒杀</span></a>
	    						</td>
    						</tr>
    					</c:forEach>
    				</tbody>
    			</table>
    		</div>
    	</div>
    </div>

    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </body>
</html>