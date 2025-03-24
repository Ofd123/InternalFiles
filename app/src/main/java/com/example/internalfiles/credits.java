package com.example.internalfiles;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class credits extends AppCompatActivity
{
    /**
     * this is the credits screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }

    /**
     * throws the user back to the main activity
     * @param view
     */
    public void goBack(View view)
    {
        finish();
    }
}