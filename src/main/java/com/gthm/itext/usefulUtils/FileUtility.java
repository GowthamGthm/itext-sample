package com.gthm.itext.usefulUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gthm.itext.model.PdfResponse;
import org.springframework.core.io.ClassPathResource;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtility {

    public static String getFileContents(String filePath) {

        ClassPathResource classPathResource = new ClassPathResource(filePath);

        try {
            String path = classPathResource.getFile()
                                           .getAbsoluteFile()
                                           .getPath();
            return Files.readString(Path.of(path));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public PdfResponse getData() {
        String fileContents = getFileContents("data.json");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES , false);
        try {
            return mapper.readValue(fileContents, PdfResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}