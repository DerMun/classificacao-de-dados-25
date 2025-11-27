package ambar;

/**
 * Classe responsável por definir a estrutura da classe Rentals (Rentables).
 */
public interface Rentables{
    String newRental(int id, int time, boolean hasLesson) throws InvalidEquipmentException;
    void saveToFile();
    String listAll();
    int size();


    void loadFile();
    boolean isEmpty();
    void clear();
}