package com.jamie.jvmstudy;

/**
 * @PackageName: com.jamie.jvmstudy
 * @ClassName: Deathlock
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/2 2:32 下午
 */
public class DeathLock {

    public static void main(String[] args) {
        final Object a = new Object();
        final Object b = new Object();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (a) {
                    try {
                        System.out.println("now i in threadA-lock-a");
                        Thread.sleep(1000l);
                        synchronized (b) {
                            System.out.println("now i in threadA-lock-b");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (b) {
                    try {
                        System.out.println("now i in threadB-lock-b");
                        Thread.sleep(1000l);
                        synchronized (a) {
                            System.out.println("now i in threadB-lock-a");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        threadA.start();
        threadB.start();

    }
}
