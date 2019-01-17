package com.jiangfw.common.lang.test.process;

public class Resource {

    /**
     * 资源的名称
     */
    public String name;

    public Resource(String name) {
        this.name = name;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Resource [name=" + name + "]";
    }

}
