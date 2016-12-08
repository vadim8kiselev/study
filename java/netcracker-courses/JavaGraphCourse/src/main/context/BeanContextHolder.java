package main.context;

import main.graph.util.impl.GraphUtilsImpl;

public enum BeanContextHolder {

    GraphUtils(main.graph.util.GraphUtils.class, GraphUtilsImpl.getInstance());

    private Class clazz;
    private Bean bean;

    BeanContextHolder(Class clazz, Bean bean) {
        this.clazz = clazz;
        this.bean = bean;
    }

    public static <T extends Beanable> T getInstance(Class<T> clazz) {
        for (BeanContextHolder instance : BeanContextHolder.values()) {
            if (instance.clazz.equals(clazz)) {
                return (T) instance.bean;
            }
        }
        throw new NoClassDefFoundError("Cannot find bean with name " + clazz.getName());
    }
}
