package com.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.seckill.entity.Seckill;

public interface SeckillMapper {

	int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

	Seckill queryById(long seckillId);

	List<Seckill> queryAll();

}
