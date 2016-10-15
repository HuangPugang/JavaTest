import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static sun.jvm.hotspot.debugger.win32.coff.DebugVC50X86RegisterEnums.TAG;

/**
 * Created by paul on 16/9/27.
 */
public class IFruitHook implements InvocationHandler {
    private Object mHookedObj;

    public IFruitHook(Object hookedObj) {
        mHookedObj = hookedObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.print("##############");
        Object result = method.invoke(mHookedObj, args);
        System.out.print("##############");

        return result;
    }
}
