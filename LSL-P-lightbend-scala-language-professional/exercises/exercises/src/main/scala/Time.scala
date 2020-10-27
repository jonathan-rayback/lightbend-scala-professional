class Time (val hours : Int, val minutes : Int) {
  // TODO - Verify hours is within 0 and 23
  // TODO - Verify minutes is whithin 0 and 59
  val asMinutes : Int = (hours * 60) + minutes

  def minus (that : Time) : Int = asMinutes - that.asMinutes
  
  // define infix operator
  def - (that : Time) : Int = minus(that)
}