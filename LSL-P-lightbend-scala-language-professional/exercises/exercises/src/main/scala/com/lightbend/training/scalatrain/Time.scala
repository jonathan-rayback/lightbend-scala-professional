package com.lightbend.training.scalatrain

object Time {

  //  determines normalized hour of day [0-23] and leftover minutes [0-59] from any number of minutes, returns a Time object
  def fromMinutes(minutes: Int): Time = {
    val hoursFromMinutes = ((minutes / 60).toInt) % 24
    val leftoverMinutes = minutes % 60
    Time(hoursFromMinutes, leftoverMinutes)
  }
}

case class Time(hours: Int = 0, minutes: Int = 0) {
  require(hours <= 23 && hours >= 0, "Hours must be between 0 and 23.")
  require(minutes <= 59 && minutes >= 0, "Minutes must be between 0 and 59.")

  val asMinutes: Int = (hours * 60) + minutes

  def minus(that: Time): Int = asMinutes - that.asMinutes

  // define infix operator
  def -(that: Time): Int = minus(that)
}
