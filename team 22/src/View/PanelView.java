/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import functionality.Add;
import functionality.CreateProduct;
import Controller.InventoryController;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JComponent;

/**
 *
 * @author yuchang Sun, junxiu WU
 */
 public class PanelView extends javax.swing.JFrame {

    private InventoryController controller = null;
    private DefaultListModel categoryListModel = new DefaultListModel(), inventoryListModel = new DefaultListModel();

    public PanelView() {
    // Initialize window close operation
    setupCloseOperation();
}

    // Set up the window close operation
    private void setupCloseOperation() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                controller.endProgram();
            }
        });
    }

    /**
     * Initializes and displays the main GUI window.
     */
    public void startGUI() {
        // Initialize form components and GUI elements
        initComponents();
        setupGUIElements();

        // Center the GUI window on screen
        setLocationRelativeTo(null);

        // Set the background color
        setBackground(new java.awt.Color(175, 199, 249));

        // Display the GUI
        setVisible(true);
    }

    /**
     * Set up individual GUI components and visual elements.
     */
    private void setupGUIElements() {
        setPanelGUIs();
        fillCategories();
        homeVisibility();
    }

    // Link the controller to this view.
    public void setController(InventoryController controller) {
        this.controller = controller;
    }

    // Establish connections to the child panels.
    public void setPanelGUIs() {
        pnlAddItem.setGUI(this);
    }

    // Configure the UI components for the home screen.
    public void homeVisibility() {
        controller.swapWindow(InventoryController.GuiWindow.MAIN);

        MainPanel.setVisible(true);
        InventPanel.setVisible(false);
        ReturnButton.setVisible(false);
        mainInventory.clearSelection();
    }

    // Adjust UI components based on category selection.
    public void VIsual() {
        controller.selection.swapEnum(controller, mainInventory.getSelectedValue());
        fillInventory();

        MainPanel.setVisible(false);
        categoryComponentVisibility();
        InventPanel.setVisible(true);
        ReturnButton.setVisible(true);
    }

    // Adjust UI components within the Category panel.
    public void categoryComponentVisibility() {
        Buttonpanel.setVisible(true);
        EditPanel.setVisible(false);
        UpdateSpinner.setValue(0);
        pnlAddItem.setVisible(false);
        pnlAddItem.clearInput();
    }

    // Set the label text to display the current category.
    public void setCategoryLabel(String name) {
        InventoryLabel.setText(name + " Stock:");
    }

    // Populate the Category list.
    private void fillCategories() {
        categoryListModel.addElement(controller.selection.Selection);
    }

    // Update the Inventory list with items from the controller.
    public void fillInventory() {
        inventoryListModel.clear();
        for (String item : controller.getItemName()) {
            inventoryListModel.addElement(item);
        }
    }
    
   // Check if an item is selected in the list.
    private boolean checkItemSelected() {
        if (inventory.getSelectedValue() != null) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "Please make your choice", "ERROR", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    // Verify if item name exists in the current category.
    public boolean checkItemExists(String name) {
        for (String itemName : controller.getItemName()) {
            if (itemName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // Create and add a new product using the provided parameters.
    public void recieveNewProduct(String name, int quantity, double price, String other) {
        CreateProduct factory = new CreateProduct();
        controller.add(factory.createProduct(controller.selection.getCurrentSelection(controller), name, quantity, price, other));
    }

    // Retrieve detailed information for a given product.
    public String showProductInfo(String name) {
        return controller.getStringName(name);
    }

    /**
    * Initializes the form. Auto-generated by the Form Editor; do not modify.
    */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTitle = new javax.swing.JPanel();
        label1 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        MainPanel = new javax.swing.JPanel();
        labelIVT = new javax.swing.JLabel();
        SelectionScroll = new javax.swing.JScrollPane();
        mainInventory = new JList(categoryListModel);
        ReturnButton = new javax.swing.JButton();
        QuitButton = new javax.swing.JButton();
        InventPanel = new javax.swing.JPanel();
        InventoryLabel = new javax.swing.JLabel();
        InventoryScroll = new javax.swing.JScrollPane();
        inventory = new javax.swing.JList<>();
        Buttonpanel = new javax.swing.JPanel();
        AddButton = new javax.swing.JButton();
        btnEditQuantity = new javax.swing.JButton();
        RemoveButton = new javax.swing.JButton();
        EditPanel = new javax.swing.JPanel();
        UpdateSpinner = new javax.swing.JSpinner();
        QuantConfirmButton = new javax.swing.JButton();
        CancelQuantButton = new javax.swing.JButton();
        pnlAddItem = new Add();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        // Set the title of the window.
        setTitle("InventoryManagement");

        // Prevent the window from taking focus automatically.
        setAutoRequestFocus(false);

        // Set the minimum size of the window.
        setMinimumSize(new java.awt.Dimension(500, 500));

        // Ensure the window size cannot be changed by the user.
        setResizable(false);

        // Set the background color of the main panel.
        MainPanel.setBackground(new java.awt.Color(255, 182, 193));

        // Use null layout for the main panel for manual control of component positions.
        MainPanel.setLayout(null);

        // Configure and set the main welcome label.
        labelIVT.setFont(new java.awt.Font("Consolas", 10, 24)); 
        labelIVT.setText("Welcome to inventory");
        MainPanel.add(labelIVT);
        labelIVT.setBounds(10, -10, 300, 80); 

        // Style the inventory list.
        mainInventory.setFont(new java.awt.Font("Consolas", 1, 20));

        // Add a mouse click event to the inventory list.
        mainInventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstCategoriesMouseClicked(evt);
            }
        });

        // Associate the inventory list with its scroll pane.
        SelectionScroll.setViewportView(mainInventory);

        // Add the scroll pane (with the inventory list) to the main panel.
        MainPanel.add(SelectionScroll); 
        SelectionScroll.setBounds(280, 15,120, 29);

        // Configure and set the "RETURN" button.
        ReturnButton.setFont(new java.awt.Font("Times New Roman", 1, 15)); 
        ReturnButton.setText("RETURN");
        ReturnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });
        getContentPane().add(ReturnButton);
        ReturnButton.setBounds(90, 320, 100, 30);

        QuitButton.setFont(new java.awt.Font("Times New Roman", 1, 15)); 
        QuitButton.setText("QUIT");
        QuitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });
        getContentPane().add(QuitButton);
        QuitButton.setBounds(220, 320, 100, 30);//quit button size

        InventPanel.setBackground(new java.awt.Color(255, 182, 193));
        InventPanel.setMinimumSize(new java.awt.Dimension(400, 280));
        InventPanel.setLayout(null);

        inventory.setFont(new java.awt.Font("Times New Roman", 0, 14)); 
        inventory.setModel(this.inventoryListModel); //set up the stock
        inventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InventoryMouseClicked(evt);
            }
        });
        InventoryScroll.setViewportView(inventory);//visualize stock

        InventPanel.add(InventoryScroll);
        InventoryScroll.setBounds(280, 40, 180, 180); // stock scroll location 

        Buttonpanel.setBackground(new java.awt.Color(255, 182, 193));

        AddButton.setBackground(new java.awt.Color(0, 0, 0));
        AddButton.setFont(new java.awt.Font("Times New Roman", 1, 12)); 
        AddButton.setForeground(new java.awt.Color(255, 255, 255)); // Setting the font color to white
        AddButton.setText("ADD");
      
        Border border1 = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3);
        Border border2 = BorderFactory.createEmptyBorder(10, 10, 10, 10); 
        AddButton.setBorder(BorderFactory.createCompoundBorder(border1, border2));
        
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        
        btnEditQuantity.setBackground(new java.awt.Color(0, 0, 0));
        btnEditQuantity.setForeground(new java.awt.Color(255, 255, 255)); 
        btnEditQuantity.setFont(new java.awt.Font("Times New Roman", 1, 12)); //edit quantity
        btnEditQuantity.setText("EDIT QUANTITY");
        btnEditQuantity.setBorder(BorderFactory.createCompoundBorder(border1, border2));
        btnEditQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditQuantityActionPerformed(evt);
            }
        });

        RemoveButton.setBackground(new java.awt.Color(0, 0, 0));
        RemoveButton.setForeground(new java.awt.Color(255, 255, 255)); 
        RemoveButton.setFont(new java.awt.Font("Times New Roman", 1, 12)); //remove product
        RemoveButton.setText("REMOVE Product");
        RemoveButton.setBorder(BorderFactory.createCompoundBorder(border1, border2));
        RemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonsLayout = new javax.swing.GroupLayout(Buttonpanel);
        Buttonpanel.setLayout(pnlButtonsLayout);
        pnlButtonsLayout.setHorizontalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addGap(0, 35, Short.MAX_VALUE)
                .addGroup(pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 35, Short.MAX_VALUE))
        );
        pnlButtonsLayout.setVerticalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnEditQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(RemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        InventPanel.add(Buttonpanel);
        Buttonpanel.setBounds(0, 30, 210, 180);

        QuantConfirmButton.setFont(new java.awt.Font("Times New Roman", 1, 12)); 
        QuantConfirmButton.setText("CONFIRM");
        QuantConfirmButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        QuantConfirmButton.setMaximumSize(new java.awt.Dimension(63, 23));
        QuantConfirmButton.setMinimumSize(new java.awt.Dimension(63, 23));
        QuantConfirmButton.setPreferredSize(new java.awt.Dimension(63, 23));
        QuantConfirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmQuantityActionPerformed(evt);
            }
        });

        CancelQuantButton.setBackground(new java.awt.Color(221, 221, 221));
        CancelQuantButton.setFont(new java.awt.Font("Times New Roman", 1, 12)); 
        CancelQuantButton.setText("CANCEL");
        CancelQuantButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        CancelQuantButton.setMaximumSize(new java.awt.Dimension(63, 23));
        CancelQuantButton.setMinimumSize(new java.awt.Dimension(63, 23));
        CancelQuantButton.setPreferredSize(new java.awt.Dimension(63, 23));
        CancelQuantButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelQuantityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEditQuantityLayout = new javax.swing.GroupLayout(EditPanel);
        EditPanel.setLayout(pnlEditQuantityLayout);
        pnlEditQuantityLayout.setHorizontalGroup(
            pnlEditQuantityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
            .addGroup(pnlEditQuantityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlEditQuantityLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(pnlEditQuantityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlEditQuantityLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(UpdateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(QuantConfirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CancelQuantButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlEditQuantityLayout.setVerticalGroup(
            pnlEditQuantityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
            .addGroup(pnlEditQuantityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlEditQuantityLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(UpdateSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(QuantConfirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(CancelQuantButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        InventPanel.add(EditPanel); 
        EditPanel.setBounds(10, 60, 210, 180);// panel location
        EditPanel.setBackground(new java.awt.Color(255, 182, 193));//panel colour
        InventPanel.add(pnlAddItem);
        pnlAddItem.setBounds(30, 40, 210, 180); 

        getContentPane().add(InventPanel);
        InventPanel.setBounds(0, 0, 500, 240); // second pink panel
        
        //for gifs
      ImageIcon gifIcon = new ImageIcon(getClass().getResource("inventory.gif"));
       if (gifIcon != null) {
            JLabel gifLabel = new JLabel(gifIcon);
            getContentPane().add(gifLabel);
            gifLabel.setBounds(0, 60, getWidth(), getHeight()-100);
        } else {
            System.out.println("Icon not found");
        }

    getContentPane().add(MainPanel);
        
     pack();
    }// </editor-fold>//GEN-END:initComponents

        /**
      * Handles the action for returning to the home screen and clears input fields.
      */
     private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {
         homeVisibility();
         pnlAddItem.clearInput();
     }

     /**
      * Updates the category label and refreshes the visual when a category is clicked.
      */
     private void lstCategoriesMouseClicked(java.awt.event.MouseEvent evt) {
         setCategoryLabel(mainInventory.getSelectedValue());
         VIsual();
     }

     /**
      * Ends the program when the Quit button is clicked.
      */
     private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {
         controller.endProgram();
     }

     /**
      * Handles the action for adding a new item, displays the appropriate input fields based on the current GUI window.
      */
     private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
         Buttonpanel.setVisible(false);

         switch (controller.guiWindow) {
             case INVENTORY:
                 pnlAddItem.setInput("inventory");
                 break;
             default:
                 break;
         }
         pnlAddItem.setVisible(true);
     }

    private void btnEditQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditQuantityActionPerformed
        if (checkItemSelected()) { //error checking
            Buttonpanel.setVisible(false);
            EditPanel.setVisible(true);
        }
    }//GEN-LAST:event_btnEditQuantityActionPerformed

        /**
     * Removes the selected item from inventory after user confirmation.
     */
    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {
        if (checkItemSelected()) { 
            int confirm = JOptionPane.showConfirmDialog(null, "Delete " + inventory.getSelectedValue(), "CONFIRMATION", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) { 
                controller.remove(inventory.getSelectedValue());
            }
        }
    }

    /**
     * Updates the quantity of the selected item.
     */
    private void btnConfirmQuantityActionPerformed(java.awt.event.ActionEvent evt) {
        int newQuantity = controller.getQuantity(inventory.getSelectedValue()) + (int) UpdateSpinner.getValue();

        if (newQuantity < 0) { 
            JOptionPane.showMessageDialog(null, "The resulting quantity is negative. Please re-enter.", "INVALID QUANTITY", JOptionPane.WARNING_MESSAGE);
            return;
        } 

        controller.updateQuantity(inventory.getSelectedValue(), (int) UpdateSpinner.getValue());
        JOptionPane.showMessageDialog(null, inventory.getSelectedValue() + " Updated quantity: " + controller.getQuantity(inventory.getSelectedValue()), "QUANTITY CHANGED", JOptionPane.PLAIN_MESSAGE);

        VIsual();
    }

    /**
     * Resets the UI without changing inventory.
     */
    private void btnCancelQuantityActionPerformed(java.awt.event.ActionEvent evt) {
        VIsual();
    }
    
    private void InventoryMouseClicked(java.awt.event.MouseEvent evt) {
     // Dispose of any existing dialog
     if(itemDialog != null) {
         itemDialog.dispose();
     }

     String itemInfo = showProductInfo(inventory.getSelectedValue());
     String itemTitle = inventory.getSelectedValue().toUpperCase() + " INFO";

     itemDialog = new JDialog();
     itemDialog.setTitle(itemTitle);
     itemDialog.setSize(400, 250);
     itemDialog.setLocationRelativeTo(null);

     JTextArea infoArea = new JTextArea(itemInfo);
     infoArea.setEditable(false);
     infoArea.setWrapStyleWord(true);
     infoArea.setLineWrap(true);
     infoArea.setCaretPosition(0);
     infoArea.setForeground(new Color(50, 50, 50));
     infoArea.setBackground(new Color(255, 255, 255, 123));
     infoArea.setFont(new Font("Arial", Font.BOLD, 14));
     infoArea.setMargin(new Insets(10,10,10,10));

     JScrollPane scrollPane = new JScrollPane(infoArea);
     scrollPane.setBorder(null);
     scrollPane.setOpaque(false); // Make it transparent
     scrollPane.getViewport().setOpaque(false); // Make viewport transparent

     try {
         BufferedImage img = ImageIO.read(PanelView.class.getResource("product.jpg"));
         JLabel background = new JLabel(new ImageIcon(img));
         background.setLayout(new BorderLayout());
         background.add(scrollPane, BorderLayout.CENTER);
         itemDialog.setContentPane(background);
     } catch (IOException ex) {
         System.out.println("Failed to load the background image.");
         ex.printStackTrace();
         JPanel panel = new JPanel(new BorderLayout());
         panel.add(scrollPane, BorderLayout.CENTER);
         itemDialog.setContentPane(panel);
     }

     // Add padding to the content
     ((JComponent) itemDialog.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

     itemDialog.setVisible(true);
 }
    
    
    
    
    private javax.swing.JButton AddButton;
    private javax.swing.JButton CancelQuantButton;
    private javax.swing.JButton QuantConfirmButton;private javax.swing.JButton btnEditQuantity;
    private javax.swing.JButton QuitButton;
    private javax.swing.JButton RemoveButton;
    private javax.swing.JButton ReturnButton;
    private javax.swing.JLabel labelIVT;
    private javax.swing.JLabel InventoryLabel;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JList<String> mainInventory;
    private javax.swing.JList<String> inventory;
    private Add pnlAddItem;
    private javax.swing.JPanel Buttonpanel;
    private javax.swing.JPanel InventPanel;
    private javax.swing.JPanel EditPanel;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JScrollPane SelectionScroll;
    private javax.swing.JScrollPane InventoryScroll;
    private javax.swing.JSpinner UpdateSpinner;
    private JDialog itemDialog;
}
