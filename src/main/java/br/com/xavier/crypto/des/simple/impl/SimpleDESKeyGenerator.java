package br.com.xavier.crypto.des.simple.impl;

final class SimpleDESKeyGenerator {
	
	//XXX CONSTRUCTOR
	protected SimpleDESKeyGenerator(){}
	
	//XXX STATIC METHODS
	public byte[] deriveKey(byte[] key, int roundCount, boolean isEncrypt) {
		return rotateLeft(key, 7, roundCount) ;
	}
	
	private static byte[] rotateLeft(byte[] rawBytes, int startIndex, int positions) {
		return ByteArrayUtils.rotateLeft(rawBytes, startIndex, positions);
	}

}
