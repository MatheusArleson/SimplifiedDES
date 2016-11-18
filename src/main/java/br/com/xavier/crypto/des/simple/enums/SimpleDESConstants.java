package br.com.xavier.crypto.des.simple.enums;

import java.util.Objects;

import br.com.xavier.crypto.des.simple.interfaces.ValueHolder;

public enum SimpleDESConstants implements ValueHolder<Integer>{
	
	//XXX ENUM MEMBERS
	BLOCK_BITS_SIZE(12),
	BLOCK_PARTITION_SIZE(6),
	KEY_BITS_SIZE(9),
	MINIMUN_CRYPTO_ROUNDS(2);
	
	//XXX PROPERTIES
	private final int value;
	
	//XXX CONSTRUCTOR
	private SimpleDESConstants(int value) {
		this.value = Objects.requireNonNull(value);
	}
	
	//XXX OVERRIDE METHODS
	public Integer getValue() {
		return value;
	}

}
