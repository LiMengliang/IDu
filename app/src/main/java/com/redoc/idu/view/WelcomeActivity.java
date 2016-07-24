package com.redoc.idu.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.R;
import com.redoc.idu.utils.BitmapUtils;

import java.io.IOException;

/**
 * Welcome activity.
 */
public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mRootLayout;
    private View mEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        mRootLayout = (RelativeLayout)findViewById(R.id.root_layout);
        mEnter = findViewById(R.id.enter_button);
        mEnter.setOnClickListener(this);
        // <Lessons Learned>
        // 我们在布局文件里面写android:background和android:src这些属性，
        // 实际上解析之后执行的是view.setBackgroundResource和view.setImageResource方法，
        // 这两个方法实际上是拿到资源ID再去获取资源的drawable。
        // 他们会decode图片后，最终都是通过java层的createBitmap来完成的，需要消耗更多内存。
        // 实际上我们可以用decodeStream来替代，因为decodeStream直接调用JNI>>nativeDecodeAsset()
        // 来完成decode，无需再使用java层的createBitmap，从而节省了java层的空间.
        // 另外我们可以设置图片的参数,例如设置为Bitmap.Config.RGB_565来减少内存开销。
        // 因为在android文档中描述Bitmap.Config.RGB_565每一个像素存在2个字节中，
        // 而默认的Bitmap.Config.ARGB_8888每一个像素则需要4个字节，理论上足足节省了一半空间。
        try {
            mRootLayout.setBackground(BitmapUtils.res2BitmapDrawable(IDuApplication.Context, R.drawable.splash));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if(v == mEnter) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
}
