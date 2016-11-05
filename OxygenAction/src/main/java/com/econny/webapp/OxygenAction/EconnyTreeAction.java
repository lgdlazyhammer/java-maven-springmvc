package com.econny.webapp.OxygenAction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.econny.webapp.OxygenEntity.ApiResultEntity;
import com.econny.webapp.OxygenEntity.TreeNodeEntity;
import com.econny.webapp.OxygenEnum.DelFlag;
import com.econny.webapp.OxygenEnum.TreeNodeType;
import com.econny.webapp.OxygenService.impl.EconnyTreeNodeServiceImpl;

@Controller
@RequestMapping("/econnyTreeService")
public class EconnyTreeAction {

	@Autowired
	EconnyTreeNodeServiceImpl econnyTreeNodeServiceImpl;

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/createTree", method = RequestMethod.POST)
	@ResponseBody
	public Object createTree(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String userId)
			throws IOException {
		TreeNodeEntity treeNodeEntity = new TreeNodeEntity();
		treeNodeEntity.setId(UUID.randomUUID().toString());
		treeNodeEntity.setType(TreeNodeType.LevelOne.getCode());
		treeNodeEntity.setRank(0);
		treeNodeEntity.setUserId(userId);
		treeNodeEntity.setParent("root");
		treeNodeEntity.setDelFlag(DelFlag.NORMAL.getStatus());
		econnyTreeNodeServiceImpl.save(treeNodeEntity);
		return new ApiResultEntity(true, treeNodeEntity, 200, "");
	}

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/createNode", method = RequestMethod.POST)
	@ResponseBody
	public Object createNode(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String id)
			throws IOException {
		TreeNodeEntity treeNodeEntityRqy = new TreeNodeEntity();
		treeNodeEntityRqy.setId(id);
		List<TreeNodeEntity> listQry = econnyTreeNodeServiceImpl.findList(treeNodeEntityRqy);
		if(listQry.size()>0){
			TreeNodeEntity treeNodeEntity = new TreeNodeEntity();
			treeNodeEntity.setId(UUID.randomUUID().toString());
			treeNodeEntity.setType(TreeNodeType.LevelTwo.getCode());
			treeNodeEntity.setRank(listQry.get(0).getRank()+1);
			treeNodeEntity.setParent(id);
			treeNodeEntity.setDelFlag(DelFlag.NORMAL.getStatus());
			econnyTreeNodeServiceImpl.save(treeNodeEntity);
			return new ApiResultEntity(true, treeNodeEntity, 200, "");
		}
		return new ApiResultEntity(false, null, 200, "");
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/getByNodeId", method = RequestMethod.POST)
	@ResponseBody
	public Object getByNodeId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String id)
			throws IOException {
		TreeNodeEntity treeNodeEntity = new TreeNodeEntity();
		treeNodeEntity.setId(id);
		List<TreeNodeEntity> list = econnyTreeNodeServiceImpl.findList(treeNodeEntity);
		if(list.size()>0){
			TreeNodeEntity treeNodeEntityCurr = list.get(0);
			TreeNodeEntity treeNodeEntityQry = new TreeNodeEntity();
			treeNodeEntityQry.setParent(treeNodeEntityCurr.getId());
			List<TreeNodeEntity> listChildren = econnyTreeNodeServiceImpl.findList(treeNodeEntityQry);
			treeNodeEntityCurr.setChildren(listChildren);
			return new ApiResultEntity(true, treeNodeEntityCurr, 200, "");
		}
		return new ApiResultEntity(false, null, 200, "");
	}
	
	//get all trees for the user
	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/getUserTrees", method = RequestMethod.POST)
	@ResponseBody
	public Object getUserTrees(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		TreeNodeEntity treeNodeEntity = new TreeNodeEntity();
		treeNodeEntity.setType(TreeNodeType.LevelOne.getCode());
		treeNodeEntity.setUserId("111");
		List<TreeNodeEntity> list = econnyTreeNodeServiceImpl.findList(treeNodeEntity);
		List<TreeNodeEntity> listRes = new ArrayList<TreeNodeEntity>();
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				TreeNodeEntity tree = getTreeNode(list.get(i));
				listRes.add(tree);
			}
			return new ApiResultEntity(true, listRes, 200, "");
		}
		return new ApiResultEntity(false, null, 200, "");
	}
	
	public TreeNodeEntity getTreeNode(TreeNodeEntity treeNodeEntity){
		
		TreeNodeEntity treeNodeEntityRes = treeNodeEntity;
		
		TreeNodeEntity treeNodeEntityQry = new TreeNodeEntity();
		treeNodeEntityQry.setParent(treeNodeEntity.getId());
		
		List<TreeNodeEntity> list = econnyTreeNodeServiceImpl.findList(treeNodeEntityQry);
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				treeNodeEntityRes.getChildren().add(getTreeNode(list.get(i)));
			}
			return treeNodeEntityRes;
		}else{
			return treeNodeEntityRes;
		}
	}

}
