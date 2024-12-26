package com.app.xm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.app.xm.mapper")
public class XmApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmApplication.class, args);
	}

}
