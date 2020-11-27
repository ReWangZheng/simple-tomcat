package com.wz.catalina;

import java.util.ArrayList;

import com.wz.myservlet.ServletRequest;
import com.wz.myservlet.ServletResponse;

public class StandardPipeline implements Pipeline{
	private StandardPipelineValueContext context=new StandardPipelineValueContext();
	protected class StandardPipelineValueContext implements ValueContext{
		private int stage=0;
		private ArrayList<Value> values=new ArrayList<>(); //其他阈
		private Value basevlaue; //基础阈
		public void invokeNext(ServletRequest req,ServletResponse res) {
			int current=stage;
			if(stage<values.size()) {
				stage++;
				values.get(current).invoke(req, res,this);
			}else if(stage==values.size()) {
				stage=0;
				basevlaue.invoke(req, res,this);
			}
		}
		@Override
		public String getInfo() {
			return "用来迭代执行管道方法";
		}
	}
	@Override
	public void invoke(ServletRequest req, ServletResponse res) {
		context.invokeNext(req, res);
	}
	@Override
	public Value getBasic() {
		return context.basevlaue;
	}
	@Override
	public void setBasic(Value v) {
		context.basevlaue=v;
	}
	@Override
	public Value[] getValues() {
		return context.values.toArray(new Value[] {});
	}
	@Override
	public void removeValue(Value value) {
		context.values.remove(value);
	}
	@Override
	public void addValue(Value v) {
		context.values.add(v);
	}
	
}
