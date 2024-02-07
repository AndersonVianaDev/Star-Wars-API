# API de Planetas de Star Wars

Bem-vindo à API de Planetas de Star Wars! 🌌 Este projeto foi desenvolvido para atender aos apaixonados pelo universo de Star Wars, permitindo a criação, listagem, busca e remoção de informações sobre os planetas da franquia.

A integração com informações sobre a quantidade de aparições em filmes do planeta criado é automatizada por meio da integração com a API pública do universo Star Wars, acessível em https://swapi.co/.

# Documentação da API com SWAGGER
```bash
   localhost:8080/swagger-ui/index.html#/
```

## Funcionalidades Principais:

- Adicionar um Planeta:
Utilize a rota dedicada para adicionar novos planetas, fornecendo informações essenciais como nome, clima e terreno. Esses dados serão inseridos no banco de dados da aplicação.

- Listar Planetas:
Obtenha uma lista completa de todos os planetas disponíveis.

- Buscar por Nome:
Realize uma busca específica por nome, encontrando detalhes sobre um planeta específico.

- Buscar por ID:
Acesse informações detalhadas de um planeta específico usando seu ID único.

- Remover Planeta:
Remova um planeta do banco de dados, garantindo a integridade das informações.

# Instalação do Projeto
## Pré requisito: Java 17 e Docker Desktop
1. Clone o projeto
```bash
  git clone git@github.com:AndersonVianaDev/Star-Wars-API.git
```
2. Instalar as dependências com o gradle
3. Instalar o Docker
4. Iniciar o database com o docker
```bash
  docker compose up
```

# Autor do projeto
Anderson Palmerim Viana
# Contato 
[![Linkedin](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/anderson-palmerim-6a5a17262/)
