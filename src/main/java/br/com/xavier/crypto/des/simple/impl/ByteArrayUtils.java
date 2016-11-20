package br.com.xavier.crypto.des.simple.impl;

import java.util.BitSet;

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

	public static BitSet getBits(byte[] bytes, int startPosition, int finalPosition){
		if(startPosition < 0){
			throw new IllegalArgumentException("start position must be equal or greather than zero.");
		}
		
		if(finalPosition > bytes.length * 8){
			throw new IllegalArgumentException("final position must be less or equal than byte size.");
		}
		
		BitSet bs = BitSet.valueOf(bytes);
		BitSet subBs = new BitSet();
		
		for (int i = startPosition; i < finalPosition; i++) {
			if(bs.get(i)){
				subBs.set(i);
			} else {
				subBs.clear(i);
			}
		}
		
		return subBs;
	}
		
}
