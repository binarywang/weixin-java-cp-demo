[![码云Gitee](https://gitee.com/binary/weixin-java-cp-demo/badge/star.svg?theme=blue)](https://gitee.com/binary/weixin-java-cp-demo)
[![Github](http://github-svg-buttons.herokuapp.com/star.svg?user=binarywang&repo=weixin-java-cp-demo&style=flat&background=1081C1)](https://github.com/binarywang/weixin-java-cp-demo)
[![Build Status](https://travis-ci.org/binarywang/weixin-java-cp-demo.svg?branch=master)](https://travis-ci.org/binarywang/weixin-java-cp-demo)
-----------------------

### 本项目为weixin-java-tools的Demo演示程序，基于Spring Boot构建，实现企业微信后端开发功能。
更多信息请查阅：https://github.com/Wechat-Group/weixin-java-tools

## 使用步骤：
1. 请注意，本demo为简化代码编译时加入了lombok支持，如果不了解lombok的话，请先学习下相关知识，比如可以阅读[此文章](https://mp.weixin.qq.com/s/cUc-bUcprycADfNepnSwZQ)；
1. 配置：复制 `/src/main/resources/application.yml.template` 或者修改其扩展名生成 `application.yml` 文件，根据自己需要填写相关配置（需要注意的是：yml文件内的属性冒号后面的文字之前需要加空格，可参考已有配置，否则属性会设置不成功）；
2. 主要配置说明如下：（	注意：如果是要配置通讯录同步的应用，agentId可以随便配置一个，保证跟下面服务器URL地址里的一致即可。）
```
wechat:
  cp:
    corpId: 111 （企业ID 在此页面查看：https://work.weixin.qq.com/wework_admin/frame#profile）
    appConfigs:
      - agentId: 1000001 （某一具体应用的AgentId）
        secret: 1111（该应用的Secret）
        token: 111 （应用中的“接受消息”部分的“接收消息服务器配置”里的Token值）
        aesKey: 111 （应用中的“接受消息”部分的“接收消息服务器配置”里的EncodingAESKey值）
      - agentId: 1000002 （另一个应用，以下同上）
        secret: 1111
        token: 111
        aesKey: 111
```
3. 运行Java程序：`WxCpDemoApplication`；
4. 打开shell或cmd，进入ngrok目录，运行 `ngrok -config ngrok.cfg -subdomain my-domain 8080` 如果运行失败，请更换my-domain为其它字符串，直至连接成功；
5. 配置企业微信对应应用中的“接受消息”部分的“接收消息服务器配置”URL地址：http://my-domain.tunnel.qydev.com/wx/cp/portal/xxxxx （xxxx要跟AgentId保持一致，注意my-domain要跟上面的一致，需要符合微信官方的要求）；
6. 根据自己需要修改各个handler的实现，加入自己的业务逻辑。
	
