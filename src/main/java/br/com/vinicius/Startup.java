package br.com.vinicius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import br.com.vinicius.config.FileStoregeConfig;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties({
	FileStoregeConfig.class
})
@ComponentScan
public class Startup {
	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}
}
