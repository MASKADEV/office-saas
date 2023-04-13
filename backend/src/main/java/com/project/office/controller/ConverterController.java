package com.project.office.controller;


import com.project.office.service.ConversionServiceImpl;
import java.io.IOException;
import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/converter")
public class ConverterController {

    @Autowired
    private ConversionServiceImpl conversionService;

    @PostMapping("/doc-pdf")
    public ResponseEntity<InputStreamResource> convertDocxToPdf(@RequestParam("file") MultipartFile docxFile) throws IOException {
        // convert docx file to pdf using conversion service
        byte[] pdfBytes = conversionService.convertDocxToPdf(docxFile.getBytes());

        // create input stream resource from pdf bytes
        InputStreamResource pdfResource = new InputStreamResource(new ByteArrayInputStream(pdfBytes));

        // set content type and headers for response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "converted.pdf");

        // return pdf resource in response entity with headers
        return ResponseEntity.ok().headers(headers).body(pdfResource);
    }
}
