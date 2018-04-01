package com.sosbicho.repository;

import com.sosbicho.domain.Bicho;
import com.sosbicho.domain.FilterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

import static org.thymeleaf.util.StringUtils.*;

public class CustomBichoRepositoryImpl implements CustomBichoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Bicho> findByFilter(FilterForm filter) {
        String sql = "SELECT b FROM Bicho b WHERE 1 = 1";
        Map<String, Object> parameters = new HashMap<>();

        if (!isEmpty(filter.getSpecies())) {
            sql += " AND LOWER(b.species) like LOWER(:species)";
            parameters.put("species", "%" + filter.getSpecies() + "%");
        }

        if (!isEmpty(filter.getSize())) {
            sql += " AND LOWER(b.size) like LOWER(:size)";
            parameters.put("size", "%" + filter.getSize() + "%");
        }

        if (filter.getAgeGreater() != null) {
            sql += " AND b.age >= :ageGreater";
            parameters.put("ageGreater", filter.getAgeGreater());
        }

        if (filter.getAgeLess() != null) {
            sql += " AND b.age <= :ageLess";
            parameters.put("ageLess", filter.getAgeLess());
        }

        Query query = em.createQuery(sql);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }

}
