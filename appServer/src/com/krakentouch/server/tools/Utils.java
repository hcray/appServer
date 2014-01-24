package com.krakentouch.server.tools;

import org.apache.mina.core.buffer.IoBuffer;

public class Utils {
	/**  
	* 将byte[]转换成string    
	* @param butBuffer  
	*/  
	public static String byteToString(byte [] b)   
	{   
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
	public static IoBuffer stringToIoBuffer(String str)   
	{   
	  
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
	public static IoBuffer byteToIoBuffer(byte [] bt,int length)   
	{   
	  
	       IoBuffer ioBuffer = IoBuffer.allocate(length);   
	       ioBuffer.put(bt, 0, length);   
	       ioBuffer.flip();   
	       return ioBuffer;   
	}   
	/**  
	* 将IoBuffer转换成byte    
	* @param str  
	*/  
	public static byte [] ioBufferToByte(Object message)   
	{   
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
	public static String ioBufferToString(Object message)   
	{   
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

}
