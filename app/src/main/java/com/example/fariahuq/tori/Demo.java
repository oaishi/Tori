package com.example.fariahuq.tori;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.bassaer.chatmessageview.model.ChatUser;
import com.github.bassaer.chatmessageview.model.Message;
import com.github.bassaer.chatmessageview.view.ChatView;

import java.util.Random;

public class Demo extends AppCompatActivity {

    private ChatView mChatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Kothabondhu");
        setContentView(R.layout.activity_demo);

        //User id
        int myId = 0;
        //User icon
        Bitmap myIcon = BitmapFactory.decodeResource(getResources(), R.drawable.tori);
        //User name
        String myName = "Michael";

        int yourId = 1;
        Bitmap yourIcon = BitmapFactory.decodeResource(getResources(), R.drawable.velero);
        String yourName = "Tori";

        final ChatUser me = new ChatUser(myId, myName, myIcon);
        final ChatUser you = new ChatUser(yourId, yourName, yourIcon);

        mChatView = (ChatView)findViewById(R.id.chat_view);

        //Set UI parameters if you need
        mChatView.setRightBubbleColor(ContextCompat.getColor(this, R.color.Chatcolor));
        mChatView.setLeftBubbleColor(Color.WHITE);
        mChatView.setBackgroundColor(ContextCompat.getColor(this, R.color.ChatBackground));
        mChatView.setSendButtonColor(ContextCompat.getColor(this, R.color.cyan900));
        mChatView.setSendIcon(R.drawable.ic_action_send);
        mChatView.setRightMessageTextColor(Color.WHITE);
        mChatView.setLeftMessageTextColor(Color.BLACK);
        mChatView.setUsernameTextColor(Color.WHITE);
        mChatView.setSendTimeTextColor(Color.WHITE);
        mChatView.setDateSeparatorColor(Color.WHITE);
        mChatView.setInputTextHint("new message...");
        mChatView.setMessageMarginTop(5);
        mChatView.setMessageMarginBottom(5);

        final Message receivedMessage = new Message.Builder()
                .setUser(you)
                .setRight(false)
                .setText("Hi")
                .build();
        mChatView.receive(receivedMessage);

        //Click Send Button
        mChatView.setOnClickSendButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new message
                Message message = new Message.Builder()
                        .setUser(me)
                        .setRight(true)
                        .setText(mChatView.getInputText())
                        .hideIcon(true)
                        .build();
                //Set to chat view
                mChatView.send(message);
                //Reset edit text
                mChatView.setInputText("");

                String msg = "Type Something";

                if(message.getText().equals("yesterday my father died"))
                {
                    msg = "pal, i'm sorry for your loss...it's okay to feel low, to feel " +
                            "angry that he is not here, to feel hard to accept that he wont be able " +
                            "to keep his hand on your head, you can share your feeling with me, pal.";
                }
                else if(message.getText().equals("yeah i'm ready"))
                {
                    msg = "I really appreciate your co-operation. Do you feel hard sometimes " +
                            "to accept the reality of the loss? Like , \"why is this happening to me?\"";
                }
                else if(message.getText().equals("no i don't feel good"))
                {
                    msg = "It's okay pal. You know grief is a natural response to losing someone you care about." +
                            "There's no right or wrong way to grieve. The important thing is to let " +
                            "yourself grieve and mourn as much and as long as you need to. " +
                            "Do you want to hear a story of one of my friends?";
                }
                //Receive message
                final Message receivedMessage = new Message.Builder()
                        .setUser(you)
                        .setRight(false)
                        .setText(msg)
                        .build();

                //ChatBot.talk(me.getName(), message.getText())
                // This is a demo bot
                // Return within 3 seconds
                int sendDelay = (new Random().nextInt(3) + 1) * 1000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mChatView.receive(receivedMessage);
                    }
                }, sendDelay);
            }

        });

    }
}

