package com.project.office.service;

import java.io.IOException;

public interface ConversionService {
    byte[] convertDocxToPdf(byte[] docxBytes) throws IOException;
    byte[] convertJsonToXml(byte[] jsonBytes) throws IOException;
}
