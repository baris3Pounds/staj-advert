package com.threepounds.advert.config;

import org.springframework.stereotype.Component;

@Component
public class DistanceAd {
    //Haversine Formula

    private static final int EARTH_RADIUS_KM = 6371;

    public static double calculateDistance(double enlem_baslangic, double boylam_baslangic , double enlem_bitis , long boylam_bitis){
        double d_enlem = Math.toRadians(enlem_bitis-enlem_baslangic);
        double d_boylam = Math.toRadians(boylam_bitis - boylam_baslangic);

        double a = Math.sin(d_enlem /2) * Math.sin(d_enlem /2)
                + Math.cos(Math.toRadians(enlem_baslangic)) * Math.cos(Math.toRadians(boylam_baslangic))
                * Math.sin(d_boylam / 2) *  Math.sin(d_boylam / 2);
        // Merkez açi
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return  EARTH_RADIUS_KM * c;
    }
}
