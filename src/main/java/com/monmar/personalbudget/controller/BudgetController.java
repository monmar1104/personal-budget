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

@Controller
@RequestMapping("/budget")
@SessionAttributes("currentBudget")
public class BudgetController {

	@Autowired
	private BudgetService budgetService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	HttpServletRequest request; 
	
	@Autowired
	HttpServletResponse response;

	@GetMapping("/list")
	public String listBudgetItems( RedirectAttributes model, Model model1) {
		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String name = auth.getName();
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		Budget lastBudget = budgetService.getLastBudget(user.getId());
		model1.addAttribute("currentBudget", lastBudget);

		int budgetId = lastBudget.getBudgetId();

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
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		Budget currentBudget = budgetService.getBudgetById(budgetId);
		ra.addFlashAttribute("currentBudget", budgetId);

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

//	@GetMapping("/showAddBudgetItemForm")
//	public String showAddTransactionForm(Model model) {
//
//		BudgetDetail budgetDetail = new BudgetDetail();
//
//		model.addAttribute("budgetDetail", budgetDetail);
//		List<Category> categoryList = categoryService.getCategoryList();
//
//		model.addAttribute("categoryList", categoryList);
//
//		return "add-transaction";
//	}


	
	@PostMapping("/addBudgetItem")
	public String addBudgetItem(@RequestParam("category") String categoryId,
			@ModelAttribute("budgetDetail") @Valid BudgetDetail budgetDetail, BindingResult result, Model model) {

		budgetDetail.setCategory(categoryService.findCategoryById(Integer.valueOf(categoryId)));
		Budget currentBudget = (Budget) model.asMap().get("currentBudget");

		budgetDetail.setBudget(currentBudget);
		budgetService.addBudgetItem(budgetDetail);

		return "redirect:/budget/list";
	}

//TODO list by budget
	
	@PostMapping("/search")
	public String searchItemByCatName(@RequestParam("categoryName") String name, Model model) {
		List<BudgetDetail> budgetDetailList = budgetService.searchBudgetItemByCatName(name);

		model.addAttribute("budgetDetailList", budgetDetailList);
		model.addAttribute("budgetDetail", new BudgetDetail());

		List<Category> categoryList = categoryService.getCategoryList();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryName", name);

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

	/*
	 * TODO adding new budget based on existing
	 * TODO budget list - error handling in case of lack of data
	 * TODO 
	 * TODO percent calculating - budget list 
	 * TODO redirect to list after login??? 
	 * TODO change forms: addTransaction,addBudgetItem 
	 * TODO change first image 
	 * TODO add new budget 
	 * TODO add aop login module 
	 * TODO validation 
	 * TODO check csrf token
	 */

}
