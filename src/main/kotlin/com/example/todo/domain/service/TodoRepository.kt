package com.example.todo.domain.service

import com.example.todo.domain.model.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository: JpaRepository<Todo, Long> {
  fun findByOrderByCreatedAtDesc(): List<Todo>
}