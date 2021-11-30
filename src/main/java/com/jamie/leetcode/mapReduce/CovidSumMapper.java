package com.jamie.leetcode.mapReduce;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

/**
 * @PackageName: com.jamie.leetcode.mapReduce
 * @ClassName: WCMapper
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/30 6:48 下午
 */
public class CovidSumMapper extends Mapper<LongWritable, Text, Text, CovidCountBean> {

    Text outKey = new Text();
    CovidCountBean outValue = new CovidCountBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        //州
        outKey.set(fields[2]);
        //Covid数据 确诊病例 死亡病例
        outValue.set(Long.parseLong(fields[fields.length-2]), Long.parseLong(fields[fields.length-1]));
        //map输出结果
        context.write(outKey, outValue);
    }
}
