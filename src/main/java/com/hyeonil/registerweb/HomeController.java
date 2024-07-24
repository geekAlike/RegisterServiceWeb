package com.hyeonil.registerweb;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		
		
		if(session.getAttribute("is_login") == null || !(boolean) session.getAttribute("is_login")) {
			return "redirect:/login";
		}
		
		
		if(session.getAttribute("login_userType") != null && session.getAttribute("login_userType").equals("관리자")) {
			return "redirect:/adminHome";
		}
		
		
		
		DB db = new DB();
		ArrayList<Member> list = db.selectData();
		String htmlText = "";
		for (int i = 0; i < list.size(); i++) {
			String userTypeStr = list.get(i).userType == 1 ? "관리자" : "일반회원";
			String genderStr = list.get(i).gender == 1 ? "남" : "여";
			htmlText = htmlText + "<tr>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).idx;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + userTypeStr;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).name;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).regId;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + genderStr;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).address;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).created;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + "<li><a href='/registerweb/detail?idx=" + list.get(i).idx + "'>디테일</a></li>";
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "</tr>";
		}
		model.addAttribute("memberList", htmlText);
		model.addAttribute("login_id", session.getAttribute("login_id"));
		return "home";
	}
	
	
	@RequestMapping(value = "/adminHome", method = RequestMethod.GET)
	public String adminHome(Locale locale, Model model, HttpSession session) {
		
		
		
		DB db = new DB();
		ArrayList<Member> list = db.selectData();
		String htmlText = "";
		for (int i = 0; i < list.size(); i++) {
			String userTypeStr = list.get(i).userType == 1 ? "관리자" : "일반회원";
			String genderStr = list.get(i).gender == 1 ? "남" : "여";
			htmlText = htmlText + "<tr>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).idx;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + userTypeStr;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).name;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).regId;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + genderStr;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).address;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).created;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + "<li><a href='/registerweb/detail?idx=" + list.get(i).idx + "'>디테일</a></li>";
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + "<li><a href='/registerweb/update?idx=" + list.get(i).idx + "'>수정</a></li>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + "<li><a href='/registerweb/delete?idx=" + list.get(i).idx + "'>삭제</a></li>";
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "</tr>";
		}
		model.addAttribute("memberList", htmlText);
		model.addAttribute("login_id", session.getAttribute("login_id"));
		return "adminHome";
	}
	
	
	
	
	@RequestMapping(value = "/memberList", method = RequestMethod.GET)
	public String memberList(Locale locale, Model model) {
		DB db = new DB();
		ArrayList<Member> list = db.selectData();
		String htmlText = "";
		for (int i = 0; i < list.size(); i++) {
			String userTypeStr = list.get(i).userType == 1 ? "관리자" : "일반회원";
			//int userTypeStr2 = list.get(i).userType;
			String genderStr = list.get(i).gender == 1 ? "남" : "여";
			htmlText = htmlText + "<tr>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).idx;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + userTypeStr;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).name;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).regId;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + genderStr;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).address;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "<td>";
			htmlText = htmlText + list.get(i).created;
			htmlText = htmlText + "</td>";
			htmlText = htmlText + "</tr>";
		}
		model.addAttribute("memberList", htmlText);
		return "memberList";
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Locale locale, Model model) {
		DB db = new DB();
		db.createTable();
		model.addAttribute("message", "테이블이 생성되었습니다.");
		return "message";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String play(Locale locale, Model model) {
		return "registerForm";
	}

	/*
	@RequestMapping(value = "/register_action", method = RequestMethod.GET)
	public String playAction(Locale locale, Model model
			, @RequestParam("name") String name
			, @RequestParam("regId") String regId
			, @RequestParam("password") String password
			, @RequestParam("gender") String gender
			, @RequestParam("address") String address) {
		
		String now = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
		Member member = new Member();
		member.register(name, regId, password, gender, address, now);
		DB db = new DB();
		db.insertData(member);
		return "redirect:/";
	}
	*/
	
	@RequestMapping(value = "/register_action", method = RequestMethod.POST)
	public String playAction(Locale locale, Model model, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String userTypeStr = request.getParameter("userType");
		//String userType = request.getParameter("userType");
		String name = request.getParameter("name");
		String regId = request.getParameter("regId");
		String password = request.getParameter("password");
		String genderStr = request.getParameter("gender");
		//String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		password = Sha256EncryptUtil.shaEncoder(password);
		
		// 전달받은 성별 문자열을 정수형으로 변환하여 저장
		int userType = userTypeStr.equalsIgnoreCase("관리자") ? 1 : 0;
		int gender = genderStr.equalsIgnoreCase("남") ? 1 : 0;
		
		System.out.println(gender);
		
		String now = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
		Member member = new Member();
		
		member.register(userType, name, regId, password, gender, address, now);
		DB db = new DB();
		db.insertData(member);
		return "redirect:/";
	}
	
	
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detailPage(Locale locale, Model model
			, @RequestParam("idx") int idx) {
		DB db = new DB();
		Member member = db.selectDetailData(idx);
		System.out.println( member.name);
		String userTypeStr = member.userType == 1 ? "관리자" : "일반회원";
		String genderStr = member.gender == 1 ? "남" : "여";
		String htmlText = "";
		
		htmlText = htmlText + "<tr>";
		htmlText = htmlText + "<td>";
		htmlText = htmlText + member.idx;
		htmlText = htmlText + "</td>";
		htmlText = htmlText + "<td>";
		htmlText = htmlText + userTypeStr;
		htmlText = htmlText + "</td>";
		htmlText = htmlText + "<td>";
		htmlText = htmlText + member.name;
		htmlText = htmlText + "</td>";
		htmlText = htmlText + "<td>";
		htmlText = htmlText + member.regId;
		htmlText = htmlText + "</td>";
		htmlText = htmlText + "<td>";
		htmlText = htmlText + genderStr;
		htmlText = htmlText + "</td>";
		htmlText = htmlText + "<td>";
		htmlText = htmlText + member.address;
		htmlText = htmlText + "</td>";
		htmlText = htmlText + "<td>";
		htmlText = htmlText + member.created;
		htmlText = htmlText + "</td>";		
		htmlText = htmlText + "<td>";
		htmlText = htmlText + "<li><a href='/registerweb/detail?idx=" + member.idx + "'>디테일</a></li>";
		htmlText = htmlText + "</td>";
		htmlText = htmlText + "<td>";
		htmlText = htmlText + "<li><a href='/registerweb/update?idx=" + member.idx + "'>수정</a></li>";
		htmlText = htmlText + "<td>";
		htmlText = htmlText + "<li><a href='/registerweb/delete?idx=" + member.idx + "'>삭제</a></li>";
		htmlText = htmlText + "</td>";		
		htmlText = htmlText + "</tr>";
		model.addAttribute("memberList", htmlText);
		return "detail";
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Locale locale, Model model
			, @RequestParam("idx") int idx) {
		
		String htmlText = "";
		
		DB db = new DB();
		if(db.deleteData(idx)) {
			htmlText = "삭제되었습니다";
		}else{
			htmlText = "삭제되지 않았습니다";
		};
		
		model.addAttribute("message", htmlText);
		return "message";
	}
	
	
	
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updatePage(Locale locale, Model model
			, @RequestParam("idx") int idx) {
		DB db = new DB();
		Member member = (Member) db.selectDetailData(idx);
		
		/*
		System.out.println( member.name);
		String htmlText = "";
		
		htmlText = htmlText + "<tr>";
		htmlText = htmlText + "<td>";
		htmlText = htmlText + member.idx;
		htmlText = htmlText + "</td>";
		htmlText = htmlText + "<td>";
		htmlText = htmlText + member.name;
		htmlText = htmlText + "</td>";
		htmlText = htmlText + "<td>";
		htmlText = htmlText + member.regId;
		htmlText = htmlText + "</td>";
		htmlText = htmlText + "<td>";
		htmlText = htmlText + member.gender;
		htmlText = htmlText + "</td>";
		htmlText = htmlText + "<td>";
		htmlText = htmlText + member.address;
		htmlText = htmlText + "</td>";
		htmlText = htmlText + "<td>";
		htmlText = htmlText + member.created;
		htmlText = htmlText + "</td>";		
		htmlText = htmlText + "<td>";
		htmlText = htmlText + "<li><a href='/registerweb/detail?idx=" + member.idx + "'>디테일</a></li>";
		htmlText = htmlText + "</td>";
		htmlText = htmlText + "<td>";
		htmlText = htmlText + "<li><a href='/registerweb/update?idx=" + member.idx + "'>수정</a></li>";
		htmlText = htmlText + "<td>";
		htmlText = htmlText + "<li><a href='/registerweb/delete?idx=" + member.idx + "'>삭제</a></li>";
		htmlText = htmlText + "</td>";		
		htmlText = htmlText + "</tr>";
		model.addAttribute("memberList", htmlText);
		return "detail";
*/	
		
		String userTypeStr = member.userType == 1 ? "관리자" : "일반회원";
		String genderStr = member.gender == 1 ? "남" : "여";
		
		model.addAttribute("idx", member.idx);	
		model.addAttribute("userType", userTypeStr);	
		model.addAttribute("name", member.name);
		model.addAttribute("regId", member.regId);
		model.addAttribute("password", member.password);
		model.addAttribute("gender", genderStr);
		model.addAttribute("address", member.address);
		model.addAttribute("created", member.created);
		
		return "updateForm";
	}
	


	@RequestMapping(value = "/update_action", method = RequestMethod.GET)
	public String updateAction(Locale locale, Model model
			, @RequestParam("idx") int idx
			, @RequestParam("userType") String userType
			, @RequestParam("name") String name
			, @RequestParam("regId") String regId
			, @RequestParam("password") String password
			, @RequestParam("gender") String gender
			, @RequestParam("address") String address
			, @RequestParam("created") String created) {
		
		// 전달받은 성별 문자열을 정수형으로 변환하여 저장
		int userTypeInt = userType.equalsIgnoreCase("관리자") ? 1 : 0;
		int genderInt = gender.equalsIgnoreCase("남") ? 1 : 0;
		password = Sha256EncryptUtil.shaEncoder(password);
		
		DB db = new DB();
		Member member = (Member) db.selectDetailData(idx);
		member.register(userTypeInt, name, regId, password, genderInt, address, created );
		System.out.println(password);
		db.updateData(member);
		model.addAttribute("message", "데이터가 수정되었습니다.");
		return "message";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String LoginPage(Locale locale, Model model) {
	
		return "loginForm";
	}

	
	
	@RequestMapping(value = "/logout_action", method = RequestMethod.GET)
	public String logoutAction(Locale locale, Model model, HttpSession session) {
		
		
		if(session.getAttribute("is_login") != null) {
			System.out.println("로그아웃 되었습니다.");
			model.addAttribute("message", "로그아웃 되었습니다.");
			session.setAttribute("is_login", false);
			session.removeAttribute("login_id");
		} else {
			System.out.println("로그아웃 실패하였습니다.");
			model.addAttribute("message", "로그아웃 실패하였습니다.");
		}
		
		return "message";
	}
	

	@RequestMapping(value = "/login_action", method = RequestMethod.GET)
	public String loginAction(Locale locale, Model model, HttpSession session
			, @RequestParam("regId") String regId
			, @RequestParam("password") String password) {
		DB db = new DB();
		password = Sha256EncryptUtil.shaEncoder(password);
		
		boolean result = db.loginData(regId, password);
		
		Member member = (Member) db.selectDetailData(regId);
		
		String userTypeStr = member.userType == 1 ? "관리자" : "일반회원";
		

		//System.out.println("regId:는" + member.regId + "password는:" + member.password);
		
		if(result) {
			System.out.println("로그인 되었습니다.");
			model.addAttribute("message", "로그인 되었습니다.");
			session.setAttribute("is_login", true);
			session.setAttribute("login_id", regId);
			session.setAttribute("login_userType", userTypeStr);
		} else {
			System.out.println("로그인 실패하였습니다.");
			model.addAttribute("message", "로그인 실패하였습니다.");
			session.setAttribute("is_login", false);
		}
		
		return "message";
	}

	
	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Locale locale, Model model) {
		
		return "<h2>Hello</h2>";
	}

	@ResponseBody
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public HashMap<String, String> test2(Locale locale, Model model) {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("version", "1.0");
		result.put("name", "test");
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/test3", method = RequestMethod.GET)
	public ArrayList<String> test3(Locale locale, Model model) {
		ArrayList<String> result = new ArrayList<String>();
		result.add("apple");
		result.add("banana");
		result.add("cherry");
		
		return result;
	}

	
	
}
