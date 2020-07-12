// 初始化树形数据
function initTreeData(){
	$.ajax({
		url:"menu/getMenuTree.json",
		type: "GET",
		dataType:"json",
		success:function(res){
			if(res.result === "SUCCESS"){
				$.fn.zTree.init($("#tree"), setting, res.data);
			}
			if(res.result === "FAILD"){
				layer.msg(res.message);
			}
		},
		error:function(){
			layer.msg("加载菜单树形失败！");
		}
	});
	
	// 动态绑定事件
	init();
}

var node = {};
function init(){
	// 添加
	$("#tree").on('click','.btnAdd',function(e){
		// 打开模态框前的值设置
		// 1. 设置标题
		$("#menuSaveModal [class=modal-title]").text("添加");
		// 2. 设置按钮值
		$("#menuSaveModal div[class='modal-footer'] span").text("保存");
		
		// console.log(this.id);
		// 将当前点击的节点ID存入window对象中
		node.id = this.id;
		
		// 打开模态框menuSaveModal
		$('#menuSaveModal').modal('show')
		
		// 打开之前先清空数据
		$("#reset").click();
		$("#menuSaveModal form input[type='hidden']").val('');
		return false;
	});
	
	// 修改
	$("#tree").on('click','.btnEdit',function(e){
		// 打开模态框前的值设置
		// 1. 设置标题
		$("#menuSaveModal [class='modal-title']").text("编辑");
		// 2. 设置按钮值
		$("#menuSaveModal div[class='modal-footer'] span").text("更新");
		
		// 将当前点击的节点ID存入window对象中
		node.id = this.id;
		
		// 3. 回显数据
		var treeNode =  $.fn.zTree.getZTreeObj("tree").getNodeByParam("id", node.id, null);
		$("#menuSaveModal form input[name=id]").val(node.id);
		$("#menuSaveModal form input[name=name]").val(treeNode.name);
		$("#menuSaveModal form input[name=url]").val(treeNode.url);
		$("#menuSaveModal form input[name=icon]").val([treeNode.icon]);
		
		// 打开模态框menuSaveModal
		$('#menuSaveModal').modal('show')
		return false;
	});
	
	// 删除节点
	$("#tree").on('click','.btnRemove',function(e){
		node.id = e.currentTarget.id;
		var treeNode =  $.fn.zTree.getZTreeObj("tree").getNodeByParam("id", node.id, null);
		
		// 添加提示信息
		$("#removeNodeSpan").html(" 【 <span class='"+treeNode.icon+"'></span>&nbsp;<span style='color:red;'>"+treeNode.name+"</span> 】");
		
		// 打开模态框
		$("#menuConfirmModal").modal("show");
		return false;
	});
	
}

// 确定删除
function sure(){
	$.get("menu/delete/"+node.id+".json", function(data){
		  if(data.result === "SUCCESS"){
			  layer.msg("删除成功！");
			  $("#menuConfirmModal").modal("hide");
			  // 重新加载
			  initTreeData();
		  }
		  if(data.result === "FAILD"){
			  layer.msg("操作失败！");
		  }
		},"json");
}

// 保存
function save(){
	var data = $("#menuSaveModal form").serialize();
	var id = $("#menuSaveModal form input[type='hidden']").val();
	if(!id){
		data = data + "&pid="+node.id;
	}
	$.ajax({
		'type': "POST",
		'url':'menu/save.json',
		'data':data,
		'dataType':'json',
		'success':function(res){
			if(res.result === "SUCCESS"){
				// 关闭模态框
				$('#menuSaveModal').modal('hide');
				layer.msg("操作成功！");
				// 重新加载
				initTreeData();
			}
			if(res.result === "FAILD"){
				layer.msg("操作失败！");
			}
		},
		'error':function(res){
			layer.msg("操作出错！");
		}
	});
}

// Tree的控件定义   设置节点后面显示一个按钮
function myAddDiyDom(treeId, treeNode){
	// <span id="tree_8_ico" title="" treenode_ico="" class="button ico_docu" style="background:url(glyphicon glyphicon-check) 0 0 no-repeat;"></span>
	let id = treeNode.tId + "_ico";
	$("#"+id).removeClass().addClass(treeNode.icon);
}

// 添加组件
function addHoverDom(treeId, treeNode){
	
	let spanId = "btnGroup"+treeNode.tId;
	
	// 如果已存在，直接返回
	if($("#"+spanId).length > 0 ){
		return;
	}
	
	// 定义span组件
	var html = "";
	
	// 定义三个组件内容
	var addBtn = "<a id='"+treeNode.id+"' class='btnAdd btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='添加子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>"; 
	var removeBtn = "<a id='"+treeNode.id+"' class='btnRemove btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title=' 删 除 节 点 '>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>"; 
	var editBtn = "<a id='"+treeNode.id+"' class='btnEdit btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title=' 修 改 节 点 '>&nbsp;&nbsp;<i class='fafa-fw fa-edit rbg '></i></a>";
	
	// 根据层级关系组装之间
	// 判断当前节点的级别 
	if(treeNode.level == 0) { // 级别为 0 时是根节点，只能添加子节点 
		html = addBtn;
	}
	if(treeNode.level === 1){  // 第二层目录
		html = addBtn + editBtn;
		if(treeNode.children.length == 0){  // 如果存在子节点
			html = removeBtn + html;
		}
		
	}
	if(treeNode.level === 2){
		html = removeBtn + editBtn;
	}
	
	// 将组件添加到a标签之后 tree_4_a
	var aId = treeNode.tId + "_a";
	$("#"+aId).after("<span id = '"+spanId+"'>"+html+"</span>");
}

// 删除组件
function removeHoverDom(treeId, treeNode){
	// 拼接按钮组的 id 
	var btnGroupId = "btnGroup"+treeNode.tId;
	// 移除对应的元素  btnGrouptree_18
	$("#" + btnGroupId).remove();
}





