package com.management.clinic.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.util.StringTokenizer;

public class FileUtil {
    public static String getUploadImage(HttpServletRequest request,String serverPath) throws Exception {
        String fileName = "";
        for (Part part : request.getParts()) {
            String contentDisp = part.getHeader("content-disposition");
            String[] items = contentDisp.split(";");
            for (String s : items) {
                if (s.trim().startsWith("filename")) {
                    fileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                }
            }
            if (!fileName.isEmpty()) {

//                if (!fileName.substring(fileName.lastIndexOf(".") + 1).equals("png")
//                        && !fileName.substring(fileName.lastIndexOf(".") + 1).equals("jpeg")
//                        && !fileName.substring(fileName.lastIndexOf(".") + 1).equals("jpg")) {
//                    return "fail";
//                }

                //+ File.separator + "image";
                StringBuilder realPath=new StringBuilder();
                StringTokenizer stk= new StringTokenizer(serverPath,"\\");
                while(stk.hasMoreTokens()){
                    String tmp=stk.nextToken();
                    if(!tmp.equals("build")){
                        realPath.append("\\");
                        realPath.append(tmp);
                    }
                }
                realPath.append(File.separator);
                realPath.append("static\\image");
                File folderUpload = new File(realPath.toString());
                if (!folderUpload.exists()) {
                    folderUpload.mkdirs();
                }
                part.write(folderUpload.getAbsolutePath() + File.separator + fileName);
                String src = "\\static\\image" + File.separator + fileName;
                return src;
            }
        }
        return "";
    }
}
