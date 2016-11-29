package eu.pawelniewiadomski.java.spring.micropassmanager.configuration;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class MicroPassManagerInitializer extends AbstractDispatcherServletInitializer {

  /**
   * {@inheritDoc}
   * <p>
   * This implementation allows to keep bean definitions in traditional XML
   * while providing configuration of the servlet as Java Annotation based.
   */
  @Override
  protected WebApplicationContext createRootApplicationContext() {
    
      XmlWebApplicationContext rootAppContext = new XmlWebApplicationContext();
      rootAppContext.setConfigLocation("WEB-INF/resources/spring/spring-app.xml");      
      return rootAppContext;    
  }

  
  /**
   * {@inheritDoc}
   * <p>This implementation creates an {@link AnnotationConfigWebApplicationContext},
   * providing it the annotated classes returned by {@link #getServletConfigClasses()}.
   */
  @Override
  protected WebApplicationContext createServletApplicationContext() {
    AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
    servletAppContext.register(MicroPassManagerWebAppConfiguration.class);
    return servletAppContext;
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

 

}