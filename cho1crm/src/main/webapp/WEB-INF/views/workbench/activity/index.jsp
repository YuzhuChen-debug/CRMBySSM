<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 	request.getServerPort() + request.getContextPath() + "/";
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
	<link rel="stylesheet" type="text/css" href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css">
	<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
	<link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
	<script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
	<script type="text/javascript" src="jquery/bs_pagination/en.js"></script>

	<script type="text/javascript">

	$(function(){
		//点击创建按钮,发送ajax请求,获取uList
		$("#createBtn").click(function () {
			$.ajax({
				url:"workbench/activity/getuList",
				dataType:"json",
				type:"get",
				success:function (data) {
					//在这里处理返回的数据,
					/*
					* 	data:{success:true,uList:[{u1},{u2},{u3}]}
					* or data:{success:false,msg:msg}
					* */
					if(data.success){
						//清除窗口中的所有数据
						$("#formBody")[0].reset();
						//把用户列表放到下拉框中
						var html = "<option></option>";
						$.each(data.uList,function (i,n) {
							html+="<option value='"+n.id+"'>"+n.name+"</option>";
						})
						$("#create-owner").html(html);
						$("#create-owner").val("${user.id}");

						//打开模态窗口
						$("#createActivityModal").modal("show");
					}else{
						alert(data.msg);
					}
				}
			})


		});

		//点击创建市场活动模态窗口中的提交按钮,发送ajax请求,添加市场活动信息
		$(".time").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
			autoclose: true,
			todayBtn: true,
			pickerPosition: "bottom-left"
		});
		$("#saveBtn").click(function () {

			var owner = $.trim($("#create-owner").val());
			var name = $.trim($("#create-name").val());
			var startDate = $.trim($("#create-startDate").val());
			var endDate = $.trim($("#create-endDate").val());
			var cost = $.trim($("#create-cost").val());
			var description = $.trim($("#create-description").val());
			if(owner==""||owner==null){
				alert("所有者不能为空");
				$("#create-owner").focus();
				return false;
			}
			if(name==""||name==null){
				alert("名称不能为空");
				$("#create-name").focus();
				 return false;
			}
			$.ajax({
				url: "workbench/activity/addActivity",
				dataType: "json",
				data:{
					owner:owner,
					name:name,
					startDate:startDate,
					endDate:endDate,
					cost:cost,
					description:description
				},
				type: "post",
				success: function (data) {
					//在这里处理返回的数据
					/*
					* 	data:{success:true}
					* or data:{success:false,msg:msg}
					* */
					if (data.success) {
						//刷新列表
						pageList(1 ,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
						//关闭模态窗口
						$("#createActivityModal").modal("hide");
					} else {

					}
				}
			})
		})

		pageList(1 ,2);

		$("#searchBtn").click(function () {
			//点击查询按钮，把查询框内的数值赋给隐藏框
			$("#hidden-name").val($.trim($("#search-name").val()));
			$("#hidden-owner").val($.trim($("#search-owner").val()));
			$("#hidden-startDate").val($.trim($("#search-startDate").val()));
			$("#hidden-endDate").val($.trim($("#search-endDate").val()));
			//alert(1);
			pageList(1 ,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
		})


        //点击修改按钮,打开修改模态窗口
        $("#editBtn").click(function () {
        	var $xz=$("input[name=xz]:checked");
        	if($xz.length==0){
        		alert("请选择需要修改的对象");
			}else if($xz.length>1){
        		alert("同时只能修改一条记录");
			}else{
        		var id =$("input[name]").val();
				//发送ajax请求,后台获取对应的参数
				$.ajax({
					url: "workbench/activity/getUserListAndActivityById.do",
					data:{
						id:id
					},
					dataType: "json",
					type: "get",
					success: function (data) {
						//alert(data.map.uList);
						//在这里处理返回的数据,
						/*
						* 	data:{success:true,map:{a:a,uList:[{u1},{u2},{u3}]}}
						* or data:{success:false,msg:msg}
						* */
						if (data.success) {
							//把获得的数据铺到修改模态窗口
							var html="<option></option>";
							$.each(data.map.uList,function (i,n) {
								html+="<option value='"+n.id+"'>"+n.name+"</option>";
							})
							$("#edit-owner").html(html);
							//alert(data.map.a.owner);
							$("#edit-owner").val(data.map.a.owner);
							$("#edit-id").val(data.map.a.id);
							$("#edit-name").val(data.map.a.name);
							$("#edit-startDate").val(data.map.a.startDate);
							$("#edit-endDate").val(data.map.a.endDate);
							$("#edit-cost").val(data.map.a.cost);
							$("#edit-description").val(data.map.a.description);
							//打开模态窗口
							$("#editActivityModal").modal("show");
						} else {
								alert(data.msg);
						}
					}
				})

			}

        })

		//完成全选和取消全选功能
		$("#qx").click(function () {
			$("input[name=xz]").prop("checked",this.checked)
		})
		$("#activityBody").on("click",$("input[name=xz]"),function () {
			$("#qx").prop("checked",$("input[name=xz]").length==$("input[name=xz]:checked").length)
		})


		
		
	});
    //以下几处,需要调用这刷新市场活动列表函数
    //1加载页面时候
    //2添加操作完成以后
    //3修改操作完成以后
    //4删除操作完成以后
    //5点击查询按钮以后
    //6分页插件操作以后
	function pageList(PageNo,PageSize) {
		//把隐藏域的数值赋给查询框
		$("#search-name").val($.trim($("#hidden-name").val()));
		$("#search-owner").val($.trim($("#hidden-owner").val()));
		$("#search-startDate").val($.trim($("#hidden-startDate").val()));
		$("#search-endDate").val($.trim($("#hidden-endDate").val()));

        var name = $.trim($("#search-name").val());
        var owner = $.trim($("#search-owner").val());
        var startDate = $.trim($("#search-startDate").val());
        var endDate = $.trim($("#search-endDate").val());
        //发送ajax请求,获取市场活动列表
        $.ajax({
            url: "workbench/activity/getActivityListByFuzzySearch",
            data:{
                pageNoStr:PageNo,
                pageSizeStr:PageSize,
                name:name,
                owner:owner,
                startDate:startDate,
                endDate:endDate
            },
            dataType: "json",
            type: "post",
            success: function (data) {
                //在这里处理返回的数据,
                /*
                *   data:{success:true,LATVO:{dataList:[{a1},{a2},{a3}],total:total}}
                *   or data:{success:false,msg:msg}
                * */

                if (data.success) {
                    var html = "";
                    $.each(data.LATVO.dataList,function (i,n) {
                        html+= '<tr class="active">';
                        html+= '    <td><input type="checkbox" name="xz" value="'+n.id+'" /></td>';
                        html+= '    <td><a style="text-decoration: none; cursor: pointer;"  onclick="window.location.href=\'workbench/activity/detail?id="'+n.id+'"\';">'+n.name+'</a></td>';
                        html+= '    <td>'+n.owner+'</td>';
                        html+= '    <td>'+n.startDate+'</td>';
                        html+= '    <td>'+n.endDate+'</td>';
                        html+= '</tr>';
                    })
					$("#activityBody").html(html);

					var totalPages = data.LATVO.total%PageSize==0 ? data.LATVO.total/PageSize :parseInt(data.LATVO.total/PageSize)+1;
					$("#activityPage").bs_pagination({
						currentPage: PageNo, // 页码
						rowsPerPage: PageSize, // 每页显示的记录条数
						maxRowsPerPage: 20, // 每页最多显示的记录条数
						totalPages: totalPages, // 总页数
						totalRows: data.LATVO.total, // 总记录条数

						visiblePageLinks: 3, // 显示几个卡片

						showGoToPage: true,
						showRowsPerPage: true,
						showRowsInfo: true,
						showRowsDefaultInfo: true,

						onChangePage : function(event, data){
							pageList(data.currentPage , data.rowsPerPage);
						}
					});
				} else {
                    alert(data.msg);
                }
            }


        })
    }
	
</script>
</head>
<body>

	<input type="hidden" id="hidden-owner"/>
	<input type="hidden" id="hidden-name"/>
	<input type="hidden" id="hidden-startDate"/>
	<input type="hidden" id="hidden-endDate"/>

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form" id="formBody">
					
						<div class="form-group">
							<label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-owner">
								  <%--<option>zhangsan</option>
								  <option>lisi</option>
								  <option>wangwu</option>--%>
								</select>
							</div>
                            <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-name">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" readonly id="create-startDate">
							</div>
							<label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" readonly  id="create-endDate">
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"  id="saveBtn">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
						<input type="hidden" id="edit-id"/>
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-owner">
								  <%--<option>zhangsan</option>
								  <option>lisi</option>
								  <option>wangwu</option>--%>
								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-name" >
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time"  readonly id="edit-startDate" >
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time " readonly id="edit-endDate" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="updateBtn">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="search-name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="search-owner">
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control time" readonly type="text" id="search-startDate" />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control time" type="text" readonly id="search-endDate">
				    </div>
				  </div>
				  
				  <button type="button" class="btn btn-default" id="searchBtn">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="createBtn"><span class="glyphicon glyphicon-plus" ></span> 创建</button>
				  <button type="button" class="btn btn-default" id="editBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox"  id="qx" /></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="activityBody">
						<%--<tr class="active">
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">发传单</a></td>
                            <td>zhangsan</td>
							<td>2020-10-10</td>
							<td>2020-10-20</td>
						</tr>
                        <tr class="active">
                            <td><input type="checkbox" /></td>
                            <td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">发传单</a></td>
                            <td>zhangsan</td>
                            <td>2020-10-10</td>
                            <td>2020-10-20</td>
                        </tr>--%>
					</tbody>
				</table>
			</div >
			
			<div id="activityPage">
				<%--<div>
					<button type="button" class="btn btn-default" style="cursor: default;">共<b>50</b>条记录</button>
				</div>
				<div class="btn-group" style="position: relative;top: -34px; left: 110px;">
					<button type="button" class="btn btn-default" style="cursor: default;">显示</button>
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
							10
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">20</a></li>
							<li><a href="#">30</a></li>
						</ul>
					</div>
					<button type="button" class="btn btn-default" style="cursor: default;">条/页</button>
				</div>
				<div style="position: relative;top: -88px; left: 285px;">
					<nav>
						<ul class="pagination">
							<li class="disabled"><a href="#">首页</a></li>
							<li class="disabled"><a href="#">上一页</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">下一页</a></li>
							<li class="disabled"><a href="#">末页</a></li>
						</ul>
					</nav>
				</div>
			</div>--%>
			
		</div>
		
	</div>
</body>
</html>