package com.jiangfw.common.lang.test;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.text.StrLookup;
import org.junit.Assert;

public class StrLookupTest {

    /**
     * <p>
     * Description:
     * </p>
     *
     * @author jiangfengwei
     */
    public static void main(String[] args) {
        testMapLookup();
    }

    public static void testMapLookup() {
        Map<String, java.io.Serializable> map = new HashMap<>();
        map.put("key", "value");
        map.put("number", 2);
        Assert.assertEquals("value", StrLookup.mapLookup(map).lookup("key"));
        Assert.assertEquals("2", StrLookup.mapLookup(map).lookup("number"));
        Assert.assertEquals(null, StrLookup.mapLookup(map).lookup(null));
        Assert.assertEquals(null, StrLookup.mapLookup(map).lookup(""));
        Assert.assertEquals(null, StrLookup.mapLookup(map).lookup("other"));
    }
}
