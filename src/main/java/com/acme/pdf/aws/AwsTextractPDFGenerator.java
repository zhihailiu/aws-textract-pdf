package com.acme.pdf.aws;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.textract.AmazonTextract;
import com.amazonaws.services.textract.AmazonTextractClientBuilder;
import com.amazonaws.services.textract.model.Block;
import com.amazonaws.services.textract.model.BoundingBox;
import com.amazonaws.services.textract.model.DetectDocumentTextRequest;
import com.amazonaws.services.textract.model.DetectDocumentTextResult;
import com.amazonaws.services.textract.model.Document;

public class AwsTextractPDFGenerator {

	private static final Logger logger = LoggerFactory.getLogger(AwsTextractPDFGenerator.class);

	public void image2PDF(InputStream in, OutputStream out, ImageType imageType) throws IOException {
		logger.debug("image2PDF, type={}", imageType.name());

		byte[] imageBytes = IOUtils.toByteArray(in);

		// Extract text
		List<TextLine> lines = extractText(ByteBuffer.wrap(imageBytes));

		// Get Image
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));

		// Create new pdf document
		PDFDocument pdfDocument = new PDFDocument();

		// Add page with text layer and image in the pdf document
		pdfDocument.addPage(image, imageType, lines);

		// Save PDF
		pdfDocument.save(out);
		pdfDocument.close();
	}

	private List<TextLine> extractText(ByteBuffer imageBytes) {

		AmazonTextract client = AmazonTextractClientBuilder.defaultClient();

		DetectDocumentTextRequest request = new DetectDocumentTextRequest()
				.withDocument(new Document().withBytes(imageBytes));

		DetectDocumentTextResult result = client.detectDocumentText(request);

		List<TextLine> lines = new ArrayList<TextLine>();
		List<Block> blocks = result.getBlocks();
		BoundingBox boundingBox = null;
		for (Block block : blocks) {
			if ((block.getBlockType()).equals("LINE")) {
				boundingBox = block.getGeometry().getBoundingBox();
				lines.add(new TextLine(boundingBox.getLeft(), boundingBox.getTop(), boundingBox.getWidth(),
						boundingBox.getHeight(), block.getText()));
			}
		}

		return lines;
	}

}
