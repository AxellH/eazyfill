package com.eazyfill.extractor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class SumTotalExtractor {
	
	private static Pattern PATTERN_TOTAL = Pattern.compile("(total (gbp)|total)(.*)$", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE),
			PATTERN_DIGITS_WITH_COMMAS_AND_DOTS = Pattern.compile("([.,\\d]{1,})");

	public static List<String> findMatches(final String text) {
		return findMatches(text, PATTERN_TOTAL);
	}		

	private static List<String> findMatches(final String text, final Pattern pattern) {
		List<String> foundMatches = new ArrayList<String>();
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			String match = matcher.group();
			foundMatches.add(match);
		}
		return foundMatches;
	}

	public static List<String> filterInContainingNumber(final List<String> candidates) {
		List<String> ret = new ArrayList<String>();
		for (String candidate : candidates) {
			if (candidate.matches(".*\\d.*")) {
				ret.add(candidate);
			}
		}
		return ret;
	}

	public static Double getValue(final String searchString) {
		Matcher totalAsNumberMatcher = PATTERN_DIGITS_WITH_COMMAS_AND_DOTS.matcher(searchString);
		while (totalAsNumberMatcher.find()) {
			String totalAsString = totalAsNumberMatcher.group();
			totalAsString = totalAsString.replaceAll(",", ".");
			int dotCount = StringUtils.countMatches(totalAsString, ".");
			if (dotCount < 2) {
				Double totalAsDouble = Double.parseDouble(totalAsString);
				return totalAsDouble;
			}
		}
		return null;
	}

}
