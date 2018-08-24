package com.github.binarywang.demo.wx.cp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.binarywang.demo.wx.cp.handler.AbstractHandler;
import com.github.binarywang.demo.wx.cp.handler.LocationHandler;
import com.github.binarywang.demo.wx.cp.handler.LogHandler;
import com.github.binarywang.demo.wx.cp.handler.MenuHandler;
import com.github.binarywang.demo.wx.cp.handler.MsgHandler;
import com.github.binarywang.demo.wx.cp.handler.NullHandler;
import com.github.binarywang.demo.wx.cp.handler.SubscribeHandler;
import com.github.binarywang.demo.wx.cp.handler.UnsubscribeHandler;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.WxCpInMemoryConfigStorage;
import me.chanjar.weixin.cp.message.WxCpMessageRouter;

import static me.chanjar.weixin.common.api.WxConsts.*;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Configuration
@ConditionalOnClass(WxCpService.class)
@EnableConfigurationProperties(WxCpProperties.class)
public class WxCpConfiguration {
  private LogHandler logHandler;
  private NullHandler nullHandler;
  private WxCpProperties properties;
  private LocationHandler locationHandler;
  private MenuHandler menuHandler;
  private MsgHandler msgHandler;
  private UnsubscribeHandler unsubscribeHandler;
  private SubscribeHandler subscribeHandler;

  @Autowired
  public WxCpConfiguration(LogHandler logHandler, NullHandler nullHandler, WxCpProperties properties,
                           LocationHandler locationHandler, MenuHandler menuHandler, MsgHandler msgHandler,
                           UnsubscribeHandler unsubscribeHandler, SubscribeHandler subscribeHandler) {
    this.logHandler = logHandler;
    this.nullHandler = nullHandler;
    this.properties = properties;
    this.locationHandler = locationHandler;
    this.menuHandler = menuHandler;
    this.msgHandler = msgHandler;
    this.unsubscribeHandler = unsubscribeHandler;
    this.subscribeHandler = subscribeHandler;
  }

  @Bean
  @ConditionalOnMissingBean
  public WxCpConfigStorage configStorage() {
    WxCpInMemoryConfigStorage configStorage = new WxCpInMemoryConfigStorage();
    configStorage.setCorpId(this.properties.getCorpId());
    configStorage.setAgentId(this.properties.getAgentId());
    configStorage.setCorpSecret(this.properties.getSecret());
    configStorage.setToken(this.properties.getToken());
    configStorage.setAesKey(this.properties.getAesKey());
    return configStorage;
  }

  @Bean
  @ConditionalOnMissingBean
  public WxCpService wxCpService(WxCpConfigStorage configStorage) {
    // 根据自己爱好选用
    // WxCpService service = new WxCpServiceJoddHttpImpl();
    // WxCpService service = new WxCpServiceApacheHttpClientImpl();
    // WxCpService service = new WxCpServiceOkHttpImpl();

    WxCpService service = new WxCpServiceImpl();
    service.setWxCpConfigStorage(configStorage);
    return service;
  }

  @Bean
  public WxCpMessageRouter router(WxCpService wxCpService) {
    final WxCpMessageRouter newRouter = new WxCpMessageRouter(wxCpService);

    // 记录所有事件的日志 （异步执行）
    newRouter.rule().handler(this.logHandler).next();

    // 自定义菜单事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
        .event(MenuButtonType.CLICK).handler(this.getMenuHandler()).end();

    // 点击菜单连接事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
        .event(MenuButtonType.VIEW).handler(this.nullHandler).end();

    // 关注事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
        .event(EventType.SUBSCRIBE).handler(this.getSubscribeHandler())
        .end();

    // 取消关注事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
        .event(EventType.UNSUBSCRIBE)
        .handler(this.getUnsubscribeHandler()).end();

    // 上报地理位置事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
        .event(EventType.LOCATION).handler(this.getLocationHandler())
        .end();

    // 接收地理位置消息
    newRouter.rule().async(false).msgType(XmlMsgType.LOCATION)
        .handler(this.getLocationHandler()).end();

    // 扫码事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
        .event(EventType.SCAN).handler(this.getScanHandler()).end();

    // 默认
    newRouter.rule().async(false).handler(this.getMsgHandler()).end();

    return newRouter;
  }

  private MenuHandler getMenuHandler() {
    return this.menuHandler;
  }

  private SubscribeHandler getSubscribeHandler() {
    return this.subscribeHandler;
  }

  private UnsubscribeHandler getUnsubscribeHandler() {
    return this.unsubscribeHandler;
  }

  private AbstractHandler getLocationHandler() {
    return this.locationHandler;
  }

  private MsgHandler getMsgHandler() {
    return this.msgHandler;
  }

  private AbstractHandler getScanHandler() {
    return null;
  }

}
