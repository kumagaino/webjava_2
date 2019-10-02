package jp.co.systena.tigerscave.rpg.applications.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.rpg.applications.models.Character;
import jp.co.systena.tigerscave.rpg.applications.models.CommandForm;
import jp.co.systena.tigerscave.rpg.applications.models.CommandResult;
import jp.co.systena.tigerscave.rpg.applications.models.JobForm;
import jp.co.systena.tigerscave.rpg.applications.models.JobForm.Detail;
import jp.co.systena.tigerscave.rpg.applications.models.JobForms;
import jp.co.systena.tigerscave.rpg.applications.models.MemberForm;

@Controller
public class SelectController {

  @Autowired
  HttpSession session;

  @RequestMapping(value="/memberDecision", method=RequestMethod.GET)
  public ModelAndView decision(ModelAndView mav) {

    // フォーム
    MemberForm memberForm = new MemberForm();
    mav.addObject("jobFormList",memberForm);

    // Viewのテンプレート名を設定
    mav.setViewName("memberDecision");

    return mav;
  }

  @RequestMapping(value="/Select", method=RequestMethod.GET)
  public ModelAndView select(ModelAndView mav, MemberForm memberForm){

    // フォーム
    JobForms jobForms = new JobForms();
    List<Detail> details = jobForms.getDetails(memberForm);
    mav.addObject("details",details);

    // Viewのテンプレート名を設定
    mav.setViewName("Select");

    return mav;
  }

  @RequestMapping(value="/Command", method=RequestMethod.POST)
  public ModelAndView command(ModelAndView mav, JobForm jobForm, BindingResult result){

    Character CharacterSession = (Character) session.getAttribute("Character");
    if (CharacterSession == null) {
      // フォームの内容をキャラクタークラスに入れる。
      List<Character> CharacterList = new ArrayList<Character>();
      List<Detail> details = jobForm.getDetails();
      details.forEach(s -> {
        Character character = new Character();
        character.setJobName(s.getJobName());
        character.setCharaName(s.getCharaName());
        CharacterList.add(character);
      });
      session.setAttribute("Character", CharacterList);
      mav.addObject("CharacterList", CharacterList);
    } else {
      // 初回以外はセッションを利用
      mav.addObject("CharacterList", CharacterSession);
    }

    // コマンド用のフォーム
    CommandForm commandForm = new CommandForm();
    mav.addObject("commandForm", commandForm);

    // Viewのテンプレート名を設定
    mav.setViewName("Command");

    return mav;
  }

  @RequestMapping(value="/Result", method=RequestMethod.GET)
  public ModelAndView result(ModelAndView mav, CommandForm commandForm){

    Character CharacterSession = (Character) session.getAttribute("Character");

    CommandResult CommandLog = new CommandResult();
    CommandLog.switchCommand(commandForm, CharacterSession);
    mav.addObject("CommandLog", CommandLog);

    // Viewのテンプレート名を設定
    mav.setViewName("Result");

    return mav;
  }

}
