package bookexamples

/**
 * Created by tsidhu15 on 9/27/16.
 */
sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Failure(reason: String) extends Calculation

object Calculator{
  def + (calc: Calculation, result: Int): Unit = {

  }
}