package com.econny.webapp.OxygenService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.econny.webapp.OxygenDao.mybatis.dao.EconnyTreeNodeDocMapper;
import com.econny.webapp.OxygenDao.mybatis.dao.EconnyTreeNodeMapper;
import com.econny.webapp.OxygenDao.mybatis.dao.EconnyTreeNodePicMapper;
import com.econny.webapp.OxygenEntity.TreeNodeDocEntity;
import com.econny.webapp.OxygenEntity.TreeNodeEntity;
import com.econny.webapp.OxygenEntity.TreeNodePicEntity;
import com.econny.webapp.OxygenService.inter.EconnyTreeNodeDocService;
import com.econny.webapp.OxygenService.inter.EconnyTreeNodePicService;
import com.econny.webapp.OxygenService.inter.EconnyTreeNodeService;

@Service
public class EconnyTreeNodePicServiceImpl implements EconnyTreeNodePicService {

	@Autowired
	EconnyTreeNodePicMapper econnyTreeNodePicMapper;

	public void save(TreeNodePicEntity treeNodePicEntity) {
		// TODO Auto-generated method stub
		econnyTreeNodePicMapper.save(treeNodePicEntity);
	}

	public void delete(TreeNodePicEntity treeNodePicEntity) {
		// TODO Auto-generated method stub
		econnyTreeNodePicMapper.delete(treeNodePicEntity);
	}

	public void update(TreeNodePicEntity treeNodePicEntity) {
		// TODO Auto-generated method stub
		econnyTreeNodePicMapper.update(treeNodePicEntity);
	}

	public List<TreeNodePicEntity> findList(TreeNodePicEntity treeNodePicEntity) {
		// TODO Auto-generated method stub
		return econnyTreeNodePicMapper.findList(treeNodePicEntity);
	}


}
