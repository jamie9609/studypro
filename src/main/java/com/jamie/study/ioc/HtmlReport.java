package com.jamie.study.ioc;

/**
 * @PackageName: com.jamie.study.ioc
 * @ClassName: HtmlReport
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/15 8:50 上午
 */
public class HtmlReport implements ReportGenerator{
    @Override
    public void generator(String[][] table) {
        System.out.println("This is Html report!");
    }
}
