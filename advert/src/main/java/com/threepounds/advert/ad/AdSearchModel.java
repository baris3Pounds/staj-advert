package com.threepounds.advert.ad;


import java.math.BigDecimal;
import lombok.Data;

@Data
public class AdSearchModel {
  private String title;
  private String desc;
  private BigDecimal price;
  private double latitude;
  private double longitude;

}
