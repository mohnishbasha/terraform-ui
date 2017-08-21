package com.glitterlabs.terraformui.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class PollingMessage {

	private static final Map<String, MessageWrapper> messages = new HashMap<>();

	public static String getNextMessage(final String pollId) {
		final MessageWrapper messageWrapper = messages.get(pollId);
		if (messageWrapper == null) {
			return null;
		}
		final String message = messageWrapper.message;
		if (StringUtils.equalsIgnoreCase(message, "Complete")) {
			messageWrapper.message = "";
		}
		return message;
	}

	public static void setNextMessage(final String pollId, final String message) {
		MessageWrapper messageWrapper = messages.get(pollId);
		if (messageWrapper == null) {
			messageWrapper = new MessageWrapper();
		}
		messageWrapper.message = message;
		messages.put(pollId, messageWrapper);
	}

	private static class MessageWrapper {
		private String message = "";

	}
}
