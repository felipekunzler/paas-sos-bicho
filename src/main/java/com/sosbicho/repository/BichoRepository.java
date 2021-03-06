package com.sosbicho.repository;

import com.sosbicho.domain.Bicho;
import com.sosbicho.domain.FilterForm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BichoRepository extends CrudRepository<Bicho, Long>, CustomBichoRepository {

}
