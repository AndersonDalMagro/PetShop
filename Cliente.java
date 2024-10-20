import java.io.Serializable; // Importa a interface Serializable para permitir que objetos da classe Cliente sejam serializados
import java.util.ArrayList; // Importa a classe ArrayList para armazenar a lista de pets

public class Cliente implements Serializable {
    // Atributos da classe Cliente
    private String nome;       // Armazena o nome do cliente
    private String endereco;   // Armazena o endereço do cliente
    private String telefone;    // Armazena o telefone do cliente
    private double saldo;      // Armazena o saldo do cliente
    private ArrayList<Pet> pets; // Armazena a lista de pets do cliente

    // Construtor da classe Cliente
    public Cliente(String nome, String endereco, String telefone) {
        this.nome = nome;                   // Inicializa o atributo nome
        this.endereco = endereco;           // Inicializa o atributo endereco
        setTelefone(telefone);              // Usa o método setter para inicializar e validar o telefone
        this.saldo = 0.0;                   // Inicializa o saldo como zero
        this.pets = new ArrayList<>();      // Inicializa a lista de pets como uma nova ArrayList
    }

    // Métodos Getters e Setters
    public String getNome() {
        return nome; // Retorna o nome do cliente
    }

    public void setNome(String nome) {
        this.nome = nome; // Define o nome do cliente
    }

    public String getEndereco() {
        return endereco; // Retorna o endereço do cliente
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco; // Define o endereço do cliente
    }

    public String getTelefone() {
        return telefone; // Retorna o telefone do cliente
    }

    public void setTelefone(String telefone) {
        // Verifica se o telefone contém apenas dígitos
        if (telefone.matches("\\d+")) {
            this.telefone = telefone; // Define o telefone do cliente se válido
        } else {
            throw new IllegalArgumentException("Telefone deve conter apenas números."); // Lança exceção se telefone for inválido
        }
    }

    public double getSaldo() {
        return saldo; // Retorna o saldo do cliente
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo; // Define o saldo do cliente
    }

    public ArrayList<Pet> getPets() {
        return pets; // Retorna a lista de pets do cliente
    }

    public void adicionarPet(Pet pet) {
        pets.add(pet); // Adiciona um pet à lista de pets do cliente
    }

    public void removerPet(Pet pet) {
        pets.remove(pet); // Remove um pet da lista de pets do cliente
    }
}