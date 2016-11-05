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
						globalTree.setTree(rootNode);
						if(data.result.children){
							for(var i=0;i<data.result.children;i++){
								var node = new econny.work.node();
								node.setId(data.result.children.id);
								rootNode.addChild(node);
							}
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
			$.ajax(getTreeOption);
	};
	
	//display the tree
	function displayTree(node){
		if(node){
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
			}
			//display children	
			if(node.children){
				displayContent += "";
				for(var i=0;i<node.children.length;i++){
					displayContent += "<tr><td></td><td class='tree-node-item btn btn-primary' title='"+node.children[i].id+"'>"+formatTitle(node.children[i].title)+"</td></tr>";				
				};	
			}
			$("#displayTree").html(displayContent);
			
			$(".tree-node-item-root").click(function(){
				if(globalTreeRootSelected == $(this).attr("title")){
					globalTreeRootSelected = "";
					displayTree(globalTree.getTree());
					 $("#addNode").removeClass("btn-success");
					 $("#addNode").addClass("btn-primary");
				}else{
					globalTreeRootSelected = $(this).attr("title");
					displayTree(globalTree.getTree());
					 $("#addNode").removeClass("btn-primary");
					 $("#addNode").addClass("btn-success");
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
	
	//format title
	function formatTitle(title){
		if(title){
			return title;
		}else{
			return "title not defined";
		}
	};
	
});