package com.sandeep.task.udemydownloadingimg;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView myImageDownload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myImageDownload = (ImageView)findViewById(R.id.myImage);
    }

    public void btnAction(View view) {

        imageDownloader myTask = new imageDownloader();

        Bitmap bitmapImage;

        try {
            bitmapImage = myTask.execute("https://lh3.googleusercontent.com/proxy/v09uE3EuhRP0wrQf9VARsc4Ac1fEoXSGLtG2TeuU5ZE2joB9RcEcyWMAiZVqbASPetd6S2xirnhE695NtEkBcC_s3Zwxi0ECjxVoT2v5KjbHQhB7").get();
            myImageDownload.setImageBitmap(bitmapImage);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


public class imageDownloader extends AsyncTask<String,Void, Bitmap>{

    @Override
    protected Bitmap doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.connect();

            InputStream inputStream = connection.getInputStream();

            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            return bitmap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
}
