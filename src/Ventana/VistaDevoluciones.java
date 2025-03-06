package Ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



public class VistaDevoluciones extends Vista {
	
	public VistaDevoluciones() {
        super("Gestión de Devoluciones");
    }
    
    @Override
    protected void inicializarPanel() {
        // Header
        JPanel headerPanel = crearHeaderPanel();
        
        // Panel izquierdo - Opciones
        String[] sidebarItems = {
            "Nueva Devolución", "Devoluciones Pendientes", "Historial", 
            "Reportes", "Configuración"
        };
        JPanel sidebarPanel = crearSidebarPanel(sidebarItems);
        
        // Panel principal - Formulario de devolución
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainContentPanel.setLayout(new BorderLayout());
        
        // Título
        JLabel titleLabel = new JLabel("Registrar Nueva Devolución");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        // Formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // N° Factura
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("N° Factura:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        JTextField facturaField = new JTextField(10);
        formPanel.add(facturaField, gbc);
        
        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBackground(new Color(74, 134, 232));
        buscarButton.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(buscarButton, gbc);
        
        // Cliente
        gbc.gridx = 3;
        formPanel.add(new JLabel("Cliente:"), gbc);
        
        gbc.gridx = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField clienteField = new JTextField(20);
        clienteField.setEditable(false);
        formPanel.add(clienteField, gbc);
        
        // Fecha factura
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Fecha Factura:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        JTextField fechaFacturaField = new JTextField(10);
        fechaFacturaField.setEditable(false);
        formPanel.add(fechaFacturaField, gbc);
        
        // Fecha devolución
        gbc.gridx = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Fecha Devolución:"), gbc);
        
        gbc.gridx = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        JTextField fechaDevolucionField = new JTextField(10);
        fechaDevolucionField.setText(java.time.LocalDate.now().toString());
        formPanel.add(fechaDevolucionField, gbc);
        
        // Motivo devolución
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Motivo:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        String[] motivos = {"Producto defectuoso", "Error en envío", "Insatisfacción del cliente", "Otros"};
        JComboBox<String> motivoCombo = new JComboBox<>(motivos);
        formPanel.add(motivoCombo, gbc);
        
        // Detalles
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Detalles:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField detallesField = new JTextField(30);
        formPanel.add(detallesField, gbc);
        
        // Tabla de productos a devolver
        String[] columnNames = {"Código", "Producto", "Cantidad Facturada", "Precio Unit.", "Cantidad a Devolver", "Subtotal", "Seleccionar"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        
        // Agregar algunas filas de ejemplo
        tableModel.addRow(new Object[]{"001", "Laptop HP 15\"", 1, "$599.99", 1, "$599.99", true});
        tableModel.addRow(new Object[]{"004", "Teclado Logitech", 2, "$39.99", 1, "$39.99", false});
        
        JTable productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setPreferredSize(new Dimension(0, 200));
        
        // Total a devolver
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.add(new JLabel("Total a devolver: "));
        JLabel totalLabel = new JLabel("$639.98");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalPanel.add(totalLabel);
        
        // Botones de acción
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(new Color(244, 67, 54));
        cancelButton.setForeground(Color.WHITE);
        
        JButton registerButton = new JButton("Registrar Devolución");
        registerButton.setBackground(new Color(74, 134, 232));
        registerButton.setForeground(Color.WHITE);
        
        buttonPanel.add(cancelButton);
        buttonPanel.add(registerButton);
        
        // Añadir componentes al panel principal
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(formPanel, BorderLayout.PAGE_START);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(totalPanel, BorderLayout.PAGE_END);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        mainContentPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Añadir todo al panel principal
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(sidebarPanel, BorderLayout.WEST);
        panel.add(mainContentPanel, BorderLayout.CENTER);
    }

}
