package com.bosonit.block11uploaddownloadfiles;

import com.bosonit.block11uploaddownloadfiles.application.FileService;
import com.bosonit.block11uploaddownloadfiles.configuration.FileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Block11UploadDownloadFilesApplication {


	public static void main(String[] args) {
		SpringApplication.run(Block11UploadDownloadFilesApplication.class, args);
	}


	@Bean
	public CommandLineRunner configurePath(FileProperties fileProperties){
		return args -> {
			if (args.length > 0){
				String path = args[0];
				fileProperties.setLocation(path);
			}
		};
	}
}
