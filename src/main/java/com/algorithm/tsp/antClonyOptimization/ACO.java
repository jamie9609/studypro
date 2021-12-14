package com.algorithm.tsp.antClonyOptimization;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;

/**
 * @PackageName: com.algorithm.tsp.antClonyOptimization
 * @ClassName: ACO
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/13 8:25 下午
 */
@Data
@NoArgsConstructor
public class ACO {
    /**
     * 蚁群
     */
    private Ant[] ants; // 蚂蚁
    /**
     * 蚂蚁数量
     */
    private int antNum; // 蚂蚁数量
    /**
     * 城市数量
     */
    private int cityNum; // 城市数量
    /**
     * 迭代次数
     */
    private int MAX_GEN; // 迭代次数
    /**
     * 信息素矩阵
     */
    private double[][] pheromone; // 信息素矩阵
    /**
     * 距离矩阵
     */
    private int[][] distance; // 距离矩阵
    /**
     * 最佳长度
     */
    private int bestLength; // 最佳长度
    /**
     * 最佳路径
     */
    private int[] bestTour; // 最佳路径

    private double alpha;
    private double beta;
    /**
     * 信息素蒸发率
     */
    private double rho;

    public ACO(int cityNum, int antNum, int maxGen, double alpha, double beta, double rho) {
        this.cityNum = cityNum;
        this.antNum = antNum;
        this.ants = new Ant[antNum];
        this.MAX_GEN = maxGen;
        this.alpha = alpha;
        this.beta = beta;
        this.rho = rho;
    }

    /**
     * 初始化ACO算法类
     *
     * @param filename
     *            数据文件名，该文件存储所有城市节点坐标数据
     * @throws IOException
     */
    public void init(String filename) throws FileNotFoundException, IOException {
        // 读取数据
        int[] x = new int[cityNum];
        int[] y = new int[cityNum];
        String strbuff;
        BufferedReader data = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        distance = new int[cityNum][cityNum];
        for (int i = 0; i < cityNum; i++) {
            strbuff = data.readLine();
            String[] strcol = strbuff.split(" ");
            x[i] = Integer.parseInt(strcol[1]);
            y[i] = Integer.parseInt(strcol[2]);
        }
        // 计算距离矩阵
        // 针对具体问题，距离计算方法也不一样，此处用的是att48作为案例，它有48个城市，距离计算方法为伪欧氏距离，最优值为10628
        for (int i = 0; i < cityNum - 1; i++) {
            distance[i][i] = 0; // 对角线为0
            for (int j = i + 1; j < cityNum; j++) {
                double rij = Math
                        .sqrt(((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j])
                                * (y[i] - y[j])) / 10.0);
                int tij = (int) Math.round(rij);
                if (tij < rij) {
                    distance[i][j] = tij + 1;
                    distance[j][i] = distance[i][j];
                } else {
                    distance[i][j] = tij;
                    distance[j][i] = distance[i][j];
                }
            }
        }
        distance[cityNum - 1][cityNum - 1] = 0;

        // 初始化信息素矩阵
        pheromone = new double[cityNum][cityNum];
        for (int i = 0; i < cityNum; i++) {
            for (int j = 0; j < cityNum; j++) {
                pheromone[i][j] = 0.1; // 初始化为0.1
            }
        }
        bestLength = Integer.MAX_VALUE;
        bestTour = new int[cityNum + 1];
        // 随机放置蚂蚁
        for (int i = 0; i < antNum; i++) {
            ants[i] = new Ant(cityNum);
            ants[i].init(distance, alpha, beta);
        }
    }

    public void solve() {
        for (int g = 0; g < MAX_GEN; g++) {
            // 每一只蚂蚁移动的过程
            for (int i = 0; i < antNum; i++) {
                for (int j = 1; j < cityNum; j++) {
                    ants[i].selectNextCity(pheromone);
                }
                // 蚂蚁回到起始位置FirstCity
                ants[i].getTabu().add(ants[i].getFirstCity());
                // 计算蚂蚁路径的长度
                if (ants[i].getTourLength() < bestLength) {
                    bestLength = ants[i].getTourLength();
                    System.out.println("第" + g + "次迭代，发现新的解" + bestLength);
                    for (int k = 0; k < cityNum + 1; k++) {
                        bestTour[k] = ants[i].getTabu().get(k);
                        System.out.print(bestTour[k] + " ");
                    }
                }
                for (int j = 0; j < cityNum; j++) {
                    ants[i].getDelta()[ants[i].getTabu().get(j)][ants[i]
                            .getTabu().get(j + 1)] = 1.0 / ants[i]
                            .getTourLength();
                    ants[i].getDelta()[ants[i].getTabu().get(j + 1)][ants[i]
                            .getTabu().get(j)] = 1.0 / ants[i]
                            .getTourLength();
                }
            }

            // 更新信息素
            updatePheromone();

            // 重新初始化蚂蚁
            for (int i = 0; i < antNum; i++) {
                ants[i].init(distance, alpha, beta);
            }
        }
        System.out.println("\n迭代完毕。");
        // 打印最佳结果
        printOptimal();
    }

    // 更新信息素
    private void updatePheromone() {
        // 信息素挥发
        for (int i = 0; i < cityNum; i++)
            for (int j = 0; j < cityNum; j++)
                pheromone[i][j] = pheromone[i][j] * (1 - rho);
        // 信息素更新
        for (int i = 0; i < cityNum; i++) {
            for (int j = 0; j < cityNum; j++) {
                for (int k = 0; k < antNum; k++) {
                    pheromone[i][j] += ants[k].getDelta()[i][j];
                }
            }
        }
    }

    private void printOptimal()
    {
        System.out.println("The optimal length is: " + bestLength);
        System.out.println("The optimal tour is: ");
        for (int i = 0; i < cityNum + 1; i++)
        {
            System.out.print(bestTour[i] + " ");
        }
        System.out.println();
    }


}

