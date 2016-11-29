package eu.pawelniewiadomski.java.spring.micropassmanager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "eu.pawelniewiadomski.java.spring.micropassmanager.controllers")
public class MicroPassManagerWebAppConfiguration extends WebMvcConfigurerAdapter {
  
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
      configurer.enable();
  }

  @Bean
  public InternalResourceViewResolver jspViewResolver() {
      InternalResourceViewResolver bean = new InternalResourceViewResolver();
      bean.setPrefix("/WEB-INF/views/");
      bean.setSuffix(".jsp");
      return bean;
  }


  @Bean(name = "messageSource")
  public ReloadableResourceBundleMessageSource getMessageSource() {
      ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
      resource.setBasename("classpath:messages");
      resource.setDefaultEncoding("UTF-8");
      return resource;
  }
}
