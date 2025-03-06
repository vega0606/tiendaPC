package Ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



public class VistaClientes extends Vista {

    public VistaClientes() {
        super("Gestión de Clientes y Proveedores");
    }
    
    @Override
    protected void inicializarPanel() {
        // Header
        JPanel headerPanel = crearHeaderPanel();
        
        // Panel izquierdo - Opciones
        String[] sidebarItems = {
            "Clientes", "Proveedores", "Historial Clientes", "Historial Proveedores"
        };
        JPanel sidebarPanel = crearSidebarPanel(sidebarItems);
        
        // Panel principal - Pestañas
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainContentPanel.setLayout(new BorderLayout());
        
        // Pestañas
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Pestaña de Clientes
        JPanel clientesPanel = new JPanel(new BorderLayout());
        
        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new BorderLayout());
        JPanel searchLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchLeftPanel.add(new JLabel("Buscar cliente:"));
        JTextField searchField = new JTextField(20);
        searchLeftPanel.add(searchField);
        JButton searchButton = new JButton("Buscar");
        searchButton.setBackground(new Color(74, 134, 232));
        searchButton.setForeground(Color.WHITE);
        searchLeftPanel.add(searchButton);
        
        JPanel searchRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton newClientButton = new JButton("Nuevo Cliente");
        newClientButton.setBackground(new Color(74, 134, 232));
        newClientButton.setForeground(Color.WHITE);
        searchRightPanel.add(newClientButton);
        
        searchPanel.add(searchLeftPanel, BorderLayout.WEST);
        searchPanel.add(searchRightPanel, BorderLayout.EAST);
        
        clientesPanel.add(searchPanel, BorderLayout.NORTH);
        
        // Crear tabla de clientes con datos
        String[] columnNames = {"ID", "Nombre", "Email", "Teléfono", "Dirección", "Estado", "Acciones"};
        Object[][] data = {
            {"C001", "Juan Pérez", "juan@ejemplo.com", "555-1234", "Calle 123", "Activo", ""},
            {"C002", "María López", "maria@ejemplo.com", "555-5678", "Avenida 456", "Activo", ""},
            {"C003", "Carlos Gómez", "carlos@ejemplo.com", "555-9012", "Plaza 789", "Inactivo", ""},
            {"C004", "Ana Rodríguez", "ana@ejemplo.com", "555-3456", "Bulevar 012", "Activo", ""},
            {"C005", "Pedro Martínez", "pedro@ejemplo.com", "555-7890", "Callejón 345", "Activo", ""}
        };
        
        DefaultTableModel clientesTableModel = new DefaultTableModel(data, columnNames);
        JTable clientesTable = new JTable(clientesTableModel);
        
        // Colorear la columna de estado
        clientesTable.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if ("Activo".equals(value)) {
                    label.setBackground(new Color(106, 168, 79));
                    label.setForeground(Color.WHITE);
                } else if ("Inactivo".equals(value)) {
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
        
        JScrollPane clientesScrollPane = new JScrollPane(clientesTable);
        clientesPanel.add(clientesScrollPane, BorderLayout.CENTER);
        
        // Añadir la pestaña de clientes al panel de pestañas
        tabbedPane.addTab("Clientes", clientesPanel);
        
        // Pestaña de Proveedores
        JPanel proveedoresPanel = new JPanel(new BorderLayout());
        
        // Panel de búsqueda para proveedores
        JPanel provSearchPanel = new JPanel(new BorderLayout());
        JPanel provSearchLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        provSearchLeftPanel.add(new JLabel("Buscar proveedor:"));
        JTextField provSearchField = new JTextField(20);
        provSearchLeftPanel.add(provSearchField);
        JButton provSearchButton = new JButton("Buscar");
        provSearchButton.setBackground(new Color(74, 134, 232));
        provSearchButton.setForeground(Color.WHITE);
        provSearchLeftPanel.add(provSearchButton);
        
        JPanel provSearchRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton newProvButton = new JButton("Nuevo Proveedor");
        newProvButton.setBackground(new Color(74, 134, 232));
        newProvButton.setForeground(Color.WHITE);
        provSearchRightPanel.add(newProvButton);
        
        provSearchPanel.add(provSearchLeftPanel, BorderLayout.WEST);
        provSearchPanel.add(provSearchRightPanel, BorderLayout.EAST);
        
        proveedoresPanel.add(provSearchPanel, BorderLayout.NORTH);
        
        // Crear tabla de proveedores con datos
        String[] provColumnNames = {"ID", "Empresa", "Contacto", "Email", "Teléfono", "Categoría", "Estado", "Acciones"};
        Object[][] provData = {
            {"P001", "Electrónicos S.A.", "Roberto García", "roberto@electsa.com", "555-2468", "Electrónicos", "Activo", ""},
            {"P002", "Periféricos C.A.", "Laura Torres", "ltorres@perifca.com", "555-1357", "Periféricos", "Activo", ""},
            {"P003", "Suministros Tech", "Miguel Díaz", "mdiaz@sumitech.com", "555-3691", "Varios", "Activo", ""},
            {"P004", "Importadora Digital", "Sofía Vargas", "svargas@impdigital.com", "555-4826", "Electrónicos", "Inactivo", ""},
            {"P005", "Tecnología Global", "Fernando Ruiz", "fruiz@tecglobal.com", "555-9753", "Hardware", "Activo", ""}
        };
        
        DefaultTableModel provTableModel = new DefaultTableModel(provData, provColumnNames);
        JTable provTable = new JTable(provTableModel);
        
        // Colorear la columna de estado
        provTable.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if ("Activo".equals(value)) {
                    label.setBackground(new Color(106, 168, 79));
                    label.setForeground(Color.WHITE);
                } else if ("Inactivo".equals(value)) {
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
        
        JScrollPane provScrollPane = new JScrollPane(provTable);
        proveedoresPanel.add(provScrollPane, BorderLayout.CENTER);
        
        // Añadir la pestaña de proveedores al panel de pestañas
        tabbedPane.addTab("Proveedores", proveedoresPanel);
        
        // Añadir el panel de pestañas al panel principal
        mainContentPanel.add(tabbedPane, BorderLayout.CENTER);
        
        // Añadir todo al panel principal
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(sidebarPanel, BorderLayout.WEST);
        panel.add(mainContentPanel, BorderLayout.CENTER);
    }

}
