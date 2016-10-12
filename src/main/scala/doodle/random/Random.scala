package doodle.random

/**
 * Created by tsidhu15 on 10/8/16.
 */
sealed trait Random[A] {
  def run(rng: scala.util.Random): A = {
    this match {
      case Primitive(fn) => fn(rng)
      case FlatMap(r, fn) => fn(r.run(rng)).run(rng)
      case Map(r, fn) => fn(r.run(rng))
    }
  }

  def zip[B](that: Random[B]): Random[(A,B)] = {
//    this.map()


//    val test: Random[(A,B)] = that.flatMap(a => that.map(b => Random(a,b)))

    this.flatMap(a => that.map(b => (a,b)))

//    this flatMap { a =>
//      that map { b =>
//        (a, b)
//      }
//    }
//    this.flatMap(a => that)
//    ""
  }

  def map[B](fn: A=>B): Random[B] = {
    Map(this, fn)
  }

  def flatMap[B](fn: A => Random[B]): Random[B] = {
//    fn(this.run(scala.util.Random))
    FlatMap(this, fn)
  }

//  val randomPoint: Random[Point] =
//    (Random.double zip Random.double).map { (pt: (Double, Double)) =>
//      val (x, y) = pt
//      Point(x, y)
//    }
}

object Random {
//  def apply(d: Double): new Random(d)

  def double(): Random[Double] = {
      new Random[Double] {}
  }
  def int(): Random[Int] = new Random[Int] {}

  def always[A](in: A): Random[A] =
    ???
}


case class Primitive[A](f: scala.util.Random => A) extends Random[A]
case class FlatMap[A,B](random: Random[A], fn: A => Random[B]) extends Random[B]
case class Map[A,B](random: Random[A], fn: A => B) extends Random[B]