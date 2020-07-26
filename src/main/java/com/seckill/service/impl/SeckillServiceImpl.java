package com.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.seckill.dao.RedisDao;
import com.seckill.dao.SeckillMapper;
import com.seckill.dao.SuccessKilledMapper;
import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.emuns.SeckillStateEnum;
import com.seckill.entity.Seckill;
import com.seckill.entity.SuccessKilled;
import com.seckill.excption.RepeatKillException;
import com.seckill.excption.SeckillCloseExcption;
import com.seckill.excption.SeckillExcption;
import com.seckill.service.SeckillService;

import ch.qos.logback.classic.Logger;

@Service
public class SeckillServiceImpl implements SeckillService {

	private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SeckillMapper seckillMapper;

	@Autowired
	private SuccessKilledMapper successKilledMapper;

	@Autowired
	private RedisDao redisDao;

	// 盐值
	private final String slat = "sad561/8*21*1e43&&^%*(5444wqe8324e54dad";

	public List<Seckill> queryAll() {
		return seckillMapper.queryAll();
	}

	public Seckill queryById(long seckillId) {
		/*
		 * 秒杀开启是输出接口地址， 否则输出系统时间和秒杀时间
		 */
		return seckillMapper.queryById(seckillId);
	}

	public Exposer exposerSeckillUrl(long seckillId) {
		// 缓存优化
		// 1：查询缓存
		Seckill seckill = redisDao.getSeckill(seckillId);
		if (seckill == null) {
			seckill = seckillMapper.queryById(seckillId);
			if (seckill == null) {
				return new Exposer(false, seckillId);
			} else {
				redisDao.putSeckill(seckill);
			}
		}

		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		// 系统当前时间
		Date nowTime = new Date();
		if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}

		return new Exposer(true, getMD5(seckillId), seckillId);
	}

	// 执行秒杀操作
	@Transactional
	/*
	 * 使用申明式事物的优点： 1.开发团队达成一致，明确标注事物的编程风格
	 * 2.保证事物方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP或者剥离事物方法外部 3.不要所有方法需要事物 (non-Javadoc)
	 * 
	 * @see com.seckill.service.SeckillService#executeSeckill(long, long,
	 * java.lang.String)
	 */
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillExcption, SeckillCloseExcption, RepeatKillException {
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillExcption("seckill data rewrite");
		}
		Date nowTime = new Date();
		try {
			// 记录秒杀行为
			int insertCount = successKilledMapper.insertSuccessKilled(seckillId, userPhone);

			// 唯一：seckill,usephone
			if (insertCount <= 0) {
				// 重复秒杀
				throw new RepeatKillException("seckill repeated(重复秒杀)");
			} else {
				int updateCpount = seckillMapper.reduceNumber(seckillId, nowTime);
				if (updateCpount <= 0) {
					// 没有减库存,秒杀结束
					throw new SeckillCloseExcption("seckill is closed(秒杀结束)");
				} else {
					// 秒杀成功
					SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
				}
			}

		} catch (SeckillCloseExcption e1) {
			throw e1;
		} catch (RepeatKillException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// 所有编译期异常，转化为运行期异常
			throw new SeckillExcption("seckill inner error:" + e.getMessage());
		}
	}

	private String getMD5(long seckillId) {
		String base = seckillId + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

}
