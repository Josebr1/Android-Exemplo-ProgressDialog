package br.com.treinaweb.exemploprogressdialog;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void demora(View view) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("Aguarde");
        progressDialog.show();

        new Thread() {
            int total = 0;

            public void run(){
                while (total < 100){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.setProgress(total);
                        }
                    });

                    try {
                        Thread.sleep(100);
                    }catch (Exception e){

                    }

                    total++;
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Toast.makeText(getBaseContext(), "Ação concluida", Toast.LENGTH_LONG).show();
                    }
                });
            }

        }.start();
    }

}
