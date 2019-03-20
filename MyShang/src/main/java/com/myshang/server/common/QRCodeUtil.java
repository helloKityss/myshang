package com.myshang.server.common;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.aspectj.weaver.ast.Var;
import org.junit.Test;

import sun.misc.BASE64Encoder;
import sun.util.logging.resources.logging;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码工具类
 */
public class QRCodeUtil {
	private static final int width = 300;// 默认二维码宽度
	private static final int height = 300;// 默认二维码高度
	private static final String format = "jpg";// 默认二维码文件格式
	private static final Map<EncodeHintType, Object> hints = new HashMap();// 二维码参数

	static {
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");// 字符编码
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 容错等级 L、M、Q、H 其中 L 为最低, H 为最高
		hints.put(EncodeHintType.MARGIN, 0);// 二维码与图片边距
	}

	/**
	 * 返回一个 BufferedImage 对象
	 * 
	 * @param content
	 *            二维码内容
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 */
	public static BufferedImage toBufferedImage(String content, int width, int height) throws WriterException, IOException {
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}
		return image;
	}

	/**
	 * 将二维码图片输出到一个流中
	 * 
	 * @param content
	 *            二维码内容
	 * @param stream
	 *            输出流
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 */
	public static void writeToStream(String content, OutputStream stream, int width, int height) throws WriterException, IOException {
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
	}

	/**
	 * 生成二维码图片文件
	 * 
	 * @param content
	 *            二维码内容
	 * @param path
	 *            文件保存路径
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 */
	public static void writeToFile(String content, String path, int width, int height) throws WriterException, IOException {
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		// toPath() 方法由 jdk1.7 及以上提供
		MatrixToImageWriter.writeToPath(bitMatrix, format, new File(path).toPath());
	}

	/**
	 * 将二维码图片输出到Base64
	 * 
	 * @param content
	 *            二维码内容
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 */
	public static String writeToBase64(String content, int width, int height) throws WriterException, IOException {
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);
		String imgBase64 = new BASE64Encoder().encode(outputStream.toByteArray());
		return imgBase64;
	}

	/**
	 * 缩放图片
	 * 
	 * @param imgPath
	 *            图片地址
	 * @throws Exception
	 */
	public static BufferedImage zoomImage(String imgPath, int width, int height) {
		BufferedImage result = null;
		try {
			BufferedImage im = null;
			File srcfile = new File(imgPath);
//			System.out.println(srcfile.toString());
//			System.out.println(imgPath);
			if (!srcfile.exists()) {
				im = ImageIO.read(new URL(imgPath));
			} else {
				im = ImageIO.read(srcfile);
			}
			/* 新生成结果图片 */
			result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			result.getGraphics().drawImage(im.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

		} catch (Exception e) {
			System.out.println("*********");
			System.out.println("创建缩略图发生异常" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 图片流
	 * 
	 * @param imgPath
	 *            图片地址
	 * @throws Exception
	 */
	public static BufferedImage loadImage(String imgPath) {
		BufferedImage im = null;
//		System.out.println(imgPath);
		try {
			File srcfile = new File(imgPath);
			if (!srcfile.exists()) {
				im = ImageIO.read(new URL(imgPath));
			} else {
				im = ImageIO.read(srcfile);
			}

		} catch (Exception e) {
			System.out.println("#########");
			System.out.println("创建缩略图发生异常" + e.getMessage());
		}
		return im;
	}

	/**
	 * 居中插入LOGO
	 * 
	 * @param source
	 *            二维码图片
	 * @param imgPath
	 *            LOGO图片地址
	 * @throws Exception
	 */
	public static BufferedImage insertImage(BufferedImage source) throws Exception {
		Graphics2D graph = source.createGraphics();

		int x = source.getWidth();
		int y = source.getHeight();
		graph.drawImage(null, x, y, null, null);
		Shape shape = new RoundRectangle2D.Float(x, y, 0, 0, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
		return source;
	}

	/**
	 * 插入LOGO
	 * 
	 * @param source
	 *            二维码图片
	 * @param imgPath
	 *            LOGO图片地址
	 * @throws Exception
	 */
	public static BufferedImage insertImage(BufferedImage source, BufferedImage logo, int x, int y) throws Exception {
		Graphics2D graph = source.createGraphics();

		graph.drawImage(logo, x, y, logo.getWidth(), logo.getHeight(), null);
		Shape shape = new RoundRectangle2D.Float(x, y, logo.getWidth(), logo.getHeight(), 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
		return source;
	}
	
	/**
	 * 生成二维码
	 * @param path		存储路径
	 * @param logoPath	logo路径
	 * @param content	内容
	 * @param width1	二维码宽
	 * @param height1	二维码高
	 * @param width2	logo在二维码中的宽
	 * @param height2	logo在二维码中的高
	 * @return path		二维码路径
	 */
	public String createQRcode(String path,String content,int i,int width1,int height1){
		String result = "";
//		System.out.println(logoPath);
		try {
			File outputFile = null;
			UUID UUId =  UUID.randomUUID();
			String uuid = UUId.toString().replace("-", "").toLowerCase();
			path = path+"/"+i+".png";
			BufferedImage source = toBufferedImage(content, width1, height1);
			BufferedImage img = insertImage(source);
			outputFile = new File(path);
			ImageIO.write(img, "png", outputFile);
			result = path;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Test
	public void aaa() {
		try {
			String path = "E://apache-tomcat-7.0.90/webapps/1d3e7de2869e44b29afbeaecffe9dc67.jpg";
			File file = new File(path);
			Boolean a = file.delete();
			if(a){
				System.out.println("成功了");
			}
			// http://tva4.sinaimg.cn/crop.0.0.180.180.180/a355fd1ajw1e8qgp5bmzyj2050050aa8.jpg
			// E:\1.jpg
//			for(int i=0;i<10;i++){
//				String content = "我是大脸猫"+i;
//				String name = "二维码"+i;
//				String path = "E://"+name+".jpg";
//				File outputFile = null;
//				String logoPath = "E://1.jpg";
//				BufferedImage source = toBufferedImage(content, width, height);
//				//outputFile = new File(path);
//				//ImageIO.write(source, "JPEG", outputFile);
//				BufferedImage logo = zoomImage(logoPath, 60, 60);
//				//outputFile = new File("E://1.jpg");
//				//ImageIO.write(logo, "JPEG", outputFile);
//				BufferedImage img = insertImage(source, logo);
//				outputFile = new File(path);
//				ImageIO.write(img, "JPEG", outputFile);
//			}
//			File outputFile = null;
//			String logoPath = "E://1.jpg";
//			BufferedImage source = toBufferedImage("我是大脸猫", width, height);
//			outputFile = new File("E://erweima.jpg");
//			//ImageIO.write(source, "JPEG", outputFile);
//			BufferedImage logo = zoomImage(logoPath, 60, 60);
//			//outputFile = new File("E://1.jpg");
//			//ImageIO.write(logo, "JPEG", outputFile);
//			BufferedImage img = insertImage(source, logo);
//			outputFile = new File("E://img.jpg");
//			ImageIO.write(img, "JPEG", outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
