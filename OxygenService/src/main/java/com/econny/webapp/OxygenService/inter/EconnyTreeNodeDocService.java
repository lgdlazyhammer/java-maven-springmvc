package com.econny.webapp.OxygenService.inter;

import java.util.List;

import com.econny.webapp.OxygenEntity.TreeNodeDocEntity;

public interface EconnyTreeNodeDocService {
	
	public void save(TreeNodeDocEntity treeNodeDocEntity);
	
	public void delete(TreeNodeDocEntity treeNodeDocEntity);
	
	public void update(TreeNodeDocEntity treeNodeDocEntity);
	
	public List<TreeNodeDocEntity> findList(TreeNodeDocEntity treeNodeDocEntity); 
	
}
