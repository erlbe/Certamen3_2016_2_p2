package cl.telematica.android.certamen3_p2;

import android.os.AsyncTask;

import org.json.JSONObject;

/**
 * Created by franciscocabezas on 11/18/16.
 */

public class MyAsyncTaskExecutor {

    private static MyAsyncTaskExecutor instance;

    public static MyAsyncTaskExecutor getInstance() {
        if(instance == null) {
            instance = new MyAsyncTaskExecutor();
        }
        return instance;
    }

    public void executeMyAsynctask(final MainActivity.Listener listener, final JSONObject objectToSend) {
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute(){

            }

            @Override
            protected String doInBackground(Void... params) {
                String resultado = new HttpServerConnection().connectToServer("http://192.168.71.2:8080/api/", 15000, "POST", objectToSend);
                return resultado;
            }

            @Override
            protected void onPostExecute(String result) {
                if(result != null){
                    System.out.println(result);

                    listener.onSuccess(result);
                }
            }
        };

        task.execute();
    }

}
