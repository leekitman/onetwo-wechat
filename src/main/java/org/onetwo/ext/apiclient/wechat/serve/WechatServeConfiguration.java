package org.onetwo.ext.apiclient.wechat.serve;

import org.onetwo.ext.apiclient.wechat.core.DefaultWechatConfig;
import org.onetwo.ext.apiclient.wechat.core.WechatConfig;
import org.onetwo.ext.apiclient.wechat.serve.service.MessageRouterServiceImpl;
import org.onetwo.ext.apiclient.wechat.serve.spi.MessageRouterService;
import org.onetwo.ext.apiclient.wechat.serve.spi.ServeEndpoint;
import org.onetwo.ext.apiclient.wechat.serve.web.ServeController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wayshall
 * <br/>
 */
@Configuration
//@ComponentScan(basePackageClasses=MessageRouterServiceImpl.class)
@ConditionalOnProperty(name=WechatConfig.ENABLE_MESSAGE_SERVE_KEY, matchIfMissing=true)
@EnableConfigurationProperties(DefaultWechatConfig.class)
public class WechatServeConfiguration  {
	
	
	@Bean
//	@Conditional(MissingServeEndpointCondition.class)
	@ConditionalOnMissingBean(ServeEndpoint.class)
	public ServeEndpoint serveEndpoint(){
		return new ServeController();
	}
	
	@Bean
	@ConditionalOnMissingBean(MessageRouterService.class)
	public MessageRouterService messageRouterService(){
		MessageRouterServiceImpl service = new MessageRouterServiceImpl();
		return service;
	}
	
	
}
