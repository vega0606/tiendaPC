package Ventana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaPedidos extends Vista{
	
	public VistaPedidos() {
        super("Gestión de Pedidos");
    }
    
    @Override
    protected void inicializarPanel() {
        // Header
        JPanel headerPanel = crearHeaderPanel();
        
        // Panel izquierdo - Opciones
        String[] sidebarItems = {
            "Nuevo Pedido", "Pedidos Pendientes", "Historial de Pedidos", 
            "Estado de Pedidos", "Proveedores"
        };
        JPanel sidebarPanel = crearSidebarPanel(sidebarItems);
        
        // Panel principal - Listado de pedidos
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainContentPanel.setLayout(new BorderLayout());
        
        // Título
        JLabel titleLabel = new JLabel("Estado de Pedidos");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        // Panel de búsqueda y filtros
        JPanel searchPanel = new JPanel(new BorderLayout());
        JPanel searchLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchLeftPanel.add(new JLabel("Buscar:"));
        JTextField searchField = new JTextField(20);
        searchLeftPanel.add(searchField);
        
        searchLeftPanel.add(Box.createHorizontalStrut(20));
        searchLeftPanel.add(new JLabel("Estado:"));
        JComboBox<String> statusCombo = new JComboBox<>(new String[]{"Todos", "Pendiente", "En proceso", "Enviado", "Entregado", "Cancelado"});
        searchLeftPanel.add(statusCombo);
        
        JPanel searchRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton newOrderButton = new JButton("Nuevo Pedido");
        newOrderButton.setBackground(new Color(74, 134, 232));
        newOrderButton.setForeground(Color.WHITE);
        searchRightPanel.add(newOrderButton);
        
        searchPanel.add(searchLeftPanel, BorderLayout.WEST);
        searchPanel.add(searchRightPanel, BorderLayout.EAST);
        
        // Tabla de pedidos
        String[] columnNames = {"N° Pedido", "Fecha", "Proveedor", "Monto", "Estado", "Fecha Entrega", "Acciones"};
        Object[][] data = {
            {"PO-0001", "2025-02-20", "Tecno Electronics SA", "$2,450.00", "Pendiente", "2025-03-15", ""},
            {"PO-0002", "2025-02-22", "Office Supply Inc.", "$750.00", "En proceso", "2025-03-10", ""},
            {"PO-0003", "2025-02-25", "Digital Components", "$1,340.00", "Enviado", "2025-03-05", ""},
            {"PO-0004", "2025-02-28", "Tecno Electronics SA", "$980.00", "Pendiente", "2025-03-20", ""},
            {"PO-0005", "2025-03-01", "Hardware Solutions", "$3,200.00", "Pendiente", "2025-03-22", ""}
        };
        
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable pedidosTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(pedidosTable);
        
        // Panel de resumen
        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        summaryPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        summaryPanel.add(new JLabel("Total Pedidos: 5"));
        summaryPanel.add(new JLabel("Pendientes: 3"));
        summaryPanel.add(new JLabel("En Proceso: 1"));
        summaryPanel.add(new JLabel("Enviados: 1"));
        summaryPanel.add(new JLabel("Valor Total: $8,720.00"));
        
        // Añadir componentes al panel principal
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(searchPanel, BorderLayout.PAGE_START);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(summaryPanel, BorderLayout.SOUTH);
        
        mainContentPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Añadir todo al panel principal
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(sidebarPanel, BorderLayout.WEST);
        panel.add(mainContentPanel, BorderLayout.CENTER);
    }
    /**
     * Devuelve el panel principal de la vista.
     * @return Panel principal con todos los componentes.
     */
    public JPanel getPanel() {
        return panel;
    }
}
