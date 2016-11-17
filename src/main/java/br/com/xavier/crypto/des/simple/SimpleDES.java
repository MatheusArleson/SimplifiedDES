package br.com.xavier.crypto.des.simple;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Objects;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.apache.commons.io.IOUtils;

public class SimpleDES {
	
	//XXX CONSTANTS
	private static int BLOCK_BITS_SIZE = 12;
	private static int KEY_BITS_SIZE = 9;
	private static int MINIMUN_CRYPTO_ROUNDS = 2;
	
	//XXX CONSTRUCTOR
	private SimpleDES(){}
	
	//XXX ENCRYPT METHODS
	public static byte[] apply(byte[] data, byte[] key, int rounds, boolean isEncrypt) throws IOException{
		validate(data, key, rounds);
		
		ByteArrayOutputStream baos = null; 
		try {
			baos = new ByteArrayOutputStream(data.length);
			ByteBuffer dataBuffer = ByteBuffer.wrap(data);
			
			while(dataBuffer.hasRemaining()){
				byte[] dataSlice = new byte[BLOCK_BITS_SIZE];
				dataBuffer.get(dataSlice);
				
				byte[] bytes = applyOnBlock(dataSlice, key, rounds, isEncrypt);
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
	private static void validate(byte[] data, byte[] key, int rounds) {
		validateData(data);
		validateKey(key);
		validateRounds(rounds);
	}
	
	private static void validateData(byte[] data) {
		Objects.requireNonNull(data);
		if(data.length % BLOCK_BITS_SIZE != 0){
			throw new  IllegalArgumentException("Data bytes  length must be an multiple of " + BLOCK_BITS_SIZE);
		}
	}

	private static void validateKey(byte[] key) {
		Objects.requireNonNull(key);
		if(key.length !=  KEY_BITS_SIZE){
			throw new  IllegalArgumentException("Key bytes length must be  " + KEY_BITS_SIZE);
		}
	}

	private static void validateRounds(int rounds) {
		Objects.requireNonNull(rounds);
		if(rounds < MINIMUN_CRYPTO_ROUNDS){
			throw new  IllegalArgumentException("Rounds must be equal or greather than  " + MINIMUN_CRYPTO_ROUNDS);
		}
	}

	//XXX CRYPTO METHODS
	private static byte[] applyOnBlock(byte[] dataSlice, byte[] key, int rounds, boolean isEncrypt) {
		// TODO Auto-generated method stub
		
		return null;
	}

}
