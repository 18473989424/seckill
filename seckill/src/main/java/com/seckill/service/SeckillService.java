package com.seckill.service;

import java.util.List;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.excption.RepeatKillException;
import com.seckill.excption.SeckillCloseExcption;
import com.seckill.excption.SeckillExcption;

public interface SeckillService {

	List<Seckill> queryAll();

	Seckill queryById(long seckillId);

	Exposer exposerSeckillUrl(long seckillId);

	SeckillExecution executeSeckill(long seckill, long userPhone, String md5)
			throws SeckillExcption, SeckillCloseExcption, RepeatKillException;

}
