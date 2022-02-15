package com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText name, surname, marks;
    Button addData,viewData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= new DatabaseHelper(this);
        name= findViewById(R.id.et_name);
        surname= findViewById(R.id.et_surname);
        marks= findViewById(R.id.et_marks);
        addData= findViewById(R.id.btn_add_data);
        viewData=findViewById(R.id.btn_view_data);
        viewData();
        addData();
    }
    public void viewData(){
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor data= db.getAllData();
                if(data.getCount()==0) {
                    showMessage("error", "404 no data found");
                    return;
                }
                else{
                    StringBuffer buffer = new StringBuffer();
                    while(data.moveToNext()){
                        buffer.append("id: "+data.getString(0)+"\n");
                        buffer.append("name: "+data.getString(1)+"\n");
                        buffer.append("surname: "+data.getString(2)+"\n");
                        buffer.append("marks: "+data.getString(3)+"\n\n");

                    }
                    showMessage("data",buffer.toString());
                }
            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void addData(){
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = db.insetData(name.getText().toString(),surname.getText().toString(),Integer.parseInt(marks.getText().toString()));
                if(isInserted){
                    Toast.makeText(getApplicationContext(), " data inserted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), " not inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}