package com.app.netflixdemoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class NetflixDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixDemoApplication.class, args);
	}

}
