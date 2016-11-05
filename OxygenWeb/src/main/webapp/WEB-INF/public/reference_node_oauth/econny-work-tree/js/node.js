EconnyNamespace('econny.work.node');

econny.work.node = function(id, title, children){
	this.id = id;
	this.title = title;
	if(children){
		this.children = children;		
	}else{
		this.children = new Array();
	}
	
	return this;
};

econny.work.node.prototype.getId = function(){
	return this.title;
};

econny.work.node.prototype.setId  = function(id){
	this.id = id;
};

econny.work.node.prototype.getTitle = function(){
	return this.title;
};

econny.work.node.prototype.setTitle = function(title){
	this.title = title;
};

econny.work.node.prototype.addChild = function(node){
	this.children.push(node);
};

econny.work.node.prototype.getChild = function(index){
	return this.children[index];
};

econny.work.node.prototype.getChildren = function(){
	return this.children;
};

