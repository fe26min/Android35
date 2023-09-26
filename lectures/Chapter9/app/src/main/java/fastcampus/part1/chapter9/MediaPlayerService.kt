package fastcampus.part1.chapter9

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.drawable.Icon
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class MediaPlayerService : Service() {


    private var mediaPlayer: MediaPlayer? = null
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()

        val playIcon = Icon.createWithResource(baseContext, R.drawable.baseline_play_arrow_24)
        val pauseIcon = Icon.createWithResource(baseContext, R.drawable.baseline_pause_24)
        val stopIcon = Icon.createWithResource(baseContext, R.drawable.baseline_stop_24)

        val mainPendingIntent = PendingIntent.getActivity(
            baseContext,
            0,
            Intent(baseContext, MainActivity::class.java)
                .apply {
                    flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                }
            ,
            PendingIntent.FLAG_IMMUTABLE
        )

        val pausePendingIntent = PendingIntent.getService(
            baseContext,
            0,
            Intent(baseContext, MediaPlayerService::class.java).apply { action = MEDIA_PLAYER_PAUSE },
            PendingIntent.FLAG_IMMUTABLE
        )

        val playPendingIntent = PendingIntent.getService(
            baseContext,
            0,
            Intent(baseContext, MediaPlayerService::class.java).apply { action = MEDIA_PLAYER_PLAY },
            PendingIntent.FLAG_IMMUTABLE
        )

        val stopPendingIntent = PendingIntent.getService(
            baseContext,
            0,
            Intent(baseContext, MediaPlayerService::class.java).apply { action = MEDIA_PLAYER_STOP },
            PendingIntent.FLAG_IMMUTABLE
        )



        val notification = Notification.Builder(baseContext, CHANNEL_ID)
            .setStyle(
                Notification.MediaStyle()
                    .setShowActionsInCompactView(0, 1, 2)
            )
            .setVisibility(Notification.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.baseline_music_note_24)
            .addAction(
                Notification.Action.Builder(
                    pauseIcon,
                    "pause",
                    pausePendingIntent
                ).build()
            )
            .addAction(
                Notification.Action.Builder(
                    playIcon,
                    "play",
                    playPendingIntent
                ).build()
            )
            .addAction(
                Notification.Action.Builder(
                    stopIcon,
                    "pause",
                    stopPendingIntent
                ).build()
            )
            .setContentIntent(mainPendingIntent)
            .setContentTitle("음악재생")
            .setContentText("음원이 재생 중 입니다...")
            .build()

        startForeground(100, notification)
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(CHANNEL_ID, "MEDIA_PLAYER", NotificationManager.IMPORTANCE_DEFAULT)

        val notificationManager = baseContext.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            MEDIA_PLAYER_PLAY -> {
                if(mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(baseContext, R.raw.cheer)
                }
                Log.d("mediaPlayer", "$mediaPlayer")
                Log.d("state", "play")
                mediaPlayer?.start()
            }
            MEDIA_PLAYER_PAUSE -> {
                Log.d("state", "pause")
                mediaPlayer?.pause()
            }
            MEDIA_PLAYER_STOP -> {

                Log.d("state", "stop")
                mediaPlayer?.stop()
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
                stopSelf()
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        mediaPlayer?.apply {
            stop()
            release()
        }
        mediaPlayer = null
        super.onDestroy()
    }

}