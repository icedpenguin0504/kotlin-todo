package com.example.todo.domain.model;

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * todoエンティティ
 */
@Entity
class Todo(
  /** ID */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,
  /** タイトル */
  var title: String,
  /** ステータス */
  var isDone: Boolean = false,
  /** 作成日時 */
  @CreationTimestamp var createdAt: LocalDateTime? = null,
  /** 更新日時 */
  @CreationTimestamp var updatedAt: LocalDateTime? = null
) {
  companion object {
    /** todoエンティティを生成 */
    fun of(title: String) = Todo(title = title)
  }
}