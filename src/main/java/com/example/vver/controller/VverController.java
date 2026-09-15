package com.example.vver.controller;

import com.example.vver.model.VverState;
import com.example.vver.service.VverCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vver")
@CrossOrigin(origins = "*") // Разрешаем подключение твоему index.html
public class VverController {

    private final VverCoreService vverService;

    @Autowired
    public VverController(VverCoreService vverService) {
        this.vverService = vverService;
    }

    @GetMapping("/data")
    public VverState getLiveReport() {
        return vverService.UpdateSimulation();
    }

    @PostMapping("/az5")
    public String triggerAz5() {
        vverService.pressAZ5();
        return "Сигнал АЗ-5 принят!";
    }

    @PostMapping("/rods")
    public void changeRods(@RequestParam int position) {
        vverService.RodsSet(position);
    }
}