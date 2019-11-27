package com.jiangfw.common.lang.test.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 *
 * @author jiangfengwei
 * @since 2013-9-16
 */
public class Process {

    /**
     * 进程名
     */
    private String name;
    /**
     * 最大需求的资源
     */
    private Map<Resource, Integer> max;
    /**
     * 还需要的资源数=max-allocation
     */
    private Map<Resource, Integer> need;
    /**
     * 已分配给该进程的资源数
     */
    private Map<Resource, Integer> allocation;
    /**
     * 系统是否有足够的资源分配给进程,初始值为设置成false
     */
    private boolean finish = false;

    /**
     * Constructor
     */
    public Process(String name, Map<Resource, Integer> max) {
        this.name = name;
        this.max = max;
        //    this.setNeed();
    }

    /**
     * @return
     */
    public List<Map<Resource, Integer>> getNeed() {
        return null;
    }

    /**
     * need=max-allocation
     */
    private void setNeed() {
        this.need = new HashMap<>();
        Set<Resource> allResSet = max.keySet();
        Object[] all = allResSet.toArray();
        for (int i = 0; i < all.length; i++) {
            int r = max.get(all[i]);
            need.put((Resource) all[i], r - allocation.get(all[i]));
        }
    }

    /**
     * 向系统请求资源
     */
    public boolean request(Map<Resource, Integer> available) {
        Set<Resource> allResSet = available.keySet();
        Object[] all = allResSet.toArray();
        for (int i = 0; i < all.length; i++) {
            int r = available.get((Resource) all[i]);
            /** 如果当前可分配的资源数矩阵元素出现小于需求量，则分配不成功 */
            if (need.get((Resource) all[i]) > r) {
                System.out.println((Resource) all[i] + "分配不成功");
                return false;
            }
        }
        System.out.println(this.getName() + "分配资源成功，已开始执行，几个时钟周期后将执行完");
        this.clear(available);
        return true;
    }

    /**
     * 进程执行完后，释放占用的资源<br>
     * （即系统中可利用资源数目会相应增加）<br>
     * Set中的toArray()方法不能立即强制类型转化不能:(Resource)all.toArray()
     */
    private void clear(Map<Resource, Integer> available) {
        Set<Resource> all = this.max.keySet();
        Object[] allResSet = all.toArray();
        for (int i = 0; i < allResSet.length; i++) {
            int sum = max.get((Resource) allResSet[i]);

      /*在这强制转化一下
      String是一种类型， String[]是另一种类型，这是不同的概念。
      Object可以强转为String（只要可以）不代表Object[]类型可以强转为String[]类型。*/
            available.put((Resource) allResSet[i], available.get((Resource) allResSet[i]) + sum);
        }
        this.max = null;
        System.out.println(this.getName() + "进程占用的资源释放了");
    }

    public Map<Resource, Integer> getAllocation() {
        return allocation;
    }

    public void setAllocation(Map<Resource, Integer> allocation) {
        this.allocation = new HashMap<Resource, Integer>();
        Set<Resource> resSet = allocation.keySet();
        Object[] resArray = resSet.toArray();
        for (int i = 0; i < resArray.length; i++) {
            int sum = allocation.get((Resource) resArray[i]);
            this.allocation.put((Resource) resArray[i], sum);
        }
        setNeed();
    }

    public String getName() {
        return name;
    }

    public Map<Resource, Integer> getMax() {
        return max;
    }
}
