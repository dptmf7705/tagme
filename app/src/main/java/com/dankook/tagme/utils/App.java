package com.dankook.tagme.utils;

import android.app.Application;

public class App extends Application {

    private int windowWidth;
    private int windowHeight;

    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }
}
