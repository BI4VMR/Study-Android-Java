package net.bi4vmr.study.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    private IMyAIDLInterface.Stub stub = new IMyAIDLInterface.Stub() {

        @Override
        public String getName() {
            return "AIDL Test.";
        }
    };

    static class MyBinder extends IMyAIDLInterface.Stub {

        @Override
        public String getName() {
            return "AIDL Test.";
        }
    }
}
