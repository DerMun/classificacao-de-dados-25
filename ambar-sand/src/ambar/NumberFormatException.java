package ambar;

import javax.swing.JOptionPane;

/**
 * Classe responsável por tratar os erros de entrada do usuário no campo de minutos e avisá-lo qual foi o erro (exception).
 */
public class NumberFormatException extends RuntimeException{
    
    String message, error_name;

    /**
     * Coleta e armazena as informações do erro.
     * @param message Mensagem do erro.
     * @param error_name Nome do erro.
     */
    public NumberFormatException(String message, String error_name){
        this.message = message;
        this.error_name = error_name;
    }


    /**
     * Mostra pop-up do erro.
     */
    public void showMessage(){
        JOptionPane.showMessageDialog(null, message, error_name, JOptionPane.ERROR_MESSAGE);
    }

}