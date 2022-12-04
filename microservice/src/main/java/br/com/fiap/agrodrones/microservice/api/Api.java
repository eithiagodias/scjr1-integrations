package br.com.fiap.agrodrones.microservice.api;

import br.com.fiap.agrodrones.microservice.dtos.DroneInfoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Api {

    public static Response send(DroneInfoDTO drone) throws GetInfoException {

        Response response = new Response("Não foi possivel enviar as infos do drone. Por favor, tente novamente.", 0);

        try {
            URL api = new URL("http://localhost:8083/agro-drones/api/drone-info");
            HttpURLConnection connection = (HttpURLConnection) api.openConnection();
            connection.setDoOutput( true );
            connection.setInstanceFollowRedirects( false );
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(drone);

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String chunk;
            StringBuffer content = new StringBuffer();
            while((chunk = buffer.readLine()) != null) {
                content.append(chunk);
            }
            buffer.close();
            connection.disconnect();

            response = (new Response(content.toString(), (int) connection.getResponseCode()));

        } catch (Exception e) {
            throw new GetInfoException("Erro ao tentar enviar infos. Tente novamente.");
        }

        return response;
    }
}
