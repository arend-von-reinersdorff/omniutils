package org.omnifaces.utils.security;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MessageDigests {

	private MessageDigests() {
	}

	/**
	 * Calculate a message digest over a given string using the specified algorithm.
	 *
	 * This method will use {@link java.nio.charset.StandardCharsets#UTF_8 UTF_8} encoding.
	 *
	 * @param message
	 *            the message to calculate the digest over
	 * @param algorithm
	 *            the name of the algorithm
	 * @return a byte array containing the message digest
	 * @throws UncheckedNoSuchAlgorithmException
	 *             if no implementation of the given algorithm could be found
	 */
	public static byte[] digest(String message, String algorithm) throws UncheckedNoSuchAlgorithmException {
		return digest(message, UTF_8, algorithm);
	}

	public static byte[] digest(String message, Charset charset, String algorithm) throws UncheckedNoSuchAlgorithmException {
		return digest(message.getBytes(UTF_8), algorithm);
	}

	public static byte[] digest(String message, byte[] salt, String algorithm) throws UncheckedNoSuchAlgorithmException {
		return digest(message, UTF_8, salt, algorithm);
	}

	public static byte[] digest(String message, Charset charset, byte[] salt, String algorithm) throws UncheckedNoSuchAlgorithmException {
		return digest(message.getBytes(charset), salt, algorithm);
	}

	public static byte[] digest(byte[] message, String algorithm) throws UncheckedNoSuchAlgorithmException {
		try {
			return MessageDigest.getInstance(algorithm).digest(message);
		}
		catch (NoSuchAlgorithmException e) {
			throw new UncheckedNoSuchAlgorithmException(e);
		}
	}

	public static byte[] digest(byte[] message, byte[] salt, String algorithm) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

			messageDigest.update(salt);

			return messageDigest.digest(message);
		}
		catch (NoSuchAlgorithmException e) {
			throw new UncheckedNoSuchAlgorithmException(e);
		}
	}
}