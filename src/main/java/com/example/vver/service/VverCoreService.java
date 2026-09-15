package com.example.vver.service;

import com.example.vver.model.VverState;
import jakarta.annotation.PostConstruct;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class VverCoreService {
    private VverState currentState = new VverState();

    public VverState getCurrentState() {
        return currentState;
    }

    public VverState UpdateSimulation() {
        if (currentState.isAZ_5()) {
            currentState.setThermalPowerMW(Math.max(0, currentState.getThermalPowerMW() * 0.4));
            currentState.setRodsPosition(Math.max(0, currentState.getRodsPosition() - 34));
            currentState.setStatus("AZ-5 АВАРИЙНЫЙ СБРОС СУЗ. РЕАКТОР ЗАГЛУШЕН");
        } else {
            double targetPower = 3000 * (currentState.getRodsPosition() / 100);
            currentState.setThermalPowerMW(currentState.getThermalPowerMW() + (targetPower - currentState.getThermalPowerMW()) * 0.15);
            currentState.setStatus(currentState.getRodsPosition() < 10 ? "⚠️ РЕАКТОР НА МИНИМАЛЬНОМ УРОВНЕ" : "✅ СТАНЦИЯ В НОРМЕ");
        }
        currentState.setTempOut(284.0 + (currentState.getThermalPowerMW() / 3000.0) * 36.0);
        currentState.setOneWaterPressureMPa(currentState.getThermalPowerMW() > 0 ? 15.7 : 0.1);

        double targetSteamPressure = (currentState.getTempOut() / 320.0) * 6.3;
        currentState.setPressureSteamTwo(Math.max(0.1, targetSteamPressure));

        currentState.setTurbineRPM(currentState.getPressureSteamTwo() > 1.0 ? 3000.0 : 0.0);
        currentState.setElectricPowerMW(currentState.getThermalPowerMW() * 0.333);

        return currentState;
    }

    public void pressAZ5() {
        currentState.setAZ_5(true);
    }

    public void RodsSet(int position) {
        if (!currentState.isAZ_5()) {
            currentState.setRodsPosition(Math.min(100, Math.max(0, currentState.getRodsPosition())));
        }
    }
    @PostConstruct
    public void initReactorBeforeWork() {
        currentState.setOzr(35);
    }
}
