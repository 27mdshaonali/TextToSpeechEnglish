package com.example.texttospeech;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView soundOn, soundOff;
    TextView novel;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        soundOn = findViewById(R.id.soundOn);
        soundOff = findViewById(R.id.soundOff);
        novel = findViewById(R.id.novel);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status == TextToSpeech.SUCCESS) {
                    textToSpeech.setSpeechRate(0.8f);
                } else {

                    Toast.makeText(MainActivity.this, "Text To Speech Failed", Toast.LENGTH_SHORT).show();

                }

            }
        });


        soundOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String novelText = novel.getText().toString();

                soundOn.setVisibility(View.GONE);
                soundOff.setVisibility(View.VISIBLE);

                textToSpeech.speak(novelText, TextToSpeech.QUEUE_FLUSH, null, null);

            }
        });

        soundOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                soundOn.setVisibility(View.VISIBLE);
                soundOff.setVisibility(View.GONE);
                textToSpeech.stop();
                novel.setText(getResources().getString(R.string.novel));

                Toast.makeText(MainActivity.this, "Sound has stopped!", Toast.LENGTH_SHORT).show();

            }
        });


    }
}