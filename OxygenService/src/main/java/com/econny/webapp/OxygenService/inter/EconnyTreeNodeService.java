package com.econny.webapp.OxygenService.inter;

import java.util.List;

import com.econny.webapp.OxygenEntity.TreeNodeEntity;

public interface EconnyTreeNodeService {
	
	public void save(TreeNodeEntity treeNodeEntity);
	
	public void delete(TreeNodeEntity treeNodeEntity);
	
	public void update(TreeNodeEntity treeNodeEntity);
	
	public List<TreeNodeEntity> findList(TreeNodeEntity treeNodeEntity); 
	
}
