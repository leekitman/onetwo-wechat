package org.onetwo.ext.apiclient.wechat.wxa.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.onetwo.ext.apiclient.wechat.basic.response.WechatResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author wayshall
 * <br/>
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class JscodeAuthResponse extends WechatResponse {
	
	String openid;
	@JsonProperty("session_key")
	String sessionKey;
	String unionid;

}
