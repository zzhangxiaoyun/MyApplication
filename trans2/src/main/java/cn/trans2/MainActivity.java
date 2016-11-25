package cn.trans2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private LinearLayout group;
    private int ONE
            ,TWO
            , THREE
            ,FOUR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        group = (LinearLayout)findViewById(R.id.group);

        ONE = dip2px(100);//最大的 就是中间那个
        TWO = dip2px(60);//第二大的 就是挨着最大的那俩
        THREE = dip2px(40);//第三大的，就是头尾那俩
        FOUR = dip2px(0);//最小的，就是在第一个位置看不见那个，滑动的时候会出来



        LayoutInflater mInflater = LayoutInflater.from(this);
        for(int i=0;i<6;i++){
            View view = mInflater.inflate(R.layout.item,null);
            int size = getSize(i);//圆的直径   按照珠子的位置设置不同的大小
            LinearLayout.LayoutParams params =  new LinearLayout.LayoutParams(size,size);
            params.gravity= Gravity.CENTER_HORIZONTAL;
            view.setLayoutParams(params);
            group.addView(view);


            int changeSize = 0;
            switch (i){
                case 0:
                    changeSize = THREE - 0;
                    break;
                case  1:
                    changeSize = TWO - THREE;
                    break;
                case 2:
                    changeSize = ONE - TWO;
                    break;
                case 3:
                    changeSize = TWO - ONE;
                    break;
                case 4:
                    changeSize = THREE - TWO;
                    break;
                case 5:
                    changeSize = 0 - THREE;
                    break;
            }
            changeSizes.put(i,changeSize);
        }

        init();
    }


    private int getSize(int i){
        int size = 0;//圆的直径   按照珠子的位置设置不同的大小
        switch (i){
            case 3:
                size = ONE;
                break;
            case 2:
            case 4:
                size = TWO;
                break;
            case 1:
            case 5:
                size = THREE;
                break;
            case 0:
                size = FOUR;
                break;
        }
        return size;
    }


    private void changeItemSize(int index, View view, int speed){
        ViewGroup.LayoutParams params =  view.getLayoutParams();
        int w = params.width;
        int h = params.height;

        w+=speed;
        h+=speed;

        params.width = w;
        params.height = h;

        if(index==5){
            Log.i("tag",w+"");
        }
        view.setLayoutParams(params);
        group.invalidate();
    }

    private static final int loopCount = 20;
    Map<Integer,Integer> changeSizes = new HashMap<>();
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            count++;
            for(int i=0;i<group.getChildCount();i++){
                View view = group.getChildAt(i);
                int changeSize = changeSizes.get(i);
                int speed = changeSize/loopCount;
                changeItemSize(i,view,speed);
            }

            if(count<loopCount){
                handler.sendEmptyMessageDelayed(0,7);
            }else{//把最后一个消失的珠子  放在第一位，实现循环
                View lastView =  group.getChildAt(group.getChildCount()-1);
                group.removeView(lastView);
                group.addView(lastView,0);

                group.invalidate();
                runing = false;
            }
        }
    };

    private boolean runing;
    private int count = 0;
    private void start(){
        if(runing)return;

        runing = true;
        count = 0;
        handler.sendEmptyMessage(0);
    }

    public int dip2px(float dpValue) {
        final float scale =  getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void init(){
        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                // if (Math.abs(e1.getRawX() - e2.getRawX()) > 250) {
                // // System.out.println("水平方向移动距离过大");
                // return true;
                // }
                if (Math.abs(velocityY) < 60) {
                    // System.out.println("手指移动的太慢了");
                    return true;
                }
                // 手势向下 down
                if ((e2.getRawY() - e1.getRawY()) > 100) {
                    start();
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    private GestureDetector mGestureDetector;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
