package com.example.niels.myapplication;

import android.provider.BaseColumns;

/**
 * Created by niels on 11/09/15.
 */
public  abstract class FeedEntry implements BaseColumns {
    public static final String TABLE_NAME = "Contacts";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_PHONE = "phone";

}