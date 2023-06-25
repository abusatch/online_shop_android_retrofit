package com.marwaeltayeb.souq.view;

import static com.marwaeltayeb.souq.utils.Constant.PRODUCTID;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.marwaeltayeb.souq.R;
import com.marwaeltayeb.souq.databinding.ActivityShippingAddressBinding;
import com.marwaeltayeb.souq.model.Shipping;
import com.marwaeltayeb.souq.storage.LoginUtils;
import com.marwaeltayeb.souq.viewmodel.ShippingViewModel;

import java.io.IOException;
import java.util.Objects;

public class ShippingAddressActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityShippingAddressBinding binding;

    private ShippingViewModel shippingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shipping_address);

        shippingViewModel = ViewModelProviders.of(this).get(ShippingViewModel.class);

        binding.proceed.setOnClickListener(this);

        binding.txtName.setText(LoginUtils.getInstance(this).getUserInfo().getName());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.proceed) {
            addShippingAddress();
        }
    }

    private void addShippingAddress() {
        String address = binding.address.getText().toString().trim();
        String city = binding.city.getText().toString().trim();
        String country = binding.country.getText().toString().trim();
        String zip = binding.zip.getText().toString().trim();
        String phone = binding.phone.getText().toString().trim();
        int userId = LoginUtils.getInstance(this).getUserInfo().getId();
        Intent intent = getIntent();
        int productId = intent.getIntExtra(PRODUCTID, 0);
        Log.e("TAG", "addShippingAddress: okesip2"+userId+"---"+productId );

//        Shipping shipping = new Shipping(address, city, country, zip, phone,userId, productId);

        shippingViewModel.addShippingAddress2(address, city, country, zip, phone,userId, productId).observe(this, shipping2 -> {
//            try {
//                if(!shipping2.){
//
//                }
                Log.e("TAG", "addShippingAddress: okesip"+userId+"---"+productId+"ss"+shipping2.toString() );
                if(Objects.equals(shipping2+"", "berhasil")){
                    Intent orderProductIntent = new Intent(ShippingAddressActivity.this, OrderProductActivity.class);
                    orderProductIntent.putExtra(PRODUCTID,productId);
                    startActivity(orderProductIntent);
                }else{
                    Log.e("TAG", "addShippingAddress: -"+shipping2+"-" );
                }
                Toast.makeText(ShippingAddressActivity.this, ""+shipping2+"", Toast.LENGTH_SHORT).show();

//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        });
    }
}
