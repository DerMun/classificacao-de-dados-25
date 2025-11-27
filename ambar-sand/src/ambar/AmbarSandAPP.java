package ambar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.File;

import javax.swing.*;

public class AmbarSandAPP extends JFrame {
    private static final String SAVE_FILE_NAME = "ambar.byte";
    private static final Color PRIMARY_COLOR = new Color(255, 191, 0);
    private static final Color SECONDARY_COLOR = new Color(255, 160, 0);
    private static final String[] EQUIPMENT_TYPES = {
            "jet ski", "barco pontão", "barco a remo", "canoa", "caiaque", "cadeira de praia", "guarda-sol", "gazebo"
    };

    private final Rentals rentals = new Rentals();
    private boolean autoSave = true;
    private boolean lessonSelected = false;
    private int selectedEquipmentId = 1;

    private JTextArea timeArea;
    private JCheckBox lessonCheckBox;
    private JComboBox<String> equipmentComboBox;
    private JCheckBoxMenuItem autoSaveMenuItem;

    public AmbarSandAPP() {
        if (new File(SAVE_FILE_NAME).exists()) {
            rentals.loadFile();
        }

        initFrame();
        createMenuBar();
        createMainPanel();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Configura as propriedades básicas do JFrame.
     */
    private void initFrame() {
        setTitle("Ambar Sand");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(854, 480));
        getContentPane().setBackground(PRIMARY_COLOR);
    }

    /**
     * Cria e configura a barra de menus.
     */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(SECONDARY_COLOR);

        JMenu fileMenu = new JMenu("File");
        JMenuItem clearListItem = new JMenuItem("Limpar Lista");
        clearListItem.addActionListener(e -> clearListAction());
        JMenuItem openListItem = new JMenuItem("Abrir Lista");
        openListItem.addActionListener(e -> rentals.loadFile());
        fileMenu.add(clearListItem);
        fileMenu.add(openListItem);

        JMenu saveMenu = new JMenu("Save");
        JMenuItem saveItem = new JMenuItem("Salvar");
        saveItem.addActionListener(e -> rentals.saveToFile());

        autoSaveMenuItem = new JCheckBoxMenuItem("Salvamento Automático: on", autoSave);
        autoSaveMenuItem.addItemListener(e -> {
            autoSave = e.getStateChange() == ItemEvent.SELECTED;
            autoSaveMenuItem.setText("Salvamento Automático: " + (autoSave ? "on" : "off"));
        });
        saveMenu.add(saveItem);
        saveMenu.add(autoSaveMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(saveMenu);
        setJMenuBar(menuBar);
    }

    /**
     * Cria o painel principal.
     */
    private void createMainPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(PRIMARY_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Tempo de Aluguel
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Tempo de aluguel (min):"), gbc);

        gbc.gridx = 1;
        timeArea = new JTextArea();
        mainPanel.add(timeArea, gbc);

        //Equipamento
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Equipamento:"), gbc);

        gbc.gridx = 1;
        equipmentComboBox = new JComboBox<>(EQUIPMENT_TYPES);
        equipmentComboBox.addActionListener(this::equipmentSelectionAction);
        mainPanel.add(equipmentComboBox, gbc);

        //Checkbox de Aula
        gbc.gridx = 1;
        gbc.gridy = 2;
        lessonCheckBox = new JCheckBox("Incluir aula");
        lessonCheckBox.setBackground(PRIMARY_COLOR);
        lessonCheckBox.addItemListener(e -> lessonSelected = (e.getStateChange() == ItemEvent.SELECTED));
        mainPanel.add(lessonCheckBox, gbc);

        //Botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(PRIMARY_COLOR);

        JButton confirmButton = new JButton("Confirmar Aluguel");
        confirmButton.addActionListener(e -> confirmRentalAction());
        buttonPanel.add(confirmButton);

        JButton listButton = new JButton("Listar Aluguéis");
        listButton.addActionListener(e -> listRentalsAction());
        buttonPanel.add(listButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void clearListAction() {
        if (rentals.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há lista para limpar!", "Lista Vazia", JOptionPane.WARNING_MESSAGE);
        } else {
            rentals.clear();
            JOptionPane.showMessageDialog(this, "Lista de aluguéis limpa com sucesso!", "Lista Limpa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void equipmentSelectionAction(ActionEvent e) {
        selectedEquipmentId = equipmentComboBox.getSelectedIndex() + 1;
        String description = (String) equipmentComboBox.getSelectedItem();

        boolean canHaveLesson = !("cadeira de praia".equals(description) || "guarda-sol".equals(description) || "gazebo".equals(description));

        lessonCheckBox.setEnabled(canHaveLesson);
        if (!canHaveLesson) {
            lessonCheckBox.setSelected(false);
            lessonSelected = false;
        }
    }

    private void confirmRentalAction() {
        try {
            String timeText = timeArea.getText();
            if (timeText.isEmpty()) {
                throw new IllegalArgumentException("Tempo de aluguel não informado!");
            }
            if (!timeText.matches("\\d+")) {
                throw new IllegalArgumentException("Tempo de aluguel deve ser um número positivo!");
            }

            int time = Integer.parseInt(timeText);
            if (time < 30) {
                throw new IllegalArgumentException("Tempo de aluguel não pode ser abaixo de 30 minutos!");
            }

            String confirmationStr = rentals.newRental(selectedEquipmentId, time, lessonSelected);
            JOptionPane.showMessageDialog(this, confirmationStr, "Aluguel Confirmado", JOptionPane.INFORMATION_MESSAGE);

            if (autoSave) {
                rentals.saveToFile();
            }
            timeArea.setText("");

        } catch (IllegalArgumentException | InvalidEquipmentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listRentalsAction() {
        if (rentals.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ainda não há aluguéis!", "Lista Vazia", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JDialog listDialog = new JDialog(this, "Lista de Aluguéis", true);
        listDialog.setSize(400, 400);
        listDialog.setLocationRelativeTo(this);

        JTextArea rentalsListArea = new JTextArea(rentals.listAll());
        rentalsListArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(rentalsListArea);
        listDialog.add(scrollPane);
        listDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AmbarSandAPP());
    }
}