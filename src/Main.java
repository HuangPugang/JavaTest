import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class Main {


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
//        changeFruitName();
//        IFruit apple = new Apple();
//        getProxy(apple).printName();
//        IFruit banana = new Banana();
//        getProxy(banana).printName();
//        Apple apple1 = new Apple();
//        apple1.testContinue();
//
//
//        Main main = new Main();
////        main = null;
////        System.gc();
//        main.amethod();
//
//
//        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
//        System.out.println(main.halfSearch(array, 0, array.length - 1, 5));
//
//        try {
//            System.out.println(InetAddress.getLocalHost());
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//
////        HttpClient client = new HttpClient();
////        client.request();
//
//        MoniClient client = new MoniClient("127.0.0.1", 8080);
//        try {
//            client.sendPost();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        Foo foo = new Foo("这个一个Foo对象！");
//        Class clazz = foo.getClass();
//        Method m1 = clazz.getDeclaredMethod("outInfo");
//        Method m2 = clazz.getDeclaredMethod("setMsg", String.class);
//        Method m3 = clazz.getDeclaredMethod("getMsg");
//
//        Field f = clazz.getDeclaredField("msg");
//        f.setAccessible(true); //设置些属性是可以访问的
//        String msss = (String) f.get(foo);
//        System.out.println(msss);
//        m1.invoke(foo);
//        m2.invoke(foo, "重新设置msg信息！");
//        String msg = (String) m3.invoke(foo);
//        System.out.println(msg);


//        new Thread(new Thread1()).start();
//        try {
//            Thread.sleep(1000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        new Thread(new Thread2()).start();

        Extern extern = new Extern();



    }

    private static IFruit getProxy(IFruit fruit) {
        return (IFruit) Proxy.newProxyInstance(
                fruit.getClass().getClassLoader(),
                fruit.getClass().getInterfaces(),
                new IFruitHook(fruit));
    }

    private static void changeFruitName() {
        try {
            Class<?> cls = Class.forName("Apple");
            Field gQueryInterface = cls.getDeclaredField("mName");
            gQueryInterface.setAccessible(true);
            gQueryInterface.set(null, "Durian");
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.print("finalize");
    }


    class ValHold {
        public int i = 10;
    }

    public void amethod() {
        int i = 99;
        ValHold v = new ValHold();
        v.i = 30;
        another(v, i);
        System.out.println("v" + v.hashCode());
        System.out.println(v.i);
    }

    public void another(ValHold v, int i) {
        i = 0;
        v.i = 20;
        System.out.println("before" + v.hashCode());
        ValHold vh = new ValHold();
        v = vh;
        System.out.println("after" + v.hashCode());

        System.out.println(v.i + "" + i);

    }


    public int halfSearch(int[] array, int low, int high, int target) {

        if (low > high) {
            return -1;
        }

        int mid = (low + high) / 2;
        if (array[mid] == target) {
            return mid;
        }

        if (target < array[mid]) {
            return halfSearch(array, low, mid, target);
        } else {
            return halfSearch(array, mid, high, target);
        }

    }

    static class Foo {
        private String msg;

        public Foo(String msg) {
            this.msg = msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public void outInfo() {
            System.out.println("这是测试Java反射的测试类");
        }
    }


    private static String string = "haha";
    private static class Thread1 implements Runnable {
        @Override
        public void run() {
            synchronized (Main.class) {
                System.out.println("enter thread1...");
                System.out.println("thread1 is waiting...");
                try {
                    // 调用wait()方法，线程会放弃对象锁，进入等待此对象的等待锁定池
                    Main.class.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 is going on ....");
                System.out.println("thread1 is over!!!");
            }
        }
    }

    private static class Thread2 implements Runnable {
        @Override
        public void run() {
            synchronized (Main.class) {
                System.out.println("enter thread2....");
                System.out.println("thread2 is sleep....");
                // 只有针对此对象调用notify()方法后本线程才进入对象锁定池准备获取对象锁进入运行状态。
                Main.class.notify();
                // ==================
                // 区别
                // 如果我们把代码：TestD.class.notify();给注释掉，即TestD.class调用了wait()方法，但是没有调用notify()
                // 方法，则线程永远处于挂起状态。
                try {
                    // sleep()方法导致了程序暂停执行指定的时间，让出cpu该其他线程，
                    // 但是他的监控状态依然保持者，当指定的时间到了又会自动恢复运行状态。
                    // 在调用sleep()方法的过程中，线程不会释放对象锁。
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 is going on....");
                System.out.println("thread2 is over!!!");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
