package com.jamie.study.ioc;

/**
 * @PackageName: com.jamie.study.ioc
 * @ClassName: PdfReport
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/15 8:51 上午
 */
public class PdfReport implements ReportGenerator{
    @Override
    public void generator(String[][] table) {
        System.out.println("This is pdf report!");
    }
}
