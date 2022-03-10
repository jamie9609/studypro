package com.jamie.javastudy.designPattern.flyWeight2;

import java.util.HashMap;

/**
 * @PackageName: com.jamie.javastudy.designPattern.flyWeight2
 * @ClassName: WebsiteFactory
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/10 10:29 上午
 */
public class WebsiteFactory {
    private HashMap<String, ConcreteWebsite> pool = new HashMap<>();

    public Website getWebsite(String name) {
        if (!pool.containsKey(name)) {
            pool.put(name, new ConcreteWebsite(name));
        }
        return pool.get(name);
    }

    public int getWebsiteCount() {
        return pool.size();
    }
}
