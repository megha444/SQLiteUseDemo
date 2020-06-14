package com.example.newdbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MyDatabaseHelper mDatabaseHelper;
    TextView mTextView;
    EditText editTextTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = (EditText) findViewById(R.id.editTextTask);
        mTextView = (TextView) findViewById(R.id.tvDatabase);
        Button addButton = (Button) findViewById(R.id.buttonAddTask);
        Button removeButton = (Button) findViewById(R.id.buttonRemoveTask);
        mDatabaseHelper = new MyDatabaseHelper(this, null, null, 1);
        printDB();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tasks tasks = new Tasks(editTextTask.getText().toString());
                mDatabaseHelper.addTask(tasks);
                editTextTask.setText("");
                printDB();
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editTextTask.getText().toString();
                mDatabaseHelper.removeTaks(input);
                editTextTask.setText("");
                printDB();
            }
        });

    }
public void printDB()
{
    String dbs= mDatabaseHelper.dbToString();
    mTextView.setText(dbs);
}
}
