package com.github.binarywang.demo.wx.cp.builder;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  @author Binary Wang(https://github.com/binarywang)
 */
public abstract class AbstractBuilder {
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  public abstract WxCpXmlOutMessage build(String content, WxCpXmlMessage wxMessage, WxCpService service);
}
