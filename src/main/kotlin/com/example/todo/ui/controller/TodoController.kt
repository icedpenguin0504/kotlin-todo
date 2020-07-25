package com.example.todo.ui.controller;

import com.example.todo.application.TodoService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
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
    model.addAttribute("todos", todoService.getAllTodos())
    return "index"
  }
}