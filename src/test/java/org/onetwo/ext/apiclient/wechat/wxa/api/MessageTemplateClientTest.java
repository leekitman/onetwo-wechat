package org.onetwo.ext.apiclient.wechat.wxa.api;

import org.junit.Test;
import org.onetwo.ext.apiclient.wechat.WechatBaseTestsAdapter;
import org.onetwo.ext.apiclient.wechat.basic.request.GetAccessTokenRequest;
import org.onetwo.ext.apiclient.wechat.basic.response.WechatResponse;
import org.onetwo.ext.apiclient.wechat.core.AccessTokenService;
import org.onetwo.ext.apiclient.wechat.utils.AccessTokenInfo;
import org.onetwo.ext.apiclient.wechat.wxa.api.MessageTemplateClient;
import org.onetwo.ext.apiclient.wechat.wxa.request.MessageTemplateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author wayshall
 * <br/>
 */
public class MessageTemplateClientTest extends WechatBaseTestsAdapter {
	
	@Autowired
	MessageTemplateClient wessageTemplateClient;
	@Autowired
	AccessTokenService accessTokenService;
	@Test
	public void testSend(){
		GetAccessTokenRequest request = GetAccessTokenRequest.builder()
																.appid(appid)
																.secret("")
															.build();
		AccessTokenInfo tokenInfo = accessTokenService.getOrRefreshAccessToken(request);
		System.out.println("tokenInfo:"+tokenInfo);
		String accessToken = tokenInfo.getAccessToken();
		System.out.println("accesstoken:"+accessToken);
		MessageTemplateRequest msg = MessageTemplateRequest.builder()
																.formId("1527732808820")
																.templateId("")
																.page("pages/schedule/schedule")
																.touser("oznnx5A2oI-_qzZIeZ90yuzy6k_U")
																.build()
																.addData("keyword1", "测试1")
																.addData("keyword2", "2018-055-31")
																.addData("keyword3", "别忘了用我们的小程序组织你的小伙伴们一起看球哦~~~");
																
		WechatResponse res = wessageTemplateClient.send(tokenInfo, msg);
		System.out.println("res:"+res);
	}

}
