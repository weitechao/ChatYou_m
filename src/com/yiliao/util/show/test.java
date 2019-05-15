package com.yiliao.util.show;

public class test {

	public static void main(String[] args) {
		
		String res=new ShowApiRequest("http://route.showapi.com/1817-1","90224","c3d19d61817d474d82908a7cbf5c2f56")
				.addTextPara("url","http://www.baidu.com")
			  .post();
			System.out.println(res);
	}

}
