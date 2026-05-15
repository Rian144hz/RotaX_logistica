# RotaX Logística - Sistema de Rastreamento e Auditoria

O **RotaX** é um sistema de processamento de dados logísticos desenvolvido em Java. Ele foca na consolidação de quilometragem de frotas e auditoria de falhas, utilizando as principais estruturas do Java Collections Framework.

## 🚀 Funcionalidades

- **Processamento de CSV:** Leitura automatizada de registros de transporte via `BufferedReader`.
- **Consolidação de Dados:** Soma automática de quilometragem por motorista utilizando `HashMap`.
- **Controle de Frota:** Identificação de veículos únicos através de `HashSet` (evitando duplicidade de placas).
- **Auditoria de Falhas:** Filtragem e listagem de entregas com status de `FALHA` em uma `ArrayList`.
- **Segurança (Custom Exceptions):** Validação de integridade que impede quilometragens negativas.

## 🛠️ Tecnologias e Estruturas Utilizadas

- **Java 21+**
- **Collections Framework:**
    - `Map<Motorista, Double>`: Para vincular e somar KMs.
    - `Set<String>`: Para garantir unicidade das placas.
    - `List<Motorista>`: Para o relatório de auditoria.
- **I/O:** Manipulação de arquivos com `FileReader` e `BufferedReader`.
- **Arquitetura:** Divisão em pacotes (`application`, `model.entities`, `model.exceptions`).

## 📂 Estrutura do Arquivo CSV

O sistema espera um arquivo `.csv` no seguinte formato:
`ID_MOTORISTA,NOME,PLACA,KM,STATUS`

**Exemplo de entrada:**
```csv
1,Matheus,ABC-1234,50.5,SUCESSO
2,Jose Igor,XYZ-9999,30.0,FALHA
3,Valentina,KJM-1010,100.2,SUCESSO
2,Jose Igor,XYZ-9999,15.5,SUCESSO
4,Manoel,BRA-2E19,80.0,SUCESSO
