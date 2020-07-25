package com.example.todo.ui.controller;

import com.example.todo.application.TodoService
import com.example.todo.ui.form.TodoForm
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * ToDoコントローラ
 */
@Controller
@RequestMapping("/")
class TodoController(
  private val todoService: TodoService
) {

  @GetMapping
  fun index(model: Model): String {
    model.addAttribute("todoForm", TodoForm())
    model.addAttribute("todos", todoService.getAllTodos())
    return "index"
  }

  @PostMapping("register")
  fun register(
    @Validated @ModelAttribute("todoForm") todoForm: TodoForm
  ): String {
    todoService.registerTodo(todoForm.toEntity())
    return "redirect:/"
  }
}