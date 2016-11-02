package com.econny.webapp.OxygenService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.econny.webapp.OxygenDao.mybatis.dao.EconnyTreeNodeDocMapper;
import com.econny.webapp.OxygenDao.mybatis.dao.EconnyTreeNodeMapper;
import com.econny.webapp.OxygenEntity.TreeNodeDocEntity;
import com.econny.webapp.OxygenEntity.TreeNodeEntity;
import com.econny.webapp.OxygenService.inter.EconnyTreeNodeDocService;
import com.econny.webapp.OxygenService.inter.EconnyTreeNodeService;

@Service
public class EconnyTreeNodeDocServiceImpl implements EconnyTreeNodeDocService {

	@Autowired
	EconnyTreeNodeDocMapper econnyTreeNodeDocMapper;

	public void save(TreeNodeDocEntity treeNodeDocEntity) {
		// TODO Auto-generated method stub
		econnyTreeNodeDocMapper.save(treeNodeDocEntity);
	}

	public void delete(TreeNodeDocEntity treeNodeDocEntity) {
		// TODO Auto-generated method stub
		econnyTreeNodeDocMapper.delete(treeNodeDocEntity);
	}

	public void update(TreeNodeDocEntity treeNodeDocEntity) {
		// TODO Auto-generated method stub
		econnyTreeNodeDocMapper.update(treeNodeDocEntity);
	}

	public List<TreeNodeDocEntity> findList(TreeNodeDocEntity treeNodeDocEntity) {
		// TODO Auto-generated method stub
		return econnyTreeNodeDocMapper.findList(treeNodeDocEntity);
	}

}
