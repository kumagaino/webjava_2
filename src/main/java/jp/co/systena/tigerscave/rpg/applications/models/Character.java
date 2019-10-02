package jp.co.systena.tigerscave.rpg.applications.models;

public class Character extends CharacterBase{

  @Override
  public String getJob() {
    return this.jobName;
  }

  @Override
  public String getName() {
    return this.charaName;
  }

  @Override
  public String getAttackWay() {
    return this.attackWay;
  }

}
