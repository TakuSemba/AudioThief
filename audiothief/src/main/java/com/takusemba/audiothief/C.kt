package com.takusemba.audiothief

import android.media.AudioAttributes
import android.media.AudioManager

object C {

  /** Audio Request Key. This key will be passed into AudioFocusGainService */
  const val AUDIOFOCUS_GAIN_KEY = 1
  const val AUDIOFOCUS_GAIN_TRANSIENT_KEY = 2
  const val AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK_KEY = 3
  const val AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE_KEY = 4

  /** Type of Audio Request */
  const val AUDIOFOCUS_GAIN = AudioManager.AUDIOFOCUS_GAIN
  const val AUDIOFOCUS_GAIN_TRANSIENT = AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
  const val AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK
  const val AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE = AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE

  /** Type of Audio Usage. This is used on api level 26 or above */
  const val USAGE_MEDIA = AudioAttributes.USAGE_MEDIA
  const val USAGE_ALARM = AudioAttributes.USAGE_ALARM
  const val USAGE_NOTIFICATION = AudioAttributes.USAGE_NOTIFICATION

  /** Type of Audio Content Type. This is used on api level 26 or above */
  const val CONTENT_TYPE_SONIFICATION = AudioAttributes.USAGE_MEDIA
  const val CONTENT_TYPE_MOVIE = AudioAttributes.USAGE_ALARM

  /** Audio Request Title. This is used for notification title */
  const val AUDIOFOCUS_GAIN_TITLE = "Hold AUDIOFOCUS_GAIN"
  const val AUDIOFOCUS_GAIN_TRANSIENT_TITLE = "Hold AUDIOFOCUS_GAIN_TRANSIENT"
  const val AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK_TITLE = "Hold AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK"
  const val AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE_TITLE = "Hold AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE"

  /** Channel Id for NotificationChannel */
  const val CHANNEL_ID = "audio_thief"

  /** Channel Name for NotificationChannel */
  const val CHANNEL_NAME = "AudioThief"

  /** Notification Id, which is used while holding an audio focus */
  const val NOTIFICATION_ID = 1

  /** Notification Id, which is used to start foreground service */
  const val FOREGROUND_ID = 1
}