package com.example.dbmscrud;

import android.database.Cursor;
import android.os.Bundle;


import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
EditText et1,et2,et3;
Button b1,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        b4=findViewById(R.id.b4);


        database g= new database(this);
       // SQLiteDatabase db =g.getReadableDatabase();

        b1.setOnClickListener(v -> {
         String name1=et1.getText().toString();
            String username1=et2.getText().toString();
            String password1=et3.getText().toString();

            if(name1.equals("")||username1.equals("")||password1.equals(""))
            {
                Toast.makeText(MainActivity.this, "Enter all values", Toast.LENGTH_SHORT).show();
            }
            else
            {
               boolean i= g.insertData(name1,username1,password1);
               if(i)
               {
                   Toast.makeText(MainActivity.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(MainActivity.this, "Not inserted", Toast.LENGTH_SHORT).show();
               }

            }
            et1.setText("");
            et2.setText("");
            et3.setText("");
        });

        b2.setOnClickListener(v -> {
            Cursor t=g.getinfo();
            if(t.getCount()==0)
            {
                Toast.makeText(MainActivity.this, "No records Found!", Toast.LENGTH_SHORT).show();
            }
            StringBuilder buffer=new StringBuilder();
            while(t.moveToNext())
            {
                buffer.append("Name->").append(t.getString(1)).append("\n");
                buffer.append("Username->").append(t.getString(2)).append("\n");
                buffer.append("Password->").append(t.getString(3)).append("\n\n\n");
            }

            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);

            builder.setCancelable(true);

            builder.setTitle("Sign Up Users' Details");
            builder.setMessage(buffer.toString());
            builder.show();

        });

        b3.setOnClickListener(v -> {
            String name1=et1.getText().toString();
            String username1=et2.getText().toString();
            String password1=et3.getText().toString();
            boolean i=g.updateData(name1,username1,password1);
            if(i)
            {
                Toast.makeText(MainActivity.this, "Successfully updated!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, "Not Updated!", Toast.LENGTH_SHORT).show();
            }
            et1.setText("");
            et2.setText("");
            et3.setText("");

        });


        b4.setOnClickListener(v -> {
            String username1=et2.getText().toString();
            boolean i=g.deleteData(username1);
            if(i)
            {
                Toast.makeText(MainActivity.this, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, "Data is not Deleted!", Toast.LENGTH_SHORT).show();
            }
            et2.setText("");
        });
    }
}