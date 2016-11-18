package br.com.xavier.crypto.des.simple.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

final class SimpleDESRoundInfo implements Serializable, Comparable<SimpleDESRoundInfo> {
	
	private static final long serialVersionUID = 270813157971429754L;
	
	//XXX PROPERTIES
	private final int blockNumber;
	private final byte[] blockLeftBytes;
	private final byte[] blockRightBytes;
	private final Integer roundNumber; 
	private final Boolean isEncrypt;
	
	//XXX CONSTRUCTOR
	protected SimpleDESRoundInfo(int blockNumber, byte[] blockLeftBytes, byte[] blockRightBytes, int roundNumber, boolean isEncrypt) {
		super();
		this.blockNumber = Objects.requireNonNull(blockNumber);
		this.blockLeftBytes = Objects.requireNonNull( blockLeftBytes );
		this.blockRightBytes = Objects.requireNonNull( blockRightBytes );
		this.roundNumber = Objects.requireNonNull( roundNumber );
		this.isEncrypt = Objects.requireNonNull( isEncrypt );
	}

	//XXX OVERRIDE METHODS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isEncrypt ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(blockLeftBytes);
		result = prime * result + Arrays.hashCode(blockRightBytes);
		result = prime * result + roundNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleDESRoundInfo other = (SimpleDESRoundInfo) obj;
		if (isEncrypt != other.isEncrypt)
			return false;
		if (roundNumber != other.roundNumber)
			return false;
		if (!Arrays.equals(blockLeftBytes, other.blockLeftBytes))
			return false;
		if (!Arrays.equals(blockRightBytes, other.blockRightBytes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CryptoRoundInfo [" 
				+ "blockLeftBytes=" + Arrays.toString(blockLeftBytes) 
				+ ", blockRightBytes=" + Arrays.toString(blockRightBytes) 
				+ ", roundNumber=" + roundNumber
				+ ", isEncrypt=" + isEncrypt 
		+ "]";
	}

	public int compareTo(SimpleDESRoundInfo other) {
		return getRoundNumber().compareTo(other.getRoundNumber());
	}
	
	//XXX GETTERS
	public int getBlockNumber() {
		return blockNumber;
	}
	
	public byte[] getBlockLeftBytes() {
		return blockLeftBytes.clone();
	}

	public byte[] getBlockRightBytes() {
		return blockRightBytes.clone();
	}

	public Integer getRoundNumber() {
		return roundNumber;
	}

	public Boolean isEncrypt() {
		return isEncrypt;
	}
	
}
