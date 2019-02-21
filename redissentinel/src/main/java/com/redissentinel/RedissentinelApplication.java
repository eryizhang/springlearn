package com.redissentinel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class RedissentinelApplication implements CommandLineRunner {

	@Autowired
	RedisTemplate redisTemplate;
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =SpringApplication.run(RedissentinelApplication.class, args);

		RedisTemplate redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");
		redisTemplate.opsForValue().set("tttttt", "tttttt");
	}

	@Override
	public void run(String... args) throws Exception {
		//String value=(String)redisTemplate.opsForValue().get("main1");
		//System.out.println(value);
		//redisTemplate.opsForValue().set("redit","redit");
	}
}

