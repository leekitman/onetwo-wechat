package org.onetwo.ext.apiclient.wechat.wxa.api;

import org.onetwo.common.apiclient.annotation.InjectProperties;
import org.onetwo.ext.apiclient.wechat.core.WechatApiClient;
import org.onetwo.ext.apiclient.wechat.utils.WechatConstants.UrlConst;
import org.onetwo.ext.apiclient.wechat.wxa.request.JscodeAuthRequest;
import org.onetwo.ext.apiclient.wechat.wxa.response.JscodeAuthResponse;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html#wxloginobject
 * @author wayshall
 * <br/>
 */
@WechatApiClient(url=UrlConst.API_DOMAIN_URL)
public interface AuthClient {

	@GetMapping(value="/sns/jscode2session")
	JscodeAuthResponse jscode2session(@InjectProperties JscodeAuthRequest request);
	
	/***
	 * 不自动注入参数
	 * @author wayshall
	 * @param request
	 * @return
	 */
	@GetMapping(value="/sns/jscode2session")
	JscodeAuthResponse jscode2sessionNotInject(JscodeAuthRequest request);

}
