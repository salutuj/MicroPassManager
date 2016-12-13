package eu.pawelniewiadomski.java.spring.micropassmanager.services.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.generators.PKCS12ParametersGenerator;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.util.encoders.Base64;

import eu.pawelniewiadomski.java.spring.micropassmanager.services.CryptoService;

public class AESCBCBase64CryptoService implements CryptoService {

  // Buffer used to transport the bytes from one stream to another
  private static byte[] buf = new byte[1024]; // input buffer
  private static byte[] obuf = new byte[1024]; // output buffer

  private Map<String, BCCipherObject> ciphersMap = new HashMap<String, BCCipherObject>();

  public void init(final String key) {
    initCiphers(key);
  }

  private void initCiphers(final String key) {
    final String salt = "temporarySaltForKeyGen";
    // create AES block ciphers in CBC mode with padding
    PBEParametersGenerator keyGenerator = new PKCS12ParametersGenerator(new SHA256Digest());
    keyGenerator.init(PKCS12ParametersGenerator.PKCS12PasswordToBytes(key.toCharArray()), salt.getBytes(), 1);
    CipherParameters keyParams = keyGenerator.generateDerivedParameters(256, 128);
    BCCipherObject bcCipherObject = new BCCipherObject();
    bcCipherObject.encryptCipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()), new PKCS7Padding());
    bcCipherObject.encryptCipher.init(true, keyParams);
    bcCipherObject.decryptCipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()), new PKCS7Padding());
    bcCipherObject.decryptCipher.init(false, keyParams);
    ciphersMap.put(key, bcCipherObject);
  }

  @Override
  public void encryptStream(final String key, final InputStream in, final OutputStream out) throws IOException, DataLengthException, IllegalStateException, InvalidCipherTextException {
    if (in == null || out == null) throw new NullPointerException("in and out may not be null");
    BCCipherObject cipherObject = getOrCreateCipherObjectForKey(key);
    try {
      int bytesRead = 0, bytesProcessed = 0;
      while ((bytesRead = in.read(buf)) >= 0) {
        bytesProcessed = cipherObject.encryptCipher.processBytes(buf, 0, bytesRead, obuf, 0);
        out.write(obuf, 0, bytesProcessed);
      }
      bytesProcessed = cipherObject.encryptCipher.doFinal(obuf, 0);
      out.write(obuf, 0, bytesProcessed);
      out.flush();
    } finally {
      in.close();
      out.close();
    }
  }

  @Override
  public void decryptStream(final String key, final InputStream in, final OutputStream out) throws IOException, DataLengthException, IllegalStateException, InvalidCipherTextException {
    if (in == null || out == null) throw new NullPointerException("in and out may not be null");
    BCCipherObject cipherObject = getOrCreateCipherObjectForKey(key);
    try {
      int bytesRead = 0; // number of bytes read from input
      int bytesProcessed = 0;
      while ((bytesRead = in.read(buf)) >= 0) {
        bytesProcessed = cipherObject.decryptCipher.processBytes(buf, 0, bytesRead, obuf, 0);
        out.write(obuf, 0, bytesProcessed);
      }
      bytesProcessed = cipherObject.decryptCipher.doFinal(obuf, 0);
      out.write(obuf, 0, bytesProcessed);
      out.flush();
    } finally {
      in.close();
      out.close();
    }
  }

  @Override
  public String encryptString(final String key, final String in) throws DataLengthException, IllegalStateException, InvalidCipherTextException, IOException {
    ByteArrayInputStream bais;
    try {
      bais = new ByteArrayInputStream(in.getBytes("UTF-8"));
    } catch (UnsupportedEncodingException ex) {
      bais = new ByteArrayInputStream(in.getBytes());
    }
    ByteArrayOutputStream baos = new ByteArrayOutputStream(in.length());
    encryptStream(key, bais, baos);
    return Base64.toBase64String(baos.toByteArray());

  }

  @Override
  public String decryptFromBase64(final String key, String in) throws DataLengthException, IllegalStateException, InvalidCipherTextException, IOException {
    byte[] decodedBytes = Base64.decode(in);
    ByteArrayInputStream bais = new ByteArrayInputStream(decodedBytes);
    ByteArrayOutputStream baos = new ByteArrayOutputStream(decodedBytes.length);
    decryptStream(key, bais, baos);
    try {
      return baos.toString("UTF-8");
    } catch (UnsupportedEncodingException ex) {
      return baos.toString();
    }
  }

  private BCCipherObject getOrCreateCipherObjectForKey(String key) {
    BCCipherObject cipherObject = ciphersMap.get(key);
    if (cipherObject == null) init(key);
    return ciphersMap.get(key);
  }

  private class BCCipherObject {
    PaddedBufferedBlockCipher encryptCipher;
    PaddedBufferedBlockCipher decryptCipher;
  }
}
