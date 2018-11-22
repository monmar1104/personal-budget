package com.monmar.personalbudget.controller;

import com.monmar.personalbudget.entity.*;
import com.monmar.personalbudget.service.BudgetService;
import com.monmar.personalbudget.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/budget")
@SessionAttributes("currentBudget")
public class BudgetController {
	

	@Autowired
	private BudgetService budgetService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	HttpSession session;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;
	
	private Logger logger = Logger.getLogger(getClass().getName());

	@GetMapping("/list")
	public String listBudgetItems(RedirectAttributes model, Model model1) {
		Budget currentBudget = null;
		session = request.getSession();
		User user = (User) session.getAttribute("user");

		int budgetId = 0;
		try {
			budgetId = (int) model1.asMap().get("budgetId");
		} catch (Exception e) {
			if (budgetId > 0) {
				currentBudget = budgetService.getBudgetById(budgetId);

			} else {
				currentBudget = budgetService.getLastBudget(user.getId());
				budgetId = currentBudget.getBudgetId();
			}
		}

		model1.addAttribute("currentBudget", currentBudget);

		List<Category> categoryList = categoryService.getCategoryList();
		model1.addAttribute("categoryList", categoryList);

		List<BudgetDetail> budgetDetailList = budgetService.getBudgetDetailListByBudgetId(budgetId);
		model1.addAttribute("budgetDetailList", budgetDetailList);

		Map<Integer, Double> getSumOfTransactionByCategoryMap = budgetService
				.getSumOfTransactionByCategoryMap(budgetId);
		model1.addAttribute("sumCategoryMap", getSumOfTransactionByCategoryMap);

		List<Budget> budgetList = budgetService.getBudgetList(user.getId());
		model1.addAttribute("budgetList", budgetList);

		BudgetDetail budgetDetail = new BudgetDetail();
		model1.addAttribute("budgetDetail", budgetDetail);

		return "list-budgets";
	}

	@PostMapping("/listBudgetItemsById")
	public String listBudgetItemsById(@RequestParam("budgetId") int budgetId, RedirectAttributes ra, Model model) {

		session = request.getSession();
		User user = (User) session.getAttribute("user");

		Budget currentBudget = budgetService.getBudgetById(budgetId);
		model.addAttribute("currentBudget", currentBudget);

		List<BudgetDetail> budgetDetailList = budgetService.getBudgetDetailListByBudgetId(budgetId);
		model.addAttribute("budgetDetailList", budgetDetailList);

		List<Category> categoryList = categoryService.getCategoryList();
		model.addAttribute("categoryList", categoryList);

		Map<Integer, Double> getSumOfTransactionByCategoryMap = budgetService
				.getSumOfTransactionByCategoryMap(budgetId);
		model.addAttribute("sumCategoryMap", getSumOfTransactionByCategoryMap);

		List<Budget> budgetList = budgetService.getBudgetList(user.getId());
		model.addAttribute("budgetList", budgetList);

		BudgetDetail budgetDetail = new BudgetDetail();
		model.addAttribute("budgetDetail", budgetDetail);

		return "list-budgets";
	}

	@GetMapping("/listByBudgetName")
	public String listBudgetItemsByBudgetName(@RequestParam("budgetName") String name, Model model) {
		List<BudgetDetail> budgetDetails = budgetService.getBudgetDetailListByName(name);

		model.addAttribute("budgetDetailList", budgetDetails);

		return "list-budgets";
	}

	@PostMapping("/addBudgetItem")
	public String addBudgetItem(@RequestParam("category") String categoryId,
			@ModelAttribute("budgetDetail") @Valid BudgetDetail budgetDetail, BindingResult result, Model model, RedirectAttributes rd) {

		budgetDetail.setCategory(categoryService.findCategoryById(Integer.valueOf(categoryId)));
		Budget currentBudget = (Budget) model.asMap().get("currentBudget");

		budgetDetail.setBudget(currentBudget);
		budgetService.addBudgetItem(budgetDetail);

		rd.addFlashAttribute("budgetId", currentBudget.getBudgetId());

		return "redirect:/budget/list";
	}

	@PostMapping("/search")
	public String searchItemByCatName(@RequestParam("currentBudget") int budgetId,
			@RequestParam("categoryName") String name, RedirectAttributes ra, Model model) {
		List<BudgetDetail> budgetDetailList = budgetService.searchBudgetItemByCatName(name, budgetId);

		model.addAttribute("budgetDetailList", budgetDetailList);
		model.addAttribute("budgetDetail", new BudgetDetail());

		List<Category> categoryList = categoryService.getCategoryList();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryName", name);

		ra.addFlashAttribute("currentBudget", budgetId);

		return "list-budgets";
	}

	@GetMapping("/showAddBudgetItemFormUpdate")
	public String showTransactionFormForUpdate(@RequestParam("budgetDetailId") int id, Model model) {

		BudgetDetail budgetDetail = budgetService.getBudgetDetailById(id);

		model.addAttribute("budgetDetail", budgetDetail);
		List<Category> categoryList = categoryService.getCategoryList();
		model.addAttribute("categoryList", categoryList);

		return "add-budget-item";
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("budgetDetailId") int id, Model model) {

		budgetService.deleteTransactionById(id);

		return "redirect:/budget/list";
	}

	@GetMapping("/showAddBudgetForm")
	public String showAddBudgetForm(Model model) {
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);

		List<Budget> budgetList = budgetService.getBudgetList(user.getId());
		model.addAttribute("budgetList", budgetList);
		Budget newBudget = new Budget();
		model.addAttribute("newBudget", newBudget);

		return "add-budget-form";
	}

	@PostMapping("/addNewBudget")
	public String addNewBudget(@ModelAttribute(value = "newBudget") Budget newBudget) {
		session = request.getSession();
		User user = (User) session.getAttribute("user");

		String budgetIdStr = request.getParameter("oldBudgetId");

		if (budgetIdStr == null) {
			budgetIdStr = "0";
		}

		newBudget.setBudgetUser(user);

		budgetService.addBudget(newBudget, Integer.parseInt(budgetIdStr));

		return "redirect:/budget/list";
	}

	/*
	 * TODO adding new budget based on existing TODO TODO TODO percent calculating -
	 * budget list TODO redirect to list after login??? TODO change forms:
	 * addTransaction,addBudgetItem TODO change first image TODO add new budget TODO
	 * add aop login module TODO validation TODO check csrf token
	 */

}
