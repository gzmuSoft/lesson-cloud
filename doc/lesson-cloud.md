# lesson-cloud 部署

原本不打算写这个教程的，后面想想还是写一下吧。这篇文章包含了项目前端以及后端的所有内容。你可以选择性的去看你需要的部分。这个项目为我们学习正式开始的第二个项目，与第一个不同的点在于，这个项目完全是有我们自己构建，而不是使用别人已经构建好的框架，自己会比较了解一点，现在以文字的方式简述一下，从他的思想到现在目前的状况都会意义讲解，以便于后面修改或者其他的操作。

## 技术选型

### 后端

- 版本控制工具：git
- 核心框架：spring-boot
- 安全框架：spring-security / shiro
- 单元测试：junit5
- 日志框架：slf4j
- 接口文档：swagger
- 接口生成：spring-data-rest
- 消息队列：RabbitMQ
- 开发工具：IDEA
- 依赖管理：Maven / Gradle
- 持久层：spring-data-jpa / mybatis

### 前端

- CSS预处理：less
- 代码格式化：ESLint
- 核心框架：vue
- 依赖管理：yarn
- CSS框架：IView
- 数据模拟：MockJS
- 模板引擎：pug
- 状态管理：vuex
- HTTP 库：axios
- 脚手架：vue-cli3

### 环境 —— 暂定

- 反向代理：nginx
- 数据库：mysql
- JDK： Oracle jdk 1.8
- 缓存：redis


## 核心思想

项目完全贯彻前后端分离的概念，分为两个部分如下：
- [lesson-cloud](https://github.com/gzmuSoft/lesson-cloud)：项目后端
- [lesson-cloud-ui](https://github.com/gzmuSoft/lesson-cloud-ui)：项目前端

前后端交互方式规则如下
- 数据交换格式：[json](https://www.json.org/json-zh.html) 
- 开放授权选择：[Oauth2](https://oauth.net/2/)
- 后端接口测试：[Postman](https://www.getpostman.com/)
- 前端借口模拟：[MockJs](http://mockjs.com/)
- 接口规范：[Restful](https://www.ruanyifeng.com/blog/2011/09/restful.html)

通过上面的方式，前后端需要做好接口设计，一旦接口设计完毕，前后端各司其职，各做各的同时进行。前端通过接口模拟，模拟已经开发完成的接口，后端通过测试工具以及测试用例，保证接口符合接口规范。两边同时进行能够更好的交互。

### 接口规范

完全遵循 Restful 规范，提供 Hypermedia API、Spring HATEOAS 以及 HAL Browser

具体详情可以参见：
- [理解RESTful架构](http://www.ruanyifeng.com/blog/2011/09/restful.html)
- [RESTful API 设计指南](http://www.ruanyifeng.com/blog/2014/05/restful_api.html)
- [RESTful API 最佳实践](http://www.ruanyifeng.com/blog/2018/10/restful-api-best-practices.html)

按照规范提供以下方法：
- 获取数据：GET
- 新增数据：POST
- 修改数据：PUT （整个更新）
- 修改数据：PATCH （部分更新）
- 删除数据：DELETE （普通用户未开放）

对于资源有如下操作：

|请求方式 | 请求路径 | 请求参数 | 描述  |
|--------|--------|--------|-------|
| GET | / |  -  |  获取所有资源简单概述 |
| GET | /profile | - | 获取所有资源的详细概述 |
| GET | /{resource}| - |  获取分页后的数据 |
| GET | /{resource}/profile | - | 获取指定资源的概述 |
| GET | /{resource}/{id} | - | 获取指定的一条数据 |
| POST| /{resource} | json 对象 | 新增一条数据 |
| PUT | /{resource}/{id} | 修改后的 json 对象 | 修改指定对象，提供所有字段 |
| PATCH | /{resource}/{id} | 修改后的 json 对象 | 修改指定对象，提供部分字段 |
| GET | /{resource}/search | - | 获取指定资源的其他操作方法 |

> 注意：在实际中我们不会对数据进行真正的删除，而是 **逻辑删除**，每张表都有一个 `is_enable` 字段，只有此字段值为真的时候代表着此数据没有被删除，当为假时代表已经被删除。所以当删除的一个数据的使用应该使用 PUT/PATCH 方法更新 `is_enable` 字段，更多时候请使用 PUT 来进行完全更新。同时，提供了管理员的实际删除权限，可以使用 `DELETE` 方法。

### 权限控制 —— 待完成

完全遵循 [Oauth2](https://oauth.net/2/) 规范，使用 [Password 密码授权](https://oauth.net/2/grant-types/password/) 完成身份认证，提供以下端点进行认证授权：
- **/oauth/token**：密码授权
- **/oauth/check_token**：用户信息获取	
- **/oauth/token**：刷新 token	

#### 密码授权
- 请求路径：/oauth/token
- 请求方式：POST
- 接口描述：通过用户名密码获取授权成功的 token，所有需要授权的请求都必须携带此 token 进行请求访问。
- 请求头：	.

```json
{
	"Authorization": "Basic 客户端usernmae+密码  base64加密"
}
```
- 请求参数：

```json
{
	"grant_type": "password",
    "scope": "当前应用权限范围",
    "username": "用户名",
    "password": "密码"
}
```
- 正确响应结果：

```json
{
	"access_token": "token内容",
    "token_type": "bearer",
    "refresh_token": "刷新token",
    "expires_in": "有效期",
    "scope": "申请的权限域"
}
```

#### 用户信息获取
- 请求路径：/oauth/check_token
- 请求方式：POST
- 接口描述：获取用户授权信息
- 请求头：	.

```json
{
	"Authorization": "Basic 客户端usernmae+密码  base64加密"
}
```
- 请求参数：

```json
{
	"token": "已经获取到的 token 信息"
}
```
- 正确响应结果：

```json
{
	"acitve": "是否启用",
    "exp": "有效时间",
    "user_name": "用户名称",
    "authorities": ["获取到的权限/角色"],
    "client_id": "客户端id",
    "scope": ["申请的权限域"]
}
```

#### 刷新 token
- 请求路径：/oauth/token
- 请求方式：POST
- 接口描述：刷新用户 token
- 请求头：	.

```json
{
	"Authorization": "Basic 客户端usernmae+密码  base64加密"
}
```

- 请求参数：

```json
{
	"grant_type": "refresh_token",
    "refresh_token": "刷新 token"
}
```
- 正确响应结果：

```json
{
	"access_token": "新的token内容",
    "token_type": "bearer",
    "refresh_token": "新的刷新token",
    "expires_in": "有效期",
    "scope": "申请的权限域"
}
```

#### 使用

所有需要授权的请求都必须在请求头中添加如下信息：
```json
{
	"Authorization": "Bearer token内容"
}
```

授权控制在表 `sys_res` 表中（待上传），精确到方法请求控制。


## 在这之前
q
你需要必备一些工具，如果一下工具已经下载可以直接跳过，当然，我不会再像以前一样提供一个个的安装包和安装教程，只会给出官网和版本号，请自行选择安装以及使用。
- [git](https://git-scm.com/)：版本控制工具

对于后端的同学：
- **JDK 1.8 不可高不可低**
- **[IDEA](https://www.jetbrains.com/idea/) 2018 或以上**
- maven 3 （安装 IDEA 时会自带）
- [mysql 5.7](https://dev.mysql.com/downloads/) / [mariadb 10](https://mariadb.org/) 或以上
- [redis 4](https://redis.io/download) 或以上

对于前端的同学：
- 可选： [nvm linux](https://github.com/creationix/nvm) / [nvm windows](https://github.com/coreybutler/nvm-windows) ：node js 版本管理工具
- **[node js](https://nodejs.org/zh-cn/) 长期支持版**
- **[yarn](https://yarn.bootcss.com/docs/install/#windows-stable) 稳定版**
- **[vue cli 3](https://cli.vuejs.org/zh/)**

### 工具
另外，我提供一些常用的工具网站给大家使用
- [Postman](https://www.getpostman.com/) —— 极其方便的接口测试工具
- [jsoncn](https://www.json.cn/) —— JSON 格式数据校验
- [ToYaml](http://www.toyaml.com/index.html) —— 极其方便的 YAML 与 Properties 互转
- [html2jade/pug](https://html2jade.org/) —— html 转换 jade/pug
- [css2less](https://www.css2less.net/) —— css 转换 less

## 后端构建
1. `IDEA` 左上角 `File -> New -> Project From Version Control -> Git` 在弹出中输入项目地址 https://github.com/gzmuSoft/lesson-cloud.git ，点击 Test 测试，测试通过后选择创建路径 clone 即可。![clone](https://resources.echocow.cn/file/2019/4/12/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_%E9%80%89%E6%8B%A9%E5%8C%BA%E5%9F%9F_20190412151447.png)
2. 克隆下来后，有些 idea 会自动下载依赖，有些需要手动下载。遇到最多的问题就是下载不下来依赖的问题，两种解决办法：
	- 如果你连接的是校园网且是联通网，请尝试访问 `10.100.0.11:5741` 或者通过命令行（cmd/shell） `ping 10.100.0.11`，查看主机是否在线，如果主机在线，可以解除根目录下的学校本地仓库的注释，重新下载依赖，如图![](https://resources.echocow.cn/file/2019/4/12/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_%E9%80%89%E6%8B%A9%E5%8C%BA%E5%9F%9F_20190412152020.png)
	- 如果无法访问或使用我校本地仓库，请修改尝试换用手机流量下载，已经配置了 阿里云 国内仓库地址。
	- 如果还不行，请删除用户家目录下的隐藏的 `.m2` 文件夹。
	- 如果再不行，烧柱香拜拜佛吧=-=
3. 然后左上角 `File -> Project Structure...` 配置一下 jdk 版本路径以及版本即可。![](https://resources.echocow.cn/file/2019/4/12/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_sun-awt-X11-XFramePeer_20190412161030.png)
4. 修改数据库配置和 redis 配置 `/lesson-cloud/lesson-cloud-core/src/main/resources/application-dev.yml`
5. 启动数据库和 redis。
6. 运行：`cn.edu.gzmu.Application.main` ，浏览器打开 http://127.0.0.1:8080 查看 HAL Broewer 是否启动即可。

![](https://resources.echocow.cn/file/2019/4/13/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_%E9%80%89%E6%8B%A9%E5%8C%BA%E5%9F%9F_20190413112042.png)

![](https://resources.echocow.cn/file/2019/4/13/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_%E9%80%89%E6%8B%A9%E5%8C%BA%E5%9F%9F_20190413112054.png)

![](https://resources.echocow.cn/file/2019/4/13/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_%E9%80%89%E6%8B%A9%E5%8C%BA%E5%9F%9F_20190413112103.png)

![](https://resources.echocow.cn/file/2019/4/13/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_%E9%80%89%E6%8B%A9%E5%8C%BA%E5%9F%9F_20190413112117.png)


### 模块
项目模块化开发，各模块作用如下

模块名称 | 描述 |
-------|----|
lesson-cloud-auth | 授权模块
lesson-cloud-common | 公共模块
lesson-cloud-core | 核心模块
lesson-cloud-generate | 代码生成模块
lesson-cloud-model | model 模块
lesson-cloud-repository | 资源模块

## 前端
webstorm
1. 同后端
2. 克隆下来以后，右击 `package.json` 选择 `yarn install` 安装依赖
3. 打开 `package.json` 找到 `"serve": "vue-cli-service serve"`，点击运行即可。一般不会出现下载不下来的情况

vscode 就是导入 打开命令行，输入 `yarn install` 和 `yarn serve` 完毕

目前为单页面，后面会弄成多页面。