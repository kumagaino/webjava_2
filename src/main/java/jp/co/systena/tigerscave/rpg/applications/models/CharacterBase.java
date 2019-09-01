package jp.co.systena.tigerscave.rpg.applications.models;

public abstract class CharacterBase {
  protected String jobName;
  protected String charaName;
  protected String attackWay;
  protected String RecoveryWay;

  public void setJobName(String job){
    this.jobName = job;
    switch (job) {
      case "戦士":
        this.attackWay = "剣";
        break;
      case "魔法使い":
        this.attackWay = "まほう";
        break;
      case "武闘家":
        this.attackWay = "拳";
        break;
      default:
        this.attackWay = "じたばた";
    }

    switch (job) {
      case "魔法使い":
        this.RecoveryWay = "まほう";
        break;
      default:
        this.RecoveryWay = "やくそう";
    }
  }

  public abstract String getJob();

  public void setCharaName(String name){
    this.charaName = name;
  }

  public abstract String getName();
  public abstract String getAttackWay();
}
