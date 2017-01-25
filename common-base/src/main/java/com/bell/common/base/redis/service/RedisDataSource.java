package com.bell.common.base.redis.service;

import redis.clients.jedis.ShardedJedis;

public interface RedisDataSource {
	
	public abstract ShardedJedis getRedisClient();

	public void returnResource(ShardedJedis shardedJedis);

	public void returnResource(ShardedJedis shardedJedis, boolean broken);
}
