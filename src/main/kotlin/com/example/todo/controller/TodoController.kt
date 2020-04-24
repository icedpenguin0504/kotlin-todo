package com.example.todo.controller;

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * ToDoコントローラ
 */
@Controller
class TodoController {

    /** トップ画面 */
    @RequestMapping("/")
    fun index(): ModelAndView {
        return ModelAndView("/index")
    }
}