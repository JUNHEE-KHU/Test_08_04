package com.example.test_08_04;

import static android.os.Build.VERSION.SDK_INT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.Build;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mOnClick(View v) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        switch(v.getId()) {
            case R.id.btnTest1:
                Intent resultIntent1 = new Intent(this, SubActivity.class);
                PendingIntent contentIntent1 = PendingIntent.getActivity(this, 0, resultIntent1, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder1 = new NotificationCompat.Builder(this,"notification_ch_id");
                builder1.setSmallIcon(R.mipmap.ic_launcher);
                builder1.setContentTitle("첫 번째 알림 제목");
                builder1.setContentText("첫 번째 알림 텍스트");
                builder1.setContentIntent(contentIntent1);
                builder1.setAutoCancel(true);

                //OREO API 26 이상에서는 채널 필요
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.0)
                {
                    builder1.setSmallIcon (R.drawable.ic_launcher_foreground) ; //mipmap 사용시 oreo 이상에서 시스템 UI 에러남
                    CharSequence channelName = "notification channel";
                    String description = "오레오 이상을 위한 것임";
                    int importance = NotificationManager. IMPORTANCE_HIGH;

                    NotificationChannel channel = new NotificationChannel("notification_ch_id", channelName, importance);
                    channel.setDescription (description);

                    //notification channel을 시스템에 등록
                    assert notificationManager != null;
                    notificationManager.createNotificationChannel(channel);
                } else
                builder1.setSmallIcon(R.mipmap.ic_launcher);
                // oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러

                assert notificationManager != null;

                notificationManager.notify(0, builder1.build());
                break;

            case R.id.btnTest2:
                Intent resultIntent2 = new Intent(this, SubActivity.class);
                PendingIntent contentIntent2 = PendingIntent.getActivity(this, 0, resultIntent2, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this);
                builder2.setSmallIcon(R.mipmap.ic_launcher);
                builder2.setContentTitle("첫 번째 알림 제목");
                builder2.setContentText("첫 번째 알림 텍스트");
                builder2.setContentIntent(contentIntent2);
                builder2.setAutoCancel(true);

                notificationManager.notify(1, builder2.build());
                break;
        }
    }
}