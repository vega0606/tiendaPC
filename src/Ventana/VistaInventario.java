package Ventana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaInventario extends Vista{
	public VistaInventario() {
        super("Control de Inventarios");
    }
    
    @Override
    protected void inicializarPanel() {
        // Header
        JPanel headerPanel = crearHeaderPanel();
        
        // Panel izquierdo - Opciones
        String[] sidebarItems = {
            "Productos", "Entradas/Salidas", "Stock Mínimo", 
            "Valoración", "Ajustes"
        };
        JPanel sidebarPanel = crearSidebarPanel(sidebarItems);
        
        // Panel principal - Listado de productos
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainContentPanel.setLayout(new BorderLayout());
        
        // Título
        JLabel titleLabel = new JLabel("Listado de Productos");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Buscar:"));
        JTextField searchField = new JTextField(20);
        searchPanel.add(searchField);
        JButton searchButton = new JButton("Buscar");
        searchButton.setBackground(new Color(74, 134, 232));
        searchButton.setForeground(Color.WHITE);
        searchPanel.add(searchButton);
        
        JButton newProductButton = new JButton("Nuevo Producto");
        newProductButton.setBackground(new Color(74, 134, 232));
        newProductButton.setForeground(Color.WHITE);
        searchPanel.add(Box.createHorizontalStrut(20));
        searchPanel.add(newProductButton);
        
        // Tabla de productos
        String[] columnNames = {"Código", "Producto", "Categoría", "Stock", "Precio Compra", "Precio Venta", "Estado", "Acciones"};
        Object[][] data = {
            {"001", "Laptop HP 15\"", "Electrónicos", 12, "$450.00", "$599.99", "Activo", ""},
            {"002", "Monitor LG 24\"", "Electrónicos", 3, "$120.00", "$189.99", "Bajo stock", ""},
            {"003", "Impresora Epson", "Periféricos", 8, "$85.00", "$129.99", "Activo", ""},
            {"004", "Teclado Logitech", "Periféricos", 15, "$25.00", "$39.99", "Activo", ""},
            {"005", "Mouse Inalámbrico", "Periféricos", 20, "$12.00", "$19.99", "Activo", ""}
        };
        
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable productTable = new JTable(tableModel);
        
        // Colorear la columna de estado
        productTable.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if ("Activo".equals(value)) {
                    label.setBackground(new Color(106, 168, 79));
                    label.setForeground(Color.WHITE);
                } else if ("Bajo stock".equals(value)) {
                    label.setBackground(new Color(255, 173, 51));
                    label.setForeground(Color.WHITE);
                } else {
                    label.setBackground(Color.WHITE);
                    label.setForeground(Color.BLACK);
                }
                
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(productTable);
        
        // Añadir componentes al panel principal
        JPanel headerSearchPanel = new JPanel(new BorderLayout());
        headerSearchPanel.add(titleLabel, BorderLayout.NORTH);
        headerSearchPanel.add(searchPanel, BorderLayout.CENTER);
        
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.add(headerSearchPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        mainContentPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Añadir todo al panel principal
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(sidebarPanel, BorderLayout.WEST);
        panel.add(mainContentPanel, BorderLayout.CENTER);
    }

}
