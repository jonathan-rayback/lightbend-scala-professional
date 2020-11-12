package com.lightbend.training.scalatrain

import scala.collection.immutable.Seq

case class Train(kind: String, number: Int, schedule: Seq[(Time, Station)]) {
  require(schedule.length >= 2, "Must have at least two stations in the schedule for this train.")
  val stations: Seq[Station] = schedule.map(_._2)

}
