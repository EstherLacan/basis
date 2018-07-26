package com.jiangfw.common.lang.test;

import com.jiangfw.common.lang.test.bean.AppraisalDayVo;
import com.jiangfw.common.lang.test.bean.AppraisalMonthVo;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Hello
 * 此代码源于开源项目E3,原作者：黄云辉
 * 
 * @author XiongChun
 * @since 2010-03-17
 */
public class Hello {
	private static final Log logger = LogFactory.getLog(Hello.class);

	public static void main(String[] args) throws ClassNotFoundException, IOException, CloneNotSupportedException {
		String oid="0000000000000100000000010000000001000000000100000000010000000000";
		int level =2;
		int base=4;
		int step=10;
		int splitStartIndex=base+(level-1)*step;
		int splitEndIndex=base+level*step;
		String parentPart=StringUtils.substring(oid, 0, splitStartIndex);//父级机构的id有效部分
		String levelPart=StringUtils.substring(oid, splitStartIndex, splitEndIndex);//当前level下的有效部分
		
		
		
		
		System.out.println(new String[]{parentPart,levelPart});
//		System.out.println(ids.toString());
		System.out.println(levelPart+"....."+parentPart);
		
		Prototype pro=new Prototype();
		pro.setString("jiangfe");
		pro.setObj(new SerializableObject());
		System.out.println(pro+","+pro.getString()+","+pro.getObj());
		
		Prototype pro2=(Prototype) pro.clone();
		System.out.println(pro2+","+pro2.getString()+","+pro2.getObj());
		
		
		Date d=new Date(1397038256242L);
		System.out.println(d);
//		Object springBean = SpringBeanLoader.getSpringBean("workTimeService");
//		WorkingTimeService workTimeService = (WorkingTimeService)springBean;
//		JSONArray resultArray = workTimeService.getPoliceWorkingTimeByMonth2("330681520000",new Date(),1);
		List<AppraisalMonthVo> resultList=new ArrayList<AppraisalMonthVo>();
		
		AppraisalMonthVo v1=new AppraisalMonthVo();
		v1.setUserId(1);
		AppraisalMonthVo v2=new AppraisalMonthVo();
		v2.setUserId(1);
		resultList.add(v1);
		resultList.add(v2);
		AppraisalDayVo appraisalVo=new AppraisalDayVo();
		appraisalVo.setUserId(1);
		//System.out.println(resultList.contains(appraisalVo));
		List<AppraisalDayVo> li=new ArrayList<AppraisalDayVo>();
		li.add(appraisalVo);
		v1.setAppList(JSONArray.fromObject(li));
		JSONArray a=JSONArray.fromObject(resultList);
		try {
		Map map=	BeanUtils.describe(resultList);
		Map map1=	BeanUtils.describe(v1);
		System.out.println(map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		// //构造生成器
		// DefaultIDGenerator generator = new DefaultIDGenerator();
		//	
		// //前缀生成器，用于产生ID前缀
		// DefaultPrefixGenerator prefixGenerator = new
		// DefaultPrefixGenerator();
		// prefixGenerator.setWithDate(false);
		// generator.setPrefixGenerator(prefixGenerator);
		// prefixGenerator.setPrefix("E3_");
		//	
		// //序号生成器
		// DefaultSequenceGenerator sequenceGenerator =
		// TimeRollingSequenceGenerator.getYearRollingSequenceGenerator();
		// sequenceGenerator.setMinValue(0);
		// sequenceGenerator.setMaxValue(9999999);
		// sequenceGenerator.setCycle(true);
		// sequenceGenerator.setCache(100);
		// generator.setSequenceGenerator(sequenceGenerator);
		//	
		// //格式化序号
		// DefaultSequenceFormater sequenceFormater = new
		// DefaultSequenceFormater();
		// sequenceFormater.setPattern("0000000");
		// generator.setSequenceFormater(sequenceFormater);
		// System.out.println(generator.create());

//		DefaultIDGenerator generator = new DefaultIDGenerator();
//		DefaultSequenceGenerator sequenceGenerator = TimeRollingSequenceGenerator
//				.getDayRollingSequenceGenerator();
//		sequenceGenerator.setMinValue(1000000);
//		sequenceGenerator.setMaxValue(9999999);
//		generator.setSequenceGenerator(sequenceGenerator);
//		System.out.println(generator.create());
//		logger.debug("dddddddddddd");

		// net.jcreate.e3.commons.id.sequence.DefaultSequenceGenerator
		// sequenceGenerator =
		// new net.jcreate.e3.commons.id.sequence.DefaultSequenceGenerator();
		// sequenceGenerator.setMinValue(1000);
		// sequenceGenerator.setMaxValue(9999);
		// generator.setSequenceGenerator(sequenceGenerator);
		// for(int i = 0; i<100; i++ ){
		// System.out.println(generator.create());
		// }

		// while( true ){
		// System.out.println(sequenceGenerator.next());
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
	}

}
