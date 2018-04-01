package com.sosbicho.repository;

import com.sosbicho.domain.Bicho;
import com.sosbicho.domain.FilterForm;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomBichoRepository {

    List<Bicho> findByFilter(FilterForm filter);

}
