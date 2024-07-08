package com.threepounds.advert.ad;

import com.threepounds.advert.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdService {

    private  AdRepository adRepository;

    public AdService(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    public Ad save(Ad ad) {
        ad.setActive(true);
        return adRepository.save(ad);
    }

    public List<Ad> list(){
        return adRepository.findAll();
    }

    public List<Ad> listByTitle(String title){return adRepository.findByTitle(title);}

    public Optional<Ad> getById(UUID adId){return adRepository.findById(adId);}

    public void deleteAd(Ad ad){adRepository.delete(ad);}
}
