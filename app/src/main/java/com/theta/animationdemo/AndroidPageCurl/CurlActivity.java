package com.theta.animationdemo.AndroidPageCurl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.theta.animationdemo.R;

public class CurlActivity extends AppCompatActivity {

    private CurlView mCurlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curl);

        int index = 0;
        if (getLastNonConfigurationInstance() != null) {
            index = (Integer) getLastNonConfigurationInstance();
        }
        mCurlView = (CurlView) findViewById(R.id.curl);
        mCurlView.setPageProvider(new PageProvider());
        mCurlView.setSizeChangedObserver(new SizeChangedObserver());
        mCurlView.setCurrentIndex(index);
        mCurlView.setBackgroundColor(0xFF202830);

        // This is something somewhat experimental. Before uncommenting next
        // line, please see method comments in CurlView.
        // mCurlView.setEnableTouchPressure(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCurlView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCurlView.onResume();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mCurlView.getCurrentIndex();

    }

    private class PageProvider implements CurlView.PageProvider {


        // Bitmap resources.
        private int[] mBitmapIds = {R.drawable.p1, R.drawable.p2,
                R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground};

        private String[] title = {"JAVA", "ANDROID", "KOTLIN", "C", "C++"};
        LayoutInflater inflater;

        public PageProvider() {
            inflater = LayoutInflater.from(CurlActivity.this);
        }

        @Override
        public int getPageCount() {
            // return mBitmapIds.length;
            return title.length;
        }
        private Bitmap loadBitmap(int width, int height, int index) {
            Bitmap b = Bitmap.createBitmap(width, height,
                    Bitmap.Config.ARGB_8888);
            b.eraseColor(0xFFFFFFFF);
            Canvas c = new Canvas(b);
            Drawable d = getResources().getDrawable(mBitmapIds[index]);

            int margin = 7;
            int border = 3;
            Rect r = new Rect(margin, margin, width - margin, height - margin);

            int imageWidth = r.width() - (border * 2);
            int imageHeight = imageWidth * d.getIntrinsicHeight()
                    / d.getIntrinsicWidth();
            if (imageHeight > r.height() - (border * 2)) {
                imageHeight = r.height() - (border * 2);
                imageWidth = imageHeight * d.getIntrinsicWidth()
                        / d.getIntrinsicHeight();
            }

            r.left += ((r.width() - imageWidth) / 2) - border;
            r.right = r.left + imageWidth + border + border;
            r.top += ((r.height() - imageHeight) / 2) - border;
            r.bottom = r.top + imageHeight + border + border;

            Paint p = new Paint();
            p.setColor(0xFFC0C0C0);
            c.drawRect(r, p);
            r.left += border;
            r.right -= border;
            r.top += border;
            r.bottom -= border;

            d.setBounds(r);
            d.draw(c);

            return b;
        }

        public Bitmap loadBitmapFromView(View v) {

            Bitmap b = Bitmap.createBitmap( v.getLayoutParams().width, v.getLayoutParams().height,Bitmap.Config.ARGB_8888);

            Canvas c = new Canvas(b);

            v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);

            v.draw(c);
            return b;

        }


        @Override
        public void updatePage(CurlPage page, int width, int height, int index) {
            switch (index) {
                case 0: {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                           100, 100);
                    //lp.addRule(LinearLayout.CENTER_IN_PARENT);
                    TextView textView = new TextView(CurlActivity.this);
                    textView.setTextSize(10);
                    textView.setLayoutParams(lp);
                    textView.setText(title[index]);
                    textView.setGravity(Gravity.CENTER);

                    Bitmap bm = loadBitmapFromView(textView);
                    page.setTexture(bm,CurlPage.SIDE_FRONT );
                    break;
                }
                case 1: {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                           100, 100);
                    //lp.addRule(LinearLayout.CENTER_IN_PARENT);
                    TextView textView = new TextView(CurlActivity.this);
                    textView.setTextSize(10);
                    textView.setLayoutParams(lp);
                    textView.setText(title[index]);
                    textView.setGravity(Gravity.CENTER);

                    Bitmap bm = loadBitmapFromView(textView);
                    page.setTexture(bm,CurlPage.SIDE_FRONT );
                    break;
                }
                case 2: {
                   LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                           100, 100);
                    //lp.addRule(LinearLayout.CENTER_IN_PARENT);
                    TextView textView = new TextView(CurlActivity.this);
                    textView.setTextSize(10);
                    textView.setLayoutParams(lp);
                    textView.setText(title[index]);
                    textView.setGravity(Gravity.CENTER);

                    Bitmap bm = loadBitmapFromView(textView);
                    page.setTexture(bm,CurlPage.SIDE_FRONT );
                    break;
                }
                case 3: {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                           100, 100);
                    //lp.addRule(LinearLayout.CENTER_IN_PARENT);
                    TextView textView = new TextView(CurlActivity.this);
                    textView.setTextSize(10);
                    textView.setLayoutParams(lp);
                    textView.setText(title[index]);
                    textView.setGravity(Gravity.CENTER);

                    Bitmap bm = loadBitmapFromView(textView);
                    page.setTexture(bm,CurlPage.SIDE_FRONT );
                    break;
                }
                case 4: {
                   LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                           100, 100);
                    //lp.addRule(LinearLayout.CENTER_IN_PARENT);
                    TextView textView = new TextView(CurlActivity.this);
                    textView.setTextSize(10);
                    textView.setLayoutParams(lp);
                    textView.setText(title[index]);
                    textView.setGravity(Gravity.CENTER);

                    Bitmap bm = loadBitmapFromView(textView);
                    page.setTexture(bm,CurlPage.SIDE_FRONT );
                    break;
                }
            }
           /* switch (index) {
                // First case is image on front side, solid colored back.
                case 0: {
                    Bitmap front = loadBitmap(width, height, 0);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setColor(Color.rgb(180, 180, 180), CurlPage.SIDE_BACK);
                    break;
                }
                // Second case is image on back side, solid colored front.
                case 1: {
                    Bitmap back = loadBitmap(width, height, 2);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    page.setColor(Color.rgb(127, 140, 180), CurlPage.SIDE_FRONT);
                    break;
                }
                // Third case is images on both sides.
                case 2: {
                    Bitmap front = loadBitmap(width, height, 1);
                    Bitmap back = loadBitmap(width, height, 3);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    break;
                }
                // Fourth case is images on both sides - plus they are blend against
                // separate colors.
                case 3: {
                    Bitmap front = loadBitmap(width, height, 2);
                    Bitmap back = loadBitmap(width, height, 1);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    page.setColor(Color.argb(127, 170, 130, 255),
                            CurlPage.SIDE_FRONT);
                    page.setColor(Color.rgb(255, 190, 150), CurlPage.SIDE_BACK);
                    break;
                }
                // Fifth case is same image is assigned to front and back. In this
                // scenario only one texture is used and shared for both sides.
                case 4:
                    Bitmap front = loadBitmap(width, height, 0);
                    page.setTexture(front, CurlPage.SIDE_BOTH);
                    page.setColor(Color.argb(127, 255, 255, 255),
                            CurlPage.SIDE_BACK);
                    break;
            }*/
        }
    }

    /**
     * CurlView size changed observer.
     */
    private class SizeChangedObserver implements CurlView.SizeChangedObserver {
        @Override
        public void onSizeChanged(int w, int h) {
            if (w > h) {
                mCurlView.setViewMode(CurlView.SHOW_TWO_PAGES);
                mCurlView.setMargins(.1f, .05f, .1f, .05f);
            } else {
                mCurlView.setViewMode(CurlView.SHOW_ONE_PAGE);
                mCurlView.setMargins(.1f, .1f, .1f, .1f);
            }
        }

    }
}

