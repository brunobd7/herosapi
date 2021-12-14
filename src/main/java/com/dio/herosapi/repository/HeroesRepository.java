package com.dio.herosapi.repository;

import com.dio.herosapi.document.HeroesModel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface HeroesRepository extends CrudRepository<HeroesModel, String> {
}
