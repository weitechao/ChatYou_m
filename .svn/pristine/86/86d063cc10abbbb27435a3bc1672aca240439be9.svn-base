package com.yiliao.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.aspectj.util.FileUtil;
   

/**
 * 图片压缩功能
 * @author LuoMeiling
 *
 */
public class ImageUtils {   
	 
	/**
	 * 
	 * @param src 图片地址
	 * @param dist 存放路径
	 */
	 public static void createThumbnail(String src,String dist){   
		 
		 float width = 640;float height = 480;
		 
		  try{   
		   File srcfile = new File(src);
		   if(!srcfile.exists()){   
		     System.out.println("文件不存在");   
		    return;   
		   }
		  
		   BufferedImage image = ImageIO.read(srcfile);   
		      
		   //获得缩放的比例   
		   double ratio = 1.0;   
		   //判断如果高、宽都不大于设定值，则不处理   
		   if(image.getHeight()>height || image.getWidth()>width){
			   
			   double ratioH = (double)height/image.getHeight();
			   double ratioW = (double)width/image.getWidth();
			   
			   if(ratioW>=ratioH){
				   
				   ratio = ratioH;
			   }else{
				   
				   ratio = ratioW;
			   }
		   }   
		   //计算新的图面宽度和高度   
		   int newWidth =(int)(image.getWidth()*ratio);   
		   int newHeight =(int)(image.getHeight()*ratio);   
		      
		   Image resizedImage = image.getScaledInstance(newWidth, newHeight,Image.SCALE_DEFAULT);//压缩图片
		   
		   BufferedImage bfImage = null;
		   
		   if(dist.substring(dist.lastIndexOf(".") + 1).equalsIgnoreCase("png")){
			   
			   bfImage= new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_ARGB_PRE);
		   }else{
			   
			   bfImage= new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_RGB);
		   }
		   
		   Graphics g = bfImage.getGraphics(); 
		   g.drawImage(resizedImage, 0, 0, null);
		   
//		   for(int i=0;i<width;i++){   
//			  for(int j=0;j<height;j++){   
//			      int rgb=bfImage.getRGB(i, j);   
//			      if(isBackPixel(rgb)){   
//			    	  bfImage.setRGB(i, j,0);   
//			      }   
//			  }   
//		   } 
//		   
//		   FileOutputStream os = new FileOutputStream(dist);   
//		   JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);   
//		   encoder.encode(bfImage);
		   
		   g.dispose();
		   
		   ImageIO.write(bfImage, dist.substring(dist  
                   .lastIndexOf(".") + 1), new FileOutputStream(dist));
//		   os.close();      
		  } catch(Exception e) {   
		      
		  }   
		 }
	 /**
	  * 首页幻灯片
	  * @param src 图片地址
	  * @param dist 存放路径
	  */
	 public static void createThumbnailForkind(String src,String dist){   
		 
		 float width = 640;float height = 374;
		 
		 try{   
			 File srcfile = new File(src);   
			 if(!srcfile.exists()){   
				 System.out.println("文件不存在");   
				 return;   
			 }
			 
			 BufferedImage image = ImageIO.read(srcfile);   
			 
//			 //获得缩放的比例   
//			 double ratio = 1.0;   
//			 //判断如果高、宽都不大于设定值，则不处理   
//			 if(image.getHeight()>height || image.getWidth()>width){
//				 
//				 double ratioH = (double)height/image.getHeight();
//				 double ratioW = (double)width/image.getWidth();
//				 
//				 if(ratioW>=ratioH){
//					 
//					 ratio = ratioH;
//				 }else{
//					 
//					 ratio = ratioW;
//				 }
//			 }   
//			 //计算新的图面宽度和高度   
//			 int newWidth =(int)(image.getWidth()*ratio);   
//			 int newHeight =(int)(image.getHeight()*ratio);   
			 int newWidth =640;   
			 int newHeight =374;   
			 
			 Image resizedImage = image.getScaledInstance(newWidth, newHeight,Image.SCALE_DEFAULT);//压缩图片
			 
			 BufferedImage bfImage = null;
			 
			 if(dist.substring(dist.lastIndexOf(".") + 1).equalsIgnoreCase("png")){
				 
				 bfImage= new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_ARGB_PRE);
			 }else{
				 
				 bfImage= new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_RGB);
			 }
			 
			 Graphics g = bfImage.getGraphics(); 
			 g.drawImage(resizedImage, 0, 0, null);
			 
//		   for(int i=0;i<width;i++){   
//			  for(int j=0;j<height;j++){   
//			      int rgb=bfImage.getRGB(i, j);   
//			      if(isBackPixel(rgb)){   
//			    	  bfImage.setRGB(i, j,0);   
//			      }   
//			  }   
//		   } 
//		   
//		   FileOutputStream os = new FileOutputStream(dist);   
//		   JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);   
//		   encoder.encode(bfImage);
			 
			 g.dispose();
			 
			 ImageIO.write(bfImage, dist.substring(dist  
					 .lastIndexOf(".") + 1), new FileOutputStream(dist));
//		   os.close();      
		 } catch(Exception e) {   
			 
		 }   
	 }
	 public static void createThumbnailAndroid(String src,String dist){   
		 
		 float width = 400;float height = 400;
		 
		  try{   
		   File srcfile = new File(src);   
		   if(!srcfile.exists()){   
		     System.out.println("文件不存在");   
		    return;   
		   }
		   
		   if(srcfile.length()>(1024.0*10)){//图片小于10k，不进行压缩
		
			   BufferedImage image = ImageIO.read(srcfile);
			      
//			 //获得缩放的比例   
//			   double ratio = 1.0;   
//			   //判断如果高、宽都不大于设定值，则不处理   
//			   if(image.getHeight()>height || image.getWidth()>width){
//				   
//				   double ratioH = height/image.getHeight();
//				   double ratioW = width/image.getWidth();
//				   
//				   if(ratioW>=ratioH){
//					   
//					   ratio = ratioH;
//				   }else{
//					   
//					   ratio = ratioW;
//				   }
//			   } 
			   
			 //计算新的图面宽度和高度   
			   int newWidth=0,newHeight = 0;
			   
			   if(image.getWidth()>=image.getHeight()){
				   
				   newWidth = 240;
				   newHeight = (int)(image.getHeight()*(width/image.getWidth()));
			   }else{
				   
				   newHeight = 240;
				   newWidth = (int)(image.getWidth()*(height/image.getHeight()));
			   }
			      
			   Image resizedImage = image.getScaledInstance(newWidth, newHeight,Image.SCALE_DEFAULT);//压缩图片
			   
			   BufferedImage bfImage = null;
			   
			   if(dist.substring(dist.lastIndexOf(".") + 1).equalsIgnoreCase("png")){
				   
				   bfImage= new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_ARGB_PRE);
			   }else{
				   
				   bfImage= new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_RGB);
			   }
			   Graphics g = bfImage.getGraphics(); 
			   g.drawImage(resizedImage, 0, 0, null);
			   g.dispose();
			   
			   ImageIO.write(bfImage, dist.substring(dist  
	                   .lastIndexOf(".") + 1), new FileOutputStream(dist));
	//		   os.close();
		   
		   }else{
			   
			   FileUtil.copyFile(new File(src), new File(dist));
		   }
		  } catch(Exception e) {   
		      
		  }   
		 }   
		
	  private static BufferedImage getConvertedImage(BufferedImage image){
	        int width=image.getWidth();
	        int height=image.getHeight();
	        BufferedImage convertedImage=null;
	        Graphics2D g2D=null;
	        //采用带1 字节alpha的TYPE_4BYTE_ABGR，可以修改像素的布尔透明
	        convertedImage=new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
	        g2D = (Graphics2D) convertedImage.getGraphics();
	        g2D.drawImage(image, 0, 0, null);
	        //像素替换，直接把背景颜色的像素替换成0
	        for(int i=0;i<width;i++){
	            for(int j=0;j<height;j++){
	                int rgb=convertedImage.getRGB(i, j);
	                if(isBackPixel(rgb)){
	                    convertedImage.setRGB(i, j,0);
	                }
	            }
	        }
	        g2D.drawImage(convertedImage, 0, 0, null);
	        return convertedImage;
	    }
	  
	  private static boolean isBackPixel(int pixel){   
		      int back[]={-16777216};   
		    for(int i=0;i<back.length;i++){   
		         if(back[i]==pixel) return true;   
		      }   
		      return false;   
	}
	
	  public static void createThumbnailVideoMin(String src,String dist){   
			 
			 float width = 200;float height = 150;
			 
			  try{   
			   File srcfile = new File(src);   
			   if(!srcfile.exists()){   
			     System.out.println("文件不存在");   
			    return;   
			   }
			  
			   BufferedImage image = ImageIO.read(srcfile);   
			      
			 //获得缩放的比例   
			   double ratio = 1.0;   
			   //判断如果高、宽都不大于设定值，则不处理   
			   if(image.getHeight()>height || image.getWidth()>width){
				   
				   double ratioH = height/image.getHeight();
				   double ratioW = width/image.getWidth();
				   
				   if(ratioW>=ratioH){
					   
					   ratio = ratioH;
				   }else{
					   
					   ratio = ratioW;
				   }
			   }   
			   //计算新的图面宽度和高度   
			   int newWidth =(int)(image.getWidth()*ratio);   
			   int newHeight =(int)(image.getHeight()*ratio);   
			      
			   Image resizedImage = image.getScaledInstance(newWidth, newHeight,Image.SCALE_FAST);//压缩图片
			   
			   BufferedImage bfImage = null;
			   
			   if(dist.substring(dist.lastIndexOf(".") + 1).equalsIgnoreCase("png")){
				   
				   bfImage= new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_ARGB_PRE);
			   }else{
				   
				   bfImage= new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_RGB);
			   }
			   
			   Graphics g = bfImage.getGraphics(); 
			   g.drawImage(resizedImage, 0, 0, null);
			   
//			   for(int i=0;i<width;i++){   
//				  for(int j=0;j<height;j++){   
//				      int rgb=bfImage.getRGB(i, j);   
//				      if(isBackPixel(rgb)){   
//				    	  bfImage.setRGB(i, j,0);   
//				      }   
//				  }   
//			   } 
//			   
//			   FileOutputStream os = new FileOutputStream(dist);   
//			   JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);   
//			   encoder.encode(bfImage);
			   
			   g.dispose();
			   
			   ImageIO.write(bfImage, dist.substring(dist  
	                   .lastIndexOf(".") + 1), new FileOutputStream(dist));
//			   os.close();      
			  } catch(Exception e) {   
			      
			  }   
			 }
} 
