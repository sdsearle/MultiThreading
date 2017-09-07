package com.example.admin.multithreading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by admin on 9/7/2017.
 */

public class TestThreadHandlerMessage extends Thread {
    Handler handler;

    public TestThreadHandlerMessage(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();

        String data = "Hi from the TestThreadHandlerMessage";
        //Create a bundel to add it to the message object
        Bundle bundle = new Bundle();
        bundle.putString("data","Hi from the TestThreadHandlerMessage");
        //create the message obj and add the bundel obj
        Message message = new Message();
        message.setData(bundle);
        //send that message object from the handler
        handler.sendMessage(message);

        //Send the Message through EVENTBUS
        EventBus.getDefault().post(new MessageEvent(data));
    }
}
