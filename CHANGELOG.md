# Changelog

Todas as mudanças relevantes deste projeto serão documentadas neste arquivo.

O formato deste arquivo é baseado em [Keep a Changelog](http://keepachangelog.com/en/1.0.0/).


## [1.1.0] - 2018-08-03
### Adicionado
- Imagens em execução no link: https://drive.google.com/open?id=1QKXCBZGbe5cJVNq8CjH_SuR33vTVRmRO
- Arquivos README.md, CHANGELOG.md, LICENSE.md
- Adicionado um ícone na janela principal do Bloco de Anotações
- Adicionado um título nas janelas do Bloco de Anotações (principal, ajuda e sobre)
- Cor do cursor de digitação alterada para cinza escuro
- Tamanho da fonte: A fonte do texto aparece do mesmo tamanho, na numeração e no texto, no Linux e no Windows
- Teclas de atalho no menu 'Arquivo', mas opções Novo, Abrir, Salvar e Salvar Como

### Modificado
- Ajuda -> Sobre: Imagem aparece corretamente no Linux e no Windows
- Ajuda -> Sobre: Alterada a versão para 1.1 na imagem
- Ajuda -> Sobre: Criada uma nova tela 'Sobre' com o novo ícone e novo versão
- Ajuda -> Conteúdo da Ajuda -> Desenvolvedores: Link para o LinkedIn do desenvolvedor
- Numeração no texto: Aparece posicionada corretamente no Linux e no Windows
- Ajuste no alinhamento da numeração
- Verificação de alterações no documento atual, antes de executar as operações do menu 'Arquivo'

### Corrigido
- Arquivo -> Salvar: Ajustado para que as quebras de linha, ao salvar os arquivos, funcionem no Linux e no Windows
- Arquivo -> Salvar Como: Ajustado para que as quebras de linha, ao salvar os arquivos, funcionem no Linux e no Windows
- Ajuda -> Conteúdo da Ajuda: Acentuação funcionando no Linux e no Windows
- Ajuda -> Conteúdo da Ajuda: Links funcionam para serem abertos no navegador quando clicados, embra as mensagens de informação e erro do navegador sejam exibidas no terminal


## [1.0.0] - 2018-07-27
### Adicionado
- Imagens em execução no link: https://drive.google.com/open?id=1QKXCBZGbe5cJVNq8CjH_SuR33vTVRmRO
- Interface de Usuário Gráfica (Graphical User Interface - GUI) através do Swing, possuindo uma área de texto, onde o usuário final pode trabalhar com textos e código fonte de projetos, com as linhas numeradas em ordem sequencial, além de um menu superior, com as opções principais 'Arquivo' e 'Ajuda'
- Arquivo -> Novo: Criar um novo arquivo
- Arquivo -> Abrir: Abrir um arquivo existente
- Arquivo -> Salvar: Salvar as alterações no arquivo atual
- Arquivo -> Salvar como: Salvar as alterações em um arquivo com outro nome
- Arquivo -> Sair: Fechar o Bloco de Anotações
- Ajuda -> Conteúdo da Ajuda: Exibir uma janela onde serão exibidas as informações de ajuda
- Ajuda -> Sobre: Exibir a equipe de desenvolvimento, junto com a empresa e com a versão do Bloco de Anotações
- Controle no menu Arquivo na opção 'Novo', que emite um aviso caso o usuário clique nessa opção e existam alterações no documento atual
- Controle no menu Arquivo na opção 'Abrir', que emite um aviso caso o usuário clique nessa opção e existam alterações no documento atual
- Controle no menu Arquivo para que as opções 'Salvar' e 'Salvar como' fiquem habilitadas apenas quando necessário
- Controle no menu Arquivo na opção 'Sair', que emite um aviso caso o usuário clique nessa opção e existam alterações no documento atual
- Controle do título da janela principal com o nome do arquivo, além do asterisco (*) para notificar alterações