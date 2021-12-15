package com.dio.herosapi.controller;

import com.dio.herosapi.constants.HeroesConstant;
import com.dio.herosapi.document.HeroesModel;
import com.dio.herosapi.repository.HeroesRepository;
import com.dio.herosapi.service.HeroesService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
//@RequestMapping("/heroes")
public class HeroesController {

    //USING OO CONSTRUCTOR
//    HeroesService heroesService;
//    HeroesRepository heroesRepository;
//
//    public HeroesController(HeroesService heroesService, HeroesRepository heroesRepository){
//        this.heroesService =heroesService;
//        this.heroesRepository = heroesRepository;
//    }

    //USING DI and IoC
    @Autowired
    HeroesService heroesService;
    @Autowired
    HeroesRepository heroesRepository;

    private static final Logger log = LoggerFactory.getLogger(HeroesController.class);

    //USING CONSTANT TO INDENTIFY ENDPOINT , LIKE A "/heroes"
    @GetMapping(HeroesConstant.HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.OK)
    public Flux<HeroesModel> getAllHeroes(){

        log.info("requesting the list of all heroes");
        return heroesService.findaAll();
    }

    @GetMapping(HeroesConstant.HEROES_ENDPOINT_LOCAL+"/{id}")
    public Mono<ResponseEntity<HeroesModel>> getHeroById(@PathVariable String id){

        log.info("requesting hero of id {} =", id);
        return heroesService.findByHeroId(id)
                .map( monoHeroModel -> new ResponseEntity<>(monoHeroModel, HttpStatus.OK) )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping(HeroesConstant.HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<HeroesModel> createHero(@RequestBody HeroesModel heroesModel){

        log.info("a new hero was created");
        return heroesService.saveHero(heroesModel);

    }


    @DeleteMapping(HeroesConstant.HEROES_ENDPOINT_LOCAL+"/{id}")
    @ResponseStatus(HttpStatus.CONTINUE)
    public Mono<HttpStatus> deleteHeroById(@PathVariable String id){

        log.info("requesting delete a hero of id {}", id);
        heroesService.deleteByHeroId(id);
        log.info("hero of id {} as deleted", id);

        return Mono.just(HttpStatus.CONTINUE);
    }



}
