package ambar;

/**
 * Classe responsável por abstrair Equipament e define um sem aula.
 */
public class EquipmentWithoutLesson extends Equipment{

    /**
     * Cria um equipamento sem aula.
     * @param id Tipo do equipamento para a classe Equipment.
     */
    public EquipmentWithoutLesson(int id){
        super(id);
        setCanHaveLesson(false);
    }

    /**
     * Retorna valor do aluguel sem aula.
     */
    @Override
    public double getValue(int time){
        return super.getValue(time);
    }
    
}