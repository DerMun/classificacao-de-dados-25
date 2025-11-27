package ambar;

import java.io.Serializable;

/**
 * Classe responsável por definir as informações principais de um equipamento.
 */
public enum EquipmentAndValues implements Serializable{

    ONE("jet ski", 50.0, 30.0),//com aula?
    TWO("barco pontão", 40.0, 30.0),
    THREE("barco a remo", 15.0, 20.0),
    FOUR("canoa", 12.0, 20.0),
    FIVE("caiaque", 10.0, 20.0),
    
    SIX("cadeira de praia", 2.0, 5.0),//definitivamente sem aula
    SEVEN("guarda-sol", 1.0, 5.0),
    EIGHT("gazebo", 3.0, 7.0);

    private String description;
    private double basicTax;
    private double taxPerHour;

    /**
     * Cria novos equipamentos (enums).
     * @param desc Nome do equipamento.
     * @param tax Taxa básica do equipamento.
     * @param perHour Taxa por hora do equipamento.
     */
    EquipmentAndValues(String desc, double tax, double perHour){
        this.description = desc;
        this.basicTax = tax;
        this.taxPerHour = perHour;
    }

    /**
     * Retorna descrição (nome) do equipamento.
     * @return String descrição do equipamento.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Retorna taxa básica do equipamento.
     * @return Double da taxa básica do equipamento.
     */
    public double getBasicTax(){
        return basicTax;
    }

    /**
     * Retorna taxa por hora do equipamento.
     * @return Double da taxa por hora do equipamento.
     */
    public double getTaxPerHour(){
        return taxPerHour;
    }
}