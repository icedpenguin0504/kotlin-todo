package com.example.todo.ui.form

import javax.validation.constraints.NotBlank

class TodoForm(
  @NotBlank
  var title: String? = null
)