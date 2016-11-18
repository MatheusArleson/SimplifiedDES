package br.com.xavier.crypto.des.simple.impl;

import java.util.Objects;

import br.com.xavier.crypto.des.simple.enums.SimpleDESConstants;

final class SimpleDESValidator {
	
	//XXX CONSTRUCTOR
	protected SimpleDESValidator(){}
	
	//XXX VALIDATION METHODS
	public void validate(byte[] data, byte[] key, int rounds) {
		validateData(data);
		validateKey(key);
		validateRounds(rounds);
	}
	
	private void validateData(byte[] data) {
		Objects.requireNonNull(data);
		if(data.length % SimpleDESConstants.BLOCK_BITS_SIZE.getValue() != 0){
			throw new IllegalArgumentException("Data bytes  length must be an multiple of " + SimpleDESConstants.BLOCK_BITS_SIZE.getValue());
		}
	}

	private void validateKey(byte[] key) {
		Objects.requireNonNull(key);
		if(key.length !=  SimpleDESConstants.KEY_BITS_SIZE.getValue()){
			throw new IllegalArgumentException("Key bytes length must be  " + SimpleDESConstants.KEY_BITS_SIZE.getValue());
		}
	}

	private void validateRounds(int rounds) {
		Objects.requireNonNull(rounds);
		if(rounds < SimpleDESConstants.MINIMUN_CRYPTO_ROUNDS.getValue()){
			throw new IllegalArgumentException("Rounds must be equal or greather than  " + SimpleDESConstants.MINIMUN_CRYPTO_ROUNDS.getValue());
		}
	}


}
