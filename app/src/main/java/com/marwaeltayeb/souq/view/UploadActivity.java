package com.marwaeltayeb.souq.view;

import static com.marwaeltayeb.souq.utils.Constant.LOCALHOST;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.marwaeltayeb.souq.R;
import com.marwaeltayeb.souq.service.AppController;
//import com.esafirm.imagepicker.features.ImagePicker;
//import com.esafirm.imagepicker.model.Image;
//import com.github.gcacace.signaturepad.views.SignaturePad;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class UploadActivity extends AppCompatActivity {
    ImageView ivProfile, propic, hasilsign, camera, galery;
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private Uri uri;
    LinearLayout lltakepic;
    final static int TAKE_PICTURE = 1;
    private static int RESULT_LOAD_IMAGE = 1;
    private static int RESULT_LOAD_IMG = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    String mCurrentPhotoPath;
    String imgDecodableString;
    TextView clearsign, loadsignature, putarputar, putarkiri, normall, uploadfotoo, keterangan, takepic, takepicc;
    String tag_json_obj = "json_obj_req";
    String urlimage;
    private static final String TAG = UploadActivity.class.getSimpleName();
    private String UPLOAD_URL = "uploadgambar.php";

    private String url = "uploadgambar.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private String UPLOAD_SIGN = LOCALHOST+"uploadfoto.php";
    Bitmap bitmap, decoded;
    int success;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 100; // range 1 - 100
    EditText idteknis, pathket;
    String currentPhotoPath;
    public static final int RESULT_GALLERY = 0;
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        propic = findViewById(R.id.profile_image);
        loadsignature = findViewById(R.id.loadsignature);
        putarputar = findViewById(R.id.putarputar);
        normall = findViewById(R.id.normall);
        putarkiri = findViewById(R.id.putarkiri);
        uploadfotoo = findViewById(R.id.simpanfotoo);
        keterangan = findViewById(R.id.keterangan);
        idteknis = findViewById(R.id.idteknis);
        pathket = findViewById(R.id.pathket);
        takepic = findViewById(R.id.takepic);
        camera = findViewById(R.id.camera);
        galery = findViewById(R.id.galery);
        lltakepic = findViewById(R.id.lltakepic);
        takepicc = findViewById(R.id.takepicc);


        SharedPreferences shr = getSharedPreferences("data_login", Context.MODE_PRIVATE);
        idteknis.setText(shr.getString("id",""));

        pathket.setText(getIntent().getStringExtra("pathh"));


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    takePicture();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        galery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                darigalery();
            }
        });

    }


    void darigalery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
    }
    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TAKE_PHOTO)
        {
            if (resultCode == RESULT_OK)
            {

                Uri uri = data.getData();
                Log.e(TAG, "urayyy "+uri );
                if(uri == null){
                    Bitmap bmp = BitmapFactory.decodeFile(pathket.getText().toString());
                    final Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setToImageView(getResizedBitmap(bmp, 1500));
                        }
                    }, 500);

                    uploadfotoo.setVisibility(View.VISIBLE);
                    uploadfotoo.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onClick(View v) {
                            uploadSign();
                        }
                    });
                    putarputar.setVisibility(View.VISIBLE);
                    putarputar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setToImageView(getResizedBitmap3(bmp, 1500));
                            putarputar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    setToImageView(getResizedBitmap4(bmp, 1500));
                                    putarputar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            setToImageView(getResizedBitmap5(bmp, 1500));
                                            putarputar.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    setToImageView(getResizedBitmap6(bmp, 1500));
                                                }
                                            });
                                        }
                                    });
                                }
                            });

                        }
                    });
                }else {

                    try {
                        Bitmap bmp = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        final Handler handler2 = new Handler();
                        handler2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                setToImageView(getResizedBitmap(bmp, 1500));
                            }
                        }, 500);

                        uploadfotoo.setVisibility(View.VISIBLE);
                        uploadfotoo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                uploadSign();
                            }
                        });
                        putarputar.setVisibility(View.VISIBLE);
                        putarputar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setToImageView(getResizedBitmap3(bmp, 1500));
                                putarputar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        setToImageView(getResizedBitmap4(bmp, 1500));
                                        putarputar.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                setToImageView(getResizedBitmap5(bmp, 1500));
                                                putarputar.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        setToImageView(getResizedBitmap6(bmp, 1500));
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });

                            }
                        });
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "Tidak dapat mengambil gambar dari gallery", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                }

            }
            else
            {

                if (resultCode == RESULT_CANCELED)
                {
                }
            }
        }else if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data ){

            Log.e(TAG, "onActivityResult: okreyy-"+data.getData().toString() );

            Uri uri = data.getData();

            try {
                Bitmap bmp = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setToImageView(getResizedBitmap(bmp, 1500));
                    }
                }, 500);

                uploadfotoo.setVisibility(View.VISIBLE);
                uploadfotoo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uploadSign();
                    }
                });
                putarputar.setVisibility(View.VISIBLE);
                putarputar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setToImageView(getResizedBitmap3(bmp, 1500));
                        putarputar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setToImageView(getResizedBitmap4(bmp, 1500));
                                putarputar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        setToImageView(getResizedBitmap5(bmp, 1500));
                                        putarputar.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                setToImageView(getResizedBitmap6(bmp, 1500));
                                            }
                                        });
                                    }
                                });
                            }
                        });

                    }
                });
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),"Tidak dapat mengambil gambar dari gallery",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }

    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        Matrix matrix = new Matrix();

        matrix.postRotate(0);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(image, width, height, true);

        return        Bitmap.createBitmap(scaledBitmap, 0, 0, width,
                height, matrix, true);
    }

    public Bitmap getResizedBitmap4(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        Matrix matrix = new Matrix();

        matrix.postRotate(180);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(image, width, height, true);

        return        Bitmap.createBitmap(scaledBitmap, 0, 0, width,
                height, matrix, true);
    }

    public Bitmap getResizedBitmap5(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        Matrix matrix = new Matrix();

        matrix.postRotate(270);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(image, width, height, true);

        return        Bitmap.createBitmap(scaledBitmap, 0, 0, width,
                height, matrix, true);
    }

    public Bitmap getResizedBitmap6(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        Matrix matrix = new Matrix();

        matrix.postRotate(360);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(image, width, height, true);

        return        Bitmap.createBitmap(scaledBitmap, 0, 0, width,
                height, matrix, true);
    }

    public Bitmap getResizedBitmap2(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        Matrix matrix = new Matrix();

        matrix.postRotate(90);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(image, width, height, true);

        return        Bitmap.createBitmap(scaledBitmap, 0, 0, width,
                height, matrix, true);
    }


    public Bitmap getResizedBitmap3(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        Matrix matrix = new Matrix();

        matrix.postRotate(90);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(image, width, height, true);
        return        Bitmap.createBitmap(scaledBitmap, 0, 0, width,
                height, matrix, true);
    }

    private void setToImageView(Bitmap bmp) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        propic.setImageBitmap(decoded);
    }


    private void uploadSign() {
        Log.e(TAG, "uploadSign: "+getIntent().getStringExtra("idas")+"-"+getIntent().getStringExtra("dari") );
        loadsignature.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_SIGN,

                new com.android.volley.Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "Response: " + response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response);
                            success = jObj.getInt(TAG_SUCCESS);


                            if (success == 1) {
                                loadsignature.setVisibility(View.GONE);
                                Log.e("v Add", jObj.getString("message"));

                                if(getIntent().getStringExtra("dari").equalsIgnoreCase("konfirmasi")) {

                                    Intent i = new Intent(getApplicationContext(), ProductActivity.class);
                                    i.putExtra("idas", jObj.getString("idas"));
                                    startActivity(i);
                                    finish();
                                }else{
                                    Intent i = new Intent(getApplicationContext(), ProductActivity.class);
                                    i.putExtra("idas", jObj.getString("idas"));
                                    startActivity(i);
                                    finish();
                                }

                                Toast.makeText(getApplicationContext(), jObj.getString("message"), Toast.LENGTH_LONG).show();
                            } else {
                                loadsignature.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            loadsignature.setVisibility(View.GONE);
                            e.printStackTrace();
                        }
                        loadsignature.setVisibility(View.GONE);
                    }
                },

                new com.android.volley.Response.ErrorListener() {



                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loadsignature.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Foto tidak boleh kosong"+error, Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("media", getStringImage(decoded));
                params.put("dari", getIntent().getStringExtra("dari"));
                params.put("idas", getIntent().getStringExtra("idas"));
                Log.e(TAG, "" + params);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        if(encodedImage.equalsIgnoreCase(null)){
            //         Toast.makeText(EditProfile.this,"nulllllll",Toast.LENGTH_LONG).show();
        }

        return encodedImage;
    }

     private Bitmap getScaledBitmap(String picturePath, int width, int height) {
        BitmapFactory.Options sizeOptions = null;
        try {
            sizeOptions = new BitmapFactory.Options();
            sizeOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(picturePath, sizeOptions);

            sizeOptions.inJustDecodeBounds = false;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return BitmapFactory.decodeFile(picturePath, sizeOptions);
    }










    void camm(){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, TAKE_PICTURE);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }
    public void takePicture() throws IOException {

        // Check if there is a camera.
        if (!hasCamera()) {
            Toast.makeText(getApplicationContext(), "This device does not have a camera.", Toast.LENGTH_SHORT).show();
            return;
        } else {

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                //...
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "fjg.aplikasi.teknisifjg",
                        photoFile);

                keterangan.setText(photoFile.toString());
                pathket.setText(photoFile.toString());





                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }

        }
    }
}