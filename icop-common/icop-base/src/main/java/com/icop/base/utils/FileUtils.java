package com.icop.base.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: liukj
 * @date: 2020/7/14
 * @description：
 */
public class FileUtils {
    private FileUtils(){}

    public static void main(String[] args) {
        String file1 = "E:\\Documents\\image\\swiper\\201911261833002.jpg";
        String file2 = "E:\\Documents\\image\\swiper\\newfile.jpg";
        try {
            copyFile(file1,file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 文件的复制，
     *  阻塞式文件复制
     */
    public static void copyFile(String originPath, String targetPath) throws IOException {
        try(
                FileInputStream fis = new FileInputStream(originPath);
                FileChannel inChannel = fis.getChannel();
                FileOutputStream fos = new FileOutputStream(targetPath);
                FileChannel fosChannel = fos.getChannel()
        ){
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            while (inChannel.read(byteBuffer) != -1){
                byteBuffer.flip();
                fosChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        }
    }



}
