package com.takusemba.audiothief

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.os.Build
import android.os.IBinder
import com.takusemba.audiothief.C.AUDIOFOCUS_GAIN
import com.takusemba.audiothief.C.AUDIOFOCUS_GAIN_KEY
import com.takusemba.audiothief.C.AUDIOFOCUS_GAIN_TRANSIENT
import com.takusemba.audiothief.C.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE
import com.takusemba.audiothief.C.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE_KEY
import com.takusemba.audiothief.C.AUDIOFOCUS_GAIN_TRANSIENT_KEY
import com.takusemba.audiothief.C.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK
import com.takusemba.audiothief.C.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK_KEY

/**
 * ForegroundService to hold AudioFocus. How you let this to hold focus depends on the key defined as AudioRequestKey
 * Request would be either [AUDIOFOCUS_GAIN], [AUDIOFOCUS_GAIN_TRANSIENT], [AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK], or [AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE]
 */
class AudioFocusGainService : Service() {

  companion object {
    private const val AUDIO_REQUEST_KEY = "AUDIO_REQUEST_KEY"
    private val EMPTY_LISTENER = AudioManager.OnAudioFocusChangeListener {}
  }

  override fun onBind(intent: Intent): IBinder? {
    return null
  }

  private val audioManager: AudioManager by lazy {
    getSystemService(Context.AUDIO_SERVICE) as AudioManager
  }

  private val notificationManager: NotificationManager by lazy {
    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
  }

  private var request: AudioFocusRequest? = null

  override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

    if (!intent.hasExtra(AUDIO_REQUEST_KEY)) throw IllegalStateException("invalid intent key")

    val audioRequest = when (intent.getIntExtra(AUDIO_REQUEST_KEY, -1)) {
      AUDIOFOCUS_GAIN_KEY -> AudioRequest.GAIN
      AUDIOFOCUS_GAIN_TRANSIENT_KEY -> AudioRequest.GAIN_TRANSIENT
      AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK_KEY -> AudioRequest.GAIN_TRANSIENT_MAY_DUCK
      AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE_KEY -> AudioRequest.GAIN_TRANSIENT_EXCLUSIVE
      else -> throw IllegalStateException("invalid request type")
    }

    if (Build.VERSION_CODES.O < Build.VERSION.SDK_INT) {
      createNotificationChannel()
      requestAudioFocusV26(audioRequest)
    } else {
      requestAudioFocusDefault(audioRequest)
    }

    val builder = Notification.Builder(this)
        .setContentTitle(audioRequest.title)
        .setOngoing(true)
        .setSmallIcon(R.drawable.ic_notification)

    if (Build.VERSION_CODES.O < Build.VERSION.SDK_INT) {
      builder.setChannelId(C.CHANNEL_ID)
    }

    val notification = builder.build()

    notificationManager.notify(C.NOTIFICATION_ID, notification)
    startForeground(C.FOREGROUND_ID, notification)

    return START_NOT_STICKY
  }

  override fun onDestroy() {
    super.onDestroy()
    if (Build.VERSION_CODES.O < Build.VERSION.SDK_INT) {
      abandonAudioFocusV26()
    } else {
      abandonAudioFocusDefault()
    }
    stopForeground(true)
  }

  private fun createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(
          C.CHANNEL_ID,
          C.CHANNEL_NAME,
          NotificationManager.IMPORTANCE_NONE
      )
      notificationManager.createNotificationChannel(channel)
    } else {
      throw IllegalStateException("api level is lower than a required version")
    }
  }

  private fun requestAudioFocusDefault(audioRequest: AudioRequest) {
    audioManager.requestAudioFocus(
        EMPTY_LISTENER,
        AudioManager.STREAM_MUSIC,
        audioRequest.focusGain
    )
  }

  private fun requestAudioFocusV26(audioRequest: AudioRequest) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val request = AudioFocusRequest
          .Builder(audioRequest.focusGain)
          .setAudioAttributes(AudioAttributes.Builder()
              .setUsage(audioRequest.usage)
              .setContentType(audioRequest.contentType)
              .build()
          )
          .setOnAudioFocusChangeListener(EMPTY_LISTENER)
          .setAcceptsDelayedFocusGain(true)
          .build()
      audioManager.requestAudioFocus(request)
      this.request = request
    } else {
      throw IllegalStateException("api level is lower than a required version")
    }
  }

  private fun abandonAudioFocusDefault() {
    audioManager.abandonAudioFocus(EMPTY_LISTENER)
  }

  private fun abandonAudioFocusV26() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val request = request
      if (request != null) {
        audioManager.abandonAudioFocusRequest(request)
      }
    } else {
      throw IllegalStateException("api level is lower than a required version")
    }
  }
}