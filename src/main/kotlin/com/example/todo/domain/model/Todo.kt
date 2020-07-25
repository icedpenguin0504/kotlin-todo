package com.example.todo.domain.model;

import lombok.Getter
import lombok.Setter
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
@Setter
@Getter
class Todo {
    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    /** タイトル */
    var title: String? = null

    /** 期日 */
    var deadline: String? = null

    /** ステータス */
    var isDone: Boolean = false

    /** 作成日時 */
    @CreationTimestamp var createdAt: LocalDateTime? = null

    /** 更新日時 */
    @CreationTimestamp var updatedAt: LocalDateTime? = null
}