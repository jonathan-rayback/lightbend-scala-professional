package com.lightbend.training.scalatrain

import play.api.libs.json.JsValue
import play.api.libs.json.JsObject
import play.api.libs.json.JsNumber
import play.api.libs.json.JsResult
import scala.util.Try
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsError
import scala.util.Success
import scala.util.Failure

object Time {

  //  determines normalized hour of day [0-23] and leftover minutes [0-59] from any number of minutes, returns a Time object
  def fromMinutes(minutes: Int): Time = {
    val hoursFromMinutes = ((minutes / 60).toInt) % 24
    val leftoverMinutes = minutes % 60
    Time(hoursFromMinutes, leftoverMinutes)
  }
  // a bad hour results in a failure, a bad minute defaults to 0
  def fromJson(js: JsValue): Option[Time] = {
    for {
      // if there's no int to get for hours, the whole function fails
      hours <- Try((js \ "hours").validate[Int].get)
      // if there's no int for minutes, just use 0
      minutes = (js \ "minutes").validate[Int].getOrElse(0)
    } yield Time(hours, minutes)
  }.toOption

  // my initial run at it, uses partial function in success case, the above is much cleaner
  def fromJsonALT(js: JsValue): Option[Time] = {
    Try {
      (js \ "hours").validate[Int].get
    } match {
      case Success(h) => {
        val m = (js \ "minutes").validate[Int].getOrElse(0)
        Some(Time(h, m))
      }
      case Failure(e) => None
    }
  }

}

case class Time(hours: Int = 0, minutes: Int = 0) extends Ordered[Time] {
  require(hours <= 23 && hours >= 0, "Hours must be between 0 and 23.")
  require(minutes <= 59 && minutes >= 0, "Minutes must be between 0 and 59.")

  val asMinutes: Int = (hours * 60) + minutes
  val toJson: JsValue = JsObject(Seq("hours" -> JsNumber(hours), "minutes" -> JsNumber(minutes)))

  def minus(that: Time): Int = asMinutes - that.asMinutes

  // define infix operator
  def -(that: Time): Int = minus(that)

  override def toString: String = f"$hours%02d:$minutes%02d"

  override def compare(that: Time): Int = this - that
}
