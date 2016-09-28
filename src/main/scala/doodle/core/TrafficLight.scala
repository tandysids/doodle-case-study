package doodle.core

/**
 * Created by tsidhu15 on 9/27/16.
 */
sealed trait TrafficLight {
  def next (trafficLight: TrafficLight) = {
    trafficLight match {
      case Red => Green
      case Green => Yellow
      case Yellow => Red
    }
  }
}

final case object Red extends TrafficLight
final case object Green extends TrafficLight
final case object Yellow extends TrafficLight
