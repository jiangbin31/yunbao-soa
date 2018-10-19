package org.dubbo.pojo.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class FileUtils {
	
	private static final Logger logger=Logger.getLogger(FileUtils.class);
	
	public  static boolean createDir(String destDirName) {  
        File dir = new File(destDirName);  
        if (dir.exists()) {  
            return true;  
        }  
        if (!destDirName.endsWith(File.separator)) {  
            destDirName = destDirName + File.separator;  
        }  
        //创建目录  
        if (dir.mkdirs()) {  
            return true;  
        } else {  
            return false;  
        }  
    }
	
	public static boolean isFileExist(String filePath, String fileName){
		try{
			File dir = new File(filePath); 
			if(dir.exists()){
				File file = new File(filePath+fileName); 
				if(file.exists()) {
					return true;
				}
			}
		}catch(Exception e){
			logger.error("",e);
		}
		return false;
	}
	
	public static void writeToTxtFile(String filePath, String fileName,String message) {  
        try {  
            File f = new File(filePath+fileName);  
            if (!f.exists()) {  
            	 f.createNewFile();// 不存在则创建  
            } 
            BufferedWriter output=null;
        	output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, false)));
        	output.write(message);
        	output.close();  
        } catch (Exception e) {
        	logger.error("",e);
        }  
    }
	
	/**
	 * 判断文件的编码格式
	 * @param fileName :file
	 * @return 文件编码格式
	 * @throws Exception
	 */
	private static String getTxtCode(String fileName){
		String code = "GBK";
		BufferedInputStream bin=null;
		try{
			File file=new File(fileName);
            if(file.isFile() && file.exists()){
            	bin= new BufferedInputStream(new FileInputStream(fileName));
    			int p = (bin.read() << 8) + bin.read();
    			switch (p) {
    				case 0xefbb:
    					code = "UTF-8";
    					break;
    				case 0xfffe:
    					code = "Unicode";
    					break;
    				case 0xfeff:
    					code = "UTF-16BE";
    					break;
    				default:
    					code = "GBK";
    			}
            }
		}catch(Exception e){
			logger.error("",e);
		}finally{
			if(bin!=null){
				try {
					bin.close();
				} catch (Exception e) {
				}
			}
		}
		return code;
	}
	
	public static String readFromTxtFile(String filePath, String fileName) {
		String messageTxt=null;
		try{
			String encoding=getTxtCode(filePath+fileName);
			File file=new File(filePath+fileName);
            if(file.isFile() && file.exists()){
            	InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);
            	BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                //只读取第一行
                if((lineTxt = bufferedReader.readLine()) != null){
                	lineTxt=lineTxt.trim();
                }
                messageTxt=lineTxt;
            }
		}catch(Exception e){
			logger.error("",e);
		}
		return messageTxt;
	}
	
	/**
	 * 追加的方法写入
	 * @param filePath
	 * @param fileName
	 * @param message
	 */
	public static void writeToTxtFileForAdd(String filePath, String fileName,String message) {  
        try {  
            File f = new File(filePath+fileName);  
            if (!f.exists()) {  
            	 f.createNewFile();// 不存在则创建  
            } 
            BufferedWriter output=null;
        	output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true)));
        	output.write(message);
        	output.close();  
        } catch (Exception e) {
        	logger.error("",e);
        }  
    }
	
	/**
	 * 
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public static List<String> readAllFromTxtFile(String filePath, String fileName) {
		List<String> messageList=new ArrayList<String>();
		try{
			String encoding=getTxtCode(filePath+fileName);
			File file=new File(filePath+fileName);
            if(file.isFile() && file.exists()){
            	InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);
            	BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                	lineTxt=lineTxt.trim();
                	messageList.add(lineTxt);
                }
            }
		}catch(Exception e){
			logger.error("",e);
		}
		return messageList;
	}
	
	public static void main(String[] args) {
		String threadName="aaaaa-1";
		String messageText="abcabc";
		String filePath=PropertyUtils.getProperty("tmp_cache_file_save_path");
		String fileName=threadName+".txt";
		if(FileUtils.createDir(filePath)){
			FileUtils.writeToTxtFile(filePath, fileName, messageText);
		}
		//FileUtils.writeToTxtFile(filePath, fileName, "");
		logger.info(readFromTxtFile(filePath,fileName));
	}
}
