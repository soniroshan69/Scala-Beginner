package practice.part3

object MapExercise extends App{
  
  def add(network:Map[String, Set[String]], person:String):Map[String, Set[String]]={
    network+(person->Set())
  }
  
  def remove(network:Map[String, Set[String]], person:String):Map[String, Set[String]]={
    val friends = network(person)
    def removeConnections(friends:Set[String], networkAcc:Map[String, Set[String]]):Map[String, Set[String]]={
      if(friends.isEmpty) networkAcc
      else removeConnections(friends.tail, unfriend(network, person, friends.head))
    }
    removeConnections(friends, network)
  }
  
  def friend(network:Map[String, Set[String]], person:String, friend:String):Map[String, Set[String]]={
    val friends1 = network(person)
    val friends2 = network(friend)
    network+(person->(friends1+friend))+(friend->(friends2+person))
  }
  
  def unfriend(network:Map[String, Set[String]], person:String, friend:String):Map[String, Set[String]]={
    val friends1 = network(person)
    val friends2 = network(friend)
    network+(person->(friends1-friend))+(friend->(friends2-person))
  }
  
  def frindsCount(network:Map[String, Set[String]], person:String):Int={
    network(person).size
  }
  
  def mostFrinds(network:Map[String, Set[String]]):String={
    network.maxBy(pair => pair._2.size)._1
  }
  
  def noFriends(network:Map[String, Set[String]]):Int={
    network.count(pair => pair._2.isEmpty)
  }
  
  def socialConn(network:Map[String, Set[String]], p1:String, p2:String):Boolean={
    
    var friendsOfP1 = network(p1) 
    def socialConnAux(p2:String, alreadyChecked: Set[String], needToCheck:Set[String]):Boolean={
      if(needToCheck.size==0) false
      else if(p2==needToCheck.head) true
      else if(alreadyChecked.contains(needToCheck.head)) socialConnAux(p2, alreadyChecked, needToCheck.tail)
      else socialConnAux(p2, alreadyChecked+needToCheck.head, needToCheck.tail++network(needToCheck.head))
    }
    
    socialConnAux(p2, Set(), friendsOfP1)
  }
  
}