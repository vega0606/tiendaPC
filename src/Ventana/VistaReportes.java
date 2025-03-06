package Ventana;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VistaReportes extends Vista {
	public VistaReportes() {
        super("Reportes y Análisis");
    }
    
    @Override
    protected void inicializarPanel() {
        // Header
        JPanel headerPanel = crearHeaderPanel();
        
        // Panel izquierdo - Opciones
        String[] sidebarItems = {
            "Ventas por Período", "Productos Vendidos", "Rentabilidad", 
            "Clientes", "Dashboard"
        };
        JPanel sidebarPanel = crearSidebarPanel(sidebarItems);
        
        // Panel principal - Dashboard
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainContentPanel.setLayout(new BorderLayout());
        
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título y filtros
        JPanel headerPanel2 = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        filterPanel.add(new JLabel("Período:"));
        JTextField startDateField = new JTextField(10);
        JTextField endDateField = new JTextField(10);
        filterPanel.add(startDateField);
        filterPanel.add(new JLabel(" - "));
        filterPanel.add(endDateField);
        
        headerPanel2.add(titleLabel, BorderLayout.WEST);
        headerPanel2.add(filterPanel, BorderLayout.EAST);
        
        // Gráficos (simulados con JPanel)
        JPanel chartsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        
        // Gráfico de barras
        JPanel barChartPanel = new JPanel(new BorderLayout());
        barChartPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JLabel barChartTitle = new JLabel("Ventas Mensuales");
        barChartTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        barChartTitle.setFont(new Font("Arial", Font.BOLD, 14));
        
        JPanel barChart = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                
                int width = getWidth();
                int height = getHeight();
                
                // Ejes
                g2d.setColor(Color.BLACK);
                g2d.drawLine(50, height - 50, width - 50, height - 50); // Eje X
                g2d.drawLine(50, 50, 50, height - 50); // Eje Y
                
                // Datos para el gráfico
                int[] datos = {50, 70, 80, 90, 60, 40};
                String[] meses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun"};
                
                int barWidth = (width - 100) / datos.length;
                int maxValue = 100; // Escala máxima
                
                // Dibujar barras
                for (int i = 0; i < datos.length; i++) {
                    int barHeight = (int)((double)datos[i] / maxValue * (height - 100));
                    int x = 50 + i * barWidth;
                    int y = height - 50 - barHeight;
                    
                    g2d.setColor(new Color(74, 134, 232));
                    g2d.fillRect(x, y, barWidth - 10, barHeight);
                    
                    // Etiquetas eje X
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(meses[i], x + barWidth/2 - 10, height - 30);
                }
            }
        };
        
        barChartPanel.add(barChartTitle, BorderLayout.NORTH);
        barChartPanel.add(barChart, BorderLayout.CENTER);
        
        // Gráfico de pastel
        JPanel pieChartPanel = new JPanel(new BorderLayout());
        pieChartPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JLabel pieChartTitle = new JLabel("Productos más Vendidos");
        pieChartTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pieChartTitle.setFont(new Font("Arial", Font.BOLD, 14));
        
        JPanel pieChart = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                
                int width = getWidth();
                int height = getHeight();
                
                // Datos para el gráfico
                int[] datos = {35, 25, 22, 18};
                Color[] colores = {
                    new Color(74, 134, 232),
                    new Color(106, 168, 79),
                    new Color(241, 194, 50),
                    new Color(204, 65, 37)
                };
                String[] etiquetas = {"Laptops (35%)", "Monitores (25%)", "Impresoras (22%)", "Otros (18%)"};
                
                int x = width / 2;
                int y = height / 2;
                int radio = Math.min(width, height) / 3;
                
                int startAngle = 0;
                
                // Dibujar segmentos
                for (int i = 0; i < datos.length; i++) {
                    int arcAngle = Math.round(360f * datos[i] / 100f);
                    g2d.setColor(colores[i]);
                    g2d.fillArc(x - radio, y - radio, 2 * radio, 2 * radio, startAngle, arcAngle);
                    startAngle += arcAngle;
                }
                
                // Leyenda
                int legendX = 50;
                int legendY = 20;
                
                for (int i = 0; i < etiquetas.length; i++) {
                    g2d.setColor(colores[i]);
                    g2d.fillRect(legendX, legendY + i * 20, 10, 10);
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(etiquetas[i], legendX + 20, legendY + i * 20 + 10);
                }
            }
        };
        
        pieChartPanel.add(pieChartTitle, BorderLayout.NORTH);
        pieChartPanel.add(pieChart, BorderLayout.CENTER);
        
        chartsPanel.add(barChartPanel);
        chartsPanel.add(pieChartPanel);
        
        contentPanel.add(headerPanel2, BorderLayout.NORTH);
        contentPanel.add(chartsPanel, BorderLayout.CENTER);
        
        mainContentPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Añadir todo al panel principal
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(sidebarPanel, BorderLayout.WEST);
        panel.add(mainContentPanel, BorderLayout.CENTER);
    }

}
