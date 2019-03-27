package com.seckill.test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.dao.SeckillMapper;
import com.seckill.entity.Seckill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-mybatis.xml" })
public class SeckillMapperTest {

	@Resource
	private SeckillMapper sm;

	@Test
	public void testReduceNumber() {
		int i = sm.reduceNumber(1001, new Date());
		System.out.println(i);
	}

	@Test
	public void testQueryById() {
		long seckillId = 1000;
		Seckill seckill = sm.queryById(seckillId);
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}

	@Test
	public void testQueryAll() {
		List<Seckill> seckills = sm.queryAll();
		for (Seckill seckill : seckills) {
			System.out.println(seckill);
		}
	}
}
