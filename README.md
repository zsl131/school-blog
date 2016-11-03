# Springboot+JPA+Thymeleaf 校园博客完整小网站


此项目是一个比较简易的校园博客。
麻雀虽小五脏俱全，虽然是比较简易的但是涉及的知识点还是比较全面的。

此项目涵盖了【[知识林](http://www.zslin.com)】中[Springboot](http://www.zslin.com/?cateId=3)和[Thymeleaf](http://www.zslin.com/?cateId=5)中所有知识点的内容。

主要功能有：

1. [系统管理](http://www.zslin.com/web/article/detail/27 "完整项目基础架构精简版-实现权限管理 - 知识林")
    + 系统初始化
    + 菜单管理
    + 角色管理
    + 用户管理
    + 系统配置管理

2. [用户注册](http://www.zslin.com/web/article/detail/22 "Springboot 之 JavaMailSender发送电子邮件 - 知识林")
    + 邮件验证码实现
    + 用户登陆
    + 找回密码

3. 博文分类管理
    + 添加分类
    + 修改分类

4. 博文管理
    + 添加博文
    + 修改博文

5. 博文评论（未实现）

------

### 初始化系统

+ 在`resources/application-dev.properties`中配置了数据库的相关配置，可以修改`spring.datasource`相关的东西以修改数据库

+ 启动项目后访问地址：`/init`进行初始化（需要输入管理员昵称、用户名和密码）

+ 访问`/admin`登陆到后台管理，在`系统配置`中可修改系统名称、管理员邮箱（用于接收各事件的邮件通知）等。

+ 在`网站配置`中可以设置学校名称用于前台显示。

### 用户注册

+ 通过邮箱注册，注册时需要发送验证码到注册邮箱，验证通过才能注册。注册时判断邮箱地址是否已存在，如果存在则提示且不能再次注册。

+ 找回密码，忘记密码是可通过申请邮箱地址找回，也通过发送验证码的方式，验证码验证通过后生成一个随机密码发送至注册邮箱中。

+ 使用邮箱地址和登陆密码进行登陆

+ 登陆后可修改自己的基本信息，如：姓名、座右铭、个人介绍及登陆密码等。

邮件发送可以参考另一篇文章[《Springboot 之 JavaMailSender发送电子邮件》](http://www.zslin.com/web/article/detail/22 "Springboot 之 JavaMailSender发送电子邮件 - 知识林")

### 博文管理

博文管理分为博文分类管理和博文内容管理

+ 点“我的分类”即可进入分类管理，可对自己的分类进行新增、修改和删除操作；

+ 点“我的博文”即可进入博文内容管理，可对自己发布的博文进行修改，也可新增，在新增时可以直接新增分类。

+ 在博文内容管理中使用`wangEditor`在线编辑器，已配置好图片上传的服务端程序； 

### 后台管理

后台管理功能属于系统管理人员操作，可以参考另一个纯净项目[《完整项目基础架构精简版-实现权限管理》](http://www.zslin.com/web/article/detail/27 "完整项目基础架构精简版-实现权限管理 - 知识林")

+ 权限管理
    
在权限管理中有：`菜单管理`、`角色管理`、`用户管理`，这三块功能属于系统功能，在系统初始化时自动生成。

`菜单管理`：可修改菜单名称、排序、图标（图标来源于`fontawesome.io`或`v3.bootcss.com`）；

`角色管理`：可添加、修改、删除角色，也可为角色授权资源菜单；

`用户管理`：可添加、修改、删除用户，也可以为用户分配角色，只有分配有角色的用户才可以登陆系统，且只能访问所拥有角色权限范围内的功能。

+ 系统管理

`系统配置`：在系统管理中目前只有这一个功能，用于设置网站名称、系统管理员邮箱地址（用于接收提示邮件，包括：`用户注册通知`、`用户发布博文的通知`、`用户修改博文的通知`）等。

+ 系统应用

系统应用属于网站管理功能

`网站配置`：目前可设置学校名称，将显示在前面页面中； 

`博文管理`：用于管理用户发布的博文信息，可设置博文的属性（包括是否前台显示、是否为推荐）；

`用户管理`：查看从前台注册的用户（即网站用户）。

### 未完成功能

在这个版本中暂时不写博文点评功能，此功能在技术层面上讲没有任何难点，所以暂时不实现。

### 访问

初始化页面：[http://sblog.zslin.com/init](http://sblog.zslin.com/init)（已经初始化过不能再重复初始化）

前台页面：[http://sblog.zslin.com](http://sblog.zslin.com)

后台管理：[http://sblog.zslin.com/admin](http://sblog.zslin.com/admin)

后台管理用户名：admin

后台管理用户密码：111111

### 说明

本项目在Github上的地址：https://github.com/zsl131/school-blog

本项目作者：[知识林](http://www.zslin.com)


------------

### 项目截图

本人页面设计水平太差，先随便设计点效果出来主要是演示后台程序功能，所以页面设计先暂时放一放。

**用户注册页面效果：**

![用户注册页面](http://og1mqilfp.bkt.clouddn.com/web-user-register.jpg)


**用户信息信息、密码修改页面：**

![用户信息信息、密码修改页面](http://og1mqilfp.bkt.clouddn.com/web-user-update.jpg)


**添加分类页面效果：**

![添加分类页面效果](http://og1mqilfp.bkt.clouddn.com/web-add-category.jpg)


**删除信息提示效果：**

![删除信息提示效果](http://og1mqilfp.bkt.clouddn.com/web-delete-remind.jpg)


**添加博文页面效果：**

![添加博文页面效果](http://og1mqilfp.bkt.clouddn.com/web-add-article.jpg)


**博文列表页面效果：**

![博文列表页面效果](http://og1mqilfp.bkt.clouddn.com/web-list-article.jpg)


**404错误页面效果：**

![404错误页面效果](http://og1mqilfp.bkt.clouddn.com/web-error-404.jpg)


**500错误页面效果：**

![500错误页面效果](http://og1mqilfp.bkt.clouddn.com/web-error-500.jpg)


**后台管理页面效果：**

![后台管理页面效果](http://og1mqilfp.bkt.clouddn.com/admin-list-article.jpg?imageMogr2/thumbnail/!100p)