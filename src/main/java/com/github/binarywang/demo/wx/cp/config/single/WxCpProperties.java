//package com.github.binarywang.demo.wx.cp.config.single;
//
//import com.github.binarywang.demo.wx.cp.utils.JsonUtils;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
//import java.util.List;
//
///**
// * @author <a href="https://github.com/binarywang">Binary Wang</a>
// */
//@Getter
//@Setter
//@ConfigurationProperties(prefix = "wechat.cp")
//public class WxCpProperties {
//  /**
//   * 设置企业微信的corpId
//   */
//  private String corpId;
//
//  private List<AppConfig> appConfigs;
//
//  @Getter
//  @Setter
//  public static class AppConfig {
//    /**
//     * 设置企业微信应用的AgentId
//     */
//    private Integer agentId;
//
//    /**
//     * 设置企业微信应用的Secret
//     */
//    private String secret;
//
//    /**
//     * 设置企业微信应用的token
//     */
//    private String token;
//
//    /**
//     * 设置企业微信应用的EncodingAESKey
//     */
//    private String aesKey;
//
//  }
//
//  @Override
//  public String toString() {
//    return JsonUtils.toJson(this);
//  }
//}
