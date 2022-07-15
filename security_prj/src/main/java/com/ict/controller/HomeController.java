package com.ict.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.simple.parser.ParseException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.ict.domain.AuthVO;
import com.ict.domain.CustomUser;
import com.ict.domain.MemberVO;
import com.ict.naver.NaverLoginBO;
import com.ict.service.SecurityService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private NaverLoginBO naverLoginBO;
	
	@Autowired
	private SecurityService service;
	
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	@RequestMapping(value="/naverLogin", method = RequestMethod.GET)
	public String naverLogin(HttpSession session) {
		
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		
		System.out.println("네이버 로그인 권한 url : " + naverAuthUrl);
		
		session.setAttribute("url", naverAuthUrl);
		
		return "redirect:/customLogin";
		
	}// naverLogin END
	
	
	@RequestMapping(value = "/naver/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
		throws IOException, ParseException  {
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		
		apiResult = naverLoginBO.getUserProfile(oauthToken);
		
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		System.out.println("json파싱 API : " + response_obj);
		
		String id = (String) response_obj.get("id");
		String email = (String) response_obj.get("email");
		String userName = (String) response_obj.get("nickname");
		
		MemberVO user = new MemberVO();
		
		List<AuthVO> authList = new ArrayList<AuthVO>();
		
		AuthVO auth = new AuthVO();
		
		UUID uuid = UUID.randomUUID();
		auth.setUserid("NAVER_" + id);
		auth.setAuth("ROLE_MEMBER");
		authList.add(auth);
		
		user.setUserid("NAVER_" + id);
		user.setAuthList(authList);
		user.setUserpw(uuid.toString());
		user.setUserName(userName);
		
		System.out.println("INSERT하기 전 마지막 체크 : " + user);
		
		if(service.read(user.getUserid()) == null) {
			service.insertMember(user);
		}
		
		CustomUser customUser = new CustomUser(user);
		
		// 시큐리티 권한을 직접 세팅해줍니다.
		Authentication authentication = new UsernamePasswordAuthenticationToken(customUser, null, customUser.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/secu/member";
				
	}

	
}