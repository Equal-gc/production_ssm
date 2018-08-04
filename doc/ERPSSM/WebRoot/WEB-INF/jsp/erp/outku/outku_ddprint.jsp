<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<!-- 下拉框 -->
	<link rel="stylesheet" href="static/ace/css/chosen.css" />
	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/index/top.jsp"%>
</head>
<body class="no-skin">
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
	<!-- /section:basics/sidebar -->
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
					
						<div style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 10px;">订单编号:</td>
								<td style="padding-top: 10px;">${pd.ORDER_NUMBER}</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 10px;">商品名称:</td>
								<td style="padding-top: 10px;">${pd.GOODS_NAME}</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 10px;">出库时间:</td>
								<td style="padding-top: 10px;">${pd.OUTTIME}</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 10px;">数量:</td>
								<td style="padding-top: 10px;">${pd.INCOUNT}</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 10px;">单价:</td>
								<td style="padding-top: 10px;">${pd.PRICE}&nbsp;元</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 10px;">总价:</td>
								<td style="padding-top: 10px;">${pd.ZPRICE}&nbsp;元</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 10px;">备注:</td>
								<td style="padding-top: 10px;">${pd.BZ}</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 10px;">客户:</td>
								<td style="padding-top: 10px;">${pd.CUSTOMER_NAME}</td>
							</tr>
						</table>
						</div>
						
						<table id="table_report" class="table table-striped table-bordered table-hover">
						<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary"  onClick="window.print();">打印</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->


	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		
		//通过商品编码读取数据
		function getByBm(BIANMA){
			$.ajax({
				type: "POST",
				url: 'goods/getByBm.do',
		    	data: {BIANMA:BIANMA,tm:new Date().getTime()},
				dataType:'json',
				cache: false,
				success: function(data){
					if("success" == data.result){
						$("#GOODS_ID").val(data.pd.GOODS_ID);
						$("#GOODS_NAME").val(data.pd.TITLE);
					}else{
						$("#BIANMA").tips({
							side : 1,
							msg : "此编码不存在",
							bg : '#FF5080',
							time : 15
						});
					}
				}
			});
		}
		
		//设置商品ID和名称
		function setGoogsName(){
			var selectVale = $("#SGOODS_ID").val();
			var selectText = $("#SGOODS_ID").find("option:selected").text();
			$("#GOODS_ID").val(selectVale);
			$("#GOODS_NAME").val(selectText);
		}
		
		//计算总价
		function jisuanz(){
			var INCOUNT = Number("" == $("#INCOUNT").val()?"0":$("#INCOUNT").val());
			var PRICE = Number("" == $("#PRICE").val()?"0":$("#PRICE").val());
			if(0-INCOUNT>0){
				$("#INCOUNT").tips({
					side:3,
		            msg:'数量不能小于零',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#INCOUNT").focus();
				return false;
			}
			if(0-PRICE>0){
				$("#PRICE").tips({
					side:3,
		            msg:'单价不能小于零',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRICE").focus();
				return false;
			}
			$("#ZPRICE").val(INCOUNT*PRICE);
		}
		
		//保存
		function save(){
			if($("#GOODS_ID").val()==""){
				$("#xzsp").tips({
					side:3,
		            msg:'请选择商品',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#GOODS_ID").focus();
			return false;
			}
			if($("#INCOUNT").val()==""){
				$("#INCOUNT").tips({
					side:3,
		            msg:'请输入数量',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#INCOUNT").focus();
			return false;
			}
			if($("#PRICE").val()==""){
				$("#PRICE").tips({
					side:3,
		            msg:'请输入单价',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRICE").focus();
			return false;
			}
			if($("#ZPRICE").val()==""){
				$("#ZPRICE").tips({
					side:3,
		            msg:'请输入总价',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ZPRICE").focus();
			return false;
			}
			if($("#BZ").val()==""){
				$("#BZ").tips({
					side:3,
		            msg:'请输入备注',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#BZ").focus();
			return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		$(function() {
			$("#ZPRICE").attr("readonly","readonly");
			$("#ZPRICE").css("color","gray");
			//下拉框
			if(!ace.vars['touch']) {
				$('.chosen-select').chosen({allow_single_deselect:true}); 
				$(window)
				.off('resize.chosen')
				.on('resize.chosen', function() {
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				}).trigger('resize.chosen');
				$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
					if(event_name != 'sidebar_collapsed') return;
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				});
				$('#chosen-multiple-style .btn').on('click', function(e){
					var target = $(this).find('input[type=radio]');
					var which = parseInt(target.val());
					if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
					 else $('#form-field-select-4').removeClass('tag-input-style');
				});
			}
		});
		
		//打开选择客户窗口
		function choiceCus(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="选择客户";
			 diag.URL = '<%=basePath%>customer/windowsList.do';
			 diag.Width = 700;
			 diag.Height = 545;
			 diag.Modal = false;			//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮
			 diag.CancelEvent = function(){ //关闭事件
				 var CUSTOMER_ID = diag.innerFrame.contentWindow.document.getElementById('CUSTOMER_ID').value;
				 if("" != CUSTOMER_ID){
					var NAME = diag.innerFrame.contentWindow.document.getElementById('NAME').value;
					$("#CUSTOMER_ID").val(CUSTOMER_ID); //客户ID
					$("#CUSTOMER_NAME").val(NAME);		//客户姓名
				}
				diag.close();
			 };
			 diag.show();
		}
		</script>
</body>
</html>