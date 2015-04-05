package danipix.anirss.rest.service;

/**
 * Created by Dani Pix on 4/5/2015.
 */
public interface OnProgressListener {
    void onProgressStart(String moduleName);

    void onProgressStop(String moduleName);
}
