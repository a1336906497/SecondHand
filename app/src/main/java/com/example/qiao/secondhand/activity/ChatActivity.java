package com.example.qiao.secondhand.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiao.secondhand.R;
import com.example.qiao.secondhand.adapter.ChatListViewAdapter;
import com.example.qiao.secondhand.bean.ChatBean;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    Socket socket;
    @BindView(R.id.goods_true_text)
    TextView goodsTrueText;
    @BindView(R.id.goods_commend_edit)
    EditText goodsCommendEdit;
    @BindView(R.id.chat_input_relative)
    RelativeLayout chatInputRelative;
    @BindView(R.id.chat_listview)
    ListView chatListview;
    @BindView(R.id.chat_text)
    TextView chatText;
    @BindView(R.id.activity_chat)
    RelativeLayout activityChat;
    private BufferedWriter bw;
    private OutputStream out;
    private int id;

    private MyHandler myHandler = new MyHandler();
    private BufferedReader bufferedReader;
    private String content;
    private SharedPreferences preferences;

    private ChatListViewAdapter adapter;
    private String userimage1;
    private String userimage2;
    private int myid;

    private List<ChatBean> list=new ArrayList<>();


    private class MyHandler extends Handler {
        public void handleMessage(Message msg) {
         //   Toast.makeText(ChatActivity.this, "hello", Toast.LENGTH_SHORT).show();
            Bundle bundle = msg.getData();            //获取Message中发送过来的数据
            String content = bundle.getString("text");
            chatText.setText(content);

            ChatBean bean=new ChatBean(userimage1,content,0);
            list.add(bean);
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        preferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
        Intent intent=getIntent();
        id=intent.getIntExtra("id",3);
        userimage1=intent.getStringExtra("userimage");
        userimage2=preferences.getString("userImage","");
        myid=preferences.getInt("userID",3);
        adapter=new ChatListViewAdapter(list,this);

        chatListview.setAdapter(adapter);





        new Thread(new Runnable() {
            @Override
            public void run() {

                initSocket();
            }
        }).start();



        goodsTrueText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=goodsCommendEdit.getText().toString();
                if (!TextUtils.isEmpty(text)){
                        writeCilent(text);
                }
            }
        });
    }


    private void initSocket() {
        try {
            socket = new Socket("10.0.2.2", 9000);            //用户的客户端Socket
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
            bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
            bw.write(preferences.getInt("userID",3)+"");
            bw.newLine();
            bw.flush();
            //out=socket.getOutputStream();
            while (true) {
                    content = null;
                    content = bufferedReader.readLine();
                    Bundle bundle = new Bundle();
                    bundle.putString("text", content);
                    Message msg = new Message();
                    msg.setData(bundle);            //将数据封装到Message对象中
                    myHandler.sendMessage(msg);
                    content = null;
                }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public void writeCilent(String text){
        try {
//                        OutputStreamWriter osw=new OutputStreamWriter(socket.getOutputStream(),"UTF-8");
//                        PrintWriter pw=new PrintWriter(osw,true);
//                        pw.write(text);
//                        pw.flush();

//                        out.write(text.getBytes());
//                        out.flush();

//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                initSocket();
//                            }
//                        }).start();

            Toast.makeText(ChatActivity.this, text, Toast.LENGTH_SHORT).show();




            bw.write(5+"##"+myid+"##"+text);
            bw.newLine();
            bw.flush();

            ChatBean bean=new ChatBean(userimage2,text,1);
            list.add(bean);
            adapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ChatActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}


