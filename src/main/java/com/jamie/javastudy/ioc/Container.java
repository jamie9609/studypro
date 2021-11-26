package com.jamie.javastudy.ioc;


import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName: com.jamie.study.ioc
 * @ClassName: ReportService
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/15 8:52 上午
 */
public class Container {

    //全局变量，是contatiner的引用
    //public static Container instance;

    //通过IDs来管理各个组件
    private Map<String,Object> components;

    public Container() {
        //instance = this;
        components = new HashMap<String,Object>();
        ReportGenerator reportGen = new PdfReport();//输出Pdf
        components.put("reportGenerator",reportGen);

        //这个时候components中已经有reportGenerator组件了
        ReportService reportSe = new ReportService();
        reportSe.setReportGen(reportGen);//在这里进行了注入！！！其实注入也没什么神秘的嘛！
        components.put("reportService",reportSe);
    }

    public Object getComponent(String it) {
        return components.get(it);
    }
}
