EconnyNamespace('econny.work.tree');

econny.work.tree = function(root, title){
	this.root = root;
	this.title = title;
	
};

econny.work.tree.prototype.getTree = function(){
	return this.root;
};

econny.work.tree.prototype.setTree = function(root){
	this.root = root;
};

econny.work.tree.prototype.getTitle = function(){
	return this.title;
};

econny.work.tree.prototype.setTitle = function(title){
	this.title = title;
};

econny.work.tree.prototype.findNode = function(id, currNode){
	
	if(currNode.id == id){
		return currNode;
	}else if(currNode.children){
		for(var i=0;i<currNode.children.length;i++){
			if(currNode.children[i].id == id){
				return currNode.getChild(i);
			}else{
				this.findNode(id, currNode.getChild(i));
			}
		}
	}else{
		return;
	}
};

econny.work.tree.prototype.deleteNode = function(id, currNode){
	
	if(currNode.children){	
		for(var i=0;i<currNode.children.length;i++){
			if(currNode.children[i].id == id){
				currNode.children.splice(i,1);
			}else{
				this.deleteNode(id, currNode.children[i]);
			}
		}
	}
};
