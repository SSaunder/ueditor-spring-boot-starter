# ueditor-spring-boot-starter

## 概述
UEditor富文本编辑器服务端，该服务端不支持以下功能：
- 远程图片抓取

## 使用方法
1. 创建spring-boot项目
2. 在`pom.xml`文件中添加依赖
    ```java
    <dependency>
      <groupId>com.baidu.ueditor</groupId>
      <artifactId>ueditor-spring-boot-starter</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>
    ```
3. 在`application.yml` 或 `application.properties` 文件添加如下属性即可使用
    ```
    com.baidu.ueditor.base-url= 上传文件后访问基本URL，例如http://localhost:8090
    com.baidu.ueditor.home-dir= 实际存储文件的根目录
    ```
> ### 注意
> 文件访问服务需要支持跨域，否则文件上传后将在富文本编辑器中不能正常显示。
