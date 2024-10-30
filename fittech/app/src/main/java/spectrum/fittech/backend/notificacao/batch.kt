package spectrum.fittech.backend.notificacao

import android.content.Context
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit

fun scheduleDailyNotification(context: Context) {
    val currentDateTime = Calendar.getInstance()
    val targetDateTime = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 50)
        set(Calendar.SECOND, 0)
    }

    if (targetDateTime.before(currentDateTime)) {
        targetDateTime.add(Calendar.DAY_OF_MONTH, 1)
    }

    val initialDelay = targetDateTime.timeInMillis - currentDateTime.timeInMillis

    val dailyWorkRequest = PeriodicWorkRequestBuilder<NotificacaoAgenda>(1, TimeUnit.DAYS)
        .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
        .build()

    WorkManager.getInstance(context).enqueue(dailyWorkRequest)
}