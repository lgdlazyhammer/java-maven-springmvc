package com.econny.webapp.OxygenDao.mybatis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.econny.webapp.OxygenEntity.TreeNodeEntity;

@Repository
public interface EconnyTreeNodeMapper {
	
	public void save(TreeNodeEntity treeNodeEntity);
	
	public void delete(TreeNodeEntity treeNodeEntity);
	
	public void update(TreeNodeEntity treeNodeEntity);
	
	public List<TreeNodeEntity> findList(TreeNodeEntity treeNodeEntity); 
	
}
