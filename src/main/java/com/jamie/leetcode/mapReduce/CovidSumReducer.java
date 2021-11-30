package com.jamie.leetcode.mapReduce;

import org.apache.hadoop.mapreduce.Reducer;

import javax.xml.soap.Text;
import java.io.IOException;

/**
 * @PackageName: com.jamie.leetcode.mapReduce
 * @ClassName: CovidSumReducer
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/30 7:28 下午
 */
public class CovidSumReducer extends Reducer<Text, CovidCountBean,Text,CovidCountBean> {

    CovidCountBean outValue = new CovidCountBean();

    @Override
    protected void reduce(Text key, Iterable<CovidCountBean> values, Context context) throws IOException, InterruptedException {
        long totalCases = 0;
        long totalDeaths =0;
        //累加统计
        for (CovidCountBean value : values) {
            totalCases += value.getCases();
            totalDeaths +=value.getDeaths();
        }

        outValue.set(totalCases, totalDeaths);

        context.write(key,outValue);
    }
}
