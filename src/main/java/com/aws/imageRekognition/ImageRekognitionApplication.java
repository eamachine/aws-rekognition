package com.aws.imageRekognition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class ImageRekognitionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageRekognitionApplication.class, args);
	}

	@Bean(name = "asyncExecutor")
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(20);
		executor.setMaxPoolSize(1000);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("ImageRekognition-");
		executor.initialize();
		return executor;
	}
}
