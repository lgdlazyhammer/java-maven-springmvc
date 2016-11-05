package com.econny.webapp.OxygenEntity;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeEntity {

	private String id;// primary key
	private String title;// title
	private Integer type;// type 0-root 1-leaf
	private Integer rank;// the level of the node located in
	private String parent;//parent
	private String userId;//user id
	private String description;//description
	private String delFlag;//delete flag
	private List<TreeNodeEntity> children;// children
	private List<TreeNodeDocEntity> docs;//document ids
	private List<TreeNodePicEntity> pics;//picture ids
	
	public TreeNodeEntity(){
		children = new ArrayList<TreeNodeEntity>();
		docs = new ArrayList<TreeNodeDocEntity>();
		pics = new ArrayList<TreeNodePicEntity>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public List<TreeNodeEntity> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNodeEntity> children) {
		this.children = children;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public List<TreeNodeDocEntity> getDocs() {
		return docs;
	}

	public void setDocs(List<TreeNodeDocEntity> docs) {
		this.docs = docs;
	}

	public List<TreeNodePicEntity> getPics() {
		return pics;
	}

	public void setPics(List<TreeNodePicEntity> pics) {
		this.pics = pics;
	}

}
