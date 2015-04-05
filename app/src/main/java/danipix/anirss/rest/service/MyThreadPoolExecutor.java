package danipix.anirss.rest.service;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dani Pix on 4/5/2015.
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {
    private static MyThreadPoolExecutor instance;

    public static MyThreadPoolExecutor getInstance() {
        if(instance == null){
            instance = new MyThreadPoolExecutor();
        }
        return instance;
    }

    public MyThreadPoolExecutor() {
        super(1, 128, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
    }

    public static void setInstance(){
        instance = null;
    }

}
