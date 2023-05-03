package com.wcm.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.wcm.service.QrCodeService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/qrcode")
public class QrCodeController {
	private static final String QR_CODE_IMAGE_PATH = "C:\\Users\\2000078079\\Desktop\\qrcodes";
	@Autowired
	private QrCodeService qrCodeService;

    @GetMapping("/")
    public String getQRCode(){
        String text="Tuch re Bhava";

        try {
            // Generate and Save Qr Code Image in static/image folder
            qrCodeService.generateQRCodeImage(text,250,250,QR_CODE_IMAGE_PATH);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
		return "QR code generated";
    }
}
