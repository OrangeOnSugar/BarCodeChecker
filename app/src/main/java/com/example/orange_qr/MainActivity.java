package com.example.orange_qr;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.LinearGradient;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {
    private CodeScanner mCodeScanner;
    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void run() {
                        code = result.getText();
                        if (code.length() == 13)
                        {
                            ShowMenu();
                        }
                        else if(code.length() < 13)
                        {
                            while (code.length() != 13)
                            {
                                code = "0"+code;
                            }
                            ShowMenu();
                        }
                        else
                        {
                            mCodeScanner.startPreview();
                        }
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }

    public void ShowMenu()
    {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(code, BarcodeFormat.CODE_128,500,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                    MainActivity.this, R.style.BottomSheetDialogTheme
            );
            View bottomView = LayoutInflater.from(getApplicationContext())
                    .inflate(R.layout.bottom_result, (LinearLayout)findViewById(R.id.result_container));
            ImageView barcode = bottomView.findViewById(R.id.result_qr);
            barcode.setImageBitmap(bitmap);
            TextView resultpod = bottomView.findViewById(R.id.result_podlin);
            TextView resulcountry = bottomView.findViewById(R.id.result_country);
            TextView resulcode = bottomView.findViewById(R.id.result_code);
            resulcode.setText(code);
            resultpod.setText("??????????????????????: " + Tryornot(code));
            resulcountry.setText("????????????: " + Contry(code));
            bottomSheetDialog.setContentView(bottomView);
            bottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    mCodeScanner.startPreview();
                }
            });
            bottomSheetDialog.show();
        }
        catch (WriterException e)
        {
            e.printStackTrace();
        }
    }

    public String Contry(String code)
    {
        String line = code.replace("", ",").trim();
        line = line.substring(1, line.length()-1);
        String[] items = line.split(",");
        int[] codeint = new int[items.length];
        String countrycode = "";
        for (int i = 0; i < items.length; i++)
        {
            codeint[i] = Integer.parseInt(items[i]);
        }
        countrycode = String.valueOf(codeint[0]) + String.valueOf(codeint[1]) + String.valueOf(codeint[2]);
        if (Integer.valueOf(countrycode) >= 0 && Integer.valueOf(countrycode) <= 13)
        {
            return "?????? ?????? ????????????";
        }
        else if(Integer.valueOf(countrycode) >= 30 && Integer.valueOf(countrycode) <= 37)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 380)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 383)
        {
            return "????????????????";
        }
        else if(Integer.valueOf(countrycode) == 385)
        {
            return "????????????????";
        }
        else if(Integer.valueOf(countrycode) == 387)
        {
            return "???????????? ?????? ??????????????????????";
        }
        else if(Integer.valueOf(countrycode) >= 400 && Integer.valueOf(countrycode) <= 440)
        {
            return "????????????????";
        }
        else if(Integer.valueOf(countrycode) >= 45 && Integer.valueOf(countrycode) <= 49)
        {
            return "????????????";
        }
        else if(Integer.valueOf(countrycode) >= 460 && Integer.valueOf(countrycode) <= 469)
        {
            return "????????????";
        }
        else if(Integer.valueOf(countrycode) == 471)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 474)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 475)
        {
            return "????????????";
        }
        else if(Integer.valueOf(countrycode) == 477)
        {
            return "??????????";
        }
        else if(Integer.valueOf(countrycode) == 478)
        {
            return "????????????????????";
        }
        else if(Integer.valueOf(countrycode) == 479)
        {
            return "??????-??????????";
        }
        else if(Integer.valueOf(countrycode) == 480)
        {
            return "??????????????????";
        }
        else if(Integer.valueOf(countrycode) == 481)
        {
            return "????????????????";
        }
        else if(Integer.valueOf(countrycode) == 482)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 484)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 485)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 486)
        {
            return "????????????";
        }
        else if(Integer.valueOf(countrycode) == 487)
        {
            return "??????????????????";
        }
        else if(Integer.valueOf(countrycode) == 489)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 49)
        {
            return "????????????";
        }
        else if(Integer.valueOf(countrycode) == 50)
        {
            return "????????????????????????????";
        }
        else if(Integer.valueOf(countrycode) == 520)
        {
            return "????????????";
        }
        else if(Integer.valueOf(countrycode) == 528)
        {
            return "??????????";
        }
        else if(Integer.valueOf(countrycode) == 529)
        {
            return "????????";
        }
        else if(Integer.valueOf(countrycode) == 531)
        {
            return "??????????????????";
        }
        else if(Integer.valueOf(countrycode) == 535)
        {
            return "????????????";
        }
        else if(Integer.valueOf(countrycode) == 539)
        {
            return "????????????????";
        }
        else if(Integer.valueOf(countrycode) == 54)
        {
            return "??????????????,????????????????????";
        }
        else if(Integer.valueOf(countrycode) == 560)
        {
            return "????????????????????";
        }
        else if(Integer.valueOf(countrycode) == 569)
        {
            return "??????????";
        }
        else if(Integer.valueOf(countrycode) == 590)
        {
            return "????????????";
        }
        else if(Integer.valueOf(countrycode) == 594)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 599)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 600 || Integer.valueOf(countrycode) == 601)
        {
            return "?????????? ????????????";
        }
        else if(Integer.valueOf(countrycode) == 609)
        {
            return "????????????????";
        }
        else if(Integer.valueOf(countrycode) == 611)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 613)
        {
            return "??????????";
        }
        else if(Integer.valueOf(countrycode) == 616)
        {
            return "??????????";
        }
        else if(Integer.valueOf(countrycode) == 619)
        {
            return "??????????";
        }
        else if(Integer.valueOf(countrycode) == 621)
        {
            return "??????????";
        }
        else if(Integer.valueOf(countrycode) == 622)
        {
            return "????????????";
        }
        else if(Integer.valueOf(countrycode) == 624)
        {
            return "??????????";
        }
        else if(Integer.valueOf(countrycode) == 625)
        {
            return "????????????????";
        }
        else if(Integer.valueOf(countrycode) == 626)
        {
            return "????????";
        }
        else if(Integer.valueOf(countrycode) == 627)
        {
            return "????????????";
        }
        else if(Integer.valueOf(countrycode) == 628)
        {
            return "?????????????????? ????????????";
        }
        else if(Integer.valueOf(countrycode) == 629)
        {
            return "??????";
        }
        else if(Integer.valueOf(countrycode) == 64)
        {
            return "??????????????????";
        }
        else if(Integer.valueOf(countrycode) >= 690 && Integer.valueOf(countrycode) <= 693)
        {
            return "??????????";
        }
        else if(Integer.valueOf(countrycode) == 70)
        {
            return "????????????????";
        }
        else if(Integer.valueOf(countrycode) == 729)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 73)
        {
            return "????????????";
        }
        else if(Integer.valueOf(countrycode) == 740)
        {
            return "??????????????????";
        }
        else if(Integer.valueOf(countrycode) == 741)
        {
            return "??????????????????";
        }
        else if(Integer.valueOf(countrycode) == 742)
        {
            return "????????????????";
        }
        else if(Integer.valueOf(countrycode) == 743)
        {
            return "??????????????????";
        }
        else if(Integer.valueOf(countrycode) == 744)
        {
            return "??????????-????????";
        }
        else if(Integer.valueOf(countrycode) == 745)
        {
            return "????????????";
        }
        else if(Integer.valueOf(countrycode) == 746)
        {
            return "?????????????????????????? ????????????????????";
        }
        else if(Integer.valueOf(countrycode) == 750)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 759)
        {
            return "??????????????????";
        }
        else if(Integer.valueOf(countrycode) == 76)
        {
            return "??????????????????";
        }
        else if(Integer.valueOf(countrycode) == 770)
        {
            return "????????????????";
        }
        else if(Integer.valueOf(countrycode) == 773)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 775)
        {
            return "????????";
        }
        else if(Integer.valueOf(countrycode) == 777)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 779)
        {
            return "??????????????????";
        }
        else if(Integer.valueOf(countrycode) == 780)
        {
            return "????????";
        }
        else if(Integer.valueOf(countrycode) == 784)
        {
            return "????????????????";
        }
        else if(Integer.valueOf(countrycode) == 786)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 789)
        {
            return "????????????????";
        }
        else if(Integer.valueOf(countrycode) >= 80 && Integer.valueOf(countrycode) <= 83)
        {
            return "????????????";
        }
        else if(Integer.valueOf(countrycode) == 84)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 850)
        {
            return "????????";
        }
        else if(Integer.valueOf(countrycode) == 858)
        {
            return "????????????????";
        }
        else if(Integer.valueOf(countrycode) == 859)
        {
            return "??????????";
        }
        else if(Integer.valueOf(countrycode) == 860)
        {
            return "??????????????????";
        }
        else if(Integer.valueOf(countrycode) == 867)
        {
            return "???????????????? ??????????";
        }
        else if(Integer.valueOf(countrycode) == 869)
        {
            return "????????????";
        }
        else if(Integer.valueOf(countrycode) == 87)
        {
            return "????????????????????";
        }
        else if(Integer.valueOf(countrycode) == 880)
        {
            return "?????????? ??????????";
        }
        else if(Integer.valueOf(countrycode) == 885)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 888)
        {
            return "????????????????";
        }
        else if(Integer.valueOf(countrycode) == 890)
        {
            return "??????????";
        }
        else if(Integer.valueOf(countrycode) == 893)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 899)
        {
            return "??????????????????";
        }
        else if(Integer.valueOf(countrycode) == 90 || Integer.valueOf(countrycode) == 91)
        {
            return "??????????????";
        }
        else if(Integer.valueOf(countrycode) == 93)
        {
            return "??????????????????";
        }
        else if(Integer.valueOf(countrycode) == 94)
        {
            return "?????????? ????????????????";
        }
        else if(Integer.valueOf(countrycode) == 955)
        {
            return "????????????????";
        }
        else if(Integer.valueOf(countrycode) == 958)
        {
            return "??????????";
        }
        else
        {
            return "?????????????????????? ????????????";
        }
    }

    public String Tryornot(String code)
    {
        String line = code.replace("", ",").trim();
        line = line.substring(1, line.length()-1);
        String[] items = line.split(",");
        int[] codeint = new int[items.length];
        int first = 0, second = 0, sum;

        for (int i = 0; i < items.length; i++)
        {
            codeint[i] = Integer.parseInt(items[i]);
        }

        for (int i=1;i < items.length+1;i++)
        {
            if (i% 2 == 0)
            {
                first += codeint[i-1];
            }
            else
            {
                second += codeint[i-1];
            }
        }
        first*=3;
        second -= codeint[codeint.length-1];
        sum = first + second;

        while (sum > 9)
        {
            sum-=10;
        }

        if (10 - sum == codeint[codeint.length-1])
        {
            return "?????????? ?????? ??????????????????";
        }
        else
        {
            return  "?????????? ?????? ????????????????????";
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}