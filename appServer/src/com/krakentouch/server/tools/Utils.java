package com.krakentouch.server.tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.mina.core.buffer.IoBuffer;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Utils {
	/**  
	* 将byte[]转换成string    
	* @param butBuffer  
	*/  
	public static String byteToString(byte [] b){   
	       StringBuffer stringBuffer = new StringBuffer();   
	       for (int i = 0; i < b.length; i++)   
	       {   
	           stringBuffer.append((char) b [i]);   
	       }   
	       return stringBuffer.toString();   
	}   
	  
	/**  
	* 将bytebuffer转换成string    
	* @param str  
	*/  
	public static IoBuffer stringToIoBuffer(String str){   
	  
	       byte bt[] = str.getBytes();   
	  
	       IoBuffer ioBuffer = IoBuffer.allocate(bt.length);   
	       ioBuffer.put(bt, 0, bt.length);   
	       ioBuffer.flip();   
	       return ioBuffer;   
	}   
	/**  
	* 将IoBuffer转换成string    
	* @param str  
	*/  
	public static IoBuffer byteToIoBuffer(byte [] bt,int length){   
	  
	       IoBuffer ioBuffer = IoBuffer.allocate(length);   
	       ioBuffer.put(bt, 0, length);   
	       ioBuffer.flip();   
	       return ioBuffer;   
	}   
	/**  
	* 将IoBuffer转换成byte    
	* @param str  
	*/  
	public static byte [] ioBufferToByte(Object message){   
	      if (!(message instanceof IoBuffer))   
	      {   
	          return null;   
	      }   
	      IoBuffer ioBuffer = (IoBuffer)message;   
	      byte[] b = new byte[ioBuffer.limit()];   
	      ioBuffer.get(b);   
	      return b;   
	}   
	/**  
	* 将IoBuffer转换成string    
	* @param butBuffer  
	*/  
	public static String ioBufferToString(Object message){   
	      if (!(message instanceof IoBuffer))   
	      {   
	        return "";   
	      }   
	      IoBuffer ioBuffer = (IoBuffer) message;   
	      byte[] b = new byte [ioBuffer.limit()];   
	      ioBuffer.get(b);   
	      StringBuffer stringBuffer = new StringBuffer();   
	  
	      for (int i = 0; i < b.length; i++)   
	      {   
	  
	       stringBuffer.append((char) b [i]);   
	      }   
	       return stringBuffer.toString();   
	} 
	
	
	/***
	 * 解析XML格式的命令
	 * @param commandStr
	 * @return 包含命令的map
	 */
	public static Map<String,String> parseCommand(String commandStr){
		Map<String,String> retMap = new HashMap<String,String>();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(commandStr); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节点
			Iterator<?> it = rootElt.attributeIterator();
			while(it.hasNext()){
				Attribute attribute = (Attribute) it.next();
				String name = attribute.getName();
				String value = attribute.getValue();
				retMap.put(name, value);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return retMap;
	}
	
	
	public static void main(String[] args) {
		String commandStr = "<TCP address=\"DB\" command=\"new\" table=\"DeskMap\" value=\"DeskID=00000000\" ></TCP>";
		Map<String,String> retMap = parseCommand(commandStr);
		System.out.println("retMap: " + retMap);
	}

}
