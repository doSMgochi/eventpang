package com.callor.eventpang.utils;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

public class XssSanitizer {

	public static String sanitize(String input) {
		PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS).and(Sanitizers.IMAGES);

		String sanitized = policy.sanitize(input);
		return sanitized;
	}
}