package com.vtb.javacourses.lesson15;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        Path file1 = Paths.get("./src/main/resources/lesson15/task1.txt");

        System.out.println(getNumberOfSequence(file1, "we"));

    }

    //Task1
    public static int getNumberOfSequence(Path path, String sequence) throws IOException {
//        int cap = 8192;
        final int cap = sequence.length()*10;
        int resultCount = 0;
        byte[] sequenceByteArr = sequence.getBytes();

        RandomAccessFile aFile = new RandomAccessFile(path.toString(), "r");
        ByteBuffer buf = ByteBuffer.allocate(cap);
        FileChannel inChannel = aFile.getChannel();
        int bytesRead = inChannel.read(buf);

        while (bytesRead != -1) {
            buf.flip();
//            System.out.println("---");
            while(buf.hasRemaining()){
                boolean isMatch = false;
                int lastPos = 0;

                if ((char) buf.get() == sequenceByteArr[0]) {
                    isMatch = true;
                    lastPos = buf.position();
                }

                int index = 1;
                while (isMatch){
                    if(index == sequenceByteArr.length && isMatch){
                        resultCount++;
                        buf.position(lastPos);
                        break;
                    }

                    if ((char) buf.get() != sequenceByteArr[index]){
                        isMatch = false;
                        buf.position(lastPos);
                    }

                    index++;
                }

                if (buf.limit() - buf.position() < sequenceByteArr.length){
                    break;
                }
            }

            buf.compact();
            bytesRead = inChannel.read(buf);
        }

        aFile.close();

        return resultCount;
    }


}
