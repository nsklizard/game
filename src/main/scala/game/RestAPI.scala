package game

import helloworld.TestRequestJSON._
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

/**
 * Created by lizard on 22.09.2015.
 */
object RestAPI extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val registerUserFormat = jsonFormat4(RegisterUser)
  implicit val loginFormat = jsonFormat2(Login)

  implicit val getSkillsFormat = jsonFormat1(GetSkills)
//  implicit val createCreatureFormat = jsonFormat3(CreateCreature)
  implicit val settleCreatureFormat = jsonFormat4(SettleCreature)
  implicit val killCreatureFormat = jsonFormat2(KillCreature)
}

case class RegisterUser(name:String, email:String, login:String, password:String)
case class Login(login:String, password:String)



case class GetSkills(uid:Long)
case class CreateCreature(uid:Long, name:String, skills:Array[Skills])
case class SettleCreature(uid:Long, creatureId:Long, x:Long, y:Long)
case class KillCreature(uid:Long, creatureId:Long)
