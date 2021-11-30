package com.jamie.leetcode.mapReduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * mapreduce 学习参考：https://cloud.tencent.com/developer/article/1886450
 * @PackageName: com.jamie.leetcode.mapReduce
 * @ClassName: CovidSumDriver
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/30 7:30 下午
 */
public class CovidSumDriver {
    public static void main(String[] args) throws Exception{
        //配置文件对象
        Configuration conf = new Configuration();
        // 创建作业实例
        Job job = Job.getInstance(conf, CovidSumDriver.class.getSimpleName());
        // 设置作业驱动类
        job.setJarByClass(CovidSumDriver.class);

        // 设置作业mapper reducer类
        job.setMapperClass(CovidSumMapper.class);
        job.setReducerClass(CovidSumReducer.class);

        // 设置作业mapper阶段输出key value数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(CovidCountBean.class);
        //设置作业reducer阶段输出key value数据类型 也就是程序最终输出数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(CovidCountBean.class);

        // 配置作业的输入数据路径
        FileInputFormat.setInputPaths(job, new Path("src/main/java/com/jamie/leetcode/mapReduce/case1"));
        // 配置作业的输出数据路径
        FileOutputFormat.setOutputPath(job, new Path("src/main/java/com/jamie/leetcode/mapReduce/case2"));


        //判断输出路径是否存在 如果存在删除
        Path outputPath = new Path("src/main/java/com/jamie/leetcode/mapReduce/case2");
        outputPath.getFileSystem(conf).delete(outputPath,true);

        // 提交作业并等待执行完成
        boolean resultFlag = job.waitForCompletion(true);
        //程序退出
        System.exit(resultFlag ? 0 :1);
    }
}
