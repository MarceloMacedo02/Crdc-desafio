package br.com.areadigital.crdcdesafio.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.areadigital.crdcdesafio.entities.CnabDat;

@Repository
public interface CnabDatRepository extends MongoRepository<CnabDat, String> {
    Optional<CnabDat> findByHeaderCompanyId(String companyId);
}