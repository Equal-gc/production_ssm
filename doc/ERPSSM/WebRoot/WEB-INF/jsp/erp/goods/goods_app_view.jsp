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
	<!-- jsp文件头和头部 -->
	<%@ include file="../../system/index/top.jsp"%>
	
	<style type="text/css">
	
	**--案列图片部分样式--**/
	.example_box{ }
	.example-pic{ text-align: center; background-color:#fff; font-size: 2rem; font-weight: bold; color: #d9323c; padding: 1rem 0.8rem;}
	.good_mian{ background-color:#fff; padding:1rem 0.8rem 0 0.8rem; overflow:hidden;}
	.good_img{ width:100%; height:auto;}
	.good_img img{ max-width:100%; max-height:100%;}
	.good_cont{ box-sizing:border-box; padding: 1rem 0; text-align: center; font-size: 1.3rem;  color: #343434;}
	.yuyue-box{ padding: 1.2rem 0.8rem; text-align: center; background-color:#fff;}
	.yuyue-main{ display: inline-block;  width: 50%; background-color:#ff2938; -webkit-box-shadow:0 0 0.3rem 0.5rem #f0eeef; -moz-box-shadow:0 0 0.3rem 0.5rem #f0eeef;  box-shadow:0 0 0.3rem 0.5rem #f0eeef;  height: 2.6rem; line-height: 2.6rem; border-radius: 1.3rem; color: #fff; font-size: 1.4rem;}
		
	</style>
	
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
					
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<th colspan="10" style="padding-top: 13px;" class="center">商品详情</th>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商品名称:</td>
								<td colspan="10" style="padding-top: 13px;">${pd.TITLE}</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商品编码:</td>
								<td style="padding-top: 13px;">${pd.BIANMA}</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商品品牌:</td>
								<td style="padding-top: 13px;">${pd.BNAME}</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商品类别:</td>
								<td style="padding-top: 13px;">${pd.TNAME}</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">计量单位:</td>
								<td style="padding-top: 13px;">${pd.UNAME}</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商品简述:</td>
								<td colspan="10" style="padding-top: 13px;">${pd.SHORTDESC}</td>
							</tr>
							<tr>
								<td colspan="10" style="padding-top: 13px;"><div>${pd.DESCRIPTION}</div></td>
							</tr>
							<tr>
								<td colspan="10" style="padding-top: 13px;">
									<c:forEach items="${varList}" var="var" varStatus="vs">
								    <div class="good_mian mb15">
								        <div  class="good_img"><a><img src="<%=basePath%>uploadFiles/uploadImgs/${var.PATH}" title="${var.TITLE}"></a></div>
								    </div>
								    </c:forEach>
								</td>
							</tr>
						</table>
						</div>
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
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		
		</script>
</body>
</html>