package org.activiti.cloud.services.process.model.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableAutoConfiguration
@ComponentScan("org.activiti")
@EnableJpaRepositories("org.activiti.cloud.services.process.model.jpa")
@EntityScan("org.activiti.cloud.services.process.model.core.model")
@Inherited
public @interface EnableActivitiProcessModel {

}