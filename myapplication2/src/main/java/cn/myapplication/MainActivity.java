package cn.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void start(View view){
        try {
            Intent intent = Intent.parseUri("#Intent;action=com.baidu.appsearch.extinvoker.LAUNCH;S.tjindirect=ala%3Dwise-app%40strong%40%E5%A4%A9%E5%A4%A9%E9%85%B7%E8%B7%91%40buttonR%40followup;B.needextratj=true;S.tjlanding=ala%3Dwise-app%40strong%40%E5%A4%A9%E5%A4%A9%E9%85%B7%E8%B7%91%40buttonR%40landing%40738143;S.id=ala.com.baidu.searchbox;S.backop=0;S.pkg=com.tencent.pao;S.quitop=1;S.activefrom=alading;S.func=10;B.download_immediatly=true;i.download_mode=1;end",0);
            startActivity(intent);
            Log.i("tag","start action:"+intent.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }






    }
}
