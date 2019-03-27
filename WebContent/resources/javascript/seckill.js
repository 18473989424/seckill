//存放主要交互逻辑
//javascript 模块化

var seckill ={
	//封装秒杀相关ajax的url
	url: {
		now : function(){
			return '/seckill/seckill/time/now';
		},
        exposer : function(seckillId){
        	return '/seckill/seckill/'+seckillId+'/exposer';
        },
        exexution : function(seckillId,md5){
    		return '/seckill/seckill/'+seckillId+'/'+md5+'/execution';
    	}
	},
	//验证手机号
	validatePhone(phone){
		if(phone && phone.length == 11 && !isNaN(phone)){
			return true;
		}else{
			return false;
		}
	},
	handleSeckillKill:function(seckillId,node){
		//执行秒杀
		node.hide()
		.html("<button class='btn btn-primary btn-lg' id='killBtn'>开始秒杀</button>");
		$.post(seckill.url.exposer(seckillId),{},function(result){
			//在回掉函数中执行交互流程
			if(result && result['success']){
				var exposer = result['data'];
				if(exposer['exposed']){
					//开启秒杀
					var md5 = exposer['md5'];
					var killUrl = seckill.url.exexution(seckillId, md5);
					$('#killBtn').one('click',function(){
						//执行秒杀请求
						//1:禁用按钮
						$(this).addClass('disabled');
						//2：发送秒杀请求
						$.post(killUrl,{},function(result){
							var killResult = result['data'];
							var state = killResult['state'];
							var stateInfo = killResult['stateinfo'];
							//3：显示秒杀结果
							node.html('<span calss="label label-success">'+stateInfo+'</span>');
						});
					});
					node.show();
				}else{
					//未开启秒杀
					//重新计时
					var now = result['now'];
					var start = result['strat'];
					var end = result['end'];
					seckill.countdown(seckillId,now,start,end);
				}
			}else{
				console.log('result:'+result);
			}
		});
	},
	countdown:function(seckillId,nowTime,startTime,endTime){
		var seckillBox = $("#seckill-box");
		//时间判断
		if(nowTime>endTime){
			//秒杀结束
			seckillBox.html("秒杀结束");
		}else if(nowTime < startTime){
			//秒杀结束，计时事件绑定
			var killTime = new Date(startTime + 1000);
			seckillBox.countdown(killTime,function(event){
				//时间格式
				var format = event.strftime('秒杀倒计时： %D天 %H时 %M分 %S秒')
				seckillBox.html(format);
				/*时间完成后回调事件*/
			}).on('finish.countdown',function(){
				//获取秒杀地址,控制实现逻辑，执行秒杀
				seckill.handleSeckillKill(seckillId,seckillBox);
			});
		}else{
			//秒杀开始
			seckill.handleSeckillKill(seckillId,seckillBox);
		}
	},
	//详情秒杀逻辑
	detail: {
		init: function(params){
			//手机验证和登录，计时交互
			//规划我们的交互流程
			//从cookie中获取手机号
			var killPhone = $.cookie('killPhone');
			var seckillId = params['seckillId'];
			var startTime = params['startTime'];
			var endTime = params['endTime'];
			//验证手机号
			if(!seckill.validatePhone(killPhone)){
				//绑定手机号
				//控制输出
				var killPhoneModal = $("#killPhoneModal");
				//显示弹出层
				killPhoneModal.modal({
					show:true,  
					backdrop: 'static', //禁止位置关闭
					keyboard:false  //关闭键盘事件
				});
				$("#killPhoneBtn").click(function(){
					var inputPhone = $("#killPhoneKey").val();
					if(seckill.validatePhone(inputPhone)){
						//电话写入cookie
						$.cookie('killPhone',inputPhone,{expires:7,path:'/seckill'})
						//刷新页面
						window.location.reload();
					}else{
						$("#killPhoneMessage").hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
					}
				});
			}
			//已经登录
			//计时交互
			$.get(seckill.url.now(),{},function(result){
				if(result && result['success']){
					var nowTime = result['data'];
					//时间判断
					seckill.countdown(seckillId,nowTime,startTime,endTime);
				}else{
					console.log("result:"+result)
				}
			});
		}
	}
}