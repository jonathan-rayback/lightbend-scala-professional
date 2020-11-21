package com.lightbend.training.scalatrain

import scala.collection.immutable.Seq

sealed abstract class TrainInfo {
  def number: Int
}

case class InterCityExpress(number: Int, hasWifi: Boolean = false) extends TrainInfo
case class RegionalExpress(number: Int) extends TrainInfo
case class BavarianRegional(number: Int) extends TrainInfo

case class Train(info: TrainInfo, schedule: Seq[(Time, Station)]) {
  require(schedule.length >= 2, "Must have at least two stations in the schedule for this train.")
  val stations: Seq[Station] = schedule.map(_._2)

}
