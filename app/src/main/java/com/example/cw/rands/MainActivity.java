package com.example.cw.rands;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    //输入框
    private EditText editText;
    //添加按钮
    private Button addButton;
    //显示所有姓名的文本
    private TextView nameText;
    //删除按钮
    private Button delButton;
    //清空按钮
    private Button clearButton;
    //随机按钮
    private Button randButton;
    //随机姓名显示文本
    private TextView randText;

    //map里的key值
    private int key=0;

    //输入框的文本内容
    private String edit;

    //输入文本框的总内容
    private String sumText;

    //名字计数器
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //下面开始实例化
        //输入框
        editText=(EditText)findViewById(R.id.mainEditText);
        //添加按钮
        addButton=(Button) findViewById(R.id.mainInputButton);
        //显示所有姓名的文本
         nameText=(TextView)findViewById(R.id.mainShowNameText);
        //删除按钮
        delButton=(Button)findViewById(R.id.mainDelOneNameButton);
        //清空按钮
        clearButton=(Button)findViewById(R.id.mainClearAllNameButton);
        //随机按钮
        randButton=(Button)findViewById(R.id.mainRandButton);
        //随机姓名显示文本
        randText=(TextView)findViewById(R.id.mainRandText);
        //实例化结束

        //下面输入的名字用map来存储
        final Map<Integer,String> map = new HashMap<Integer,String>();

        i=0;//计数器清零

        //设置添加按钮的监听事件
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit=editText.getText().toString();
                if(edit.equals("")||edit==null){        //判断输入是否为空
                    Toast.makeText(MainActivity.this,"输入为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(sumText==null){
                    sumText=" "+edit;
                    editText.setText("");
                }else {
                    sumText=sumText+" 、"+edit;
                    editText.setText("");
                }

                map.put(i,edit);    //向map中添加有效数据
                i++;                //key值增加
                nameText.setText("此时随机对象有： "+sumText);

            }
        });

        //删除按钮的监听事件
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i>0){
                    i--;    //key值减一，根据 hashmap特性，将来添加数据会覆盖在之前的key和value值上
                    int i=sumText.lastIndexOf(" ");
                    sumText=sumText.substring(0,i);
                    nameText.setText("此时随机对象有： "+sumText);
                    randText.setText("");
                    return;
                }else {
                    Toast.makeText(MainActivity.this,"没有随机对象",Toast.LENGTH_SHORT).show();
                    randText.setText("");
                    return;
                }

            }
        });

        //清空按钮的监听事件
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sumText==null){
                    Toast.makeText(MainActivity.this,"没有随机对象",Toast.LENGTH_SHORT).show();
                    return;
                }
                sumText=null;
                nameText.setText("此时随机对象有： ");
                randText.setText("");
                i=0;
                map.clear();
                return;
            }
        });

        //随机按钮的监听事件
        randButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0){
                    Toast.makeText(MainActivity.this,"请输入要随机的对象名称！",Toast.LENGTH_SHORT).show();
                    return;
                }
                int randNumbers = (int)(Math.random()*i);
                String str =map.get(randNumbers).toString();
                randText.setText("本次随机结果为： "+str);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "更多问题查看右上角帮助", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {           //右上角的菜单
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {           //这里是简单的随机数生成器
            Intent toEasy = new Intent(this,Main2Activity_easy.class);
            startActivity(toEasy);
            Toast.makeText(MainActivity.this, "现在是随机数生成模式", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.help){          //这里是帮助的activity
            Intent toHelp= new Intent(this,Main3Activity_help.class);
            startActivity(toHelp);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
