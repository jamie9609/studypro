package com.algorithm.tsp.antClonyOptimization;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @PackageName: com.algorithm.tsp.antClonyOptimization
 * @ClassName: TSP
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/13 8:32 下午
 */
public class TSP {

    public static void main(String[] args) throws IOException {
        ACO aco = new ACO(48, 100, 1000, 1.0f, 5.0f, 0.5f);
        try {
            aco.init("/Users/didi/IdeaProjects/studypro/src/main/java/com/algorithm/tsp/antClonyOptimization/att48.tsp");
            aco.solve();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
