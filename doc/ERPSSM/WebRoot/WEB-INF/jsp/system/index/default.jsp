<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<!-- jsp文件头和头部 -->
<%@ include file="../index/top.jsp"%>
<!-- 百度echarts -->
<script src="plugins/echarts/echarts.min.js"></script>
<script type="text/javascript">
setTimeout("top.hangge()",500);
</script>
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
							<div  style="width: 800px;padding-top: 15px;">
								<table class="table table-striped table-bordered table-hover">
									<tr>
										<td style="width: 100px;">总进货金额</td>
										<td><font style="color: red"><b>${pd.injine }</b></font>&nbsp;元</td>
										<td style="width: 100px;">总销售金额</td>
										<td><font style="color: red"><b>${pd.outjine }</b></font>&nbsp;元</td>
										<td style="width: 100px;">总销售利润</td>
										<td><font style="color: red"><b>${pd.lirun}</b></font>&nbsp;元</td>
										<td style="width: 120px;">30天内销售金额</td>
										<td><font style="color: red"><b>${pd.outjine30 }</b></font>&nbsp;元</td>
									</tr>
								</table>
							</div>
							<div id="main" style="width: 650px;height:300px;"></div>
							
							<script type="text/javascript">
						        // 基于准备好的dom，初始化echarts实例
						        var myChart = echarts.init(document.getElementById('main'));
						
						        // 指定图表的配置项和数据
								var option = {
						            title: {
						                text: '客户统计'
						            },
						            tooltip: {},
						            xAxis: {
						                data: ["客户总数：${pd.userCount}","30天内新增客户数：${pd.newUuserCount}"]
						            },
						            yAxis: {},
						            series: [
						               {
						                name: '',
						                type: 'bar',
						                data: [${pd.userCount},${pd.newUuserCount}],
						                itemStyle: {
						                    normal: {
						                        color: function(params) {
						                            // build a color map as your need.
						                            var colorList = ['#6FB3E0','#87B87F'];
						                            return colorList[params.dataIndex];
						                        }
						                    }
						                }
						               }
						            ]
						        };	        

						        // 使用刚指定的配置项和数据显示图表。
						        myChart.setOption(option);
						    </script>
							
						</div>
						<!-- /.col -->
						
						<div class="span6" style="padding-top: 13px;">
									<div class="tabbable">
								            <ul class="nav nav-tabs" id="myTab">
								              <li class="active"><a data-toggle="tab" href="#home"><i class="green icon-home bigger-110"></i>折线图</a></li>
								              <li><a data-toggle="tab" href="#profile"><i class="green icon-cog bigger-110"></i>柱状图</a></li>
								            </ul>
								            <div class="tab-content">
											  <div id="home" class="tab-pane in active">
												<table style="width:865px;" border="0px;">
													<tr>
														<td>
															<jsp:include
															page="../../FusionChartsHTMLRenderer.jsp" flush="true">
															<jsp:param name="chartSWF" value="static/FusionCharts/Line.swf" />
															<jsp:param name="strURL" value="" />
															<jsp:param name="strXML" value="${str2}" />
															<jsp:param name="chartId" value="myNext" />
															<jsp:param name="chartWidth" value="860" />
															<jsp:param name="chartHeight" value="300" />
															<jsp:param name="debugMode" value="false" />
															</jsp:include>
														</td>
													</tr>
												</table>
											  </div>
											  <div id="profile" class="tab-pane">
												 <table style="width:865px;" border="0px;">
													<tr>
														<td>
															<jsp:include
															page="../../FusionChartsHTMLRenderer.jsp" flush="true">
															<jsp:param name="chartSWF" value="static/FusionCharts/Column3D.swf" />
															<jsp:param name="strURL" value="" />
															<jsp:param name="strXML" value="${str1}" />
															<jsp:param name="chartId" value="myNext" />
															<jsp:param name="chartWidth" value="860" />
															<jsp:param name="chartHeight" value="300" />
															<jsp:param name="debugMode" value="false" />
															</jsp:include>
														</td>
													</tr>
												</table>
											  </div>
								            </div>
									</div>
								 </div><!--/span-->		
						
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->


		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../index/foot.jsp"%>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		$(top.hangge());
	</script>
<script type="text/javascript" src="static/ace/js/jquery.js"></script>
</body>
</html>