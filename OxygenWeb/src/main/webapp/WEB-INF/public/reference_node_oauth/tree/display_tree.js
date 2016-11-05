$(function(){
	
	var globalTree = new econny.work.tree();
	var globalTreeRootSelected = "";
	var globalTreeItemSelected = "";
	var globalAttrHref = $("#goToDeatilNodePage").attr("href");
	var treeNodeArr = new Array();
	var globalLineCount = 0;
	var globalRowCount = 0;
	
	getAllTrees();
	
	//create a tree
	function getAllTrees(){
		
		var getTreeOption = {
				type: 'POST',
				url: '/OxygenWeb/econnyTreeService/getUserTrees',
				data: {},
				dataType: 'json',
				success: function(data){
					if(data.result){
						saveTreeToArr(data.result[0]);
					}
					globalLineCount = calculateLine(treeNodeArr);
					globalRowCount = calculateRow(treeNodeArr);
					console.log("line: "+globalLineCount+" row: "+globalRowCount);
					console.log(treeNodeArr);
					displayAll();
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
				},
				timeout: function(data){
				}
			};
			//initialize the page
			$.ajax(getTreeOption);
	};
	
	function saveTreeToArr(node){
		if(node.children.length > 0){
			//save the current node info
			var nodeTemp = new econny.work.node();
			nodeTemp.setId(node.id);
			nodeTemp.setRank(node.rank);
			nodeTemp.setTitle(node.title);
			treeNodeArr.push(nodeTemp);
			//loop the children
			for(var i=0;i<node.children.length;i++){
				saveTreeToArr(node.children[i]);
			}
		}else{
			//save the node info without children
			var nodeTemp = new econny.work.node();
			nodeTemp.setId(node.id);
			nodeTemp.setRank(node.rank);
			nodeTemp.setTitle(node.title);
			treeNodeArr.push(nodeTemp);
		}
	};
	
	function displayAll(){
		var contentHtml = "";
		for(var i=0;i<globalRowCount;i++){
			contentHtml += "<tr><td>"+(parseInt(i)+1)+" level</td>";
			for(var j=0;j<treeNodeArr.length;j++){
				if(treeNodeArr[j].rank == i){
					contentHtml += "<td class='tree-node-item' title='"+treeNodeArr[j].id+"'>"+formatTitle(treeNodeArr[j].title)+"</td>";
				}
			}
			contentHtml += "</tr>";
		}
		$("#displayTree").html(contentHtml);
		
		$(".tree-node-item").click(function(){
			if(globalTreeItemSelected){
				//if the global selected exist and equals the clicked one means double click
				if(globalTreeItemSelected==$(this).attr("title")){
					 $("#goToDeatilNodePage").attr("href",globalAttrHref+"?id="+$(this).attr("title"));
					 $("#goToDeatilNodePage").removeClass("btn-primary");
					 $("#goToDeatilNodePage").addClass("btn-success");
					 
					 $(this).addClass("btn-success");
				}else{						 
					 $("[title='"+globalTreeItemSelected+"']").removeClass("btn-success");
					 
					globalTreeItemSelected = $(this).attr("title");
				}
			}else{
				globalTreeItemSelected = $(this).attr("title");
			}
		});
	};
	
	function calculateLine(treeNodeArr){
		var lineCountArr = new Array();
		var lineCount = 1;
		for(var i=0;i<calculateRow(treeNodeArr);i++){
			lineCountArr[i] = 0;
		}
		if(treeNodeArr){
			for(var ii=0;ii<treeNodeArr.length;ii++){
				lineCountArr[treeNodeArr[ii].rank]++;
			}
		}
		for(var iii=0;iii<calculateRow(treeNodeArr);iii++){
			if(lineCount < lineCountArr[iii]){
				lineCount = lineCountArr[iii];
			}
		}
		return lineCount;
	};
	
	function calculateRow(treeNodeArr){
		var rowCount = 0;
		if(treeNodeArr){
			for(var i=0;i<treeNodeArr.length;i++){
				if(treeNodeArr[i].rank > rowCount){
					rowCount = treeNodeArr[i].rank;
				}
			}
			return rowCount + 1;
		}
	};
	
	function formatTitle(title){
		if(title){
			return title;
		}else{
			return "title not defined";
		}
	};
	
});