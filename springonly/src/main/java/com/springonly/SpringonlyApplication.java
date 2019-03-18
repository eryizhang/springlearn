package com.springonly;

import com.springonly.beanmade.annotation.beanmodel.SuperMarket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableCaching
@EnableWebMvc
public class SpringonlyApplication implements CommandLineRunner {

	private static Log logSpringonlyApplication= LogFactory.getLog(SpringonlyApplication.class);
	public static void main(String[] args) {

		ConfigurableApplicationContext ctx=SpringApplication.run(SpringonlyApplication.class, args);
		SuperMarket superMarket=ctx.getBean(SuperMarket.class);
		logSpringonlyApplication.info("***************"+superMarket.toString()+"***************");

	}

	@Override
	public void run(String... args) throws Exception {

	}
}

