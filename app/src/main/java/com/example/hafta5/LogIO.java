package com.example.hafta5;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class LogIO {

    private static final String FILE_NAME = "CallLogs.txt";
    private static ArrayList<String> logs ;

    public static void saveLogs(Context context, ArrayList<String> logs){
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(logs);
            outputStream.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> loadLogs(Context context){
        logs = new ArrayList<String>();
        try {

            FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            logs = (ArrayList<String>) inputStream.readObject();
            inputStream.close();
            fileInputStream.close();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return logs;
    }

}
