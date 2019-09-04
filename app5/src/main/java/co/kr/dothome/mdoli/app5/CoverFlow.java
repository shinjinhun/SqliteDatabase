package co.kr.dothome.mdoli.app5;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

public class CoverFlow extends Gallery {

    private Camera camera = new Camera();
    private int centerPoint;

    public static int maxRotationAngle = 55;
    public static int maxZoom = -60;

    public CoverFlow(Context context) {
        super(context);

        init(); //초기화 작업
    }



    private void init(){
        this.setStaticTransformationsEnabled(true);
    }

    protected boolean getChildStaticTransformation(View child, Transformation t) {
        final int childCenter = getCenterOfView(child);
        final int childWidth = child.getWidth();
        int rotationAngle = 0;

        t.clear();

        t.setTransformationType(Transformation.TYPE_MATRIX);

        if(childCenter == centerPoint) {
            transformImageBitmap((ImageView) child, t, 0);
        } else {
            rotationAngle =  (int)(((float)(centerPoint - childCenter) / childWidth) * maxRotationAngle);
            if(Math.abs(rotationAngle) > maxRotationAngle) {
                rotationAngle = (rotationAngle < 0) ? -maxRotationAngle : maxRotationAngle;
            }
            transformImageBitmap((ImageView)child, t, rotationAngle);
        }

        return true;
    }

    private void transformImageBitmap(ImageView child, Transformation t, int rotationAngle){
        camera.save();
        final Matrix imageMatrix = t.getMatrix();
        final int imageHeight = child.getLayoutParams().height;
        final int imageWidth = child.getLayoutParams().width;
        final int rotation = Math.abs(rotationAngle);

        camera.translate(0, 0, 100 );

        if (rotation < maxRotationAngle) {
            float zoomAmount = (float) (maxZoom + (rotation * 1.5));
            camera.translate(0,0, zoomAmount);
        }

        camera.rotateY(rotationAngle);
        camera.getMatrix(imageMatrix);

        // 이미지 이동
        imageMatrix.preTranslate(-(imageWidth/2), -(imageHeight/2));
        imageMatrix.postTranslate((imageWidth/2), (imageHeight/2));

        camera.restore();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerPoint = getCenterOfCoverflow();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private static int getCenterOfView(View view) {
        return view.getLeft() + view.getWidth()/2;
    }

    private int getCenterOfCoverflow(){
        return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
    }


    public CoverFlow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CoverFlow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
//
//    public CoverFlow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
}
