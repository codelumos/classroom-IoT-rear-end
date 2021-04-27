# classroom-IoT-rear-end

## 项目描述

模拟教室IoT设备管理平台后端

## 功能模块

1. 连接管理

2. 设备管理

3. 数据分析

4. 规则引擎

## 项目构建

1. 创建iot_classroom数据库，执行iot_classroom.sql建表和插入数据

2. 搭建MQTT服务器，修改clientMock\\MqttService中的MQTT代理服务器配置

> MQTT服务器搭建参考以下链接：
>
> https://www.cnblogs.com/saryli/p/9760113.html
>
> https://blog.csdn.net/qq_38113006/article/details/105655753

3. 修改application.yml中的配置信息

4. 从IoTApplication入口启动项目
