package ambar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * classe responsável por gerenciar os aluguéis. cria novos, armazena em lista e salva em arquivo serializado.
 */
public class Rentals implements Serializable, Rentables{//ObjectInputValidation
    private ArrayList<Rental> rentals;


    /**
     * Inicializa ArrayList de aluguéis.
     */
    public Rentals(){
        this.rentals = new ArrayList<>();
    }


    /**
     * Usa o método isEmpty da ArrayList para que ele possa ser usado fora dessa classe que contém a lista
     * @return Se a lista está vazia (true) ou não (false).
     */
    public boolean isEmpty(){//como a lista rentals é "interna" esse método precisa ser implementado no Rentals para poder usar isEmpty externamente (main)
        return rentals.isEmpty();
    }


    /**
     * Usa o método clear da ArrayList para que ele possa ser usado fora dessa classe que contém a lista
     */
    public void clear(){
        rentals.clear();
    }


    /**
     * Cria um novo aluguel e adiciona à lista ou dispara uma exceção caso o id não exista.
     * @param id Determina qual será o equipamento alugado.
     * @param time Tempo de aluguel em minutos.
     * @param hasLesson Determina se o aluguel terá aula para utilizar o equipamento.
     * @return String com informações do aluguel.
     * @throws InvalidEquipmentException
     */
    public String newRental(int id, int time, boolean hasLesson) throws InvalidEquipmentException{

        if(hasLesson == true){//Rental instânciado com base em aula true ou false
            EquipmentWithLesson equipment_with_lesson = new EquipmentWithLesson(id);//passa o id para instanciar um equipamento especifico do enum
            Lesson less = new Lesson(equipment_with_lesson);//informa que há aula onde lesson é a abstração de equipamento
            Rental rental = new Rental(time, less);
            rentals.add(rental);
            return rental.toString();//retorna string para servir como mensagem no JOptionPane
        }
        else{
            EquipmentWithoutLesson equipment_without_lesson = new EquipmentWithoutLesson(id);//unica mudança é que não tem aula
            Rental rental = new Rental(time, equipment_without_lesson);
            rentals.add(rental);
            return rental.toString();
        }
        
    }

    
    /**
     * Salva a lista rentals em bytes (serialização).
     */
    public void saveToFile(){
        try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("src/ambar/ambar.byte"))){//cria OOS para escrever. FOS abre arquivo para para escrever bytes
            writer.writeObject(rentals);//escreve rentals no arquivo
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!", "Salvamento", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch(IOException e){
            e.printStackTrace();
        }    
    }


    /**
     * Lê e deserializa um arquivo serializado chamado "ambar.byte" para uma lista de Rental.
     */
    public void loadFile(){//carrega dados para a lista rentals

        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream("src/ambar/ambar.byte"))){//cria OIS para ler objetos do arquivo
            rentals.clear();//limpa a lista atual antes de carregar os dados do arquivo ambar
            
            rentals.addAll((ArrayList<Rental>) reader.readObject());//lê os objetos serializados do arquivo e adiciona à lista rentals

            //reader.registerValidation(this, 0);//registra o objeto para validação. intância atual "rentals", validação imediata (prioridade 0).
            JOptionPane.showMessageDialog(null, "Lista de aluguéis carregada com sucesso!", "Lista Aberta", JOptionPane.INFORMATION_MESSAGE);
        } 
        
        catch (FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "O arquivo serializado que contém a lista de aluguéis não foi encontrado!", "Lista Não Encontrada", JOptionPane.ERROR_MESSAGE);
        }
        
        catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Algo deu errado ao carregar a lista", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Valida a leitura na deserialização. Não está em uso pois sua validação é severa.
     */
    /*@Override
    public void validateObject() throws InvalidObjectException{//validação personalizada após leitura do objeto. parte da interface OIV

        if(rentals == null){
            throw new InvalidObjectException("A lista de alugueis não foi corretamente carregada!");
        }
    }*/


    /**
     * Lista todas as informações da lista rentals.
     * @return String com todas as infromações dos aluguéis.
     */
    public String listAll(){
        StringBuilder rentals_list = new StringBuilder();
        for(Rental rental : rentals){
            rentals_list.append(rental.toString()).append("\n===================================================\n\n");
        }

        return rentals_list.toString();
    }

    public int size() {
        return rentals.size();
    }
}