package br.com.xavier.crypto.des.simple.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Queue;

import org.apache.commons.io.IOUtils;

import br.com.xavier.crypto.des.simple.enums.SimpleDESConstants;

public final class SimpleDES {
	
	//XXX PROPERTIES
	private SimpleDESValidator validator;
	private SimpleDESKeyGenerator keyGenerator;
	private Queue<SimpleDESRoundInfo> roundsInfoQueue;
	
	//XXX CONSTRUCTOR
	private SimpleDES(){}
	
	//XXX ENCRYPT METHODS
	public byte[] apply(byte[] data, byte[] key, int rounds, boolean isEncrypt) throws IOException{
		validate(data, key, rounds);
		
		ByteArrayOutputStream baos = null; 
		try {
			baos = new ByteArrayOutputStream(data.length);
			ByteBuffer dataBuffer = ByteBuffer.wrap(data);
			
			int blockCount = 0;
			while(dataBuffer.hasRemaining()){
				byte[] dataBlock = new byte[SimpleDESConstants.BLOCK_BITS_SIZE.getValue()];
				dataBuffer.get(dataBlock);
				
				byte[] bytes = processBlock(blockCount, dataBlock, key, rounds, isEncrypt);
				blockCount++;
				
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
	
	private void validate(byte[] data, byte[] key, int rounds) {
		validator.validate(data, key, rounds);
	}

	//XXX CRYPTO METHODS
	private byte[] processBlock(int blockNumber, byte[] dataSlice, byte[] key, int rounds, boolean isEncrypt) {
		SimpleDESRoundInfo previousState = processInitialState(blockNumber, dataSlice, key, isEncrypt);
		roundsInfoQueue.add(previousState);
		
		int roundCount = 0;
		while(roundCount < rounds-1){
			previousState = processRound(blockNumber, key, roundCount, isEncrypt, previousState);
			roundsInfoQueue.add(previousState);
			roundCount++;
		}
		
		previousState = processFinalRound(key, previousState, isEncrypt);
		roundsInfoQueue.add(previousState);
		
		return null;
	}
	
	private SimpleDESRoundInfo processInitialState(int blockNumber, byte[] dataSlice, byte[] key,  boolean isEncrypt) {
		byte[] blockLeftBytes = new byte[SimpleDESConstants.BLOCK_PARTITION_SIZE.getValue()];
		byte[] blockRightBytes = new byte[SimpleDESConstants.BLOCK_PARTITION_SIZE.getValue()];
		
		ByteBuffer buffer = ByteBuffer.wrap(dataSlice);
		buffer.get(blockLeftBytes);
		buffer.get(blockRightBytes);
		
		SimpleDESRoundInfo roundInfo = new SimpleDESRoundInfo(blockNumber, blockLeftBytes, blockRightBytes, 0, isEncrypt);
		return roundInfo;
	}

	private SimpleDESRoundInfo processRound(int blockNumber, byte[] key, int roundNumber, boolean isEncrypt, SimpleDESRoundInfo previousRoundState) {
		byte[] derivedKey = deriveKey(key, roundNumber, isEncrypt);
		return processBlock(blockNumber, roundNumber, derivedKey, isEncrypt, previousRoundState);
	}
	
	private byte[] deriveKey(byte[] key, int roundCount, boolean isEncrypt) {
		return keyGenerator.deriveKey(key, roundCount, isEncrypt);
	}

	private SimpleDESRoundInfo processBlock(int blockNumber, int roundNumber, byte[] derivedKey, boolean isEncrypt, SimpleDESRoundInfo previousRound) {
		byte[] newLeftBlockBytes = previousRound.getBlockRightBytes();
		byte[] newRightBlockBytes = processNewRightBlockBytes(previousRound, derivedKey);
		
		SimpleDESRoundInfo roundInfo = new SimpleDESRoundInfo(blockNumber, newLeftBlockBytes, newRightBlockBytes, roundNumber, isEncrypt);
		return roundInfo;
	}
	
	private byte[] processNewRightBlockBytes(SimpleDESRoundInfo previousRound, byte[] derivedKey) {
		byte[] feistelResult = feistel( previousRound.getBlockRightBytes(), derivedKey );
		return ByteArrayUtils.xor( previousRound.getBlockLeftBytes(), feistelResult );
	}

	private byte[] feistel(byte[] previousLeftBlockBytes, byte[] derivedKey) {
		return null;
	}

	private SimpleDESRoundInfo processFinalRound(byte[] key, SimpleDESRoundInfo roundState, boolean isEncrypt){
		return null;
	}
}
