package br.edu.uffs.cc.Nelb;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BuscaLocalizacao extends Activity {

  //vari�veis latitude e longitude ter�o as cordenadas do usu�rio
  private EditText edLatitude;
  private EditText edLongitude;
  private Button btHome;

  @Override // M�todo onCreate, iniciada quando o a tela incial � finalizada passando o app para essa nova tela
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.busca_localizacao);
      btHome =(Button) findViewById(R.id.btHome);
      btHome.setOnClickListener (new View.OnClickListener(){
      public void onClick(View v){
        	  Intent BackHome=new Intent(BuscaLocalizacao.this, TelaInicialActivity.class);
        	  BuscaLocalizacao.this.startActivity(BackHome);
        	  BuscaLocalizacao.this.finish();
          }
      });
      edLatitude = (EditText) findViewById(R.id.edLatitude);
      edLongitude = (EditText) findViewById(R.id.edLongitude);
      startGPS();
  }

  //M�todo que faz a leitura de fato dos valores recebidos do GPS
  public void startGPS(){
      LocationManager lManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
      LocationListener lListener = new LocationListener() {
          public void onLocationChanged(Location locat) {
              updateView(locat);
          }
          public void onStatusChanged(String provider, int status, Bundle extras) {}
          public void onProviderEnabled(String provider) {}
          public void onProviderDisabled(String provider) {}
      };
      lManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, lListener);
  }

  //  M�todo que faz a atualiza��o da tela para o usu�rio.
  public void updateView(Location locat){
      Double latitude = locat.getLatitude();
      Double longitude = locat.getLongitude();

      edLatitude.setText(latitude.toString());
      edLongitude.setText(longitude.toString());
  }
}