package com.wz.jsp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.lang.ProcessBuilder.Redirect;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.DocFlavor.STRING;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;


public class JspParser {
	public static void main(String[] args) {
 		JspParser parser = new JspParser();
 		File file =new File("F:\\JAVA´úÂë\\Mytomcat\\webroot\\one.jsp");
 		try {
			parser.parse(file,"Fir");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	StringBuffer res;
	StringBuffer define;
	String model="";
	public JspParser() {
		map.put("page", Pattern.compile("<%[\\s]*@[\\s]*page"));
		map.put("define", Pattern.compile("<%![\\s\\S]*%>"));
		map.put("jsp:forward", Pattern.compile("<jsp:forward.*>[\\s\\S]*?</jsp:forward>"));
		map.put("jsp:include", Pattern.compile("<jsp:include.*>[\\s\\S]*?</jsp:include>"));
		map.put("expression", Pattern.compile("<%=[\\s\\S]*?%>"));
		File model=new File("F:\\JAVA´úÂë\\Mytomcat\\webroot\\model.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(model));
			String line =null;
			while((line=reader.readLine())!=null) {
				this.model = this.model+line+"\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void parse(File file,String name) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		res = new StringBuffer();
		define = new StringBuffer();
		StringBuffer code =new StringBuffer();
		String line = null;
		while((line = reader.readLine())!=null) {
			code.append(line+"\n");
		}
		String code_str = code.toString();
		Pattern p = Pattern.compile("(<%[\\s\\S]*?%>|<jsp:.*?>[\\s\\S]*?</jsp:.*?>)");
		Matcher m = p.matcher(code_str);
		int html_start = 0;
		int jsp_start = 0;
		int jsp_end = 0;
		int html_end = 0;
		while (m.find()) {
			String jsp_mes = m.group();
			jsp_start = m.start();
			jsp_end = m.end();
			
			html_end = jsp_start;
			String html_mes = code_str.substring(html_start,html_end);
			
			handle_html(html_mes);
			handle_jsp(jsp_mes);

			html_start = jsp_end;
		}
		handle_html(code_str.substring(jsp_end));
		model=model.replace("@java_code@", res);
		model=model.replace("@define@", define);
		model=model.replace("@class_name@", name);
		File target = new File("F:\\JAVA´úÂë\\Mytomcat\\src\\com\\wz\\jsp\\"+name+".java");
		BufferedWriter br=new BufferedWriter(new FileWriter(target));
		br.write(model);
		br.flush();

	}
	public void handle_html(String mes) {
		mes = mes.replaceAll("[\\t]*", "");
		mes = mes.replaceAll("\"", "a");
		write_op(mes);
	}
	HashMap<String, Pattern> map=new HashMap<>();
	public void handle_jsp(String mes) {
		boolean state = false;
		for(String key:map.keySet()) {
			Pattern p =map.get(key);
			Matcher m = p.matcher(mes);
			state = m.find();
			if(state) {
				if(key.equals("define")) {
					handle_java_define(mes);
				}
				return;
			}
		}
		if(!state) {
			handle_jave_code(mes);
		}
	}
	
	public void handle_page(String mes) {
		
	}
	public void handle_jsp_include(String mes) {
		
	}
	public void handle_jsp_forwadr(String mes) {
		
	}
	public void handle_jave_code(String mes) {
		mes = mes.replace("<%", "");
		mes = mes.replace("%>", "");
		res.append(mes);
	}
	
	public void write_op(String str) {
		for(String line:str.split("\n")) {
			if(line.equals("")) {
				continue;
			}
			this.res.append("\t\tout.print(\""+ line +"\");\n\r");
		}
	}
	
	public void handle_java_define(String mes) {
		mes = mes.replace("<%!", "");
		mes = mes.replace("%>", "");
		this.define.append(mes);
	}
	
	public int Compiler(String path) {
		JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
		
		int state = jc.run(null, System.out, null, path);
		return state;
	}
}
