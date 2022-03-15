package com.management.clinic.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

public class FileUtil {
    public static String getUploadImage(HttpServletRequest request, String serverPath) throws Exception {
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
                StringBuilder realPath = new StringBuilder();
                StringTokenizer stk = new StringTokenizer(serverPath, "\\");
                while (stk.hasMoreTokens()) {
                    String tmp = stk.nextToken();
                    if (!tmp.equals("build")) {
                        realPath.append("\\");
                        realPath.append(tmp);
                    }
                }
                realPath.append(File.separator);
                realPath.append("static\\image");
                File folderUpload = new File(realPath.toString());
                fileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
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

    public static Map<Integer, String> getUploadImages(HttpServletRequest request, String serverPath) throws Exception {
        Map<Integer, String> files = new HashMap<>();
        int index = 0;
        for (Part part : request.getParts()) {
            String fileName = "";
            String contentDisp = part.getHeader("content-disposition");
            String[] items = contentDisp.split(";");

            for (String s : items) {
                if (s.trim().startsWith("filename")) {
                    fileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                }
//                if (s.trim().startsWith("name")&&s.trim().contains("@")) {
//                   try{
//                       index= Integer.parseInt(s.split("@")[1].substring(0,1));
//                   }catch (Exception e){
//
//                   }
//                }
            }
            if (!fileName.isEmpty()) {
                StringBuilder realPath = new StringBuilder();
                StringTokenizer stk = new StringTokenizer(serverPath, "\\");
                while (stk.hasMoreTokens()) {
                    String tmp = stk.nextToken();
                    if (!tmp.equals("build")) {
                        realPath.append("\\");
                        realPath.append(tmp);
                    }
                }
                realPath.append(File.separator);
                realPath.append("static\\image");
                File folderUpload = new File(realPath.toString());
                fileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
                if (!folderUpload.exists()) {
                    folderUpload.mkdirs();
                }
                part.write(folderUpload.getAbsolutePath() + File.separator + fileName);
                String src = "\\static\\image" + File.separator + fileName;
                files.put(index, src);
                index += 1;
            }
        }

        return files;
    }
}
