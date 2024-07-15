package com.example.ap;
import java.io.*;

public class Storegame {
    private static gameData gameData;

    static void serialize() throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("C:\\Users\\arava\\Downloads\\2022091\\ap\\src\\main\\java\\com\\example\\ap\\Data"));
            out.writeObject(gameData);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(out!=null){
            out.close();
        }}
    }

    public static void deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream (new FileInputStream("C:\\Users\\arava\\Downloads\\2022091\\ap\\src\\main\\java\\com\\example\\ap\\Data"));
            gameData = (gameData) in.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }finally {if(in!=null) {
            in.close();
        }
        }
    }

}