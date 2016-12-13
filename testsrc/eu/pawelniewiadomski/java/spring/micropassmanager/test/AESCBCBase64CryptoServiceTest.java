package eu.pawelniewiadomski.java.spring.micropassmanager.test;

import java.io.IOException;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import eu.pawelniewiadomski.java.spring.micropassmanager.services.impl.AESCBCBase64CryptoService;

@ContextConfiguration(locations = { "classpath:spring/test-context.xml" })
public class AESCBCBase64CryptoServiceTest extends BaseTest {

  @InjectMocks
  AESCBCBase64CryptoService cryptoService;

  @Override
  @BeforeClass
  public void initMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @BeforeClass
  public void setupBeforeClass() {
    System.out.println("*** AESCBCBase64CryptoServiceTest ***");
  }

  @Test
  public void testSimpleStringEncryption() throws DataLengthException, IllegalStateException, InvalidCipherTextException, IOException {
    System.out.println("* AESCBCBase64CryptoServiceTest::testSimpleStringEncryption");
    final String key = "pass123";
    final String text = "Ala ma kota";

    System.out.println("Key: " + key);
    System.out.println("Test text: " + text);
    String encrypted = cryptoService.encryptString(key, text);
    System.out.println("Test encrypted: " + encrypted);
    String decrypted = cryptoService.decryptFromBase64(key, encrypted);
    System.out.println("Test decrypted: " + decrypted);
    Assert.assertEquals(decrypted, text);
  }

  @Test
  public void testComplexStringEncryption() throws DataLengthException, IllegalStateException, InvalidCipherTextException, IOException {
    System.out.println("* AESCBCBase64CryptoServiceTest::testComplexStringEncryption");
    final String key = "p!w@e#r$t%y^u&i*o(p)-_=+{[}]|'\";:,<.>/?\\`~ąęźżśĆółń";
    final String text = "Chodźże jaśnie ołówku.\nProszę, usiądź sobie.\r\n\tZnaki specyjalne: 1-0:!@#$%^&*()\n"
        + "Dalej=tylda`+podkreślenie_+apostrofy 'jasna rzecz\"{[}]|i podwójny backslash \\"
        + "Chyba tyle /?..,;";
    System.out.println("Key: " + key);
    System.out.println("Test text: " + text);
    String encrypted = cryptoService.encryptString(key, text);
    System.out.println("Test encrypted: " + encrypted);
    String decrypted = cryptoService.decryptFromBase64(key, encrypted);
    System.out.println("Test decrypted: " + decrypted);
    Assert.assertEquals(decrypted, text);
  }
}
