package com.jiangfw.common.lang.test.json;

import com.jiangfw.common.lang.test.json.entity.Address;
import com.jiangfw.common.lang.test.json.entity.CycleObject;
import com.jiangfw.common.lang.test.json.entity.Person;
import com.jiangfw.common.lang.test.json.entity.TestBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonBeanProcessor;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.JSONUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Hello 此代码源于开源项目E3,原作者：黄云辉
 *
 * @author XiongChun
 * @since 2010-03-17
 */
public class JsonTest {

    public static void main(String[] args) {

        JSONObject json = JSONObject.fromObject("{\"reason\":\"解绑成功！\",\"result\":true}");

        String result = json.getString("result");
        boolean r = json.getBoolean("result");
        System.out.println(r);
        System.out.println(StringUtils.equalsIgnoreCase(result, "true"));

//		jsonPrimaryMethod();
//		 jsonBeanProcessor();
        // jsonValueProcessor();
        // jsonPropertityNameProcessor();
        // testJson1();
        String aa = null;
        try {
            JSONObject j = JSONObject.fromObject(aa);
            System.out.println(j.isNullObject());
            System.out.println(j);
        } catch (JSONException e) {
            // TODO: handle exception
            System.out.println("11");

        }
    }

    /**
     * <p>
     * Description:基本方法
     * </p>
     *
     * @author jiangfengwei
     */
    private static void jsonPrimaryMethod() {
        JSONObject json = JSONObject.fromObject(false);
        json.accumulate("acc", true);// 不断添加json值到array
        json.accumulate("acc", true);
        json.accumulate("acc", "jiangfw");
        json.element("element", true);// 覆盖上一个值
        json.element("element", "jfw");
        System.out.println(json.put("put", "put1"));// 返回上一个值，并且放入本值
        System.out.println(json.put("put", "put2"));
        System.out.println(json.put("put", "put3"));
        System.out.println(json.optString("acc"));
        json.element("element", JSONObject.fromObject(new Person()));
        System.out.println(json);
    }

    /**
     * <p>
     * Description:JsonBeanProcessor 要转换的object对象的处理器
     * </p>
     *
     * @author jiangfengwei
     */
    private static void jsonBeanProcessor() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonBeanProcessor(Person.class,// Object.class
                // 不能进来，必须完全匹配类型
                new JsonBeanProcessor() {

                    public JSONObject processBean(Object bean,
                            JsonConfig jsonConfig) {
                        if (!(bean instanceof Person)) {
                            return JSONObject.fromObject(bean);
                        }
                        Person person = (Person) bean;
                        return new JSONObject().element("name1",
                                person.getName()).element("lastname1",
                                person.getLastname());
                    }
                });

        Person bean = new Person();
        bean.setName("jack");
        bean.setLastname("mich");
        bean.setAddress(new Address());
        JSONObject jsonObj = JSONObject.fromObject(bean, jsonConfig);
        JSON json = JSONSerializer.toJSON(bean, jsonConfig);// bean的处理器
        System.out.println(json);
        System.out.println(jsonObj);
        System.out.println("********************");
        List<Person> list = new ArrayList<>();
        list.add(bean);
        // jsonarray 调用config时和jsonobject一致
        JSONArray arr = JSONArray.fromObject(list, jsonConfig);
        System.out.println(arr);
    }

    /**
     * <p>
     * Description:要转化为json对象的内部类型的处理器
     * </p>
     *
     * @author jiangfengwei
     */
    private static void jsonValueProcessor() {
        // 注册date类型的转化方式
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class,
                new JsonValueProcessorImplTest());
        TestBean test = new TestBean("jiangfw", "jiangfengwei", new Date(
                2013 - 1900, 6 - 1, 1));
        JSONObject jsonFromBean = JSONObject.fromObject(test, jsonConfig);
        System.out.println(jsonFromBean);

        // prints {"birthday":"2009-10-26","id":"id","name":"name"}

        String[] dateFormats = new String[]{"yyyy/MM/dd", "yyyy-MM-dd"};
        JSONUtils.getMorpherRegistry().registerMorpher(
                new DateMorpher(dateFormats));// 注册变形器，将jsonObject对象中的字符串按对应format变成date类型
        TestBean jsonToBean = (TestBean) JSONObject.toBean(jsonFromBean,
                TestBean.class);
        // TestBean jsonToBean2 = (TestBean) JSONObject.toBean(jsonFromBean,
        // jsonConfig);
        System.out.println(jsonToBean);
        // prints TestBean@1126b07[id=id,name=name,birthday=Mon Oct 26 00:00:00
        // CST 2009]
    }

    /**
     * <p>
     * Description:修改key的值
     * </p>
     *
     * @author jiangfengwei
     */
    private static void jsonPropertityNameProcessor() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class,
                new JsonValueProcessorImplTest());
        // 修改JavaBean的property对象的显示名称
        jsonConfig.registerJsonPropertyNameProcessor(TestBean.class,
                new JsonNameProcessor());
        // jsonConfig.registerJavaPropertyNameProcessor(Date.class, new
        // JsonNameProcessor());
        TestBean test = new TestBean("jiangfw", "jiangfengwei", new Date(
                2013 - 1900, 9 - 1, 1));
        JSONObject jsonFromBean = JSONObject.fromObject(test, jsonConfig);
        System.out.println(jsonFromBean);
    }

    /**
     * 这里测试如果含有自包含的时候需要CycleDetectionStrategy
     */
    public static void testCycleObject() {
        CycleObject object = new CycleObject();
        object.setMemberId("yajuntest");
        object.setSex("male");
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

        JSONObject json = JSONObject.fromObject(object, jsonConfig);
        System.out.println(json);
    }

    public static void testJson1() {
        JSONArray array = JSONArray
                .fromObject("[{\"filterEnd\":\"\",\"filterStart\":\"\",\"fields\":\"\"}]");
        System.out.println(array);
        JSONObject obj = JSONObject.fromObject(array.get(0));
        System.out.println(obj.get("fields"));
    }
}
