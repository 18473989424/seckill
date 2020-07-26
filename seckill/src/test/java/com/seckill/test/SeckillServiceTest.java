package com.seckill.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.service.SeckillService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-mybatis.xml", "classpath:spring-service.xml" })
public class SeckillServiceTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SeckillService seckillService;

	@Test
	public void testQueryAll() {
		List<Seckill> list = seckillService.queryAll();
		logger.info("list={}", list);
	}

	@Test
	public void testQueryById() {
		long id = 1000;
		Seckill seckill = seckillService.queryById(id);
		logger.info("seckill={}", seckill);
	}

	@Test
	public void testExposerSeckillUrl() {
		long id = 1000;
		Exposer exposer = seckillService.exposerSeckillUrl(id);
		logger.info("exposer={}", exposer);
		if (exposer.isExposed()) {
			try {
				long phone = 18473912523L;
				String md5 = "8e8a4e463353ea182ac28cae6076a4c";
				SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
				logger.info("result={}", execution);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			logger.info("exposer={}", exposer);
		}
	}

}
