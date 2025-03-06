package Ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class VistaExportImport extends Vista {
	
	 public VistaExportImport() {
	        super("Importación y Exportación de Datos");
	    }
	    
	    @Override
	    protected void inicializarPanel() {
	        // Header
	        JPanel headerPanel = crearHeaderPanel();
	        
	        // Panel izquierdo - Opciones
	        String[] sidebarItems = {
	            "Exportar Datos", "Importar Datos", "Respaldo", 
	            "Recuperación", "Configuración"
	        };
	        JPanel sidebarPanel = crearSidebarPanel(sidebarItems);
	        
	        // Panel principal - Exportación/Importación
	        JPanel mainContentPanel = new JPanel();
	        mainContentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
	        mainContentPanel.setLayout(new BorderLayout());
	        
	        // Título
	        JLabel titleLabel = new JLabel("Exportación e Importación de Datos");
	        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
	        
	        // Pestañas
	        JTabbedPane tabbedPane = new JTabbedPane();
	        
	        // Pestaña de Exportación
	        JPanel exportPanel = new JPanel();
	        exportPanel.setLayout(new BoxLayout(exportPanel, BoxLayout.Y_AXIS));
	        exportPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	        
	        JLabel exportTitleLabel = new JLabel("Exportar Datos del Sistema");
	        exportTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
	        exportTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        
	        JLabel exportDescLabel = new JLabel("<html>Seleccione las entidades que desea exportar y el formato de destino.<br>Los archivos exportados se guardarán en la carpeta de exportación configurada.</html>");
	        exportDescLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        
	        // Panel de opciones de exportación
	        JPanel exportOptionsPanel = new JPanel();
	        exportOptionsPanel.setLayout(new GridLayout(0, 2, 10, 5));
	        exportOptionsPanel.setBorder(BorderFactory.createTitledBorder("Datos a Exportar"));
	        exportOptionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        exportOptionsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
	        
	        // Checkboxes para entidades
	        exportOptionsPanel.add(new JCheckBox("Productos"));
	        exportOptionsPanel.add(new JCheckBox("Clientes"));
	        exportOptionsPanel.add(new JCheckBox("Proveedores"));
	        exportOptionsPanel.add(new JCheckBox("Transacciones"));
	        exportOptionsPanel.add(new JCheckBox("Usuarios"));
	        exportOptionsPanel.add(new JCheckBox("Configuración"));
	        
	        // Panel de formato
	        JPanel formatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        formatPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        formatPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
	        
	        formatPanel.add(new JLabel("Formato:"));
	        String[] formatos = {"CSV", "XML", "JSON", "Excel (.xlsx)"};
	        JComboBox<String> formatComboBox = new JComboBox<>(formatos);
	        formatPanel.add(formatComboBox);
	        
	        // Panel de destino
	        JPanel destinationPanel = new JPanel(new BorderLayout());
	        destinationPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        destinationPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
	        
	        JPanel destInnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        destInnerPanel.add(new JLabel("Destino:"));
	        JTextField destinationField = new JTextField(30);
	        destinationField.setText("C:\\Exports\\");
	        destInnerPanel.add(destinationField);
	        
	        JButton browseButton = new JButton("Examinar...");
	        destInnerPanel.add(browseButton);
	        
	        destinationPanel.add(destInnerPanel, BorderLayout.CENTER);
	        
	        // Panel de botones de acción
	        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        actionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        actionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
	        
	        JButton exportButton = new JButton("Exportar Datos");
	        exportButton.setBackground(new Color(74, 134, 232));
	        exportButton.setForeground(Color.WHITE);
	        actionPanel.add(exportButton);
	        
	        // Agregar componentes al panel de exportación
	        exportPanel.add(exportTitleLabel);
	        exportPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	        exportPanel.add(exportDescLabel);
	        exportPanel.add(Box.createRigidArea(new Dimension(0, 20)));
	        exportPanel.add(exportOptionsPanel);
	        exportPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	        exportPanel.add(formatPanel);
	        exportPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	        exportPanel.add(destinationPanel);
	        exportPanel.add(Box.createRigidArea(new Dimension(0, 20)));
	        exportPanel.add(actionPanel);
	        
	        // Pestaña de Importación
	        JPanel importPanel = new JPanel();
	        importPanel.setLayout(new BoxLayout(importPanel, BoxLayout.Y_AXIS));
	        importPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	        
	        JLabel importTitleLabel = new JLabel("Importar Datos al Sistema");
	        importTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
	        importTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        
	        JLabel importDescLabel = new JLabel("<html>Seleccione el archivo que desea importar y el tipo de datos.<br>Los datos importados se integrarán al sistema según la configuración establecida.</html>");
	        importDescLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        
	        // Panel de origen
	        JPanel sourcePanel = new JPanel(new BorderLayout());
	        sourcePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        sourcePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
	        
	        JPanel sourceInnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        sourceInnerPanel.add(new JLabel("Archivo:"));
	        JTextField sourceField = new JTextField(30);
	        sourceInnerPanel.add(sourceField);
	        
	        JButton sourceBrowseButton = new JButton("Examinar...");
	        sourceInnerPanel.add(sourceBrowseButton);
	        
	        sourcePanel.add(sourceInnerPanel, BorderLayout.CENTER);
	        
	        // Panel de tipo de datos
	        JPanel dataTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        dataTypePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        dataTypePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
	        
	        dataTypePanel.add(new JLabel("Tipo de Datos:"));
	        String[] tiposDatos = {"Productos", "Clientes", "Proveedores", "Transacciones", "Usuarios", "Configuración"};
	        JComboBox<String> dataTypeComboBox = new JComboBox<>(tiposDatos);
	        dataTypePanel.add(dataTypeComboBox);
	        
	        // Panel de opciones de importación
	        JPanel importOptionsPanel = new JPanel();
	        importOptionsPanel.setLayout(new BoxLayout(importOptionsPanel, BoxLayout.Y_AXIS));
	        importOptionsPanel.setBorder(BorderFactory.createTitledBorder("Opciones de Importación"));
	        importOptionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        importOptionsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
	        
	        JCheckBox replaceCheckBox = new JCheckBox("Reemplazar datos existentes con el mismo ID");
	        replaceCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
	        
	        JCheckBox skipCheckBox = new JCheckBox("Omitir registros que ya existen");
	        skipCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
	        
	        JCheckBox backupCheckBox = new JCheckBox("Crear respaldo antes de importar");
	        backupCheckBox.setSelected(true);
	        backupCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
	        
	        importOptionsPanel.add(replaceCheckBox);
	        importOptionsPanel.add(skipCheckBox);
	        importOptionsPanel.add(backupCheckBox);
	        
	        // Panel de botones de acción
	        JPanel importActionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        importActionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        importActionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
	        
	        JButton validateButton = new JButton("Validar");
	        validateButton.setBackground(Color.LIGHT_GRAY);
	        importActionPanel.add(validateButton);
	        
	        JButton importButton = new JButton("Importar Datos");
	        importButton.setBackground(new Color(74, 134, 232));
	        importButton.setForeground(Color.WHITE);
	        importActionPanel.add(importButton);
	        
	        // Agregar componentes al panel de importación
	        importPanel.add(importTitleLabel);
	        importPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	        importPanel.add(importDescLabel);
	        importPanel.add(Box.createRigidArea(new Dimension(0, 20)));
	        importPanel.add(sourcePanel);
	        importPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	        importPanel.add(dataTypePanel);
	        importPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	        importPanel.add(importOptionsPanel);
	        importPanel.add(Box.createRigidArea(new Dimension(0, 20)));
	        importPanel.add(importActionPanel);
	        
	        // Pestaña de Respaldo
	        JPanel backupPanel = new JPanel();
	        backupPanel.setLayout(new BorderLayout());
	        
	        // Añadir pestañas
	        tabbedPane.addTab("Exportar", exportPanel);
	        tabbedPane.addTab("Importar", importPanel);
	        tabbedPane.addTab("Respaldo", backupPanel);
	        
	        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
	        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	        contentPanel.add(titleLabel, BorderLayout.NORTH);
	        contentPanel.add(tabbedPane, BorderLayout.CENTER);
	        
	        mainContentPanel.add(contentPanel, BorderLayout.CENTER);
	        
	        // Añadir todo al panel principal
	        panel.add(headerPanel, BorderLayout.NORTH);
	        panel.add(sidebarPanel, BorderLayout.WEST);
	        panel.add(mainContentPanel, BorderLayout.CENTER);
	    }

}
