package eu.pawelniewiadomski.java.spring.micropassmanager.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;

public interface CryptoService {
  void encryptStream(String key, InputStream in, OutputStream out) throws IOException, DataLengthException, IllegalStateException, InvalidCipherTextException;

  void decryptStream(String key, InputStream in, OutputStream out) throws IOException, DataLengthException, IllegalStateException, InvalidCipherTextException;

  String encryptString(String key, String in) throws DataLengthException, IllegalStateException, InvalidCipherTextException, IOException;
  String decryptFromBase64(String key, String in) throws DataLengthException, IllegalStateException, InvalidCipherTextException, IOException;

}
