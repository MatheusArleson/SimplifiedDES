package br.com.xavier.crypto.des.simple;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class SimpleDESRoundInfo implements Serializable, Comparable<SimpleDESRoundInfo> {
	
	private static final long serialVersionUID = 270813157971429754L;
	
	//XXX PROPERTIES
	private final byte[] leftBytes;
	private final byte[] rightBytes;
	private final Integer roundNumber; 
	private final Boolean isEncrypt;
	
	//XXX CONSTRUCTOR
	public SimpleDESRoundInfo(byte[] leftBytes, byte[] rightBytes, int roundNumber, boolean isEncrypt) {
		super();
		this.leftBytes = Objects.requireNonNull( leftBytes );
		this.rightBytes = Objects.requireNonNull( rightBytes );
		this.roundNumber = Objects.requireNonNull( roundNumber );
		this.isEncrypt = Objects.requireNonNull( isEncrypt );
	}

	//XXX OVERRIDE METHODS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isEncrypt ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(leftBytes);
		result = prime * result + Arrays.hashCode(rightBytes);
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
		if (!Arrays.equals(leftBytes, other.leftBytes))
			return false;
		if (!Arrays.equals(rightBytes, other.rightBytes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CryptoRoundInfo [" 
				+ "leftBytes=" + Arrays.toString(leftBytes) 
				+ ", rightBytes=" + Arrays.toString(rightBytes) 
				+ ", roundNumber=" + roundNumber
				+ ", isEncrypt=" + isEncrypt 
		+ "]";
	}

	public int compareTo(SimpleDESRoundInfo other) {
		return getRoundNumber().compareTo(other.getRoundNumber());
	}
	
	//XXX GETTERS
	public byte[] getLeftBytes() {
		return leftBytes;
	}

	public byte[] getRightBytes() {
		return rightBytes;
	}

	public Integer getRoundNumber() {
		return roundNumber;
	}

	public Boolean isEncrypt() {
		return isEncrypt;
	}
	
}
