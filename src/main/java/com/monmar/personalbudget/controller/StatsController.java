package com.monmar.personalbudget.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.stat.internal.CategorizedStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.Category;
import com.monmar.personalbudget.entity.FinancialTransaction;
import com.monmar.personalbudget.entity.Stat;
import com.monmar.personalbudget.entity.User;
import com.monmar.personalbudget.service.BudgetService;
import com.monmar.personalbudget.service.CategoryService;
import com.monmar.personalbudget.service.TransactionService;

@Controller
@RequestMapping("stats")
public class StatsController {

	@Autowired
	BudgetService budgetService;

	@Autowired
	CategoryService catService;
	
	@Autowired
	TransactionService transactionService;

	@Autowired
	HttpSession session;
	
	@Autowired
	HttpServletRequest request;
	
	List<Category> categories = null;

	@GetMapping("/")
	public String showStatsMainPage() {
		return "stats-page";
	}

	
	@GetMapping("/chartPanel")
	public String showChartPanel(Model model) {
		GregorianCalendar cal = new GregorianCalendar();
		categories = catService.getCategoryList();
		model.addAttribute("categoryList", categories);
		LocalDate currentDateMinusThreeMonths = LocalDate.now().minusMonths(3);
		int categoryId = categories.get(0).getCategoryId();
		
		request.setAttribute("categoryName", categories.stream().filter(c -> c.getCategoryId() == categoryId).map(Category::getCategoryName).findFirst().get());		
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("transactionDateFrom", LocalDate.of(currentDateMinusThreeMonths.getYear(), currentDateMinusThreeMonths.getMonth(), 1));
		request.setAttribute("transactionDateTo", LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getMonth().length(cal.isLeapYear(LocalDate.now().getYear()))));
		
		return "chart";
	}
	
	@GetMapping("/chartPanelPost")
	public String showChartPanelPost(@RequestParam("categoryId") int categoryId, 
			@RequestParam("transactionDateFrom") String dateFrom, 
			@RequestParam("transactionDateTo") String dateTo, Model model) {
		
		categories = catService.getCategoryList();
		
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("categoryName", categories.stream().filter(c -> c.getCategoryId() == categoryId).map(Category::getCategoryName).findFirst().get());
		model.addAttribute("transactionDateFrom", dateFrom);
		model.addAttribute("transactionDateTo", dateTo);
		model.addAttribute("categoryList", categories);
		
		return "chart";
	}
	
	
	@GetMapping("/getExpensesFromCurrentBudget")
	@ResponseBody
	public List<Stat> getExpensesFromCurrentBudget(Model model) {
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		//temp
		categories = catService.getCategoryList();


		Budget budget = budgetService.getCurrentBudget(user.getId());
		Map<Integer, Double> getSumOfTransactionByCategoryMap = budgetService
				.getSumOfTransactionByCategoryMap(budget.getBudgetId());


		List<Stat> stats = new ArrayList<Stat>();

		getSumOfTransactionByCategoryMap.forEach((k, v) -> stats.add(new Stat(categories.stream()
				.filter(id -> id.getCategoryId() == k).map(n -> n.getCategoryName()).findFirst().get(),"Month", v)));
		return stats;

	}
	
	@GetMapping("/filterByDateByCategory")
	@ResponseBody
	public List<Stat> getExspensesByCategoryByDate(
			@RequestParam("categoryId") int categoryId, 
			@RequestParam("transactionDateFrom") String dateFrom, 
			@RequestParam("transactionDateTo") String dateTo
											)	{
		session = request.getSession();
		User user = (User) session.getAttribute("user");
			
		List<Stat> stats = transactionService.getSumOfTransactionsByCategoryByDate(user.getId(), categoryId, dateFrom, dateTo);

		return stats;
	}

}
