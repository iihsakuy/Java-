[TOC]

# Java 编程思想

## 第一章

##第二章
##第三章
##第四章 控制执行流程

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
###迭代

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
####break和continue

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



##第五章初始化与清理

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

当我们写的类中没有构造器时，编译器会自动创建一个默认构造器




