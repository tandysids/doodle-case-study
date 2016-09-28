package doodle.core

import doodle.backend.Canvas

/**
 * Created by tsidhu15 on 9/26/16.
 */
trait Image {

  def beside (that: Image, width: Int, height: Int): Image = Beside (this, that)

  def draw (canvas: Canvas): Unit = ???
//    this match {
//      case Rectangle (w, h) =>
//        canvas.rectangle(-w/2, h/2, w/2, -h/2)
//      case RectangleWithFillColor (r, color) =>
//        canvas.setFill(color)
//        r.draw(canvas)
//      case Circle (r) => canvas.circle(0, 0, r)
//      case Beside (left, right) => left.draw(canvas); right.draw(canvas)
//    }
//  }

}

case class Beside(left: Image, right: Image) extends Image

case class RectangleWithFillColor (r: Rectangle, color: Color) extends Image {
  override def draw (canvas: Canvas): Unit = {
    canvas.setFill(color)
    r.draw(canvas)
  }
}

case class Rectangle (width:Int, height:Int) extends Image {

  override def draw (canvas: Canvas): Unit = {
    canvas.rectangle(-width/2, height/2, width/2, -height/2)
  }

  def fillColor (color: Color): RectangleWithFillColor = {
    RectangleWithFillColor(this, color)
  }

  def boundingBox: BoundingBox = {
    BoundingBox(0, 0, width, height)
  }
}
case class Circle (radius: Int) extends Image {
  override def draw (canvas: Canvas): Unit = {
    canvas.circle(0, 0, r)
  }
}
case class Triangle (radius: Int) extends Image

final case class Coordinate(x: Double, y: Double)

final case class BoundingBox(left: Double, top: Double, right: Double, bottom: Double){
  def center: Coordinate  = {
    Coordinate (right - left, bottom - top)
  }

  def combine (other: BoundingBox): BoundingBox = {
    val leftT = Math.min(left, other.left)
    val topT = Math.min(top, other.top)
    val rightT = Math.max(right, other.right)
    val bottomT = Math.max(bottom, other.bottom)

    BoundingBox(leftT, topT, rightT, bottomT)
  }

}
