package br.com.xavier.crypto.des.simple.impl;

import org.apache.commons.codec.binary.BinaryCodec;

public final class ByteArrayUtils {

	// XXX CONSTRUCTOR
	private ByteArrayUtils() {
	}

	// XXX STATIC METHODS
	public static byte[] rotateLeft(byte[] rawBytes, int startIndex, int positions) {
		String binaryString = BinaryCodec.toAsciiString(rawBytes);
		binaryString = binaryString.substring(startIndex);

		String shifted = null;
		if (positions == 0) {
			shifted = binaryString;
		} else {
			while (positions > 0) {
				shifted = binaryString.substring(1, binaryString.length()) + binaryString.charAt(0);
				positions--;
			}
		}

		byte[] ShiftedBytes = BinaryCodec.fromAscii(shifted.toCharArray());
		return ShiftedBytes;
	}

	public static byte[] xor(byte[] oneArray, byte[] otherArray) {
		byte[] result = new byte[Math.min(oneArray.length, otherArray.length)];

		for (int i = 0; i < result.length; i++) {
			result[i] = (byte) (((int) oneArray[i]) ^ ((int) otherArray[i]));
		}

		return result;
	}
}
