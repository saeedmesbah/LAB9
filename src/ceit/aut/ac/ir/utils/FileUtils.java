package ceit.aut.ac.ir.utils;

import ceit.aut.ac.ir.model.Note;

import java.io.*;
import java.util.Date;

public class FileUtils {

    private static final String NOTES_PATH = "./notes/";

    //It's a static initializer. It's executed when the class is loaded.
    //It's similar to constructor
    static {
        boolean isSuccessful = new File(NOTES_PATH).mkdirs();
        System.out.println("Creating " + NOTES_PATH + " directory is successful: " + isSuccessful);
    }

    public static File[] getFilesInDirectory() {
        return new File(NOTES_PATH).listFiles();
    }


    public static String fileReader(File file){
        //TODO: Phase1: read content from file
        String s="";
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(file);
            int input;
            while ((input = fileReader.read()) != -1)
                s=s+(char)input;
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void fileWriter(String content){
        //TODO: write content on file
        Date date = new Date();
        String fileName = getProperFileName(content);
        Note note = new Note(fileName, content, date.toString());
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(".\\notes\\"+fileName);
            fileWriter.write(note.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //TODO: Phase1: define method here for reading file with InputStream
    public static String fileReader2(File file) throws IOException {
        FileInputStream in = null;
        String s="";
        try {
            in = new FileInputStream(file);
            int c;
            while ((c = in.read()) != -1) {
                s=s+(char)c;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return s;
    }

    //TODO: Phase1: define method here for writing file with OutputStream
    public static void fileWriter2(String content) throws FileNotFoundException {
        FileOutputStream out = null;
        String fileName = getProperFileName(content);
        out = new FileOutputStream(".\\notes\\"+fileName);


    }

    //TODO: Phase2: proper methods for handling serialization

    private static String getProperFileName(String content) {

        int loc = content.indexOf("\n");
        if (loc != -1) {
            return content.substring(0, loc);
        }
        if (!content.isEmpty()) {
            return content;
    }
        return System.currentTimeMillis() + "_new file.txt";
    }
}
