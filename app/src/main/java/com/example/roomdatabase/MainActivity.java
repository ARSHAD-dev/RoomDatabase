package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/*
       created by Arshadkhan
*/
public class MainActivity extends AppCompatActivity {
    EditText t1, t2;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (EditText) findViewById(R.id.t1);
        t2 = (EditText) findViewById(R.id.t2);
        b1 = (Button) findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new bgThread().start();
            }
        });
    }

    class bgThread extends Thread {
        public void run() {
            super.run();
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "Room_db").build();
            UserDao userDao = db.userDao();
            userDao.insertRecord(new User(6, t1.getText().toString(), t2.getText().toString()));
            t1.setText("");
            t2.setText("");
            //  Toast.makeText(getApplicationContext(), "Inserted Sucessfully", Toast.LENGTH_LONG).show();
        }
    }
}