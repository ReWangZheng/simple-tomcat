package com.wz.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;

import javax.sound.midi.Soundbank;

public class XMLParser {
	public static void main(String[] args) {
		XMLParser parser=new XMLParser("./config/servletconfig.xml");
		Selector s=parser.getSelector();
		Node p_node = s.select("/servlet/servlet-name[content=MyServlet]")[0]
				.getParent();
		System.out.println();
	}
	private int cursor;
	private Node root=new Node();
	private char[] xml;
	public XMLParser(File file) {
		try {
			FileInputStream fi=new FileInputStream(file);
			byte[] d=new byte[1024];
			int ch=-1;
			String buffer="";
			while((ch=fi.read(d))!=-1) {
				buffer+=new String(d,0,ch);
			}
			xml=buffer.toCharArray();
			cursor=0;
			this.root.setName("ROOT");
			this.root.addChild(parserNode());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public Selector getSelector() {
		return new URLselector(root);
	}
	
	public XMLParser(String filename) {
		this(new File(filename));
	}
	
	public Node parserNode() {
		Node node=new Node();
		byte state=0;
		String key="";
		String value="";
		String name="";
		String content="";
		boolean isLeaf=false;
		boolean yinhao=false;
		boolean isname=false;
		boolean annotation=false;
		while(cursor<xml.length && (state>>2)!=0b1111) {
			if(annotation==true) {
				if(xml[cursor]=='>' && xml[cursor-1]=='-' && xml[cursor-2]=='-') {
					annotation=false;
				}
				cursor++;
				continue;
			}
			
			switch (xml[cursor]) {
			case '<':
				if(yinhao==false&&(""+xml[cursor+1]+xml[cursor+2]+xml[cursor+3]).equals("!--")) {
					annotation=true;
					cursor++;
					continue;
				}
				if(state==0b000000) {
					state=0b100000;
				}else if((state>>2)==0b1100 && !isLeaf&&xml[cursor+1]!='/') {
					Node child=parserNode();
					node.addChild(child);
				}else if(((state>>2)==0b1100 && isLeaf)||xml[cursor+1]=='/') {
					if(isLeaf) {
						node.setContent(content);
					}
					cursor+=1+name.length();
					state=0b111000;
				}
				break;
			case '>':
				if((state>>2)==0b1000) {
					state=0b110000;
					node.setName(name);
					isname=true;
				}else if((state>>2)==0b1110) {
					return node;
				}
				break;
			case ' ':
				if(state==0b100000 && isname==false) {
					state=0b100010;
					node.setName(name);
					isname=true;
				}else if(state==0b100000 && isname) {
					state=0b100010;
				}else if(yinhao) {
					value+=xml[cursor];
				}
				break;
			case '=':
				if(state==0b100010) {
					state=0b100011;
				}
				break;
			case '\n':case '\r':case '\t':
				if(yinhao) {
					value+=xml[cursor];
				}
				break;
			case '\'':
			case '\"':
				if(yinhao&&state==0b100011) {
					yinhao =false;
					node.put(key, value);
					key="";
					value="";
					state=0b100000;
				}else if((!yinhao&&state==0b100011) ){
					yinhao=true;
				}
				break;
			default:
				if(state==0b100000 &&!isname) {
					name+=xml[cursor];
				}else if(state==0b100010) {
					key+=xml[cursor];
				}else if(state==0b100011) {
					value+=xml[cursor];
				}else if((state>>2)==0b1100) {
					content+=xml[cursor];
					isLeaf=true;
					node.setIsleaf(isLeaf);
				}
				break;
			}
			cursor++;
		}
		return node;
	}
	
	
	
	
}
