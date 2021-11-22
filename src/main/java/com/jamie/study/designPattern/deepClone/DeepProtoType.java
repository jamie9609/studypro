package com.jamie.study.designPattern.deepClone;

import java.io.*;

/**
 * @PackageName: com.jamie.study.designPattern.deepClone
 * @ClassName: DeepProtoType
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 1:59 下午
 */
public class DeepProtoType implements Serializable, Cloneable {
    public static final long serialVersionUID = 1L;

    public DeepCloneableTarget deepCloneableTarget;

    // 深拷贝的第一种方式，重写clone方法；
    @Override
    public Object clone() throws CloneNotSupportedException {
        Object deep = null;
        deep = super.clone();

        // 对于引用类型的属性，做单独处理；
        DeepProtoType deepProtoType = (DeepProtoType) deep;

        deepProtoType.deepCloneableTarget = (DeepCloneableTarget)deepCloneableTarget.clone();

        return deep;
    }

    // 深拷贝的第二种方式：序列化
    public Object deepClone(){
        //创建流对象
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;

        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try{
            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            //把当前对象已对象流的方式输出;
            oos.writeObject(this);

            // 反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            DeepProtoType copyObj =(DeepProtoType)ois.readObject();
            return copyObj;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        } finally {
            try {
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (Exception e2) {
                System.out.println(e2.toString());
            }
        }

    }
}
