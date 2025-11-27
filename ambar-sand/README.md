## Ambar Sand

Pequeno projeto com a finalidade de praticar os conceitos de POO. Ambar Sand é um programa que tem a finalidade de gerenciar os aluguéis de equipamentos de praia, salvando as informações dos aluguéis em um arquivo serializado fixo. Foi intencional que o usuário não tivesse grande influência com o local do arquivo serializado, assim como saves em jogos. 

## Estrutura do Diretório

Os três principais paths do diretório são:

- `ambar-sand/src/ambar`: Pasta que armazena o código fonte.
- `ambar-sand/bin/classes/ambar`: Pasta que armazenas as classes compiladas.
- `ambar-sand`: Pasta principal. Aqui é possivel compilar e rodar o programa.

## Dependências

Há alguns requisitos para usar o programa:

- `JDK` : É obrigatório te-lo para compilar e rodar o programa.
- `MAKE` : É opcional que o ambiente do sistema possa usar o makefile, mas caso queira usa-lo é necessário, basta digitar "make" ou "make run". Contudo, é possivel compilar com "javac -d bin/classes src/ambar/*.java" e dar run com "java -cp bin/classes ambar.AmbarSandAPP" (*é preciso estar na pasta principal para executar esses comandos com êxito*).
