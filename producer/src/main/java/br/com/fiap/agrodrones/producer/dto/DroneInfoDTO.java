package br.com.fiap.agrodrones.producer.dto;

public class DroneInfoDTO {

    private Integer id_drone;
    private double latitude;
    private double longitude;
    private Integer temperatura;
    private Integer umidade;
    private boolean ativar_rastreamento;

    public Integer getId_drone() {
        return id_drone;
    }

    public void setId_drone(Integer id_drone) {
        this.id_drone = id_drone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Integer getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Integer temperatura) {
        this.temperatura = temperatura;
    }

    public Integer getUmidade() {
        return umidade;
    }

    public void setUmidade(Integer umidade) {
        this.umidade = umidade;
    }

    public boolean isAtivar_rastreamento() {
        return ativar_rastreamento;
    }

    public void setAtivar_rastreamento(boolean ativar_rastreamento) {
        this.ativar_rastreamento = ativar_rastreamento;
    }

}
