package practice.part2

object ExceptionTest extends App{
  
  def divide(x:Int, y:Int)={
    if(y==0) throw new Exception
    else x/y
  }
  
  def divide1(x:Int, y:Int)={
    throw new Exception
  }
  
  def divide3(x:Int, y:Int)={
    val result = try{
      1
    }catch{
    case e:Exception => 1.2
    }
  }
}