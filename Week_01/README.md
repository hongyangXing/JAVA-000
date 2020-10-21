# 极客大学「Java进阶训练营-第0期」作业提交仓库




## week1作业说明

1. 选做：自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。
2. 必做：自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。
3. 必做：画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的关系。
4. 选做：检查一下自己维护的业务系统的 JVM 参数配置，用 jstat 和 jstack、jmap 查看一下详情，并且自己独立分析一下大概情况，思考有没有不合理的地方，如何改进。
   注意：如果没有线上系统，可以自己 run 一个 web/java 项目。

#### 1. 选做：自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。
     
     原始代码:
     
     public static void main(String[] args) {
         for (int i = 0; ; ) if (i++ / i - i * i % i == 0) return;
     }
     
     字节码:
     
     public com.example.demo.geek.Hello();
        Code:
           0: aload_0
           1: invokespecial #1                  // Method java/lang/Object."<init>":()V
           4: return
    
      public static void main(java.lang.String[]);
        Code:
           0: iconst_0           
           1: istore_1
           2: iload_1
           3: iinc          1, 1
           6: iload_1
           7: idiv
           8: iload_1
           9: iload_1
          10: imul
          11: iload_1
          12: irem
          13: isub
          14: ifne          2
          17: return
    }
    

## 注意事项

 如果对 Git 和 GitHub 不太了解，请参考 [Git 官方文档](https://git-scm.com/book/zh/v2) 或者极客时间的[《玩转 Git 三剑客》](https://time.geekbang.org/course/intro/145)视频课程。
