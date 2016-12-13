package eu.pawelniewiadomski.java.spring.micropassmanager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;
 
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "eu.pawelniewiadomski.java.spring.micropassmanager.controllers")
public class MicroPassManagerWebAppConfiguration extends WebMvcConfigurerAdapter {
  
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/js/**").addResourceLocations("/js/");
      registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    //  registry.addResourceHandler("/**.html").addResourceLocations("/");
      
  }

  
//  @Override
//  public void addViewControllers(ViewControllerRegistry registry) {
//  //  registry.addViewController("/").setViewName("forward:/index.html");    
//    //registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//  }
//  

  
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
      configurer.enable();
  }

//  @Override  
//  public void configurePathMatch(PathMatchConfigurer configurer) {  
//      super.configurePathMatch(configurer);  
//      //configurer.setUseSuffixPatternMatch(false);  
//  }  
  
  @Bean(name = "messageSource")
  public ReloadableResourceBundleMessageSource getMessageSource() {
      ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
      resource.setBasename("classpath:messages");
      resource.setDefaultEncoding("UTF-8");
      return resource;
  }
}
