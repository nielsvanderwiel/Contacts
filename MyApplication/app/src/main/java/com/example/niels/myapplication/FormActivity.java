package com.example.niels.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.*;
import android.widget.EditText;
import android.database.sqlite.*;


public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

    }

    //Called when user presses the submit button. the Data is collected and saved to the sqlite database
    //When all is done we go back to the main view
    public void submit(View v){
        EditText Name = (EditText)findViewById(R.id.NameF);
        EditText Phone = (EditText)findViewById(R.id.PhoneF);
        Log.d("lol", "" + Phone.getText());
        Log.d("lol", "" + Name.getText());

        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(v.getContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_NAME, ""+Name.getText());
        values.put(FeedEntry.COLUMN_NAME_PHONE, ""+Phone.getText());
        db.insert(FeedEntry.TABLE_NAME, null, values);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
