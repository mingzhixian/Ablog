# ABlog
## Ablog-一个简单高效的博客网站
------------------------------------------------------------------------------------------------------专注于分享本博客使用
## 介绍
一个简单高效的博客网站。支持markdown编辑，书写更流畅；无用户机制，专注于分享；极简风格，更高效的阅读体验；匿名评论，简单的讨论；支持夜间模式，呵护夜间努力的你。<br/>
核心功能：
- 文章/页面发布、分类
- 文章markdown/富文本编辑支持、快速编辑
- 日间、夜间双色模式
- 文章浏览次数统计
- 垃圾评论自动过滤
- 多平台支持
- 上传markdown文件
- 下载所有文章
环境支持：
- 作者精力有限，环境支持优化并不会过于关注，一般支持chrome以及使用Chromium内核的近期的浏览器
- 后端运行环境为java-tomcat,在博客初步完成后，会转为go语言（早着呢）。
- 手动部署命令容易过期，所以建议使用docker部署。
## 部署
### 手动部署
1. 安装数据库sqlite（当前，几乎所有的Linux操作系统都将SQLite作为一部分一起发布。可使用以下命令来检查你的机器上是否安装了SQLite。）
```
$ sqlite3
  SQLite version 3.7.15.2 2013-01-09 11:53:05
  Enter ".help" for instructions
  Enter SQL statements terminated with a ";"
sqlite>
```
如果没有安装，就在包管理器中安装
```
centos:
$ sudo yum install sqlite3
ubuntu/debain:
$ sudo apt install sqlite3
```
2.  安装java环境
下载Java(这里安装的是Java11)
```
ubuntu/debain :
$ apt install openjdk-11-jdk
centos:
$ yum install java-11-openjdk
```
检查是否安装成功：
```
$ java -version
openjdk version "11.0.13" 2021-10-19 LTS
OpenJDK Runtime Environment 18.9 (build 11.0.13+8-LTS)
OpenJDK 64-Bit Server VM 18.9 (build 11.0.13+8-LTS, mixed mode, sharing)
//说明安装成功
```
3. 安装tomcat（首先需要安装Java环境，上一步已经实现）
ubuntu:(可以直接在包管理器下载)
```
$ apt install tomcat
```
centos
```
$ wget https://dlcdn.apache.org/tomcat/tomcat-8/v8.5.75/bin/apache-tomcat-8.5.75.tar.gz
```
将包放在自己想要放在的路径下，进行解压，命令如下：
```
$ tar -zxvf apache-tomcat-8.5.75.tar.gz
```
启动tomcat：
```
tomcat的启动脚本是tomcat目录中的bin路径下的 startup.sh ，运行该脚本即可启动tomcat，命令如下：
$ ./startup.sh
```
启动后，可以查看tomcat的进程来确定是否启动成功：
```
$ ps -ef|grep tomcat
```
4. 安装ant
先更新软件包：
```
sudo apt-get update
```
安装：
```
ubuntu:$ sudo apt-get install ant
centos :$ sudo yum install ant
```
5. 下载源码
先安装git 
```
ubuntu:$ sudo apt-get install git
centos :$ sudo yum install git
```
然后在你希望的地方：
```
git clone https://github.com/mingzhixian/Ablog.git
```
6. 编译
进入到build.xml目录：
```
$ ant   //在当前目录下的build.xml运行Ant，执行缺省的target,自动编译
```
7. 部署
找到tomcat安装的目录，apache-tomcat-8.5.73\webapps\  将编译好的项目放在这里面
### docker部署
作者跑路去写go语言的Ablog了，为了偷懒就不搞docker了，遂直接使用以前项目的docker.<br/>
1. 安装docker<br/>
不用我多说了吧。
2. 第二步下载镜像
```
wget http://150.158.81.132/dockerimgs/PeoManager.tar
```
3. 加载镜像
```
docker load --input PeoManager.tar
```
4. run
```
docker run -d -p 8060:8080 -v /dockerapp/Ablog:/usr/local/tomcat/webapps --name Ablog peomanagerd
```
5. 更改项目
```
//下载本项目中的Ablog文件夹下载下来，然后复制到服务器/dockerapp/Ablog下
docker restart Ablog
```
6. 检测<br/>
防火墙打开8060端口，访问检测是否成功（可在第四步中将8060端口改为自己想要的端口）链接：http://ip:8060/Ablog



