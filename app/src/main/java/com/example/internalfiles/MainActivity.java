package com.example.internalfiles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity
{
    /**
     * this class opens a internal file and saves data into it
     * then, the file text will be presented on the screen of the application
     * @author Ofek Dafna
     */
    private final String FILENAME = "internalFile.txt";

    TextView showText;
    String userTxt,fileTxt;
    EditText getText;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //---------------
        showText = findViewById(R.id.showText);
        getText = findViewById(R.id.getText);
        //--------------------------------------
        try
        {
            FileOutputStream fOS = openFileOutput(FILENAME,MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            oSW.close();
            fOS.close();
            FileInputStream fIS = openFileInput(FILENAME);
            InputStreamReader iSR = new InputStreamReader(fIS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while (line != null)
            {
                sB.append(line+'\n');
                line = bR.readLine();
            }
            fileTxt = sB.toString();
            showText.setText(fileTxt);
            bR.close();
            iSR.close();
            fIS.close();

        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


    }

    /**
     * the function saves new entered text into the file
     * @param view
     */
    public void save(View view)
    {
        try
        {
            FileOutputStream fOS = openFileOutput(FILENAME,MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            BufferedWriter bW = new BufferedWriter(oSW);
            userTxt = getText.getText().toString();
            bW.write(fileTxt + userTxt);
            bW.close();
            oSW.close();
            fOS.close();

        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * the function resets the text in the file
     * @param view
     */
    public void reset(View view)
    {
        try
        {
            FileOutputStream fOS = openFileOutput(FILENAME,MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            BufferedWriter bW = new BufferedWriter(oSW);
            bW.write("");
            bW.close();
            oSW.close();
            fOS.close();
            showText.setText("");
            getText.setText("");
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * the function saves the file's contents, adds the newly text to the file and then terminates the app
     * @param view
     */
    public void exit(View view)
    {
        save(view);
        finish();
    }

    /**
     * moves the user to the credits screen
     * @param view
     */
    public void credits(View view)
    {
        Intent credits = new Intent(this,credits.class);
        startActivity(credits);
    }
}