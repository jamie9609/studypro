package com.jamie.javastudy.designPattern.factory;

/**
 * @PackageName: com.jamie.javastudy.designPattern.factory
 * @ClassName: SimpleFactory
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 11:32 上午
 */
public class SimpleFactory {
    public static void main(String[] args) {
        System.out.println("SimpleFactory build start...");

        Product product1 = SimpleFactoryClient.makeProduct(1);
        product1.show();
        Product product2 = SimpleFactoryClient.makeProduct(2);
        product2.show();
        System.out.println("SimpleFactory end...");
    }

    public interface Product {
        void show();
    }

    static class ConcreteProduct1 implements Product {
        @Override
        public void show() {
            System.out.println("产品1 是 苹果");
        }
    }

    static class ConcreteProduct2 implements Product {
        @Override
        public void show() {
            System.out.println("产品2 是 西瓜");
        }
    }


    static class SimpleFactoryClient {

        public static Product makeProduct(int kind) {
            switch (kind) {
                case 1 :
                    return new ConcreteProduct1();
                default :
                    return new ConcreteProduct2();
            }
        }
    }


}

