package com.controller;

import com.entity.ResultMsg;
import com.entity.ResultStatusCode;
import com.utils.ComUtil;
import com.utils.FileTypeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 文件上传公共服务主入口。
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

	private final static Logger logger = LoggerFactory.getLogger(FileController.class);

	@Value("${file.uploadPath}")
	private String uploadPath;

	/*
	 * @Value("${file.imgURL}") private String imgURL;
	 * 2.6.6.1   upload 图片接口
	 * @return
	 * @throws Exception
	 */

	@ResponseBody
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	public Object uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
		ResultMsg resultMsg = null;
		if (!file.isEmpty()) {

			String fileName = ComUtil.getUUID32();
			String OriginalFilename = file.getOriginalFilename();
			String suffix = OriginalFilename.substring(OriginalFilename.lastIndexOf("."));//.jpg
			//System.out.println(suffix);
			fileName = fileName+suffix;
			File fil = new File(uploadPath + fileName );
			try {

				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fil));
				out.write(file.getBytes());
				out.flush();
				out.close();

			} catch (FileNotFoundException e) {

				 e.printStackTrace();
				logger.error(e.getStackTrace().toString());
				resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), e.getMessage(), null);
				return resultMsg;

			} catch (IOException e) {
				logger.error(e.getStackTrace().toString());
				resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), e.getMessage(), null);
				return resultMsg;
			}

			Map<String, Object> body = new HashMap<String, Object>();
			body.put("fileName", fileName);
			System.out.println("fileName  "+fileName);
			System.out.println("origName  "+file.getOriginalFilename());
			body.put("origName", new String(file.getOriginalFilename().getBytes(), "UTF-8"));
			resultMsg = new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), ResultStatusCode.SUCCESS.getErrorMsg(),
					body); // 返回上传文件的访问路径

		} else {
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), "文件为空", null);
		}
		return resultMsg;
	}

	/**
	 * 2.6.6.2   批量上传 图片接
	 * @return
	 * @throws Exception
	 */

	@ResponseBody
	@RequestMapping(value = "/uploadImages", method = RequestMethod.POST)
	public Object uploadImages(HttpServletRequest request) {
		ResultMsg resultMsg = null;

		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");

		if (files.isEmpty()) {
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), "文件不能为空", null);
			return resultMsg;

		}

		Map<String, Object> body = new HashMap<String, Object>();
		List uploads = new ArrayList();
		List fails = new ArrayList();
		for (MultipartFile multipartFile : files) {
			String fileName = ComUtil.getUUID32();
			File fil = new File(uploadPath + fileName);
			try {
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fil));
				out.write(multipartFile.getBytes());
				out.flush();
				out.close();
				uploads.add(fileName);

			} catch (FileNotFoundException e) {
				// e.printStackTrace();
				logger.error(e.getStackTrace().toString());
				continue;

			} catch (IOException e) {
				logger.error(e.getStackTrace().toString());
				fails.add(multipartFile.getName());
				continue;
			}
		}
		body.put("names", uploads);
		body.put("fails", fails);
		resultMsg = new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), ResultStatusCode.SUCCESS.getErrorMsg(),
				body); // 返回上传文件的访问路径

		return resultMsg;
	}

	/**
	 * 2.6.6.3   image  显示接口
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showImage")
	@ResponseBody
	public void showImage(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "fileName", required = true) String name) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setContentType("image/jpeg");
		String fullFileName = uploadPath + name;

		File file = new File(fullFileName);
		if (!file.exists()){
			logger.info("\n" + "显示图片：" + fullFileName + "文件不存在。" + "\n");
			return;
		}
		// logger.info("fullFileName is {}.", fullFileName);
		FileInputStream fis = new FileInputStream(fullFileName);
		OutputStream os = response.getOutputStream();
		try {
			int count = 0;
			byte[] buffer = new byte[1024 * 1024];
			while ((count = fis.read(buffer)) != -1)
				os.write(buffer, 0, count);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null)
				os.close();
			if (fis != null)
				fis.close();
		}
	}

	/**
	 * 2.2.6.4	上传文件接口
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public Object uploadFile(@RequestParam(value = "fileName") MultipartFile file, HttpServletResponse response)
			throws IOException {
		// response.setHeader("Access-Control-Allow-Origin", "*");
		ResultMsg resultMsg = null;
		if (!file.isEmpty()) {
			String fileType = "";
			/*** MultipartFile转File ***/
			String originalName = new String(file.getOriginalFilename().getBytes(), "UTF-8");
			File f = new File(originalName);
			InputStream ins = file.getInputStream();
			FileTypeUtil.inputStreamToFile(ins, f);

			fileType = FileTypeUtil.getFileType(f);// 获取文件类型
			File del = new File(f.toURI());
			del.delete();// 删除临时的file文件
			/***********************/

			if (!StringUtils.hasText(fileType)) {
				resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), "不支持此文件类型", null);
				return resultMsg;
			}
			String fileName = ComUtil.getUUID32() + "." + fileType;
			File fil = new File(uploadPath + fileName);
			try {
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fil));
				out.write(file.getBytes());
				out.flush();
				out.close();

			} catch (FileNotFoundException e) {

				// e.printStackTrace();
				logger.error(e.getStackTrace().toString());
				resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), e.getMessage(), null);
				return resultMsg;

			} catch (IOException e) {
				logger.error(e.getStackTrace().toString());
				resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), e.getMessage(), null);
				return resultMsg;
			}

			Map<String, Object> body = new HashMap<String, Object>();
			body.put("fileName", fileName);
			body.put("origName", originalName);
			resultMsg = new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), ResultStatusCode.SUCCESS.getErrorMsg(),
					body); // 返回上传文件的访问路径

		} else {
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), "文件为空", null);
		}
		return resultMsg;
	}

	/**
	 * 2.6.6.5   批量上传文件接口
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */

	@ResponseBody
	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	public Object uploadFiles(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		ResultMsg resultMsg = null;
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");

		if (files.isEmpty()) {
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), "文件不能为空", null);
			return resultMsg;
		}

		Map<String, Object> body = new HashMap<String, Object>();
		List uploads = new ArrayList();
		List fails = new ArrayList();
		for (MultipartFile multipartFile : files) {
			String fileType = "";
			/*** MultipartFile转File ***/
			String originalName = new String(multipartFile.getOriginalFilename().getBytes(), "UTF-8");
			File f = new File(originalName);
			InputStream ins = multipartFile.getInputStream();
			FileTypeUtil.inputStreamToFile(ins, f);

			fileType = FileTypeUtil.getFileType(f);// 获取文件类型
			File del = new File(f.toURI());
			del.delete();// 删除临时的file文件
			/***********************/

			if (!StringUtils.hasText(fileType)) {
				resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), "不支持此文件类型", null);
				return resultMsg;
			}

			String fileName = ComUtil.getUUID32() + "." + fileType;
			File fil = new File(uploadPath + fileName);
			try {
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fil));
				out.write(multipartFile.getBytes());
				out.flush();
				out.close();
				uploads.add(fileName);

			} catch (FileNotFoundException e) {
				// e.printStackTrace();
				logger.error(e.getStackTrace().toString());
				continue;

			} catch (IOException e) {
				logger.error(e.getStackTrace().toString());
				fails.add(multipartFile.getName());
				continue;
			}
		}
		body.put("names", uploads);
		body.put("fails", fails);
		resultMsg = new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), ResultStatusCode.SUCCESS.getErrorMsg(),
				body); // 返回上传文件的访问路径

		return resultMsg;
	}


	@RequestMapping("/downloadFile")
	@ResponseBody
	public void downloadFile(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "fileName", required = true) String name) throws IOException {

		String filePath = uploadPath + name;// 获取要下载的路径+文件名
		// fullFileName = URLEncoder.encode(fullFileName, "UTF-8");//对文件进行url编码
		File fil = new File(filePath);
		if (!fil.exists()){
			logger.info("\n" + "下载：" + filePath + "文件不存在。" + "\n");
			return;
		}
		// 读取文件
		FileInputStream file = new FileInputStream(filePath);
		OutputStream os = response.getOutputStream();

		// name = URLEncoder.encode(name, "UTF-8");//对文件名编码
		/******* 对文件名编码 *********/
		String agent = request.getHeader("User-Agent");// 获得请求头中的User-Agent
		System.out.println(agent);
		String filenameEncoder = "";// 根据不同的客户端进行不同的编码
		if (agent.contains("MSIE")) {// IE浏览器

			filenameEncoder = URLEncoder.encode(name, "utf-8");
			filenameEncoder = filenameEncoder.replace("+", " ");
		} else if (agent.contains("Firefox")) {// 火狐浏览器

			BASE64Encoder base64Encoder = new BASE64Encoder();
			filenameEncoder = "=?utf-8?B?" + base64Encoder.encode(name.getBytes("utf-8")) + "?=";
		} else {// 其它浏览器

			filenameEncoder = URLEncoder.encode(name, "utf-8");
		}
		/******* 对文件名编码 *********/
		// 设置响应类型、响应头
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition", "attachment;filename=" + filenameEncoder);
		response.setContentLength(file.available());

		try {
			int len = 0;
			byte[] buffer = new byte[1024];

			while ((len = file.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
			os.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null)
				os.close();
			if (file != null)
				file.close();
		}
	}

	/**
	 * 
	 * 2.2.6.7	删除文件接口
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteFile")
	@ResponseBody
	public Object deleteFile(@RequestParam(value = "fileName", required = true) String name) throws IOException {
		ResultMsg resultMsg = null;
		String filePath = uploadPath + name;// 获取路径+文件名
		File file = new File(filePath);
		if (file.exists() && file.delete()) {
			resultMsg = new ResultMsg(ResultStatusCode.SUCCESS.getErrorCode(), ResultStatusCode.SUCCESS.getErrorMsg(),
					null);
		} else {
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrorCode(), "文件不存在", null);
		}
		return resultMsg;
	}


	/**
	 * 根据地址获得数据的字节流
	 * @param filePath 地址
	 * @return
	 */
	public static byte[] getByteStreamNetByUrl(String filePath) {
		try {
			URL url = new URL(filePath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();// 通过输入流获取数据
			byte[] btImg = readInputStream(inStream);// 得到二进制数据
			return btImg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从输入流中获取数据
	 * 
	 * @param inStream 输入流
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

}
