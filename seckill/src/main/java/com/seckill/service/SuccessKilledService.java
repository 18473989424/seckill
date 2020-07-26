package com.seckill.service;

import com.seckill.entity.SuccessKilled;

public interface SuccessKilledService {

	int insertSuccessKilled(long seckillId, long userPhone);

	SuccessKilled queryByIdWithSeckill(long seckillId, long userPhone);

}
