package com.algorithm.tsp.antClonyOptimization;

import lombok.Data;

import java.util.Random;
import java.util.Vector;

/**
 * 蚁群算法原理：
 * 假如蚁群中所有蚂蚁的数量为m，所有城市之间的信息素用矩阵pheromone表示，最短路径为bestLength，最佳路径为bestTour。每只蚂蚁都有自己的内存，内存中用一个禁忌表（Tabu）来存储该蚂蚁已经访问过的城市，表示其在以后的搜索中将不能访问这些城市；还有用另外一个允许访问的城市表（Allowed）来存储它还可以访问的城市；另外还用一个矩阵（Delta）来存储它在一个循环（或者迭代）中给所经过的路径释放的信息素；还有另外一些数据，例如一些控制参数（clip_image002[4]，clip_image002[6]，clip_image002[8]，Q），该蚂蚁行走玩全程的总成本或距离（tourLength），等等。假定算法总共运行MAX_GEN次，运行时间为t。
 * @PackageName: com.algorithm.tsp.antClonyOptimization
 * @ClassName: Ant
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/13 8:06 下午
 */
@Data
public class Ant implements Cloneable{

    /**
     * 已搜索过的城市
     */
    private Vector<Integer> tabu; // 已搜索过的城市
    /**
     * 尚未搜索的城市
     */
    private Vector<Integer> allowedCities; // 尚未搜索的城市
    /**
     * 信息素变化矩阵
     */
    private double[][] delta; // 信息素变化矩阵
    /**
     * 城市间距离矩阵
     */
    private int[][] distance; // 城市间距离矩阵
    /**
     * 公式常量
     */
    private double alpha; // 公式常量
    /**
     * 公式常量
     */
    private double beta; // 公式常量
    /**
     * 路径长度
     */
    private int tourLength; // 路径长度
    /**
     * 城市数量
     */
    private int cityNum; // 城市数量
    /**
     * 起始城市
     */
    private int firstCity; // 起始城市
    /**
     * 当前城市
     */
    private int currentCity; // 当前城市


    public Ant(int cityNum, int tourLength) {
        this.cityNum = cityNum;
        this.tourLength = tourLength;
    }

    public Ant(int num)
    {
        cityNum = num;
        tourLength = 0;

    }

    /**
     * 初始化蚂蚁，随机选择起始位置
     *
     * @param distance 距离矩阵
     * @param a alpha
     * @param b beta
     */
    public void init(int[][] distance, double a, double b) {
        alpha = a;
        beta = b;
        allowedCities = new Vector<>();
        tabu = new Vector<>();
        this.distance = distance;
        delta = new double[cityNum][cityNum];
        // 初始化数据成员
        for (int i = 0; i < cityNum; i++) {
            allowedCities.add(i);
            for (int j = 0; j < cityNum; j++) {
                delta[i][j] = 0.0;
            }
        }
        // 随机选择第一个城市位置
        Random random = new Random(System.currentTimeMillis());
        firstCity = random.nextInt(cityNum);
        for (Integer i : allowedCities) {
            if (i == firstCity) {
                allowedCities.remove(i);
                break;
            }
        }
        tabu.add(firstCity);
        currentCity = firstCity;
    }

    /**
     * 选择下一个城市,根据信息素
     * @param pheromone 信息素矩阵
     */
    public void selectNextCity(double[][] pheromone) {
        double[] p = new double[cityNum];
        double sum = 0;
        // 计算分母部分
        for (Integer i : allowedCities) {
            sum += Math.pow(pheromone[currentCity][i], alpha)
                    * Math.pow(1.0 / distance[currentCity][i], beta);
        }
        // 计算概率矩阵
        for (int i = 0; i < cityNum; i++) {
            boolean flag = false;
            for (Integer j : allowedCities) {
                if (i == j) {
                    p[i] = (Math.pow(pheromone[currentCity][i], alpha) * Math
                            .pow(1.0 / distance[currentCity][i], beta))
                            / sum;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                p[i] = 0.0;
            }
        }

        // 采用轮盘赌选择下一个城市
        Random random = new Random(System.currentTimeMillis());
        double sleectP = random.nextDouble();
        int selectCity = 0;
        double sum1 = 0.f;
        for (int i = 0; i < cityNum; i++) {
            sum1 += p[i];
            if (sum1 >= sleectP) {
                selectCity = i;
                break;
            }
        }
        // 从允许选择的城市中去除select city
        for (Integer i : allowedCities) {
            if (i == selectCity) {
                allowedCities.remove(i);
                break;
            }
        }
        // 在已搜索过的城市表中添加select city
        tabu.add(selectCity);
        // 将当前城市改为选择的城市
        currentCity = selectCity;
    }

    /**
     * 计算当前蚂蚁路径长度
     *
     * @return 路径长度
     */
    private int calculateTourLength() {
        int len = 0;
        for (int i = 0; i < cityNum; i++) {
            len += distance[this.tabu.get(i)][this.tabu.get(i + 1)];
        }
        return len;
    }
}
