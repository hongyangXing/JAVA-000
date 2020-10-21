

#### 1. 选做：自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。
     
    
 ```java
//代码块:
public static void main(String[] args) {
     for (int i = 1; i < 100; ) i = i++ * 3 + i / 2 - i--;
}

//字节码:
0: iconst_1                //常量1，入操作数栈
1: istore_1                //常量1，出操作数栈，入LocalVariableTable槽位1
2: iload_1                 //局部标量表槽位1（int i=1）出栈，入操作数栈
3: bipush        100       //100，入操作数栈
5: if_icmpge     27        //比较数值，如果满足条件跳入27行指令return
8: iload_1                 //局部标量表槽位1（int i=1）出栈，入操作数栈
9: iinc          1, 1      //本地变量表槽位1数值加1
12: iconst_3               //常量3，入操作数栈
13: imul                   //相乘
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
```

#### 2. 必做：自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。

 ```java
//代码块:
public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File f = new File(this.getClass().getResource("/Week_01/Hello.xlass").getPath());
        byte[] bytes = new byte[(int) f.length()];
        try {
            new FileInputStream(f).read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return super.findClass(name);
        }
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return defineClass(name, bytes, 0, bytes.length);
    }
}
```

#### 3. 必做：画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的关系。
    
    jmap -heap [pid]
    
    Heap Configuration:
       MinHeapFreeRatio         = 0
       MaxHeapFreeRatio         = 100
       MaxHeapSize              = 4273995776 (4076.0MB)     //-Xmx 最大堆内存 默认为物理内存的1/4 (-Xms 初始化堆内存大小,建议设置为和最大堆内存一样,防止动态扩容)
       NewSize                  = 89128960 (85.0MB)         //-Xmn等价-XX:NewSize 官方建议设置为 -Xmx 的 1/2 ~ 1/4,(使用G1垃圾收集器 不应该 设置该选项)
       MaxNewSize               = 1424490496 (1358.5MB)     //
       OldSize                  = 179306496 (171.0MB)
       NewRatio                 = 2                         // new:old = 1:2,(MaxNewSize*3 = MaxHeapSize)
       SurvivorRatio            = 8
       MetaspaceSize            = 21807104 (20.796875MB)    //-XX:MetaspaceSize 指定元空间的初始化大小,以字节为单位,达到该值会触发垃圾收集进行类型卸载...
       CompressedClassSpaceSize = 1073741824 (1024.0MB)
       MaxMetaspaceSize         = 17592186044415 MB         //-XX:MaxMetaspaceSize Java8默认不限制Meta空间, 一般不允许设置该选项。默认值-1(不限制大小),只受限于本地内存
       G1HeapRegionSize         = 0 (0.0MB)
    
    
    //-XX:MaxDirectMemorySize 设置直接内存的最大容量，如不去设置，默认和-Xmx一致
    //-Xss  设置每个线程栈的字节数。 例如 -Xss1m 指定线程栈为1MB，与-XX:ThreadStackSize=1m等价,hotspot 应该不区分虚拟机栈和本地方法栈(-Xoss实际没有任何效果),栈深过小容易stackoverflow,过大影响线程池分配数量
    
    1.一个java进程内存 = stack(线程栈内存(java thread count*xss))+heap(堆(newsize+oldsize)+非堆(metaspace+css+codecache))+jvm自身+directmemory
    2.nativememory = 堆外(directmemory)+非堆;
    
    
    
    

#### 4. 选做：检查一下自己维护的业务系统的 JVM 参数配置，用 jstat 和 jstack、jmap 查看一下详情，并且自己独立分析一下大概情况，思考有没有不合理的地方，如何改进。注意：如果没有线上系统，可以自己 run 一个 web/java 项目。
 ```java
    //TODO
```