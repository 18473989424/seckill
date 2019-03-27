<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>秒杀详情页</title>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

  </head>
  <body>
    <div class="container">
    	<div class="panel panel-default text-center">
    		<div class="panel-heading">
    			${seckill.name}
    		</div>
    		<div class="panel-body">
    			<h2 class="text-danger"></h2>
    			<!-- 显示time图标 -->
    			<span class="glyphicon glyphicon-time"></span>
    			<!-- 展示倒计时 -->
    			<span class="glyphicon" id="seckill-box"></span>
    		</div>
    		<div class="modal fade" id="killPhoneModal">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title">秒杀电话：</h4>
			      </div>
			      <div class="modal-body">
			        <input type="text" placehoider="请填写手机号(*^_^*)" id="killPhoneKey">
			      </div>
			      <div id="killPhoneMessage"></div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-primary" id="killPhoneBtn">Submit</button>
			      </div>
			    </div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
    	</div>
    </div>

    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </body>
  <script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
  <script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
  <script type="text/javascript" src="../../resources/javascript/seckill.js"></script>
  <script type="text/javascript">
  	$(function(){
  		//使用EL表达式传入参数
  		seckill.detail.init({
  			seckillId : ${seckill.seckillId},
  	  		startTime : ${seckill.startTime.time},//毫秒
  	  		endTime : ${seckill.endTime.time}
  		});
  	});
  </script>
</html>