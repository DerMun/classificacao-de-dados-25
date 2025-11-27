package ambar;

/**
 * Classe responsável por abstrair Equipament e define um com aula, mas sem valor.
 */
public class EquipmentWithLesson extends Equipment{

    /**
     * Define que terá aula e pode criar um equipamento.
     * @param id Tipo do equipamento para a classe Equipment.
     */
    public EquipmentWithLesson(int id){
        super(id);
        setCanHaveLesson(true);
    }

    /**
     * Retorna valor do aluguel sem aula.
     */
    @Override
    public double getValue(int time){
        return super.getValue(time);
    }

}