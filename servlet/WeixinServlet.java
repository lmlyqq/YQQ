package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.SignUtil;
import util.WeixinProcess;

/**
 * Servlet implementation class WeixinServlet
 */
public class WeixinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public WeixinServlet() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		       
		// 微信加密签名  
		        
		String signature = request.getParameter("signature");  
		       
		// 时间戳  
		       
		String timestamp = request.getParameter("timestamp");  
		        
		// 随机数  
		       
		String nonce = request.getParameter("nonce");  
		         
		// 随机字符串  
		       
		String echostr = request.getParameter("echostr");  
		         
		        
		PrintWriter out = response.getWriter();  
		         
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
	       
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
		           
			out.print(echostr);  
		        
		}  
		         
		out.close();
		        
		out = null;

		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String appid=getServletContext().getInitParameter("appid");
		
		System.out.println(appid);
		
		request.setCharacterEncoding("UTF-8");  
		
        response.setCharacterEncoding("UTF-8");  
  
        /** 读取接收到的xml消息 */  
        StringBuffer sb = new StringBuffer();  
        
        InputStream is = request.getInputStream();  
        
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");  
        
        BufferedReader br = new BufferedReader(isr);  
        
        String s="";
        
        while ((s = br.readLine()) != null) {  
        	
            sb.append(s);  
        }  
        
        String xml = sb.toString(); //次即为接收到微信端发送过来的xml数据  
		
		String result="";
		
		result = new WeixinProcess().processWechatMag(xml);  
		
		
	     
		try {  
	           
	    	 OutputStream os = response.getOutputStream();  
	            
	    	 os.write(result.getBytes("UTF-8"));  
	           
	    	 os.flush();  
	            
	    	 os.close();  
	        
	     } catch (Exception e) {  
	            
	    	 e.printStackTrace();  
	        
	     }  
	}

}
