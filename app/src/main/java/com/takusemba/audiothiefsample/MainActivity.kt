package com.takusemba.audiothiefsample

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.ui.PlayerView

class MainActivity : AppCompatActivity() {

  companion object {
    private const val URL = "https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8"
    private val URI: Uri = Uri.parse(URL)
  }

  private var player: SimpleExoPlayer? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onStart() {
    super.onStart()
    player = SimpleExoPlayer.Builder(this).build()
    val playerView = findViewById<PlayerView>(R.id.player_view)
    playerView.player = player
    val mediaItem = MediaItem.fromUri(URI)
    val audioAttributes = AudioAttributes.Builder()
        .setUsage(C.USAGE_MEDIA)
        .setContentType(C.CONTENT_TYPE_MOVIE)
        .build()
    player?.setAudioAttributes(audioAttributes, true)
    player?.setMediaItem(mediaItem)
    player?.prepare()
    player?.playWhenReady = true
  }

  override fun onStop() {
    super.onStop()
    player?.release()
    player = null
  }
}
