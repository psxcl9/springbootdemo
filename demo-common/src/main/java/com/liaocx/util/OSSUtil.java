package com.liaocx.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
/**
 *
 * @ClassName: OSSUtil.java
 * @Description: TODO 阿里云OSS工具
 *
 * @author wjq
 *
 * @date 2017年1月14日 下午2:13:47
 */
public class OSSUtil {

	// oss终端地址
	private final static String endpoint = "oss-cn-hangzhou.aliyuncs.com";
	// oss访问id
	private final static String accessId = "LTAIy6uHvZ9Mslli";
	// oss访问key
	private final static String accessKey = "fs1TFMEO5KLKMurXbmS2lAjRpDv6Bq";
	// oss存储空间
	private final static String bucket = "liaocx";

	// oss客户端
	private static OSSClient client;

	static {
		try {
			client = new OSSClient(endpoint, accessId, accessKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String uploadImg(MultipartFile file){
		if (file == null) {
			return "参数为空";
		}
		String fileName = UpFileNameType.buildFileName(UpFileNameType.TIMESTAMP, file.getOriginalFilename());
		StringBuilder imgdir = new StringBuilder();
		imgdir.append("img/");
		String dir = new SimpleDateFormat("yyyy-MM").format(new Date());
		imgdir.append(dir).append("/");
		imgdir.append(fileName);
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		client.putObject(bucket, imgdir.toString(), inputStream);
		return imgdir.toString();
	}
	
	
	public static String batchuploadImg(MultipartFile[] file){
		String url=",";
		for (int i = 0; i < file.length; i++) {
			StringBuilder imgdir = new StringBuilder();
			String fileName = UpFileNameType.buildFileName(UpFileNameType.TIMESTAMP, file[i].getOriginalFilename());
			imgdir.append("img/");
			String dir = new SimpleDateFormat("yyyy-MM").format(new Date());
			imgdir.append(dir).append("/");
			imgdir.append(fileName);
			
			InputStream inputStream = null;
			try {
				inputStream = file[i].getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			client.putObject(bucket, imgdir.toString(), inputStream);
			url+=imgdir.toString()+",";
		}
		  String regex = "^,*|,*$";
          String  str= url.replaceAll(regex, "");
		return str;
	}

	/**
	 * @param key
	 * @Title: deleteObject
	 * @Description: TODO(删除对象)
	 * @throws
	 * @author lirui 820975690@qq.com
	 * @Date 2016年11月16日 上午9:39:40
	 */
	public static void deleteObject(String key) {
		client.deleteObject(bucket, key);
	}

	/**
	 * 批量删除
	 * @param keys
	 */
	public static void batchDeleteObject(String[] keys) {
		List<String> keyList = new ArrayList<String>();
		for (int i = 0; i < keys.length; i++) {
			keyList.add(keys[i]);
		}
		DeleteObjectsResult deleteObjectsResult = client.deleteObjects(new DeleteObjectsRequest(bucket).withKeys(keyList));
		deleteObjectsResult.getDeletedObjects();
		// 关闭OSSClient
		client.shutdown();
	}

	/**
	 *
	 * @ClassName: OSSUtil.java
	 * @Description: TODO 上传文件命名类型
	 *
	 * @author lirui 820975690@qq.com
	 * @version V1.0
	 * @Date 2016年11月16日 上午11:32:33
	 */
	public enum UpFileNameType {
		/**
		 * 本地命名
		 */
		LOCAL(1, "本地命名"),
		/**
		 * 随机命名
		 */
		RANDOM(2, "随机命名"),
		/**
		 * 时间戳命名
		 */
		TIMESTAMP(3, "时间戳命名"),
		/**
		 * UUID-时间戳命名
		 */
		UUID_TIMESTAMP(4, "UUID-时间戳命名");

		private int value;
		private String text;

		private UpFileNameType(int value, String text) {
			this.value = value;
			this.text = text;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		/**
		 * @param type	OSSUtil.UpFileNameType  枚举
		 * @param source	原始文件名
		 * @return
		 * @Title: buildFileName
		 * @Description: TODO(根据上传文件命名类型生成文件名)
		 * @throws
		 */
		public static String buildFileName(UpFileNameType type, String source) {
			String fileName = source;
			String fileExt = StringUtil.getFileExt(source).toLowerCase();
			switch (type) {
			case LOCAL:
				return fileName;
			case RANDOM:
				int len = 10;
				String chars = "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678";
				int maxPos = chars.length();
				for (int i = 0; i < len; i++) {
					fileName += chars.charAt((int) Math.floor(Math.random()
							* maxPos));
				}
				break;
			case TIMESTAMP:
				fileName = String.valueOf(System.currentTimeMillis());
				break;
			case UUID_TIMESTAMP:
				fileName = UUID.randomUUID().toString().replace("-", "")
				.toLowerCase()
				+ String.valueOf(System.currentTimeMillis());
				break;
			default:
				return fileName;
			}
			fileName += fileExt;
			return fileName;
		}

	}

}