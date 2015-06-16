package jp.co.cyberagent.android.gpuimage.sample.filter;

import android.graphics.PointF;
import android.opengl.GLES20;

import jp.co.cyberagent.android.gpuimage.GPUImageTwoInputFilter;
import jp.co.cyberagent.android.gpuimage.Rotation;

/**
 * Created by wei on 6/5/15.
 */
public class SelectiveFocusFilter extends GPUImageTwoInputFilter {
//    private static final String SELECTIVE_GAUSIAN_SHADER = "" +
//            " varying highp vec2 textureCoordinate;\n" +
//            " varying highp vec2 textureCoordinate2;\n" +
//            " \n" +
//            " uniform sampler2D inputImageTexture;\n" +
//            " uniform sampler2D inputImageTexture2; \n" +
//            " \n" +
//            " uniform lowp float excludeCircleRadius;\n" +
//            " uniform lowp vec2 excludeCirclePoint;\n" +
//            " uniform lowp float excludeBlurSize;\n" +
//            " uniform highp float aspectRatio;\n" +
//            "\n" +
//            " void main()\n" +
//            " {\n" +
//            "     lowp vec4 sharpImageColor = texture2D(inputImageTexture, textureCoordinate);\n" +
//            "     lowp vec4 blurredImageColor = texture2D(inputImageTexture2, textureCoordinate2);\n" +
//            "     \n" +
//            "     highp vec2 textureCoordinateToUse = vec2(textureCoordinate2.x, (textureCoordinate2.y * aspectRatio + 0.5 - 0.5 * aspectRatio));\n" +
//            "     highp float distanceFromCenter = distance(excludeCirclePoint, textureCoordinateToUse);\n" +
//            "     \n" +
//            "     gl_FragColor = mix(sharpImageColor, blurredImageColor, smoothstep(excludeCircleRadius - excludeBlurSize, excludeCircleRadius, distanceFromCenter));\n" +
//            " }";

    private static final String SELECTIVE_GAUSIAN_SHADER = "" +
            " varying highp vec2 textureCoordinate;\n" +
            " varying highp vec2 textureCoordinate2;\n" +
            " \n" +
            " uniform sampler2D inputImageTexture;\n" +
            " uniform sampler2D inputImageTexture2; \n" +
            " \n" +
            " uniform lowp float excludeCircleRadius;\n" +
            " uniform lowp vec2 excludeCirclePoint;\n" +
            " uniform lowp float excludeBlurSize;\n" +
            " uniform highp float aspectRatio;\n" +
            "\n" +
            " void main()\n" +
            " {\n" +
            "     lowp vec4 sharpImageColor = texture2D(inputImageTexture2, textureCoordinate2);\n" +
            "     lowp vec4 blurredImageColor = texture2D(inputImageTexture, textureCoordinate);\n" +
            "     \n" +
            "     highp vec2 textureCoordinateToUse = vec2(textureCoordinate2.x, (textureCoordinate2.y * aspectRatio + 0.5 - 0.5 * aspectRatio));\n" +
            "     highp float distanceFromCenter = distance(excludeCirclePoint, textureCoordinateToUse);\n" +
            "     \n" +
            "     gl_FragColor = mix(sharpImageColor, blurredImageColor, smoothstep(excludeCircleRadius - excludeBlurSize, excludeCircleRadius, distanceFromCenter));\n" +
            " }";

    public SelectiveFocusFilter() {
        super(SELECTIVE_GAUSIAN_SHADER);
    }

    @Override
    public void onInit() {
        super.onInit();

        int excludeCirclePointLocation = GLES20.glGetUniformLocation(getProgram(), "excludeCirclePoint");
        int excludeCircleRadiusLocation = GLES20.glGetUniformLocation(getProgram(), "excludeCircleRadius");
        int excludeBlurSizeLocation = GLES20.glGetUniformLocation(getProgram(), "excludeBlurSize");

        setPoint(excludeCirclePointLocation, new PointF(0.5f, 0.5f));
        setFloat(excludeCircleRadiusLocation, 0.2f);
        setFloat(excludeBlurSizeLocation, 0.1f);

        int aspectRatioLocation = GLES20.glGetUniformLocation(getProgram(), "aspectRatio");
        setFloat(aspectRatioLocation, 1.5f);
    }

    public void setBlurSize(float size) {
        int excludeBlurSizeLocation = GLES20.glGetUniformLocation(getProgram(), "excludeBlurSize");
        setFloat(excludeBlurSizeLocation, size);
    }

    @Override
    protected void onDrawArraysPre() {
        setSecondTextureInput(1);
        super.onDrawArraysPre();
    }

    @Override
    public void setSecondTextureInput(int texture) {
        super.setSecondTextureInput(texture);
//        setRotation(Rotation.ROTATION_90, false, false);
    }
}
