package com.evolv.care.app.controller;

import com.evolv.care.app.aop.Secure;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API to Pinch the Server to wakeup by prevent it from
 * spin down
 */
@RestController
@RequestMapping("/api/schedule")
public class UtilController {

    /**
     * Dummy API to make the server alive
     * @return Pinch message
     */
    @Secure
    @GetMapping("/pinch")
    public String pinchToAlive(){
        return "I am Alive, and not tobe spin down";
    }
}

