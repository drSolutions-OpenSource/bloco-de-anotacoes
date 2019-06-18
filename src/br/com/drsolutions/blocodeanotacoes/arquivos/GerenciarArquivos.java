/*
 * Copyright 2018 [Drsolutions Tecnologia em Informática Ltda.-ME].
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.drsolutions.blocodeanotacoes.arquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import javax.swing.JFileChooser;

/**
 * Gerenciar os arquivos do Bloco de Anatoções, sendo capaz de salvar e abrir
 * os arquivos do sistema operacional.
 * 
 * @author Diego Mendes Rodrigues
 * @version 1.1
 */
public class GerenciarArquivos {
    private String erro;
    private String nomeDoArquivo;
    private String caminhoDoArquivo;
    private String conteudo;

    /**
     * Inicializar as propriedades da instância dessa classe.
     */
    public GerenciarArquivos() {
        this.erro = "";
        this.nomeDoArquivo = "";
        this.caminhoDoArquivo = "";
        this.conteudo = "";
    }
    
    /**
     * Salvar um arquivo no sistema operacional.
     * 
     * @param nome String sendo o nome do arquivo
     * @param conteudo String sendo o conteúdo do arquivo
     * @return boolean retornando true caso o arquivo tenha sido salvo, 
     *         ou false caso tenha ocorrido um problema
     */
    public boolean salvar(String nome, String conteudo) {
        /* Criar um novo arquivo e escreve no início dele */
        OutputStream os;
        OutputStreamWriter osw;
        BufferedWriter bw;
        try {
            os = new FileOutputStream(nome);
            osw = new OutputStreamWriter(os, "UTF-8");
            
            bw = new BufferedWriter(osw);
            
            /* Quebra de linha no formato Windows */
            conteudo = conteudo.replaceAll("\n", "\r\n");
            bw.write(conteudo);
            
            bw.close();
        } catch (FileNotFoundException ex) {
            erro = "Falha ao salvar o arquivo " + nome + ".\n\n"
            + "Tente novamente!";
            return false;
        } catch (IOException ex) {
            erro = "Falha ao escrever ou ao encerrar o arquivo " + nome+ ".\n\n"
            + "Tente novamente!";
            return false;
        }
        
        return true;
    }
    
    /**
     * Exibir uma janela para o que usuário selecione o local e o nome do 
     * arquivo que será salvo no sistema operacional. 
     * Depois da seleção, o método salvar(...) é chamdo para salvar o arquivo.
     * 
     * @param conteudo String sendo o conteúdo do arquivo
     * @return boolean retornando true caso o arquivo tenha sido salvo, 
     *         ou false caso tenha ocorrido um problema
     */
    public boolean salvarComo(String conteudo) {
        JFileChooser arquivo = new JFileChooser(); 
        arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        if (arquivo.showSaveDialog(null)==1){
            /* Botão Cancelar foi clicado */
            return false;
        } else {
            /* Botão Salvar foi clicado */
            this.nomeDoArquivo = arquivo.getSelectedFile().getName();
            this.caminhoDoArquivo = arquivo.getSelectedFile().toString();

            return salvar(arquivo.getSelectedFile().toString(), conteudo);
        }
    }
    
    /**
     * Abrir um arquivo do sistema operacional.
     * @return boolean retornando true caso o arquivo tenha sido aberto, 
     *         ou false caso tenha ocorrido um problema
     */
    public boolean abrir() {
        JFileChooser arquivo = new JFileChooser(); 
        arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        if (arquivo.showOpenDialog(null)==1){
            /* Botão Cancelar foi clicado */
            return false;
        } else {
            /* Botão Abrir foi clicado */
            this.nomeDoArquivo = arquivo.getSelectedFile().getName();
            this.caminhoDoArquivo = arquivo.getSelectedFile().toString();

            try {
                // Lendo um arquivo
                InputStream is = new FileInputStream(this.caminhoDoArquivo);
                InputStreamReader ir = new InputStreamReader(is);
                BufferedReader br;

                String s = null;
                try {
                    br = new BufferedReader(ir);
                    do {
                        if (s != null)
                            this.conteudo += (s+"\n");

                    } while ((s = br.readLine()) != null);

                    br.close();
                } catch (IOException ex) {
                    erro = "Falha ao ler o arquivo " + this.nomeDoArquivo + ".\n\n"
                    + "Tente novamente!";
                    return false;
                }  
            } catch (FileNotFoundException ex) {
                erro = "Falha ao abrir o arquivo " + this.nomeDoArquivo + ".\n\n"
                    + "Tente novamente!";
                return false;
            }             
            return true;
        }
    }

    public String getErro() {
        return erro;
    }

    public String getNomeDoArquivo() {
        return nomeDoArquivo;
    }

    public String getCaminhoDoArquivo() {
        return caminhoDoArquivo;
    }

    public String getConteudo() {
        return conteudo;
    }
}
