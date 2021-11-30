package com.jamie.javastudy.mapReduce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 *
 * 统计美国，每个州state累计确诊案例数、累计死亡案例数。
 * txt 字段含义如下：date（日期）,county（县）,state（州）,fips（县编码code）,cases（累计确诊病例）,deaths（累计死亡病例）。
 *
 * @PackageName: com.jamie.leetcode.mapReduce
 * @ClassName: CovidCountBean
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/30 7:14 下午
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CovidCountBean implements Writable {
    //确诊病例数
    private long cases;
    //死亡病例数
    private long deaths;

    public void set(long cases, long deaths) {
        this.cases = cases;
        this.deaths = deaths;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(cases);
        dataOutput.writeLong(deaths);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.cases = dataInput.readLong();
        this.deaths =dataInput.readLong();
    }

    @Override
    public String toString() {
        return  cases +"\t"+ deaths;
    }
}
