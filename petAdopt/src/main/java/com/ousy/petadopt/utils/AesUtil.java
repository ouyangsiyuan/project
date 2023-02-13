package com.ousy.petadopt.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Map;

/***
 * @author gxh
 * 获取微信隐私数据并解密
 */
public class AesUtil {

	static {
		//BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
		Security.addProvider(new BouncyCastleProvider());
	}

	/**
	 * AES解密
	 *
	 * @param data           //密文，被加密的数据
	 * @param key            //秘钥  sessionKey
	 * @param iv             //偏移量
	 * @param encodingFormat //解密后的结果需要进行的编码
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String data, String key, String iv, String encodingFormat) throws Exception {

		//被加密的数据
		byte[] dataByte = Base64.decodeBase64(data);
		//加密秘钥
		byte[] keyByte = Base64.decodeBase64(key);
		//偏移量
		byte[] ivByte = Base64.decodeBase64(iv);
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
			AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
			parameters.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
			byte[] resultByte = cipher.doFinal(dataByte);
			if (null != resultByte && resultByte.length > 0) {
				String result = new String(resultByte, encodingFormat);
				return result;
			}
			return null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidParameterSpecException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void getAuth(Map<String,String> params){
		String url = "";
		try {
			//获取api中的session_key
			String sendGet = WxLoginUtils.sendGet(url, null);
			JSONObject jsonObject = JSONObject.parseObject(sendGet);
			Object openId = jsonObject.get("openId");
			String session_key = (String) jsonObject.get("session_key");

			//解密微信数据
			String decrypt = decrypt(params.get("data"), session_key, params.get("iv"), "UTF-8");
			JSONObject resultJson = JSONObject.parseObject(decrypt);
			//获取数据
			String nickName = resultJson.getString( "nickName" );
			String avatarUrl = resultJson.getString( "avatarUrl" );
			String gender  = resultJson.getString( "gender" );
			String city = resultJson.getString( "city" );
			String country = resultJson.getString( "country" );
			String province = resultJson.getString( "province" );
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

