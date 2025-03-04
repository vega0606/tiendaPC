package Ventana;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuLateral {
	 private SistemaFacturacionInventario sistema;
	    
	    public MenuLateral(SistemaFacturacionInventario sistema) {
	        this.sistema = sistema;
	    }
	    
	    public JPanel crearMenuLateral() {
	        JPanel menuPanel = new JPanel();
	        menuPanel.setBackground(new Color(51, 51, 51));
	        menuPanel.setPreferredSize(new Dimension(200, 700));
	        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
	        
	        // Logo o título
	        JLabel titleLabel = new JLabel("SISTEMA");
	        titleLabel.setForeground(Color.WHITE);
	        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
	        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        titleLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
	        menuPanel.add(titleLabel);
