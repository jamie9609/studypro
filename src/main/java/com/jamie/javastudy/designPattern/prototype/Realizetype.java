package com.jamie.javastudy.designPattern.prototype;

/**
 * @PackageName: com.jamie.javastudy.designPattern.prototype
 * @ClassName: Realizetype
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 11:22 上午
 */
public class Realizetype implements Cloneable{
    public Realizetype() {
        System.out.println("具体原型创建成功！");
    }

    public Realizetype clone () throws CloneNotSupportedException {
        System.out.println("原型复制成功！");
        return (Realizetype)super.clone();
    }

}
