package com.acme.pdf.controlller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.acme.pdf.aws.AwsTextractPDFGenerator;
import com.acme.pdf.aws.ImageType;

@RestController
public class AwsTextractPDFController {

	private final static Logger logger = LoggerFactory.getLogger(AwsTextractPDFController.class);

	private AwsTextractPDFGenerator pdfGenerator = new AwsTextractPDFGenerator();

	@ResponseBody
	@RequestMapping(value = "/png", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] png2pdf(@RequestParam("file") MultipartFile file) throws IOException {

		logger.debug("png2pdf");

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		pdfGenerator.image2PDF(file.getInputStream(), out, ImageType.PNG);

		return out.toByteArray();
	}

}
