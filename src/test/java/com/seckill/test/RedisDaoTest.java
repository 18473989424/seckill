package com.seckill.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.dao.RedisDao;
import com.seckill.dao.SeckillMapper;
import com.seckill.entity.Seckill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-mybatis.xml" })
public class RedisDaoTest {
	private long id = 1000;

	@Autowired
	RedisDao redisDao;

	@Autowired
	SeckillMapper seckillDao;

	@Test
	public void testRedisDao() {
		// get and put

		Seckill seckill = redisDao.getSeckill(id);
		if (seckill == null) {
			seckill = seckillDao.queryById(id);
			if (seckill != null) {
				String result = redisDao.putSeckill(seckill);
				System.out.println(result);
				seckill = redisDao.getSeckill(id);
			}
		}
		System.out.println(seckill);
	}

}
