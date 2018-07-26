package linux;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jiangfw on 2017/3/27.
 */
public class TestRex {

    public static void main(String[] args) {
        //针对字符串处理
        TestRex reg = new TestRex();
        //校验qq的reg正则表达式
        //这里的\w 是指的是[a-zA-Z0-9],还有一个重要的是?,*.+这三个分别
        //?表示出现1次或者1次都没有，
        //+表示出现1次或者n次，
        //*表示出现0次或者n次，
        //还有些特殊的写法X{n}恰好n次X{n,}至少n次，X{n,m}n次到m次，
        String mathReg = "[1-9]\\d{4,19}";
        String divisionReg = "(.)\\1+";
        //\\b 是指的边界值
        String getStringReg = "\\b\\w{3}\\b";
        //字符串匹配(首位是除0 的字符串)
        reg.getMatch("739295732",mathReg);
        reg.getMatch("039295732",mathReg);
        //字符串的替换
        //去除叠词
        reg.getReplace("12111123ASDASDAAADDD",divisionReg,"$1");
        //字符串的分割
        //切割叠词,重复的
        //这里要知道一个组的概念(.)\\1第二个和第一个至相同
        reg.getDivision("aadddddasdasdasaaaaaassssfq",divisionReg);
        //字符串的获取
        //现在获取三个字符串取出
        reg.getString("ming tian jiu yao fangjia le ",getStringReg);

        System.out.println("************************************************");
        Pattern p = Pattern.compile("(med)|(\\d*.xml$)|(fibrillation)|(recovery)",Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher("1489975287-0-1489975286742_main.MED");
        while(m.find()){
            System.out.println(m.groupCount());
            System.out.println("Group 0:"+m.group(0));//得到第0组——整个匹配
            System.out.println("Group 1:"+m.group(1));//得到第一组匹配——与(or)匹配的
            System.out.println("Group 2:"+m.group(2));//得到第二组匹配——与(ld!)匹配的，组也就是子表达式
        }


        System.out.println("******************************************************");

        // TODO Auto-generated method stub
        String str = "Hello,World! in Java.";
        Pattern pattern = Pattern.compile("W(or)(ld!)");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            System.out.println("Group 0:"+matcher.group(0));//得到第0组——整个匹配
            System.out.println("Group 1:"+matcher.group(1));//得到第一组匹配——与(or)匹配的
            System.out.println("Group 2:"+matcher.group(2));//得到第二组匹配——与(ld!)匹配的，组也就是子表达式
            System.out.println("Start 0:"+matcher.start(0)+" End 0:"+matcher.end(0));//总匹配的索引
            System.out.println("Start 1:"+matcher.start(1)+" End 1:"+matcher.end(1));//第一组匹配的索引
            System.out.println("Start 2:"+matcher.start(2)+" End 2:"+matcher.end(2));//第二组匹配的索引
            System.out.println(str.substring(matcher.start(0),matcher.end(1)));//从总匹配开始索引到第1组匹配的结束索引之间子串——Wor
        }
    }
    /**
     * 获取查询的字符串
     * 将匹配的字符串取出
     */
    private void getString(String str, String regx) {
        //1.将正在表达式封装成对象Patten 类来实现
        Pattern pattern = Pattern.compile(regx);
        //2.将字符串和正则表达式相关联
        Matcher matcher = pattern.matcher(str);
        //3.String 对象中的matches 方法就是通过这个Matcher和pattern来实现的。

        System.out.println(matcher.matches());
        //查找符合规则的子串
        while(matcher.find()){
            //获取 字符串
            System.out.println(matcher.group());
            //获取的字符串的首位置和末位置
            System.out.println(matcher.start()+"--"+matcher.end());
        }
    }
    /**
     * 字符串的分割
     */
    private void getDivision(String str, String regx) {
        String [] dataStr = str.split(regx);
        for(String s:dataStr){
            System.out.println("正则表达式分割++"+s);
        }
    }
    /**
     * 字符串的替换
     */
    private void getReplace(String str, String regx,String replaceStr) {
        String stri = str.replaceAll(regx,replaceStr) ;
        System.out.println("正则表达式替换"+stri);
    }
    /**
     * 字符串处理之匹配
     * String类中的match 方法
     */
    public void getMatch(String str, String regx){
        System.out.println("正则表达匹配"+str.matches(regx));
    }
}
