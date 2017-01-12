# Scala

##安装Scala

到Scala官方下载地址下载：http://scala-lang.org/download/
下载结束了之后，解压压缩包，并配置Scala的环境变量，注意：配置Scala之前一定要配置Java的环境变量哦！！！

##RELP

刚才我们已经启动了Scala RELP，它是一个基于命令行的交互式编程环境。对于有着Python、Ruby等动态语言的同学来说，这是一个很常用和工具。但Javaer们第一次见到会觉得比较神奇。我们可以在RELP中做一些代码尝试而不用启动笨拙的IDE，这在我们思考问题时非常的方便。对于Javaer有一个好消息，JDK 9干始将内建支持RELP功能。

对于Scala常用的IDE（集成开发环境），推荐使用IDEA for scala plugins。

Scala的强大，除了它自身对多核编程更好的支持、函数式特性及一些基于Scala的第3方库和框架（如：Akka、Playframework、Spark、Kafka……），还在于它可以无缝与Java结合。所有为Java开发的库、框架都可以自然的融入Scala环境。当然，Scala也可以很方便的Java环境集成，比如：Spring。若你需要第3方库的支持，可以使用Maven、Gradle、Sbt等编译环境来引入。

Scala是一个面向对象的函数式特性编程语言，它继承了Java的面向对特性，同时又从Haskell等其它语言那里吸收了很多函数式特性并做了增强。

##变量、基础数据类型

###变量
Scala中变量不需要显示指定类型，但需要提前声明。这可以避免很多命名空间污染问题。Scala有一个很强大的类型自动推导功能，它可以根据右值及上下文自动推导出变量的类型。你可以通过如下方式来直接声明并赋值。
```scala
scala> val a = 1
a: Int = 1

scala> val b = true
b: Boolean = true

scala> val c = 1.0
c: Double = 1.0

scala> val a = 30 + "岁"
a: String = 30岁
Immutable
```

（注：函数式编程有一个很重要的特性：不可变性。Scala中除了变量的不可变性，它还定义了一套不可变集合scala.collection.immutable._。）

val代表这是一个final variable，它是一个常量。定义后就不可以改变，相应的，使用var定义的就是平常所见的变量了，是可以改变的。从终端的打印可以看出，Scala从右值自动推导出了变量的类型。Scala可以如动态语言似的编写代码，但又有静态语言的编译时检查。这对于Java中冗长、重复的类型声明来说是一种很好的进步。

（注：在RELP中，val变量是可以重新赋值的，这是RELP的特性。在平常的代码中是不可以的。）

###基础数据类型

Scala中基础数据类型有：Byte、Short、Int、Long、Float、Double，Boolean，Char、String。和Java不同的是，Scala中没在区分原生类型和装箱类型，如：int和Integer。它统一抽象成Int类型，这样在Scala中所有类型都是对象了。编译器在编译时将自动决定使用原生类型还是装箱类型。

##字符串

Scala中的字符串有3种。

1.分别是普通字符串，它的特性和Java字符串一至。
2.连线3个双引号在Scala中也有特殊含义，它代表被包裹的内容是原始字符串，可以不需要字符转码。这一特性在定义正则表达式时很有优势。
3.还有一种被称为“字符串插值”的字符串，他可以直接引用上下文中的变量，并把结果插入字符串中。常见的有s,f,raw
s表示字符串中引入插值，f在s的基础上增加了格式控制，raw就是转译字符串中出现的特殊字符使用方法如下
```scala
scala> val c2 = '吴'
c2: Char = 吴

scala> val s1 = "杭州师范大学"
s1: String = 杭州师范大学

scala> val s2 = s"杭州师范大学${c2}永涵"
s2: String = 杭州师范大学

scala> val s3 = s"""杭州师范大学"工程师"\n${c2}永涵是温州人"""
s3: String =
杭州师范大学"工程师"
吴永涵是江津人
```
##运算符和命名

Scala中的运算符其实是定义在对象上的方法（函数），你看到的诸如：3 + 2其实是这样子的：3.+(2)。+符号是定义在Int对象上的一个方法。支持和Java一至的运算符（方法）：

（注：在Scala中，方法前的.号和方法两边的小括号在不引起歧义的情况下是可以省略的。这样我们就可以定义出很优美的DSL）

==、!=：比较运算
!、|、&、^：逻辑运算
>>、<<：位运算

注意：
在Scala中，修正了（算更符合一般人的常规理解吧）==和!=运算符的含义。在Scala中，==和!=是执行对象的值比较，相当于Java中的equals方法（实际上编译器在编译时也是这么做的）。而对象的引用比较需要使用eq和ne两个方法来实现。

##控制语句（表达式）

Scala中支持if、while、for comprehension（for表达式)、match case（模式匹配）四大主要控制语句。Scala不支持switch和? :两种控制语句，但它的if和match case会有更好的实现。

###if

Scala支持if语句，其基本使用和Java、Python中的一样。但不同的时，它是有返回值的。

（注：Scala是函数式语言，函数式语言还有一大特性就是：表达式。函数式语言中所有语句都是基于“表达式”的，而“表达式”的一个特性就是它会有一个值。所有像Java中的? :3目运算符可以使用if语句来代替）。
```scala
scala> if (true) "真" else "假"
res0: String = 真

scala> val f = if (false) "真" else "假"
f: String = 假

scala> val unit = if (false) "真"
unit: Any = ()

scala> val unit2 = if (true) "真" 
unit2: Any = 真
```
可以看到，if语句也是有返回值的，将表达式的结果赋给变量，编译器也能正常推导出变量的类型。unit和unit2变量的类型是Any，这是因为else语句的缺失，Scala编译器就按最大化类型来推导，而Any类型是Scala中的根类型。()在Scala中是Unit类型的实例，可以看做是Java中的Void。

###while

Scala中的while循环语句：
```scala
while (条件) {
  语句块
}
```
###for comprehension

Scala中也有for表达式，但它和Java中的for不太一样，它具有更强大的特性。通常的for语句如下：

```scala
for (变量 <- 集合) {
  语句块
}
```

Scala中for表达式除了上面那样的常规用法，它还可以使用yield关键字将集合映射为另一个集合：

```scala
scala> val list = List(1, 2, 3, 4, 5)
list: List[Int] = List(1, 2, 3, 4, 5)

scala> val list2 = for (item <- list) yield item + 1
list2: List[Int] = List(2, 3, 4, 5, 6)
```

还可以在表达式中使用if判断：
```scala
scala> val list3 = for (item <- list if item % 2 == 0) yield item
list3: List[Int] = List(2, 4)
```
还可以做flatMap操作，解析2维列表并将结果摊平（将2维列表拉平为一维列表）：
```scala
scala> val llist = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
llist: List[List[Int]] = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))

scala> for {
     |   l <- llist
     |   item <- l if item % 2 == 0
     | } yield item
res3: List[Int] = List(2, 4, 6, 8)
```
看到了，Scala中for comprehension的特性是很强大的。Scala的整个集合库都支持这一特性，包括：Seq、Map、Set、Array……

Scala没有C-Like语言里的for (int i = 0; i < 10; i++)语法，但Range（范围这个概念），可以基于它来实现循环迭代功能。在Scala中的使用方式如下：

```scala
scala> for (i <- (0 until 10)) {
     |   println(i)
     | }
0
1
2
3
4
5
6
7
8
9
```

Scala中还有一个to方法：
```scala
scala> for (i <- (0 to 10)) print(" " + i)
 0 1 2 3 4 5 6 7 8 9 10
```
你还可以控制每次步进的步长，只需要简单的使用by方法即可：

```scala
scala> for (i <- 0 to 10 by 2) print(" " + i)
 0 2 4 6 8 10
match case
```

模式匹配，是函数式语言很强大的一个特性。它比命令式语言里的switch更好用，表达性更强。

```scala
scala> def level(s: Int) = s match {
     |   case n if n >= 90 => "优秀"
     |   case n if n >= 80 => "良好"
     |   case n if n >= 70 => "良"
     |   case n if n >= 60 => "及格"
     |   case _ => "差"
     | }
level: (s: Int)String

scala> level(51)
res28: String = 差

scala> level(93)
res29: String = 优秀

scala> level(80)
res30: String = 良好
```

可以看到，模式匹配可以实现switch相似的功能。但与switch需要使用break明确告知终止之后的判断不同，Scala中的match case是默认break的。只要其中一个case语句匹配，就终止之后的所以比较。且对应case语句的表达式值将作为整个match case表达式的值返回。

Scala中的模式匹配还有类型匹配、数据抽取、谓词判断等其它有用的功能。这里只做简单介绍，之后会单独一个章节来做较详细的解读。

##集合

在java.util包下有丰富的集合库。Scala除了可以使用Java定义的集合库外，它还自己定义了一套功能强大、特性丰富的scala.collection集合库API。

在Scala中，常用的集合类型有：List、Set、Map、Tuple、Vector等。

###List

Scala中List是一个不可变列表集合，它很精妙的使用递归结构定义了一个列表集合。
```scala
scala> val list = List(1, 2, 3, 4, 5)
list: List[Int] = List(1, 2, 3, 4, 5)
```
除了之前使用Listobject来定义一个列表，还可以使用如下方式：
```scala
scala> val list = 1 :: 2 :: 3 :: 4 :: 5 :: Nil
list: List[Int] = List(1, 2, 3, 4, 5)
```
List采用前缀操作的方式（所有操作都在列表顶端（开头））进行，::操作符的作用是将一个元素和列表连接起来，并把元素放在列表的开头。这样List的操作就可以定义成一个递归操作。添加一个元素就是把元素加到列表的开头，List只需要更改下头指针，而删除一个元素就是把List的头指针指向列表中的第2个元素。这样，List的实现就非常的高效，它也不需要对内存做任何的转移操作。List有很多常用的方法：
```scala
scala> list.indexOf(3)
res6: Int = 2

scala> 0 :: list
res8: List[Int] = List(0, 1, 2, 3, 4, 5)

scala> list.reverse
res9: List[Int] = List(5, 4, 3, 2, 1)

scala> list.filter(item => item == 3)
res11: List[Int] = List(3)

scala> list
res12: List[Int] = List(1, 2, 3, 4, 5)

scala> val list2 = List(4, 5, 6, 7, 8, 9)
list2: List[Int] = List(4, 5, 6, 7, 8, 9)

scala> list.intersect(list2)
res13: List[Int] = List(4, 5)

scala> list.union(list2)
res14: List[Int] = List(1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9)

scala> list.diff(list2)
res15: List[Int] = List(1, 2, 3)
```

Scala中默认都是Immutable collection，在集合上定义的操作都不会更改集合本身，而是生成一个新的集合。这与Java集合是一个根本的区别，Java集合默认都是可变的。

###Tuple

Scala中也支持Tuple（元组）这种集合，但最多只支持22个元素（事实上Scala中定义了Tuple0、Tuple1……Tuple22这样22个TupleX类，实现方式与C++ Boost库中的Tuple类似）。和大多数语言的Tuple类似（比如：Python），Scala也采用小括号来定义元组。

```scala
scala> val tuple1 = (1, 2, 3)
tuple1: (Int, Int, Int) = (1,2,3)

scala> tuple1._2
res17: Int = 2

scala> val tuple3 = Tuple3("吴","永","涵")
tuple2: (String, String,String) = (吴,永,涵)
```
可以使用xxx._[X]的形式来引用Tuple中某一个具体元素，其_[X]下标是从1开始的，一直到22（若有定义这么多）。

###Set

Set是一个不重复且无序的集合，初始化一个Set需要使用Set对象：
```scala
scala> val set = Set("Scala", "Java", "C++", "Javascript", "C#", "Python", "PHP") 
set: scala.collection.immutable.Set[String] = Set(Scala, C#, Python, Javascript, PHP, C++, Java)

scala> set + "Go"
res21: scala.collection.immutable.Set[String] = Set(Scala, C#, Go, Python, Javascript, PHP, C++, Java)

scala> set filterNot (item => item == "PHP")
res22: scala.collection.immutable.Set[String] = Set(Scala, C#, Python, Javascript, C++, Java)
```
###Map

Scala中的Map默认是一个HashMap，其特性与Java版的HashMap基本一至，除了它是Immutable的：
```scala
scala> val map = Map("a" -> "A", "b" -> "B")
map: scala.collection.immutable.Map[String,String] = Map(a -> A, b -> B)

scala> val map2 = Map(("b", "B"), ("c", "C"))
map2: scala.collection.immutable.Map[String,String] = Map(b -> B, c -> C)
Scala中定义Map时，传入的每个Entry（K、V对）其实就是一个Tuple2（有两个元素的元组），而->是定义Tuple2的一种便捷方式。

scala> map + ("z" -> "Z")
res23: scala.collection.immutable.Map[String,String] = Map(a -> A, b -> B, z -> Z)

scala> map.filterNot(entry => entry._1 == "a")
res24: scala.collection.immutable.Map[String,String] = Map(b -> B)

scala> val map3 = map - "a"
map3: scala.collection.immutable.Map[String,String] = Map(b -> B)

scala> map
res25: scala.collection.immutable.Map[String,String] = Map(a -> A, b -> B)
```
Scala的immutable collection并没有添加和删除元素的操作，其定义+（List使用::在头部添加）操作都是生成一个新的集合，而要删除一个元素一般使用 - 操作直接将Key从map中减掉即可。

（注：Scala中也scala.collection.mutable._集合，它定义了不可变集合的相应可变集合版本。一般情况下，除非一此性能优先的操作（其实Scala集合采用了共享存储的优化，生成一个新集合并不会生成所有元素的复本，它将会和老的集合共享大元素。因为Scala中变量默认都是不可变的），推荐还是采用不可变集合。因为它更直观、线程安全，你可以确定你的变量不会在其它地方被不小心的更改。）

##Class

Scala里也有class关键字，不过它定义类的方式与Java有些区别。Scala中，类默认是public的，且类属性和方法默认也是public的。Scala中，每个类都有一个“主构造函数”，主构造函数类似函数参数一样写在类名后的小括号中。因为Scala没有像Java那样的“构造函数”，所以属性变量都会在类被创建后初始化。所以当你需要在构造函数里初始化某些属性或资源时，写在类中的属性变量就相当于构造初始化了。

在Scala中定义类非常简单：

class Person(name: String, val age: Int) {
  override def toString(): String = s"姓名：$name, 年龄: $age"
}
默认，Scala主构造函数定义的属性是private的，可以显示指定：val或var来使其可见性为：public。

Scala中覆写一个方法必需添加：override关键字，这对于Java来说可以是一个修正。当标记了override关键字的方法在编译时，若编译器未能在父类中找到可覆写的方法时会报错。而在Java中，你只能通过@Override注解来实现类似功能，它的问题是它只是一个可选项，且编译器只提供警告。这样你还是很容易写出错误的“覆写”方法，你以后覆写了父类函数，但其实很有可能你是实现了一个新的方法，从而引入难以察觉的BUG。

实例化一个类的方式和Java一样，也是使用new关键字。

```scala
scala> val me = new Person("吴永涵", 20)
me: Person = 姓名：吴永涵, 年龄: 20

scala> println(me)
姓名：吴永涵, 年龄: 20

scala> me.name
<console>:20: error: value name is not a member of Person
       me.name
          ^

scala> me.age
res11: Int = 30
case class（样本类）
```
case class是Scala中学用的一个特性，像Kotlin这样的语言也学习并引入了类似特性（在Kotlin中叫做：data class）。case class具有如下特性：

1.不需要使用new关键词创建，直接使用类名即可
2.默认变量都是public final的，不可变的。当然也可以显示指定var、private等特性，但一般不推荐这样用
3.自动实现了：equals、hashcode、toString等函数
4.自动实现了：Serializable接口，默认是可序列化的
5.可应用到match case（模式匹配）中
6.自带一个copy方法，可以方便的根据某个case class实例来生成一个新的实例
……
这里给出一个case class的使用样例：

```scala
scala> trait Person
defined trait Person

scala> case class Man(name: String, age: Int) extends Person
defined class Man

scala> case class Woman(name: String, age: Int) extends Person
defined class Woman

scala> val man = Man("吴永涵", 20)
man: Man = Man(吴永涵,20)

scala> val woman = Woman("女人", 23)
woman: Woman = Woman(女人,23)

scala> val manNextYear = man.copy(age = 31)
manNextYear: Man = Man(吴永涵,31)
```

###object

Scala有一种不同于Java的特殊类型，Singleton Objects。
```scala
object Blah {
  def sum(l: List[Int]): Int = l.sum
}
```

在Scala中，没有Java里的static静态变量和静态作用域的概念，取而代之的是：object。它除了可以实现Java里static的功能，它同时还是一个线程安全的单例类。

###伴身对象

大多数的object都不是独立的，通常它都会与一个同名的class定义在一起。这样的object称为伴身对象。
```scala
class IntPair(val x: Int, val y: Int)

object IntPair {
  import math.Ordering
  implicit def ipord: Ordering[IntPair] =
    Ordering.by(ip => (ip.x, ip.y))
}
```
注意

1.伴身对象必需和它关联的类定义定义在同一个.scala文件。

2.伴身对象和它相关的类之间可以相互访问受保护的成员。在Java程序中，很多时候会把static成员设置成private的，在Scala中需要这样实现此特性：
```scala
class X {
  import X._
  def blah = foo
}
object X {
  private def foo = 42
}
```
##函数

在Scala中，函数是一等公民。函数可以像类型一样被赋值给一个变量，也可以做为一个函数的参数被传入，甚至还可以做为函数的返回值返回。

从Java 8开始，Java也具备了部分函数式编程特性。其Lamdba函数允许将一个函数做值赋给变量、做为方法参数、做为函数返回值。

在Scala中，使用def关键字来定义一个函数方法：

```scala
scala> def calc(n1: Int, n2: Int): (Int, Int) = {
     |   (n1 + n2, n1 * n2)
     | }
calc: (n1: Int, n2: Int)(Int, Int)

scala> val (add, sub) = calc(5, 1)
add: Int = 6
sub: Int = 5
```
这里定义了一个函数：calc，它有两个参数：n1和n2，其类型为：Int。cala函数的返回值类型是一个有两个元素的元组，在Scala中可以简写为：(Int, Int)。在Scala中，代码段的最后一句将做为函数返回值，所以这里不需要显示的写return关键字。

而val (add, sub) = calc(5, 1)一句，是Scala中的抽取功能。它直接把calc函数返回的一个Tuple2值赋给了add他sub两个变量。

函数可以赋给变量：
```scala
scala> val calcVar = calc _
calcVar: (Int, Int) => (Int, Int) = <function2>

scala> calcVar(2, 3)
res4: (Int, Int) = (5,6)

scala> val sum: (Int, Int) => Int = (x, y) => x + y
sum: (Int, Int) => Int = <function2>

scala> sum(5, 7)
res5: Int = 12
```
在Scala中，有两种定义函数的方式：

1.将一个现成的函数/方法赋值给一个变量，如：val calcVar = calc _。下划线在此处的含意是将函数赋给了变量，函数本身的参数将在变量被调用时再传入。
2.直接定义函数并同时赋给变量，如：val sum: (Int, Int) => Int = (x, y) => x + y，在冒号之后，等号之前部分：(Int, Int) => Int是函数签名，代表sum这个函数值接收两个Int类型参数并返回一个Int类型参数。等号之后部分是函数体，在函数函数时，x、y参数类型及返回值类型在此可以省略。

###一个函数示例：自动资源管理

在我们的日常代码中，资源回收是一个很常见的操作。在Java 7之前，我们必需写很多的try { ... } finally { xxx.close() }这样的样版代码来手动回收资源。Java 7开始，提供了try with close这样的自动资源回收功能。Scala并不能使用Java 7新加的try with close资源自动回收功能，但Scala中有很方便的方式实现类似功能：
```scala
def using[T <: AutoCloseable, R](res: T)(func: T => R): R = {
  try {
    func(res)
  } finally {
    if (res != null)
      res.close()
  }
}

val allLine = using(Files.newBufferedReader(Paths.get("/etc/hosts"))) { reader =>
  @tailrec
  def readAll(buffer: StringBuilder, line: String): String = {
    if (line == null) buffer.toString
    else {
      buffer.append(line).append('\n')
      readAll(buffer, reader.readLine())
    }
  }

  readAll(new StringBuilder(), reader.readLine())
}
println(allLine)
```
using是我们定义的一个自动化资源管帮助函数，它接爱两个参数化类型参数，一个是实现了AutoCloseable接口的资源类，一个是形如：T => R的函数值。func是由用户定义的对res进行操作的函数代码体，它将被传给using函数并由using代执行。而res这个资源将在using执行完成返回前调用finally代码块执行.close方法来清理打开的资源。

这个：T <: AutoCloseable范型参数限制了T类型必需为AutoCloseable类型或其子类。R范型指定using函数的返回值类型将在实际调用时被自动参数化推导出来。我们在Scala Console中参看allLine变量的类型可以看到 allLine将被正确的赋予String类型，因为我们传给using函数参数func的函数值返回类型就为String：
```scala
scala> :type allLine
String
```
在readAll函数的定义处，有两个特别的地方：

1.这个函数定义在了其它函数代码体内部
它有一个@tailrec注解
在Scala中，因为函数是第一类的，它可以被赋值给一个变量。所以Scala中的def定义函数可以等价val func = (x: Int, y: Int) => x + y这个的函数字面量定义函数形式。所以，既然通过变量定义的函数可以放在其它函数代码体内，通过def定义的函数也一样可以放在其它代码体内，这和Javascript很像。

2.@tailrec注解的含义是这个函数是尾递归函数，编译器在编译时将对其优化成相应的while循环。若一个函数不是尾递归的，加上此注解在编译时将报错。

##模式匹配（match case）

模式匹配是函数式编程里面很强大的一个特性。

之前已经见识过了模式匹配的简单使用方式，可以用它替代：if else、switch这样的分支判断。除了这些简单的功能，模式匹配还有一系列强大、易用的特性。

###match 中的值、变量和类型
```scala
scala> for {
     |   x <- Seq(1, false, 2.7, "one", 'four, new java.util.Date(), new RuntimeException("运行时异常"))
     | } {
     |   val str = x match {
     |     case d: Double => s"double: $d"
     |     case false => "boolean false"
     |     case d: java.util.Date => s"java.util.Date: $d"
     |     case 1 => "int 1"
     |     case s: String => s"string: $s"
     |     case symbol: Symbol => s"symbol: $symbol"
     |     case unexpected => s"unexpected value: $unexpected"
     |   }
     |   println(str)
     | }
int 1
boolean false
double: 2.7
string: one
symbol: 'four
java.util.Date: Sun Jul 24 16:51:20 CST 2016
unexpected value: java.lang.RuntimeException: 运行时异常
```
上面小试牛刀校验变量类型的同时完成类型转换功能。在Java中，你肯定写过或见过如下的代码：
```java
public void receive(message: Object) {
    if (message isInstanceOf String) {
        String strMsg = (String) message;
        ....
    } else if (message isInstanceOf java.util.Date) {
        java.util.Date dateMsg = (java.util.Date) message;
        ....
    } ....
}
```
对于这样的代码，真是辣眼睛啊~~~。

###序列的匹配

```scala
scala> val nonEmptySeq = Seq(1, 2, 3, 4, 5)

scala> val emptySeq = Seq.empty[Int]

scala> val emptyList = Nil

scala> val nonEmptyList = List(1, 2, 3, 4, 5)

scala> val nonEmptyVector = Vector(1, 2, 3, 4, 5)

scala> val emptyVector = Vector.empty[Int]

scala> val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)

scala> val emptyMap = Map.empty[String, Int]

scala> def seqToString[T](seq: Seq[T]): String = seq match {
     |   case head +: tail => s"$head +: " + seqToString(tail)
     |   case Nil => "Nil"
     | }

scala> for (seq <- Seq(
     |   nonEmptySeq, emptySeq, nonEmptyList, emptyList,
     |   nonEmptyVector, emptyVector, nonEmptyMap.toSeq, emptyMap.toSeq)) {
     |   println(seqToString(seq))
     | }
1 +: 2 +: 3 +: 4 +: 5 +: Nil
Nil
1 +: 2 +: 3 +: 4 +: 5 +: Nil
Nil
1 +: 2 +: 3 +: 4 +: 5 +: Nil
Nil
(one,1) +: (two,2) +: (three,3) +: Nil
Nil
```
模式匹配能很方便的抽取序列的元素，seqToString使用了模式匹配以递归的方式来将序列转换成字符串。case head +: tail将序列抽取成“头部”和“非头部剩下”两部分，head将保存序列第一个元素，tail保存序列剩下部分。而case Nil将匹配一个空序列。

###case class的匹配
```scala
scala> trait Person

scala> case class Man(name: String, age: Int) extends Person

scala> case class Woman(name: String, age: Int) extends Person

scala> case class Boy(name: String, age: Int) extends Person

scala> val father = Man("父亲", 33)

scala> val mather = Woman("母亲", 30)

scala> val son = Man("儿子", 7)

scala> val daughter = Woman("女儿", 3)

scala> for (person <- Seq[Person](father, mather, son, daughter)) {
     |   person match {
     |     case Man("父亲", age) => println(s"父亲今年${age}岁")
     |     case man: Man if man.age < 10 => println(s"man is $man")
     |     case Woman(name, 30) => println(s"${name}今年有30岁")
     |     case Woman(name, age) => println(s"${name}今年有${age}岁")
     |   }
     | }
父亲今年33岁
母亲今年有30岁
man is Man(儿子,7)
女儿今年有3岁
```
在模式匹配中对case class进行解构操作，可以直接提取出感兴趣的字段并赋给变量。同时，模式匹配中还可以使用guard语句，给匹配判断添加一个if表达式做条件判断。

并发

Scala是对多核和并发编程的支付做得非常好，它的Future类型提供了执行异步操作的高级封装。

Future对象完成构建工作以后，控制权便会立刻返还给调用者，这时结果还不可以立刻可用。Future实例是一个句柄，它指向最终可用的结果值。不论操作成功与否，在future操作执行完成前，代码都可以继续执行而不被阻塞。Scala提供了多种方法用于处理future。
```scala
scala> :paste
// Entering paste mode (ctrl-D to finish)

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

val futures = (0 until 10).map { i =>
  Future {
    val s = i.toString
    print(s)
    s
  }
}

val future = Future.reduce(futures)((x, y) => x + y)

val result = Await.result(future, Duration.Inf)

// Exiting paste mode, now interpreting.

0132564789

scala> val result = Await.result(future, Duration.Inf)
result: String = 0123456789
```
上面代码创建了10个Future对象，Future.apply方法有两个参数列表。第一个参数列表包含一个需要并发执行的命名方法体（by-name body）；而第二个参数列表包含了隐式的ExecutionContext对象，可以简单的把它看作一个线程池对象，它决定了这个任务将在哪个异步（线程）执行器中执行。futures对象的类型为IndexedSeq[Future[String]]。本示例中使用Future.reduce把一个futures的IndexedSeq[Future[String]]类型压缩成单独的Future[String]类型对象。Await.result用来阻塞代码并获取结果，输入的Duration.Inf用于设置超时时间，这里是无限制。

这里可以看到，在Future代码内部的println语句打印输出是无序的，但最终获取的result结果却是有序的。这是因为虽然每个Future都是在线程中无序执行，但Future.reduce方法将按传入的序列顺序合并结果。

除了使用Await.result阻塞代码获取结果，我们还可以使用事件回调的方式异步获取结果。Future对象提供了几个方法通过回调将执行的结果返还给调用者，常用的有：
```scala
onComplete: PartialFunction[Try[T], Unit]：当任务执行完成后调用，无论成功还是失败
onSuccess: PartialFunction[T, Unit]：当任务成功执行完成后调用
onFailure: PartialFunction[Throwable, Unit]：当任务执行失败（异常）时调用
```
```scala
import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

val futures = (1 to 2) map {
  case 1 => Future.successful("1是奇数")
  case 2 => Future.failed(new RuntimeException("2不是奇数"))
}

futures.foreach(_.onComplete {
  case Success(i) => println(i)
  case Failure(t) => println(t)
})

Thread.sleep(2000)
```
futures.onComplete方法是一个偏函数，它的参数是：Try[String]。Try有两个子类，成功是返回Success[String]，失败时返回Failure[Throwable]，可以通过模式匹配的方式获取这个结果。