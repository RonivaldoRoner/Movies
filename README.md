# Projeto: Catálogo de Filmes e Séries

Este projeto é um aplicativo Android que permite aos usuários consultar uma lista de filmes e séries, e armazenar essas informações localmente para acesso offline. Ele foi desenvolvido seguindo os princípios de Clean Architecture e Clean Code para garantir um código organizado, testável e de fácil manutenção.

## Funcionalidades

*   **Consulta de Filmes e Séries:** O aplicativo permite que os usuários pesquisem e visualizem informações sobre filmes e séries.
*   **Armazenamento Local:** As informações de filmes e séries são armazenadas em um banco de dados local para acesso offline. Isso permite que os usuários continuem acessando os dados mesmo quando não houver conexão com a internet.
*   **Visualização Offline:** Os usuários podem visualizar os filmes e séries previamente consultados mesmo sem conexão com a internet.

## Arquitetura

Este projeto utiliza a **Clean Architecture** como base para sua estrutura. Isso significa que o código é dividido em camadas distintas, cada uma com uma responsabilidade específica. As principais camadas são:

*   **Application (Apresentação):** Responsável pela interface do usuário (UI) e pela interação com o usuário. Contém Activities, Fragments, ViewModels, etc.
*   **Domain (Domínio):** Contém as regras de negócio da aplicação. Define as entidades, casos de uso (use cases) e interfaces de repositórios.
*   **Data (Dados):** Responsável pela manipulação de dados, seja de fontes remotas (APIs) ou locais (banco de dados). Utiliza os repositórios definidos na camada de domínio.
*   **Remote (APIs):** Responsável pela manipulação de dados de fontes remotas (APIs). Implementa os repositórios definidos na camada de domínio.
*   **Local (Cache):** Responsável pela manipulação de dados de fontes locais (banco de dados). Implementa os repositórios definidos na camada de domínio.

**Diagrama Simplificado:**

## Padrões e Princípios

*   **Clean Architecture:** Como mencionado, o projeto segue os princípios da Clean Architecture para separação de responsabilidades e independência das camadas.
*   **Clean Code:** O código é escrito seguindo os princípios de Clean Code, como nomes significativos, funções curtas, comentários explicativos (quando necessário), e formatação consistente.
*   **Injeção de Dependência:** Utiliza-se um framework de injeção de dependência (ex: Koin) para gerenciar as dependências entre as camadas e facilitar os testes.
*   **Padrão Repository:** A camada de dados implementa o padrão Repository para abstrair a fonte de dados (remota ou local).
*   **Casos de Uso (Use Cases):** A camada de domínio define os casos de uso, que representam as ações que o usuário pode realizar no aplicativo.
* **MVVM:** O projeto utiliza o padrão arquitetural MVVM (Model-View-ViewModel) na camada de apresentação para separar a lógica de negócios da interface do usuário.

## Tecnologias Utilizadas

*   **Kotlin:** Linguagem de programação principal.
*   **Android Jetpack:** Conjunto de bibliotecas para desenvolvimento Android moderno.
*   **Compose:** Desenvolvimento de view de forma declarativa.
*   **Room:** Biblioteca para persistência de dados local (banco de dados SQLite).
*   **Retrofit:** Biblioteca para comunicação com APIs REST.
*   **Koin:** Framework para injeção de dependência.
*   **Coroutines:** Para programação assíncrona.
*   **Flow:** Para observação de mudanças de dados.
*   **ViewModel:** Para gerenciar dados relacionados à UI.
* **Navigation Component:** Para gerenciar a navegação entre telas.

## Como Executar

1.  Clone o repositório: `git clone [https://github.com/RonivaldoRoner/Movies.git]`
2.  Abra o projeto no Android Studio.
3.  Sincronize o projeto com o Gradle.
4.  Execute o aplicativo em um emulador ou dispositivo físico.

## Estrutura de Pastas

*   **`app/`:** Pode depender de `domain/` e `data/`.
*   **`data/`:** Pode depender de `remote/`, `local/` e `domain/`.
*   **`domain/`:** Não depende de nenhuma outra camada. É a camada mais interna e independente.
*   **`remote/`:** Depende apenas de si mesma.
*   **`local/`:** Depende apenas de si mesma.
* **`remote/` e `local/`:** Implementam as interfaces de `repository/` da camada `domain/`.