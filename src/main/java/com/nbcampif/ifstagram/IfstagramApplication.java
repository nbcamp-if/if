package com.nbcampif.ifstagram;

import javax.management.ValueExp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:db.properties", "classpath:jwt.properties"})
public class IfstagramApplication {

  public static void main(String[] args) {
    SpringApplication.run(IfstagramApplication.class, args);
  }

}
