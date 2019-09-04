package co.kr.dothome.mdoli.app5;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MyView myView = new MyView(this);
//        setContentView(myView);

        // 커버플로우 어댑터 설정
        CoverFlow coverFlow = (CoverFlow)findViewById(R.id.coverFlow);
        ImageAdapter imgAdapter = new ImageAdapter(this);
        coverFlow.setAdapter(imgAdapter);

        //커버플로우에 속성 설정
        coverFlow.setSpacing(-150);
        coverFlow.setSelection(2,true);
        coverFlow.setAnimationDuration(3000);

    }

    // 어댑터 클래스
    public class ImageAdapter extends BaseAdapter{

        private Context mContext;
        private Integer[] mImageIds = {
                R.drawable.camera_img1,
                R.drawable.camera_img2,
                R.drawable.camera_img3,
                R.drawable.camera_img4,
                R.drawable.camera_img5,
                R.drawable.camera_img6,
                R.drawable.camera_img7,
                R.drawable.camera_img8
                };

        private ImageView[] mImage; // 이미지 뷰 배열

        public ImageAdapter(Context context) {
            mContext = context;
            mImage = new ImageView[mImageIds.length];
        }

        public int getCount(){
            return mImage.length;
        }

        public Object getItem(int position){
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent){

            ImageView imageV = new ImageView(mContext);
            imageV.setImageResource(mImageIds[position]);
            imageV.setLayoutParams(new CoverFlow.LayoutParams(500,280));
            imageV.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

            BitmapDrawable drawable = (BitmapDrawable)imageV.getDrawable();
            drawable.setAntiAlias(true);

            return imageV;
        }



    }


}
