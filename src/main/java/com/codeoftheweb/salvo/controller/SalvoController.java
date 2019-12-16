package com.codeoftheweb.salvo.controller;

import com.codeoftheweb.salvo.service.SalvoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalvoController {

    @Autowired
    private SalvoService salvoService;
}
