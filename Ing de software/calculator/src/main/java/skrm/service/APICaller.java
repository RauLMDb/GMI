package skrm.service;

import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skrm.controller.request.Datos;

import java.io.IOException;

@Service
public class APICaller {

    @Autowired
    private OkHttpClient client;

    public String getInfo() throws IOException {
        Request request = new Request.Builder().url("http://ismi.fi.upm.es:5000/").build();
        Response response = client.newCall(request).execute();
        String json = response.body().string();

        if (!response.isSuccessful())
            return "ERROR";

        // TipoRetorno returned = new GsonBuilder().setPrettyPrinting().create().fromJson(json, TipoRetorno.class);
        String returned = json.toString();

        response.close();

        return returned;
    }

    public String sendInfo(Datos datos) throws IOException {
        JSONObject json = new JSONObject(datos);

        RequestBody body = RequestBody.create(json.toString(), MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder().url("http://ismi.fi.upm.es:5000/post-data").post(body).build();

        Call call = client.newCall(request);
        Response response = call.execute();

        if(!response.isSuccessful()){
            return "ERROR";
        }
        return response.body().string();
	}
}
