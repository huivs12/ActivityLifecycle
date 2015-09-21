package com.thh.androidlifecycle;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    /**
     * Activity创建时执行的第一个方法，在这个方法中，加载布局资源，初始化所需数据。
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");
        if (savedInstanceState!=null){
            String value = savedInstanceState.getString("value");
            Log.i(TAG, "value:"+value);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
        /**
         * 如果Activity异常销毁后，必会执行onSaveInstanceState方法，然后重建时也必会执行onRestoreInstanceState方法，
         * 所以在这里无需判断savedInstanceState是否为空，而如果在onCreate方法需要做判空处理。Google推荐在此方法获取。
         */
        String value = savedInstanceState.getString("value");
        Log.i(TAG, "value:"+value);
    }

    /**
     * 当按下Home键或重新打开了一个Activity后，再回到这个Activity。就会执行这个方法。
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    /**
     * Activity正在被启动，这个时候Activity已经可见了，但是还是处于后台。无法进行交互。
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    /**
     * 当执行这个方法的时候，Activity已经显示在我们的前台，并且可以进行交互了。
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    /**
     * Activity正在被停止，这个时候可以做一个数据存储或停止动画的操作，不可做耗时操作
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putString("value", "hello!!!");
    }

    /**
     * Activity即将停止，可以做一些轻微重量级的回收工作，不可做耗时操作
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    /**
     * Activity即将销毁，也是生命周期中执行的最后一个方法，在这个方法中做一些回收工作和最终的资源释放
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        /**
         * 当配置发生改变时，会调用此方法。需要在AndroidMainfast文件Activity标签中设置它的configChanges属性
         * 常用的有：orientation,locale,keyboardHideen
         *
         * 可以通过orientation设置当屏幕旋转时，不需要重新创建。
         * 需要注意的是：在MinVersion或tagetVersion为13以上时，还需要加上screenSize参数值。
         *
         * 当配置发生改变后，可以在此方法中做相关操作。
         */
        Log.i(TAG, "onConfigurationChanged");
    }

    /**
     * 注意：当打开另一个Activity时，所执行的生命周期的方法是这样：
     * MainActivity:onPause -> Main2Activity:onCreate -> Main2Activity:onStart -> Main2Activity:onResume -> MainActivity:onStop
     *
     * 在Android源码中，就是先执行上一个Activity的onPause后，再启动当前这个Activity的。当这个Activity启动完成后，再执行上一个Activity的onStop方法
     *
     * 当按返回键，回到上一个Activity时的执行顺序是这样的：
     * Main2Activity:onPause -> MainActivity:onRestart -> MainActivity:onStart -> MainActivity:onResume -> Main2Activity:onStop -> Main2Activity:onDestroy
     *
     * @param view
     */
    public void open(View view){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
