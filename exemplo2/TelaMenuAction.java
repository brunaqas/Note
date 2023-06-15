/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exemplo2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;


public class TelaMenuAction extends JFrame implements ActionListener {
    // Barra de menu e menus
    JMenuBar barraMenu = new JMenuBar();
    JMenu arquivo = new JMenu("Arquivo");
    JMenu editar = new JMenu("Editar");
    JMenu ajuda = new JMenu("Ajuda");

    // Itens do menu Arquivo
    JMenuItem novoArquivo = new JMenuItem("Novo");
    JMenuItem abrirArquivo = new JMenuItem("Abrir");
    JMenuItem salvarArquivo = new JMenuItem("Salvar");
    JMenuItem sair = new JMenuItem("Sair");

    // Itens do menu Editar
    JMenuItem cortar = new JMenuItem("Cortar");
    JMenuItem copiar = new JMenuItem("Copiar");
    JMenuItem colar = new JMenuItem("Colar");
    JMenuItem selecionarTudo = new JMenuItem("Selecionar Tudo");

    // Item do menu Ajuda
    JMenuItem sobre = new JMenuItem("Sobre");

    // Área de texto
    JTextArea areaTexto = new JTextArea();

    TelaMenuAction() {
        setTitle("Aplicativo Notepad");
        setBounds(300, 200, 1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setJMenuBar(barraMenu);

        // Adicionar menus à barra de menu
        barraMenu.add(arquivo);
        barraMenu.add(editar);
        barraMenu.add(ajuda);

        // Adicionar itens ao menu Arquivo
        arquivo.add(novoArquivo);
        arquivo.add(abrirArquivo);
        arquivo.add(salvarArquivo);
        arquivo.add(sair);

        // Adicionar itens ao menu Editar
        editar.add(cortar);
        editar.add(copiar);
        editar.add(colar);
        editar.add(selecionarTudo);

        // Adicionar item ao menu Ajuda
        ajuda.add(sobre);

        JScrollPane telaPrincipalRolagem = new JScrollPane(areaTexto);

        add(telaPrincipalRolagem);

        areaTexto.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);

        // Adicionar listeners de ação aos itens do menu
        novoArquivo.addActionListener(this);
        abrirArquivo.addActionListener(this);
        salvarArquivo.addActionListener(this);
        sair.addActionListener(this);
        cortar.addActionListener(this);
        copiar.addActionListener(this);
        colar.addActionListener(this);
        selecionarTudo.addActionListener(this);
        sobre.addActionListener(this);
    }

    public static void main(String[] args) {
        new TelaMenuAction().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Novo")) {
            // Limpa a área de texto
            areaTexto.setText(null);
        } else if (e.getActionCommand().equalsIgnoreCase("Abrir")) {
            // Abre um diálogo de seleção de arquivo para abrir um arquivo de texto
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos de Texto (*.txt)", "txt"));
            int returnValue = fileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    StringBuilder content = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    // Define o conteúdo do arquivo na área de texto
                    areaTexto.setText(content.toString());
                } catch (IOException ex) {
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Salvar")) {
            // Abre um diálogo de seleção de arquivo para salvar o conteúdo da área de texto em um arquivo de texto
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos de Texto (*.txt)", "txt"));
            int returnValue = fileChooser.showSaveDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    // Escreve o conteúdo da área de texto no arquivo
                    writer.write(areaTexto.getText());
                } catch (IOException ex) {
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Sair")) {
            // Encerra o aplicativo
            System.exit(0);
        } else if (e.getActionCommand().equalsIgnoreCase("Cortar")) {
            // Executa a operação de corte na área de texto (move o texto selecionado para a área de transferência)
            areaTexto.cut();
        } else if (e.getActionCommand().equalsIgnoreCase("Copiar")) {
            // Executa a operação de cópia na área de texto (copia o texto selecionado para a área de transferência)
            areaTexto.copy();
        } else if (e.getActionCommand().equalsIgnoreCase("Colar")) {
            // Executa a operação de colagem na área de texto (cola o conteúdo da área de transferência na posição do cursor)
            areaTexto.paste();
        } else if (e.getActionCommand().equalsIgnoreCase("Selecionar Tudo")) {
            // Seleciona todo o texto da área de texto
            areaTexto.selectAll();
        } else if (e.getActionCommand().equalsIgnoreCase("Sobre")) {
            // Exibe uma caixa de diálogo com informações sobre o aplicativo
            JOptionPane.showMessageDialog(this, "Aplicativo Notepad\nDesenvolvido por [Nome do desenvolvedor]\nUma aplicação de bloco de notas simples.", "Sobre", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
