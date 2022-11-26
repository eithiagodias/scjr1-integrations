package br.com.fiap.agrodrones.microservice.managers;

import br.com.fiap.agrodrones.microservice.dtos.DroneInfoDTO;

import java.util.Collection;
import java.util.HashMap;

import java.util.Map;

public class DronesManager {
    private static DronesManager instance;
    private Map<Integer, DroneInfoDTO> drones;

    public Map<Integer, DroneInfoDTO> getDrones() {
        return drones;
    }

    public void setDrones(Map<Integer, DroneInfoDTO> drones) {
        this.drones = drones;
    }

    DronesManager() {
        drones = new HashMap<Integer, DroneInfoDTO>();
    }

    public static DronesManager getInstance() {
        if (instance == null) {
            instance = new DronesManager();
        }
        return instance;
    }

    public void addDrone(DroneInfoDTO drone) {
        Integer droneId = drone.getId_drone();
        if(!this.drones.containsKey(droneId)) {
            this.drones.put(droneId, drone);
        }
    }

    public DroneInfoDTO getDrone(Integer droneId) {
        return this.drones.get(droneId);
    }

    public void removeDrone(Integer droneId) {
        this.drones.remove(droneId);
    }

    public DroneInfoDTO random() {
        Collection<DroneInfoDTO> coll = this.getDrones().values();
        int num = (int) (Math.random() * coll.size());
        int random_temperatura = (int)Math.floor(Math.random()*(40-(-25)+1)+(-25));
        int random_umidade = (int)Math.floor(Math.random()*(100-0+1)+0);
        for(DroneInfoDTO d: coll) if (--num < 0) {
            d.setTemperatura(random_temperatura);
            d.setUmidade(random_umidade);
            return d;
        };
        throw new AssertionError();
    }

    public void updateDrone(Integer droneId, DroneInfoDTO drone) {
        if(this.drones.containsKey(droneId)) {
            DroneInfoDTO d = this.drones.get(droneId);
            d.setLatitude(drone.getLatitude());
            d.setLongitude(drone.getLongitude());
            d.setTemperatura(drone.getTemperatura());
            d.setUmidade(drone.getUmidade());
            d.setAtivar_rastreamento(drone.getAtivar_rastreamento());
            this.drones.replace(droneId, d);
        }
    }
}
