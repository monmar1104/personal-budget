package com.monmar.personalbudget.controller;

import com.monmar.personalbudget.entity.Budget;
import com.monmar.personalbudget.entity.Category;
import com.monmar.personalbudget.entity.FinancialTransaction;
import com.monmar.personalbudget.entity.User;
import com.monmar.personalbudget.service.BudgetService;
import com.monmar.personalbudget.service.CategoryService;
import com.monmar.personalbudget.service.TransactionService;

import exception.EmptyArgumentException;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/transaction")
public class FinancialTransactionController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;

	@Autowired
	BudgetService budgetService;

	@GetMapping("/list")
	public String showTransactionList(Model model) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		List<FinancialTransaction> financialTransactionList = transactionService
				.getTransactionListByUserId(user.getId());
		model.addAttribute("transactionList", financialTransactionList);

		FinancialTransaction financialTransaction = new FinancialTransaction();
		model.addAttribute("transaction", financialTransaction);
		List<Category> categoryList = categoryService.getCategoryList();
		model.addAttribute("categoryList", categoryList);

		return "list-transaction";
	}

	@PostMapping("/addTransaction")
	public String addTransaction(@RequestParam("categoryId") String categoryId,
			@Valid @ModelAttribute("transaction") FinancialTransaction financialTransaction, BindingResult result,
			Model model, RedirectAttributes ra) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (result.hasErrors() || categoryId.equals("0")) {
			ra.addFlashAttribute("addTransactionError", "error");
			return "redirect:/transaction/list";
		}

		financialTransaction.setCategory(categoryService.findCategoryById(Integer.valueOf(categoryId)));
		
		financialTransaction.setTransactionUser(user);
		
		transactionService.saveTransaction(financialTransaction);

		return "redirect:/transaction/list";
	}

	@GetMapping("/showTransactionFormUpdate")
	public String showTransactionFormForUpdate(@RequestParam("transactionId") int id, Model model) {

		FinancialTransaction transaction = transactionService.getTransactionById(id);

		model.addAttribute("transaction", transaction);
		List<Category> categoryList = categoryService.getCategoryList();
		model.addAttribute("categoryList", categoryList);

		return "update-transaction-form";
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("transactionId") int id, Model model) {

		transactionService.deleteTransactionById(id);

		return "redirect:/transaction/list";
	}

	@PostMapping("/search")
	public String searchTransactionByName(@RequestParam("transactionName") String name, Model model) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<FinancialTransaction> transactionList = transactionService.searchTransactionByNameByUserId(name,
				user.getId());

		model.addAttribute("transactionList", transactionList);
		model.addAttribute("transaction", new FinancialTransaction());

		List<Category> categoryList = categoryService.getCategoryList();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryName", name);

		return "list-transaction";
	}
	
	@GetMapping("/searchCategory")
	public String searchTransactionByCategoryName(@RequestParam("categoryId") int categoryId, @RequestParam("categoryName") String name,
			@RequestParam("budgetId") String budgetId, Model model) {
		
		Budget budget = budgetService.getBudgetById(Integer.parseInt(budgetId));
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<FinancialTransaction> transactionList = transactionService.searchTransactionByIdByUserIdByDate(categoryId,
			 budget.getBudgetDateFrom(), budget.getBudgetDateTo(), user.getId() );

		model.addAttribute("transactionList", transactionList);
		model.addAttribute("transaction", new FinancialTransaction());

		List<Category> categoryList = categoryService.getCategoryList();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryName", name);

		return "list-transaction";
	}
	
	

	@PostMapping("/filterByDate")
	public String searchTransactionByName(@RequestParam("transactionDateFrom") String dateFrom,
			@RequestParam("transactionDateTo") String dateTo, Model model) {

		List<FinancialTransaction> transactionList = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		try {
			transactionList = transactionService.searchTransactionByDateByUserId(dateFrom, dateTo, user.getId());
		} catch (EmptyArgumentException e) {
			model.addAttribute("transError", e.getMessage());
		}
		model.addAttribute("transactionList", transactionList);
		model.addAttribute("transaction", new FinancialTransaction());

		List<Category> categoryList = categoryService.getCategoryList();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("dateFrom", dateFrom);
		model.addAttribute("dateTo", dateTo);

		return "list-transaction";
	}
}
