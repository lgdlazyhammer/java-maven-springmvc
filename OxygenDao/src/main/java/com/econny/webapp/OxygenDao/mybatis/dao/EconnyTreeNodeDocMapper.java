package com.econny.webapp.OxygenDao.mybatis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.econny.webapp.OxygenEntity.TreeNodeDocEntity;

@Repository
public interface EconnyTreeNodeDocMapper {
	
	public void save(TreeNodeDocEntity treeNodeDocEntity);
	
	public void delete(TreeNodeDocEntity treeNodeDocEntity);
	
	public void update(TreeNodeDocEntity treeNodeDocEntity);
	
	public List<TreeNodeDocEntity> findList(TreeNodeDocEntity treeNodeDocEntity); 
	
}
