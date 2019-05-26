package zeon.com.chatapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class service extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "OnCreate", Toast.LENGTH_SHORT).show();

        Log.d("service", "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
        Log.d("start", "onStartCommand: ");

        return super.onStartCommand(intent, flags, startId);

    }
}
