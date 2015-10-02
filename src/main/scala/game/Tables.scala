package game

/**
 * Created by lizard on 23.09.2015.
 */

import io.strongtyped.active.slick.{CrudActions, ActiveRecord, EntityActions}
import slick.ast.BaseTypedType
import slick.driver.PostgresDriver
import slick.driver.PostgresDriver.api._
import io.strongtyped.active.slick.Lens._
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by lizard on 15.09.2015.
 */

object Types {

  case class Skill(id: Option[Long],
                   name: String,
                   power: Long)

}


object Tables {

  class Skills(tag: Tag) extends Table[Types.Skill](tag, "skills") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def power = column[Long]("power")

    def * = (id.?, name, power) <>(Types.Skill.tupled, Types.Skill.unapply _)
  }

}


case class Coffee(name: String, id: Option[Int] = None)

object Repo {

  object CoffeeRepo extends EntityActions(PostgresDriver) {
    import jdbcProfile.api._
//    import slick.driver.PostgresDriver.api._


    val baseTypedType = implicitly[BaseTypedType[Id]] //

    type Entity = Coffee
    type Id = Int
    type EntityTable = CoffeeTable

    val tableQuery = TableQuery[CoffeeTable] //

    def $id(table: CoffeeTable): Rep[Id] = table.id //

    val idLens = lens { coffee: Coffee => coffee.id } { (coffee, id) => coffee.copy(id = id) }


    class CoffeeTable(tag: Tag) extends Table[Coffee](tag, "COFFEE") {
      //
      def name = column[String]("NAME")

      def id = column[Id]("ID", O.PrimaryKey, O.AutoInc)

      def * = (name, id.?) <>(Coffee.tupled, Coffee.unapply)
    }

    def findByName(name: String): DBIO[Seq[Coffee]] = {
      tableQuery.filter(_.name === name).result
    }
  }

  implicit class EntryExtensions(val model: Coffee) extends ActiveRecord[Coffee] {
    //    val repository = CoffeeRepo
    override def entity: Coffee = model

    override val repository: CrudActions = CoffeeRepo
  }

//  val saveAction = Coffee("Colombia").save()
}
