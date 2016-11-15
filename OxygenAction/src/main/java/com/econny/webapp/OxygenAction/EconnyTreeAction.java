package com.econny.webapp.OxygenAction;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
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
import com.econny.webapp.OxygenEntity.TreeNodeDocEntity;
import com.econny.webapp.OxygenEntity.TreeNodeEntity;
import com.econny.webapp.OxygenEntity.TreeNodePicEntity;
import com.econny.webapp.OxygenEntity.UploadFileEntity;
import com.econny.webapp.OxygenEnum.DelFlag;
import com.econny.webapp.OxygenEnum.NodeFileType;
import com.econny.webapp.OxygenEnum.TreeNodeType;
import com.econny.webapp.OxygenService.impl.EconnyTreeNodeDocServiceImpl;
import com.econny.webapp.OxygenService.impl.EconnyTreeNodePicServiceImpl;
import com.econny.webapp.OxygenService.impl.EconnyTreeNodeServiceImpl;
import com.econny.webapp.OxygenService.impl.UploadFileServiceImpl;

@Controller
@RequestMapping("/econnyTreeService")
public class EconnyTreeAction {

	@Autowired
	EconnyTreeNodeServiceImpl econnyTreeNodeServiceImpl;
	
	@Autowired
	EconnyTreeNodeDocServiceImpl econnyTreeNodeDocServiceImpl;
	
	@Autowired
	EconnyTreeNodePicServiceImpl econnyTreeNodePicServiceImpl;
	
	@Autowired
	UploadFileServiceImpl uploadFileServiceImpl;

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
	@RequestMapping(value = "/updateNode", method = RequestMethod.POST)
	@ResponseBody
	public Object updateNode(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String id,@RequestParam(required = true) String title,@RequestParam(required = false) String description)
			throws IOException {
		TreeNodeEntity treeNodeEntityRqy = new TreeNodeEntity();
		treeNodeEntityRqy.setId(id);
		List<TreeNodeEntity> listQry = econnyTreeNodeServiceImpl.findList(treeNodeEntityRqy);
		if(listQry.size()>0){
			TreeNodeEntity treeNodeEntity = listQry.get(0);
			treeNodeEntity.setTitle(title);
			treeNodeEntity.setDescription(description);
			econnyTreeNodeServiceImpl.update(treeNodeEntity);
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
			
			TreeNodePicEntity treeNodePicEntity = new TreeNodePicEntity();
			treeNodePicEntity.setNodeId(id);
			List<TreeNodePicEntity> listPic = econnyTreeNodePicServiceImpl.findList(treeNodePicEntity);
			
			TreeNodeDocEntity treeNodeDocEntity = new TreeNodeDocEntity();
			treeNodeDocEntity.setNodeId(id);
			List<TreeNodeDocEntity> listDoc = econnyTreeNodeDocServiceImpl.findList(treeNodeDocEntity);
			
			treeNodeEntityCurr.setPics(listPic);
			treeNodeEntityCurr.setDocs(listDoc);
		
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

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/saveNodeDoc", method = RequestMethod.POST)
	@ResponseBody
	public Object saveNodeDoc(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String id, @RequestParam(required = true) String fileId)
			throws IOException {
		TreeNodeDocEntity treeNodeDocEntity = new TreeNodeDocEntity();
		treeNodeDocEntity.setId(UUID.randomUUID().toString());
		treeNodeDocEntity.setNodeId(id);
		treeNodeDocEntity.setFileId(fileId);
		treeNodeDocEntity.setDelFlag("0");
		econnyTreeNodeDocServiceImpl.save(treeNodeDocEntity);
		
		return new ApiResultEntity(false, null, 200, "");
	}

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/saveNodePic", method = RequestMethod.POST)
	@ResponseBody
	public Object saveNodePic(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String id, @RequestParam(required = true) String fileId)
			throws IOException {
		TreeNodePicEntity treeNodePicEntity = new TreeNodePicEntity();
		treeNodePicEntity.setId(UUID.randomUUID().toString());
		treeNodePicEntity.setNodeId(id);
		treeNodePicEntity.setFileId(fileId);
		treeNodePicEntity.setDelFlag("0");
		econnyTreeNodePicServiceImpl.save(treeNodePicEntity);
		
		return new ApiResultEntity(false, null, 200, "");
	}

	@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST })
	@RequestMapping(value = "/deleteNodeFile", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteNodeFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String nodeId, @RequestParam(required = true) String fileId, @RequestParam(required = true) Integer type)
			throws IOException {
		// 获取文件暂存和保存路径
		Properties prop = new Properties();
		InputStream in = request.getSession().getServletContext()
				.getResourceAsStream("/WEB-INF/fileService.properties");
		prop.load(in);
		in.close();
		
		String realPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/" + prop.getProperty("filePathSave"));
		
		if (!realPath.endsWith(java.io.File.separator)) {
			realPath += java.io.File.separator;
	        }

		String realPathStatic = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/" + prop.getProperty("filePathStatic"));
		
		if (!realPathStatic.endsWith(java.io.File.separator)) {
			realPathStatic += java.io.File.separator;
		        }
				
		if(type.equals(NodeFileType.TypeOne.getCode())){
			TreeNodeDocEntity treeNodeDocEntity = new TreeNodeDocEntity();
			treeNodeDocEntity.setNodeId(nodeId);
			treeNodeDocEntity.setFileId(fileId);
			econnyTreeNodeDocServiceImpl.delete(treeNodeDocEntity);
			//delete file
			UploadFileEntity uploadFileEntity = new UploadFileEntity();
			uploadFileEntity.setId(fileId);
			uploadFileServiceImpl.delete(uploadFileEntity, realPath, realPathStatic);
			
		}else if(type.equals(NodeFileType.TypeTwo.getCode())){
			TreeNodePicEntity treeNodePicEntity = new TreeNodePicEntity();
			treeNodePicEntity.setNodeId(nodeId);
			treeNodePicEntity.setFileId(fileId);
			econnyTreeNodePicServiceImpl.delete(treeNodePicEntity);
			//delete file
			UploadFileEntity uploadFileEntity = new UploadFileEntity();
			uploadFileEntity.setId(fileId);
			uploadFileServiceImpl.delete(uploadFileEntity, realPath, realPathStatic);
		}
		return new ApiResultEntity(false, null, 200, "");
	}

}
