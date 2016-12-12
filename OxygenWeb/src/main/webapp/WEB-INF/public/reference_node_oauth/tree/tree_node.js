$(function(){
	
	var globalTree = new econny.work.tree();
	var globalTreeRootSelected = "";
	var globalTreeItemSelected = "";
	var globalAttrHref = $("#goToDeatilNodePage").attr("href");
	
	getByNodeId();
	
	//create a tree
	function getByNodeId(){
		
		var getTreeOption = {
				type: 'POST',
				url: '/OxygenWeb/econnyTreeService/getByNodeId',
				data: {id:$("#nodeId").val()},
				dataType: 'json',
				success: function(data){
					if(data.result){
						var rootNode = new econny.work.node();
						rootNode.setId(data.result.id);
						rootNode.setTitle(data.result.title);
						rootNode.setDescription(data.result.description);
						rootNode.setChildren(data.result.children);
						globalTree.setTree(rootNode);
						if(data.result.children){
							for(var i=0;i<data.result.children;i++){
								var node = new econny.work.node();
								node.setId(data.result.children[i].id);
								node.setTitle(data.result.children[i].title);
								rootNode.addChild(node);
							}
						}
						displayDocTable(data.result.docs);
						displayPicTable(data.result.pics);
					}
					displayTree(globalTree.getTree());
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
				},
				timeout: function(data){
				}
			};
			//initialize the page
			$.ajax(getTreeOption);
	};
	
	function deleteFileById(nodeId, fileId, type){
		var fileDeleteSingleOption = {
				type : 'POST',
				url : '/OxygenWeb/econnyTreeService/deleteNodeFile',
				data : {
					nodeId : nodeId,
					fileId : fileId,
					type : type
				},
				success : function(data) {
					//refresh page
					getByNodeId();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				},
				timeout : function(data) {
				}
			};
			// initialize the page
			$.ajax(fileDeleteSingleOption);
	};
	
	function displayDocTable(param){
		if(param){
			var contentHtml = "";
			for(var i=0;i<param.length;i++){
				contentHtml += "<tr><td><a href='/OxygenWeb/commonService/fileDownloadSingle?id="+param[i].fileId+"'>doc</a></td><td><button title='"+param[i].fileId+"' class='btn btn-primary node-doc-delete-button'>delete</button></td></tr>";
			};
			
			$("#nodeDocsTable").html(contentHtml);
			$(".node-doc-delete-button").click(function(){
				$(this).attr('title');
				deleteFileById(globalTreeRootSelected,$(this).attr('title'),0);
			});
		};
	};
	
	function getPicRefUrl(fileId){
		var getResourceOwnerAllOption = {
				type : 'POST',
				url : '/OxygenWeb/commonService/fileDownloadSingle',
				data : {
					id : fileId
				},
				success : function(data) {
					if(data){
						$(".node-pics-img[title="+fileId+"]").attr("src","/OxygenWeb/" + data.result.refUrl);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				},
				timeout : function(data) {
				}
			};
			// initialize the page
			$.ajax(getResourceOwnerAllOption);
	};
	
	function displayPicTable(param){
		if(param){
			var contentHtml = "";
			for(var i=0;i<param.length;i++){
				contentHtml += "<tr><td><img title='"+param[i].fileId+"' class='node-pics-img' src='/OxygenWeb/'/></td><td><button title='"+param[i].fileId+"' class='btn btn-primary node-pic-delete-button'>delete</button></td></tr>";
			};
			
			$("#nodePicsTable").html(contentHtml);
			
			$(".node-pic-delete-button").click(function(){
				$(this).attr('title');
				deleteFileById(globalTreeRootSelected,$(this).attr('title'),1);
			});
			
			$(".node-pics-img").click(function(){
				$(this).attr('title');
				getPicRefUrl($(this).attr('title'));
			});
			
			$(".node-pics-img").click();
		};
	};
	
	//display the tree
	function displayTree(node){
		if(node){
			$("#nodeTitleInput").val(node.title);
			$("#nodeDescriptionInput").val(node.description);
			//display node
			var displayContent = "";
			if(node.id && globalTreeRootSelected){
				if(node.id == globalTreeRootSelected){
					displayContent += "<tr><td class='tree-node-item-root btn btn-primary fade-a-lot' title='"+node.id+"'>"+formatTitle(node.title)+"</td></tr>";
				}else{
					displayContent += "<tr><td class='tree-node-item-root btn btn-primary' title='"+node.id+"'>"+formatTitle(node.title)+"</td></tr>";
				}
			}else{
				displayContent += "<tr><td class='tree-node-item-root btn btn-primary' title='"+node.id+"'>"+formatTitle(node.title)+"</td></tr>";
			};
			//display children	
			if(node.children){
				displayContent += "";
				for(var i=0;i<node.children.length;i++){
					displayContent += "<tr><td></td><td class='tree-node-item btn btn-primary' title='"+node.children[i].id+"'>"+formatTitle(node.children[i].title)+"</td></tr>";				
				};	
			};
			$("#displayTree").html(displayContent);
			
			$(".tree-node-item-root").click(function(){
				if(globalTreeRootSelected == $(this).attr("title")){
					 $("#goToDeatilNodePage").removeClass("btn-success");
					 $("#goToDeatilNodePage").addClass("btn-primary");
					 
					globalTreeRootSelected = "";
					displayTree(globalTree.getTree());
					 $("#addNode").removeClass("btn-success");
					 $("#addNode").addClass("btn-primary");
					 
					 $("#updateNodeButton").removeClass("btn-success");
					 $("#updateNodeButton").addClass("btn-primary");
					 $("#updateNodeButton").attr("disabled",true);

					 $("#nodeDescriptionInput").attr("readonly",true);
					 $("#nodeTitleInput").attr("readonly",true);
					 
					 $("#saveNodeDocButton").attr("disabled",true);
					 $("#saveNodePicButton").attr("disabled",true);
				}else{
					$("#goToDeatilNodePage").attr("href",globalAttrHref+"?id="+$(this).attr("title"));
					$("#goToDeatilNodePage").removeClass("btn-primary");
					$("#goToDeatilNodePage").addClass("btn-success");
					
					globalTreeRootSelected = $(this).attr("title");
					displayTree(globalTree.getTree());
					 $("#addNode").removeClass("btn-primary");
					 $("#addNode").addClass("btn-success");
					 
					 $("#updateNodeButton").removeClass("btn-primary");
					 $("#updateNodeButton").addClass("btn-success");
					 $("#updateNodeButton").attr("disabled",false);
					 
					 $("#nodeDescriptionInput").attr("readonly",false);
					 $("#nodeTitleInput").attr("readonly",false);
					 
					 $("#saveNodeDocButton").attr("disabled",false);
					 $("#saveNodePicButton").attr("disabled",false);
				}
			});
			
			$(".tree-node-item").click(function(){
				if(globalTreeItemSelected){
					//if the global selected exist and equals the clicked one means double click
					if(globalTreeItemSelected==$(this).attr("title")){
						 $("#goToDeatilNodePage").attr("href",globalAttrHref+"?id="+$(this).attr("title"));
						 $("#goToDeatilNodePage").removeClass("btn-primary");
						 $("#goToDeatilNodePage").addClass("btn-success");
						 
						 $(this).removeClass("btn-primary");
						 $(this).addClass("btn-success");
					}else{						 
						 $("[title='"+globalTreeItemSelected+"']").removeClass("btn-success");
						 $("[title='"+globalTreeItemSelected+"']").addClass("btn-primary");
						 
						globalTreeItemSelected = $(this).attr("title");
					}
				}else{
					globalTreeItemSelected = $(this).attr("title");
				}
			});
		}
	};
	
	//add a node
	$("#addNode").click(function(){
		
		var addTreeNodeOption = {
				type: 'POST',
				url: '/OxygenWeb/econnyTreeService/createNode',
				data: {id:globalTreeRootSelected},
				dataType: 'json',
				success: function(data){
					if(data.result){
						var node = new econny.work.node();
						node.setId(data.result.id);
						var nodeToInsert = globalTree.findNode(globalTreeRootSelected, globalTree.getTree());
						if(nodeToInsert){
							nodeToInsert.addChild(node);
						}
					}
					
					displayTree(globalTree.getTree());
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
				},
				timeout: function(data){
				}
			};
			//initialize the page
			$.ajax(addTreeNodeOption);
	});
	
	//add a node
	$("#updateNodeButton").click(function(){
		
		var param = {id:globalTreeRootSelected,title:$("#nodeTitleInput").val(),description:$("#nodeDescriptionInput").val()};
		if(!param.title){
			alert("title is empty.");
			return false;
		};
		
		var updateTreeNodeOption = {
				type: 'POST',
				url: '/OxygenWeb/econnyTreeService/updateNode',
				data: param,
				dataType: 'json',
				success: function(data){
					if(data.result){
						 globalTreeRootSelected = "";
						 $("#addNode").removeClass("btn-success");
						 $("#addNode").addClass("btn-primary");
						 //update success
						 $("#updateNodeButton").removeClass("btn-success");
						 $("#updateNodeButton").addClass("btn-primary");
						 $("#updateNodeButton").attr("disabled",true);

						 $("#nodeDescriptionInput").attr("readonly",true);
						 $("#nodeTitleInput").attr("readonly",true);
						getByNodeId();
					};
					
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
				},
				timeout: function(data){
				}
			};
			//initialize the page
			$.ajax(updateTreeNodeOption);
	});
	

	
	//add a node doc
	$("#saveNodeDocButton").click(function(){
		$.ajaxFileUpload({
			url : '/OxygenWeb/commonService/fileUploadSingle', // 需要链接到服务器地址
			secureuri : false,
			fileElementId : 'fileNodeDoc', // 文件选择框的id属性
			dataType : 'json', // 服务器返回的格式，可以是json
			type: 'post',
			data : {
				secureLevel : 1
			},
			success : function(data, status) // 相当于java中try语句块的用法
			{
				if(data.result){
					
					var param = {id:globalTreeRootSelected,fileId:data.result};
					
					var saveTreeNodeDocOption = {
							type: 'POST',
							url: '/OxygenWeb/econnyTreeService/saveNodeDoc',
							data: param,
							dataType: 'json',
							success: function(data){
								//refresh page
								getByNodeId();
							},
							error: function(XMLHttpRequest, textStatus, errorThrown){
							},
							timeout: function(data){
							}
						};
						//initialize the page
						$.ajax(saveTreeNodeDocOption);
					
				};
			},
			error : function(data, status, e) // 相当于java中catch语句块的用法
			{
			}
		});
	});
	
	//add a node doc
	$("#saveNodePicButton").click(function(){
		
		$.ajaxFileUpload({
			url : '/OxygenWeb/commonService/fileUploadSingle', // 需要链接到服务器地址
			secureuri : false,
			fileElementId : 'fileNodePic', // 文件选择框的id属性
			dataType : 'json', // 服务器返回的格式，可以是json
			type: 'post',
			data : {
				secureLevel : 2
			},
			success : function(data, status) // 相当于java中try语句块的用法
			{
				if(data.result){
					
					var param = {id:globalTreeRootSelected,fileId:data.result};
					
					var saveTreeNodePicOption = {
							type: 'POST',
							url: '/OxygenWeb/econnyTreeService/saveNodePic',
							data: param,
							dataType: 'json',
							success: function(data){
								//refresh page
								getByNodeId();
							},
							error: function(XMLHttpRequest, textStatus, errorThrown){
							},
							timeout: function(data){
							}
						};
						//initialize the page
						$.ajax(saveTreeNodePicOption);
					
				};
			},
			error : function(data, status, e) // 相当于java中catch语句块的用法
			{
			}
		});
	});
	
	//format title
	function formatTitle(title){
		if(title){
			return title;
		}else{
			return "title not defined";
		}
	};
	
});