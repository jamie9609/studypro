package com.jamie.javastudy.createObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackageName: com.jamie.javastudy.createObject
 * @ClassName: Apple
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/2/7 3:57 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apple implements Cloneable{
    private String name;

    private String color;

    private Integer heavy;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"name\":");
        sb.append(name);
        sb.append("\n");
        sb.append("\"color\":");
        sb.append(color);
        sb.append("\n");
        sb.append("\"heavy\":");
        sb.append(heavy);
        sb.append("\n");
        return sb.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
