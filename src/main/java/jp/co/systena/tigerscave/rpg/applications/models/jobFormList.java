package jp.co.systena.tigerscave.rpg.applications.models;

import java.util.ArrayList;
import java.util.List;

public class jobFormList {

  public List<JobForm> getJobFormList() {
    List<JobForm> jobFormList = new ArrayList<JobForm>();

    for (int i=0; i<4; i++) {
      JobForm jobForm = new JobForm();
      jobFormList.add(jobForm);
    }

    return jobFormList;
  }
}
