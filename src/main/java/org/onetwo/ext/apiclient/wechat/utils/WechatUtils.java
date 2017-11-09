package org.onetwo.ext.apiclient.wechat.utils;

import java.security.AlgorithmParameters;
import java.security.Security;

import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.onetwo.common.encrypt.AESCoder;
import org.onetwo.common.encrypt.Crypts;
import org.onetwo.common.encrypt.PKCS7Encoder;
import org.onetwo.common.jackson.JsonMapper;
import org.onetwo.common.utils.LangUtils;
import org.onetwo.ext.apiclient.wechat.basic.api.WechatServer;
import org.onetwo.ext.apiclient.wechat.basic.request.GetAccessTokenRequest;
import org.onetwo.ext.apiclient.wechat.basic.response.AccessTokenResponse;
import org.onetwo.ext.apiclient.wechat.core.WechatConfig;
import org.onetwo.ext.apiclient.wechat.utils.WechatConstants.GrantTypeKeys;
import org.onetwo.ext.apiclient.wechat.wxa.auth.response.WxappUserInfo;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author wayshall
 * <br/>
 */
public class WechatUtils {

	public static final String ACCESS_TOKEN_PREFIX = "wechat_accesstoken_";
	public static final String LOCK_KEY = "lock_wechat_acesstoken_";
	static {
		Security.addProvider(new BouncyCastleProvider());
	}
	public static WxappUserInfo decrypt(String sessionKey, String iv, String encryptedData){
		AESCoder aes = AESCoder.pkcs7Padding(Base64.decodeBase64(sessionKey))
								.initer((cipher, mode, keySpec)->{
									AlgorithmParameters params = AlgorithmParameters.getInstance(Crypts.AES_KEY);  
							        params.init(new IvParameterSpec(Base64.decodeBase64(iv)));
							        cipher.init(mode, keySpec, params);
								});
		byte[] decryptData = aes.decrypt(Base64.decodeBase64(encryptedData));
		String userInfo = LangUtils.newString(PKCS7Encoder.decode(decryptData));
		WxappUserInfo wxUser = JsonMapper.defaultMapper().fromJson(userInfo, WxappUserInfo.class);
		return wxUser;
	}
	
	public static GetAccessTokenRequest createGetAccessTokenRequest(WechatAppInfo wechatAppInfo){
		GetAccessTokenRequest request = GetAccessTokenRequest.builder()
															.grantType(GrantTypeKeys.CLIENT_CREDENTIAL)
															.appid(wechatAppInfo.getAppid())
															.secret(wechatAppInfo.getAppsecret())
															.build();
		return request;
	}
	
	public static GetAccessTokenRequest createGetAccessTokenRequest(WechatConfig wechatConfig){
		GetAccessTokenRequest request = GetAccessTokenRequest.builder()
				.grantType(wechatConfig.getGrantType())
				.appid(wechatConfig.getAppid())
				.secret(wechatConfig.getAppsecret())
				.build();
		return request;
	}
	
	public static AccessTokenInfo getAccessToken(WechatServer wechatServer, GetAccessTokenRequest request){
		
		AccessTokenResponse response = wechatServer.getAccessToken(request);
		AccessTokenInfo accessToken = AccessTokenInfo.builder()
													.accessToken(response.getAccessToken())
													.expiresIn(response.getExpiresIn())
													.build();
		return accessToken;
	}
	
	@SuppressWarnings("unchecked")
	public static BoundValueOperations<String, AccessTokenInfo> boundValueOperationsByAppId(RedisTemplate<String, ?> redisTemplate, String appid){
		String key = getAccessTokenKey(appid);
		BoundValueOperations<String, AccessTokenInfo> opt = (BoundValueOperations<String, AccessTokenInfo>)redisTemplate.boundValueOps(key);
		return opt;
	}

	public static String getAccessTokenKey(String appid){
		return WechatUtils.ACCESS_TOKEN_PREFIX + appid;
	}
	
	private WechatUtils(){
	}

}
