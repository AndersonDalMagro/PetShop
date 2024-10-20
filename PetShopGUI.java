import javax.swing.*; // Importa classes para criar a interface gráfica do usuário
import javax.swing.table.DefaultTableCellRenderer; // Importa a classe para personalizar a renderização das células da tabela
import java.awt.*; // Importa classes para gerenciamento de layout e componentes gráficos
import java.io.BufferedReader; // Importa a classe para ler arquivos de texto
import java.io.File; // Importa a classe File para manipulação de arquivos
import java.io.FileReader; // Importa a classe para ler arquivos de texto
import java.io.IOException; // Importa a classe para tratamento de exceções de I/O
import java.util.ArrayList; // Importa a classe ArrayList para gerenciamento de listas
import java.util.HashMap; // Importa a classe HashMap para armazenar serviços e seus preços
import java.util.Map; // Importa a interface Map

public class PetShopGUI {
    private JFrame frame; // Janela principal da aplicação
    private JPanel panel; // Painel para organizar os componentes
    private JButton btnCadastrarCliente, btnListarClientes, btnExcluirCliente; // Botões de clientes
    private JButton btnCadastrarPet, btnListarPets, btnExcluirPet; // Botões de pets
    private JButton btnLancarServico, btnLancarPagamento, btnListarSaldos; // Botões de serviços e pagamentos
    private JButton btnListarClientesComSaldoNegativo, btnVisualizarServicos, btnImportarDados, btnSair; // Outros botões

    // Construtor da classe que inicializa a interface gráfica
    public PetShopGUI() {
        initializeUI(); // Chama o método para configurar a interface
    }

    // Método para inicializar a interface do usuário
    private void initializeUI() {
        frame = new JFrame("Sistema Pet Shop"); // Cria a janela principal
        panel = new JPanel(new GridBagLayout()); // Cria um painel com layout GridBag

        GridBagConstraints gbc = new GridBagConstraints(); // Cria um objeto para gerenciar as restrições de layout
        gbc.fill = GridBagConstraints.BOTH; // Define que os componentes preencherão ambos os eixos

        // Inicializando botões
        initializeButtons(gbc); // Chama o método para criar e adicionar botões

        // Configurando a janela
        frame.add(panel); // Adiciona o painel à janela
        frame.setSize(800, 700); // Define o tamanho da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento de fechamento da janela
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela
        frame.setVisible(true); // Torna a janela visível
    }

    // Método para inicializar os botões
    private void initializeButtons(GridBagConstraints gbc) {
        // Botões de Clientes
        btnCadastrarCliente = new JButton("Cadastrar Cliente"); // Cria o botão para cadastrar cliente
        btnListarClientes = new JButton("Listar Clientes"); // Cria o botão para listar clientes
        btnExcluirCliente = new JButton("Excluir Cliente"); // Cria o botão para excluir cliente

        // Botões de Pets
        btnCadastrarPet = new JButton("Cadastrar Pet"); // Cria o botão para cadastrar pet
        btnListarPets = new JButton("Listar Pets"); // Cria o botão para listar pets
        btnExcluirPet = new JButton("Excluir Pet"); // Cria o botão para excluir pet

        // Botões de Serviços
        btnLancarServico = new JButton("Lançar Serviço"); // Cria o botão para lançar serviço
        btnLancarPagamento = new JButton("Lançar Pagamento"); // Cria o botão para lançar pagamento
        btnListarSaldos = new JButton("Listar Saldos"); // Cria o botão para listar saldos
        btnListarClientesComSaldoNegativo = new JButton("Listar Clientes com Saldo Negativo"); // Cria o botão para listar clientes com saldo negativo
        btnVisualizarServicos = new JButton("Visualizar Serviços"); // Cria o botão para visualizar serviços
        btnImportarDados = new JButton("Importar Dados"); // Cria o botão para importar dados
        btnImportarDados.setBackground(Color.GREEN); // Define a cor de fundo do botão de importação
        btnSair = new JButton("Sair"); // Cria o botão para sair da aplicação
        btnSair.setBackground(Color.RED); // Define a cor de fundo do botão de sair
        btnSair.setForeground(Color.WHITE); // Define a cor do texto do botão de sair
        btnSair.addActionListener(e -> System.exit(0)); // Adiciona ação para fechar a aplicação

        // Adicionando botões ao painel
        gbc.weightx = 1.0; // Define o peso horizontal
        gbc.weighty = 1.0; // Define o peso vertical

        // Adiciona cada botão ao painel usando o método auxiliar
        addButtonToPanel(btnCadastrarCliente, gbc, 0, 0);
        addButtonToPanel(btnListarClientes, gbc, 1, 0);
        addButtonToPanel(btnExcluirCliente, gbc, 0, 1);
        addButtonToPanel(btnCadastrarPet, gbc, 1, 1);
        addButtonToPanel(btnListarPets, gbc, 0, 2);
        addButtonToPanel(btnExcluirPet, gbc, 1, 2);
        addButtonToPanel(btnLancarServico, gbc, 0, 3);
        addButtonToPanel(btnLancarPagamento, gbc, 1, 3);
        addButtonToPanel(btnListarSaldos, gbc, 0, 4);
        addButtonToPanel(btnListarClientesComSaldoNegativo, gbc, 1, 4);
        addButtonToPanel(btnVisualizarServicos, gbc, 0, 5);
        addButtonToPanel(btnImportarDados, gbc, 0, 6, 2); // O botão de importação ocupa duas colunas
        addButtonToPanel(btnSair, gbc, 0, 7, 2); // O botão de sair ocupa duas colunas

        // Ações dos botões
        configureButtonActions(); // Chama o método para configurar as ações dos botões
    }

    // Método auxiliar para adicionar um botão ao painel
    private void addButtonToPanel(JButton button, GridBagConstraints gbc, int gridx, int gridy) {
        addButtonToPanel(button, gbc, gridx, gridy, 1); // Chama o método sobrecarregado com largura padrão de 1
    }

    // Método auxiliar para adicionar um botão ao painel com controle de largura
    private void addButtonToPanel(JButton button, GridBagConstraints gbc, int gridx, int gridy, int gridwidth) {
        gbc.gridx = gridx; // Define a posição horizontal do botão
        gbc.gridy = gridy; // Define a posição vertical do botão
        gbc.gridwidth = gridwidth; // Define a largura do botão
        panel.add(button, gbc); // Adiciona o botão ao painel
    }

    // Método para configurar as ações dos botões
    private void configureButtonActions() {
        // Adiciona ações para cada botão
        btnCadastrarCliente.addActionListener(e -> cadastrarCliente());
        btnListarClientes.addActionListener(e -> listarClientes());
        btnExcluirCliente.addActionListener(e -> excluirCliente());
        btnCadastrarPet.addActionListener(e -> cadastrarPet());
        btnListarPets.addActionListener(e -> listarPets());
        btnExcluirPet.addActionListener(e -> excluirPet());
        btnLancarServico.addActionListener(e -> lancarServico());
        btnLancarPagamento.addActionListener(e -> lancarPagamento());
        btnListarSaldos.addActionListener(e -> listarSaldos());
        btnListarClientesComSaldoNegativo.addActionListener(e -> listarClientesComSaldoNegativo());
        btnVisualizarServicos.addActionListener(e -> visualizarServicos());
        btnImportarDados.addActionListener(e -> importarDados());
    }

    // Método para listar todos os clientes e seus pets
    private void listarClientes() {
        if (Main.getClientes().isEmpty()) { // Verifica se não há clientes cadastrados
            JOptionPane.showMessageDialog(frame, "Nenhum cliente cadastrado."); // Mensagem de aviso
            return; // Sai do método
        }

        // Título da janela
        JFrame clientFrame = new JFrame("Lista de Clientes e Pets");
        clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite fechar a janela sem fechar a aplicação
        clientFrame.setSize(600, 400); // Define o tamanho da janela
        clientFrame.setLocationRelativeTo(frame); // Centraliza a janela em relação à janela principal

        // Definir os dados da tabela
        ArrayList<Object[]> dataList = new ArrayList<>(); // Cria uma lista para armazenar os dados

        // Preencher os dados
        for (Cliente cliente : Main.getClientes()) { // Percorre a lista de clientes
            // Adiciona os dados do cliente
            dataList.add(new Object[]{cliente.getNome(), cliente.getEndereco(), cliente.getTelefone(), String.format("R$ %.2f", cliente.getSaldo()), "Cliente"});

            // Adiciona os dados dos pets
            if (!cliente.getPets().isEmpty()) { // Verifica se o cliente tem pets
                for (Pet pet : cliente.getPets()) { // Percorre a lista de pets do cliente
                    dataList.add(new Object[]{"  " + pet.getNome(), "  " + pet.getRaca(), "Idade: " + pet.getIdade(), pet.getDescricao(), "Pet"});
                }
            } else {
                dataList.add(new Object[]{"  Nenhum pet", "", "", "", "Pet"}); // Mensagem quando não há pets
            }
        }

        // Converter a lista para um array
        Object[][] data = dataList.toArray(new Object[0][5]); // Converte a lista para um array 2D

        // Criar a tabela
        String[] columnNames = {"Nome", "Endereço", "Telefone", "Saldo/Descrição", "Tipo"}; // Nomes das colunas
        JTable clientTable = new JTable(data, columnNames); // Cria a tabela com os dados e nomes das colunas
        clientTable.setFillsViewportHeight(true); // Faz a tabela preencher a altura do painel
        clientTable.setRowHeight(30); // Ajusta a altura das linhas

        // Configurar cores para diferenciar clientes e pets
        clientTable.setDefaultRenderer(Object.class, new CustomTableCellRenderer()); // Define um renderizador personalizado para a tabela

        // Adicionar a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(clientTable); // Cria um JScrollPane para permitir rolagem
        clientFrame.add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane à janela

        // Exibir a janela
        clientFrame.setVisible(true); // Torna a janela visível
    }

    // Classe personalizada para renderizar células da tabela
    class CustomTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Chama o método pai

            String tipo = (String) table.getValueAt(row, 4); // Obtém o tipo da célula
            if ("Cliente".equals(tipo)) { // Verifica se é um cliente
                setFont(getFont().deriveFont(Font.BOLD)); // Define a fonte como negrito
                setBackground(Color.LIGHT_GRAY); // Define o fundo como claro para clientes
            } else {
                setFont(getFont().deriveFont(Font.PLAIN)); // Fonte normal para pets
                setBackground(Color.WHITE); // Fundo branco para pets
            }
            return this; // Retorna o componente
        }
    }

    // Método para importar dados de um arquivo
    public void importarDados() {
        File file = new File("C:\\SistemaPetShop\\Dados.txt"); // Cria um objeto File para o arquivo de dados
        if (!file.exists()) { // Verifica se o arquivo existe
            JOptionPane.showMessageDialog(frame, "Arquivo de dados não encontrado."); // Mensagem de erro
            return; // Sai do método
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) { // Inicia o BufferedReader para ler o arquivo
            String linha; // Variável para armazenar cada linha lida
            while ((linha = br.readLine()) != null) { // Lê cada linha do arquivo
                String[] partes = linha.split(";"); // Divide a linha em partes
                if (partes.length >= 6) { // Deve ter pelo menos 6 partes
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
                    Main.adicionarCliente(cliente); // Adiciona o cliente ao sistema
                } else {
                    System.out.println("Formato inválido na linha: " + linha); // Mensagem de erro
                }
            }
            JOptionPane.showMessageDialog(frame, "Dados importados com sucesso!"); // Mensagem de sucesso
            btnImportarDados.setVisible(false); // Esconde o botão após importação
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Erro ao importar dados: " + e.getMessage()); // Mensagem de erro
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Erro ao converter saldo. Verifique o formato do arquivo."); // Mensagem de erro
        }
    }

    // Método para visualizar serviços disponíveis
    private void visualizarServicos() {
        JFrame serviceFrame = new JFrame("Serviços Disponíveis"); // Cria a janela para visualizar serviços
        serviceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite fechar a janela sem fechar a aplicação
        serviceFrame.setSize(400, 300); // Define o tamanho da janela
        serviceFrame.setLocationRelativeTo(frame); // Centraliza a janela em relação à janela principal

        // Lendo os serviços do arquivo
        String[] columnNames = {"Serviço", "Preço"}; // Nomes das colunas da tabela
        Object[][] data = loadServicos(); // Carrega os serviços

        JTable serviceTable = new JTable(data, columnNames); // Cria a tabela com os dados e nomes das colunas
        serviceTable.setFillsViewportHeight(true); // Faz a tabela preencher a altura do painel
        serviceTable.setRowHeight(30); // Ajusta a altura das linhas
        serviceTable.getColumnModel().getColumn(0).setPreferredWidth(200); // Define a largura da coluna de serviços
        serviceTable.getColumnModel().getColumn(1).setPreferredWidth(100); // Define a largura da coluna de preços

        JScrollPane scrollPane = new JScrollPane(serviceTable); // Cria um JScrollPane para permitir rolagem
        serviceFrame.add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane à janela
        serviceFrame.setVisible(true); // Torna a janela visível
    }

    // Método para carregar serviços de um arquivo
    private Object[][] loadServicos() {
        ArrayList<Object[]> serviceList = new ArrayList<>(); // Cria uma lista para armazenar os serviços
        try (BufferedReader br = new BufferedReader(new FileReader(Main.getServicosFile()))) { // Inicia o BufferedReader para ler o arquivo de serviços
            String linha; // Variável para armazenar cada linha lida
            while ((linha = br.readLine()) != null) { // Lê cada linha do arquivo
                String[] partes = linha.split(";"); // Divide a linha em partes
                if (partes.length == 2) { // Verifica se há duas partes (serviço e preço)
                    String servico = partes[0].trim(); // Nome do serviço
                    String preco = String.format("R$ %.2f", Double.parseDouble(partes[1].trim().replace(",", "."))); // Preço do serviço formatado
                    serviceList.add(new Object[]{servico, preco}); // Adiciona o serviço à lista
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Erro ao carregar serviços: " + e.getMessage()); // Mensagem de erro
        }
        return serviceList.toArray(new Object[0][2]); // Retorna a lista como um array 2D
    }

    // Método para cadastrar um novo cliente
    private void cadastrarCliente() {
        String nome; // Variável para armazenar o nome do cliente
        while (true) {
            nome = JOptionPane.showInputDialog("Digite o nome do cliente:"); // Solicita o nome do cliente
            if (nome == null) {
                return; // Cancelou
            } else if (nome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nome não pode ser vazio. Tente novamente."); // Mensagem de erro
            } else {
                break; // Nome válido
            }
        }

        String endereco; // Variável para armazenar o endereço do cliente
        while (true) {
            endereco = JOptionPane.showInputDialog("Digite o endereço:"); // Solicita o endereço do cliente
            if (endereco == null) {
                return; // Cancelou
            } else if (endereco.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Endereço não pode ser vazio. Tente novamente."); // Mensagem de erro
            } else {
                break; // Endereço válido
            }
        }

        String telefone; // Variável para armazenar o telefone do cliente
        while (true) {
            telefone = JOptionPane.showInputDialog("Digite o telefone:"); // Solicita o telefone do cliente
            if (telefone == null) {
                return; // Cancelou
            }
            if (telefone.matches("\\d+")) { // Verifica se o telefone contém apenas dígitos
                break; // Telefone válido
            } else {
                JOptionPane.showMessageDialog(frame, "Telefone inválido. Por favor, digite apenas números."); // Mensagem de erro
            }
        }

        Cliente cliente = new Cliente(nome, endereco, telefone); // Cria um novo cliente
        Main.adicionarCliente(cliente); // Adiciona o cliente e salva os dados

        JOptionPane.showMessageDialog(frame, "Cliente " + nome + " cadastrado com sucesso!"); // Mensagem de sucesso

        int incluirPets = JOptionPane.showConfirmDialog(frame, "Deseja incluir pets para este cliente?", "Incluir Pets", JOptionPane.YES_NO_OPTION); // Pergunta se deseja incluir pets
        if (incluirPets == JOptionPane.YES_OPTION) {
            cadastrarPetParaCliente(cliente); // Chama o método para cadastrar pets
        }
    }

    // Método para cadastrar pets para um cliente
    private void cadastrarPetParaCliente(Cliente cliente) {
        while (true) {
            String raca; // Variável para armazenar a raça do pet
            while (true) {
                raca = JOptionPane.showInputDialog("Raça do pet:"); // Solicita a raça do pet
                if (raca == null) return; // Cancelou
                if (raca.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "A raça não pode ser vazia. Tente novamente."); // Mensagem de erro
                } else {
                    break; // Raça válida
                }
            }

            String nome; // Variável para armazenar o nome do pet
            while (true) {
                nome = JOptionPane.showInputDialog("Nome do pet:"); // Solicita o nome do pet
                if (nome == null) return; // Cancelou
                if (nome.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "O nome não pode ser vazio. Tente novamente."); // Mensagem de erro
                } else {
                    break; // Nome válido
                }
            }

            int idade = -1; // Inicializa com um valor inválido
            while (idade < 0) { // Enquanto a idade for inválida
                String idadeStr = JOptionPane.showInputDialog("Idade do pet (anos):"); // Solicita a idade do pet
                if (idadeStr == null) return; // Cancelou
                try {
                    idade = Integer.parseInt(idadeStr); // Converte a idade para inteiro
                    if (idade < 0) {
                        JOptionPane.showMessageDialog(frame, "Idade não pode ser negativa."); // Mensagem de erro
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Idade inválida. Por favor, digite apenas números."); // Mensagem de erro
                }
            }

            String descricao; // Variável para armazenar a descrição do pet
            while (true) {
                descricao = JOptionPane.showInputDialog("Descrição do pet:"); // Solicita a descrição do pet
                if (descricao == null) return; // Cancelou
                if (descricao.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "A descrição não pode ser vazia. Tente novamente."); // Mensagem de erro
                } else {
                    break; // Descrição válida
                }
            }

            Pet pet = new Pet(raca, nome, idade, descricao); // Cria um novo pet
            cliente.adicionarPet(pet); // Adiciona o pet ao cliente
            Main.salvarDados(); // Salvar após adicionar pet

            JOptionPane.showMessageDialog(frame, "Pet " + nome + " cadastrado com sucesso!"); // Mensagem de sucesso

            int adicionarOutroPet = JOptionPane.showConfirmDialog(frame, "Deseja adicionar outro pet?", "Adicionar Outro Pet", JOptionPane.YES_NO_OPTION); // Pergunta se deseja adicionar outro pet
            if (adicionarOutroPet == JOptionPane.NO_OPTION) {
                break; // Sai do loop se não deseja adicionar mais
            }
        }
    }

    // Método para excluir um cliente
    private void excluirCliente() {
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente a ser excluído:"); // Solicita o nome do cliente
        if (nome == null) {
            return; // Cancelou
        }

        Cliente clienteParaRemover = null; // Inicializa a variável para armazenar o cliente a ser removido
        for (Cliente cliente : Main.getClientes()) { // Percorre a lista de clientes
            if (cliente.getNome().equalsIgnoreCase(nome)) { // Verifica se o nome do cliente corresponde
                clienteParaRemover = cliente; // Armazena o cliente a ser removido
                break; // Sai do loop
            }
        }

        if (clienteParaRemover != null) {
            Main.removerCliente(clienteParaRemover); // Remove o cliente e salva os dados
            JOptionPane.showMessageDialog(frame, "Cliente excluído com sucesso."); // Mensagem de sucesso
        } else {
            JOptionPane.showMessageDialog(frame, "Cliente não encontrado."); // Mensagem de erro
        }
    }

    // Método para cadastrar um pet para um cliente
    private void cadastrarPet() {
        String nomeCliente = JOptionPane.showInputDialog("Digite o nome do cliente:"); // Solicita o nome do cliente
        if (nomeCliente == null) {
            return; // Cancelou
        }

        Cliente cliente = encontrarCliente(nomeCliente); // Busca o cliente pelo nome

        if (cliente != null) {
            cadastrarPetParaCliente(cliente); // Chama o método para cadastrar pets
        } else {
            JOptionPane.showMessageDialog(frame, "Cliente não encontrado."); // Mensagem de erro
        }
    }

    // Método para listar os pets de um cliente
    private void listarPets() {
        String nomeCliente = JOptionPane.showInputDialog("Digite o nome do cliente:"); // Solicita o nome do cliente
        if (nomeCliente == null) {
            return; // Cancelou
        }

        Cliente cliente = encontrarCliente(nomeCliente); // Busca o cliente pelo nome

        if (cliente != null) {
            if (cliente.getPets().isEmpty()) { // Verifica se o cliente tem pets
                JOptionPane.showMessageDialog(frame, "Nenhum pet cadastrado para " + cliente.getNome() + "."); // Mensagem de aviso
                return; // Sai do método
            }

            // Título da janela
            JFrame petFrame = new JFrame("Lista de Pets"); // Cria a janela para listar pets
            petFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite fechar a janela sem fechar a aplicação
            petFrame.setSize(400, 300); // Define o tamanho da janela
            petFrame.setLocationRelativeTo(frame); // Centraliza a janela em relação à janela principal

            // Definir os dados da tabela
            String[] columnNames = {"Nome do Pet", "Raça", "Idade", "Descrição"}; // Nomes das colunas
            Object[][] data = new Object[cliente.getPets().size()][4]; // Cria um array 2D para armazenar os dados dos pets

            // Preencher os dados
            int index = 0; // Inicializa o índice
            for (Pet pet : cliente.getPets()) { // Percorre a lista de pets do cliente
                data[index][0] = pet.getNome(); // Nome do pet
                data[index][1] = pet.getRaca(); // Raça do pet
                data[index][2] = pet.getIdade(); // Idade do pet
                data[index][3] = pet.getDescricao(); // Descrição do pet
                index++; // Incrementa o índice
            }

            // Criar a tabela
            JTable petTable = new JTable(data, columnNames); // Cria a tabela com os dados e nomes das colunas
            petTable.setFillsViewportHeight(true); // Faz a tabela preencher a altura do painel
            petTable.setRowHeight(30); // Ajusta a altura das linhas

            // Adicionar a tabela a um JScrollPane
            JScrollPane scrollPane = new JScrollPane(petTable); // Cria um JScrollPane para permitir rolagem
            petFrame.add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane à janela

            // Exibir a janela
            petFrame.setVisible(true); // Torna a janela visível
        } else {
            JOptionPane.showMessageDialog(frame, "Cliente não encontrado."); // Mensagem de erro
        }
    }

    // Método para excluir um pet de um cliente
    private void excluirPet() {
        String nomeCliente = JOptionPane.showInputDialog("Digite o nome do cliente:"); // Solicita o nome do cliente
        if (nomeCliente == null) {
            return; // Cancelou
        }

        Cliente cliente = encontrarCliente(nomeCliente); // Busca o cliente pelo nome

        if (cliente != null) {
            int option = JOptionPane.showConfirmDialog(frame, "Deseja excluir todos os pets de " + cliente.getNome() + "?", "Excluir Pets", JOptionPane.YES_NO_OPTION); // Pergunta se deseja excluir todos os pets

            if (option == JOptionPane.YES_OPTION) {
                cliente.getPets().clear(); // Exclui todos os pets
                Main.salvarDados(); // Salvar após exclusão
                JOptionPane.showMessageDialog(frame, "Todos os pets de " + cliente.getNome() + " foram excluídos com sucesso."); // Mensagem de sucesso
            } else {
                String nomePet = JOptionPane.showInputDialog("Digite o nome do pet a ser excluído:"); // Solicita o nome do pet
                if (nomePet == null) return; // Cancelou

                Pet petParaRemover = null; // Inicializa a variável para armazenar o pet a ser removido
                for (Pet pet : cliente.getPets()) { // Percorre a lista de pets do cliente
                    if (pet.getNome().equalsIgnoreCase(nomePet)) { // Verifica se o nome do pet corresponde
                        petParaRemover = pet; // Armazena o pet a ser removido
                        break; // Sai do loop
                    }
                }

                if (petParaRemover != null) {
                    cliente.removerPet(petParaRemover); // Remove o pet
                    Main.salvarDados(); // Salvar após exclusão
                    JOptionPane.showMessageDialog(frame, "Pet " + nomePet + " excluído com sucesso."); // Mensagem de sucesso
                } else {
                    JOptionPane.showMessageDialog(frame, "Pet não encontrado."); // Mensagem de erro
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Cliente não encontrado."); // Mensagem de erro
        }
    }

    // Método para lançar um serviço para um cliente
    private void lancarServico() {
        String nomeCliente = JOptionPane.showInputDialog("Digite o nome do cliente:"); // Solicita o nome do cliente
        if (nomeCliente == null) {
            return; // Cancelou
        }

        Cliente cliente = encontrarCliente(nomeCliente); // Busca o cliente pelo nome

        if (cliente != null) {
            // Definir manualmente os serviços
            Map<String, Double> servicos = new HashMap<>(); // Cria um mapa para armazenar serviços e preços
            servicos.put("Banho", 100.00); // Adiciona serviço de banho
            servicos.put("Banho e tosa", 150.00); // Adiciona serviço de banho e tosa
            servicos.put("Vacina comum", 95.50); // Adiciona serviço de vacina comum
            servicos.put("Castração", 178.50); // Adiciona serviço de castração
            servicos.put("Ração 3kg", 67.99); // Adiciona ração de 3kg
            servicos.put("Ração 5kg", 89.00); // Adiciona ração de 5kg

            // Criar um painel para os checkboxes
            JPanel panel = new JPanel(); // Cria um painel para os checkboxes
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Define o layout como vertical

            // Array para armazenar os checkboxes e seus valores
            JCheckBox[] checkBoxes = new JCheckBox[servicos.size()]; // Cria um array de checkboxes
            int index = 0; // Inicializa o índice
            for (Map.Entry<String, Double> entry : servicos.entrySet()) { // Percorre os serviços
                checkBoxes[index] = new JCheckBox(entry.getKey() + " - R$ " + String.format("%.2f", entry.getValue())); // Cria um checkbox para o serviço
                panel.add(checkBoxes[index]); // Adiciona o checkbox ao painel
                index++; // Incrementa o índice
            }

            // Mostrar a lista de serviços em um JOptionPane
            int result = JOptionPane.showConfirmDialog(frame, panel, "Selecione os serviços", // Pergunta se deseja lançar serviços
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE); // Configura o diálogo

            if (result == JOptionPane.OK_OPTION) { // Se o usuário confirmar
                double total = 0.0; // Inicializa o total

                // Calcular o total dos serviços selecionados
                for (int i = 0; i < checkBoxes.length; i++) { // Percorre os checkboxes
                    if (checkBoxes[i].isSelected()) { // Se o checkbox estiver selecionado
                        String servicoSelecionado = checkBoxes[i].getText().split(" - ")[0]; // Obtém o nome do serviço
                        double preco = servicos.get(servicoSelecionado); // Obtém o preço do serviço
                        total += preco; // Adiciona o preço ao total
                    }
                }

                // Atualizar o saldo do cliente
                cliente.setSaldo(cliente.getSaldo() - total); // Debita do saldo atual do cliente
                Main.salvarDados(); // Salvar após lançamento de serviço

                // Exibir mensagem de sucesso
                JOptionPane.showMessageDialog(frame, "Serviços lançados com sucesso! Total: R$ " + String.format("%.2f", total)); // Mensagem de sucesso
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Cliente não encontrado."); // Mensagem de erro
        }
    }

    // Método para lançar um pagamento para um cliente
    private void lancarPagamento() {
        String nomeCliente = JOptionPane.showInputDialog("Digite o nome do cliente:"); // Solicita o nome do cliente
        if (nomeCliente == null) {
            return; // Cancelou
        }

        Cliente cliente = encontrarCliente(nomeCliente); // Busca o cliente pelo nome

        if (cliente != null) {
            String pagamentoStr; // Variável para armazenar o valor do pagamento
            double pagamento; // Variável para armazenar o valor do pagamento em formato double
            while (true) {
                pagamentoStr = JOptionPane.showInputDialog("Digite o valor do pagamento:"); // Solicita o valor do pagamento
                if (pagamentoStr == null) return; // Cancelou
                try {
                    pagamento = Double.parseDouble(pagamentoStr); // Converte o valor para double
                    if (pagamento <= 0) { // Verifica se o pagamento é positivo
                        JOptionPane.showMessageDialog(frame, "Valor inválido. Por favor, digite um valor positivo."); // Mensagem de erro
                    } else {
                        break; // Valor válido
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Valor inválido. Por favor, digite um número."); // Mensagem de erro
                }
            }
            cliente.setSaldo(cliente.getSaldo() + pagamento); // Atualiza o saldo do cliente com o pagamento
            Main.salvarDados(); // Salvar após lançamento de pagamento
            JOptionPane.showMessageDialog(frame, "Pagamento lançado com sucesso!"); // Mensagem de sucesso
        } else {
            JOptionPane.showMessageDialog(frame, "Cliente não encontrado."); // Mensagem de erro
        }
    }

    // Método para listar todos os saldos dos clientes
    private void listarSaldos() {
        if (Main.getClientes().isEmpty()) { // Verifica se não há clientes cadastrados
            JOptionPane.showMessageDialog(frame, "Nenhum cliente cadastrado."); // Mensagem de aviso
            return; // Sai do método
        }

        // Título da janela
        JFrame saldoFrame = new JFrame("Saldos dos Clientes"); // Cria a janela para listar saldos
        saldoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite fechar a janela sem fechar a aplicação
        saldoFrame.setSize(400, 300); // Define o tamanho da janela
        saldoFrame.setLocationRelativeTo(frame); // Centraliza a janela em relação à janela principal

        // Definir os dados da tabela
        String[] columnNames = {"Nome", "Telefone", "Saldo"}; // Nomes das colunas
        Object[][] data = new Object[Main.getClientes().size()][3]; // Cria um array 2D para armazenar os dados

        // Preencher os dados
        int index = 0; // Inicializa o índice
        for (Cliente cliente : Main.getClientes()) { // Percorre a lista de clientes
            data[index][0] = cliente.getNome(); // Nome do cliente
            data[index][1] = cliente.getTelefone(); // Telefone do cliente
            data[index][2] = String.format("R$ %.2f", cliente.getSaldo()); // Saldo do cliente formatado
            index++; // Incrementa o índice
        }

        // Criar a tabela
        JTable saldoTable = new JTable(data, columnNames); // Cria a tabela com os dados e nomes das colunas
        saldoTable.setFillsViewportHeight(true); // Faz a tabela preencher a altura do painel
        saldoTable.setRowHeight(30); // Ajusta a altura das linhas

        // Adicionar a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(saldoTable); // Cria um JScrollPane para permitir rolagem
        saldoFrame.add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane à janela

        // Exibir a janela
        saldoFrame.setVisible(true); // Torna a janela visível
    }

    // Método para listar clientes com saldo negativo
    private void listarClientesComSaldoNegativo() {
        ArrayList<Cliente> clientesComSaldoNegativo = new ArrayList<>(); // Cria uma lista para armazenar clientes com saldo negativo
        for (Cliente cliente : Main.getClientes()) { // Percorre a lista de clientes
            if (cliente.getSaldo() < 0) { // Verifica se o saldo é negativo
                clientesComSaldoNegativo.add(cliente); // Adiciona o cliente à lista
            }
        }

        if (clientesComSaldoNegativo.isEmpty()) { // Verifica se a lista está vazia
            JOptionPane.showMessageDialog(frame, "Nenhum cliente com saldo negativo encontrado."); // Mensagem de aviso
            return; // Sai do método
        }

        // Título da janela
        JFrame saldoNegativoFrame = new JFrame("Clientes com Saldo Negativo"); // Cria a janela para listar clientes com saldo negativo
        saldoNegativoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Permite fechar a janela sem fechar a aplicação
        saldoNegativoFrame.setSize(400, 300); // Define o tamanho da janela
        saldoNegativoFrame.setLocationRelativeTo(frame); // Centraliza a janela em relação à janela principal

        // Definir os dados da tabela
        String[] columnNames = {"Nome", "Telefone", "Saldo"}; // Nomes das colunas
        Object[][] data = new Object[clientesComSaldoNegativo.size()][3]; // Cria um array 2D para armazenar os dados

        // Preencher os dados
        int index = 0; // Inicializa o índice
        for (Cliente cliente : clientesComSaldoNegativo) { // Percorre a lista de clientes com saldo negativo
            data[index][0] = cliente.getNome(); // Nome do cliente
            data[index][1] = cliente.getTelefone(); // Telefone do cliente
            data[index][2] = String.format("R$ %.2f", cliente.getSaldo()); // Saldo do cliente formatado
            index++; // Incrementa o índice
        }

        // Criar a tabela
        JTable saldoNegativoTable = new JTable(data, columnNames); // Cria a tabela com os dados e nomes das colunas
        saldoNegativoTable.setFillsViewportHeight(true); // Faz a tabela preencher a altura do painel
        saldoNegativoTable.setRowHeight(30); // Ajusta a altura das linhas

        // Adicionar a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(saldoNegativoTable); // Cria um JScrollPane para permitir rolagem
        saldoNegativoFrame.add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane à janela

        // Exibir a janela
        saldoNegativoFrame.setVisible(true); // Torna a janela visível
    }

    // Método para encontrar um cliente pelo nome
    private Cliente encontrarCliente(String nome) {
        for (Cliente cliente : Main.getClientes()) { // Percorre a lista de clientes
            if (cliente.getNome().equalsIgnoreCase(nome)) { // Verifica se o nome do cliente corresponde
                return cliente; // Retorna o cliente encontrado
            }
        }
        return null; // Cliente não encontrado
    }

    // Método principal que inicia a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PetShopGUI::new); // Inicia a GUI em um thread do Swing
    }
}