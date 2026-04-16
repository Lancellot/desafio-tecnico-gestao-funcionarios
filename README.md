# 🏭 Sistema de Gestão de Funcionários — Indústria

Projeto desenvolvido como **teste prático de programação Java**, implementando um sistema de gestão de funcionários com operações de listagem, agrupamento, ordenação e cálculos salariais.

---

## 📁 Estrutura do Projeto

```
desafio-tecnico-gestao-funcionarios/
└── src/
    └── main/
        └── java/
            └── com/
                └── industria/
                    ├── Pessoa.java          # Classe base com nome e data de nascimento
                    ├── Funcionario.java     # Estende Pessoa com salário e função
                    └── Principal.java       # Classe main com todas as operações
```

---

## 🚀 Como Executar

### Pré-requisito
- **Java 11+** instalado ([Download JDK](https://adoptium.net/))

### Via terminal

```bash
# 1. Descompacte o projeto e entre na pasta
cd projeto-java

# 2. Compile os arquivos
javac -d out src/main/java/com/industria/*.java

# 3. Execute
java -cp out com.industria.Principal
```

### Via IntelliJ IDEA (recomendado)

1. Baixe o [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download) (gratuito)
2. Abra o IntelliJ → **Open** → selecione a pasta `projeto-java`
3. Abra o arquivo `Principal.java`
4. Clique na **seta verde ▶️** ao lado do método `main`

### Via Eclipse

1. **File → Import → Existing Projects into Workspace**
2. Selecione a pasta `projeto-java`
3. Abra `Principal.java` → botão direito → **Run As → Java Application**

---

## 📋 Funcionalidades Implementadas

| # | Requisito | Descrição |
|---|-----------|-----------|
| 3.1 | Inserção | Todos os funcionários inseridos na lista na ordem da tabela |
| 3.2 | Remoção | Funcionário **João** removido da lista |
| 3.3 | Listagem formatada | Data no formato `dd/MM/yyyy`, salário com separador de milhar (`.`) e decimal (`,`) |
| 3.4 | Aumento salarial | 10% de aumento aplicado a todos os funcionários |
| 3.5 | Agrupamento | Funcionários agrupados por função em um `Map<String, List<Funcionario>>` |
| 3.6 | Listagem por função | Impressão dos funcionários agrupados por função |
| 3.8 | Aniversariantes | Funcionários com aniversário em **outubro (10)** e **dezembro (12)** |
| 3.9 | Maior idade | Nome e idade do funcionário mais velho |
| 3.10 | Ordem alfabética | Lista de funcionários ordenada pelo nome |
| 3.11 | Total de salários | Soma de todos os salários |
| 3.12 | Salários mínimos | Quantidade de salários mínimos (R$ 1.212,00) que cada funcionário recebe |

---

## 🧱 Modelagem das Classes

```
Pessoa
├── nome: String
└── dataNascimento: LocalDate

Funcionario (extends Pessoa)
├── salario: BigDecimal
└── funcao: String
```

---

## 🛠️ Tecnologias e Conceitos Utilizados

- **Java 11+** — linguagem principal
- **BigDecimal** — precisão em cálculos monetários (evita erros de ponto flutuante)
- **LocalDate + Period** — datas e cálculo de idade
- **Stream API** — `filter`, `sorted`, `groupingBy`, `reduce`, `min`
- **NumberFormat + Locale pt-BR** — formatação de valores monetários
- **DateTimeFormatter** — formatação de datas no padrão brasileiro
- **Collections (List, Map, ArrayList)** — estruturas de dados
- **Herança** — `Funcionario extends Pessoa`

---

## 💡 Exemplo de Saída

```
✔ Funcionário João removido da lista.

══════════════════════════════════════════════════════
  3.3 – LISTA DE FUNCIONÁRIOS
══════════════════════════════════════════════════════
  Nome: Maria        | Nascimento: 18/10/2000 | Salário: R$        2.009,44 | Função: Operador
  Nome: Caio         | Nascimento: 02/05/1961 | Salário: R$        9.836,14 | Função: Coordenador
  ...

══════════════════════════════════════════════════════
  3.11 – TOTAL DOS SALÁRIOS
══════════════════════════════════════════════════════
  Total: R$ 50.906,82
```

---

## 👤 Autor
Assis Pire Neto

Desenvolvido como parte do processo seletivo — Teste Prático de Programação Java.
