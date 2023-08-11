package com.ejercicio2.block6personcontrollers.config;

import com.ejercicio2.block6personcontrollers.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {

    @Bean
    public Person bean1(){
        return new Person("bean1",24,"Jaén");
    }

    @Bean
    public Person bean2(){
        return new Person("bean2",20,"Málaga");
    }

    @Bean
    public  Person bean3(){
        return new Person("bean3", 28, "Sevilla");
    }
}
