    package com.festivos.api.dominio.entidades;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.Table;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Entity
    @Table(name = "Tipo")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Tipo {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        
        private String tipo;
    }