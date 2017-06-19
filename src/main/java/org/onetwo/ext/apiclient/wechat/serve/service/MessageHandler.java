package org.onetwo.ext.apiclient.wechat.serve.service;

import org.onetwo.ext.apiclient.wechat.serve.msg.ReceiveMessage;

/**
 * @author wayshall
 * <br/>
 */
@FunctionalInterface
public interface MessageHandler<T extends ReceiveMessage, R> {
	
	R onMessage(T message);

}