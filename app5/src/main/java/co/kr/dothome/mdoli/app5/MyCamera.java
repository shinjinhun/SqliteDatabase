package co.kr.dothome.mdoli.app5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyCamera extends View {

    Context mContext;
    Bitmap mBitmap;
    Canvas mCanvas;
    Paint paint;

    Bitmap mImage;
    Camera camera = new Camera();

    public MyCamera(Context context) {
        super(context);

        init(context);
    }

    public MyCamera(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private  void  init(Context context) {
        mContext = context;

        paint = new Paint();

        mImage =  BitmapFactory.decodeResource(mContext.getResources(), R.drawable.img2);
    }

    protected  void  onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0 ,0 , null);
        }
    }

    // 초기화
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap);

        mCanvas.drawBitmap(mImage, 0,0, paint);

        camera.save();  // 그때 그때 상태 저장

        Matrix matrix = new Matrix();
        camera.rotateY(45.0f);
        camera.translate(0,0,500);  // 카메라 앞 뒤로 이동
        camera.getMatrix(matrix);   // 카메라에 설정된 매트릭스 객체를 참조

        camera.restore(); // 해재

        Bitmap  cImage = Bitmap.createBitmap(mImage, 0,0, mImage.getWidth(), mImage.getHeight(), matrix, true);

        mCanvas.drawBitmap(cImage, 400,600, paint);
    }


//
//    public MyCamera(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    public MyCamera(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
}
