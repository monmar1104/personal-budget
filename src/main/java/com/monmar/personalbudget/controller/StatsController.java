package com.monmar.personalbudget.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.Category;

import com.monmar.personalbudget.entity.Stat;
import com.monmar.personalbudget.entity.User;
import com.monmar.personalbudget.service.BudgetService;
import com.monmar.personalbudget.service.CategoryService;

@Controller
@RequestMapping("stats")
public class StatsController {

	@Autowired
	BudgetService budgetService;

	@Autowired
	CategoryService catService;

	@Autowired
	HttpSession session;
	@Autowired
	HttpServletRequest request;

	@GetMapping("/")
	public String showStatsMainPage() {
		return "stats-page";
	}

	@GetMapping("showSumOfExpensesChart")
	public String showSumOfExpensesChart() {

		return "chart";
	}

	@GetMapping("/getStats")
	@ResponseBody
	public List<Stat> getStats() {
		session = request.getSession();
		User user = (User) session.getAttribute("user");

		List<Category> categories = catService.getCategoryList();

		Budget budget = budgetService.getCurrentBudget(user.getId());
		Map<Integer, Double> getSumOfTransactionByCategoryMap = budgetService
				.getSumOfTransactionByCategoryMap(budget.getBudgetId());

		List<Stat> stats = new ArrayList<Stat>();

		getSumOfTransactionByCategoryMap.forEach((k, v) -> stats.add(new Stat(categories.stream()
				.filter(id -> id.getCategoryId() == k).map(n -> n.getCategoryName()).findFirst().get(), v)));
		return stats;

	}

}
