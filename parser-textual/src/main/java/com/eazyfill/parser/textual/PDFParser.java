package com.eazyfill.parser.textual;

import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFParser {
	
	private final PDDocument document;
	private String text;
	
	public PDFParser(final InputStream pdfContentStream) throws IOException {
		PDFTextStripper pdfStripper = new PDFTextStripper();
		document = PDDocument.load(pdfContentStream);
		this.text = pdfStripper.getText(document);
		close();
	}
	
	private void close() {
		try {
			document.close();
		} catch (IOException ignored) {
		}
	}
	
	public String getText() {
		return text;
	}
	
}
