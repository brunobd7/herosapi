package com.dio.herosapi.service;

import com.dio.herosapi.document.HeroesModel;
import com.dio.herosapi.repository.HeroesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroesService {

    @Autowired
    private HeroesRepository heroesRepository;

//    public HeroesService(HeroesRepository heroesRepository){
//        this.heroesRepository = heroesRepository;
//    }


    public Flux<HeroesModel> findaAll(){
        return Flux.fromIterable(this.heroesRepository.findAll());
    }

    public Mono<HeroesModel> findByHeroId(String id){
        return  Mono.justOrEmpty(this.heroesRepository.findById(id));
    }

    public Mono<HeroesModel> saveHero(HeroesModel heroesModel){
        return Mono.justOrEmpty(this.heroesRepository.save(heroesModel));
    }

    public Mono<Boolean> deleteByHeroId(String id){

        this.heroesRepository.deleteById(id);

        return Mono.just(true);

    }


}
