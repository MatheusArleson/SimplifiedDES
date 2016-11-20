package br.com.xavier.crypto.des.simple.impl;

import java.util.BitSet;

final class FeistelFunction {

	public byte[]  apply(byte[] data, byte[] key, byte[][] substitutionBoxLeftBytes, byte[][] substitutionBoxRightBytes){
		byte[] expandedData = expand(data);
		byte[] xorResult = ByteArrayUtils.xor(expandedData, key);
		
		int length = xorResult.length;
		int middle = length / 2;
		
		BitSet firstBits = ByteArrayUtils.getBits(xorResult, 0, middle);
		BitSet lastBits = ByteArrayUtils.getBits(xorResult, middle, length);
		
		substitute(firstBits, substitutionBoxLeftBytes);
		
		
		return null;
	}

	private byte[] expand(byte[] data) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
