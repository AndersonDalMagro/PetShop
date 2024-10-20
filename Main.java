import java.io.*; // Importa classes para leitura e escrita de arquivos
import java.util.ArrayList; // Importa a classe ArrayList para gerenciar a lista de clientes
import java.util.HashMap; // Importa a classe HashMap para gerenciar os serviços e seus preços
import java.util.Map; // Importa a interface Map
import javax.swing.SwingUtilities; // Importa a classe SwingUtilities para manipulação da interface gráfica

public class  Main{
    public static ArrayList<Cliente> clientes = new ArrayList<>(); // Lista de clientes
    private static Map<String, Double> servicos = new HashMap<>(); // Mapa para armazenar serviços e seus preços

    // Caminhos dos arquivos de dados e serviços
    private static final String DADOS_FILE = "C:\\SistemaPetShop\\Dados.txt";
    private static final String SERVICOS_FILE = "C:\\SistemaPetShop\\Servicos.txt";

    // Método getter para o caminho do arquivo de serviços
    public static String getServicosFile() {
        return SERVICOS_FILE; // Retorna o caminho do arquivo de serviços
    }

    public static void main(String[] args) {

        carregarDados(); // Carrega dados ao iniciar o sistema
        carregarServicos(); // Carrega serviços antes de iniciar a GUI

        // Inicializa a interface gráfica em um novo thread
        SwingUtilities.invokeLater(() -> new PetShopGUI());
    }

    // Método para carregar dados de clientes e pets do arquivo
    private static void carregarDados() {

        File file = new File(DADOS_FILE); // Cria um objeto File para o arquivo de dados
        if (file.exists()) { // Verifica se o arquivo existe
            try (BufferedReader br = new BufferedReader(new FileReader(file))) { // Inicia o BufferedReader para ler o arquivo
                String linha; // Variável para armazenar cada linha lida
                while ((linha = br.readLine()) != null) { // Lê cada linha do arquivo
                    String[] partes = linha.split(";"); // Divide a linha em partes
                    if (partes.length >= 6) { // Verifica se há pelo menos 6 partes
                        String nome = partes[0].trim(); // Nome do cliente
                        String endereco = partes[1].trim(); // Endereço do cliente
                        String telefone = partes[2].trim(); // Telefone do cliente
                        double saldo = Double.parseDouble(partes[3].trim().replace(",", ".")); // Saldo do cliente
                        Cliente cliente = new Cliente(nome, endereco, telefone); // Cria um novo cliente
                        cliente.setSaldo(saldo); // Define o saldo do cliente

                        // Adiciona os pets
                        for (int i = 4; i < partes.length; i += 4) {
                            if (i + 3 < partes.length) { // Verifica se há dados suficientes para um pet
                                String raca = partes[i].trim(); // Raça do pet
                                String nomePet = partes[i + 1].trim(); // Nome do pet
                                int idade = Integer.parseInt(partes[i + 2].trim()); // Idade do pet
                                String descricao = partes[i + 3].trim(); // Descrição do pet
                                Pet pet = new Pet(raca, nomePet, idade, descricao); // Cria um novo pet
                                cliente.adicionarPet(pet); // Adiciona o pet ao cliente
                            }
                        }
                        clientes.add(cliente); // Adiciona o cliente à lista de clientes
                    } else {
                        System.out.println("Formato inválido na linha: " + linha); // Mensagem de erro
                    }
                }
                System.out.println("Dados carregados com sucesso: " + clientes.size() + " clientes encontrados."); // Mensagem de sucesso
            } catch (IOException e) {
                e.printStackTrace(); // Imprime o stack trace em caso de erro de I/O
            } catch (NumberFormatException e) {
                System.out.println("Erro ao converter saldo. Verifique o formato do arquivo."); // Mensagem de erro
            }
        } else {
            System.out.println("Arquivo de dados não encontrado: " + DADOS_FILE); // Mensagem de erro
        }
    }

    // Método para salvar os dados dos clientes e pets no arquivo
    public static void salvarDados() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DADOS_FILE, false))) { // Inicia o BufferedWriter
            for (Cliente cliente : clientes) { // Percorre a lista de clientes
                StringBuilder linha = new StringBuilder(); // Cria um StringBuilder para construir a linha
                linha.append(cliente.getNome()).append(";") // Nome do cliente
                        .append(cliente.getEndereco()).append(";") // Endereço do cliente
                        .append(cliente.getTelefone()).append(";") // Telefone do cliente
                        .append(String.format("%.2f", cliente.getSaldo())); // Saldo do cliente

                for (Pet pet : cliente.getPets()) { // Percorre a lista de pets do cliente
                    linha.append(";") // Adiciona separador
                            .append(pet.getRaca()).append(";") // Raça do pet
                            .append(pet.getNome()).append(";") // Nome do pet
                            .append(pet.getIdade()).append(";") // Idade do pet
                            .append(pet.getDescricao()); // Descrição do pet
                }
                bw.write(linha.toString()); // Escreve a linha no arquivo
                bw.newLine(); // Adiciona uma nova linha após cada cliente
            }
            System.out.println("Dados salvos com sucesso."); // Mensagem de sucesso
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage()); // Mensagem de erro
        }
    }

    // Método para carregar os serviços do arquivo
    private static void carregarServicos() {
        try (BufferedReader br = new BufferedReader(new FileReader(SERVICOS_FILE))) { // Inicia o BufferedReader para ler o arquivo de serviços
            String linha; // Variável para armazenar cada linha lida
            while ((linha = br.readLine()) != null) { // Lê cada linha do arquivo
                String[] partes = linha.split(";"); // Divide a linha em partes
                if (partes.length == 2) { // Verifica se há duas partes (serviço e preço)
                    String servico = partes[0].trim(); // Nome do serviço
                    double preco = Double.parseDouble(partes[1].trim().replace(",", ".")); // Preço do serviço
                    servicos.put(servico, preco); // Adiciona o serviço e preço ao mapa
                    System.out.println("Serviço carregado: " + servico + " - R$ " + preco); // Mensagem de depuração
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar serviços: " + e.getMessage()); // Mensagem de erro
        }
    }

    // Método getter para a lista de clientes
    public static ArrayList<Cliente> getClientes() {
        return clientes; // Retorna a lista de clientes
    }

    // Método getter para os serviços
    public static Map<String, Double> getServicos() {
        return servicos; // Retorna o mapa de serviços
    }

    // Método para adicionar um cliente à lista
    public static void adicionarCliente(Cliente cliente) {
        clientes.add(cliente); // Adiciona o cliente à lista
        salvarDados(); // Salvar ao adicionar um cliente
    }

    // Método para remover um cliente da lista
    public static void removerCliente(Cliente cliente) {
        clientes.remove(cliente); // Remove o cliente da lista
        salvarDados(); // Salvar ao remover um cliente
    }
}