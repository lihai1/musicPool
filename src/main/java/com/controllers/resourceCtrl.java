/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

/**
 *
 * @author LihaiMac
 */

import com.dao.AppGlobalProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

@CrossOrigin
@RestController
@EnableAutoConfiguration
public class resourceCtrl {

    private Logger logger = LoggerFactory.getLogger(resourceCtrl.class);

    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public void doDownload(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {

        // get absolute path of the application
        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        appPath="";
        System.out.println("appPath = " + appPath);
        logger.info("appPath =  {}",appPath);
        // construct the complete absolute path of the file
        String filePath = "apk/android-debug.apk";
        String fullPath = appPath + filePath;
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[AppGlobalProperties.BUFFER_SIZE];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();

    }

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "http://www.pais.co.il/Lotto/Pages/last_Results.aspx?download=1";

    @RequestMapping(path = "/schedule", method = RequestMethod.GET)
    private static void sendGET() throws IOException {

            try {
                //  String url="http://www.pais.co.il/Lotto/Pages/last_Results.aspx?download=1";
                //  URLConnection connection = new URL(url).openConnection();
                //  BufferedWriter bw = new BufferedWriter (new FileWriter("test"));
                //  while(connection.getInputStream().available()>0)
                //   bw.write(connection.getInputStream().read());
                URI uri = new URI(GET_URL);
                java.awt.Desktop.getDesktop().browse(uri);
                File f=new File(System.getProperty("user.home")+"/Downloads/lotto.csv");
                f.renameTo(new File("lotto.data"));
                Thread.sleep(1000);
                f=new File("/Users/LihaiMac/Downloads/lotto.csv");

                //  System.gc();
                if(f.delete()){
                    System.out.println(f.getName() + " is deleted!");
                }else{
                    System.out.println("Delete operation is failed.");
                }
            } catch (Exception e) {
                //System.out.println("THROW::: make sure we handle browser error");
                e.printStackTrace();
            }

    }

}


