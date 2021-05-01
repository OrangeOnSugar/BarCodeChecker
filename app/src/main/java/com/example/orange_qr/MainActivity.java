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
            resultpod.setText("Подлинность: " + Tryornot(code));
            resulcountry.setText("Страна: " + Contry(code));
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
            return "США или Канада";
        }
        else if(Integer.valueOf(countrycode) >= 30 && Integer.valueOf(countrycode) <= 37)
        {
            return "Франция";
        }
        else if(Integer.valueOf(countrycode) == 380)
        {
            return "Франция";
        }
        else if(Integer.valueOf(countrycode) == 383)
        {
            return "Словения";
        }
        else if(Integer.valueOf(countrycode) == 385)
        {
            return "Хорватия";
        }
        else if(Integer.valueOf(countrycode) == 387)
        {
            return "Босния или Герцеговина";
        }
        else if(Integer.valueOf(countrycode) >= 400 && Integer.valueOf(countrycode) <= 440)
        {
            return "Германия";
        }
        else if(Integer.valueOf(countrycode) >= 45 && Integer.valueOf(countrycode) <= 49)
        {
            return "Япония";
        }
        else if(Integer.valueOf(countrycode) >= 460 && Integer.valueOf(countrycode) <= 469)
        {
            return "Россия";
        }
        else if(Integer.valueOf(countrycode) == 471)
        {
            return "Тайвань";
        }
        else if(Integer.valueOf(countrycode) == 474)
        {
            return "Эстония";
        }
        else if(Integer.valueOf(countrycode) == 475)
        {
            return "Латвия";
        }
        else if(Integer.valueOf(countrycode) == 477)
        {
            return "Литва";
        }
        else if(Integer.valueOf(countrycode) == 478)
        {
            return "Узбекистан";
        }
        else if(Integer.valueOf(countrycode) == 479)
        {
            return "Шри-Ланка";
        }
        else if(Integer.valueOf(countrycode) == 480)
        {
            return "Филиппины";
        }
        else if(Integer.valueOf(countrycode) == 481)
        {
            return "Беларусь";
        }
        else if(Integer.valueOf(countrycode) == 482)
        {
            return "Украина";
        }
        else if(Integer.valueOf(countrycode) == 484)
        {
            return "Молдова";
        }
        else if(Integer.valueOf(countrycode) == 485)
        {
            return "Армения";
        }
        else if(Integer.valueOf(countrycode) == 486)
        {
            return "Грузия";
        }
        else if(Integer.valueOf(countrycode) == 487)
        {
            return "Казахстан";
        }
        else if(Integer.valueOf(countrycode) == 489)
        {
            return "Гонконг";
        }
        else if(Integer.valueOf(countrycode) == 49)
        {
            return "Япония";
        }
        else if(Integer.valueOf(countrycode) == 50)
        {
            return "Великобритания";
        }
        else if(Integer.valueOf(countrycode) == 520)
        {
            return "Греция";
        }
        else if(Integer.valueOf(countrycode) == 528)
        {
            return "Ливан";
        }
        else if(Integer.valueOf(countrycode) == 529)
        {
            return "Кипр";
        }
        else if(Integer.valueOf(countrycode) == 531)
        {
            return "Македония";
        }
        else if(Integer.valueOf(countrycode) == 535)
        {
            return "Мальта";
        }
        else if(Integer.valueOf(countrycode) == 539)
        {
            return "Ирландия";
        }
        else if(Integer.valueOf(countrycode) == 54)
        {
            return "Бельгия,Люксембург";
        }
        else if(Integer.valueOf(countrycode) == 560)
        {
            return "Португалия";
        }
        else if(Integer.valueOf(countrycode) == 569)
        {
            return "Дания";
        }
        else if(Integer.valueOf(countrycode) == 590)
        {
            return "Польша";
        }
        else if(Integer.valueOf(countrycode) == 594)
        {
            return "Румыния";
        }
        else if(Integer.valueOf(countrycode) == 599)
        {
            return "Венгрия";
        }
        else if(Integer.valueOf(countrycode) == 600 || Integer.valueOf(countrycode) == 601)
        {
            return "Южная Африка";
        }
        else if(Integer.valueOf(countrycode) == 609)
        {
            return "Маврикий";
        }
        else if(Integer.valueOf(countrycode) == 611)
        {
            return "Марокко";
        }
        else if(Integer.valueOf(countrycode) == 613)
        {
            return "Алжир";
        }
        else if(Integer.valueOf(countrycode) == 616)
        {
            return "Кения";
        }
        else if(Integer.valueOf(countrycode) == 619)
        {
            return "Тунис";
        }
        else if(Integer.valueOf(countrycode) == 621)
        {
            return "Сирия";
        }
        else if(Integer.valueOf(countrycode) == 622)
        {
            return "Египет";
        }
        else if(Integer.valueOf(countrycode) == 624)
        {
            return "Ливия";
        }
        else if(Integer.valueOf(countrycode) == 625)
        {
            return "Иордания";
        }
        else if(Integer.valueOf(countrycode) == 626)
        {
            return "Иран";
        }
        else if(Integer.valueOf(countrycode) == 627)
        {
            return "Кувейт";
        }
        else if(Integer.valueOf(countrycode) == 628)
        {
            return "Судовская Аравия";
        }
        else if(Integer.valueOf(countrycode) == 629)
        {
            return "ОАЭ";
        }
        else if(Integer.valueOf(countrycode) == 64)
        {
            return "Финляндия";
        }
        else if(Integer.valueOf(countrycode) >= 690 && Integer.valueOf(countrycode) <= 693)
        {
            return "Китай";
        }
        else if(Integer.valueOf(countrycode) == 70)
        {
            return "Норвегия";
        }
        else if(Integer.valueOf(countrycode) == 729)
        {
            return "Израиль";
        }
        else if(Integer.valueOf(countrycode) == 73)
        {
            return "Швеция";
        }
        else if(Integer.valueOf(countrycode) == 740)
        {
            return "Гватемала";
        }
        else if(Integer.valueOf(countrycode) == 741)
        {
            return "Сальвадор";
        }
        else if(Integer.valueOf(countrycode) == 742)
        {
            return "Гондурас";
        }
        else if(Integer.valueOf(countrycode) == 743)
        {
            return "Никарагуа";
        }
        else if(Integer.valueOf(countrycode) == 744)
        {
            return "Коста-Рика";
        }
        else if(Integer.valueOf(countrycode) == 745)
        {
            return "Панама";
        }
        else if(Integer.valueOf(countrycode) == 746)
        {
            return "Доминиканская Республика";
        }
        else if(Integer.valueOf(countrycode) == 750)
        {
            return "Мексика";
        }
        else if(Integer.valueOf(countrycode) == 759)
        {
            return "Венесуэла";
        }
        else if(Integer.valueOf(countrycode) == 76)
        {
            return "Швейцария";
        }
        else if(Integer.valueOf(countrycode) == 770)
        {
            return "Колумбия";
        }
        else if(Integer.valueOf(countrycode) == 773)
        {
            return "Уругвай";
        }
        else if(Integer.valueOf(countrycode) == 775)
        {
            return "Перу";
        }
        else if(Integer.valueOf(countrycode) == 777)
        {
            return "Боливия";
        }
        else if(Integer.valueOf(countrycode) == 779)
        {
            return "Аргентина";
        }
        else if(Integer.valueOf(countrycode) == 780)
        {
            return "Чили";
        }
        else if(Integer.valueOf(countrycode) == 784)
        {
            return "Парагвай";
        }
        else if(Integer.valueOf(countrycode) == 786)
        {
            return "Эквадор";
        }
        else if(Integer.valueOf(countrycode) == 789)
        {
            return "Бразилия";
        }
        else if(Integer.valueOf(countrycode) >= 80 && Integer.valueOf(countrycode) <= 83)
        {
            return "Италия";
        }
        else if(Integer.valueOf(countrycode) == 84)
        {
            return "Испания";
        }
        else if(Integer.valueOf(countrycode) == 850)
        {
            return "Куба";
        }
        else if(Integer.valueOf(countrycode) == 858)
        {
            return "Словакия";
        }
        else if(Integer.valueOf(countrycode) == 859)
        {
            return "Чехия";
        }
        else if(Integer.valueOf(countrycode) == 860)
        {
            return "Югославия";
        }
        else if(Integer.valueOf(countrycode) == 867)
        {
            return "Северная Корея";
        }
        else if(Integer.valueOf(countrycode) == 869)
        {
            return "Турция";
        }
        else if(Integer.valueOf(countrycode) == 87)
        {
            return "Нидерланды";
        }
        else if(Integer.valueOf(countrycode) == 880)
        {
            return "Южная Корея";
        }
        else if(Integer.valueOf(countrycode) == 885)
        {
            return "Таиланд";
        }
        else if(Integer.valueOf(countrycode) == 888)
        {
            return "Сингапур";
        }
        else if(Integer.valueOf(countrycode) == 890)
        {
            return "Индия";
        }
        else if(Integer.valueOf(countrycode) == 893)
        {
            return "Вьетнам";
        }
        else if(Integer.valueOf(countrycode) == 899)
        {
            return "Индонезия";
        }
        else if(Integer.valueOf(countrycode) == 90 || Integer.valueOf(countrycode) == 91)
        {
            return "Австрия";
        }
        else if(Integer.valueOf(countrycode) == 93)
        {
            return "Австралия";
        }
        else if(Integer.valueOf(countrycode) == 94)
        {
            return "Новая Зеландия";
        }
        else if(Integer.valueOf(countrycode) == 955)
        {
            return "Малайзия";
        }
        else if(Integer.valueOf(countrycode) == 958)
        {
            return "Макао";
        }
        else
        {
            return "Неизвестная страна";
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
            return "штрих код подлинный";
        }
        else
        {
            return  "штрих код поддельный";
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