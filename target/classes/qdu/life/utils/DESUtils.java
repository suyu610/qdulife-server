package qdu.life.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URLDecoder;
import java.security.Key;
import java.util.Base64;

/**
 * @ClassName DESUtils
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/1411:34 下午
 * @Version 0.1
 **/


public class DESUtils {

  /**
   * 偏移变量，固定占8位字节
   */
  private final static String IV_PARAMETER = "12345678";
  /**
   * 密钥算法
   */
  private static final String ALGORITHM = "DES";
  /**
   * 加密/解密算法-工作模式-填充模式
   */

//  mode:CryptoJS.mode.ECB,
//  padding: CryptoJS.pad.Pkcs7



  private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";
  /**
   * 默认编码
   */
  private static final String CHARSET = "UTF-8";

  /**
   * 生成key
   *
   * @param password
   * @return
   * @throws Exception
   */
  private static Key generateKey(String password) throws Exception {
    SecretKeySpec key = new SecretKeySpec(password.getBytes(), "DES");
    return key;
  }


  /**
   * DES加密字符串
   *
   * @param password 加密密码，长度不能够小于8位
   * @param data     待加密字符串
   * @return 加密后内容
   */
  public static String encrypt(String password, String data) {
    if (password == null || password.length() < 8) {
      throw new RuntimeException("加密失败，key不能小于8位");
    }
    if (data == null)
      return null;
    try {
      Key secretKey = generateKey(password);
      Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
      IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
      byte[] bytes = cipher.doFinal(data.getBytes(CHARSET));

      //JDK1.8及以上可直接使用Base64，JDK1.7及以下可以使用BASE64Encoder
      //Android平台可以使用android.util.Base64
      return new String(Base64.getEncoder().encode(bytes));

    } catch (Exception e) {
      e.printStackTrace();
      return data;
    }
  }

  /**
   * DES解密字符串
   *
   * @param password 解密密码，长度不能够小于8位
   * @param data     待解密字符串
   * @return 解密后内容
   */
  private static byte[] iv = {1, 2, 3, 4, 5, 6, 7, 8};

  public static String decrypt(String password, String data) throws UnsupportedEncodingException {
    data = URLDecoder.decode(data,"UTF-8");
    if (password == null || password.length() < 8) {
      throw new RuntimeException("加密失败，key不能小于8位");
    }
    byte[] bytes = password.getBytes();
    if (data == null)
      return null;
    try {
      SecretKeySpec key = new SecretKeySpec(password.getBytes(), "DES");

      Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
      IvParameterSpec zeroIv = new IvParameterSpec(iv);
      cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
      return new String(cipher.doFinal(Base64.getDecoder().decode(data.getBytes(CHARSET))), CHARSET);
    } catch (Exception e) {
      e.printStackTrace();
      return data;
    }
  }
}

//orGq35M1vYYqy38PADVDbq
