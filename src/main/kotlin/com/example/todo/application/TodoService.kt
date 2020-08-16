package com.example.todo.application

import com.example.todo.domain.model.Todo
import com.example.todo.domain.service.TodoRepository
import org.springframework.stereotype.Service

/**
 * todoサービス
 */
@Service
class TodoService(
  private val todoRepository: TodoRepository
) {
  /** 全件取得 */
  fun getAllTodos(): List<Todo> = todoRepository.findByOrderByCreatedAtDesc()

  /** todo登録 */
  fun registerTodo(todo: Todo): Todo = todoRepository.save(todo)

  /** 既存タイトルの重複を検出 */
  fun isTitleDuplicated(title: String): Boolean = todoRepository.findByTitle(title) != null
}