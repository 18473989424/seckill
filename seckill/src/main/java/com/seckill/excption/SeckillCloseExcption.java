package com.seckill.excption;

/**
 * 秒杀关闭异常
 * 
 * @author lenove
 *
 */
@SuppressWarnings("serial")
public class SeckillCloseExcption extends SeckillExcption {

	public SeckillCloseExcption(String message) {
		super(message);
	}

	public SeckillCloseExcption(String message, Throwable cause) {
		super(message, cause);
	}
}
