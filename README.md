[TOC]

# Java 编程思想

## 第一章

## 第二章
## 第三章
## 第四章 控制执行流程

### true和false

- java中不允许将一个数字作为布尔值使用

 ```
 if (45){
     、、、
 }
 ```

这样写编译器会报错Incompatible types.

### if-else

If-else语句是控制程序流程最基本的形式，其中else是可选的

```java
if(Boolean-expression)
	statement
```

或

```java
if(Boolean-expression)
	statement
else
 	statement
```
### 迭代

while、do-while和for划分为迭代语句，它们控制的语句会重复执行，直到起控制作用的BooleanExpression得到`false`的结果为止

#### while
```java
while(Boolean-expression)
	statement
```

#### do-while

```java
do
	statement
while(Boolean-expression)
```



do-while和while唯一的区别是do-while中的语句至少会执行一次，即使Boolean-expression第一次就被计算为`false`

#### for

```java
for(initialization; Boolean-expression; step)
	statement
```

initialization、Boolean-expression、step都可以为空，只要Boolean-expression的结果为`true`就会执行for循环中的语句

#### 逗号操作符

Java中唯一用到逗号操作符的地方之后for循环，在表达式的初始化和步进控制部分，可以使用一系列逗号分隔的语句，但它们必须是相同类型，这些语句会独立执行

```java
for(int i = 1, j = 1 + 10; i < 5 ; i++ , j = i * 2){
    System.out.println("i = " + i +",j = " + j );
}
```

### Foreach语法

`foreach`语法用于数组和容器，可以不必创建`int`变量去对由访问项构成的序列进行计数，`foreach`会自动产生每一项

```java
float f[] = new float[10];
\\...f的赋值操作
for(float i : f){
    System.out.println(i)
}
```

#### return

return关键字有两个方面的用途

- 指定一个方法的返回值
- 退出当前方法，并返回值

`void`方法没有`return`语句，因为该方法的结尾会有一个隐式的`return`语句，如果一个方法声明它将返回一个`void`之外其他值，就需要保证每一个路径都会返回一个值
#### break和continue

`break`用于强制退出循环，不执行循环中剩余的语句

`continue`用于停止当前执行的语句，退回循环起始处，开始下一次循环

#### switch

```java
switch(intergral-selector){
    case intergral-value1:statement;break;
    case intergral-value2:statement;break;
    case intergrak-value3:statement;break;
    //...
    default:statement;
}
```

如果省略case后面的break，则会继续执行下面的case语句



## 第五章初始化与清理

### 用构造器确保初始化

构造器采用与类相同的名称，不接受任何参数的构造器叫做默认构造器，Java中称作无参构造器

和其他方法一样，构造器也能够带形式参数，以便指定如何创建对象

构造器有助于减少错误，并使代码更易读

构造器不允许返回值



### 方法重载

#### 区分重载方法

Java通过独一无二的参数列表来区分方法重载

#### 涉及基本类型的重载

基本类型能从一个"较小"的类型自动提升到一个"较大"的类型，而如果将较大的基本类型参数传入接受较小基本类型参数的方法中，需要通过类型转换来进行窄化转换，不然编译器会报错

#### 以返回值区分重载方法

不可以通过返回值来区分重载方法



### 默认构造器

当我们写的类中没有构造器时，编译器会自动创建一个默认构造器，但是如果已经定义了一个构造器，编译器就不会自动创建构造器了



### this关键字

当同一个类中的两个对象a和b调用类中的同一个方法时，如何区分是a还是b调用的呢？其实编译器会把所操作对象的引用偷偷作为第一个参数传递给方法，比如a.test()就会变成a.(a)，但这是内部的表达形式，我们不能这么写代码

如果我们想要在方法的内部获得当前方法的引用时，由于这个引用是编辑器悄悄传入的，所以没有标识符可用，但是有一个专门的关键字来表示它：`this`，`this`关键字只能在方法内部使用，表示调用方法的对象的引用



#### 在构造器中调用构造器

可以用`this(parameter)`来在构造器中调用构造器，需要放在方法起始处，并且只能调用一次

除构造器外，Java禁止在其他任何方法内调用构造器

```java
public class Flower {
    int petalCount = 0;
    String color = "white;
    Flower(int color){
        this.color = color;
    }

    Flower(String petalCount){
        this.petalCount = petalCount;
    }

    Flower(int color, String petalCount){
        this(color);//构造器需要放在起始处，否则编译器会报错
       	//this(PetalCount);//不能使用两次构造器
        this.petalCount = petalCount;
    }
}
```



#### static的含义

在static方法中不存在this，因为static方法的内部不能调用非静态的方法



### 清理：终结处理和垃圾回收

Java允许在类中定义一个名为finalize()的方法，一旦垃圾回收器准备好释放对象占用的存储空间，将首先调用finalize()方法，并在下一次垃圾回收动作发生时，才会真正回收对象占用的内存

- finalize()是Object的protected方法，子类可以覆盖该方法以实现资源清理工作，GC在回收对象之前调用该方法。

- finalize()与C++中的析构函数不是对应的。C++中的析构函数调用的时机是确定的（对象离开作用域就delete掉），但Java中的finalize的调用具有不确定性

- 不建议用finalize方法完成“非内存资源”的清理工作，但建议用于：① 清理本地对象(通过JNI(Java Native Interface)创建的对象)；② 作为确保某些非内存资源(如Socket、文件等)释放的一个补充：在finalize方法中显式调用其他资源释放方法。其原因可见下文[finalize的问题]

具体的finalize流程：

对象可由两种状态，涉及到两类状态空间，一是终结状态空间 F = {unfinalized, finalizable, finalized}；二是可达状态空间 R = {reachable, finalizer-reachable, unreachable}。各状态含义如下：

- unfinalized: 新建对象会先进入此状态，GC并未准备执行其finalize方法，因为该对象是可达的
- finalizable: 表示GC可对该对象执行finalize方法，GC已检测到该对象不可达。正如前面所述，GC通过F-Queue队列和一专用线程完成finalize的执行
- finalized: 表示GC已经对该对象执行过finalize方法
- reachable: 表示GC Roots引用可达
- finalizer-reachable(f-reachable)：表示不是reachable，但可通过某个finalizable对象可达
- unreachable：对象不可通过上面两种途径可达


![](http://ww1.sinaimg.cn/large/a993324ely1fyvphdh5isj20da08d74e.jpg)

变迁说明：

1. 新建对象首先处于[reachable, unfinalized]状态(A)
2. 随着程序的运行，一些引用关系会消失，导致状态变迁，从reachable状态变迁到f-reachable(B, C, D)或unreachable(E, F)状态
3. 若JVM检测到处于unfinalized状态的对象变成f-reachable或unreachable，JVM会将其标记为finalizable状态(G,H)。若对象原处于[unreachable, unfinalized]状态，则同时将其标记为f-reachable(H)。
4. 在某个时刻，JVM取出某个finalizable对象，将其标记为finalized并在某个线程中执行其finalize方法。由于是在活动线程中引用了该对象，该对象将变迁到(reachable, finalized)状态(K或J)。该动作将影响某些其他对象从f-reachable状态重新回到reachable状态(L, M, N)
5. 处于finalizable状态的对象不能同时是unreahable的，由第4点可知，将对象finalizable对象标记为finalized时会由某个线程执行该对象的finalize方法，致使其变成reachable。这也是图中只有八个状态点的原因
6. 程序员手动调用finalize方法并不会影响到上述内部标记的变化，因此JVM只会至多调用finalize一次，即使该对象“复活”也是如此。程序员手动调用多少次不影响JVM的行为
7. 若JVM检测到finalized状态的对象变成unreachable，回收其内存(I)
8. 若对象并未覆盖finalize方法，JVM会进行优化，直接回收对象（O）
9. 注：System.runFinalizersOnExit()等方法可以使对象即使处于reachable状态，JVM仍对其执行finalize方法

#### 终结条件

finalize()可以作为一个终结条件，用来验证对象没有被适当清理的情况

```java
class Book {
    boolean checkOut = false;

    Book(boolean checkOut) {
        this.checkOut = checkOut;
    }

    public void checkIn() {
        checkOut = false;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (checkOut) {
            System.out.println("Error : check out");
        }
    }
}

public class TerminationCondition{
        public static void main(String[] args) {
            Book book = new Book(true);
            book.checkIn();
            new Book(true);
            System.gc();
        }
    }

```

所有Book对象在被回收前都应该check in，但在main方法中，有一本书未被签入，finalize()可以作为终结条件来验证这种缺陷，否则将很难发现



#### 垃圾回收器如何工作

### 成员初始化

当类的字段为基本类型时，类的每个基本类型都会有一个初始值。

| 基本类型 | 初始值 |
| -------- | ------ |
| int      | 0      |
|boolean|false|
|char|0|
|byte|0|
|short|0|
|long|0|
|float|0.0|
|double|0.0|
|reference|Null|

在类里定义一个对象时，如果不将其初始化，此引用就会获得一个初始值null

#### 指定初始化

如果想为某个变量赋初值，可以在定义变量的地方直接赋值（C++中不能这么做）

```java
public class InitialValues{
    boolean t = false;
    char c = s;
    byte b = 4;
    int i = 5;
    short s = 0xff;
    long l = 10;
    folat = 0.02;
    double = 3.1415926;
}
```

### 构造器初始化

#### 初始化顺序

在类中，变量定义的顺序决定了初始化的顺序，即使变量定义散布于方法之间，它仍然会在任何方法调用前（包括构造器）初始化

```java
class Window {
    Window(int i) {
        System.out.println("Window(" + i +")");
    }
}

class House{
    Window w1 = new Window(1);

    House(){
        System.out.println("House()");
        w3 = new Window(4);
    }

    Window w2 = new Window(2);

    void f(){
        System.out.println("f()");
    }

    Window w3 = new Window(3);
}

public class OrderOfInitialization {
    public static void main(String[] args) {
        House house = new House();
        house.f();
    }
}
```

Output:

```
Window(1)
Window(2)
Window(3)
House()
Window(4)
f()
```

#### 静态数据的初始化

初始化的顺序是先静态对象，而后是"非静态"对象



#### 显示的静态初始化

Java允许将多个静态初始化动作组织成一个特殊的"静态块"

```
public class Spoon{
    static int i;
    static {
        i = 4;
    }
}
```

"静态块"只执行一次，在首次生成这个类的对象时或是首次访问属于这个类的静态数据成员时

```java
class Cup{
    Cup(int marker){
        System.out.println("Cup("+marker+")");
    }

    void f(int marker){
        System.out.println("f("+marker+")");
    }
}

class Cups{
    static Cup cup1;
    static Cup cup2;
    static {
        cup1 = new Cup(1);
        cup2 = new Cup(2);
    }
    Cups(){
        System.out.println("Cups()");
    }

}
public class ExplicitStatic {
    public static void main(String[] args) {
        System.out.println("Inside main");
        Cups.cup1.f(1);//(1)
//        Cups cups1 = new Cups();//(2)
//        Cups cups2 = new Cups();//(2)
    }
}
```

无论是执行（1）还是（2），Cups的静态初始化都会得到执行，即使激活两行（2）的代码，"静态块"也只会初始化一次

#### 非静态实例初始化

非静态实例初始化子句与静态初始化子句一摸一样，只是少了static关键字，它是在构造器之前完成的

### 数组初始化

要定义一个数组，只需要在类型名后面加上一堆空的方括号即可

```java
int[] a1;
```

方括号也可以置于标识符后面

```java
int a1[];
```

#### 可变参数列表

```java
class A {
}

public class VarArgs {
    static void printArrays(Object[] args) {
        for (Object obj : args) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printArrays(new Object[]{
                new Integer(12), new Float(3.14), new Double(6.1800)
        });
        printArrays(new Object[]{"one", "two", "three"});
        printArrays(new Object[]{new A(),new A(),new A()});

    }
}
```

Output：

```
12 3.14 6.18
one two three
finalize_exercise.A@2503dbd3 finalize_exercise.A@4b67cf4d finalize_exercise.A@7ea987ac
```



所有的类都直接或间接继承自Object类，所以可以创建一个Object类型的数组，通过print打印每个对象



```java

public class NewVarArgs {
    static void printArrays(Object...args){
        for (Object object : args){
            System.out.print(object + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printArrays(new Integer(12),new Float(3.14),new Double(6.180));
        printArrays(47,3.14,6.180);
        printArrays("one","two","three");
        printArrays(new A(),new A(),new A());
        printArrays((Object[])new Integer[]{1,2,3,4});
        printArrays();
    }
}
```

Output:

```
12 3.14 6.18
47 3.14 6.18
one two three
finalize_exercise.A@2503dbd3 finalize_exercise.A@4b67cf4d finalize_exercise.A@7ea987ac
1 2 3 4

```

这里后面没看完

### 枚举类型

```java
public enum Spiciness{
    NOT,MILD,MEDIUM,HOT,FLAMING
}
```

这里创建了一个名为Spiciness的枚举类型，它具有5个具名值，因为枚举类型的实例是常量，所以按照惯例用大写字母表示（如果一个名字中有多个单词，用下划线分隔）

为了使用枚举类型，需要创建一个该类型的引用：

```java
public class SimpleEnumUse{
    public static void main(String[] args){
        Spiciness howHot = Spiciness.MEDIUM;
        System.out.println(howHot)
    }
}

```

enum可以结合switch使用

### 总结



## 访问权限控制

访问权限是为了把"变动的事物与不变的事物区分开来"

Java中访问权限有4种，从最大权限到最小依次是：pubilic,protected,包访问权限,private

### 包：库单元

包内包含有一组类，它们在单一的名字空间下被组织在了一起

一个.java文件被称为一个编译单元，编译单元内可以有一个public类，该类的名称必须与文件名相同，每个编译单元只能有一个public类，否则会编译错误，如果该编译单元中还有其他类，包外是无法看到的，因为它们不是public类，它们主要用来为public类提供支持

#### 代码组织

当编译一个.java文件时，在.java文件中的每个类都会有一个输出文件，而该输出文件的名称与.java文件中的每个类的名称相同，只是多了一个后缀名.class，因此编译少量.java文件会得到大量的.class文件

类库实际上是一组类文件，每个文件都有一个public类和任意数量的非public类，如果希望这些构建属于同一个群组，可以使用package关键字，它必须写在程序中除注释外的第一行代码

```
package access;
```

#### 创建独一无二的包

#### 冲突

如果将两个含有含有相同名称的类库以"*"的形式同时导入，会出现潜在的冲突，比如在下面两个包中都有Vector类

```
import net.mindview.simple.*
import java.util.*
```
如果这时候创建一个Vector类的实例，就会产生冲突

```java
Vetor v = new Vector();
```

编译器不知道需要使用哪个Vector，所以编译器会报错，需要强制我们明确指明，如果想要一个标准的Java Vector类，旧的这样写

```java
java.util.Vector v = new java.util.Vector();
```

可以用单个类导入的形式来避免这种冲突
#### 定制工具库





