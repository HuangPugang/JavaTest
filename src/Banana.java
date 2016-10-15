/**
 * Created by paul on 16/9/27.
 */
public class Banana implements IFruit {
    private final static String TAG = Banana.class.getSimpleName();
    private static String mName = Banana.class.getSimpleName();
    @Override
    public void printName() {
        System.out.print(mName);
    }
}
