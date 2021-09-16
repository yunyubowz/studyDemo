package com.example.demo.distributedSystem;

/**
 * zookeeper
 * 配置文件zoo.cfg
 * 配置端口 数据存储目录 follower连接到leader的最大时长 follower与leader同步数据的最大时长
 * 保存快照的最大数量 清除多余的快照
 *
 * 各种命令 create 创建节点 delete 删除节点（但不能有子节点） deleteall 删除节点及所有子节点
 * close 关闭当前会话 connect 重新连接会话 setquota -n|-b val path 设置节点限额-n 子节点数 -b字节数
 * listquota path 查看节点限额  delquota path 删除节点限额 get path获取节点数据
 *
 * create /nodeName 创建持久节点(永久存活)
 * create -s /nodeOrdinalName 创建持久序号节点(永久存活但是名字后会有节点序号)
 * create -e /nodeTemporaryName 创建临时节点(只存在当前会话)
 * create -e -s /nodeTemporaryOrdinalName 创建临时序号节点(只存在当前会话但是名字后会有节点序号)
 *
 *
 * get -s /pathName或stat /pathName "获取节点属性"
 * cZxid(创建该节点事物id)
 * ctime(创建时间)
 * mZxid(修改当前节点数据事物id)
 * mtime(最后修改时间)
 * pZxid(增删子节点的事物id)
 * cversion(增删子节点的次数)
 * dataVersion(修改当前节点数据的次数)
 * aclVersion(修改权限的次数)
 * ephemeralOwner = 0x0(表明当前节点是否为临时节点)
 * dataLength = 17(当前节点的数据长度)
 * numChildren = 3(子节点的数量)
 *
 * 监听
 * ls   -w /pathName 监听当前节点的子节点的变化(增删)
 * get  -w /pathName 监听当前节点的数据的变化
 * stat -w /pathName 监听当前节点的属性变化
 *
 * acl权限设置
 * world  开放模式所有人都可以访问
 * ip     ip模式，限定ip访问
 * auth   用户密码认证模式
 * digest 密文加密
 * permission权限
 * c  可以创建子节点
 * d  可以增删当前节点和子节点
 * r  可以获取当前节点和子节点的数据
 * w  可以设置当前节点和子节点数据
 * a  可以设置当前节点权限
 *
 * world模式设置权限
 * setAcl /pathName world:anyone:cdrwa
 * ip模式设置权限
 * setAcl /pathName ip:192.168.133.129:cdrwa
 * digest模式设置权限
 * echo -n <用户名>:<密码> | openssl dgst -binary -sha1 | openssl base64 (生成密钥)
 * 2Rz3ZtRZEs5RILjmwuXW/wT13Tk=
 * setAcl /pathName digest:yubo:2Rz3ZtRZEs5RILjmwuXW/wT13Tk=:cdrwa
 * addauth 用户名:密钥(当前会话添加权限)
 *
 */
public class ZookeeperDemo1 {
}
