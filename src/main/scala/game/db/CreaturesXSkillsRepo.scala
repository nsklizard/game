package game.db

import io.strongtyped.active.slick.{CrudActions, ActiveRecord, EntityActions}
import io.strongtyped.active.slick.Lens._
import slick.ast.BaseTypedType
import slick.driver.PostgresDriver

/**
 * Created by lizard on 03.10.2015.
 */

case class CreaturesXSkills(creatureId: Long, skillId: Long, skillPower: Long, id: Option[Long] = None)

object CreaturesXSkillsRepo extends EntityActions(PostgresDriver) {

  import jdbcProfile.api._

  val baseTypedType = implicitly[BaseTypedType[Id]] //

  type Entity = CreaturesXSkills
  type Id = Long
  type EntityTable = CreaturesXSkillsTable

  val tableQuery = TableQuery[CreaturesXSkillsTable] //

  def $id(table: CreaturesXSkillsTable): Rep[Id] = table.id //

  val idLens = lens { creaturesXSkills: CreaturesXSkills => creaturesXSkills.id } { (creaturesXSkills, id) => creaturesXSkills.copy(id = id) }


  class CreaturesXSkillsTable(tag: Tag) extends Table[CreaturesXSkills](tag, "creaturesxskills") {

    def id = column[Id]("id", O.PrimaryKey, O.AutoInc)

    def creatureId = column[Long]("creature_id")

    def skillId = column[Long]("skill_id")

    def skillPower = column[Long]("skill_power")

    def * = (creatureId, skillId, skillPower, id.?) <>(CreaturesXSkills.tupled, CreaturesXSkills.unapply)


    val creatures = TableQuery[PlayersRepo.PlayerTable]

    def creature = foreignKey("creaturesxskills_creature_fk", creatureId, creatures)(_.id)


    val skills = TableQuery[PlayersRepo.PlayerTable]

    def skill = foreignKey("creaturesxskills_skill_fk", skillId, skills)(_.id)
  }


  implicit class EntryExtensions(val entity: CreaturesXSkills) extends ActiveRecord[CreaturesXSkills] {
    override val repository: CrudActions = CreaturesXSkillsRepo
  }

}
