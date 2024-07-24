package com.kopo.dicegame;

import java.util.ArrayList;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		DB db = new DB();
		ArrayList<Dice> list = db.selectData();
		String htmlText = "";
		for (int i = 0; i < list.size(); i++) {
			htmlText = htmlText + "<tr>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).idx;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).user;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).com;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).result;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).created;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "</tr>";
		}
		model.addAttribute("list", htmlText);
		return "list";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Locale locale, Model model) {
		DB db = new DB();
		db.createTable();
		model.addAttribute("message", "테이블이 생성되었습니다.");
		return "message";
	}
	
	@RequestMapping(value = "/play", method = RequestMethod.GET)
	public String play(Locale locale, Model model) {
		return "form";
	}

	
	@RequestMapping(value = "/play_action", method = RequestMethod.GET)
	public String playAction(Locale locale, Model model) {
		Dice dice = new Dice();
		dice.roll();
		DB db = new DB();
		db.insertData(dice);
		return "redirect:/";
	}
}
