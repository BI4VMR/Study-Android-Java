package net.bi4vmr.study.store_file;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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
            String content = readFile(is);
            Log.d(TAG, "test.txt: " + content);
        });

        btAssets.setOnClickListener(v -> {
            // 获取AssetManager
            AssetManager am = getAssets();
            try {
                // 查看"config"目录下的子项
                String[] filenames = am.list("config");
                // 依次读取每个文件的内容
                for (String file : filenames) {
                    InputStream is = am.open("config/" + file);
                    String content = readFile(is);
                    Log.d(TAG, file + ": " + content);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 关闭AssetManager
            am.close();
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
            Log.d(TAG, "内部存储-文件: 读-" + internalFileDir.canRead() + " 写-" + internalFileDir.canWrite());

            // 文件读写
            try {
                /*
                 * 打开文件输出流
                 *
                 * 参数一：文件名，打开"/data/data/<包名>/files/"目录中的对应文件，若文件不存在将自动创建。
                 * 参数二：写入模式，"MODE_PRIVATE"表示清空写入；"MODE_PRIVATE"表示追加写入。
                 * 返回值：FileOutputStream对象
                 */
                FileOutputStream os = openFileOutput("in.txt", MODE_PRIVATE);
                os.write("Hello".getBytes(StandardCharsets.UTF_8));
                Log.i(TAG, "写入in.txt成功：Hello");
                os.close();

                /*
                 * 打开文件输入流
                 * 参数一：文件名，打开"/data/data/<包名>/files/"目录中的对应文件，若文件不存在返回值为Null。
                 * 返回值：FileOutputStream对象
                 */
                FileInputStream is = openFileInput("in.txt");
                String content = readFile(is);
                Log.i(TAG, "读取in.txt内容：" + content);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
             *      传入"Null"或空字符串时表示"files"目录本身。
             *      传入"Environment.DIRECTORY_MUSIC"等常量值，将返回类似"<包名>/files/Music"的对应目录。
             *      传入自定义的字符串，系统将返回同名目录，目录不存在时将自动创建。
             * 返回值：默认返回虚拟存储卡中的路径，如果未找到任何可用路径，则会返回"Null"。
             */
            File externalFileDir = getExternalFilesDir("");
            Log.d(TAG, "外部存储-文件: " + externalFileDir.toString());
            Log.d(TAG, "内部存储-文件: 读-" + externalFileDir.canRead() + " 写-" + externalFileDir.canWrite());

            /*
             * 获取所有缓存、文件目录（外部存储）
             *
             * 参数与"getExternalCacheDir()"、"getExternalFilesDir()"类似，返回值为数组，包括所有外部存储设备的路径。
             */
            File[] externalCacheDirs = getExternalCacheDirs();
            File[] externalFilesDirs = getExternalFilesDirs(null);

            // 文件读写
            try {
                // 构造File对象
                File file = new File(externalFileDir, "ex.txt");
                // 写入文件
                FileOutputStream os = new FileOutputStream(file);
                os.write("Hello".getBytes(StandardCharsets.UTF_8));
                Log.i(TAG, "写入ex.txt成功：Hello");
                os.close();

                // 读取文件
                FileInputStream is = new FileInputStream(file);
                String content = readFile(is);
                Log.i(TAG, "读取ex.txt内容：" + content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btCommon.setOnClickListener(v -> {
            /*
             * 共享数据目录，Android 11及以下版本需要申请读写权限。
             */
            // 获取共享目录的根路径
            File shareDir = Environment.getExternalStorageDirectory();
            Log.d(TAG, "共享存储-根目录: " + shareDir.toString());
            Log.d(TAG, "共享存储-根目录: 读-" + shareDir.canRead() + " 写-" + shareDir.canWrite());

            // 获取指定类型的目录，参数类型同"getExternalCacheDir()"、"getExternalFilesDir()"方法。
            File shareDLDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            Log.d(TAG, "共享存储-Doc: " + shareDLDir.toString());
            Log.d(TAG, "共享存储-Doc: 读-" + shareDLDir.canRead() + " 写-" + shareDLDir.canWrite());

            // 文件读写
            try {
                // 构造File对象
                File file = new File(shareDLDir, "share.txt");
                // 写入文件
                FileOutputStream os = new FileOutputStream(file);
                os.write("Shared".getBytes(StandardCharsets.UTF_8));
                Log.i(TAG, "写入share.txt成功：Shared");
                os.close();

                // 读取文件
                FileInputStream is = new FileInputStream(file);
                String content = readFile(is);
                Log.i(TAG, "读取share.txt内容：" + content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // 读取文件，返回字符串。
    private String readFile(InputStream is) {
        InputStreamReader isReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isReader);
        StringBuilder sb = new StringBuilder();

        try {
            // 循环读取数据
            do {
                String line = reader.readLine();
                // 如果读不到内容，则退出循环。
                if (line == null) {
                    break;
                } else {
                    sb.append(line);
                }
            } while (true);

            // 释放资源
            reader.close();
            isReader.close();
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 删除换行符
        String content = sb.toString().replace("\n", "");
        content = content.replace("\r", "");

        return content;
    }
}