## API de Atendimento para Clínica Médica

### 1. INTRODUÇÃO
Bem-vindo ao repositório da API responsável pela administração do módulo de atendimento em uma aplicação de clínica médica. Esta API simula o registro de atendimentos realizados pelos médicos, mantendo históricos detalhados dos pacientes em prontuários.

API desemvolvida em Spring utilizando as principais tecnologias do ecossistema além de conteinerização e utilização de serviços Cloud como AWS RDS.


### 2. DESCRIÇÃO DO PRODUTO: REQUISITOS FUNCIONAIS
Elencamos abaixo com maior detalhe as seguintes funcionalidades do sistema de atendimento médico:

1. Criar um prontuário: permitirá criar prontuário com os seguintes dados/campos: registro de agenda ou data da consulta, histórico do paciente e prontuário detalhado da consulta, receituário ou receita definida pelo médico na consulta para o paciente e exames recomendados para o paciente realizar. Todos os campos são de preenchimento obrigatório;

2. Editar um prontuário existente: funcionalidade de editar um prontuário que já está no banco de dados. Assim, o médico poderá editar informações como agenda, histórico, receita ou exame que estão em algum prontuário salvo;

3. Listar prontuários: fazer uma lista com todos os prontuários que estão salvos no sistema;

4. Ver um prontuário: olhar um prontuário específico. Essa função só permite a visualização, não é possível editar um prontuário;

5. Excluir um prontuário: deletar do sistema um prontuário salvo.

6. Listar as consultas: permite ao médico listar as consultas cadastradas no sistema.


### 3.PRINCIPAIS TECNOLOGIAS UTILIZADAS
1. Lombok: Utilizado no desenvolvimento para reduzir a verbosidade do código.

2. Spring Web: framework escolhido que permite a criação de APIs REST de forma eficiente.

3. Spring Data JPA: Utilizado para simplificar o acesso a dados em bancos de dados relacionais, proporcionando uma implementação fácil e consistente das operações de persistência.

4. MySQL Driver:Fornece a conectividade entre a aplicação e o banco de dados MySQL, permitindo a execução de consultas e atualizações.

5. Model Mapper: Facilita a conversão de objetos entre diferentes camadas da aplicação, simplificando a transferência de dados.

6. JUnit: Framework de teste unitário que foi utilizado e garantiu a confiabilidade e robustez do código.

7. Swagger: Ferramenta escolhida para gerar a documentação da API, facilitando a compreensão dos endpoints disponíveis e seus parâmetros.

8. SLF4J: API de logging que permite a flexibilidade na escolha da implementação de logging, facilitando a integração com diferentes sistemas de log.

9. Feign: Cliente HTTP declarativo para simplificar a comunicação entre serviços, facilitando a integração com outros sistemas.

10. Flyway: Gerenciador de migração de banco de dados, automatizando o versionamento e aplicação de alterações no esquema do banco de dados.

11. Docker: Utilizado para criar a imagem da aplicação em um contêiner, assegurando isolamento e portabilidade da aplicação.

12. AWS RDS (Amazon Relational Database Service): Serviço de nuvem da AWS de banco de dados relacional gerenciado, escolhido para armazenar o banco de dados da aplicação, oferecendo vantagens como escalabilidade automática, backups automatizados, alta disponibilidade e fácil manutenção.

13. My JSON Server: serviço online que permitiu criar uma API REST simulada a partir de um arquivo JSON.


### 4. DIAGRAMA
![Diagrama do projeto](/diagrama.jpg)

