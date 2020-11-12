package com.lightbend.training.scalatrain

class JourneyPlanner(val trains: Set[Train]) {

  val stations: Set[Station] = trains.flatMap(
    _.stations
  )

}
