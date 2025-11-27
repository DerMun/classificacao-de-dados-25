package ambar;

import java.io.Serializable;

/**
 * Contém as informações gerais de um alugel.
 */
public class Rental implements Serializable{

    private static long nextId = 0;

    private long contract;
    private int time;
    private double price;

    private Equipment equip;//usado para gerar informações toString e getPrice. e também polimorfismo


    /**
     * Cria um novo aluguel.
     * @param time Tempo de aluguel em minutos.
     * @param equip Equipamento alugado.
     */
    public Rental(int time, Equipment equip){
        this.contract = nextId++;
        this.time = time; 
        this.price = equip.getValue(time);
        this.equip = equip;
    }


    /**
     * Retorna o valor total do aluguel.
     * @return Valor total do aluguel.
     */
    public double totalPrice(){
        return price;
    }


    /**
     * Retorna String com todas as informações de um aluguel.
     */
    @Override
    public String toString(){
        String rental_str = "INFORMAÇÕES GERAIS DO ALUGUEL:\nNúmero do contrato: " + contract + "\nDuração do aluguel (min): " + time + "\nPreço total: " + totalPrice() + "\n\n" + equip.toString();
        return rental_str;
    }
}