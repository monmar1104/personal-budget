package com.monmar.personalbudget.controller;

import java.time.LocalDate;
import java.util.ArrayList;
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
		categories = catService.getCategoryList();
		model.addAttribute("categoryList", categories);
		request.setAttribute("categoryId", categories.get(0).getCategoryId());
		request.setAttribute("transactionDateFrom", LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1));
		request.setAttribute("transactionDateTo", LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getMonth().length(true)));
		return "chart";
	}
	

	@GetMapping("/getExpensesFromCurrentBudget")
	@ResponseBody
	public List<Stat> getExpensesFromCurrentBudget(Model model) {
		session = request.getSession();
		User user = (User) session.getAttribute("user");

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
//		int categoryId = (int)request.getAttribute("categoryId"); 
//		String dateFrom = (String) request.getAttribute("transactionDateFrom"); 
//		String dateTo = (String)request.getAttribute("transactionDateTo");
		
		session = request.getSession();
		User user = (User) session.getAttribute("user");
			
		List<Stat> stats = transactionService.getSumOfTransactionsByCategoryByDate(user.getId(), categoryId, dateFrom, dateTo);
//		List<Stat> stats = transactionService.getSumOfTransactionsByCategoryByDate(5, 75, "2018-11-01", "2018-12-31");

		return stats;
	}

}
