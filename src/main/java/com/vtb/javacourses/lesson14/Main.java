package com.vtb.javacourses.lesson14;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String FILENAME1 = "./src/main/resources/lesson14/task1/data1.txt";
        File file = new File(FILENAME1);

//        String readed = readFile(file);
//        System.out.println(readed);

//        int result = getNumberOfSequence(readFile(file).toString(), "wer");
        getNumberOfSequence(readFile(file), "wer");

        joinAllFiles(new File("./src/main/resources/lesson14/task2/"));

    }

    //Task1
    public static int getNumberOfSequence(String string, String sequence) {
        System.out.println("Gotten sequence: " + sequence);

        int index = string.indexOf(sequence);
        int count = 0;
        while (index != -1) {
            count++;
            index = string.indexOf(sequence, index + 1);
        }

        System.out.println("Number of sequence entries: " + count);

        return count;
    }

    public static String readFile(File file) throws IOException {
        StringBuilder resultReader1 = new StringBuilder();
        FileInputStream fstream = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            resultReader1.append(strLine).append("\n");
        }

        br.close();
        return resultReader1.toString();
    }

    //Task2
    public static File joinAllFiles(File dir) throws IOException {
        if (dir == null) {
            throw new FileNotFoundException();
        }
        if (!dir.isDirectory()) {
            throw new FileNotFoundException(dir + " is not directory");
        }
        if (!dir.exists()) {
            throw new FileNotFoundException(dir + " does not exists");
        }

        if (dir.listFiles() != null) {
            File[] files = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    int index = name.indexOf(".");
                    return name.substring(index).equals(".txt");
                }
            });

//            File joined = new File(dir.getPath() + "/joined_" + dir.getName() + ".txt");  //NOT WORKING
            String outputPath = dir.getPath().substring(0, dir.getPath().lastIndexOf(File.separator)) + File.separator +"joined_" + dir.getName() + ".txt";    //separator must always exists in correct path
            File joined = new File(outputPath);
            FileOutputStream foStream = new FileOutputStream(joined, false);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(foStream));

            System.out.println("Starting merge files in " + dir.getPath() + " into " + outputPath);
            for (File curFile : files) {
                System.out.println("Joining " + curFile.getName());
                bufferedWriter.write(readFile(curFile));
                bufferedWriter.flush();
            }
            bufferedWriter.close();

            System.out.println("Files merged");
            return joined;
        } else {
            throw new RuntimeException(dir + " has no files");
        }
    }
}
