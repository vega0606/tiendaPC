package Ventana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;


public class VistaTransacciones extends Vista {

	 public VistaTransacciones() {
	        super("Gestión de Transacciones");
	    }
	    
	    @Override
	    protected void inicializarPanel() {
	        // Header
	        JPanel headerPanel = crearHeaderPanel();
	        
	        // Panel izquierdo - Opciones
	        String[] sidebarItems = {
	            "Ventas", "Compras", "Devoluciones", 
	            "Facturas Pendientes", "Historial"
	        };
	        JPanel sidebarPanel = crearSidebarPanel(sidebarItems);
	        
	        // Panel principal - Transacciones
	        JPanel mainContentPanel = new JPanel();
	        mainContentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
	        mainContentPanel.setLayout(new BorderLayout());
	        
	        // Título
	        JLabel titleLabel = new JLabel("Historial de Transacciones");
	        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
	        
	        // Panel de filtros
	        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        filterPanel.add(new JLabel("Periodo:"));
	        JTextField startDateField = new JTextField(10);
	        JTextField endDateField = new JTextField(10);
	        filterPanel.add(startDateField);
	        filterPanel.add(new JLabel(" - "));
	        filterPanel.add(endDateField);
	        
	        JPanel filterPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        filterPanel2.add(new JLabel("Tipo:"));
	        String[] tiposTransaccion = {"Todas", "Ventas", "Compras", "Devoluciones"};
	        JComboBox<String> tipoComboBox = new JComboBox<>(tiposTransaccion);
	        filterPanel2.add(tipoComboBox);
	        
	        filterPanel2.add(Box.createHorizontalStrut(20));
	        filterPanel2.add(new JLabel("Estado:"));
	        String[] estadosTransaccion = {"Todos", "Completada", "Pendiente", "Cancelada"};
	        JComboBox<String> estadoComboBox = new JComboBox<>(estadosTransaccion);
	        filterPanel2.add(estadoComboBox);
	        
	        JButton searchButton = new JButton("Buscar");
	        searchButton.setBackground(new Color(74, 134, 232));
	        searchButton.setForeground(Color.WHITE);
	        filterPanel2.add(Box.createHorizontalStrut(20));
	        filterPanel2.add(searchButton);
	        
	        JButton newTransactionButton = new JButton("Nueva Transacción");
	        newTransactionButton.setBackground(new Color(74, 134, 232));
	        newTransactionButton.setForeground(Color.WHITE);
	        filterPanel2.add(Box.createHorizontalStrut(20));
	        filterPanel2.add(newTransactionButton);
	        
	        // Tabla de transacciones
	        String[] columnNames = {"ID", "Fecha", "Cliente/Proveedor", "Tipo", "Total", "Estado", "Usuario", "Acciones"};
	        Object[][] data = {
	            {"T001", "01/02/2025", "Informática Central", "Venta", "$1,299.99", "Completada", "admin", ""},
	            {"T002", "28/01/2025", "Tech Solutions", "Compra", "$3,450.00", "Completada", "admin", ""},
	            {"T003", "25/01/2025", "Juan Pérez", "Venta", "$599.99", "Completada", "vendedor1", ""},
	            {"T004", "22/01/2025", "María López", "Venta", "$189.99", "Pendiente", "vendedor2", ""},
	            {"T005", "20/01/2025", "Carlos Gómez", "Devolución", "$129.99", "Completada", "admin", ""}
	        };
	        
	        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
	        JTable transactionTable = new JTable(tableModel);
	        
	        // Colorear la columna de estado
	        transactionTable.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	                
	                if ("Completada".equals(value)) {
	                    label.setBackground(new Color(106, 168, 79));
	                    label.setForeground(Color.WHITE);
	                } else if ("Pendiente".equals(value)) {
	                    label.setBackground(new Color(255, 173, 51));
	                    label.setForeground(Color.WHITE);
	                } else if ("Cancelada".equals(value)) {
	                    label.setBackground(new Color(204, 65, 37));
	                    label.setForeground(Color.WHITE);
	                } else {
	                    label.setBackground(Color.WHITE);
	                    label.setForeground(Color.BLACK);
	                }
	                
	                label.setHorizontalAlignment(SwingConstants.CENTER);
	                return label;
	            }
	        });
	        
	        JScrollPane scrollPane = new JScrollPane(transactionTable);
	        
	        // Panel de resumen
	        JPanel summaryPanel = new JPanel();
	        summaryPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	        summaryPanel.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY),
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)
	        ));
	        
	        summaryPanel.add(new JLabel("Total Ventas: $2,089.97"));
	        summaryPanel.add(Box.createHorizontalStrut(20));
	        summaryPanel.add(new JLabel("Total Compras: $3,450.00"));
	        summaryPanel.add(Box.createHorizontalStrut(20));
	        summaryPanel.add(new JLabel("Balance: -$1,360.03"));
	        
	        // Añadir componentes al panel principal
	        JPanel headerFilterPanel = new JPanel(new BorderLayout());
	        headerFilterPanel.add(titleLabel, BorderLayout.NORTH);
	        headerFilterPanel.add(filterPanel, BorderLayout.CENTER);
	        headerFilterPanel.add(filterPanel2, BorderLayout.SOUTH);
	        
	        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
	        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	        contentPanel.add(headerFilterPanel, BorderLayout.NORTH);
	        contentPanel.add(scrollPane, BorderLayout.CENTER);
	        contentPanel.add(summaryPanel, BorderLayout.SOUTH);
	        
	        mainContentPanel.add(contentPanel, BorderLayout.CENTER);
	        
	        // Añadir todo al panel principal
	        panel.add(headerPanel, BorderLayout.NORTH);
	        panel.add(sidebarPanel, BorderLayout.WEST);
	        panel.add(mainContentPanel, BorderLayout.CENTER);
	    }
}
