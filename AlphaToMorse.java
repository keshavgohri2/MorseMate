package com.myrestaurant.morsemate;

import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AlphaToMorse extends AppCompatActivity {
    Button btnA,btnB,btnC,btnD,btnE,btnF,btnG,btnH,btnI,btnJ,btnK,btnL,btnM,btnN,btnO,btnP,btnQ,btnR,btnS,btnT,btnU,btnV,btnW,btnX,btnY,btnZ
            ,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnBackSpace;
    TextView textAlpha,textAlphaToMorse;
    private Map<String, String> morseCodeMap;
    private static final int MAX_STREAMS = 5;
    private SoundPool soundPool;
    private int buttonId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alpha_to_morse);
        initializeSoundPool();
        btnA = findViewById(R.id.letter_A);
        btnB = findViewById(R.id.letter_B);
        btnC = findViewById(R.id.letter_C);
        btnD = findViewById(R.id.letter_D);
        btnE = findViewById(R.id.letter_E);
        btnF = findViewById(R.id.letter_F);
        btnG = findViewById(R.id.letter_G);
        btnH = findViewById(R.id.letter_H);
        btnI = findViewById(R.id.letter_I);
        btnJ = findViewById(R.id.letter_J);
        btnK = findViewById(R.id.letter_K);
        btnL = findViewById(R.id.letter_L);
        btnM = findViewById(R.id.letter_M);
        btnN = findViewById(R.id.letter_N);
        btnO = findViewById(R.id.letter_O);
        btnP = findViewById(R.id.letter_P);
        btnQ = findViewById(R.id.letter_Q);
        btnR = findViewById(R.id.letter_R);
        btnS = findViewById(R.id.letter_S);
        btnT = findViewById(R.id.letter_T);
        btnU = findViewById(R.id.letter_U);
        btnV = findViewById(R.id.letter_V);
        btnW = findViewById(R.id.letter_W);
        btnX = findViewById(R.id.letter_X);
        btnY = findViewById(R.id.letter_Y);
        btnZ = findViewById(R.id.letter_Z);
        btn0 = findViewById(R.id.number_zero);
        btn1 = findViewById(R.id.number_one);
        btn2 = findViewById(R.id.number_two);
        btn3 = findViewById(R.id.number_three);
        btn4 = findViewById(R.id.number_four);
        btn5 = findViewById(R.id.number_five);
        btn6 = findViewById(R.id.number_six);
        btn7 = findViewById(R.id.number_seven);
        btn8 = findViewById(R.id.number_eight);
        btn9 = findViewById(R.id.number_nine);
        textAlpha = findViewById(R.id.txt_alpha);
        textAlphaToMorse = findViewById(R.id.txt_alpha_to_morse);
        btnBackSpace = findViewById(R.id.btn_backspace);
        textAlpha.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        textAlphaToMorse.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        morseCodeMap = new HashMap<>();
        morseCodeMap.put("._", "A");
        morseCodeMap.put("_...", "B");
        morseCodeMap.put("___.", "C");
        morseCodeMap.put("_..", "D");
        morseCodeMap.put(".", "E");
        morseCodeMap.put(".._.", "F");
        morseCodeMap.put("__.", "G");
        morseCodeMap.put("....", "H");
        morseCodeMap.put("..", "I");
        morseCodeMap.put(".___", "J");
        morseCodeMap.put("_._", "K");
        morseCodeMap.put("._..", "L");
        morseCodeMap.put("____", "M");
        morseCodeMap.put("_.", "N");
        morseCodeMap.put("___", "O");
        morseCodeMap.put(".__.", "P");
        morseCodeMap.put("__._", "Q");
        morseCodeMap.put("._.", "R");
        morseCodeMap.put("...", "S");
        morseCodeMap.put("_", "T");
        morseCodeMap.put(".._", "U");
        morseCodeMap.put("..._", "V");
        morseCodeMap.put(".__", "W");
        morseCodeMap.put("_.._", "X");
        morseCodeMap.put("_.__", "Y");
        morseCodeMap.put("__..", "Z");
        morseCodeMap.put(".____", "1");
        morseCodeMap.put("..___", "2");
        morseCodeMap.put("...__", "3");
        morseCodeMap.put("...._", "4");
        morseCodeMap.put(".....", "5");
        morseCodeMap.put("_....", "6");
        morseCodeMap.put("__...", "7");
        morseCodeMap.put("___..", "8");
        morseCodeMap.put("____.", "9");
        morseCodeMap.put("_____", "0");

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("._");
                appendToDotDashTextViewW("A");
                playSoundEffect(buttonId);

            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("_...");
                appendToDotDashTextViewW("B");
                playSoundEffect(buttonId);
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("___.");
                appendToDotDashTextViewW("C");
                playSoundEffect(buttonId);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("_..");
                appendToDotDashTextViewW("D");
                playSoundEffect(buttonId);
            }
        });
        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView(".");
                appendToDotDashTextViewW("E");
                playSoundEffect(buttonId);
            }
        });
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView(".._.");
                appendToDotDashTextViewW("F");
                playSoundEffect(buttonId);
            }
        });
        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("__.");
                appendToDotDashTextViewW("G");
                playSoundEffect(buttonId);
            }
        });
        btnH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("....");
                appendToDotDashTextViewW("H");
                playSoundEffect(buttonId);
            }
        });
        btnI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("..");
                appendToDotDashTextViewW("I");
                playSoundEffect(buttonId);
            }
        });
        btnJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView(".___");
                appendToDotDashTextViewW("J");
                playSoundEffect(buttonId);
            }
        });
        btnK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("_._");
                appendToDotDashTextViewW("K");
                playSoundEffect(buttonId);
            }
        });
        btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("._..");
                appendToDotDashTextViewW("L");
                playSoundEffect(buttonId);
            }
        });
        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("____");
                appendToDotDashTextViewW("M");
                playSoundEffect(buttonId);
            }
        });
        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("_.");
                appendToDotDashTextViewW("N");
                playSoundEffect(buttonId);
            }
        });
        btnO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("___");
                appendToDotDashTextViewW("O");
                playSoundEffect(buttonId);
            }
        });
        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView(".__.");
                appendToDotDashTextViewW("P");
                playSoundEffect(buttonId);
            }
        });
        btnQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("__._");
                appendToDotDashTextViewW("Q");
                playSoundEffect(buttonId);
            }
        });
        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("._.");
                appendToDotDashTextViewW("R");
                playSoundEffect(buttonId);
            }
        });
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("...");
                appendToDotDashTextViewW("S");
                playSoundEffect(buttonId);
            }
        });
        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("_");
                appendToDotDashTextViewW("T");
                playSoundEffect(buttonId);
            }
        });
        btnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView(".._");
                appendToDotDashTextViewW("U");
                playSoundEffect(buttonId);
            }
        });
        btnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("..._");
                appendToDotDashTextViewW("V");
                playSoundEffect(buttonId);
            }
        });
        btnW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView(".__");
                appendToDotDashTextViewW("W");
                playSoundEffect(buttonId);
            }
        });
        btnX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("_.._");
                appendToDotDashTextViewW("X");
                playSoundEffect(buttonId);
            }
        });
        btnY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("_.__");
                appendToDotDashTextViewW("Y");
                playSoundEffect(buttonId);
            }
        });
        btnZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("__..");
                appendToDotDashTextViewW("Z");
                playSoundEffect(buttonId);
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("_____");
                appendToDotDashTextViewW("0");
                playSoundEffect(buttonId);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView(".____");
                appendToDotDashTextViewW("1");
                playSoundEffect(buttonId);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("..___");
                appendToDotDashTextViewW("2");
                playSoundEffect(buttonId);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("...___");
                appendToDotDashTextViewW("3");
                playSoundEffect(buttonId);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("...._-");
                appendToDotDashTextViewW("4");
                playSoundEffect(buttonId);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView(".....");
                appendToDotDashTextViewW("5");
                playSoundEffect(buttonId);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("_....");
                appendToDotDashTextViewW("6");
                playSoundEffect(buttonId);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("__...");
                appendToDotDashTextViewW("7");
                playSoundEffect(buttonId);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("___..");
                appendToDotDashTextViewW("8");
                playSoundEffect(buttonId);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToDotDashTextView("____.");
                appendToDotDashTextViewW("9");
                playSoundEffect(buttonId);
            }
        });
        btnBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearDotDashTextView();
            }
        });
    }
    private void appendToDotDashTextView(String symbol) {
        textAlpha.setText(textAlpha.getText() + " " +symbol);
    }
    private void appendToDotDashTextViewW(String symbol) {
        textAlphaToMorse.setText(textAlphaToMorse.getText()+symbol);
    }
    private void clearDotDashTextView() {
        textAlpha.setText("");
        textAlphaToMorse.setText("");
    }
    private void initializeSoundPool() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(MAX_STREAMS)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            //noinspection deprecation
            soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }

        buttonId = soundPool.load(this, R.raw.button_click_sound, 1);
    }
    private void playSoundEffect(final int soundId) {
        if (soundPool != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
                }
            }).start();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
    /*
    private int getRandomColor() {
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
     */
}
