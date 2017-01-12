package cn.limbo.demo

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}


/**
  * Created by limbo on 2017/1/11.
  */
object HelloWorld {

  def main(args: Array[String]): Unit = {
    /** ********************* 元 组 *********************************/
    //原先使用的是Pair，但是现在过时了，推荐使用Tuple，Tuple这个东西很有意思，Tuple1是1维的元组
    //Tuple2是二维的以此类推，一共有Tuple17
    val a: (Int, String) = Tuple2(10, "aaa");

    /** ********************* 元 组 *********************************/

    /** ********************* 字 符 串 *********************************/
    val c = '吴'

    val s1 = "杭州师范大学";
    println("s1:" + s1)
    val s2 = s"杭州师范大学${c}永涵"

    println("s2:" + s2);

    val s3 = s"""杭州师范大学"工程师"\n${c}永涵是温州人"""
    println("s3:" + s3);

    println(raw"a\nb");

    val height = 1.9d
    val name = "Limbo"
    println(f"$name%s is $height%2.2f meters tall")
    /** ********************* 字 符 串 *********************************/

    //加法的内部实现，其实就是一个函数
    val x = 3
    val y = 5
    println(x.+(y))

    val f = if (false)
      "true"
    println(f.getClass);

    /** ********************** 字 符 串 的 mkString 用 法 *******************************/
    def mkTable() {
      val table = for (row <- 1 to 9) yield {
        for (col <- 1 to 9) yield {
          val line = (row * col).toString
          val space = " " * (4 - line.length)
          (line + space).mkString
        }
      }
      println(table.mkString("\n"))
    }

    /** ********************** 字 符 串 的 mkString 用 法 *******************************/

    /** ********************** for 循 环 *******************************/
    val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9)

    val list2 = for (item <- list)
      yield item + 1
    println("list：" + list2)


    // 在for中使用if
    val list3 = for (item <- list if item % 2 == 0)
      yield item

    println("list3: " + list3)

    val list4 = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))

    //二重循环
    val list5 = for {
      l <- list4
      item <- l
      if item % 2 == 0
    } yield item

    println("list5: " + list5)

    //until 不包括10
    for (i <- (0 until 10 by 2)) {
      print(i + " ")
    }
    println()

    //to包括10
    for (i <- (0 to 10 by 2)) {
      print(i + " ")
    }
    println()
    /** ********************** for 循 环 *******************************/


    /** ********************** match case *******************************/
    //=>表示一种映射关系，是scala提供的语法糖，可以理解为等价于
    def level(s: Int) = s match {
      case n if n >= 90 => "优秀"
      case n if n >= 80 => "良好"
      case n if n >= 70 => "中等"
      case n if n >= 60 => "及格"
      case _ => "差"
    }

    println(level(99))
    //做类型检验
    //关于symbol类型的介绍 http://www.lai18.com/content/2259273.html
    for {
      x <- Seq(1, false, 2.7, "one", 'four, new java.util.Date(), new RuntimeException("运行时异常"))
    } {
      val str = x match {
        case d: Double => s"double $d"
        case false => "boolean false"
        case d: java.util.Date => s"java.util.Date: $d"
        case i: Int => s"Int $i"
        case s: String => s"String $s"
        case symbol: Symbol => s"symbol: $symbol"
        case unknown => s"unknown value $unknown"
      }
      println(str)
    }

    //在match case中使用 序列
    val nonEmptySeq = Seq(1, 2, 3, 4, 5)
    val emptySeq = Seq.empty[Int]
    val emptyList = Nil
    val nonEmptyList = List(1, 2, 3, 4, 5)
    val nonEmptyVector = Vector(1, 2, 3, 4, 5)
    val emptyVector = Vector.empty[Int]
    val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)
    val emptyMap = Map.empty[String, Int]

    //head +: tail将序列抽取成“头部”和“非头部剩下”两部分，head将保存序列第一个元素，tail保存序列
    // 剩下部分。而case Nil将匹配一个空序列。
    def seqToString[T](seq: Seq[T]): String = seq match {
      case head +: tail => s"$head +:" + seqToString(tail)
      case Nil => "Nil"
    }

    //注意Map转换为Seq的时候一定要显示转换调用.toSeq
    for {
      x <- Seq(nonEmptySeq, emptySeq, nonEmptyList, emptyList,
        nonEmptyVector, emptyVector, nonEmptyMap.toSeq, emptyMap.toSeq)
    } {
      println(seqToString(x))
    }

    //使用case class
    val father = Man("father", 30)
    val mather = Woman("mather", 29)
    val son = Boy("son", 6)

    for {
      person <- Seq[Person](father, mather, son)
    } person match {
      case Man("father", age) => println(s"father is $age")
      case boy: Boy if boy.age < 10 => println(s"boy is $boy")
      case Woman(name, 29) => println(s"$name is 29")
      case _ => println("...")
    }

    /** ********************** match case *******************************/

    //列表的另一种定义方式，一定要以Nil结尾
    val list6 = 1 :: 2 :: 3 :: 4 :: 5 :: 6 :: Nil

    list6.filter(item => item > 1).foreach(println)

    var map = Map(1 -> 2, "a" -> a);
    map += (7 -> "7")
    println(map)

    val man = Man("wyh", 10)
    println(man)

    def sum(l: List[Int]): Int = l.sum

    println(sum(list6))

    /** ********************** 函 数 的 定 义 *******************************/
    //方法一
    def calc(a: Int, b: Int): (Int, Int) = {
      (a + b, a - b)
    }

    //将函数赋给变量
    val calcVar = calc _
    println(calcVar(2, 1))

    //方法二
    //直接定义函数并同时赋给变量，如：val sum: (Int, Int) => Int = (x, y) => x + y，在冒号之后
    //等号之前部分：(Int, Int) => Int是函数签名，代表sum这个函数值接收两个Int类型参数并返回一个Int
    //类型参数。等号之后部分是函数体，在函数函数时，x、y参数类型及返回值类型在此可以省略
    val sum2: (Int, Int) => Int = (x, y) => x + y

    /** ********************** 函 数 的 定 义 *******************************/

    /** ********************** 并 发 *******************************/
    val futures = (0 until 10).map {
      i =>
        Future {
          val s = i.toString
          print(s)
          s
        }
    }

    println()

    val future = Future.reduceLeft(futures)((x, y) => x + y)
    val result = Await.result(future,Duration.Inf)

    println(future)
    println(result)

    val futures1 = (1 to 2).map{
      case 1=> Future.successful("1是奇数")
      case 2=>Future.failed(new RuntimeException("2不是奇数"))
    }

    futures1.foreach(_.onComplete{
      case Success(i) => println(i)
      case Failure(t) => println(t)
    })

    Thread.sleep(2000)
    /** ********************** 并 发 *******************************/

  }
}

trait Person

case class Man(name: String, age: Int) extends Person

case class Woman(name: String, age: Int) extends Person

case class Boy(name: String, age: Int) extends Person
