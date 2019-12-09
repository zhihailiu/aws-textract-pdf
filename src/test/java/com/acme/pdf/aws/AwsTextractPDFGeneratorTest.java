package com.acme.pdf.aws;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AwsTextractPDFGeneratorTest {

	private AwsTextractPDFGenerator service;

	private String png;
	private String pdf;

	@BeforeEach
	public void setup() {
		service = new AwsTextractPDFGenerator();
		png = "C:\\Users\\ZhihaiLiu\\dev\\projects\\aws-textract-pdf\\src\\test\\resources\\TeraThink.png";
		pdf = "C:\\Users\\ZhihaiLiu\\dev\\projects\\aws-textract-pdf\\src\\test\\resources\\TeraThink.pdf";
	}

	@Test
	public void testImage2Pdf() throws IOException {
		try (InputStream in = new FileInputStream(png); 
				OutputStream out = new FileOutputStream(pdf)) {
			service.image2PDF(in, out, ImageType.PNG);
		}
	}

}
