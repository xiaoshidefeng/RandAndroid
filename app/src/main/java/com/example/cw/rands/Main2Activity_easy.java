package com.example.cw.rands;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2Activity_easy extends AppCompatActivity  implements View.OnTouchListener{

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

    //确定按钮
    private Button inputButton;
    //随机按钮
    private Button randButton;
    //输入框
    private EditText editText;
    //范围显示文本
    private TextView maxNumberText;
    //输出文本
    private TextView outputText;

    String text=null;

    int maxnum=1;
    Long lll=2147483648L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_activity_easy);

        //实例化各个部件
        inputButton=(Button)findViewById(R.id.EasyInputButtom);
        randButton=(Button)findViewById(R.id.EasyRandButton);
        editText=(EditText)findViewById(R.id.EasyEditText);
        maxNumberText= (TextView) findViewById(R.id.EasyMaxNumber);
        outputText= (TextView) findViewById(R.id.EasyRandNumber);
        //实例化结束

        //设置确定范围的按钮监听事件
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text=editText.getText().toString();
                Pattern p = Pattern.compile("[0-9]*");
                Matcher m = p.matcher(text);
                if(text==null|text.equals("")){          //输入为空时
                   Toast.makeText(Main2Activity_easy.this,"输入为空！",Toast.LENGTH_SHORT).show();
                   return;
               }else if(!m.matches()){          //非法输入
                   Toast.makeText(Main2Activity_easy.this,"请输入小于 2147483648 且大于1的合法整数！",Toast.LENGTH_SHORT).show();
                   return;
               }
                else if(text.length()>10){   //非法输入 大于int的范围
                    Toast.makeText(Main2Activity_easy.this,"请输入小于 2147483648 且大于1的合法整数！",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {

                    //测试当数据为10位时的大小 用Long
                    Long test= Long.parseLong(text);
                    if(test>=lll){          //如果超过int范围
                        Toast.makeText(Main2Activity_easy.this,"请输入小于 2147483648 且大于1的合法整数！",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //测试结束

                    //下面开始 显示范围
                    editText.setText("");
                    maxnum = Integer.parseInt(text);
                    maxNumberText.setText("此时的范围 [ 0 , "+maxnum+")");
                    //显示成功
                }
            }
        });

        //下面开始设置随机按钮的监听事件
        randButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randNumbers = (int)(Math.random()*maxnum);
                outputText.setText("本次随机数为： "+randNumbers);
            }
        });

        //下面是滑动屏幕返回的监听事件
        LinearLayout ll = (LinearLayout)findViewById(R.id.Easy_layout);
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

                    Toast.makeText(this,"离开随机数生成模式",Toast.LENGTH_LONG).show();
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
