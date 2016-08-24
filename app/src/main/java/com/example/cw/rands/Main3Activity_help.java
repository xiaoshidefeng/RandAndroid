package com.example.cw.rands;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity_help extends AppCompatActivity  implements View.OnTouchListener{

    //手指滑动的最小速度
    private static final int X_MinSpeed = 200;

    //手指滑动最小距离
    private static final int X_MinDistance = 150;

    //手指落下时的横坐标
    private float X_Down;

    //手指移动时的横坐标
    private float X_Move;

    //手指拿起时的横坐标
    private float X_away;

    //和我联系
    private TextView callMe;

    //报警
    private TextView call110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_activity_help);

        callMe=(TextView)findViewById(R.id.Call);
        call110=(TextView)findViewById(R.id.CallPlic);

        callMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="mqqwpa://im/chat?chat_type=wpa&uin=1330661071";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });

        call110.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + "110"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        LinearLayout ll = (LinearLayout)findViewById(R.id.help_layout);
        ll.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:{
                X_Down=motionEvent.getX();//获取落下点的x
                break;
            }
            case MotionEvent.ACTION_UP:
                X_away=motionEvent.getX();       //获取移动的x
                int distanceX = (int)(X_away-X_Down);
                if(distanceX>X_MinDistance){
                    finish();
                    return true;
                }
                break;
            default:
                break;
        }
        return true;
    }
}
