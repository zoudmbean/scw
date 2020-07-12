// 分页函数
function generateTbody(){
	// 1. ajax请求服务器获取pageInfo对象
	var pageInfo = getPageInfo();
	// 2.填充表格
	fillTableBody(pageInfo);
}

// 给查询按钮绑定单击响应函数
function initBtn(){
	$("#searchBtn").bind("click",function(){
		// ①获取关键词数据赋值给对应的全局变量
		window.keyword = $("#keywordInput").val();
		
		// ②调用分页函数刷新页面
		generateTbody();
	});
}

// 生成表格
function fillTableBody(pageInfo){
	
	// 清除tbody中的旧的内容
	$("#rolePageBody").empty();
	
	// 这里清空是为了让没有搜索结果时不显示页码导航条
	$("#Pagination").empty();
	
	var errmsg = "<tr><td colspan='4' align='center'>抱歉！没有查询到您搜索的数据！</td></tr>";
	if(!pageInfo){
		$("#rolePageBody").append(errmsg);
		return;
	}
	if(pageInfo.list == null || pageInfo.list.length == 0){
		$("#rolePageBody").append(errmsg);
		return;
	}
	
	// 使用pageInfo的list属性填充tbody
	for(var i = 0 ; i < pageInfo.list.length ; i++){
		var role = pageInfo.list[i];
		var roleId = role.id;
		var roleName = role.name;
		var numberTd = "<td>"+(i+1)+"</td>";
		var checkboxTd = "<td><input id='"+roleId+"' class='itemBox' type='checkbox'></td>";
		var roleNameTd = "<td>"+roleName+"</td>";
		
		var checkBtn = "<button type='button' class='btn btn-success btn-xs'><i class=' glyphicon glyphicon-check'></i></button>";
		
		// 通过button标签的id属性（别的属性其实也可以）把roleId值传递到button按钮的单击响应函数中，在单击响应函数中使用this.id
		var pencilBtn = "<button id='"+roleId+"' type='button' class='btn btn-primary btn-xs pencilBtn'><i class=' glyphicon glyphicon-pencil'></i></button>";
		
		// 通过button标签的id属性（别的属性其实也可以）把roleId值传递到button按钮的单击响应函数中，在单击响应函数中使用this.id
		var removeBtn = "<button id='"+roleId+"' type='button' class='btn btn-danger btn-xs removeBtn'><i class=' glyphicon glyphicon-remove'></i></button>";
		
		var buttonTd = "<td>"+checkBtn+" "+pencilBtn+" "+removeBtn+"</td>";
		
		var tr = "<tr>"+numberTd+checkboxTd+roleNameTd+buttonTd+"</tr>";
		
		$("#rolePageBody").append(tr);
		
		// 生成分页导航条
		initPagination(pageInfo);
	}
}

function clearForm(formId){
	$(':input','#'+formId)

    .not(':button,:submit,:reset')   //将myform表单中input元素type为button、submit、reset、hidden排除

    .val('')  //将input元素的value设为空值

    .removeAttr('checked')

    .removeAttr('checked') // 如果任何radio/checkbox/select inputs有checked or selected 属性，将其移除
}

// 获取pageInfo对象
function getPageInfo(){
	// 调用$.ajax()函数发送请求并接受$.ajax()函数的返回值
	var ajaxResult = $.ajax({
		"url": "role/getPageInfo.json",
		"type":"get",
		"data": {
			"pageNum": window.pageNum,
			"pageSize": window.pageSize,
			"keywords": window.keyword
		},
		"async":false,		// 同步加载数据
		"dataType":"json"
	});
	// 判断当前响应状态码是否为200
	var statusCode = ajaxResult.status;
	// 如果当前响应状态码不是200，说明发生了错误或其他意外情况，显示提示消息，让当前函数停止执行
	if(statusCode != 200) {
		layer.msg("失败！响应状态码="+statusCode+" 说明信息="+ajaxResult.statusText);
		return null;
	}
	// 如果响应状态码是200，说明请求处理成功，获取pageInfo
	var resultEntity = ajaxResult.responseJSON;
	// 从resultEntity中获取result属性
	var result = resultEntity.result;
	
	// 判断result是否成功
	if(result == "FAILED") {
		layer.msg(resultEntity.message);
		return null;
	}
	
	// 确认result为成功后获取pageInfo
	var pageInfo = resultEntity.data;
	
	// 返回pageInfo
	return pageInfo;
}

// 初始化导航条
function initPagination(pageInfo){
	var total = pageInfo.total;
	// 创建分页
	$("#Pagination").pagination(total, {
		num_edge_entries: 3, //边缘页数
		num_display_entries: 5, //主体页数
		callback: pageselectCallback,
		items_per_page:pageInfo.pageSize, //每页显示1项
		current_page:pageInfo.pageNum - 1,
		prev_text:"上一页",
		next_text:"下一页"
	});
}

// 回调函数
function pageselectCallback(pageIndex, jq){
	// 修改window对象的pageNum属性
	window.pageNum = pageIndex + 1;
	
	// 调用分页函数
	generateTbody();
	
	// 取消页码超链接的默认行为
	return false;
}

// 显示删除模态框
/*
 * 		<h4></h4>
        <div id="content"></div>
 * */
function showRemoveModal(arr,flag){
	if(!arr){
		layer.msg("请至少选择一条数据删除！");
		return;
	}
	// 清除旧的数据
	$("#modalBody").html('');
	// 定义全局变量
	window.idArray = [];
	var title = "您确认删除角色：";
	var content = "";
	if(flag){
		title = "您确认删除以下角色：";
	}
	// 遍历
	arr.forEach(role => {
		content += role.name + ",";
		window.idArray.push(role.id);
	});
	var last = content.lastIndexOf(",");
	if(last > 0 ){
		content = content.substring(0,last);
	}
	var html = "<h4>"+title + content + "  吗？" + "</h4>";
	if(flag && arr.length > 1){
		html = "<h4>"+title+"</h4>";
		html += "<div style='text-align:center'>"+content.replace(/,/g,"<br/>")+"</div>";
		console.log("content = " + content.replace(/,/g,"<br/>"));
	}
	$("#modalBody").html(html);
	$("#removemodal").modal("show");
}
