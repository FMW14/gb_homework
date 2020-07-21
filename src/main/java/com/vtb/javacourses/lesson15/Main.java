package com.vtb.javacourses.lesson15;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        Path file1 = Paths.get("./src/main/resources/lesson15/task1.txt");
        Path path2 = Paths.get("./src/main/resources/lesson15/task2");

//        System.out.println(getNumberOfSequence(file1, "we"));

        joinAllFiles(path2);
//        joinAllFiles(Paths.get("./src/main/resources/lesson15/task4"));

    }

    //Task1
    public static int getNumberOfSequence(Path path, String sequence) throws IOException {
//        int cap = 8192;
        final int cap = sequence.length() * 10;
        int resultCount = 0;
        byte[] sequenceByteArr = sequence.getBytes();

        RandomAccessFile aFile = new RandomAccessFile(path.toString(), "r");
        ByteBuffer buf = ByteBuffer.allocate(cap);
        FileChannel inChannel = aFile.getChannel();
        int bytesRead = inChannel.read(buf);

        while (bytesRead != -1) {
            buf.flip();
//            System.out.println("---");
            while (buf.hasRemaining()) {
                boolean isMatch = false;
                int lastPos = 0;

                if ((char) buf.get() == sequenceByteArr[0]) {
                    isMatch = true;
                    lastPos = buf.position();
                }

                int index = 1;
                while (isMatch) {
                    if (index == sequenceByteArr.length && isMatch) {
                        resultCount++;
                        buf.position(lastPos);
                        break;
                    }

                    if ((char) buf.get() != sequenceByteArr[index]) {
                        isMatch = false;
                        buf.position(lastPos);
                    }

                    index++;
                }

                if (buf.limit() - buf.position() < sequenceByteArr.length) {
                    break;
                }
            }

            buf.compact();
            bytesRead = inChannel.read(buf);
        }

        aFile.close();

        return resultCount;
    }

    //Task2
    public static Path joinAllFiles(Path dir) throws IOException {
        if (!Files.exists(dir)) {
            throw new RuntimeException(dir + " doesnt exists");
        }

        List<Path> pathList = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(dir.toString()))) {

            pathList = paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".txt"))
                    .collect(Collectors.toList());
//                    .forEach(System.out::println);
        }

        System.out.println(pathList);

        String resultFileName = dir.getParent().toString() + File.separator + dir.getFileName().toString() + "_joined.txt";
        if (Files.exists(Paths.get(resultFileName))) {
            Files.delete(Paths.get(resultFileName));
        }

        RandomAccessFile aFile = new RandomAccessFile(resultFileName, "rw");
        FileChannel destChannel = aFile.getChannel();

        long position = 0;
        for (Path curPath : pathList) {
            FileChannel sourceChannel = new FileInputStream(curPath.toString()).getChannel();
            destChannel.transferFrom(sourceChannel, position, sourceChannel.size());
            position += sourceChannel.size();
//            System.out.println(position);
            sourceChannel.close();
        }
        destChannel.close();

        return Paths.get(resultFileName);
    }

}
