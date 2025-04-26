# 对CS61B的第二次学习

## 遇到的问题
### 我的环境
1. Windows 11
2. Intellij IDEA

### lab1
1. 问：在使用命令行编译运行的时候一直会出现找不到或无法加载主类的报错
- 答：在正确配置`JAVA_HOME`和`PATH`后还需配置`CLASSPATH`，
但是每次编译完成一个任务后，都需要重新配置`CLASSPATH`，很麻烦。
故可以在运行时加上一条`-cp .`规则。
例如：
```bash
javac HelloWorld.java
java -cp . HelloWorld
```

Start Time: 12/31/2024

End Time: TODO



暂停学习 目前还有lab13-15 hw5-7 proj2-3