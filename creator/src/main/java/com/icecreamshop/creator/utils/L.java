package com.icecreamshop.creator.utils;

import android.util.Log;

import com.icecreamshop.creator.BuildConfig;

import java.util.ArrayList;

/**
 * A Wrapper around the Android log for ease of use
 */
public class L {

    private static final String TAG = "icecreamshop";

    private static final boolean DEBUG = BuildConfig.DEBUG;

    public static void d() {
        d("\n");
    }

    public static void d(String msg) {
        if (DEBUG) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();

            String callerClassName = elements[1].getFileName();
            String methodName = elements[1].getMethodName();
            ArrayList<String> messageList = splitString(msg);
            for (String message : messageList) {
                Log.d(TAG, "[" + callerClassName.replace("java", "") + methodName + "] " + message);
            }
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();

            String callerClassName = elements[1].getFileName();
            String methodName = elements[1].getMethodName();
            ArrayList<String> messageList = splitString(msg);
            for (String message : messageList) {
                Log.i(TAG, "[" + callerClassName.replace("java", "") + methodName + "] " + message);
            }
        }
    }

    public static void e(String msg, Throwable cause) {
        if (DEBUG) {
            ArrayList<String> messageList = splitString(msg);
            for (String message : messageList) {
                Log.e(TAG, "[" + message + "]", cause);
            }
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();

            String callerClassName = elements[1].getFileName();
            String methodName = elements[1].getMethodName();
            ArrayList<String> messageList = splitString(msg);
            for (String message : messageList) {
                Log.e(TAG, "[" + callerClassName.replace("java", "") + methodName + "] " + message);
            }
        }
    }

    /**
     * Divides a string into chunks of a given character size.
     *
     * @param text      String text to be sliced
     * @param sliceSize int Number of characters
     * @return ArrayList<String>   Chunks of strings
     */
    private static ArrayList<String> splitString(String text, int sliceSize) {
        ArrayList<String> textList = new ArrayList<>();
        if (text != null) {
            String aux;
            int left, right = 0;
            int charsLeft = text.length();
            while (charsLeft != 0) {
                left = right;
                if (charsLeft >= sliceSize) {
                    right += sliceSize;
                    charsLeft -= sliceSize;
                } else {
                    right = text.length();
                    charsLeft = 0;
                }
                aux = text.substring(left, right);
                textList.add(aux);
            }
        } else {
            textList.add("You want to Log.d (*null*)!");
        }
        return textList;
    }

    /**
     * Divides a string into chunks.
     *
     * @param text String text to be sliced
     * @return ArrayList<String>
     */
    private static ArrayList<String> splitString(String text) {
        return splitString(text, 1000);
    }
}
