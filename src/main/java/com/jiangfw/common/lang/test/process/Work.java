package com.jiangfw.common.lang.test.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Work是系统中所有进程和所有资源一个状态
 */
public class Work {

    /**
     * 进程队列
     */
    private List<Process> proc;
    /**
     * 资源总和
     */
    private Map<Resource, Integer> res;
    /**
     * 可利用资源总和
     */
    private Map<Resource, Integer> availRes;

    public Work(List<Process> proc, Map<Resource, Integer> res) {
        super();
        this.proc = proc;
        this.res = res;
    }

    public void setPros() {

    }

    /**
     *
     */
    public void doWork() {
        for (int i = 0; i < this.proc.size(); i++) {
            if (proc.get(i).request(this.availRes)) {
                System.out.println(proc.get(i).getName() + "进程已完毕，下个进程正在请求");
            }
        }
    }

    /**
     *
     * @param allocation
     */
    public void initialized(Map<Resource, Integer> allocation) {
        for (int i = 0; i < proc.size(); i++) {
            this.proc.get(i).setAllocation(allocation);
        }
    }

    /**
     *
     * @param availRes
     */
    public void setAvailRes(Map<Resource, Integer> availRes) {
        this.availRes = new HashMap<Resource, Integer>();
        Set<Resource> allSet = availRes.keySet();
        Object[] allRes = allSet.toArray();
        for (int i = 0; i < allRes.length; i++) {
            this.availRes.put((Resource) allRes[i], availRes.get(allRes[i]));
        }
    }
}
