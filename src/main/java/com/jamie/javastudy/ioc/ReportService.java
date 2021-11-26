package com.jamie.javastudy.ioc;

/**
 * @PackageName: com.jamie.study.ioc
 * @ClassName: ReportService
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/15 8:55 上午
 */
public class ReportService {


    //private ReportGenerator reportGen = new PdfReport();

    //private ReportGenerator reportGen = (ReportGenerator)Container.instance.getComponent("reportGenerator");
    private ReportGenerator reportGen;

    public void setReportGen(ReportGenerator reportGen){
        this.reportGen = reportGen;
    }

    //打印出某一年的报告
    public void printAnnualReport(int year){
        String[][] statistics = null;

        // to do some statistics

        reportGen.generator(statistics);
    }

    //打印出某年中的某个月的报告

    public void printMonthReort(int year,int month){
        String[][] statistics = null;

        //to do some statistics

        reportGen.generator(statistics);
    }
}
