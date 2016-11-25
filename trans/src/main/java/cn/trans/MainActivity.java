package cn.trans;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageone, imagetow,image0,image1,image2,image3,image4,image5;

    private GestureDetector mGestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageone = (ImageView)findViewById(R.id.imageone);
        imagetow = (ImageView)findViewById(R.id.imagetwo);

        image0 = (ImageView)findViewById(R.id.image0);
        image1 = (ImageView)findViewById(R.id.image1);
        image2 = (ImageView)findViewById(R.id.image2);
        image3 = (ImageView)findViewById(R.id.image3);
        image4 = (ImageView)findViewById(R.id.image4);
        image5 = (ImageView)findViewById(R.id.image5);
        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                // if (Math.abs(e1.getRawX() - e2.getRawX()) > 250) {
                // // System.out.println("水平方向移动距离过大");
                // return true;
                // }
                if (Math.abs(velocityY) < 100) {
                    // System.out.println("手指移动的太慢了");
                    return true;
                }
                // 手势向下 down
                if ((e2.getRawY() - e1.getRawY()) > 200) {
                    startAnima();
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    public void start(View view){
        startAnima();
    }

    private void startAnima(){
//        imageone.startAnimation(new AnimaSet(imageone.getHeight(),1.2f));
        image0.startAnimation(new StartAnimaSet(4f,25f));
        image1.startAnimation(new BiggerAnimaSet((image1.getHeight()+image2.getHeight())/2f,1.2f));
        image2.startAnimation(new BiggerAnimaSet((image2.getHeight()+image3.getHeight())/2f,1.33333f));
        image3.startAnimation(new SmllerAnimaSet((image3.getHeight()+image4.getHeight())/2f,0.75f));
        image4.startAnimation(new SmllerAnimaSet((image4.getHeight()+image5.getHeight())/2f,0.8333f));
        image5.startAnimation(new EndAnimaSet(image5.getHeight()/2f,0f));
    }



}
