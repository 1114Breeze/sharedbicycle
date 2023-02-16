import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author test
 * @classname ${NAME}
 * @date $DATE ${TIME}
 */
public class Main {
    public static void main(String[] args) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取系统当前时间
        long now = System.currentTimeMillis();
        System.out.println(now);
    }
}