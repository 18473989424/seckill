package com.seckill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seckill.dao.SuccessKilledMapper;
import com.seckill.entity.SuccessKilled;
import com.seckill.service.SuccessKilledService;

@Service
public class SuccessKilledServiceImpl implements SuccessKilledService {
	@Autowired
	SuccessKilledMapper mapper;

	public int insertSuccessKilled(long seckillId, long userPhone) {
		return 0;
	}

	public SuccessKilled queryByIdWithSeckill(long seckillId, long userPhone) {
		return null;
	}

}
