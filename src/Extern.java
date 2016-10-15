/**
 * Created by paul on 16/10/5.
 */
public class Extern {
    static int num = 4;                    // 静态变量第二个
    {
        num += 3;
        System.out.println("b");
    }

    int a = 5;

    {                             // 成员变量第三个
        System.out.println("c");
    }

    static {                                        // 静态块，第一个加载
        System.out.println("a");
    }

    static void run()                     // 静态方法，调用的时候才加载// 注意看，e没有加载
    {
        System.out.println("e");
    }
}
