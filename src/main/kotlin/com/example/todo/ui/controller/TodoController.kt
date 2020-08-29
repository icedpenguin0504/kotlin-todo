package com.example.todo.ui.controller

import com.example.todo.application.TodoService
import com.example.todo.domain.model.Todo
import com.example.todo.ui.form.TodoForm
import com.example.todo.util.MessageUtils
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.lang.IllegalArgumentException

/**
 * ToDoコントローラ
 */
@Controller
@RequestMapping("/")
class TodoController(
  private val todoService: TodoService
) {

  /** top画面 */
  @GetMapping
  fun showTopPage(model: Model): String {
    if (!model.containsAttribute("todoForm")) {
      model.addAttribute("todoForm", TodoForm())
    }
    model.addAttribute("todos", todoService.getAllTodos())
    return "index"
  }

  /** todo登録 */
  @PostMapping
  fun register(
    @Validated @ModelAttribute("todoForm") todoForm: TodoForm,
    redirectAttributes: RedirectAttributes
  ): String {
    val isTitleDuplicated = todoService.isTitleDuplicated(todoForm.title!!)
    redirectAttributes.addFlashAttribute("isTitleDuplicated", isTitleDuplicated)
    // TODO タイトルのエラーハンドリング
    if (!isTitleDuplicated) {
      todoService.registerTodo(
        todoForm.title?.let { Todo.of(title = it) }
          ?: throw IllegalArgumentException(MessageUtils.get("e.0001", arrayOf("Title")))
      )
    }
    return "redirect:/"
  }

  /** todo削除 */
  @DeleteMapping("{id}")
  fun delete(@PathVariable("id") id: Long): String {
    todoService.deleteTodo(id)
    return "redirect:/"
  }

  /**
   * 編集画面を表示
   */
  @GetMapping("{id}")
  fun showEditPage(
    model: Model,
    @PathVariable("id") id: Long
  ): String {
    val todo = todoService.getTodoById(id)
    val todoForm = TodoForm()
    todoForm.title = todo.title
    model.addAttribute("todoForm", todoForm)
    model.addAttribute("id", id)
    return "edit"
  }

  /**
   * todo更新
   */
  @PatchMapping("{id}")
  fun update(
    @Validated @ModelAttribute("todoForm") todoForm: TodoForm,
    @PathVariable("id") id: Long,
    model: Model
  ): String {
    lateinit var viewName: String
    todoForm.title?.let { title ->
      viewName = if (todoService.isTitleDuplicated(id, title)) {
        model.addAttribute("isTitleDuplicated", true)
        "edit"
      } else {
        todoService.updateTodo(id, title)
        "redirect:/"
      }
    } ?: throw IllegalArgumentException(MessageUtils.get("e.0001", arrayOf("Title")))
    return viewName
  }

  /**
   * todo検索
   */
  @GetMapping("/search")
  fun search(
    @RequestParam("title") title: String,
    model: Model
  ): String {
    model.addAttribute("todos", todoService.getTodosByTitle(title))
    model.addAttribute("isFiltered", true)
    model.addAttribute("query", title)
    model.addAttribute("todoForm", TodoForm())
    return "index"
  }
}