package practice.part1

object FunctionCalls extends App{
  
  def infinite():Int = 1+infinite()
  
  def test(x:Int, y: =>Int) = print(x)
  
  
  test(infinite(), 6) //doesnt work
  test(6, infinite()) //works
}