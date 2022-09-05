package outOfTopic.EnglishMacedonianTranslation;

import java.io.*;

public class Main {

    /**
     * You can simply add your absolute path for your own text file,
     * just copy the *absolute* path and replace it with the 'SOURCE_PATH' String variable.
     * <p>
     * For the 'DESTINATION_PATH', same thing, copy the absolute path with new 'output' text file
     * shown as here, where you want the output to be shown
     */
    public static String SOURCE_PATH = "C:\\Users\\Ace\\Desktop\\input.txt";
    public static String DESTINATION_PATH = "C:\\Users\\Ace\\Desktop\\output.txt";

    public static void main(String[] args) {
        File sourceFile = new File(SOURCE_PATH);
        File outputFormattedFile = new File(DESTINATION_PATH);

        /**
         * If the output file exists, then delete it.
         */
        if (outputFormattedFile.exists()) {
            outputFormattedFile.delete();
        }

        FirstSecondLanguageApplication firstSecondLanguageApplication = new FirstSecondLanguageApplication();
        try {

            /**
             * Calling the 'readInput()' method
             */
            firstSecondLanguageApplication.readInput(new FileInputStream(sourceFile));

            /**
             * Calling the sendOutput() method. You can test this in the console (System.out), via 'FileOutputStream'
             * or both :)
             */
            firstSecondLanguageApplication.sendOutput(new FileOutputStream(outputFormattedFile));
            firstSecondLanguageApplication.sendOutput(System.out);
        } catch (IOException e) {
            System.err.println("Error with files");
            return;
        }
        //System.out.println("Working");
    }
}
