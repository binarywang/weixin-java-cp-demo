package com.github.binarywang.demo.wx.cp.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.chanjar.weixin.cp.message.WxCpMessageHandler;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public abstract class AbstractHandler implements WxCpMessageHandler {
  protected Logger logger = LoggerFactory.getLogger(getClass());
}
