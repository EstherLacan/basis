package com.jiangfw.function;

/**
 * Created by EstherLacan on 2019/1/25.
 */
@FunctionalInterface
public interface JfwFunction<I, O> {

    O apply(I i);

    boolean equals(Object obj);

    /**
     * V->I --> I->O =====> V->O .
     *
     * @param before 先执行的方法
     */
    default <V> JfwFunction<V, O> andThen(JfwFunction<? super V, ? extends I> before) {
        return (V v) -> apply(before.apply(v));
    }

    /**
     * I->O --> O->V =====> I->V .
     *
     * @param after 后执行的方法
     */
    default <V> JfwFunction<I, V> compress(JfwFunction<? super O, ? extends V> after) {
        return (I i) -> after.apply(apply(i));
    }

}
