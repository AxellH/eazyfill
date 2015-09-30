package com.eazyfill.parser.textual;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class TestReadingPDFFile {

	@Test
	public void testConvertingPDFFileIntoText() throws IOException {
		InputStream contentStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("pdf-sample.pdf");
		PDFParser paringResult = new PDFParser(contentStream);
		assertNotNull(paringResult.getText());
		assertTrue(paringResult.getText().startsWith("Adobe Acrobat PDF Files"));
	}

}
