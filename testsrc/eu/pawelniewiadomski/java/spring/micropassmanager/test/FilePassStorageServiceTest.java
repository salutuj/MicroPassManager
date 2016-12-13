package eu.pawelniewiadomski.java.spring.micropassmanager.test;

import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import eu.pawelniewiadomski.java.spring.micropassmanager.data.PasswordData;
import eu.pawelniewiadomski.java.spring.micropassmanager.services.PassStorageService;
import eu.pawelniewiadomski.java.spring.micropassmanager.services.impl.FilePassStorageService;

@ContextConfiguration(locations = { "classpath:spring/test-context.xml" })
public class FilePassStorageServiceTest extends BaseTest {

  @InjectMocks
  private PassStorageService storageService = new FilePassStorageService();

  @Override
  public void initMocks() {

  }

  @BeforeClass
  public void setupBeforeClass() {

  }

  @Test
  public void testCRUDcycle() {
    PasswordData readPasswordData = null;
    PasswordData originalPasswordData = new PasswordData();
    originalPasswordData.setUser("pawel");
    originalPasswordData.setKey("BankAccount");
    originalPasswordData.setLogin("pawelsBankLogin");
    originalPasswordData.setPassword("encryptedPassword");
    PasswordData passwordMatcherData = new PasswordData();
    passwordMatcherData.setUser("pawel");
    passwordMatcherData.setKey("BankAccount");
    passwordMatcherData.setLogin("pawelsBankLogin");

    // Read empty
    readPasswordData = storageService.readPassword(passwordMatcherData);
    Assert.assertNull(readPasswordData, "password should be null as nonexisting");
    // Create
    storageService.storePassword(originalPasswordData);
    // Read created
    readPasswordData = storageService.readPassword(passwordMatcherData);
    Assert.assertEquals(readPasswordData, originalPasswordData, "read password does not match stored one");
    // Update
    originalPasswordData.setPassword("otherEncryptedPassword");
    storageService.updatePassword(originalPasswordData);
    readPasswordData = storageService.readPassword(passwordMatcherData);
    Assert.assertEquals(readPasswordData, originalPasswordData, "read password does not match changed one");
    // Delete
    storageService.removePassword(passwordMatcherData);
    readPasswordData = storageService.readPassword(passwordMatcherData);
    Assert.assertNull(readPasswordData, "deleted password should be null as nonexisting");
  }
}
