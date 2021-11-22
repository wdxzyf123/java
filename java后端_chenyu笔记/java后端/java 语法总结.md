#### Java length()方法，length属性和size()方法的区别

1. `length()`方法是针对字符串来说的，要求一个字符串的长度就要用到它的length()方法；
2. `length` 属性是针对Java中的数组来说的，要求数组的长度可以用其length属性；
3. Java中的`size()`方法是针对泛型集合说的，如果想看这个泛型有多少个元素，就调用此方法来查看

#### Java HashMap 的用法

创建HashMap 对象 Sites

```java
HashMap<Integer, String> Sites = new HashMap<Integer, String>();
```

添加键值对

```java
Sites.put(1, "Google");
Sites.put(2, "Runoob");
Sites.put(3, "Taobao");
Sites.put(4, "Zhihu");
```

访问元素

```java
(Sites.get(3));
```

删除元素

```java
Sites.remove(4);
```

删除所有键值对(key-value)可以使用clear方法

```java
Sites.clear();
```

计算大小

```java
System.out.println(Sites.size());
```

迭代HashMap

```java
// 输出 key 和 value
for (Integer i : Sites.keySet()) {
    System.out.println("key: " + i + " value: " + Sites.get(i));
}
// 返回所有 value 值
for(String value: Sites.values()) {
    // 输出每一个value
    System.out.print(value + ", ");
}
```

检查 hashMap 中是否存在指定的 key 对应的映射关系

```java
System.out.println(Sites.containKey(4));
```

#### Java HashSet 的用法

创建HashSet 对象 sites

```java
HashSet<String> sites = new HashSet<String>();
```

添加元素

```java
sites.add("Google");
sites.add("Runoob");
sites.add("Taobao");
sites.add("Zhihu");
sites.add("Runoob");  // 重复的元素不会被添加
```

判断元素是否存在

```java
System.out.println(sites.contains("Taobao"));
```

删除元素

```java
sites.remove("Taobao");  // 删除元素，删除成功返回 true，否则为 false
```

删除集合中的所有元素可以使用clear方法

```java
sites.clear(); 
```

计算大小

```java
System.out.println(sites.size());  
```

迭代HaseSet

```java
System.out.println(i);
```

#### Java ArrayList的对象

创建ArrayList 对象 sites

```java
ArrayList<String> sites = new ArrayList<String>();
```

添加元素

```java
sites.add("Google");
sites.add("Runoob");
sites.add("Taobao");
sites.add("Weibo");
```

访问元素

```java
System.out.println(sites.get(1));  // 访问第二个元素
```

修改元素

```java
sites.set(2, "Wiki"); // 第一个参数为索引位置，第二个为要修改的值
```

删除元素

```java
sites.remove(3); // 删除第四个元素
```

计算大小

```java
System.out.println(sites.size());
```

迭代数组列表

```java
for (int i = 0; i < sites.size(); i++) {
    System.out.println(sites.get(i));
}
for (String i : sites) {
    System.out.println(i);
}
```

#### Java Stack 对象

创建 Stack 类对象

```java
Stack<Integer> st = new Stack<Integer>();
```

测试栈堆是否为空

```java
boolean empty() 
```

查看堆栈顶部的对象，但不从堆栈中移除它

```java
Object peek( )
```

移除堆栈顶部的对象，并将此函数的值返回该对象

```java
Object pop( )
```

把项压入堆栈顶部

```java
Object push(Object element)
```

返回对象在堆栈的位置，以1为基数。

```java
int search(Object element)
```

#### String、StringBuffer和StringBuilder的区别

##### 1. String长度不可变而StringBuffer和StringBuilder长度可变

```java
public final class String implements java.io.Serializable, Comparable<String>, CharSequence {}
	/** The value is used for character storage. */
	private final char value[];
```

```java
public final class StringBuffer extends AbstractStringBuilder implements java.io.Serializable, CharSequence
```

```java
public final class StringBuilder extends AbstractStringBuilder
```

```java
abstract class AbstractStringBuilder implements Appendable, CharSequence {
    /**
     * The value is used for character storage
     */
    char[] value;
```

我们能看到，String这个类底层使用了final修饰的长度不可变的字符数组，所以它长度不可变

```java
private final char value[];
```

而StringBuffer和StringBuilder都继承自AbstractStringBuilder，且AbstractStringBuilder底层使用的是可变字符数组，所以二者长度可变。

```jav
char[] value;
```

##### 2. 他们的运行速度不同：StringBuilder > StringBuffer > String

```java
String  str = "abc";
str = str + "cd"
```

对于String对象，其字符串长度是不可变的，其实JVM是先创建的了一个str对象，将”abc“赋值给str，然后在内存中又创建了第二个str对象，将第一个str对象的”abc“与”de“相加再复制给第二个str对象，此时Java虚拟机的垃圾回收机制开始其工作将第一个str对象回收。所以说

