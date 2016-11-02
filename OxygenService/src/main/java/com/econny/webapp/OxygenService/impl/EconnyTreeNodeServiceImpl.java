package com.econny.webapp.OxygenService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.econny.webapp.OxygenDao.mybatis.dao.EconnyTreeNodeMapper;
import com.econny.webapp.OxygenEntity.TreeNodeEntity;
import com.econny.webapp.OxygenService.inter.EconnyTreeNodeService;

@Service
public class EconnyTreeNodeServiceImpl implements EconnyTreeNodeService {

	@Autowired
	EconnyTreeNodeMapper econnyTreeNodeMapper;

	public void save(TreeNodeEntity treeNodeEntity) {
		// TODO Auto-generated method stub
		econnyTreeNodeMapper.save(treeNodeEntity);
	}

	public void delete(TreeNodeEntity treeNodeEntity) {
		// TODO Auto-generated method stub
		econnyTreeNodeMapper.delete(treeNodeEntity);
	}

	public void update(TreeNodeEntity treeNodeEntity) {
		// TODO Auto-generated method stub
		econnyTreeNodeMapper.update(treeNodeEntity);
	}

	public List<TreeNodeEntity> findList(TreeNodeEntity treeNodeEntity) {
		// TODO Auto-generated method stub
		return econnyTreeNodeMapper.findList(treeNodeEntity);
	}

}
