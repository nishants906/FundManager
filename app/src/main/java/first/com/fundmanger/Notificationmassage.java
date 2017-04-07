package first.com.fundmanger;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Nishant on 07/04/17.
 */

public class Notificationmassage extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent arg1) {
        showNotification(context);
    }

    private void showNotification(Context context) {
        Log.i("notification", "visible");

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, Notificationmassage.class), 0);

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Fund Manager")
                        .setContentText("Update your today's Transaction!");
        mBuilder.setContentIntent(contentIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }
}