package org.dubbo.pojo.utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;


public class CsvUtil {

    private static final Logger logger=Logger.getLogger(FileUtils.class);

//    private static final String FTP_DIR = "web";
//    private static final Integer FTP_PORT = 21;
//    public static final String FTP_IP = "192.168.0.118";
//    private static final String FTP_USERNAME = "jnby";
//    private static final String FTP_PASSWORD = "jnby1994";

    /**
     * 创建并写文件
     * @param exportData
     * @param fileds
     * @param map
     * @param filePath
     * @param fileName
     * @param <T>
     * @return
     */
    public static <T> File createCsvFile(List<T> exportData, String[] fileds, LinkedHashMap map,
                                     String filePath, String fileName){
        if(map!=null&&filePath!=null&&fileName!=null){
            writeHead(map,filePath,fileName);
        }
        if(exportData!=null&&fileds!=null&&filePath!=null&&fileName!=null){
            writeContext(exportData,fileds,filePath,fileName);
        }
        return new File(filePath+fileName);
    }



    public static File writeHead(LinkedHashMap map,String filePath,String fileName){
        BufferedWriter bufferedWriter = null;
        try{
            bufferedWriter = fileToStream(filePath, fileName);
            // 写入文件头部
            for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                bufferedWriter.write((String) propertyEntry.getValue() != null ? new String(((String) propertyEntry.getValue()).getBytes("GBK"), "GBK") : "");
                if (propertyIterator.hasNext()) {
                    bufferedWriter.write(",");
                }
                System.out.println(new String(((String) propertyEntry.getValue()).getBytes("GBK"), "GBK"));
            }
            bufferedWriter.write("\r\n");
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedWriter!=null){
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new File(filePath+fileName);
    }

    public static <T> File writeContext(List<T> exportData, String[] fileds,String filePath, String fileName){
        BufferedWriter bufferedWriter = null;
        try{
            bufferedWriter = fileToStream(filePath, fileName);
            for (int j = 0; exportData != null && !exportData.isEmpty()
                    && j < exportData.size(); j++) {
                T t = (T) exportData.get(j);
                Class clazz = t.getClass();
                String[] contents = new String[fileds.length];
                for (int i = 0; fileds != null && i < fileds.length; i++) {
                    String filedName = toUpperCaseFirstOne(fileds[i]);
                    Method method = clazz.getMethod(filedName);
                    method.setAccessible(true);
                    Object obj = method.invoke(t);
                    String str = String.valueOf(obj);
                    if (str == null || str.equals("null"))
                        str = "";
                    contents[i] = str;
                }
                for (int n = 0; n < contents.length; n++) {
                    // 将生成的单元格添加到工作表中
                    bufferedWriter.write(contents[n]);
                    bufferedWriter.write(",");
                }
                bufferedWriter.write("\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedWriter!=null){
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new File(filePath+fileName);
    }

    private static String toUpperCaseFirstOne(String origin) {
        StringBuffer sb = new StringBuffer(origin);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        sb.insert(0, "get");
        return sb.toString();
    }

    public static boolean isFileCreated(String path){
        File file = new File(path);
        return file.exists();
    }

    private static BufferedWriter fileToStream(String filePath,String fileName) throws IOException {
        //创建文件的对象
        File csvFile = null;
        //csv创建文件缓冲流
        BufferedWriter csvFileOutputStream = null;
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 定义文件名格式并创建
        csvFile = new File(file,fileName);
        if(!csvFile.exists()){
            csvFile.createNewFile();
        }
        // UTF-8使正确读取分隔符","
        csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(csvFile,true), "GBK"), 1024);
        System.out.println("csvFileOutputStream：" + csvFileOutputStream);
        return csvFileOutputStream;
    }



    public static void main(String[] args) {
        List list = new ArrayList();//数据对象，add实体类
        Test test = new Test();
        for(int i = 0;i<100;i++){
            test.setA("a"+i);
            test.setB("b"+i);
            test.setC("c"+i);
            test.setD("d"+i);
            test.setE("e"+i);
            list.add(test);
        }
        String fileName = "test.csv";//文件名
        String outPutPath = "src/resources/report/"+File.separator+"csvReport"+File.separator;
        //对应实体类的属性
        String[] headArr = new String[]{"a","b","c","d","e"};
        LinkedHashMap map = new LinkedHashMap<>();
        map.put("1", "第一列");
        map.put("2", "第二列");
        map.put("3", "第三列");
        map.put("4", "第四列");
        map.put("5", "第五列");
//        System.out.println(this.getClass().getp);
//        CsvUtil.createCsvFile(list,headArr,map,outPutPath,fileName);
//        FTPUtils.upload(FTPUtils.connect(FTP_DIR,FTP_IP,FTP_PORT,FTP_USERNAME,FTP_PASSWORD), new File("D:\\csvTest\\test.csv"));
    }

    static class Test{
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }

        public String getE() {
            return e;
        }

        public void setE(String e) {
            this.e = e;
        }
    }


    // ============ //第二种格式：Arraylist<map>填充实体类的基本信息==================
//             for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
//                 Object row = (Object) iterator.next();
//                     for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
//                     java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
//                     csvFileOutputStream.write((String) BeanUtils.getProperty(row, ((String) propertyEntry.getKey()) != null ? (String) propertyEntry.getKey() : ""));
//                     if (propertyIterator.hasNext()) {
//                        csvFileOutputStream.write(",");
//                     }
//                 }
//                 if (iterator.hasNext()) {
//                 csvFileOutputStream.write("\r\n");
//                 }
//             }
}
