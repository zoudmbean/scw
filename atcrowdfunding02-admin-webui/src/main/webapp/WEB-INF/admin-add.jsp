<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh_CN">

<!-- 引入head标签体内容-->
<%@ include file="/WEB-INF/admin-head.jsp" %>

<body>
	<!-- 引入左侧导航 -->
	<%@ include file="/WEB-INF/admin-nav.jsp" %>

	<div class="container-fluid">
		<div class="row">
			<!-- 引入头部面板 -->
			<%@ include file="/WEB-INF/admin-sider.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="#">数据列表</a></li>
				  <li class="active">新增</li>
				</ol>
				<div class="panel panel-default">
	              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
				  <div class="panel-body">
					<form role="form" method="post" action="admin/saveAdmin.html">
					  <p style="color:red">${requestScope.exception.message }</p>
					  <div class="form-group">
						<label for="exampleInputPassword1">登陆账号</label>
						<input type="text" class="form-control" name = "loginAcct" id="loginAcct" placeholder="请输入登陆账号" value="${requestScope.admin.loginAcct }">
					  </div>
					  <div class="form-group">
						<label for="exampleInputPassword1">用户昵称</label>
						<input type="text" class="form-control" name="userName" id="userName" placeholder="请输入用户名称" value="${requestScope.admin.userName }">
					  </div>
					  <div class="form-group">
						<label for="exampleInputEmail1">邮箱地址</label>
						<input type="email" class="form-control" name = "email" id="email" placeholder="请输入邮箱地址" value="${requestScope.admin.email }">
						<p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
					  </div>
					  <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
					  <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
					</form>
				  </div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>