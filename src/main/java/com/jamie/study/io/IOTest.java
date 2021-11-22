package com.jamie.study.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @PackageName: com.jamie.study.io
 * @ClassName: IOTest
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 8:32 下午
 */
public class IOTest {
    public static void main(String[] args) throws IOException {
        File file = new File("./test.txt");
        write(file);
        System.out.println(read2(file));
    }

    /**
     * 字符流
     * @param file
     * @throws IOException
     */
    public static void write(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        String str = "松下问童子，言师采药去。只在此山中，云深不知处。";
        outputStreamWriter.write(str);
        outputStreamWriter.close();
    }

    /**
     * 字符流
     * @param file
     * @throws IOException
     */
    public static String read(File file) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);

        char[] chars = new char[1024];
        StringBuilder sb = new StringBuilder();
        int length;
        while ((length = inputStreamReader.read(chars)) != -1) {
            sb.append(chars, 0, length);
        }

        inputStreamReader.close();
        return sb.toString();
    }

    /**
     * 字符缓冲流
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String read2(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        // 用来接收读取的字节数组
        StringBuilder sb = new StringBuilder();

        // 按行读数据
        String line;
        // 循环取数据
        while ((line = br.readLine()) != null) {
            // 将读取的内容转换成字符串
            sb.append(line);
        }
        // 关闭流
        br.close();

        return sb.toString();
    }

}
