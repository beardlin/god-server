package net.lantrack.framework.sysbase.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.coobird.thumbnailator.Thumbnails;

/***
 * 图片处理工具类      
 * @date 2018年9月18日
 */
public class PictureUtil {
	
	/**
	 * 将图片压缩到输出流中
	 * @param path
	 * @param width
	 * @param height
	 * @param out
	 * @throws IOException 
	 * @date 2018年9月18日
	 */
	public static void compressToOutPutStream(String path,int width,int height,OutputStream out) throws IOException {
		Thumbnails.of(path).size(width, height).toOutputStream(out);
	}
	public static void compressToOutPutStream(InputStream input,int width,int height,OutputStream out) throws IOException {
		Thumbnails.of(input).size(width, height).toOutputStream(out);
	}
	/**
	 * 将图片压缩到指定位置
	 * @param path
	 * @param width
	 * @param height
	 * @param outFilepath
	 * @throws IOException
	 * @date 2018年9月18日
	 */
	public static void compressToFile(String path,int width,int height,String outFilepath) throws IOException {
		Thumbnails.of(path).size(width, height).toFile(outFilepath);
	}
}
