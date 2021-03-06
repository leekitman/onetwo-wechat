package org.onetwo.ext.apiclient.wechat;

import org.onetwo.ext.apiclient.wechat.basic.request.GetAccessTokenRequest;
import org.onetwo.ext.apiclient.wechat.core.AccessTokenService;
import org.onetwo.ext.apiclient.wechat.utils.AccessTokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("product")
public class WechatBaseTestsAdapter extends WechatBaseBootTests {

	@Value("${wechat.appid}")
	protected String appid;
	@Value("${wechat.appsecret}")
	protected String appsecret;
	
	@Autowired
	AccessTokenService accessTokenService;
	
	protected AccessTokenInfo getAccessToken(){
		GetAccessTokenRequest request = GetAccessTokenRequest.builder()
																.appid(appid)
																.secret(appsecret)
															.build();
		AccessTokenInfo tokenInfo = accessTokenService.getOrRefreshAccessToken(request);
		return tokenInfo;
	}
}
