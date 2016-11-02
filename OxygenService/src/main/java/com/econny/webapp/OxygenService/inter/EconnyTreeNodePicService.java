package com.econny.webapp.OxygenService.inter;

import java.util.List;

import com.econny.webapp.OxygenEntity.TreeNodePicEntity;

public interface EconnyTreeNodePicService {
	
	public void save(TreeNodePicEntity treeNodePicEntity);
	
	public void delete(TreeNodePicEntity treeNodePicEntity);
	
	public void update(TreeNodePicEntity treeNodePicEntity);
	
	public List<TreeNodePicEntity> findList(TreeNodePicEntity treeNodePicEntity); 
	
}
