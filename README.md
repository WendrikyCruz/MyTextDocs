# MyTextDocs
Editor de Texto para Web, que permite a criação e edição de documentos, através de qualquer browser permitindo ao usuário manter seus textos salvos de maneira remota com a possibilidade de download no formato PDF.
Você pode conferir a aplicação funcionando neste link [MyTextDocs](https://mytextdocs.herokuapp.com/Login), basta criar um usuário e fazer o Login para começar a usar.


**Obs.: O site não está responsivo, por isso recomendo que use um computador para poder ter uma melhor experiência, para conferir um projeto responsivo você pode conferir [AQUI](https://github.com/WendrikyCruz/PaginaResponsiva/blob/master/index.html).**

###### Tecnlogias Utilizadas
O MyTextDocs foi desenvolvido na linguagem Java em sua versão 8 utilizando o framework Spring boot, usando o padrão de arquitetura de software MVC, com JPA para persistência de dados em um banco MySQL, Spring boot Security para fazer o controle da segurança e autentificação no site, o CKEditor como ferramenta para criação e edicão de texto, ITextPDF para converter o HTML criado pelo editor do CKEditor em documentos PDF, o Thymeleaf para "integrar" o front com o back da aplicação e o Bootstrap para fazer o front.

###### Configuração
A ferramenta/IDE utilizada para desenvolver a aplicação foi a IntelliJ IDEA, quanto a configuração, o [arquivo application.properties](https://github.com/WendrikyCruz/MyTextDocs/blob/master/src/main/resources/application.properties) que está na branch master está configurado para ambiente de produção, em um pipeline para automatizar o processo de deploy quando atualizo a mesma aqui no Git, porém, o [arquivo application.properties](https://github.com/WendrikyCruz/MyTextDocs/blob/Desenvolvimento/src/main/resources/application.properties) que está na branch desenvolvimento contém as configurações para rodar a aplicação de maneira local, lembrando que as seguintes propriedades:
'server.port', pode ser configurada na porta de sua preferência e 'spring.datasource.username' e 'spring.datasource.password' devem ter as mesmas credenciais usadas em seu banco local, lembrando que para que tudo funcione bem você deve criar o banco 'mytextdocs' em seu SGBD.

