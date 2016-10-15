/**
 * Created by paul on 16/9/27.
 */
public class Apple implements IFruit {
    private final static String TAG = Banana.class.getSimpleName();
    private static String mName = Apple.class.getSimpleName();

    @Override
    public void printName() {
        System.out.print(mName);
    }


    public void testBreak() {
        for (int i = 0; i < 3; i++) {
            try {
                System.out.println(i);
                if (i == 1) {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("执行了finally语句");
            }
        }
    }

    // 测试continue语句
    public void testContinue() {
        for (int i = 0; i < 3; i++) {
            try {
                System.out.println(i);
                if (i == 1) {
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("执行了finally语句");
            }
        }
    }
}
