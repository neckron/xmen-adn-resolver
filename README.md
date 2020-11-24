#ML Examen

## Contexto

 - Encontrar si un arreglo de cadenas contiene ADN Mutante, dados ciertos criterios.
 - Obtener la relación entre la cantidad de mutantes y humanos dado que se ha validado el adn de cada individuo previamente.
 
## Solución




## Guía de ejecución (local)

 - dar de alta base de datos potsgresql (instalador o docker)
 - crear una nueva base de datos
 - ejecutar el script de creación de los objetos de bdatos usando este script :
 
```sql

CREATE SEQUENCE public.dna_mutant_validation_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.dna_mutant_validation_seq
    OWNER TO docker; -- cambiar acorde el ambiente

CREATE TABLE public.dna_mutant_validation
(
    id bigint NOT NULL DEFAULT nextval('dna_mutant_validation_seq'::regclass),
    validation_algorithm character varying(20) COLLATE pg_catalog."default" NOT NULL,
    dna_sequence character varying COLLATE pg_catalog."default",
    individual_name character varying(30) COLLATE pg_catalog."default",
    is_mutant boolean NOT NULL,
    CONSTRAINT dna_mutant_validation_pkey PRIMARY KEY (id),
    CONSTRAINT unique_validation_by_algorithm UNIQUE (validation_algorithm, dna_sequence)
)

TABLESPACE pg_default;

ALTER TABLE public.dna_mutant_validation
    OWNER to docker; -- cambiar acorde el ambiente

CREATE INDEX idx_dna_seuqnce_algorithm
    ON public.dna_mutant_validation USING btree
    (dna_sequence COLLATE pg_catalog."default" ASC NULLS LAST, validation_algorithm COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;
```
 - modificar `application.properties` con la información de la base de datos recién creada.
 
 - en la raiz del proyecto ejecutar:
    - validación tests : `mvn clean test`
    - validación cobertura : `mvn clean verify`
    - correr proyecto localmente: `mvn spring-boot:run`
    
 - una vez se ha desplegado éxitosamente, usar este [Postman Collection](https://www.getpostman.com/collections/0498f3d3b7769f7374bb) para lanzar request al API.
 
 ## Cloud
 
 - Para desplegar una nueva versión se debe hacer push a la rama `master`. Heroku se encarga de correr los tests y deplegar la app automáticamente.
 - endpoint : xmen-dnaresolution.herokuapp.com
 
 ## API Documentación
 
 - [local](http://localhost:8999/swagger-ui.html)
 - [cloud](http://xmen-dnaresolution.herokuapp.com/swagger-ui.html)