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
	
		       
		// ΢�ż���ǩ��  
		        
		String signature = request.getParameter("signature");  
		       
		// ʱ���  
		       
		String timestamp = request.getParameter("timestamp");  
		        
		// �����  
		       
		String nonce = request.getParameter("nonce");  
		         
		// ����ַ���  
		       
		String echostr = request.getParameter("echostr");  
		         
		        
		PrintWriter out = response.getWriter();  
		         
		// ͨ������signature���������У�飬��У��ɹ���ԭ������echostr����ʾ����ɹ����������ʧ��  
	       
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
  
        /** ��ȡ���յ���xml��Ϣ */  
        StringBuffer sb = new StringBuffer();  
        
        InputStream is = request.getInputStream();  
        
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");  
        
        BufferedReader br = new BufferedReader(isr);  
        
        String s="";
        
        while ((s = br.readLine()) != null) {  
        	
            sb.append(s);  
        }  
        
        String xml = sb.toString(); //�μ�Ϊ���յ�΢�Ŷ˷��͹�����xml����  
		
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
