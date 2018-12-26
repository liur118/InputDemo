package com.loocup.inputdemo;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

import java.util.Arrays;

public class SimpleIME extends InputMethodService implements KeyboardView.OnKeyboardActionListener {
    private KeyboardView kv;
    private Keyboard keyboard;
    private boolean caps = false;
    private final String TAG = "simple-imput-method";

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        Log.i(TAG, String.format("..........onKey method(%d, %s)", primaryCode, Arrays.toString(keyCodes)));
        InputConnection ic = getCurrentInputConnection();
        playClick(primaryCode);
        switch(primaryCode){
            case -5 :
                ic.deleteSurroundingText(1, 0);
                break;
            case -1:
                caps = !caps;
                keyboard.setShifted(caps);
                kv.invalidateAllKeys();
                break;
            case -4:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case -6:
                // 上
                Log.i(TAG, "-6");
                ic.sendKeyEvent(new KeyEvent(KeyEvent.KEYCODE_SYSTEM_NAVIGATION_UP, -6));
                break;
            case -7:
                // 下
                Log.i(TAG, "-7");
                ic.sendKeyEvent(new KeyEvent(KeyEvent.KEYCODE_SYSTEM_NAVIGATION_DOWN, -7));
                break;
            case -8:
                // 左
                Log.i(TAG, "-8");
                ic.sendKeyEvent(new KeyEvent(KeyEvent.KEYCODE_SYSTEM_NAVIGATION_LEFT, -8));
                break;
            case -9:
                // 右
                Log.i(TAG, "-9");
                ic.sendKeyEvent(new KeyEvent(KeyEvent.KEYCODE_SYSTEM_NAVIGATION_RIGHT, -9));
                break;
            default:
                char code = (char)primaryCode;
                if(Character.isLetter(code) == caps){
                    code = Character.toUpperCase(code);
                }
            ic.commitText(String.valueOf(code),1);
        }
    }

    @Override
    public void onPress(int primaryCode) {
    }

    @Override
    public void onRelease(int primaryCode) {
    }

    @Override
    public void onText(CharSequence text) {
    }

    @Override
    public void swipeDown() {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeRight() {
    }

    @Override
    public void swipeUp() {
    }

    @Override
    public View onCreateCandidatesView() {
        return super.onCreateCandidatesView();
    }

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }

    private void playClick(int keyCode){
        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        switch(keyCode){
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }
}