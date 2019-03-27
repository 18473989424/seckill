package com.seckill.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.dao.SuccessKilledMapper;
import com.seckill.entity.SuccessKilled;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-mybatis.xml" })
public class SuccessKilledMapperTest {

	@Resource
	SuccessKilledMapper skm;

	@Test
	public void testInsertSuccessKilled() {
		int i = skm.insertSuccessKilled(1001L, 18473911011L);
		System.out.println(i);
	}

	@Test
	public void testQueryByIdWithSeckill() {
		SuccessKilled successKilled = skm.queryByIdWithSeckill(1000L, 18473911011L);
		System.out.println(successKilled);
	}

}
