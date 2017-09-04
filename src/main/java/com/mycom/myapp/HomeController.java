package com.mycom.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycom.myapp.dao.TickerDAO;
import com.mycom.myapp.dao.TranConfigDAO;
import com.mycom.myapp.service.DbService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	private TickerDAO tickerDAO;
	@Inject
	private TranConfigDAO tranConfigDAO;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "test";
	}

	// <--- 추가
	@Autowired
	private SqlSession sqlSession;
	// 추가 --->

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		// <--- 추가
		HashMap<String, String> input = new HashMap<String, String>();
		input.put("name", "han");
		List<HashMap<String, String>> outputs = sqlSession.selectList("userControlMapper.selectSample", input);
		System.out.println(outputs.toString());
		model.addAttribute("list", outputs.toString());
		// 추가 --->

		return "list";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/tickerList", method = RequestMethod.GET)
	public String tickerList(Model model) throws Exception {
		// <--- 추가
		model.addAttribute("tickerList", tickerDAO.getTickerList());
		// 추가 --->

		return "ticker/tickerList";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/tickerStaticList", method = RequestMethod.GET)
	public String tickerStaticList(Model model) throws Exception {
		// <--- 추가
		model.addAttribute("tickerStaticList", tickerDAO.getTickerStaticList());
		// 추가 --->

		return "ticker/tickerStaticList";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/tranConfigList", method = RequestMethod.GET)
	public String tranConfigList(Model model) throws Exception {
		// <--- 추가
		model.addAttribute("tranConfigList", tranConfigDAO.getTranConfigList());
		// 추가 --->

		return "tranConfig/tranConfigList";
	}	

}
