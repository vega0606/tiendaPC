package Ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class VistaAlertas extends Vista {
	
	public VistaAlertas() {
        super("Sistema de Alertas");
    }
    
    @Override
    protected void inicializarPanel() {
        // Header
        JPanel headerPanel = crearHeaderPanel();
        
        // Panel izquierdo - Opciones
        String[] sidebarItems = {
            "Todas las Alertas", "Stock Mínimo", "Pagos Pendientes", 
            "Vencimientos", "Configuración"
        };
        JPanel sidebarPanel = crearSidebarPanel(sidebarItems);
        
        // Panel principal - Dashboard de alertas
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainContentPanel.setLayout(new BorderLayout());
        
        // Título
        JLabel titleLabel = new JLabel("Panel de Alertas del Sistema");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        // Panel de filtros
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Filtrar por tipo:"));
        JComboBox<String> filterCombo = new JComboBox<>(new String[]{"Todas", "Stock", "Pagos", "Vencimientos", "Sistema"});
        filterPanel.add(filterCombo);
        
        filterPanel.add(Box.createHorizontalStrut(20));
        filterPanel.add(new JLabel("Prioridad:"));
        JComboBox<String> priorityCombo = new JComboBox<>(new String[]{"Todas", "Alta", "Media", "Baja"});
        filterPanel.add(priorityCombo);
        
        JButton refreshButton = new JButton("Actualizar");
        refreshButton.setBackground(new Color(74, 134, 232));
        refreshButton.setForeground(Color.WHITE);
        filterPanel.add(Box.createHorizontalStrut(20));
        filterPanel.add(refreshButton);
        
        // Paneles de resumen (tarjetas)
        JPanel summaryPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        
        // Tarjeta 1: Alertas críticas
        JPanel card1 = createSummaryCard("Alertas Críticas", "8", new Color(244, 67, 54, 80), Color.RED);
        summaryPanel.add(card1);
        
        // Tarjeta 2: Stock bajo
        JPanel card2 = createSummaryCard("Stock Bajo", "15", new Color(255, 152, 0, 80), Color.ORANGE.darker());
        summaryPanel.add(card2);
        
        // Tarjeta 3: Pagos pendientes
        JPanel card3 = createSummaryCard("Pagos Pendientes", "4", new Color(33, 150, 243, 80), Color.BLUE);
        summaryPanel.add(card3);
        
        // Tarjeta 4: Expirando pronto
        JPanel card4 = createSummaryCard("Expirando Pronto", "6", new Color(76, 175, 80, 80), Color.GREEN.darker());
        summaryPanel.add(card4);
        
        // Tabla de alertas
        String[] columnNames = {"ID", "Tipo", "Descripción", "Fecha", "Prioridad", "Estado", "Acciones"};
        Object[][] data = {
            {"A001", "Stock", "Monitor LG 24\" bajo stock mínimo (3 unidades)", "2025-03-01", "Alta", "Pendiente", ""},
            {"A002", "Pago", "Factura #F-2358 vence en 2 días", "2025-03-02", "Media", "Pendiente", ""},
            {"A003", "Stock", "Teclado Logitech bajo stock mínimo (5 unidades)", "2025-03-01", "Baja", "Atendida", ""},
            {"A004", "Sistema", "Copia de seguridad programada", "2025-03-05", "Media", "Programada", ""},
            {"A005", "Vencimiento", "Licencia de software expira en 15 días", "2025-03-15", "Alta", "Pendiente", ""}
        };
        
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable alertsTable = new JTable(tableModel);
        
        // Colorear la columna de prioridad
        alertsTable.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if ("Alta".equals(value)) {
                    label.setBackground(new Color(244, 67, 54));
                    label.setForeground(Color.WHITE);
                } else if ("Media".equals(value)) {
                    label.setBackground(new Color(255, 152, 0));
                    label.setForeground(Color.WHITE);
                } else if ("Baja".equals(value)) {
                    label.setBackground(new Color(76, 175, 80));
                    label.setForeground(Color.WHITE);
                } else {
                    label.setBackground(Color.WHITE);
                    label.setForeground(Color.BLACK);
                }
                
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });
        
        // Colorear la columna de estado
        alertsTable.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if ("Pendiente".equals(value)) {
                    label.setBackground(new Color(33, 150, 243));
                    label.setForeground(Color.WHITE);
                } else if ("Atendida".equals(value)) {
                    label.setBackground(new Color(76, 175, 80));
                    label.setForeground(Color.WHITE);
                } else if ("Programada".equals(value)) {
                    label.setBackground(new Color(156, 39, 176));
                    label.setForeground(Color.WHITE);
                } else {
                    label.setBackground(Color.WHITE);
                    label.setForeground(Color.BLACK);
                }
                
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(alertsTable);
        
        // Añadir componentes al panel principal
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel headerFilterPanel = new JPanel(new BorderLayout(0, 10));
        headerFilterPanel.add(titleLabel, BorderLayout.NORTH);
        headerFilterPanel.add(filterPanel, BorderLayout.CENTER);
        
        contentPanel.add(headerFilterPanel, BorderLayout.NORTH);
        contentPanel.add(summaryPanel, BorderLayout.PAGE_START);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        mainContentPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Añadir todo al panel principal
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(sidebarPanel, BorderLayout.WEST);
        panel.add(mainContentPanel, BorderLayout.CENTER);
    }
    
    private JPanel createSummaryCard(String title, String value, Color bgColor, Color fgColor) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(bgColor);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(fgColor, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(fgColor);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setForeground(fgColor);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }

}
