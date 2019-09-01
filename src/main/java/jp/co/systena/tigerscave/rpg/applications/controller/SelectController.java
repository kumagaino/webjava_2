package jp.co.systena.tigerscave.rpg.applications.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.rpg.applications.models.CharacterList;
import jp.co.systena.tigerscave.rpg.applications.models.CommandForm;
import jp.co.systena.tigerscave.rpg.applications.models.CommandResult;
import jp.co.systena.tigerscave.rpg.applications.models.JobForm;

@Controller
public class SelectController {

  @Autowired
  HttpSession session;

  @RequestMapping(value="/Select", method=RequestMethod.GET)
  public ModelAndView select(ModelAndView mav){

    // フォーム
    mav.addObject("jobForm", new JobForm());

    // Viewのテンプレート名を設定
    mav.setViewName("Select");

    return mav;
  }

  @RequestMapping(value="/Command", method=RequestMethod.GET)
  public ModelAndView command(ModelAndView mav, JobForm jobForm){

    CharacterList Character = (CharacterList) session.getAttribute("Character");
    if (Character == null) {
      Character = new CharacterList();
      Character.setJobName(jobForm.getJobName());
      Character.setCharaName(jobForm.getCharaName());
      session.setAttribute("Character", Character);
    }
    mav.addObject("Character", Character);

    // コマンド用のフォーム
    CommandForm commandForm = new CommandForm();
    mav.addObject("commandForm", commandForm);

    // Viewのテンプレート名を設定
    mav.setViewName("Command");

    return mav;
  }

  @RequestMapping(value="/Result", method=RequestMethod.GET)
  public ModelAndView result(ModelAndView mav, CommandForm commandForm){

    CharacterList Character = (CharacterList) session.getAttribute("Character");

    CommandResult CommandLog = new CommandResult();
    CommandLog.switchCommand(commandForm, Character);
    mav.addObject("CommandLog", CommandLog);

    // Viewのテンプレート名を設定
    mav.setViewName("Result");

    return mav;
  }
}
