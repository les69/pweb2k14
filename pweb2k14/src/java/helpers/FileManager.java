/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import helpers.ServletHelperClass;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.DbHelper;

/**
 *
 * @author lorenzo
 */
public class FileManager {
    public static String SaveFile(Part filePart,String groupName, String username, String absoluteFilePath, HttpServletResponse response, DbHelper helper) throws IOException {
        InputStream filecontent = filePart.getInputStream();
        String fileName = getFileName(filePart);
        String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
        String hash = ServletHelperClass.encryptPassword(fileName+groupName+username);
        File avatarFile = new File(absoluteFilePath + "/" + hash);
        OutputStream fos = null;
        //Uncomment line below if we want to preserve old avatars
        //if (!avatarFile.exists())
        try {
            fos = new FileOutputStream(avatarFile);
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = filecontent.read(bytes)) != -1) {
                fos.write(bytes, 0, read);
            }
            
        } catch (IOException e) {
            String s = e.getMessage();
            response.sendRedirect("/NotSupported.jsp");
        } finally {
            if (fos != null) {
                fos.close();
            }
            filecontent.close();
        }
        return hash;
    }
    public static String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
    
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
            return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
}
}
