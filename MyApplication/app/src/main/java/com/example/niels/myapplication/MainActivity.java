package com.example.niels.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.view.*;
import android.content.Intent;
import android.database.*;
import java.util.*;
import java.util.List;
import android.widget.ArrayAdapter;


public class MainActivity extends AppCompatActivity {

    private ListView list;
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
        FillList();
    }

    //Extract all the Data from the Contacts and add it too the listItems. Then whe notify the adapter
    //so the listview gets updated.
    public void  FillList(){
        ArrayList<Contact> contacts = getdata();
        for(Contact contact : contacts){
            listItems.add(contact.name+ contact.phone);
        }
        adapter.notifyDataSetChanged();
    }


    //Collect all the contact data and return it in an Array
    public ArrayList<Contact> getdata(){
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor  cursor = db.rawQuery("select * from " + FeedEntry.TABLE_NAME, null);
        ArrayList<Contact> contacts =  new ArrayList<Contact>();

        if (cursor .moveToFirst()) {

            while (cursor.isAfterLast() == false) {
                Contact contact = new Contact();
                contact.name = cursor.getString(1);
                contact.phone = cursor.getString(2);
                contacts.add(contact);
                cursor.moveToNext();
            }
        }
        return contacts;
    }


    //Open the add contact form
    public void startForm(View v){
        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public class Contact{
        public String name;
        public String phone;
    }
}
