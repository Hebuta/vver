package com.example.vver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "vver_logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VverState {
    public double ThermalPowerMW = 3000;
    public double OneWaterPressureMPa = 15.7;
    public double TempOut = 320;
    public int rodsPosition = 100;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public double PressureSteamTwo = 6.3;
    public double TurbineRPM = 3000;
    public double electricPowerMW = 1000;
    public boolean AZ_5 = false;

    public String status = "Реактор стабилен";

    public void setOzr(int i) {
    }
}
