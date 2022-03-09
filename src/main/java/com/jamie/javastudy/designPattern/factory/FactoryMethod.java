package com.jamie.javastudy.designPattern.factory;

/**
 * @PackageName: com.jamie.javastudy.designPattern.factory
 * @ClassName: SimpleFactory2
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 11:44 上午
 */
public class FactoryMethod {

    public static void main(String[] args) {
        System.out.println("simpleFactory start ...");

        FruitFactoryStore fruitStore = new FruitFactoryStore();
        Product product1 = fruitStore.newProduct(1);
        product1.show();
        Product product2 = fruitStore.newProduct(2);
        product2.show();
        BigFactoryStore bigFactoryStore = new BigFactoryStore();
        Product product3 = bigFactoryStore.newProduct(1);
        product3.show();
        Product product4 = bigFactoryStore.newProduct(2);
        product4.show();
        System.out.println("simpleFactory end ...");
    }

    public interface Product{
        void show();
    }

    public interface AbstractFactory {
        Product newProduct(int kind);
    }

    public static class Apple implements Product{
        @Override
        public void show() {
            System.out.println("生产了一个苹果");
        }
    }

    public static class Watermelon implements Product{
        @Override
        public void show() {
            System.out.println("生产了一个西瓜");
        }
    }

    public static class Watch implements Product{
        @Override
        public void show() {
            System.out.println("生产了一个电脑");
        }
    }

    public static class Tv implements Product{
        @Override
        public void show() {
            System.out.println("生产了一个电视");
        }
    }

    public static class FruitFactoryStore implements AbstractFactory{
        @Override
        public Product newProduct(int kind) {
            switch (kind) {
                case 1:
                    return new Apple();
                default:
                    return new Watermelon();
            }
        }
    }

    public static class BigFactoryStore implements AbstractFactory{
        @Override
        public Product newProduct(int kind) {
            switch (kind) {
                case 1:
                    return new Watch();
                default:
                    return new Tv();
            }
        }
    }
}
