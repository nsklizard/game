package game

/**
 * Created by lizard on 23.09.2015.
 */
class DBActor {
  //когда-нибудь эта штука будет лазить в БД за данными

  def getSkills():Array[Skills]={
    Array(new Skills(1L,"Attack",0))
  }
}
