package linux;

/**
 * @author xdwang
 * @ceate 2012-7-20 下午22:22:44
 * @email xdwangiflytek@gmail.com
 * @description process工具类
 */
public class ProcessUtil {
    /**
     * @param cmdStr 命令字符串
     * @descrption 执行外部exe公用方法
     * @author xdwang
     * @create 2012-7-20下午22:24:32
     */
    public static void execProcess(String cmdStr) {
        Process process = null;
        try {
            System.out.println(cmdStr);
            process = Runtime.getRuntime().exec(cmdStr);
            new ProcessClearStream(process.getInputStream(), "INFO").start();
            new ProcessClearStream(process.getErrorStream(), "ERROR").start();
            int status = process.waitFor();
            System.out.println("Process exitValue:" + status);
        } catch (Exception e) {
            System.out.println("执行" + cmdStr + "出现错误，" + e.toString());
        } finally {
            if (process != null) {
                process.destroy();
            }
            process = null;
        }
    }

    public static void main(String[] args) {
        execProcess("python D:\\Script\\python\\primary\\for.py");
    }
}
