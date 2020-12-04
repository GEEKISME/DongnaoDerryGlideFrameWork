package com.biotag.dongnaoderryglideframework;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.biotag.dongnaoderryglideframework.dongnaoglide.glide.DNGlide;
import com.biotag.dongnaoderryglideframework.dongnaoglide.listener.RequestListener;

public class MainActivity extends Activity {

    Button btn_loadone,btn_load100;
    ScrollView scrollView;
    LinearLayout scrollview_line;
    ImageView iv_q;
//    private String imageurl = "https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2941105969,51191030&fm=26&gp=0.jpg";
    private String imageurl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607075965412&di=1142d9eec5cb52d4cd0d355355abcd3e&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fblog%2F201511%2F10%2F20151110162400_jWwGJ.thumb.700_0.jpeg";
    private String[] imageUrlArray = new String[20];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStringUrlArray();
        initview();
        initpermission();
    }

    private void initStringUrlArray() {
        imageUrlArray[0] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607075965434&di=cbc1b5a1779d156c74ee8bb3796b63cb&imgtype=0&src=http%3A%2F%2Fwww.55506.net%2Fd%2Ffile%2Fmorepic%2F191530%2Fxmwor100uf1.jpg";
        imageUrlArray[1] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607075965433&di=542e160ef3224eec5e707f0c2653dde7&imgtype=0&src=http%3A%2F%2Fimage.biaobaiju.com%2Fuploads%2F20181007%2F16%2F1538901946-KwpcqSnuTt.jpg";
        imageUrlArray[2] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607075965432&di=877700995fe79dbf3b0b1c7b670731db&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftranslate%2Fw640h735%2F20180312%2FnPS6-fxpwyhx1515823.jpg";
        imageUrlArray[3] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607075965431&di=336d61987be462495e233d30698982d9&imgtype=0&src=http%3A%2F%2F01.minipic.eastday.com%2F20170818%2F20170818092454_0be75b00d9da4ed6845c01aa86bb8a7b_1.jpeg";
        imageUrlArray[4] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607076221127&di=98c15fc3c6d128d99f73f764f11ef930&imgtype=0&src=http%3A%2F%2Fmp2.qiyipic.com%2Fimage%2F20171024%2F24%2F46%2Fppu_151089910102_pp_601_300_300.jpg";
        imageUrlArray[5] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607077939739&di=3763b2c7d313689ce02df0131beef414&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2F0%2Ffd%2F0f8a1161967.jpg";
        imageUrlArray[6] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607075965430&di=fa7ef3583fee34208e89f16d3eff0c11&imgtype=0&src=http%3A%2F%2Fc3.haibao.cn%2Fimg%2F690_955_100_0%2F1510661051.7031%2Fb8b010e7afa2a9cb7dae4102266ba517.jpg";
        imageUrlArray[7] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607075965410&di=0d5991d9df66dc9368635990122efd52&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201507%2F06%2F20150706022808_WykcR.jpeg";
        imageUrlArray[8] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607075965430&di=df35cbf2a2739aa89fa225ced569fdad&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2Fa%2F94%2F2ccc498394_250_350.jpg";
        imageUrlArray[9] = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1055195773,2814819262&fm=26&gp=0.jpg";
        imageUrlArray[10] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607075965429&di=1be76b4e2d09c34783b4f019941bc6c8&imgtype=0&src=http%3A%2F%2Fd.paper.i4.cn%2Fmax%2F2018%2F02%2F06%2F14%2F1517898745001_444299.jpg";
        imageUrlArray[11] = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1136321297,2068116254&fm=26&gp=0.jpg";
        imageUrlArray[12] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607075965426&di=b3ad4330495c72f04351ce48d23f17d3&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2Fc%2Fca%2F3eee1353284.jpg_195.jpg";
        imageUrlArray[13] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607075965424&di=0db48fdd2fae164d61aea3a7fb5071af&imgtype=0&src=http%3A%2F%2Fimg.ewebweb.com%2Fuploads%2F20191112%2F11%2F1573529925-PezTZfXvJp.jpg";
        imageUrlArray[14] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607075965422&di=e1d406e2e4333e46ee91d6cd17854988&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn10101%2F568%2Fw720h648%2F20190315%2Fe500-hufnxfn5383765.jpg";
        imageUrlArray[15] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607075965422&di=0fdf398a78f39563e457df8cef17164d&imgtype=0&src=http%3A%2F%2Fimage.meifajie.com%2Fattachments%2Fimage%2F2017-08%2F20170828110817_71596.png";
        imageUrlArray[16] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607075965421&di=b42c51b791a236fe9372468d25915a56&imgtype=0&src=http%3A%2F%2Fc2.haibao.cn%2Fimg%2F600_0_100_0%2F1508764453.9803%2F13fd9050ade52d8ceb3d34516cd65cbd.jpg";
        imageUrlArray[17] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607075965417&di=903e8371afec04f92a84dcefc1aaaf3d&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2F5%2Fa8%2Faf0c96dbc2_250_350.jpg";
        imageUrlArray[18] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1607075965411&di=e36a61ba8b20b1f38c2b68c1097a899a&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F5%2F5972f26809829.jpg";
        imageUrlArray[19] = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2168666032,491175836&fm=26&gp=0.jpg";
    }

    private void initpermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (       checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED
                        || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissions(new String[]{
                            Manifest.permission.CAMERA,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    }, 1);
                }
            }
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter!=null && !bluetoothAdapter.isEnabled()) {
                Toast.makeText(this, "请先打开蓝牙~", Toast.LENGTH_LONG).show();
                return;
            }

        }
    }
    private void initview() {
        iv_q = findViewById(R.id.iv);
        btn_load100 = findViewById(R.id.btn_load100);
        btn_loadone = findViewById(R.id.btn_loadone);
        scrollView = findViewById(R.id.scrollview);
        scrollview_line = findViewById(R.id.scrollview_line);
        btn_loadone.setOnClickListener(v->{
            DNGlide.with(this).load(imageurl).listener(new RequestListener() {
                @Override
                public void onSuccess(Bitmap bitmap) {

                }

                @Override
                public void onFaile() {

                }
            }).loading(R.mipmap.ic_launcher).into(iv_q);

            ;
        });
        btn_load100.setOnClickListener(v->{
            for (int i = 0; i < 20; i++) {
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                scrollview_line.addView(imageView);
                DNGlide.with(this).loading(R.mipmap.ic_launcher).load(imageUrlArray[i]).into(imageView);
                Log.i("tmsk","当前url："+i+" ，"+imageUrlArray[i]);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}