import java.io.Serializable; // Importa a interface Serializable para permitir que objetos da classe Pet sejam serializados

public class Pet implements Serializable {
    // Atributos da classe Pet
    private String raca;      // Armazena a raça do pet
    private String nome;      // Armazena o nome do pet
    private int idade;        // Armazena a idade do pet
    private String descricao;  // Armazena uma descrição do pet

    // Construtor da classe Pet
    public Pet(String raca, String nome, int idade, String descricao) {
        this.raca = raca;                // Inicializa o atributo raca
        this.nome = nome;                // Inicializa o atributo nome
        setIdade(idade);                 // Usa o método setter para inicializar e validar a idade
        this.descricao = descricao;      // Inicializa o atributo descricao
    }

    // Métodos Getters e Setters
    public String getRaca() {
        return raca; // Retorna a raça do pet
    }

    public void setRaca(String raca) {
        this.raca = raca; // Define a raça do pet
    }

    public String getNome() {
        return nome; // Retorna o nome do pet
    }

    public void setNome(String nome) {
        this.nome = nome; // Define o nome do pet
    }

    public int getIdade() {
        return idade; // Retorna a idade do pet
    }

    public void setIdade(int idade) {
        // Verifica se a idade é não negativa
        if (idade >= 0) {
            this.idade = idade; // Define a idade do pet se válida
        } else {
            throw new IllegalArgumentException("Idade não pode ser negativa."); // Lança exceção se idade for negativa
        }
    }

    public String getDescricao() {
        return descricao; // Retorna a descrição do pet
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao; // Define a descrição do pet
    }

    @Override
    public String toString() {
        // Retorna uma representação em string do objeto Pet
        return "Pet{" +
                "raça='" + raca + '\'' +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", descrição='" + descricao + '\'' +
                '}';
    }
}