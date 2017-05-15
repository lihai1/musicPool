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

import com.dao.UserService;
import com.models.FormRequest;
import com.models.formAnalyzeRequest;
import com.models.statsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicLong;

import static com.dao.AppGlobalProperties.BUFFER_SIZE;

@CrossOrigin
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/generate")
public class GeneratFormCtrl {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(GeneratFormCtrl.class);
    private UserService apiRequestService;

    @Autowired
    public GeneratFormCtrl(UserService apiRequestService) {
        this.apiRequestService = apiRequestService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity hello(@RequestParam(required=false, defaultValue="World") String name) {
        return new ResponseEntity<>(String.format("hello world , %s!!!",name), HttpStatus.OK);
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    //@ResponseBody
    public ResponseEntity generateNewForm(@RequestBody FormRequest formReq) {
        //User u = new User("admin2","password");
        //apiRequestService.saveUser(u);
        return new ResponseEntity<>(formReq.runRequest(), HttpStatus.OK);
    }

    @RequestMapping(value = "/pares", method = RequestMethod.POST)
    //@ResponseBody
    public ResponseEntity getStatistics(@RequestBody statsRequest formReq) {
        return new ResponseEntity<>(formReq.runRequest(), HttpStatus.OK);
    }

    @PostMapping(value = "/analyze")
    @ResponseBody
    public ResponseEntity getAnalyze(@RequestBody formAnalyzeRequest formReq) {
        return new ResponseEntity<>(formReq.runRequest(), HttpStatus.OK);
    }

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

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();

    }


}


