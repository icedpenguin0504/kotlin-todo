package com.example.todo.ui.form

import com.example.todo.domain.model.Todo
import lombok.Data
import javax.validation.constraints.NotBlank

@Data
class TodoForm {
  @NotBlank
  var title: String? = null

  fun toEntity(): Todo {
    val todo = Todo()
    todo.title = this.title
    return todo
  }
}