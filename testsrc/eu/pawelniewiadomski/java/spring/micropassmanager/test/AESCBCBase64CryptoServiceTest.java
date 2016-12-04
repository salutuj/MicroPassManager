package eu.pawelniewiadomski.java.spring.micropassmanager.test;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;

import eu.pawelniewiadomski.java.spring.micropassmanager.services.impl.AESCBCBase64CryptoService;

public class AESCBCBase64CryptoServiceTest extends BaseTest{

  @InjectMocks
  AESCBCBase64CryptoService cryptoService;
  
  @Override  
  @BeforeClass
  public void initMocks(){
    MockitoAnnotations.initMocks(this);
  }

  
}
