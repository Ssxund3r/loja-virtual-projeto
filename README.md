<h1>Loja Virtual 🏪</h1> 

> Status: Desenvolvimento! 🟡🟡🟡

### 1. Principais Tecnologias Aplicadas 💻
<ul> <li>JavaEE e Java</li> <li>Spring Boot Rest API</li> <li>Spring Boot MVC</li> <li>Spring Data JPA</li> <li>Spring JDBC Template</li> <li>Spring Mock Teste</li> <li>Java Mail</li> <li>Async e Scheduled</li> <li>SQL e PostgreSQL</li> <li>API RESTfull</li> <li>API de Transportadora</li> <li>API de E-mail Marketing</li> <li>API e PIX, Boleto e Cartão de Crédito</li> <li>Angular 15 e TypeScript</li> <li>Bootstrap, HTML e CSS</li> <li>ChartJS</li> <li>AWS Cloud</li> <li>Aws EC2, RDS, S3, ElasbeanStalk</li> </ul>
<hr>

### 2. Módulos Que serão Desenvolvidos 💻
<ul> <li>Gerenciamento de usuários</li> <li>Gerenciamento de empresa</li> <li>Gerenciamento de nota fiscal de entrada</li> <li>Gerenciamento de fornecedor/marca</li> <li>Gerenciamento de produtos</li> <li>Gerenciamento de clientes</li> <li>Gerenciamento de conta a receber</li> <li>Gerenciamento de conta a pagar</li> <li>Vendas com PIX, Cartão e Boleto</li> <li>Gerenciamento de estoque</li> <li>Envio de ofertas por e-mail</li> <li>Relatórios de produtos e compras</li> <li>Notas fiscais de cliente</li> <li>Recuperação de senha</li> <li>Chat com cliente</li> <li>Relatório de estoque</li> <li>Relatório de compra, carrinho e mais clicados</li> <li>Parte de SAC (Serviço de Atendimento ao Cliente)</li> <li>Fluxo de caixa</li> <li>Loja online e gerenciamento</li> </ul>
<hr>

### 3. Requisitos Funcionais e Não Funcionais 📕🗂️📝

# 1. Gerenciamento de acesso para usuário e cadastro (Gerência o Sistema - Loja).
* O sistema deve ter um usuário master para o desenvolvedor gerenciar a loja virtual
* Deve ser possível adicionar vários acessos/papel/role para o usuário(Acesso a relatório, acesso a cadastros, acesso a nota)
* Os usuários devem ficar amarrados a empresa que trabalham.
* Os usuários devem acessar com login e senha.
* A senha deve ser criptografia no banco
* Não pode cadastrar usuário com mesmo CPF o mesmo login e e-mail.
* Senhas devem ser trocadas a cada 90 dias. 

# 2. Cadastro de empresa.
* Ao cadastrar uma nova empresa, gerar o usuário padrão para enviar por e-mail.
* O cadastro inicial do usuário para empresa deve ter o acesso master/admin.
* Ao cadastrar empresa tem que validar o CNPJ e validar se já existe no banco para não gravar duplicado.
* Deve ser informador todos os dados básicos fiscais como: CNPJ, razão social, nome, responsável, e-mail e endereço completo. 
* Inscrição estadual, categoria. 
* Validar inscrição estadual para não gravar duplicado.


# 3. Cadastro de categorias de produtos. 
* Cadastrar a descrição da categoria
* Ter definido no banco categorias padrões.
* Não permitir cadastrar categoria com mesmo descrição 

# 4. Cadastro de fornecedor. 
* Deve-se cadastrar o fornecedor com seus dados ficais, CNPJ, Inscrição estadual, dados de endereço, dados de contato. 
* Não cadastrar fornecedor com mesmo CNPJ e mesmo nome. 
* Os dados de endereço e dados fiscais devem ser buscados em API's externas.

# 5. Cadastro de produto.
* Nome do produto de forma correta.
* Deve ser informado o tipo da unidade (UN, Peça, Kilo, CX, Litro).
* Não deixar cadastrar o produto com nome igual.
* Ao cadastrar o produto é obrigatório associar a uma categoria.
* Não permitir cadastrar o produto com menos de 10 letras.
* Cadastrar um lista de imagens para o produto.
* Cadastrar descrição completa que pode ser mais de 2000 mil caracteres.
* Ao cadastrar as fotos para o produto, deve ser feito o redimensionamento da imagem para  ficar com tamanho de  600x800.
* Validar a imagem deve ter no máximo 1MB para fazer o upload.
* O produto deve ser associado ao fornecedor na hora do cadastro.
* Também deve ser associado a uma marca.
* Cadastrar a imagem em tamanho real e também em tamanho miniatura.
* Limite mínimo de 3 imagens e no máximo 6.
* Informar a quantidade em estoque.
* Informar a quantidade em estoque.
* Poder cadastrar um quantidade de estoque baixo e/ou esgotado para dar alerta.
* Validar  o estoque antes da venda para ver se possível vender. (Na Venda).
* Poder cadastrar um vídeo do Youtube (Link).

# 6. Cadastro de Cliente (Quem compra pelo sistema - Loja).
*  Dados de endereço completo, podemos usar o para facilitar a pesquisa de CEP.
*  O cliente sempre deverá ter um login e senha.
*  Nome, cpf, telefone, e-mail.

# 7. Contas a receber.
* Clientes que geraram boleto.
* Entra todas as vendas e valores de produtor vendidos.

# 8. Contas a pagar.
* Ex: Pagar os fornecedores de produtos.
* Entra com os dados da nota fiscal do produtos.
* Associar o fornecedor.
* Campo para informar um valor total.
* Campo para informar descontos.
* Campo para informar a data de vencimento.
* Campo para informar a forma de pagamento (Cheque, pix, cartao, boleto).

# 9. Realizar vendas.
* O cliente informar os dados básicos, nome, cpf, e-mail, telefone.
* (Cliente de cadastrar com Facebook ou Google
Essa autentição é feita com Auth2,).
* O cliente informa o CEP e o endereço completo de entrega.
* Pode ter a opção de informar o cupom de desconto.
* Obrigatórios informar o numero da casa, ou prédio.
* Seleciona a forma de pagamento (Cartão, Boleto, PIX).
* Deve ser feito o calculo do frete  de acordo com o cep do cliente.
* O valor do frente deve ser incluído na venda.
* Mostrar para o cliente o numero de dias que o produto demora para ser entregue.
* Finaliza a venda.
* Dar baixo no estoque de todos os produtos comprados., após a autorização de cobrança do cartão de credito.
* Envia e-mail para o cliente dizendo que a compra foi realizada com sucesso, enviar numero do pedido.
* Se a venda for por boleto então o estoque deve ser baixado quando o sistema receber o pagamento do boleto.
* O responsável pela loja virtual recebe o e-mail de venda realizada.

# 10. Item de venda.
* Ligado com o produto
* Será salvo no banco a quantidade que foi vendida.

# 11. Gerenciamento de Estoque/Controle de Estoque.
* Atualizar o estoque do produto de forma manual pela tela do cadastro de produto.
* Atualizar a quantidade de produto quando cadastrar uma nota fiscal de compra de produtos para a loja.

# 12. Cadastro de Formas de Pagamento
* Cadastrar a  forma em texto (Cheque, boleto, cartão, PIX, transferência).

# 13. Envio de ofertas da Loja por E-mail
* (GetResponse e-mail marketing – Até 2000 e-mail é grátis).

# 14. Castrado de cupons de descontos
* No cadastro de cupom deve ser informado a descrição do cupom e o valor de desconto.

# 15. Histórico de Compras
* Relatório das compras de produtos feitas com os fornecedores da loja virtual.
* Intervalo de datas.

# 16. Relatórios de Produtos Vendidos
* Relatório de produto vendidos pela loja virtual. 
* Ter a opção de intervalo de datas. 
* Poder informar uma descrição de produto. 

# 17. Nota Fiscal do Cliente 
* Enviar a nota fiscal do cliente para o e-mail dele, após ter recebido o pagamento da venda. 

# 18. Recuperação de senha 
* O cliente deve informar o e-mail e senha deve ser enviada para ele no e-mail.

# 19. Chat do Cliente com o Suporte 
* Para ter um chat profissional vamos aplicar o JivoChat.

# 20. Cadastro de Marca
* Deve ser informada a descrição da marca do produto. 

# 21. Relatórios de Estoque Baixo (Reposição)
* Realizar o relatório por intervalo de datas.
* Poder selecionar o produto para imprimir relatório. 

# 22. Devolução / Troca de Produtos 

# 23. Avaliação de Produtos 
* O cliente deve estar logado.
* O cliente tem que ter comprado o produto realmente para poder avaliar o produto.
* O cliente pode escrever a avaliação do produto em nossa página.
* Depois que a pessoa receber o produto em casa, será enviado um e-mail de pedido de avaliação do produto.

# 24. Relatórios de Compra Cancelada.
* Esse relatório serve para a equipe da loja virtual entrar em contato com os clientes para finalizar a compra porque se foi cancelada é porque o cliente teve problema para finalizar a compra.

# 25. Relatório de Carrinho/Checkout Abandonados
* Esse relatório é para a equipe entrar em contato com o cliente para finalizar a compra.

# 26. Integração com Logística 
* Vamos usar o correios ou outra API de logística.

# 27. Relatórios de Produto em Evidência (Mais clicados, mais comprados, favoritos)
* Gravar a quantidade de clique que os clientes submetem em cima do produtos.

# 28. Parte de SAC
* Temos que mostrar os dados de contato no site para oferecer o atendimento ao cliente.

# 29. Fluxo de Caixa
# 30. Abertura e Fechamento de Caixa
# 31. Mapear Vendas de Produto por Região.
# 32. Relatórios por Marcas
# 33. Bônus para Fidelidade
# 34. Pesquisar sobre: Melhor Envio

<hr>

> ### Loja Virtual - ER 

![SCREEN_DIAGRAMA_LOJA_VIRTUAL](https://github.com/Ssxund3r/loja_virtual_project/assets/76450972/95a28aef-266d-468c-9288-f27390e46540)

<hr>
