package com.econny.webapp.OxygenService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.econny.webapp.OxygenDao.mybatis.dao.EconnyTreeNodeDocMapper;
import com.econny.webapp.OxygenDao.mybatis.dao.EconnyTreeNodeMapper;
import com.econny.webapp.OxygenEntity.TreeNodeDocEntity;
import com.econny.webapp.OxygenEntity.TreeNodeEntity;
import com.econny.webapp.OxygenService.inter.EconnyTreeNodeDocService;
import com.econny.webapp.OxygenService.inter.EconnyTreeNodeService;

@Service
@Transactional(readOnly=true)
public class EconnyTreeNodeDocServiceImpl implements EconnyTreeNodeDocService {

	@Autowired
	EconnyTreeNodeDocMapper econnyTreeNodeDocMapper;

	@Transactional(readOnly=false)
	public void save(TreeNodeDocEntity treeNodeDocEntity) {
		// TODO Auto-generated method stub
		econnyTreeNodeDocMapper.save(treeNodeDocEntity);
	}
	
	@Transactional(readOnly=false)
	public void delete(TreeNodeDocEntity treeNodeDocEntity) {
		// TODO Auto-generated method stub
		econnyTreeNodeDocMapper.delete(treeNodeDocEntity);
	}
	
	@Transactional(readOnly=false)
	public void update(TreeNodeDocEntity treeNodeDocEntity) {
		// TODO Auto-generated method stub
		econnyTreeNodeDocMapper.update(treeNodeDocEntity);
	}

	public List<TreeNodeDocEntity> findList(TreeNodeDocEntity treeNodeDocEntity) {
		// TODO Auto-generated method stub
		return econnyTreeNodeDocMapper.findList(treeNodeDocEntity);
	}

}
