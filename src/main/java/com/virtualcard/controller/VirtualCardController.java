package com.virtualcard.controller;


import com.virtualcard.service.VirtualCardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/vitural-card")
@RequiredArgsConstructor
public class VirtualCardController {

    private static final Logger logger = LoggerFactory.getLogger(VirtualCardController.class);

    private final VirtualCardService service;


}
