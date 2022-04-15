package net.bi4vmr.study.store_file;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "myapp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btRaw = findViewById(R.id.btRaw);
        Button btAssets = findViewById(R.id.btAssets);
        Button btInternal = findViewById(R.id.btInternal);
        Button btExternal = findViewById(R.id.btExternal);
        Button btCommon = findViewById(R.id.btCommon);

        btRaw.setOnClickListener(v -> {
            // 读取"raw/test.txt"
            InputStream is = getResources().openRawResource(R.raw.test);

        });

        btAssets.setOnClickListener(v -> {
            // TODO 查看"config"目录下的子项
        });

        btInternal.setOnClickListener(v -> {
            /*
             * 内部存储-应用私有数据目录，无需申请读写权限，其它程序不能访问，通常用户也不能访问。
             */
            // 获取缓存目录（内部存储）
            File internalCacheDir = getCacheDir();
            Log.d(TAG, "内部存储-缓存: " + internalCacheDir.toString());
            // 获取文件目录（内部存储）
            File internalFileDir = getFilesDir();
            Log.d(TAG, "内部存储-文件: " + internalFileDir.toString());
        });

        btExternal.setOnClickListener(v -> {
            /*
             * 外部存储-应用私有数据目录，无需申请读写权限，其它程序不能访问，用户可以通过文件管理器访问。
             */
            // 获取缓存目录（外部存储）
            File externalCacheDir = getExternalCacheDir();
            Log.d(TAG, "外部存储-缓存: " + externalCacheDir.toString());
            /*
             * 获取文件目录（外部存储）
             *
             * 参数：文件类型。
             *      传入Null或空字符串时表示File目录本身。
             *      传入Environment.DIRECTORY_MUSIC等常量值，将返回类似"<包名>/files/Music"的对应目录。
             *      传入自定义的字符串，系统将返回同名目录，目录不存在时将自动创建。
             * 返回值：默认返回虚拟存储卡中的路径，如果未找到任何可用路径，则会返回Null。
             */
            File externalFilesDir = getExternalFilesDir("");
            Log.d(TAG, "外部存储-文件: " + externalFilesDir.toString());

            /*
             * 获取所有缓存、文件目录（外部存储）
             *
             * 参数与getExternalCacheDir()、getExternalFilesDir()类似，返回值为数组，包括所有外部存储设备的路径。
             */
            File[] externalCacheDirs = getExternalCacheDirs();
            File[] externalFilesDirs = getExternalFilesDirs(null);
        });

        btCommon.setOnClickListener(v -> {
            /*
             * 共享数据目录，Android 11及以下版本需要申请读写权限。
             */
            // 获取共享目录的根路径
            File shareDir = Environment.getExternalStorageDirectory();
            Log.d(TAG, "共享存储-根目录: " + shareDir.toString());
            Log.d(TAG, "共享存储-根目录: 读-" + shareDir.canRead() + " 写-" + shareDir.canWrite());

            // 获取指定类型的目录，参数类型同getExternalCacheDir()、getExternalFilesDir()方法
            File shareMusicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
            Log.d(TAG, "共享存储-Music: " + shareMusicDir.toString());
            Log.d(TAG, "共享存储-根目录: 读-" + shareMusicDir.canRead() + " 写-" + shareMusicDir.canWrite());
        });
    }
}