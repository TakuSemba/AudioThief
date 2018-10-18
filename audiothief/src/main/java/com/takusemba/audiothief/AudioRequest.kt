package com.takusemba.audiothief

enum class AudioRequest(
    val key: Int,
    val focusGain: Int,
    val title: String,
    val usage: Int,
    val contentType: Int
) {
  GAIN(
      key = C.AUDIOFOCUS_GAIN_KEY,
      focusGain = C.AUDIOFOCUS_GAIN,
      title = C.AUDIOFOCUS_GAIN_TITLE,
      usage = C.USAGE_MEDIA,
      contentType = C.CONTENT_TYPE_MOVIE
  ),

  GAIN_TRANSIENT(
      key = C.AUDIOFOCUS_GAIN_TRANSIENT_KEY,
      focusGain = C.AUDIOFOCUS_GAIN_TRANSIENT,
      title = C.AUDIOFOCUS_GAIN_TRANSIENT_TITLE,
      usage = C.USAGE_ALARM,
      contentType = C.CONTENT_TYPE_SONIFICATION
  ),

  GAIN_TRANSIENT_MAY_DUCK(
      key = C.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK_KEY,
      focusGain = C.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK,
      title = C.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK_TITLE,
      usage = C.USAGE_NOTIFICATION,
      contentType = C.CONTENT_TYPE_SONIFICATION
  ),

  GAIN_TRANSIENT_EXCLUSIVE(
      key = C.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE_KEY,
      focusGain = C.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE,
      title = C.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE_TITLE,
      usage = C.USAGE_ALARM,
      contentType = C.CONTENT_TYPE_SONIFICATION
  )
}