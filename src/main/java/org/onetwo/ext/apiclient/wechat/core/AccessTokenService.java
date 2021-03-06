package org.onetwo.ext.apiclient.wechat.core;

import java.util.Optional;

import org.onetwo.ext.apiclient.wechat.basic.request.GetAccessTokenRequest;
import org.onetwo.ext.apiclient.wechat.utils.AccessTokenInfo;

/**
 * @author wayshall
 * <br/>
 */
public interface AccessTokenService {

	/***
	 * 不传参数，默认读取配置的
	 * @author wayshall
	 * @return
	 */
	AccessTokenInfo getAccessToken();
	AccessTokenInfo getOrRefreshAccessToken(GetAccessTokenRequest request);
	AccessTokenInfo refreshAccessToken(GetAccessTokenRequest request);
	void removeAccessToken(String appid);
	/***
	 * 根据appid获取缓存的token
	 * @author wayshall
	 * @param appid
	 * @return
	 */
	Optional<AccessTokenInfo> getAccessToken(String appid);
}