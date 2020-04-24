package practice.part2
import java.util.Date
import java.sql.{Date => SqlDate}
object PackageAndImports extends App{
  
  val javaDate = new Date
  val sqlDate = new SqlDate(2020, 4, 16)
  
  println(SPEED_OF_LIGHT)
  
}