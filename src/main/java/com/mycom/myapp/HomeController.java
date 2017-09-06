package com.mycom.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycom.myapp.dao.TickerDAO;
import com.mycom.myapp.dao.TranConfigDAO;
import com.mycom.myapp.service.DbService;
import com.mycom.myapp.vo.TranConfigVO;

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

	// <--- �߰�
	@Autowired
	private SqlSession sqlSession;
	// �߰� --->

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

		// <--- �߰�
		HashMap<String, String> input = new HashMap<String, String>();
		input.put("name", "han");
		List<HashMap<String, String>> outputs = sqlSession.selectList("userControlMapper.selectSample", input);
		System.out.println(outputs.toString());
		model.addAttribute("list", outputs.toString());
		// �߰� --->

		return "list";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/tickerList", method = RequestMethod.GET)
	public String tickerList(Model model) throws Exception {
		// <--- �߰�
		model.addAttribute("tickerList", tickerDAO.getTickerList());
		// �߰� --->

		return "ticker/tickerList";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/tickerStaticList", method = RequestMethod.GET)
	public String tickerStaticList(Model model) throws Exception {
		// <--- �߰�
		model.addAttribute("tickerStaticList", tickerDAO.getTickerStaticList());
		// �߰� --->

		return "ticker/tickerStaticList";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/tranConfigList", method = RequestMethod.GET)
	public String tranConfigList(Model model) throws Exception {
		// <--- �߰�
		model.addAttribute("tranConfigList", tranConfigDAO.getTranConfigList());
		// �߰� --->

		return "tranConfig/tranConfigList";
	}	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/tranConfigSave", method = RequestMethod.POST)
	public String tranConfigSave(@RequestParam Map<String, String> params, Model model) throws Exception {
		TranConfigVO tranConfigVO = new TranConfigVO();
		tranConfigVO.setCurrency(params.get("currency"));
		tranConfigVO.setTran_yn(params.get("tran_yn"));
		tranConfigVO.setTran_type(params.get("tran_type"));
		tranConfigVO.setStatus(params.get("status"));
		tranConfigVO.setTarget_price(new Float(params.get("target_price")));
		tranConfigVO.setUnits(new Float(params.get("target_price")));
		
		tranConfigDAO.updateTranConfig(tranConfigVO);
		
		model.addAttribute("tranConfigList", tranConfigDAO.getTranConfigList());

		return "tranConfig/tranConfigList";
	}		
	

}
