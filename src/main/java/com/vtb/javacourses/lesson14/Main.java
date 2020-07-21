package com.vtb.javacourses.lesson14;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String FILENAME1 = "./src/main/resources/lesson14/task1/data1.txt";
        File file = new File(FILENAME1);

        getNumberOfSequence(readFile(file), "wer");

        joinAllFiles(new File("./src/main/resources/lesson14/task2/"));
//        joinAllFiles(new File("./src/main/resources/lesson14/task/"));
//        joinAllFiles(new File("./src/main/resources/lesson14/testdir"));

        File dirForDelete = new File("./src/main/resources/lesson14/task3/");
        deleteDir(dirForDelete);
//        deleteDir(null);

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
    public static File joinAllFiles(File dir) throws IOException { //TODO записывать в итоговый файл по мере чтения файлов
        if (!dir.exists()) {
            throw new FileNotFoundException("Dir does not exists: " + (dir.getAbsolutePath()));
        }
        if (!dir.isDirectory()) {
            throw new InvalidPathException(dir.getAbsolutePath() + " is not directory");
        }
        if (dir.listFiles().length != 0) {
            File[] files = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    int index = name.indexOf(".");
                    return name.substring(index).equals(".txt");
                }
            });

//            File joined = new File(dir.getPath() + "/joined_" + dir.getName() + ".txt");  //NOT WORKING (working only once exact)
            String outputPath = dir.getPath().substring(0, dir.getPath().lastIndexOf(File.separator)) + File.separator + "joined_" + dir.getName() + ".txt";    //separator must always exists in correct path
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
            throw new EmptyDirectoryException(dir.getAbsolutePath() + " has no files");
        }
    }

    //Task3
    public static void deleteDir(File path) {
        if (!path.exists()) {
            System.err.println("Dir does not exists: " + path.getAbsolutePath());
            return;
        }

        if (path.isDirectory()) {
            File[] files = path.listFiles();
            if (files.length != 0) {
                for (File curFile : files) {
                    deleteDir(curFile);
                }
            }
        }

        System.out.println("Deleting " + path.getAbsoluteFile());
        path.delete();
    }
}
