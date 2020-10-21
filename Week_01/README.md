# 极客大学「Java进阶训练营-第0期」作业提交仓库




## week1作业说明

1. 选做：自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。
2. 必做：自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。
3. 必做：画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的关系。
4. 选做：检查一下自己维护的业务系统的 JVM 参数配置，用 jstat 和 jstack、jmap 查看一下详情，并且自己独立分析一下大概情况，思考有没有不合理的地方，如何改进。
   注意：如果没有线上系统，可以自己 run 一个 web/java 项目。

#### 1. 选做：自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。
     
    
 ```java
  //代码块:
  public static void main(String[] args) {
         for (int i = 1; i < 100; ) i = i++ * 3 + i / 2 - i--;
  }


//字节码:
0: iconst_1               //常量1，入操作数栈
1: istore_1               //常量1，出操作数栈，入LocalVariableTable槽位1，代表完成：int i = 1
2: iload_1                //局部标量表槽位1（int i=1）出栈，入操作数栈
3: bipush        100      //常量100，入操作数栈
5: if_icmpge     27       //比较数值，如果满足条件跳入27行指令return
8: iload_1                //局部标量表槽位1（int i=1）出栈，入操作数栈
9: iinc          1, 1     //本地变量表槽位1数值加1，(i++)
12: iconst_3               //常量3，入操作数栈
13: imul                   //操作数栈相乘计算
14: iload_1                
15: iconst_2               
16: idiv                   //相除
17: iadd                   //相加
18: iload_1
19: iinc          1, -1    
22: isub                   //相减
23: istore_1               
24: goto          2        //跳入第2行指令
27: return                 //方法执行完毕






## 注意事项

 如果对 Git 和 GitHub 不太了解，请参考 [Git 官方文档](https://git-scm.com/book/zh/v2) 或者极客时间的[《玩转 Git 三剑客》](https://time.geekbang.org/course/intro/145)视频课程。
