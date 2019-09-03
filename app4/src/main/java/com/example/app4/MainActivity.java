package com.example.app4;


import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image01 = (ImageView)findViewById(R.id.img01);
        ImageView image02 = (ImageView)findViewById(R.id.img02);
        ImageView image03 = (ImageView)findViewById(R.id.img03);
        ImageView image04 = (ImageView)findViewById(R.id.img04);
        ImageView image05 = (ImageView)findViewById(R.id.img05);
        ImageView image06 = (ImageView)findViewById(R.id.img06);
        ImageView image07 = (ImageView)findViewById(R.id.img07);
        ImageView image08 = (ImageView)findViewById(R.id.img08);


        image01.setScaleType(ImageView.ScaleType.CENTER);       // 이미지뷰의 중앙에 배치
        image02.setScaleType(ImageView.ScaleType.CENTER_CROP);  // 이미지를 뷰의 크기와 동일하게 하면서 축소/확대(가로세로비율 동일)를 한다.
        image03.setScaleType(ImageView.ScaleType.CENTER_INSIDE); // DEFAULT, 이미지를 뷰안에 모두 담는다. 축소/확대(가로세로비율 동일)를 한다.
        image04.setScaleType(ImageView.ScaleType.FIT_CENTER);
        image05.setScaleType(ImageView.ScaleType.FIT_END);      // 우측하단에 배치
        image06.setScaleType(ImageView.ScaleType.FIT_START);    // 좌측상단에 배치
        image07.setScaleType(ImageView.ScaleType.FIT_XY);       // 가로세로 비율을 이미지 뷰에 크기에 맞게 확대 축소하여 채우기
        image08.setScaleType(ImageView.ScaleType.MATRIX);       // 뷰안에 원본이미지 표시 ( 원본사진 좌측 상단 기준으로 )


        Matrix matrix = new Matrix();
        matrix.postRotate(30.0f);

        image08.setImageMatrix(matrix);


    }
}
