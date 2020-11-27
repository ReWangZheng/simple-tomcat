package com.wz.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Node {
	private String name;
	private LinkedList<Node> children;
	private Node parent;
	public boolean isIsleaf() {
		return isleaf;
	}
	public void setIsleaf(boolean isleaf) {
		this.isleaf = isleaf;
	}
	private HashMap<String, ArrayList<Node>> mapper;
	private String value;
	private HashMap<String, String> attributes;
	private String Content;
	private boolean isleaf=false;
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public Node() {
		children=new LinkedList<>();
		mapper=new HashMap<>();
		attributes=new HashMap<>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String attribute(String key) {
		return attributes.get(key);
	}
	public void put(String key,String value) {
		attributes.put(key, value);
	}
	@Override
	public String toString() {
		String res=name+"[";
		if(isleaf) {
			res+=" content="+Content+" ]";
		}else {
			for(String key:attributes.keySet()) {
				res+=" "+key+"="+attributes.get(key);
			}
			res+=" ]{";
			for(Node c:children) {
				res+=c.toString();
			}
			res+=" }";
		}
		return res;
	}
	
	public void addChild(Node c) {
		c.setParent(this);
		this.children.add(c);
		String name=c.getName();
		if(this.mapper.containsKey(c.getName())) {
			mapper.get(c.getName()).add(c);
		}else{
			ArrayList<Node> nodelist=new ArrayList<>();
			nodelist.add(c);
			mapper.put(c.getName(),nodelist);
		}
	}
	public LinkedList<Node> getChildren() {
		return children;
	}
	
	public Node[] map(String name) {
		if(!this.mapper.containsKey(name)) {
			return null;
		}
		return mapper.get(name).toArray(new Node[]{});
	}
	
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public Selector getSelector() {
		return new URLselector(this);
	}
}
