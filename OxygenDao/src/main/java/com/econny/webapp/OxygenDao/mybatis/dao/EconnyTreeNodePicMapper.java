package com.econny.webapp.OxygenDao.mybatis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.econny.webapp.OxygenEntity.TreeNodeDocEntity;
import com.econny.webapp.OxygenEntity.TreeNodePicEntity;

@Repository
public interface EconnyTreeNodePicMapper {
	
	public void save(TreeNodePicEntity treeNodePicEntity);
	
	public void delete(TreeNodePicEntity treeNodePicEntity);
	
	public void update(TreeNodePicEntity treeNodePicEntity);
	
	public List<TreeNodePicEntity> findList(TreeNodePicEntity treeNodePicEntity); 
	
}
