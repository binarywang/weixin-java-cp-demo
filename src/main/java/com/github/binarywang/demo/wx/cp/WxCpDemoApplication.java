package com.github.binarywang.demo.wx.cp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 在配置目录下，分为single和mutil目录，代表单实例和多实例方式，请自行选择配置方式
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@SpringBootApplication
public class WxCpDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(WxCpDemoApplication.class, args);
  }

}
