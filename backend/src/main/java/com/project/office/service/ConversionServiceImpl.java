package com.project.office.service;

import com.itextpdf.text.*;
import org.springframework.stereotype.Service;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class ConversionServiceImpl implements ConversionService{
    @Override
    public byte[] convertDocxToPdf(byte[] docxBytes) throws IOException {
        if (docxBytes.length == 0) {
            throw new IllegalArgumentException("Supplied file was empty");
        }

        // read docx file using Apache POI
        XWPFDocument document = new XWPFDocument(new ByteArrayInputStream(docxBytes));

        // create pdf document using iText
        Document pdfDocument = new Document();
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(pdfDocument, pdfOutputStream);
        } catch (DocumentException e) {
            // handle exception here, e.g. log error and return default value
            System.err.println("Error creating PDF writer: " + e.getMessage());
            return new byte[0];
        }
        pdfDocument.open();

        // loop through paragraphs of docx document
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            // create new paragraph in pdf document
            Paragraph pdfParagraph = new Paragraph();

            // loop through runs of docx paragraph and add text to pdf paragraph
            for (XWPFRun run : paragraph.getRuns()) {
                String text = run.getText(0);
                if (text != null) {
                    pdfParagraph.add(new Chunk(text));
                }
            }

            // add pdf paragraph to pdf document
            try{
                pdfDocument.add(pdfParagraph);
            }catch (Exception e) {
            }
        }

        // close pdf document and output stream
        pdfDocument.close();
        pdfOutputStream.close();

        // return byte array of converted pdf file
        return pdfOutputStream.toByteArray();
    }

    @Override
    public byte[] convertJsonToXml(byte[] jsonBytes) throws IOException {
        return new byte[0];
    }

}

