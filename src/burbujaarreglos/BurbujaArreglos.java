/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burbujaarreglos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilmer Petro
 */
public class BurbujaArreglos extends JFrame{

    /**
     * @param args the command line arguments
     */
    private static int[] myArreglo = null;
    private static JButton llenar;
    private static JButton desordenado;
    private static JButton ordenar;
    private static BurbujaArreglos ventana;
    private static JList ldesordenada;
    private static JList lordenada;
    private static JComboBox combo;
    public static void main(String[] args) {
        inicializar();
    }
    private static void inicializar(){
        ventana = new BurbujaArreglos();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setBounds(500, 230, 600, 400);
        ventana.setVisible(true);
        ventana.setResizable(false);
    }
    public BurbujaArreglos()
    {
        setLayout(null);
        if(myArreglo == null){
            llenar = new JButton("LLENAR ARREGLO");
            llenar.setBounds(20, 20, 150, 30);
            llenar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ventana.dispose();
                    llenarArreglo();
                }
            });
            add(llenar);
        }else{
            desordenado = new JButton("MOSTRAR DESORDENADO");
            desordenado.setBounds(20, 20, 220, 30);
            combo = new JComboBox();
            combo.setBounds(20, 60, 220, 30);
            combo.addItem("ORDENAR DE");
            combo.addItem("MENOR A MAYOR");
            combo.addItem("MAYOR A MENOR");
            ordenar = new JButton("ORDENAR");
            ordenar.setBounds(20, 100, 220, 30);
            ordenar.setEnabled(false);
            ldesordenada = new JList();
            ldesordenada.setBounds(250, 20, 100, 150);
            lordenada = new JList();
            lordenada.setBounds(370, 20, 100, 150);
            desordenado.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   ldesordenada.setModel(mostrarArregloDesordenado());
                   ldesordenada.setVisible(true);
                   desordenado.setEnabled(false);
                }
            });
            ordenar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ordenarArreglo();
                    lordenada.setModel(mostrarArregloOrdenado());
                    lordenada.setVisible(true);
                }
            });
            combo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getActionCommand() == "comboBoxChanged"){
                        if(combo.getSelectedIndex() != 0){
                            ordenar.setEnabled(true);
                        }else{
                            ordenar.setEnabled(false);
                        }
                    }
                }
            });
            add(desordenado);
            add(combo);
            add(ordenar);
            add(ldesordenada);
            add(lordenada);
            ldesordenada.setVisible(false);
            lordenada.setVisible(false);
        }
        
    }
    private static void llenarArreglo(){
        myArreglo = new int[5];
        for(int i = 0; i < myArreglo.length; i++){
            String recibido = JOptionPane.showInputDialog("Ingrese un numero");
            if(validarNumero(recibido)){
                myArreglo[i] = Integer.parseInt(recibido.trim());
            }else{
                JOptionPane.showMessageDialog(null, "DEBE DIGITAR UN NUMERO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        inicializar();
    }
    private static DefaultListModel mostrarArregloDesordenado(){
        DefaultListModel<String> model = new DefaultListModel<>();
        for(int  i = 0; i < myArreglo.length; i++){
            model.addElement(Integer.toString(myArreglo[i]));
        }
        return model;
    }
    private static void ordenarArreglo(){
       switch(combo.getSelectedIndex()){
            case 1 :
                menor_mayor();
               break;
            case 2 :
                mayor_menor();
               break;
       }
    }
    private static void mayor_menor(){
        int aux;
        for (int i = 0; i < myArreglo.length; i++) {
            for (int j = 0; j < myArreglo.length - i - 1; j++) {
                if(myArreglo[j + 1] > myArreglo[j]){
                    aux = myArreglo[j + 1];
                   myArreglo[j + 1] =  myArreglo[j];
                   myArreglo[j] = aux;
                }
            }
        }
    }
    private static void menor_mayor(){
        int aux;
        for (int i = 0; i < myArreglo.length; i++) {
            for (int j = 0; j < myArreglo.length - i - 1; j++) {
                if(myArreglo[j + 1] < myArreglo[j]){
                    aux = myArreglo[j + 1];
                   myArreglo[j + 1] =  myArreglo[j];
                   myArreglo[j] = aux;
                }
            }
        }
    }
    private static DefaultListModel mostrarArregloOrdenado(){
        DefaultListModel<String> model = new DefaultListModel<>();
        for(int  i = 0; i < myArreglo.length; i++){
            model.addElement(Integer.toString(myArreglo[i]));
        }
        return model;
    }
    private static boolean validarNumero(String value){
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
