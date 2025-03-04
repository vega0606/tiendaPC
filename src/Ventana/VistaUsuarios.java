package Ventana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class VistaUsuarios extends Vista {
	 public VistaUsuarios() {
	        super("Administración de Usuarios");
	    }
	    
	    @Override
	    protected void inicializarPanel() {
	        // Header
	        JPanel headerPanel = crearHeaderPanel();
	        
	        // Panel izquierdo - Opciones
	        String[] sidebarItems = {
	            "Usuarios", "Roles", "Permisos", 
	            "Actividad", "Configuración"
	        };
	        JPanel sidebarPanel = crearSidebarPanel(sidebarItems);
	        
	        // Panel principal - Listado de usuarios
	        JPanel mainContentPanel = new JPanel();
	        mainContentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
	        mainContentPanel.setLayout(new BorderLayout());
	        
	        // Pestañas
	        JTabbedPane tabbedPane = new JTabbedPane();
	        
	        // Pestaña de Usuarios
	        JPanel usuariosPanel = new JPanel(new BorderLayout());
	        
	        // Título
	        JLabel titleLabel = new JLabel("Gestión de Usuarios");
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
	        
	        JButton newUserButton = new JButton("Nuevo Usuario");
	        newUserButton.setBackground(new Color(74, 134, 232));
	        newUserButton.setForeground(Color.WHITE);
	        searchPanel.add(Box.createHorizontalStrut(20));
	        searchPanel.add(newUserButton);
	        
	        // Tabla de usuarios
	        String[] columnNames = {"ID", "Nombre", "Usuario", "Correo", "Rol", "Estado", "Último Acceso", "Acciones"};
	        Object[][] data = {
	            {"1", "Administrador", "admin", "admin@sistema.com", "Administrador", "Activo", "01/03/2025 08:45", ""},
	            {"2", "Juan Pérez", "jperez", "juan.perez@ejemplo.com", "Vendedor", "Activo", "28/02/2025 15:20", ""},
	            {"3", "María López", "mlopez", "maria.lopez@ejemplo.com", "Vendedor", "Activo", "27/02/2025 10:15", ""},
	            {"4", "Carlos Gómez", "cgomez", "carlos.gomez@ejemplo.com", "Contador", "Inactivo", "15/01/2025 09:30", ""},
	            {"5", "Ana Martínez", "amartinez", "ana.martinez@ejemplo.com", "Almacén", "Activo", "28/02/2025 14:05", ""}
	        };
	        
	        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
	        JTable userTable = new JTable(tableModel);
	        
	        // Colorear la columna de estado
	        userTable.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
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
	        
	        JScrollPane scrollPane = new JScrollPane(userTable);
	        
	        // Añadir componentes al panel de usuarios
	        JPanel headerSearchPanel = new JPanel(new BorderLayout());
	        headerSearchPanel.add(titleLabel, BorderLayout.NORTH);
	        headerSearchPanel.add(searchPanel, BorderLayout.CENTER);
	        
	        usuariosPanel.add(headerSearchPanel, BorderLayout.NORTH);
	        usuariosPanel.add(scrollPane, BorderLayout.CENTER);
	        
	        // Pestaña de Roles
	        JPanel rolesPanel = new JPanel(new BorderLayout());
	        
	        JPanel rolesHeaderPanel = new JPanel(new BorderLayout());
	        JLabel rolesTitleLabel = new JLabel("Gestión de Roles");
	        rolesTitleLabel.setFont(new Font("Arial", Font.BOLD, 17));
	        
	        JPanel rolesSearchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        JButton addRoleButton = new JButton("Nuevo Rol");
	        addRoleButton.setBackground(new Color(74, 134, 232));
	        addRoleButton.setForeground(Color.WHITE);
	        rolesSearchPanel.add(addRoleButton);
	        
	        rolesHeaderPanel.add(rolesTitleLabel, BorderLayout.NORTH);
	        rolesHeaderPanel.add(rolesSearchPanel, BorderLayout.CENTER);
	        
	        // Tabla de roles
	        String[] rolesColumnNames = {"ID", "Nombre", "Descripción", "Usuarios", "Acciones"};
	        Object[][] rolesData = {
	            {"1", "Administrador", "Control total del sistema", "1", ""},
	            {"2", "Vendedor", "Gestión de ventas y clientes", "2", ""},
	            {"3", "Contador", "Acceso a reportes financieros", "1", ""},
	            {"4", "Almacén", "Gestión de inventario", "1", ""}
	        };
	        
	        DefaultTableModel rolesTableModel = new DefaultTableModel(rolesData, rolesColumnNames);
	        JTable rolesTable = new JTable(rolesTableModel);
	        JScrollPane rolesScrollPane = new JScrollPane(rolesTable);
	        
	        rolesPanel.add(rolesHeaderPanel, BorderLayout.NORTH);
	        rolesPanel.add(rolesScrollPane, BorderLayout.CENTER);
	        
	        // Añadir pestañas
	        tabbedPane.addTab("Usuarios", usuariosPanel);
	        tabbedPane.addTab("Roles", rolesPanel);
	        
	        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
	        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	        contentPanel.add(tabbedPane, BorderLayout.CENTER);
	        
	        mainContentPanel.add(contentPanel, BorderLayout.CENTER);
	        
	        // Añadir todo al panel principal
	        panel.add(headerPanel, BorderLayout.NORTH);
	        panel.add(sidebarPanel, BorderLayout.WEST);
	        panel.add(mainContentPanel, BorderLayout.CENTER);
	    }
}
