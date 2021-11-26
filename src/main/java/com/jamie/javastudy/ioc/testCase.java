package com.jamie.javastudy.ioc;

/**
 * @PackageName: com.jamie.study.ioc
 * @ClassName: testCase
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/15 8:44 上午
 */
public class testCase {
    static class A {

        public A() {

            new B(this).print();// 调用B的方法

        }

        public void print() {

            System.out.println("HelloAA from A!");

        }

    }

    static class B {

        A a;

        public B(A a) {

            this.a = a;

        }

        public void print() {

            a.print();//调用A的方法

            System.out.println("HelloAB from B!");

        }

    }

    public static class HelloA {

        public static void main(String[] args) {

            A aaa = new A();

            aaa.print();

            B bbb = new B(aaa);

            bbb.print();

        }

    }
}
