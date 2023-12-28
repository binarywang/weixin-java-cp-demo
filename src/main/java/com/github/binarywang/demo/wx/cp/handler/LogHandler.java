package com.github.binarywang.demo.wx.cp.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.github.binarywang.demo.wx.cp.utils.JsonUtils;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;

/**
 *  @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Component
public class LogHandler extends AbstractHandler {
  @Override
  public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
                                  WxSessionManager sessionManager) {
    log.info("\n接收到请求消息，内容：{}", JsonUtils.toJson(wxMessage));
    return null;
  }

}
