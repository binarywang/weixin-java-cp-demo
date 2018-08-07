package com.github.binarywang.demo.wx.cp.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * * @author Binary Wang(https://github.com/binarywang)
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "wechat.cp")
public class WxCpProperties {
  /**
   * 设置微信企业号的corpId
   */
  private String corpId;

  /**
   * 设置微信企业应用的AgentId
   */
  private Integer agentId;

  /**
   * 设置微信企业应用的Secret
   */
  private String secret;

  /**
   * 设置微信企业号的token
   */
  private String token;

  /**
   * 设置微信企业号的EncodingAESKey
   */
  private String aesKey;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
