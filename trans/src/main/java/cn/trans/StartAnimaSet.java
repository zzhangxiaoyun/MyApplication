package cn.trans;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by zhangxiaoyun01 on 2016/9/24.
 *
 * @functionModule
 */
public class StartAnimaSet extends AnimationSet {
    /**
     *
     TranslateAnimation transAnimation = new TranslateAnimation( 0f, 0f,0f,imageone.getHeight());
     transAnimation.setInterpolator(new LinearInterpolator());
     transAnimation.setDuration(1000);
     ScaleAnimation scaleAnimation = new ScaleAnimation(1f,1.14f,1f,1.14f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
     scaleAnimation.setDuration(1000);
     AnimationSet animationSet = new AnimationSet(false);
     animationSet.addAnimation(transAnimation);
     animationSet.addAnimation(scaleAnimation);
     *
     *
     */

    public StartAnimaSet(float transDistance,float toScaleXy) {
        super(false);

        TranslateAnimation transAnimation = new TranslateAnimation( 0f, 0f,0f,transDistance);
        transAnimation.setInterpolator(new LinearInterpolator());
        transAnimation.setDuration(Config.DURATION);
        transAnimation.setFillAfter(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f,toScaleXy,1f,toScaleXy, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(Config.DURATION);
        scaleAnimation.setFillAfter(true);

        addAnimation(transAnimation);
        addAnimation(scaleAnimation);
    }



}
