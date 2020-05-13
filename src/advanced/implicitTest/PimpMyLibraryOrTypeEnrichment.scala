package advanced.implicitTest

object PimpMyLibraryOrTypeEnrichment extends App{
  
  implicit class RichInt(n:Int){
    def isEven = n%2==0
    //def times(f:()=>)
  }
  println(2.isEven)
  
  //compiler does not do multiple implicit searches
  //isOdd method will not be avaialbe
  /*implicit class RicherInt(n:RichInt){
    def isOdd = n.n%2!=0
  }*/
  
  implicit class RichString(s:String){
    def asInt = Integer.parseInt(s)
    def encrypt(places:Int) = s(1)
  }
}