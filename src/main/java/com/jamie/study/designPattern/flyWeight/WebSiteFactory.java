package com.jamie.study.designPattern.flyWeight;

import java.util.HashMap;

/**
 * @PackageName: com.jamie.study.designPattern.flyWeight
 * @ClassName: WebSiteFactory
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 11:29 上午
 */
public class WebSiteFactory {

    /**
     * 集合，充当池的作用
     */
    private HashMap<String, ConcreteWebSite> pool = new HashMap<>();

    /**
     * 根据网站类型，返回一个网站，如果没有就创建网站，放到池子里
     * @param type
     * @return
     */
    public WebSite getWebSiteCategory(String type) {
        if (!pool.containsKey(type)) {
            pool.put(type, new ConcreteWebSite(type));
        }
        return pool.get(type);
    }

    public int getWebSiteCount() {
        return pool.size();
    }

}
