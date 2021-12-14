package com.algorithm.tsp.jsprit;
import com.graphhopper.jsprit.analysis.toolbox.GraphStreamViewer;
import com.graphhopper.jsprit.analysis.toolbox.Plotter;
import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl.Builder;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleType;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.reporting.SolutionPrinter;
import com.graphhopper.jsprit.core.util.Solutions;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * 车辆路径优化问题求解工具Jsprit的简单介绍与入门
 * 参考：https://my.oschina.net/u/4131402/blog/4426657
 * 参考：https://cloud.tencent.com/developer/article/1507860
 * @PackageName: com.algorithm.tsp.jsprit
 * @ClassName: JspritDemo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/14 3:00 下午
 */
public class JspritDemo {
    /**
     * 记录客户点信息
     */
    static int[][] customerNode ;
    /**
     * 车辆容量
     */
    static int capacity = 0;
    /**
     * 仓库横坐标
     */
    static int xdepot = 0;
    /**
     * 仓库纵坐标
     */
    static int ydepot = 0;
    /**
     * 用户数
     */
    static int numberOfCustomer = 0;
    /**
     * 对应容量值的索引
     */
    final static int WEIGHT_INDEX = 0;

    public static void main(String[] args) {
        //创建输出文件
        File dir = new File("src/main/java/com/algorithm/tsp/jsprit/output");
        if (!dir.exists()) {
            System.out.println("creating directory ./output");
            boolean result = dir.mkdir();
            if (result) System.out.println("./output created");
        }
        String path = "src/main/java/com/algorithm/tsp/jsprit/examples.txt";
        readData(path);
        buildProblem();
    }

    /**
     * 读入数据
     * @param path
     */
    public static void readData(String path) {
        try {
            Scanner cin = new Scanner(new BufferedReader(new FileReader(path)));
            numberOfCustomer = cin.nextInt();
            capacity = cin.nextInt();
            xdepot = cin.nextInt();
            ydepot = cin.nextInt();

            customerNode = new int[numberOfCustomer][3];
            for(int i = 0; i < numberOfCustomer; i++) {
                for(int j = 0; j<3 ; j++) {
                    customerNode[i][j] = cin.nextInt();
                }
            }
            cin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 求解
     */
    public static void buildProblem() {
        //建立一个求解器类型实例，为vehicleType。设置容量
        VehicleTypeImpl.Builder vehicleTypeBuilder = VehicleTypeImpl.Builder.newInstance("vehicleType").addCapacityDimension(WEIGHT_INDEX, capacity);
        VehicleType vehicleType = vehicleTypeBuilder.build();

        //建立一个求解器实例，名称为vechicle，坐标为读入的坐标，设定求解器的类型
        Builder vehicleBuilder = VehicleImpl.Builder.newInstance("vehicle");
        vehicleBuilder.setStartLocation(Location.newInstance(xdepot, ydepot));
        vehicleBuilder.setType(vehicleType);
        VehicleImpl vehicle = vehicleBuilder.build();

        //声明服务点的集合
        Collection<Service> serviceNode = new ArrayList<>();

        //读入各服务点的数据
        for(int i = 0; i < numberOfCustomer;i++) {
            Service serviceNodeTemp = Service.Builder.newInstance(""+i).addSizeDimension(WEIGHT_INDEX, customerNode[i][2]).setLocation(Location.newInstance(customerNode[i][0], customerNode[i][1])).build();
            serviceNode.add(serviceNodeTemp);
        }

        //实例化一个VRP的builder，并将中心点和服务点加入后实例化。
        VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
        vrpBuilder.addVehicle(vehicle);
        vrpBuilder.addAllJobs(serviceNode);
        VehicleRoutingProblem problem = vrpBuilder.build();

        //为问题获取算法
        VehicleRoutingAlgorithm algorithm = Jsprit.createAlgorithm(problem);

        //记录解的集合记录
        Collection<VehicleRoutingProblemSolution> solutions = algorithm.searchSolutions();

        //寻找最优解
        VehicleRoutingProblemSolution bestSolution = Solutions.bestOf(solutions);

        //现实求解的结果详情
        SolutionPrinter.print(problem, bestSolution, SolutionPrinter.Print.VERBOSE);

        //将求解的结果进行可视化
        new Plotter(problem, bestSolution).plot("src/main/java/com/algorithm/tsp/jsprit/output/plot.png","Jsprit_example");
        new GraphStreamViewer(problem, bestSolution).labelWith(GraphStreamViewer.Label.ID).setRenderDelay(200).display();
    }

}
