package jp.co.cyberagent.android.gpuimage.sample.filter;

import jp.co.cyberagent.android.gpuimage.GPUImageFilterGroup;
import jp.co.cyberagent.android.gpuimage.GPUImageGaussianBlurFilter;

/**
 * Created by wei on 6/5/15.
 */
public class SelectiveGausianFilter extends GPUImageFilterGroup {

    private final SelectiveFocusFilter focusFilter;
    private final GPUImageGaussianBlurFilter blurFilter;

    public SelectiveGausianFilter() {
        blurFilter = new GPUImageGaussianBlurFilter(2f);
        addFilter(blurFilter);

        focusFilter = new SelectiveFocusFilter();
        addFilter(focusFilter);
    }

    @Override
    public void onInit() {
        super.onInit();
    }

    @Override
    public void onInitialized() {
        super.onInitialized();
    }

    public void setBlurSize(float size) {
        focusFilter.setBlurSize(size);
        blurFilter.setBlurSize(size);
    }

    public void setSecondTextureInput(int texture) {
        focusFilter.setSecondTextureInput(texture);
    }
}
