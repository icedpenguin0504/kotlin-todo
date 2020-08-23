package com.example.todo.util

import org.springframework.core.io.ClassPathResource
import java.text.MessageFormat
import java.util.Properties

/**
 * message.propertiesのメッセージの読み込み
 */
object MessageUtils {
  const val MESSAGES_PROPERTIES = "messages.properties"

  /**
   * メッセージ取得
   *
   * @param key メッセージのキー
   * @param params メッセージに埋め込むパラメータ
   * @return メッセージ
   */
  fun get(key: String, params: Array<String>?): String {
    val props = Properties()
    props.load(ClassPathResource(MESSAGES_PROPERTIES).inputStream)

    return if (params == null) {
      props.getProperty(key)
    } else {
      MessageFormat.format(props.getProperty(key), *params)
    }
  }
}