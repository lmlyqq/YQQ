package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SignUtil {
	
	 private static String token = "weixinServlet";


	  public static boolean checkSignature(String signature, String timestamp,
			               String nonce) {

	  
      String[] arr = new String[] { token, timestamp, nonce };

      sort(arr);
	  
      StringBuilder content = new StringBuilder();

      for (int i = 0; i < arr.length; i++) {
    	  content.append(arr[i]);
      }
	  
      MessageDigest md = null;
               
      String tmpStr = null;
       
              
      try {
                  
    	  md = MessageDigest.getInstance("SHA-1");
                  
    	  // 将三个参数字符串拼接成一个字符串进行sha1加密
                  
    	  byte[] digest = md.digest(content.toString().getBytes());
          
    	  tmpStr = byteToStr(digest);
          
      } catch (NoSuchAlgorithmException e) {
             
    	  e.printStackTrace();
            
      }
       
              
      content = null;
               // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        
      return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;


 
	  }
	  
	  
	  public static void sort(String a[]) {
		  
		    
		  for (int i = 0; i < a.length - 1; i++) {
		         
			  for (int j = i + 1; j < a.length; j++) {
		              
				  if (a[j].compareTo(a[i]) < 0) {
		                   
					  String temp = a[i];
		                  
					  a[i] = a[j];
	                      
					  a[j] = temp;
		                  
		               
				  }
		        
			  }
		    
		  }
	 
	  }
	  
	  private static String byteToStr(byte[] byteArray) {
		  
		    
		  String strDigest = "";
		     		     
		  for (int i = 0; i < byteArray.length; i++) {
		    	 		     
			  strDigest += byteToHexStr(byteArray[i]);
		           		     
		  }
		        
		  return strDigest;
		           
		      
	  }
	  
	  //转16进制
	  private static String byteToHexStr(byte mByte) {
		          
		  char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
		                   'B', 'C', 'D', 'E', 'F' };
		         
		  char[] tempArr = new char[2];
		           
		  tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		          
		  tempArr[1] = Digit[mByte & 0X0F];   
		         
		  String s = new String(tempArr);
		     
                  String s ="1234567";      

		  return s;
		       
	  }

	  
}
