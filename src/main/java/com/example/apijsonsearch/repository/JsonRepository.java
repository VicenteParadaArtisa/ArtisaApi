package com.example.apijsonsearch.repository;

import com.example.apijsonsearch.model.JsonDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class JsonRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public JsonRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Búsqueda genérica por campo anidado.
     * @param campo Ruta del campo, puede ser anidada (ej: "equipo.dominio")
     * @param valor Valor exacto a buscar
     * @return JsonDoc encontrado o null
     */
    public JsonDoc buscarPorCampo(String campo, String valor) {
        Query query = new Query(Criteria.where(campo).is(valor));
        return mongoTemplate.findOne(query, JsonDoc.class, "Jsons");
    }

    /**
     * Búsqueda específica por dominio de equipo.
     * Equivalente a buscar campo "equipo.dominio"
     * @param dominio Patente o identificador
     * @return JsonDoc encontrado o null
     */
    public JsonDoc buscarPorDominio(String dominio) {
        return buscarPorCampo("equipo.dominio", dominio);
    }
}
