package com.springproject.universityinfoconvertor;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class StudentInfoController {
    @Autowired
    InfoProcessor infoProcessor;

    @GetMapping(value = "/students", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody
    byte[] getStudentInfoFile() throws IOException {
        InputStream in = new FileInputStream(infoProcessor.getCsvFile());
        return in.readAllBytes();
    }
}
