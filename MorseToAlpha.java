package com.myrestaurant.morsemate;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MorseToAlpha extends AppCompatActivity {
    TextView morseCode,morseToAlphaCode;
    Button morseDot,morseDash,btnSpace,btnBackSpace;
    private Map<String, String> morseCodeMap;
    private static final int MAX_STREAMS = 5;
    private SoundPool soundPool;
    private int dotSoundId, dashSoundId,spaceSoundId,backspaceSoundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.morse_to_alpha);
        initializeSoundPool();
        morseCode = findViewById(R.id.txt_morse_code);
        morseCode.setTextSize(TypedValue.COMPLEX_UNIT_SP, 34);
        morseToAlphaCode = findViewById(R.id.txt_morse_to_alpha);
        morseToAlphaCode.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
        morseDot = findViewById(R.id.morse_dot);
        btnBackSpace = findViewById(R.id.btn_backspace);
        morseDash = findViewById(R.id.morse_dash);
        btnSpace = findViewById(R.id.space);
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

        morseDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToDotDashTextView(".");
                playSoundEffect(dotSoundId);
            }
        });
        morseDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToDotDashTextView("_");
                playSoundEffect(dashSoundId);
            }
        });
        btnBackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSoundEffect(backspaceSoundId);
                handleBackspace();
            }
        });
        btnBackSpace.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                playSoundEffect(backspaceSoundId);
                clearDotDashTextView();
                return true;
            }
        });

        morseCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                morseToAlphaCode.setText(translateToMorseCode(editable.toString()));
            }
        });
        btnSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSoundEffect(spaceSoundId);
                handleSpace();
            }
        });
    }
    private void appendToDotDashTextView(String symbol) {
        morseCode.setText(morseCode.getText() + symbol);
    }
    private String translateToMorseCode(String dotDashString) {
        StringBuilder morseCodeBuilder = new StringBuilder();
        String currentSequence = "";

        for (int i = 0; i < dotDashString.length(); i++) {
            char c = dotDashString.charAt(i);
            if (c == '.' || c == '_') {
                currentSequence += c;
            } else {
                String translation = morseCodeMap.getOrDefault(currentSequence, "?");
                morseCodeBuilder.append(translation);
                currentSequence = "";
            }
        }
        if (!currentSequence.isEmpty()) {
            String translation = morseCodeMap.getOrDefault(currentSequence, "?");
            morseCodeBuilder.append(translation);
        }

        return morseCodeBuilder.toString();
    }
    private void handleBackspace() {
        String currentText = morseCode.getText().toString();
        if (!currentText.isEmpty()) {
            morseCode.setText(currentText.substring(0, currentText.length() - 1));
        }
    }
    private void handleSpace() {
        String currentText = morseCode.getText().toString();
        morseCode.setText(currentText + " ");
        morseToAlphaCode.setText(translateToMorseCode(currentText));
    }
    private void clearDotDashTextView() {
        morseCode.setText("");
        morseToAlphaCode.setText("");
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

        dotSoundId = soundPool.load(this, R.raw.dot_sound, 1);
        dashSoundId = soundPool.load(this, R.raw.dash_sound, 1);
        spaceSoundId = soundPool.load(this,R.raw.space_sound,1);
        backspaceSoundId = soundPool.load(this,R.raw.backspace_sound,1);
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
