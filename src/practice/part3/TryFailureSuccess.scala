package practice.part3

import scala.util.Random
import scala.util.Try
import scala.util.Success
import scala.util.Failure

object TryFailureSuccess extends App {

  val host = "localhost"
  val port = "8080"

  val connection:Try[Connection] = Try(HttpServer.getConnection(host, port))
  
  val page = connection.flatMap(x=> Try(x.get("/home")))
  
  println(page)
  
  page.foreach(println)
  
  for{
   connection <- Try(HttpServer.getConnection(host, port))
   page <- Try(connection.get("/home"))
  } println(page)
  
  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html></html>"
      else throw new RuntimeException("page not found")
    }
  }
  object HttpServer {
    def getConnection(host: String, port: String): Connection = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("No connection")
    }
  }
  
  val value = fun()
  value match {
    case Success(v) => println(v)
    case Failure(e) => println(e)
  }
  
  def fun():Try[String]={
    Try{
      val a="roshan"
      if(a=="roshan") "success"
      else throw new RuntimeException("error")
    }
   
  }
}