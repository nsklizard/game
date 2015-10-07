package actors

import akka.actor.{Props, ActorSystem, Actor}
import helloworld.MyServiceActor

/**
 * Created by lizard on 20.09.2015.
 */
object HelloActor extends App{
  val system = ActorSystem("mysys")
  val cnt = system.actorOf(Props[CCCounter], "counter")

  val r = cnt!SumDigits(1, 2)


//  val c: CCCounter = new CCCounter
////  val r:Result = c!SumDigits(1, 2)
//  println(r)
  //test
}

case class SumDigits(a:Int, b:Int)
case class Result(c:Int)

class CCCounter extends Actor{
  override def receive: Receive = {
    case SumDigits(a,b) => println(a+b)
  }
}