package com.econny.webapp.OxygenService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.econny.webapp.OxygenDao.mybatis.dao.EconnyTreeNodeMapper;
import com.econny.webapp.OxygenEntity.TreeNodeEntity;
import com.econny.webapp.OxygenService.inter.EconnyTreeNodeService;

@Service
@Transactional(readOnly=true)
public class EconnyTreeNodeServiceImpl implements EconnyTreeNodeService {

	@Autowired
	EconnyTreeNodeMapper econnyTreeNodeMapper;

	@Transactional(readOnly=false)
	public void save(TreeNodeEntity treeNodeEntity) {
		// TODO Auto-generated method stub
		econnyTreeNodeMapper.save(treeNodeEntity);
	}

	@Transactional(readOnly=false)
	public void delete(TreeNodeEntity treeNodeEntity) {
		// TODO Auto-generated method stub
		econnyTreeNodeMapper.delete(treeNodeEntity);
	}

	@Transactional(readOnly=false)
	public void update(TreeNodeEntity treeNodeEntity) {
		// TODO Auto-generated method stub
		econnyTreeNodeMapper.update(treeNodeEntity);
	}

	public List<TreeNodeEntity> findList(TreeNodeEntity treeNodeEntity) {
		// TODO Auto-generated method stub
		return econnyTreeNodeMapper.findList(treeNodeEntity);
	}

}
