package com.github.binarywang.demo.wx.cp.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * * @author Binary Wang(https://github.com/binarywang)
 */
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

  public String getCorpId() {
    return this.corpId;
  }

  public void setCorpId(String corpId) {
    this.corpId = corpId;
  }

  public Integer getAgentId() {
    return agentId;
  }

  public void setAgentId(Integer agentId) {
    this.agentId = agentId;
  }

  public String getSecret() {
    return this.secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getAesKey() {
    return this.aesKey;
  }

  public void setAesKey(String aesKey) {
    this.aesKey = aesKey;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
