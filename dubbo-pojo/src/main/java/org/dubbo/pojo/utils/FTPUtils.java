package org.dubbo.pojo.utils;


import com.alibaba.dubbo.common.utils.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * create by jinpan12430  20180206
 */
public class FTPUtils {

    private static final Logger logger = LoggerFactory.getLogger(FTPUtils.class);

    public static String DOWLOAD_URL = "http://192.168.0.118:8888/";
    private static String FTP_DIR = "/web";
    private static final Integer FTP_PORT = 21;
    private static final String FTP_IP = "192.168.0.118";
    private static final String FTP_USERNAME = "jnby";
    private static final String FTP_PASSWORD = "jnby1994";

    public static void main(String[] args) {
        //FTP上传到资源服务器
//        FTPUtils.upload(FTPUtils.connect("1/QR","192.168.0.28",21,"antscrm","12345678"), new File("F:\\中文.png"));
        File file = new File("D:\\csvTest\\a.txt");
        System.out.println(file.length());
        FTPUtils.upload(FTPUtils.connect(), file);
        FTPUtils.upload(FTPUtils.connect(FTP_DIR,FTP_IP,FTP_PORT,FTP_USERNAME,FTP_PASSWORD), file);
    }

    private static void show(FTPClient ftpClient){
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }

    /**
     *
     * @param path 上传到ftp服务器哪个路径下
     * @param addr 地址
     * @param port 端口号
     * @param username 用户名
     * @param password 密码
     * @return
     * @throws Exception
     */
    @SuppressWarnings("all")
    public static FTPClient connect(String path, String addr, int port, String username, String password){
        FTPClient ftp = new FTPClient();
        int reply;
        try {
            ftp.connect(addr,port);
            ftp.login(username,password);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return null;
            }
            path =  changeToZh(path);
           String cnPath =  new String(path.getBytes("GBK"),"iso-8859-1");
                        ftp.setControlEncoding("UTF-8");
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();
            if (StringUtils.isNotEmpty(path)){
                String [] temp = cnPath.split("/");
                if(null!=temp && temp.length>0){
                    for (int i = 0;i<temp.length;i++){
                        if (!ftp.changeWorkingDirectory(temp[i])){
                            if(ftp.makeDirectory(temp[i])){
                                ftp.changeWorkingDirectory(temp[i]);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("",e);
        }
        return ftp;
    }

    @SuppressWarnings("all")
    public static FTPClient connect(){
        FTPClient ftp = new FTPClient();
        int reply;
        try {
            ftp.connect(FTP_IP,FTP_PORT);
            ftp.login(FTP_USERNAME,FTP_PASSWORD);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return null;
            }

            FTP_DIR =  changeToZh(FTP_DIR);
            String cnPath =  new String(FTP_DIR.getBytes("GBK"),"iso-8859-1");

            ftp.setControlEncoding("UTF-8");
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();
            if (StringUtils.isNotEmpty(FTP_DIR)){
                String [] temp = cnPath.split("/");
                if(null!=temp && temp.length>0){
                    for (int i = 0;i<temp.length;i++){
                        if (!ftp.changeWorkingDirectory(temp[i])){
                            if(ftp.makeDirectory(temp[i])){
                                ftp.changeWorkingDirectory(temp[i]);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("",e);
        }
        return ftp;
    }

    public static FTPClient connect(String path){
        return connect(path,FTP_IP,FTP_PORT,FTP_USERNAME,FTP_PASSWORD);
    }

    /**
     *
     * @param ftp ftp客户端实例
     * @param file 上传的文件或文件夹
     * @throws Exception
     * @return 错误信息（为空表示成功）
     */
    public static void upload(FTPClient ftp, File file){
        try {
            if(file.isDirectory()){
                ftp.makeDirectory(changeToZh(file.getName()));
                ftp.changeWorkingDirectory(changeToZh(file.getName()));
                String[] files = file.list();
                for (int i = 0; i < files.length; i++) {
                    File file1 = new File(file.getPath()+"\\"+changeToZh(files[i]) );
                    if(file1.isDirectory()){
                        upload(ftp, file1);
                        ftp.changeToParentDirectory();
                    }else{
                        File file2 = new File(file.getPath()+"\\"+changeToZh(files[i]));
                        FileInputStream input = new FileInputStream(file2);
                        ftp.storeFile(file2.getName(), input);
                        input.close();
                    }
                }
            }else{
                File file2 = new File(file.getPath());
                FileInputStream input = new FileInputStream(file2);
                ftp.storeFile(changeToZh(file2.getName()), input);
                input.close();
            }
        } catch (IOException e) {
           logger.error("",e);
        }
    }

    private static String changeToZh(String str){
        String cnPath = null;
        try {
            cnPath = new String(str.getBytes("GBK"),"iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            logger.error("",e);
            return str;
        }
        return cnPath;
    }

    /**
     *
     * @param ftp ftp客户端实例
     * @param file 上传的文件或文件夹
     * @throws Exception
     * @return 错误信息（为空表示成功）
     */
    public static String uploadAndReturnError(FTPClient ftp, File file){
        String msg = null;
        try {
            if(file.isDirectory()){
                ftp.makeDirectory(file.getName());
                ftp.changeWorkingDirectory(file.getName());
                String[] files = file.list();
                for (int i = 0; i < files.length; i++) {
                    File file1 = new File(file.getPath()+"\\"+files[i] );
                    if(file1.isDirectory()){
                        upload(ftp, file1);
                        ftp.changeToParentDirectory();
                    }else{
                        File file2 = new File(file.getPath()+"\\"+files[i]);
                        FileInputStream input = new FileInputStream(file2);
                        ftp.storeFile(file2.getName(), input);
                        input.close();
                    }
                }
            }else{
                File file2 = new File(file.getPath());
                FileInputStream input = new FileInputStream(file2);
                ftp.storeFile(file2.getName(), input);
                input.close();
            }
        } catch (IOException e) {
            logger.error("",e);
            msg = e.getMessage();
        }

        return msg;
    }
}
