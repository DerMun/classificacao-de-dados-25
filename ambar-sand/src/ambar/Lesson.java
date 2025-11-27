package ambar;

/**
 * Classe responsável por criar um equipamento com aula e valor atualizado.
 */
public class Lesson extends EquipmentWithLesson{
    
    private final double value = 20.0;

    /**
     * Cria um equipamento com aula.
     * @param equip Passa o tipo de equipamento para a sua super classe.
     */
    public Lesson(Equipment equip){
        super(equip.getType());

    }


    /**
     * Retorna o valor do aluguel com aula.
     */
    @Override
    public double getValue(int time){
        return ( super.getValue(time) + value );//adiciona à aula ao valor total
    }

}