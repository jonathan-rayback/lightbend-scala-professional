package com.lightbend.training.scalatrain

class JourneyPlanner(val trains: Set[Train]) {

  val stations: Set[Station] = trains.flatMap(_.stations)

  def trainsAt(station: Station): Set[Train] = trains.filter(_.stations.contains(station))

  def stopsAt(station: Station): Set[(Time, Train)] = for {
    train <- trainsAt(station)
    stop <- train.schedule if stop._2 == station
  } yield (stop._1, train)

  // def isShortTripF(from: Station, to: Station): Boolean = {
  //   trains.exists(train => train.stations.dropWhile(station => station != from).drop(1).take(2).contains(to))
  // }

  def isShortTrip(from: Station, to: Station): Boolean = {
    trains.exists(_.stations.dropWhile(station => station != from) match {
      case `from` +: `to` +: _      => true
      case `from` +: _ +: `to` +: _ => true
      case _                        => false
    })
  }

}
