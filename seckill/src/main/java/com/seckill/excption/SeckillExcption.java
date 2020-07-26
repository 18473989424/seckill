package com.seckill.excption;

@SuppressWarnings("serial")
public class SeckillExcption extends RuntimeException {

	public SeckillExcption(String message) {
		super(message);
	}

	public SeckillExcption(String message, Throwable cause) {
		super(message, cause);
	}

}
