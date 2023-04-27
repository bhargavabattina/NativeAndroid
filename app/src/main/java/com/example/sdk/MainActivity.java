package com.example.sdk;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sabpaisa.gateway.android.sdk.SabPaisaGateway;
import com.sabpaisa.gateway.android.sdk.interfaces.IPaymentSuccessCallBack;
import com.sabpaisa.gateway.android.sdk.models.TransactionResponsesModel;

public class MainActivity extends AppCompatActivity implements IPaymentSuccessCallBack<TransactionResponsesModel> {


    Button PayBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SabPaisaGateway.Companion.syncImages(this);

        PayBtn = findViewById(R.id.pay_btn);


        PayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openSDK(v);
            }

        });

    }


    public void openSDK(View view) {
        SabPaisaGateway sabPaisaGateway1 =
                SabPaisaGateway.Companion.builder()
                        .setAmount(350.0)   //Mandatory Parameter
                        .setFirstName("First Name Sab") //Mandatory Parameter
                        .setLastName("Last Sab") //Mandatory Parameter
                        .setMobileNumber("1234556644") //Mandatory Parameter
                        .setEmailId("123@123.com")//Mandatory Parameter
                        .setClientCode("TM001")
                        .setSabPaisaPaymentScreen(true)
                        .setAesApiIv("YN2v8qQcU3rGfA1y")
                        .setAesApiKey("kaY9AIhuJZNvKGp2")
                        .setTransUserName("rajiv.moti_336")
                        .setTransUserPassword("RIADA_SP336")
                        .build();

        SabPaisaGateway.Companion.setInitUrlSabpaisa("https://securepay.sabpaisa.in/SabPaisa/sabPaisaInit?v=1");
        SabPaisaGateway.Companion.setEndPointBaseUrlSabpaisa("https://securepay.sabpaisa.in");
        SabPaisaGateway.Companion.setTxnEnquiryEndpointSabpaisa("https://txnenquiry.sabpaisa.in");

        sabPaisaGateway1.init(this, this);
    }


    @Override
    public void onPaymentFail(@Nullable TransactionResponsesModel transactionResponsesModel) {
        Log.d("SABPAISA", "Payment Fail");
//        resultCode?.text = "Payment Success: ${message?.status}"
//        resultCode?.setTextColor(ContextCompat.getColor(this, R.color.red))
        ((TextView) findViewById(R.id.status)).setText("Payment status (" + transactionResponsesModel.getStatus() + " " + transactionResponsesModel.getClientTxnId());
    }

    @Override
    public void onPaymentSuccess(@Nullable TransactionResponsesModel transactionResponsesModel) {
        Log.d("SABPAISA", "Payment Success");
//        resultCode?.text = "Payment Success: ${response?.status}"
//        resultCode?.setTextColor(ContextCompat.getColor(this, R.color.material_color_green_600))
        ((TextView) findViewById(R.id.status)).setText("Payment status (" + transactionResponsesModel.getStatus() + " " + transactionResponsesModel.getClientTxnId());

    }
}



