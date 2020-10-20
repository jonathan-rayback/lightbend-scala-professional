import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.diagrams.Diagrams

class HelloSpec extends AnyFunSuite with Diagrams {
  test("Hello should start with H") {
    assert("Hello".startsWith("H"))
  }
}
