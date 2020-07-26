package com.seckill.excption;

/**
 * 重复秒杀异常
 * 
 * @author lenove
 *
 */
@SuppressWarnings("serial")
public class RepeatKillException extends SeckillExcption {

	public RepeatKillException(String message) {
		super(message);
	}

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}

}
