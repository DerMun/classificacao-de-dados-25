package mercadinho;

public class Cliente {
    private String name;
    private String cpf;

    public Cliente(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }
}