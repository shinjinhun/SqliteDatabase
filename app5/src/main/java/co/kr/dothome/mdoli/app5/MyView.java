package co.kr.dothome.mdoli.app5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mPaint;

    public MyView(Context context) {
        super(context);

        mPaint = new Paint();
    }

    // 초기화
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
         createMemoryBitmap(w, h);
         mDrawing();
    }

    // 메모리 생성
    private void createMemoryBitmap(int w, int h) {
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap);
    }

    // 메모리에 그릴때
    private void mDrawing() {
        mCanvas.drawColor(Color.WHITE);

        mPaint.setColor(Color.BLUE);
        mCanvas.drawRect(200, 200, 400, 400, mPaint);
    }

    // 메모리의 그림을 화면을 그릴때
    protected void onDraw(Canvas canvas){
        if(mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0,0, null);
        }
    }

//    public myView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public myView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    public myView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
}
