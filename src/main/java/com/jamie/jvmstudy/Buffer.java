package com.jamie.jvmstudy;

import java.nio.ByteBuffer;

/**
 * @PackageName: com.jamie.jvmstudy
 * @ClassName: Buffer
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/2 3:33 下午
 */
public class Buffer {
    public static void main(String[] args) {


        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 看一下初始时4个核心变量的值
        System.out.println("初始时-->limit--->"+byteBuffer.limit());
        System.out.println("初始时-->position--->"+byteBuffer.position());
        System.out.println("初始时-->capacity--->"+byteBuffer.capacity());
        System.out.println("初始时-->mark--->" + byteBuffer.mark());

        System.out.println("--------------------------------------");

        // 添加一些数据到缓冲区中
        String s = "Java3y";
        byteBuffer.put(s.getBytes());

        // 看一下初始时4个核心变量的值
        System.out.println("put完之后-->limit--->"+byteBuffer.limit());
        System.out.println("put完之后-->position--->"+byteBuffer.position());
        System.out.println("put完之后-->capacity--->"+byteBuffer.capacity());
        System.out.println("put完之后-->mark--->" + byteBuffer.mark());

        byte[] bytes = new byte[byteBuffer.limit()];
        // 将读取的数据装进我们的字节数组中
        byteBuffer.get(bytes);

        // 输出数据
        System.out.println(new String(bytes, 0, 6));

    }


}
