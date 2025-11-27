package ambar;

import javax.swing.JOptionPane;

/**
 * Classe responsável por tratar os erros de entrada do usuário em relação ao id, e avisá-lo qual foi o erro (exception).
 */
public class InvalidEquipmentException extends RuntimeException{
    int id;
    String message;

    /**
     * Coleta e armazena as informações do erro.
     * @param id Número de id digitado erroneamente.
     * @param _class Mensagem do erro.
     */
    public InvalidEquipmentException(int id, String _class){
        this.id = id;
        message = _class;
    }

    /**
     * Mostra pop-up do erro.
     */
    public void showMessage(){
        JOptionPane.showMessageDialog(null, message + id, "EQUIPMENT ERROR", JOptionPane.ERROR_MESSAGE);
    }

}