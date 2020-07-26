package com.seckill.dto;

import com.seckill.emuns.SeckillStateEnum;
import com.seckill.entity.SuccessKilled;

/*
 *封杀秒杀执行后的结果
 *
 */

public class SeckillExecution {
	//
	private long seckillId;
	// 秒杀后的执行状态
	private int state;
	// 状态简介
	private String stateinfo;

	private SuccessKilled successKilled;

	public SeckillExecution(long seckillId, SeckillStateEnum stateEnum) {
		super();
		this.seckillId = seckillId;
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateinfo();
	}

	public SeckillExecution(long seckillId, SeckillStateEnum stateEnum, SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = stateEnum.getState();
		this.stateinfo = stateEnum.getStateinfo();
		this.successKilled = successKilled;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateinfo() {
		return stateinfo;
	}

	public void setStateinfo(String stateinfo) {
		this.stateinfo = stateinfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}

	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state + ", stateinfo=" + stateinfo
				+ ", successKilled=" + successKilled + "]";
	}
}
