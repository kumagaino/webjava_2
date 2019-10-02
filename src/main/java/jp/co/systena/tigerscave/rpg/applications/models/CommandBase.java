package jp.co.systena.tigerscave.rpg.applications.models;

public abstract class CommandBase {
  protected String commandLog;

  public void switchCommand(CommandForm commandForm, Character Character) {

    switch (commandForm.getNextAction()) {
      case "たたかう":
        setCommandLog(Character);
        break;
      case "かいふく":
        setRecoveryLog(Character);
    }

  }
  public void setCommandLog(Character Character) {
    this.commandLog = Character.charaName + "は" + Character.attackWay + "で攻撃した";
  }

  public String getCommandLog() {
    return this.commandLog;
  }

  public void setRecoveryLog(Character Character) {
    this.commandLog = Character.charaName + "は" + Character.RecoveryWay + "でかいふくした";
  }

  public String getRecoveryLog() {
    return this.commandLog;
  }
}
