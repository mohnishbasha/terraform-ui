package com.glitterlabs.terraformui.util;

import org.apache.commons.text.RandomStringGenerator;

/**
 * The Class ResourceNameGenerator.
 */
public class ResourceNameGenerator {

	/** The Constant generator. */
	private static final RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();

	/**
	 * Generate token.
	 *
	 * @return the token string
	 */
	public static String generateName() {
		return generator.generate(15);
	}

	public static String generateRandom() {
		return generator.generate(5);
	}

}
