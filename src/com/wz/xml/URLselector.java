package com.wz.xml;

import java.util.ArrayList;
import java.util.HashMap;

public class URLselector implements Selector{
	private Node root;
	
	//  /x1/x2/x3[45=c]/
	private ArrayList<Node> res;
	public URLselector(Node root) {
		this.root=root;
	}
	@Override
	public Node[] select(String path) {
		res=new ArrayList<>();
		this.select(path, root);
		return res.toArray(new Node[] {});
	}
	public void select(String path,Node node) {
		int star=path.indexOf('/');
		int end=path.indexOf('/',star+1);
		//   x/x/cc[content=acsa/acx]/axx
		String mes;
		if(end==-1) {
			
			mes=path.substring(star+1);
		}else {
			mes=path.substring(star+1,end);
		}
		int condition_star=mes.indexOf('[');
		int condition_end=mes.indexOf(']');
		if(condition_star!=-1 && condition_end==-1) {
			condition_end=path.indexOf(']')-1;
			end=path.indexOf('/',condition_end);
			if(end==-1) {
				mes=path.substring(star+1);
			}else {
				mes=path.substring(star+1,end);
			}
		}
		String name=mes;
		HashMap<String, String> conditionmap=null;
		if(condition_end!=-1 && condition_end!=-1) {
			name=mes.substring(0, condition_star);
			String condition_str=mes.substring(condition_star,condition_end+1);
			conditionmap=this.ParseCondition(condition_str);
		}
		
		
		Node[] nodes=node.map(name);
		if(nodes==null||nodes.length==0) {
			for(Node n:node.getChildren()) {
				 select(path, n);
			}
		}else {
			for(Node n:nodes) {
				boolean condition=true;
				if(conditionmap!=null) {
					for(String key:conditionmap.keySet()) {
						String v=n.attribute(key);
						if(key.equals("content")) {
							v=n.getContent();
						}
						if(v==null || !conditionmap.get(key).equals(v)) {
							condition=false;
						}
					}
				}
				if(end==-1&&condition) {
					this.res.add(n);
				}else if(condition){
					select(path.substring(end),n);	
				}
			}
		}
	}
	public HashMap<String, String> ParseCondition(String str) {
		int cursor=0;
		boolean d=false;
		String key="";
		String value="";
		boolean star=false;
		HashMap<String, String> map=new HashMap<>();
		while(cursor<str.length()) {
			switch (str.charAt(cursor)) {
			case '[':
				if(!star) {
					star=true;
				}
				break;
			case ']':
				if(!star) {
					star=false;
				}
			case ' ':
				if(d) {
					d=false;
					map.put(key, value);
					key="";
					value="";
				}
				break;
			case '=':
				if(!d) {
					d=true;
				}
				break;
			default:
				if(!d) {
					key+=str.charAt(cursor);
				}else {
					value+=str.charAt(cursor);
				}
				break;
			}
			cursor++;
		}
		return map;
	}
	@Override
	public Node selectByname(String name) {
		// TODO 自动生成的方法存根
		return null;
	}

}
