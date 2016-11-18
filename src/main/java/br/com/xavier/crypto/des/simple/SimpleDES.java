package br.com.xavier.crypto.des.simple;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;

import org.apache.commons.io.IOUtils;

public class SimpleDES {
	
	//XXX CONSTANTS
	private static int BLOCK_BITS_SIZE = 12;
	private static int BLOCK_PARTITION_SIZE = BLOCK_BITS_SIZE / 2;
	private static int KEY_BITS_SIZE = 9;
	private static int MINIMUN_CRYPTO_ROUNDS = 2;
	
	//XXX CONSTRUCTOR
	private SimpleDES(){}
	
	//XXX ENCRYPT METHODS
	public byte[] apply(byte[] data, byte[] key, int rounds, boolean isEncrypt) throws IOException{
		validate(data, key, rounds);
		
		ByteArrayOutputStream baos = null; 
		try {
			baos = new ByteArrayOutputStream(data.length);
			ByteBuffer dataBuffer = ByteBuffer.wrap(data);
			
			while(dataBuffer.hasRemaining()){
				byte[] dataBlock = new byte[BLOCK_BITS_SIZE];
				dataBuffer.get(dataBlock);
				
				byte[] bytes = processBlock(dataBlock, key, rounds, isEncrypt);
				baos.write(bytes);
				baos.flush();
			}
			
			return baos.toByteArray();
			
		} catch (IOException e) {
			throw e;
		} finally {
			if(baos != null){
				IOUtils.closeQuietly( baos );
			}
		}
	}

	//XXX VALIDATION METHODS
	private void validate(byte[] data, byte[] key, int rounds) {
		validateData(data);
		validateKey(key);
		validateRounds(rounds);
	}
	
	private void validateData(byte[] data) {
		Objects.requireNonNull(data);
		if(data.length % BLOCK_BITS_SIZE != 0){
			throw new  IllegalArgumentException("Data bytes  length must be an multiple of " + BLOCK_BITS_SIZE);
		}
	}

	private void validateKey(byte[] key) {
		Objects.requireNonNull(key);
		if(key.length !=  KEY_BITS_SIZE){
			throw new  IllegalArgumentException("Key bytes length must be  " + KEY_BITS_SIZE);
		}
	}

	private void validateRounds(int rounds) {
		Objects.requireNonNull(rounds);
		if(rounds < MINIMUN_CRYPTO_ROUNDS){
			throw new  IllegalArgumentException("Rounds must be equal or greather than  " + MINIMUN_CRYPTO_ROUNDS);
		}
	}

	//XXX CRYPTO METHODS
	private byte[] processBlock(byte[] dataSlice, byte[] key, int rounds, boolean isEncrypt) {
		SimpleDESRoundInfo roundState = processInitialState(dataSlice, key, isEncrypt);
		
		int roundCount = 0;
		while(roundCount < rounds){
			byte[] derivedKey = deriveKey(key, roundCount, isEncrypt);
			processBlock(roundState, derivedKey);
		}
		
		return null;
	}

	private SimpleDESRoundInfo processInitialState(byte[] dataSlice, byte[] key,  boolean isEncrypt) {
		byte[] leftBytes =new byte[BLOCK_PARTITION_SIZE];
		byte[] rightBytes = new byte[BLOCK_PARTITION_SIZE];
		
		ByteBuffer buffer = ByteBuffer.wrap(dataSlice);
		buffer.get(leftBytes);
		buffer.get(rightBytes);
		
		SimpleDESRoundInfo roundInfo = new SimpleDESRoundInfo(leftBytes, rightBytes, 0, isEncrypt);
		return roundInfo;
	}
	
	private byte[] deriveKey(byte[] key, int roundCount, boolean isEncrypt) {
		
	}

	private SimpleDESRoundInfo processBlock(SimpleDESRoundInfo previousRound, byte[] derivedKey) {
		return null;
	}

}
