package Ventana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaFacturacion extends Vista {
	public VistaFacturacion() {
        super("Gestión de Facturación");
    }
    
    @Override
    protected void inicializarPanel() {
        // Header
        JPanel headerPanel = crearHeaderPanel();
        
        // Panel izquierdo - Opciones
        String[] sidebarItems = {
            "Nueva Factura", "Consultar Facturas", "Anular Factura", 
            "Resumen de Ventas", "Imprimir Facturas"
        };
        JPanel sidebarPanel = crearSidebarPanel(sidebarItems);
        
        // Panel principal - Formulario de factura
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainContentPanel.setLayout(new BorderLayout());
        
        // Título
        JLabel titleLabel = new JLabel("Nueva Factura");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        // Formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Cliente
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Cliente:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField clienteField = new JTextField(20);
        formPanel.add(clienteField, gbc);
        
        // RUC/NIT
        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        formPanel.add(new JLabel("RUC/NIT:"), gbc);
        
        gbc.gridx = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        JTextField rucField = new JTextField(10);
        formPanel.add(rucField, gbc);
        
        // Fecha
        gbc.gridx = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Fecha:"), gbc);
        
        gbc.gridx = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        JTextField fechaField = new JTextField(10);
        fechaField.setText(java.time.LocalDate.now().toString());
        formPanel.add(fechaField, gbc);
        
        // Tabla de productos
        String[] columnNames = {"Código", "Descripción", "Cantidad", "Precio Unit.", "IVA", "Subtotal", "Acciones"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 6; // La columna de acciones no es editable
            }
        };
        
        // Agregar algunas filas vacías
        for (int i = 0; i < 5; i++) {
            tableModel.addRow(new Object[]{"", "", "", "", "", "", ""});
        }
        
        JTable productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setPreferredSize(new Dimension(0, 200));
        
        // Botones de acción
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(new Color(244, 67, 54));
        cancelButton.setForeground(Color.WHITE);
        
        JButton saveButton = new JButton("Guardar");
        saveButton.setBackground(new Color(74, 134, 232));
        saveButton.setForeground(Color.WHITE);
        
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        
        // Añadir componentes al panel principal
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(formPanel, BorderLayout.PAGE_START);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        mainContentPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Añadir todo al panel principal
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(sidebarPanel, BorderLayout.WEST);
        panel.add(mainContentPanel, BorderLayout.CENTER);
    }

}