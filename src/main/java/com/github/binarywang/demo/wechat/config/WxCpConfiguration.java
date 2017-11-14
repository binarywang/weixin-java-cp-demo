package com.github.binarywang.demo.wechat.config;

import com.github.binarywang.demo.wechat.handler.*;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.WxCpInMemoryConfigStorage;
import me.chanjar.weixin.cp.message.WxCpMessageRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static me.chanjar.weixin.common.api.WxConsts.*;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Configuration
@ConditionalOnClass(WxCpService.class)
@EnableConfigurationProperties(WxCpProperties.class)
public class WxCpConfiguration {
  @Autowired
  protected LogHandler logHandler;
  @Autowired
  protected NullHandler nullHandler;
  @Autowired
  private WxCpProperties properties;
  @Autowired
  private LocationHandler locationHandler;
  @Autowired
  private MenuHandler menuHandler;
  @Autowired
  private MsgHandler msgHandler;
  @Autowired
  private UnsubscribeHandler unsubscribeHandler;
  @Autowired
  private SubscribeHandler subscribeHandler;

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
  public WxCpService WxCpService(WxCpConfigStorage configStorage) {
//        WxCpService WxCpService = new me.chanjar.weixin.cp.api.impl.okhttp.WxCpServiceImpl();
//        WxCpService WxCpService = new me.chanjar.weixin.cp.api.impl.jodd.WxCpServiceImpl();
//        WxCpService WxCpService = new me.chanjar.weixin.cp.api.impl.apache.WxCpServiceImpl();
    WxCpService service = new me.chanjar.weixin.cp.api.impl.WxCpServiceImpl();
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

  protected MenuHandler getMenuHandler() {
    return this.menuHandler;
  }

  protected SubscribeHandler getSubscribeHandler() {
    return this.subscribeHandler;
  }

  protected UnsubscribeHandler getUnsubscribeHandler() {
    return this.unsubscribeHandler;
  }

  protected AbstractHandler getLocationHandler() {
    return this.locationHandler;
  }

  protected MsgHandler getMsgHandler() {
    return this.msgHandler;
  }

  protected AbstractHandler getScanHandler() {
    return null;
  }

}
