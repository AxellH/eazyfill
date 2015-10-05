package com.eazyfill.extractor;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import com.eazyfill.parser.textual.PDFParser;

public class TestExtractingSumTotal {

	@Test
	public void testFindingSumTotalFromSampleInvoice() throws IOException {
		InputStream contentStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("invoice_example.pdf");
		PDFParser paringResult = new PDFParser(contentStream);
		String textOnSampleInvoice = paringResult.getText();
		assertNotNull(textOnSampleInvoice);
		assertTrue(textOnSampleInvoice.length() > 0);
		List<String> sumTotalCandidates = SumTotalExtractor.findMatches(textOnSampleInvoice);
		sumTotalCandidates = SumTotalExtractor.filterInContainingNumber(sumTotalCandidates); // filter out candidates without numeric content
		assertTrue(sumTotalCandidates.size() > 0);
		Double totalValue = SumTotalExtractor.getValue(sumTotalCandidates.get(0));
		assertEquals((Double)120.0, totalValue);
	}

}
