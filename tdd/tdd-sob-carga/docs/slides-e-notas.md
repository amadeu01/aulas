# Slides e notas do facilitador

Texto de cada slide, na ordem do deck, com as notas do apresentador. Gerado a partir do deck da aula.

---

## Abertura: o que vamos construir e como o dojo funciona

### 1. TDD sob carga

Coding Dojo · Java 24 · Os ciclos do TDD, de Uncle Bob

Do pedido de produto ao código em produção, teste a teste

Amadeu Cavalcante · 28/09/2026

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Capa. Apresente o tema em uma frase: vamos aprender a transformar um pedido de produto em testes, e os testes em código, sempre em passos pequenos. Java é só a linguagem; o assunto é o método.
> COMO CONDUZIR: Boas-vindas (2 min). A proposta: aprender TDD fazendo, não assistindo. O foco é transformar o que o produto pede em testes que guiam o código e provam que a lógica é robusta. O domínio (progressão de cargas no powerlifting) é só uma brincadeira: tem números, limites e regras que brigam — como qualquer ticket real. O código é Java 24; quando um recurso não existir no 21, eu aviso. Não é uma aula de Java: é uma aula de como pensar em passos pequenos guiados por testes.

### 2. Como vamos usar o tempo

Roteiro · 2 horas

0:00 · 10 min

**Abertura**

Aquecimento e acordos do dojo

0:10 · 15 min

**Ciclos do TDD**

Uncle Bob, Java 24 e o setup

0:25 · 15 min

**Do pedido ao teste**

Ticket, critérios, arquitetura e dublês

0:40 · 60 min

**Dojo**

Planejar os testes e 5 rodadas de código

1:40 · 10 min

**Robustez**

Bordas, propriedades e mutação

1:50 · 10 min

**Retro**

O que aprendemos e desafios

O fio da aula: pedido de produto → exemplos → testes → código → confiança na lógica.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: O roteiro das 2 horas em seis blocos. A turma precisa saber que metade da aula é prática (o dojo). A frase de baixo resume a aula inteira: pedido de produto, exemplos, testes, código e, no fim, confiança de que a lógica está certa.
> COMO CONDUZIR: Powerlifting é só o pretexto; o fio condutor é transformar o que o produto pede em testes que guiam o código e provam que a lógica aguenta. O dojo continua sendo o maior bloco. Se atrasar, a parte de robustez pode virar só a demonstração de mutação (5 min), e as propriedades ficam como desafio.

### 3. Levanta a mão: quem já escreveu o teste antes do código?

Pergunta para a turma

E quem já recebeu um ticket que parecia claro até a hora de escrever o código?

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Pergunta de abertura para quebrar o gelo e medir a turma. A primeira mede a experiência com TDD. A segunda lembra um problema que todo mundo já viveu, o requisito que parecia claro e não era; é exatamente esse problema que a aula ensina a resolver.
> COMO CONDUZIR: Conte as mãos nas duas perguntas — isso calibra a aula. Peça para 1 ou 2 pessoas contarem um caso de requisito ambíguo que virou bug. Explique o pretexto: vamos usar powerlifting como domínio só porque é divertido e cheio de números e limites; ninguém precisa entender de treino. Guarde as respostas: vamos voltar a elas na retro.

### 4. Como funciona o dojo

Formato Randori

**Piloto**

Digita e pensa em voz alta.

**Copiloto**

Ajuda o piloto e assume o teclado na próxima troca.

**Plateia**

Pergunta a qualquer hora. Sugere código só com a barra verde.

**Acordos**

- Troca de piloto a cada 5 minutos
- Nenhuma linha de produção sem um teste vermelho
- Teste vermelho é bem-vindo: é informação
- O erro é do grupo, nunca de quem digita
- Commit a cada barra verde

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Explica o formato coding dojo Randori: um computador no projetor e a turma inteira programando junta. O piloto digita, o copiloto ajuda e assume o teclado na próxima troca, a plateia observa e pergunta. Os acordos existem para ninguém ter medo de errar em público e para manter o ritmo do TDD. A regra de só sugerir código com a barra verde evita dez pessoas ditando ao mesmo tempo enquanto o teste está quebrado.
> COMO CONDUZIR: Leia os acordos em voz alta e peça um "combinado?" da turma. Deixe um timer visível de 5 minutos. Dica: o copiloto de agora é o piloto da próxima troca, então ninguém chega frio no teclado. Se a plateia começar a ditar código com a barra vermelha, lembre do acordo com leveza.

---

## Os ciclos do TDD segundo Uncle Bob, as três formas de ficar verde e o setup Java 24

### 5. Os ciclos do TDD

Parte 1 · 15 min

Segundos, minutos, dez minutos e uma hora, segundo Uncle Bob

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Divisória da parte 1: a teoria mínima de TDD, usando o artigo "The Cycles of TDD", do Uncle Bob, como fio condutor.
> COMO CONDUZIR: Transição rápida. A parte 1 segue o artigo "The Cycles of TDD" (Robert C. Martin, 2014) e fecha com o que precisamos de Java para o dojo. Frase de efeito opcional: "no treino ninguém pula direto para o 1RM; no código também não".

### 6. O TDD nos livros do Uncle Bob

Robert C. Martin · Uncle Bob

2008 · Cap. 9, Testes de unidade

**Clean Code**

As três leis e a ideia de que teste sujo é pior que nenhum teste. Testes seguem o F.I.R.S.T.: rápidos, independentes, repetíveis, autoverificáveis e escritos na hora certa.

2011 · Cap. 5, TDD

**The Clean Coder**

TDD como disciplina profissional. Os ganhos: certeza, menos defeitos, coragem para refatorar, testes como documentação e um design melhor.

2021 · Parte I, As disciplinas

**Clean Craftsmanship**

Capítulos de TDD, TDD avançado, design de testes e refatoração, com katas passo a passo em Java e JUnit.

Base desta aula: o artigo “The Cycles of TDD” (blog.cleancoder.com, 2014).

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Situa quem é Uncle Bob (Robert C. Martin) e onde ele escreveu sobre TDD. Não precisa ler os cards: diga que são três livros, cada um com um ângulo. Clean Code ensina como um bom teste deve ser; The Clean Coder defende o TDD como postura profissional; Clean Craftsmanship ensina a praticar passo a passo. Fica como referência para quem quiser se aprofundar.
> COMO CONDUZIR: Uncle Bob conta que aprendeu TDD com Kent Beck em 1999. Os três livros mostram uma progressão: o Clean Code explica como os testes devem ser; o Clean Coder defende o TDD como parte do profissionalismo; o Clean Craftsmanship ensina a prática em detalhe, com katas (pilha, fatores primos, jogo de boliche) que servem de continuação para este dojo. Pergunte: "alguém já leu algum deles? O que ficou?"

### 7. Quatro ciclos, quatro relógios

Uncle Bob · The Cycles of TDD (2014)

Nano

Segundos

**As três leis**

Linha a linha, alternando teste e produção.

Micro

Minutos

**Red, green, refactor**

Uma volta completa por teste de unidade.

Milli

~10 min

**Específico → genérico**

O código está generalizando ou só decorando os testes?

Primário

~1 hora

**Fronteiras**

Pare e olhe a arquitetura: as regras estão no lugar certo?

No dojo: nano e micro em todas as rodadas, milli no meio de cada uma, primário ao fim da hora.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: A ideia central do artigo: TDD não é um ciclo só, são quatro ciclos, um dentro do outro, cada um com seu relógio. A cada poucos segundos você alterna entre teste e código (nano). A cada poucos minutos fecha um vermelho-verde-refatorar (micro). A cada uns 10 minutos confere se o código está ficando genérico (milli). A cada hora para e olha a arquitetura (primário). Os próximos slides detalham cada um.
> COMO CONDUZIR: Este é o mapa da aula. A ideia do artigo: TDD não é um ciclo só, são ciclos aninhados em escalas de tempo diferentes. O nano (as leis) dá cerca de uma dúzia de voltas dentro de cada micro (red-green-refactor). O milli é uma checagem periódica de generalização. O primário é a pausa para olhar a arquitetura — vamos fazê-la de verdade ao fim do dojo. Pergunte: "qual desses relógios vocês acham que mais falta no dia a dia?"

### 8. As três leis do TDD

Ciclo nano · segundo a segundo · Clean Code, cap. 9

1

Não escreva código de produção antes de ter um teste de unidade que falha.

2

Não escreva mais teste do que o suficiente para falhar. Não compilar também é falhar.

3

Não escreva mais código de produção do que o suficiente para passar o teste que está falhando.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: O ciclo mais curto, de segundos. As três leis dizem, na prática: só escreva código de produção quando houver um teste falhando; escreva do teste só o suficiente para ele falhar; escreva do código só o suficiente para ele passar. O efeito é você alternar entre teste e código a cada poucos segundos, sem nunca ficar muito tempo sem saber se o que escreveu funciona.
> COMO CONDUZIR: Segundo "The Cycles of TDD", este é o ciclo nano: você alterna entre teste e produção a cada poucos segundos, e passa por essas leis cerca de uma dúzia de vezes para cada teste de unidade. A lei 2 é a que mais surpreende: em Java, referenciar uma classe que ainda não existe já é um teste vermelho (erro de compilação). Use a IDE para gerar a classe a partir do teste (Alt+Enter / Ctrl+1). As leis aparecem no Clean Code (cap. 9) e no The Clean Coder (cap. 5). Pergunte: "qual dessas leis vocês acham mais difícil de seguir?"

### 9. Vermelho, verde, refatorar

Ciclo micro · minuto a minuto

1 · Falha

**Vermelho**

Escreva um teste pequeno que falha. Ele descreve o próximo comportamento.

2 · Passa

**Verde**

Faça passar com o mínimo de código. Vale até devolver uma constante.

3 · Limpa

**Refatorar**

Melhore código e testes com a barra verde. Nenhum comportamento novo.

“Make it work. Make it right.” Kent Beck. Primeiro correção, depois estrutura: uma coisa de cada vez.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: O ciclo de minutos, o famoso vermelho-verde-refatorar. Vermelho: escreva um teste que falha e descreve o próximo comportamento. Verde: faça passar com o mínimo de código, mesmo que feio. Refatorar: agora sim, limpe o código com a segurança dos testes, sem mudar o comportamento. O truque é separar "fazer funcionar" de "deixar bonito"; tentar as duas coisas ao mesmo tempo é o que trava a gente.
> COMO CONDUZIR: Em "The Cycles of TDD", este é o ciclo micro: uma volta por teste de unidade. O argumento de Uncle Bob: a mente não consegue perseguir ao mesmo tempo o comportamento correto e a estrutura correta, então separamos os dois — primeiro faz funcionar, depois deixa certo. E a refatoração é contínua, a cada volta, não uma fase no fim do projeto. Pergunta rápida: "por que ver o teste falhar primeiro?" Resposta esperada: para provar que o teste consegue falhar — um teste que nunca fica vermelho não protege nada. Paralelo com o treino: é a série de aquecimento que confirma que a técnica está lá antes de pôr peso.

### 10. Dois caminhos até o verde

Kent Beck · TDD by Example · teste: roundDown(101.2) deve dar 100.0

Caminho A

Não sei a solução

1. Finja (fake it)

```java
return 100.0;
```

Verde, mas fingindo

2. Segundo exemplo (triangular)

```
roundDown(104.9) == 102.5
```

Qualquer carga cuja resposta não seja 100

Vermelho: o código devolve 100.0

3. Generalize

```
floor(kg/2.5)*2.5
```

Os dois verdes: fim

Caminho B

Já sei a solução

1. Escreva direto

```
floor(kg/2.5)*2.5
```

Verde de primeira: fim

Ficou vermelho e você não entende por quê?

O passo foi grande demais. Apague a fórmula e volte para o caminho A, começando pelo passo 1.

Os dois caminhos chegam ao mesmo código. Muda só o tamanho do passo. Depois do verde: refatorar e pegar o próximo teste da lista.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: responde a uma pergunta prática do passo verde — quanto código eu escrevo para o teste passar? Depende de quanto você já sabe da solução. O exemplo é o da rodada 2: o teste diz que roundDown(101.2) deve dar 100.0 (arredondar para baixo em múltiplos de 2,5 kg).
> CAMINHO A, quando você NÃO sabe a solução (passos pequenos, três etapas):
> 1) Finja (fake it): escreva return 100.0. Parece trapaça, mas o teste fica verde em segundos e você confirma que o teste, a classe e o método estão ligados certo. Ninguém entrega isso: é um degrau.
> 2) Escreva um segundo teste (triangular): roundDown(104.9) deve dar 102.5. De onde veio o 104,9? É só um segundo exemplo, escolhido de propósito para ter uma resposta DIFERENTE de 100 (qualquer carga entre 102,5 e 104,99 serve; 104,9 fica logo abaixo de 105, então arredondando para baixo dá 102,5). Se o segundo exemplo também desse 100, ele não derrubaria o fingimento. O return 100.0 não serve mais, então esse teste fica vermelho. "Triangular" vem da navegação: com dois pontos de referência você descobre a posição; com dois exemplos você descobre a regra.
> 3) Generalize: agora você é forçado a escrever a regra de verdade, Math.floor(kg / 2.5) * 2.5, e os dois testes ficam verdes. Esse é o fim do caminho para essa regra.
> CAMINHO B, quando você JÁ sabe a solução: pule o fingimento e escreva a fórmula direto no primeiro teste (Kent Beck chama de "implementação óbvia"). É mais rápido. Mas se o teste ficar vermelho e você não entender por quê, o passo foi grande demais: volte para o caminho A.
> Os dois caminhos terminam no MESMO código. A diferença é só o tamanho do passo. Kent Beck compara com as marchas de um carro: na subida difícil (não sei a solução) você usa marcha baixa, passos pequenos; na reta conhecida, marcha alta.
> Depois do verde, em qualquer caminho: refatore (por exemplo, trocar 2.5 por uma constante) e pegue o próximo teste da lista (valor exato 102.5, carga negativa).
> COMO CONDUZIR: pergunte "qual caminho vocês costumam usar?" Quase todo mundo usa o B sempre — e é aí que nascem bugs, porque às vezes a gente só acha que sabe a solução. No dojo vamos fazer o caminho A pelo menos uma vez (rodada 2), para todos sentirem o ritmo.

### 11. Testes específicos, código genérico

Ciclo milli · a cada ~10 minutos

“À medida que os testes ficam mais específicos, o código fica mais genérico.”

Testes: exemplos

```
101.2 → 100.0
104.9 → 102.5
102.5 → 102.5
```

Específico demais

```java
if (kg == 101.2)
  return 100.0;
if (kg == 104.9) ...
```

Genérico

```
Math.floor(kg / 2.5)
  * 2.5
```

Travou? Apague o último teste e procure outro caminho.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: O ciclo de uns 10 minutos. Pare e olhe o que você escreveu: o código resolveria um caso que você ainda não testou, ou só decorou os exemplos? O card vermelho mostra o erro típico, um if para cada teste. O verde mostra a regra geral, que funciona para qualquer carga. Se todo teste novo obriga a reescrever tudo, você se enfiou num beco: apague os últimos passos e tente outro caminho.
> COMO CONDUZIR: Este é o mantra do ciclo milli em "The Cycles of TDD". A cada dez minutos, mais ou menos, pergunte: meu código de produção resolveria um caso que ainda não testei? Se a resposta é não, você está só decorando os testes com ifs. Uncle Bob diz que especificar demais o código leva a ficar travado: quando nenhum teste novo passa sem reescrever tudo, volte, apague testes e escolha outro caminho. Ideia relacionada, também dele: a Transformation Priority Premise (blog, 2013), que ordena as transformações do simples para o genérico.

### 12. Java 21 ou 24: o que muda para nós

Até o 21 ou até o 24

| Recurso | Java 21 (LTS) | Java 24 | Na aula |
|---|---|---|---|
| Records | Final | Final | Sim: dados do treino |
| Sealed interfaces | Final | Final | Sim: fases |
| Switch com pattern matching e record patterns | Final | Final | Sim: regras por fase |
| Variável sem nome _ (JEP 456) | Preview, com flag | Final (desde 22) | Bônus no 24 |
| Stream Gatherers (JEP 485) | Não existe | Final (24) | Bônus no 24 |
| Main sem classe (instance main) | Preview | Preview | Não usar |

Regra da aula: tudo que é obrigatório compila em Java 21. O que for só do 24 aparece marcado como bônus.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Responde à dúvida "Java 21 ou 24?". Tudo o que a aula usa (records, sealed, switch com patterns) já existe no Java 21. O que só existe no 24 é bônus: o _ para variável sem nome e os Stream Gatherers. Conclusão: TDD não depende da versão; a versão só muda o quanto o código fica enxuto.
> COMO CONDUZIR: JUnit 5 e AssertJ rodam igual nas duas versões — o TDD em si não depende da versão. Limitações reais no 21: o "_" só com --enable-preview, e Stream Gatherers não existem. Instance main e compact source files só viram finais no Java 25. Vale mencionar: o Java 24 não é LTS e já saiu de suporte; o LTS atual depois do 21 é o 25, e tudo desta aula roda nele também. Limite prático de JVM: bytecode tem versão (--release 21 gera class file 65; 24 gera 68), então código compilado para 24 não roda numa JVM 21 (UnsupportedClassVersionError). Pergunte: "em que versão rodam os projetos de vocês hoje?"

### 13. Setup em 2 minutos

Maven · Java 24 · JUnit 5 + AssertJ

```
<properties>
  <maven.compiler.release>24</maven.compiler.release>
</properties>

<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter</artifactId>
  <version>5.11.4</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>org.assertj</groupId>
  <artifactId>assertj-core</artifactId>
  <version>3.27.3</version>
</dependency>
```

```java
class PlatesTest {

  @Test
  void smokeTest() {
    assertThat(1 + 1)
        .isEqualTo(2);
  }
}

// mvn test -> green?
// Then the environment is ready.
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: O mínimo para começar: o pom com Java 24, JUnit 5 e AssertJ, e um teste bobo de 1 + 1 só para provar que o ambiente funciona. Se esse teste não fica verde, o problema é de ambiente, não de TDD; resolva antes do dojo.
> COMO CONDUZIR: O projeto usa --release 24: qualquer JDK 24 ou mais novo roda (o 25 LTS também). No IntelliJ, abra a pasta aulas e escolha um JDK 24+ como SDK do projeto. Confira antes da aula se há versões mais novas do JUnit e do AssertJ (qualquer 5.x serve). O teste "1 + 1" é o teste de fumaça: prova que o ambiente roda antes de começarmos. Tenha o projeto já clonado em todas as máquinas — setup não deve comer tempo do dojo.

---

## Do pedido de produto ao teste: ambiguidade, critérios, arquitetura e o vocabulário de testes de Martin Fowler

### 14. Do pedido ao teste

Parte 2 · 15 min

Powerlifting é só o pretexto: o método vale para qualquer regra de produto

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Divisória da parte 2. Deixe claro: o assunto não é powerlifting. É a habilidade de pegar um pedido de produto e transformá-lo em testes.
> COMO CONDUZIR: Deixe claro logo aqui: ninguém precisa entender de powerlifting. O domínio é uma brincadeira escolhida porque tem números, limites e regras que brigam entre si — exatamente o que aparece num ticket de pagamento, frete, desconto ou limite de crédito. A habilidade que queremos treinar é: ler um pedido de produto, achar a ambiguidade, transformar em exemplos e deixar os exemplos virarem testes que guiam o código.

### 15. Um ticket como qualquer outro

O pedido de produto · do jeito que chega

[PROG-42] · exemplo

**Sugerir a carga da próxima semana**

Como atleta, quero que o app sugira o treino da próxima semana com base no último, para progredir sem me machucar.

Critérios, como vieram

- Se o treino foi fácil, aumentar a carga
- Se o atleta estiver cansado, pegar mais leve
- Perto da competição, treinar menos
Pergunta para a turma

**Dá para escrever um teste disso?**

O que é “fácil”? Aumenta quanto? “Cansado” medido como? “Perto” são quantas semanas?

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Um ticket realista, do jeito que chega do produto. Parece claro, mas nenhum critério dá para testar: o que é "fácil"? "Cansado" medido como? "Perto" são quantas semanas? Se não dá para escrever o teste, também não dá para escrever o código sem chutar.
> COMO CONDUZIR: Mostre que o ticket é realista: parece claro, mas nenhum critério é testável como está. Peça à turma para tentar escrever o assertThat do primeiro critério — vão travar no "fácil" e no "quanto". Essa trava é o ponto: se você não consegue escrever o teste, você também não sabe escrever o código; só vai chutar. TDD começa antes do código: começa na conversa com produto. Troque "atleta" por "cliente" e "carga" por "limite de crédito" e o problema é o mesmo.

### 16. Frase, pergunta, exemplo, teste

Uma regra, do pedido ao teste

1 · Pedido

“Se o treino foi fácil, aumentar a carga.”

2 · Perguntas

- Fácil é RPE até 7?
- Aumenta quanto?
- Arredonda como?
3 · Exemplos

```
RPE 7:  100 → 105.0
RPE 8:  100 → 102.5
RPE 10: 100 →  95.0
```

Aprovados por produto

4 · Teste, com o nome da regra

```java
@Test
void easySessionIncreasesLoadBy5Percent() {  // "easy" = RPE <= 7 (agreed with product)
  assertThat(RpeRule.next(100.0, 7.0)).isEqualTo(105.0);
}
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: O método da aula em quatro passos, aplicado a uma única frase. Pegue a frase do produto; liste as perguntas que ela não responde; combine exemplos com números junto com o produto; escreva um teste por exemplo, com o nome da regra de negócio. O comentário no teste guarda a decisão do produto para sempre.
> COMO CONDUZIR: Este é o slide mais importante da parte 2. O caminho é sempre o mesmo, para qualquer domínio: (1) a frase do produto; (2) as perguntas que ela não responde; (3) exemplos concretos com números, validados com quem pediu; (4) um teste por exemplo, com o nome da regra de negócio. Repare no comentário do teste: a definição de "fácil" foi uma decisão de produto e fica registrada no código. Na próxima vez que alguém perguntar "por que RPE 7?", a resposta está no teste, não na memória de alguém. Pergunta para a turma: "quem deveria validar a coluna de exemplos: dev, QA ou produto?" Resposta: os três juntos — é a conversa que os exemplos provocam que tem valor (essa ideia é a base do BDD e do Specification by Example).

### 17. Caçando ambiguidade

Perguntas que transformam frase em regra

| Pergunte ao produto | No nosso exemplo | Vira teste de... |
|---|---|---|
| Exatamente no limite, conta? | 30% de perda tira série? | Borda: os dois lados do limite |
| Com que precisão? | 101,2 kg vira quanto? | Valor quebrado e arredondamento |
| E se o dado vier errado? | Carga negativa, RPE 11 | Entrada inválida e exceção |
| E se duas regras brigarem? | Treino fácil, mas muita fadiga | Aceitação: regras combinadas |
| O que nunca pode acontecer? | Subir carga com RPE 10 | Propriedade: vale para qualquer entrada |

Cada resposta vira um exemplo. Cada exemplo vira um teste. Sem resposta, não há teste — e o código seria um chute.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Um checklist de perguntas para levar ao produto. Cada tipo de pergunta gera um tipo de teste: a pergunta do limite gera teste de borda; a do dado errado gera teste de entrada inválida; a das regras em conflito gera teste de aceitação; e "o que nunca pode acontecer?" gera teste de propriedade.
> COMO CONDUZIR: Este checklist serve para qualquer ticket. Sugestão: leve-o para o refinamento do seu time. A coluna do meio é só o nosso pretexto; troque por frete ("pedido de exatamente R$ 200 tem frete grátis?") ou desconto e funciona igual. Note que a última coluna antecipa o resto da aula: bordas e inválidos aparecem no dojo, regras combinadas no motor e propriedades na parte de robustez. Pergunta para a turma: "qual dessas perguntas vocês mais esquecem de fazer?" Normalmente é a da borda e a do dado inválido.

### 18. Critérios de aceitação

Depois da conversa · aprovado por produto

| Frase do ticket | Regra combinada | Resultado esperado |
|---|---|---|
| Treino fácil | RPE ≤ 7 | carga +5% |
| Treino no alvo | RPE 7,5 a 8,5 | carga +2,5% |
| Treino pesado | RPE 9 | mantém a carga |
| Treino no limite | RPE ≥ 9,5 | carga −5% |
| Atleta cansado | perda de velocidade acima de 30% | −1 série |
| Atleta cansado | prontidão ≤ 2 (de 1 a 5) | carga −5% |
| (regra técnica) | sempre | para baixo, múltiplo de 2,5 kg |

> **Notas:** O QUE ESTE SLIDE QUER DIZER: O resultado da conversa com o produto: a tabela de critérios, agora com números. Cada linha é um teste pronto. Mostre que uma frase vaga ("atleta cansado") virou duas regras diferentes.
> COMO CONDUZIR: Compare com o ticket original: "fácil", "cansado" e "perto" viraram números. Essa tabela é o contrato entre produto e time — e cada linha já é um teste pronto. Repare que "atleta cansado" virou DUAS regras: uma frase de produto pode esconder vários critérios. A última linha ninguém pediu, mas o time precisa (ninguém coloca 101,2 kg numa barra); regra técnica também vira teste. Os números são didáticos (vêm da literatura de treino, veja o apêndice), não prescrição. Pergunte: "se o produto mudar o +5% para +4% amanhã, quantos testes quebram?" Resposta: só o daquela linha — e é exatamente isso que queremos.

### 19. O volume cai perto da prova

Semanas até a competição

100%

**Acúmulo**

9 semanas ou mais

RPE alvo 7 a 8

85%

**Intensificação**

8 a 3 semanas

RPE alvo 8 a 9

70%

**Polimento**

2 semanas

Carga ≥ 85% do 1RM

50%

**Semana da prova**

1 semana

Terra pesado sai; descanso final

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Mais um critério do ticket ("perto da competição, treinar menos"), agora com números: quanto mais perto da prova, menor o volume de treino. As barras mostram o volume de cada fase. Não precisa explicar a ciência do treino; basta dizer que é uma regra que depende do prazo, como um desconto que depende da data. Vira a rodada 5.
> COMO CONDUZIR: Os percentuais são o volume (séries × reps) em relação ao bloco de acúmulo. Os dois últimos ficam dentro da faixa de corte de 30 a 50% da revisão de Travis et al. (2020). Pergunte: "por que cortar volume e não intensidade?" Resposta: o volume gera a fadiga; a intensidade mantém a especificidade para o 1RM. Esta tabela vira a rodada 4 do dojo.

### 20. Um grupo de critérios, uma classe

Os testes desenham a arquitetura

| Grupo de critérios | Classe de teste | Código de produção | Depende de |
|---|---|---|---|
| Arredondamento | PlatesTest | Plates | nada |
| Esforço percebido | RpeRuleTest | RpeRule, Loads | Plates |
| Fadiga e prontidão | FatigueRuleTest | FatigueRule, ReadinessRule | Loads |
| Prazo até a prova | PhaseTest | Phase (sealed) | nada |
| O ticket inteiro | EngineAcceptanceTest | RuleBasedEngine | todas as regras |

Teste difícil de montar é um recado do design: a classe sabe coisa demais.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Mostra como os testes decidem a estrutura do código. Juntamos critérios que mudam juntos; cada grupo ganha uma classe de teste, e a classe de produção nasce para fazer esses testes passarem. A coluna "depende de" mostra o acoplamento. Se um teste é difícil de montar, é sinal de que a classe está fazendo coisa demais.
> COMO CONDUZIR: Aqui mostramos como os testes guiam a arquitetura. Não desenhamos classes antes: agrupamos critérios que mudam juntos, e cada grupo ganha uma classe de teste. A classe de produção nasce para fazer esses testes passarem. Resultado: regras pequenas e puras, com poucas dependências, e um motor que só compõe. A coluna "depende de" é o mapa de acoplamento — ela sai de graça quando cada teste só precisa do mínimo para rodar. O último item é o teste de aceitação: ele lê como o ticket e protege a combinação das regras. Pergunta para a turma: "se o produto pedir uma regra nova de sono, onde ela entra?" Resposta: um SleepRule novo com o próprio teste, e uma linha no motor — nenhuma regra existente muda.

### 21. O contrato que vamos dirigir

Só a assinatura · o comportamento nasce dos testes

```java
enum Lift { SQUAT, BENCH, DEADLIFT }

record SetLog(Lift lift, double loadKg, int sets, int reps,
              double rpe, double velocityLossPct) {}

record WeekLog(List<SetLog> sets, int readiness) {}

record Prescription(Lift lift, double loadKg, int sets, int reps) {}

interface ProgressionEngine {
  List<Prescription> nextWeek(WeekLog last, int weeksToMeet);
}
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: O contrato do sistema: os dados de entrada (o que o atleta fez na semana), a saída (o que ele deve fazer na próxima) e a interface do motor. É só a assinatura, sem nenhuma lógica. Toda a lógica vai nascer de testes durante as rodadas.
> COMO CONDUZIR: Tudo aqui compila em Java 21: records e enum. Não implementamos nada ainda. Pergunte: "o que está faltando nesse contrato?" Boas respostas: o 1RM do atleta, um tipo Kg no lugar de double (obsessão por primitivos — voltamos a isso na refatoração), validação da prontidão entre 1 e 5. Anote as respostas: viram testes ou refatorações mais tarde.

### 22. Anatomia de um teste

Martin Fowler · Mocks Aren't Stubs · vocabulário

SUT

System Under Test: o objeto que o teste está testando.

Colaborador

Outro objeto de que o SUT depende para trabalhar.

Fixture

Tudo o que o teste monta antes de rodar: SUT, colaboradores e dados.

Quatro fases

Setup, exercise, verify, teardown.

```java
@Test
void plansNextWeekFromLastWeekAndMeetDate() {
  // given · setup: the fixture
  log.record("ana", week(8.0, 35.0));
  MeetCalendar fiveWeeksOut = athlete -> 5;
  var service = new WeeklyPlanService(// SUT
      log, fiveWeeksOut, dummyNotifier, engine);

  // when · exercise
  var plan = service.planFor("ana");

  // then · verify
  assertThat(plan).containsExactly(
      new Prescription(SQUAT, 152.5, 4, 3));
  // teardown: nothing to clean
}
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: dá nome às partes de qualquer teste, usando o vocabulário do artigo "Mocks Aren't Stubs", de Martin Fowler. SUT (System Under Test) é o objeto que está sendo testado; aqui, o WeeklyPlanService. Colaboradores são os objetos de que ele depende: o registro de treinos, o calendário de provas, o aviso ao treinador e o motor. Fixture é tudo o que o teste monta antes de rodar: o SUT, os colaboradores (reais ou dublês) e os dados. Todo teste tem quatro fases: setup (monta a fixture), exercise (chama o SUT), verify (confere o resultado) e teardown (limpa; em teste de unidade com JUnit, quase nunca há o que limpar).
> Ponto importante para a turma: fixture NÃO é um tipo de dublê. A fixture é o cenário inteiro; os dublês (próximo slide) são algumas das peças que podem estar nesse cenário.
> COMO CONDUZIR: aponte cada comentário do código e pergunte "quem é o SUT? quem são os colaboradores?". Os comentários usam given/when/then, a convenção do projeto; ao lado aparece o nome do Fowler (setup/exercise/verify). O próximo slide mostra que Given/When/Then, Arrange/Act/Assert e as fases do Fowler são a mesma ideia com nomes diferentes. Diga que a rodada 6 do dojo vai construir exatamente este teste.

### 23. Todo teste é entrada e saída

Given, When, Then · a mesma ideia com três nomes

| Given · dado | When · quando | Then · então |
|---|---|---|
| Arrange · Act · Assert | Arrange | Act | Assert |
| Fowler (4 fases) | Setup (a fixture) | Exercise | Verify |
| Saída é um valor | carga de 101,2 kg | arredondar | 100 kg |
| Saída é um erro | carga de −1 kg | arredondar | exceção: carga inválida |
| Saída é um efeito | RPE 7 e 35% de perda | planejar a semana | o treinador é avisado |

Regra pura: cada linha de exemplo vira uma linha de @CsvSource. Quando a saída é um efeito, o dublê (spy ou mock) é quem mostra o resultado.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: sim, dá para pensar todo teste como uma função: você prepara uma entrada, executa uma ação e confere uma saída. As três primeiras linhas mostram que Given/When/Then (vindo do BDD, Dan North), Arrange/Act/Assert (usado no livro Pragmatic Unit Testing in Java with JUnit) e as quatro fases do Fowler (setup/exercise/verify) são a mesma estrutura com nomes diferentes. No código do projeto usamos comentários // given // when // then.
> As três linhas de baixo mostram que a "saída" nem sempre é um valor de retorno: pode ser um valor (101,2 vira 100), um erro (carga negativa lança exceção) ou um efeito num colaborador (o treinador recebe um alerta). Para valor e erro, o assertThat basta. Para efeito, é o dublê (spy ou mock) que permite enxergar o que aconteceu.
> Consequência prática: nas regras puras deste projeto (Plates, RpeRule, FatigueRule, Phase) a tabela de exemplos do produto vira diretamente um teste parametrizado, uma linha de @CsvSource por exemplo.
> COMO CONDUZIR: pergunte "qual é o given, o when e o then do teste de fadiga?" (given: SetLog com 35% de perda; when: nextSets; then: 4 séries). Depois peça um exemplo de teste em que a saída é um efeito (resposta: o alerta ao treinador da rodada 6).

### 24. Cinco dublês de teste

Martin Fowler · TestDouble · termo de Gerard Meszaros

| Dublê | O que é | No nosso projeto (rodada 6) |
|---|---|---|
| Dummy | Passado como parâmetro, mas nunca usado | CoachNotifier que falha se alguém chamar |
| Stub | Dá respostas prontas às chamadas | MeetCalendar: athlete -> 5 |
| Fake | Funciona de verdade, mas com um atalho | InMemoryTrainingLog: um HashMap no lugar do banco |
| Spy | Um stub que grava como foi chamado | SpyCoachNotifier guarda os alertas numa lista |
| Mock | Programado com as chamadas que deve receber | mock(CoachNotifier.class) e verify(...) |

Test double é o nome geral, como o dublê do cinema. Dublê substitui colaborador; nunca o SUT.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: "test double" (dublê de teste) é o nome geral para qualquer objeto que substitui um colaborador real num teste. O termo é de Gerard Meszaros, inspirado no dublê de cinema, e Martin Fowler resume os cinco tipos no artigo "TestDouble":
> Dummy: é passado porque a assinatura exige, mas ninguém usa. O nosso lança erro se for chamado, o que prova que aquele caminho não foi usado.
> Stub: devolve respostas prontas. O calendário sempre responde "5 semanas".
> Fake: funciona de verdade, mas com um atalho que não serve para produção. O registro de treinos guarda tudo num HashMap em vez de um banco.
> Spy: é um stub que também anota como foi chamado. Depois o teste lê o que ele anotou.
> Mock: vem programado com as chamadas que espera receber, e o teste confere se elas aconteceram (com Mockito, via verify).
> Todos os cinco aparecem na rodada 6, no mesmo teste do WeeklyPlanService.
> COMO CONDUZIR: volte à conversa do fake it: dublê substitui um COLABORADOR do SUT, nunca o próprio SUT. Mockar a classe que você está testando é testar o mock. Pergunta para a turma: "o fake it da rodada 2 é um fake no sentido do Fowler?" Resposta: não. Fake it é uma implementação provisória no código de produção; fake do Fowler é um dublê completo que vive no código de teste. O nome parecido confunde, vale deixar claro.

### 25. Verificar estado ou comportamento

Martin Fowler · Mocks Aren't Stubs

Estado · com spy

```java
var spy = new SpyCoachNotifier();
var service = new WeeklyPlanService(
    log, athlete -> 5, spy, engine);

service.planFor("ana");

assertThat(spy.alerts).containsExactly(
    "ana: conflicting signals on SQUAT");
```

Pergunta: o que ficou registrado no fim?

Comportamento · com mock

```java
CoachNotifier mock = mock(CoachNotifier.class);
var service = new WeeklyPlanService(
    log, athlete -> 5, mock, engine);

service.planFor("ana");

verify(mock).alert("ana",
    "conflicting signals on SQUAT");
```

Pergunta: a chamada certa aconteceu?

Neste dojo somos clássicos, como o Fowler: objetos reais quando dá (o motor é real) e dublês só nas bordas.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Fowler separa duas formas de conferir um teste. Verificação de estado: depois de rodar, você olha o resultado ou o que ficou registrado (aqui, a lista de alertas que o spy guardou). Verificação de comportamento: você confere se o SUT fez as chamadas esperadas aos colaboradores (aqui, se chamou alert com os argumentos certos). Os dois testes provam a mesma regra de produto: quando os sinais brigam, o treinador é avisado.
> Fowler também descreve duas escolas: a clássica usa objetos reais sempre que possível e dublês só quando o real é difícil de usar (banco, rede, e-mail); a mockista usa mocks para qualquer colaborador com comportamento interessante, o que ajuda a desenhar de fora para dentro, mas prende os testes à forma das chamadas. Ele se declara clássico, e este dojo segue a mesma linha: as regras e o motor são reais; só as bordas (registro de treinos, calendário, aviso ao treinador) viram dublês.
> COMO CONDUZIR: pergunte "se amanhã o serviço passar a mandar os alertas em lote, qual teste quebra?" Resposta: o do mock quebra, porque ele confere a chamada exata; o do spy pode continuar verde se a lista final for a mesma. Esse é o custo do acoplamento que o Fowler descreve. No Mockito, a expectativa é conferida depois, com verify; conceitualmente continua sendo verificação de comportamento.

---

## Dojo: planejar os testes, cinco rodadas de código (com os cinco dublês de teste) e o ciclo primário

### 26. Dojo: mãos no teclado

Parte 3 · 60 min

Uma rodada para planejar os testes, cinco de código e a pausa do ciclo primário

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Divisória do dojo. A partir daqui você fala menos e a turma digita.
> COMO CONDUZIR: Chame o primeiro piloto e copiloto. Inicie o timer. A partir daqui você fala menos e pergunta mais. No meio de cada rodada, faça a pergunta do ciclo milli: "o código está ficando mais genérico ou só decorando os testes?"

### 27. Seis rodadas, uma entrega cada

Plano do dojo · 60 min · código em inglês

| Rodada | Tempo | Entrega esperada | Checkpoint |
|---|---|---|---|
| 1 · Planejamento | 8 min | TEST-LIST.md com todos os testes | A turma concorda com a ordem |
| 2 · Anilhas | 9 min | Plates.roundDown e 3 testes | Fake it, triangulação, genérico |
| 3 · RPE | 11 min | RpeRule e Loads, teste parametrizado | O bug do double apareceu e caiu |
| 4 · Fadiga | 8 + 2 min | FatigueRule e ReadinessRule | Teste da borda de 30% |
| 5 · Fases | 8 min | Phase selada e switch | Terra fora da semana da prova |
| 6 · Colaboradores | 10 min | WeeklyPlanService e os 5 dublês | Nenhum dublê do SUT |
| Ciclo primário | 4 min | Revisão das fronteiras | Regras sem JUnit, UI ou banco |

Nomes de classes, métodos e testes em inglês. Conversa, lista e slides em português.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: O seu relógio para o dojo: cinco rodadas, o que cada uma entrega e como saber que ela terminou (o checkpoint). Cada rodada tem um slide de tarefa e um slide de estado esperado, que é o gabarito.
> COMO CONDUZIR: Use este slide como seu relógio. Cada rodada tem um slide de tarefa (o que a turma vê) e um slide de "estado esperado" (o código ao fim da rodada). Só mostre o estado esperado depois que a turma chegar lá ou se a rodada travar — ele é gabarito e também ponto de resgate: se o tempo acabar, cole o código do estado esperado e siga para a próxima rodada, assim ninguém fica para trás. Por que o código em inglês: é a convenção da maioria dos times e das bibliotecas; se quiserem texto em português no relatório, usem @DisplayName("...") nos testes.

### 28. Decidir todos os testes

Rodada 1 · 8 min · planejamento

Como fazer

- Cada linha do modelo vira um exemplo: entrada → saída
- Dê um nome em inglês, como vai ficar no código
- Ordene do mais simples ao mais difícil
- Nada de código ainda: a lista vive no TEST-LIST.md
Pergunta para a turma

Por que não escrever todos esses testes no código agora?

```
# TEST-LIST.md

## Plates
[ ] roundsDown: 101.2 -> 100.0
[ ] ...

## RpeRule (target RPE 8)
[ ] rpe8: +2.5% (100 -> 102.5)
[ ] ...

## FatigueRule
[ ] ...

## Phase
[ ] ...
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Rodada 1, sem código nenhum: a turma transforma os critérios em uma lista de testes (a "lista de testes" do Kent Beck). É o plano de trabalho. Depois os testes entram no código um de cada vez, respeitando as três leis.
> COMO CONDUZIR: PLANO (8 min). 0–2 min: todos leem os slides do modelo e das fases em silêncio e anotam exemplos. 2–6 min: o piloto digita no TEST-LIST.md o que a turma dita, uma seção por regra. 6–8 min: a turma ordena; você só confirma que o primeiro é o de anilhas. ESTADO ESPERADO: próximo slide (cerca de 14 itens).
> 
> PERGUNTAS E RESPOSTAS.
> "Por que não escrever os testes já no código?" → A lei 2: um teste vermelho por vez. Com dez testes vermelhos você não sabe qual passo quebrou nada. A lista é um plano (Kent Beck), não código.
> "E se esquecermos um caso?" → Ótimo: a lista é viva. Caso novo que aparecer no meio da rodada vai para a lista, não para o código na hora.
> "Não é design antecipado demais?" → Não: planejamos exemplos (o quê), não a implementação (como). O como emerge dos testes.
> "Por que nomes em inglês?" → Convenção do código; o texto em português pode ir no @DisplayName.
> Se a turma travar: aponte a tabela de regras — cada linha é literalmente um teste.

### 29. A lista que queremos ter

Rodada 1 · estado esperado

```
## Plates
[ ] roundsDown: 101.2 -> 100.0
[ ] keepsExactMultiple: 102.5
[ ] rejectsNegativeLoad

## RpeRule (target RPE 8)
[ ] rpe7:  +5%   (100 -> 105.0)
[ ] rpe8:  +2.5% (100 -> 102.5)
[ ] rpe9:  keeps (100 -> 100.0)
[ ] rpe10: -5%   (100 ->  95.0)
```

```
## FatigueRule / ReadinessRule
[ ] velocityLoss 35%: sets 5 -> 4
[ ] velocityLoss 30%: sets stay 5
[ ] readiness 2: load -5%
[ ] readiness 4: load unchanged

## Phase
[ ] 9 weeks: Accumulation (1.00)
[ ] 8..3 weeks: Intensification (0.85)
[ ] 2 weeks: Taper (0.70)
[ ] 1 week: MeetWeek (0.50), no deadlift
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Gabarito da rodada 1. A lista da turma não precisa bater palavra por palavra; importa ter números concretos e os casos de borda.
> COMO CONDUZIR: GABARITO da rodada 1. A lista da turma não precisa bater palavra por palavra; o que importa: (1) exemplos concretos com números, (2) casos de borda presentes — 102.5 exato, 30% exatos, 8 e 3 semanas, (3) ordem do simples para o complexo. Se faltar borda, pergunte: "o que acontece com exatamente 30%?" em vez de ditar. Guarde a lista visível: a cada teste verde, o piloto marca [x]. Isso dá à turma a sensação de progresso — é a barra de progresso do dojo.

### 30. Arredondar para as anilhas

Rodada 2 · 9 min

Objetivo

Uma função pura que arredonda para baixo em múltiplos de 2,5 kg.

Pergunta para a turma

Qual o próximo teste que quebra o fake it?

```java
@Test
void roundsDownToNearestMultipleOf2_5() {
  assertThat(Plates.roundDown(101.2))
      .isEqualTo(100.0);
}

// simplest possible green
static double roundDown(double kg) {
  return 100.0;  // fake it!
}
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Rodada 2, o primeiro código de verdade. A turma escreve o primeiro teste, vê falhar, faz passar com a constante (fake it) e depois adiciona um segundo exemplo para forçar a regra geral (triangular). É o slide "Três formas de ficar verde" na prática.
> COMO CONDUZIR: PLANO (10 min, 2 pilotos).
> Passo 1 — vermelho de compilação: o piloto escreve só este teste. Plates não existe: isso já é falha (lei 2). A IDE cria a classe e o método retornando 0.0.
> Passo 2 — vermelho de asserção: expected 100.0, got 0.0. Mostre a mensagem do AssertJ.
> Passo 3 — verde com fake it: return 100.0.
> Passo 4 — triangular: a turma propõe 104.9 → 102.5. Vermelho. Agora sim: Math.floor(kg / 2.5) * 2.5.
> Passo 5 — keepsExactMultiple (102.5) e rejectsNegativeLoad. Refatorar: constante INCREMENT e construtor privado.
> ESTADO ESPERADO: próximo slide.
> 
> PERGUNTAS E RESPOSTAS.
> "Por que não escrever o floor direto?" → Pode (implementação óbvia), mas hoje treinamos a marcha mais baixa. Na vida real, use a marcha que a sua confiança permite.
> "Por que static?" → É uma função pura, sem estado. Se um dia precisar de configuração (anilhas de 1,25 kg), vira instância — e os testes dizem se algo quebrou.
> "keepsExactMultiple já nasceu verde. E aí?" → Não guiou código novo, mas documenta a borda. Mantemos; se fosse repetição, apagaríamos.
> "Por que não BigDecimal já?" → Nenhum teste pediu ainda. Vai pedir na próxima rodada.

### 31. Plates, depois do verde

Rodada 2 · estado esperado · 3 testes verdes

```java
class PlatesTest {
  @Test
  void roundsDownToNearestMultipleOf2_5() {
    assertThat(Plates.roundDown(101.2))
        .isEqualTo(100.0);
  }
  @Test
  void keepsExactMultiple() {
    assertThat(Plates.roundDown(102.5))
        .isEqualTo(102.5);
  }
  @Test
  void rejectsNegativeLoad() {
    assertThatThrownBy(() -> Plates.roundDown(-1))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
```

```java
final class Plates {
  static final double INCREMENT = 2.5;

  private Plates() {}

  static double roundDown(double kg) {
    if (kg < 0) {
      throw new IllegalArgumentException(
          "load must be >= 0");
    }
    return Math.floor(kg / INCREMENT)
        * INCREMENT;
  }
}
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Gabarito da rodada 2: três testes e a classe Plates. Mostre que o número mágico 2.5 virou a constante INCREMENT na refatoração sem nenhum teste mudar.
> COMO CONDUZIR: GABARITO da rodada 2. Checkpoint: três testes verdes e a lista com Plates marcada [x]. Se a rodada estourou o tempo, cole este código e siga. Aproveite para mostrar a refatoração com a barra verde: o número mágico 2.5 virou INCREMENT sem nenhum teste mudar. Pergunta rápida para a turma: "o teste de 104.9 que usamos para triangular sumiu. Deveria ficar?" Resposta: pode ficar; se for redundante com o de 101.2, apagar também é legítimo — teste é código e também se refatora.

### 32. O RPE ajusta a carga

Rodada 3 · 11 min

Objetivo

Com alvo em RPE 8, a carga sobe, mantém ou desce. Uma tabela de exemplos vira um teste parametrizado.

Pergunta para a turma

Comece por uma linha só. Fake it ou implementação óbvia?

```java
@ParameterizedTest
@CsvSource({
  "7.0,  100.0, 105.0",
  "8.0,  100.0, 102.5",
  "9.0,  100.0, 100.0",
  "10.0, 100.0,  95.0"
})
void adjustsLoadByRpe(double rpe, double load,
                      double expected) {
  assertThat(RpeRule.next(load, rpe))
      .isEqualTo(expected);
}
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Rodada 3: a regra do RPE (o esforço que o atleta sentiu) com um teste parametrizado, que é uma tabela de exemplos. Aqui acontece a surpresa da aula: a linha do RPE 8 falha por erro de arredondamento do double, mesmo com a lógica certa.
> COMO CONDUZIR: PLANO (12 min, 2 a 3 pilotos).
> Passo 1: comece com @CsvSource de UMA linha ("9.0, 100.0, 100.0"). Fake it: return load. Verde.
> Passo 2: adicione a linha "10.0" → vermelho → if (rpe >= 9.5) return load * 0.95. Verde.
> Passo 3: adicione "7.0" e depois "8.0". A linha do RPE 8 fica VERMELHA mesmo com a lógica certa: 100 * 1.025 em double é 102.49999999999999, e o roundDown da rodada anterior leva a 100.0.
> Passo 4: deixe a turma investigar (rode em debug ou imprima o valor). Correção esperada: extrair Loads.scale com BigDecimal. O teste não muda — só a produção.
> ESTADO ESPERADO: próximo slide.
> 
> PERGUNTAS E RESPOSTAS.
> "Por que não usar isCloseTo com tolerância?" → Esconderia o bug: o resultado final (100 em vez de 102,5) está errado para o atleta, não é ruído.
> "Por que não float/double de uma vez fora?" → Poderíamos trocar tudo por BigDecimal ou record Kg; fica como refatoração (slide de refatoração).
> "Teste parametrizado não viola 'um teste por vez'?" → Não, se você adiciona uma linha por vez. Cada linha é um exemplo; a lei 2 vale por linha.
> "Dependência?" → junit-jupiter já traz o junit-jupiter-params.

### 33. RpeRule e o bug do double

Rodada 3 · estado esperado · 4 linhas verdes

```java
final class RpeRule {
  static double next(double loadKg, double rpe) {
    return Loads.scale(loadKg, factorFor(rpe));
  }

  private static double factorFor(double rpe) {
    if (rpe <= 7.0) return 1.05;
    if (rpe <= 8.5) return 1.025;
    if (rpe <  9.5) return 1.00;
    return 0.95;
  }
}
```

```java
// 100 * 1.025 == 102.49999999999999
// roundDown -> 100.0 (red!)

final class Loads {
  static double scale(double kg, double factor) {
    var exact = BigDecimal.valueOf(kg)
        .multiply(BigDecimal.valueOf(factor));
    return Plates.roundDown(exact.doubleValue());
  }
}
```

Checkpoint: a linha do RPE 8 ficou vermelha, a turma entendeu por quê e corrigiu sem mexer no teste.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Gabarito da rodada 3 e explicação do bug: 100 × 1,025 em double dá 102,4999…; ao arredondar para baixo vira 100 em vez de 102,5. BigDecimal resolve. Repare que o teste não mudou; só o código de produção.
> COMO CONDUZIR: GABARITO da rodada 3. Valores conferidos: 100 × 1,05 = 105; × 1,025 = 102,5; × 1,00 = 100; × 0,95 = 95 — todos exatos com BigDecimal.valueOf. Mensagem principal: o teste pegou um bug de ponto flutuante no minuto em que ele nasceu. Sem o teste, isso viraria "o app às vezes sugere 100 kg em vez de 102,5" em produção. Loads.scale é reaproveitado na rodada de fadiga — mostre isso lá. Pergunta para a turma: "por que BigDecimal.valueOf e não new BigDecimal(1.025)?" Resposta: new BigDecimal(double) carrega o erro binário do double; valueOf usa a representação decimal em texto.

### 34. A fadiga corta o volume

Rodada 4 · 8 min

Objetivo

Perda de velocidade acima de 30% tira uma série. Prontidão ≤ 2 corta 5% da carga.

Pergunta para a turma

Essa regra mora na mesma classe do RPE ou em outra?

```java
@Test
void velocityLossAbove30RemovesOneSet() {
  // given
  var last = new SetLog(SQUAT, 150, 5, 3, 8.0, 35.0);
  // when
  var sets = FatigueRule.nextSets(last);
  // then
  assertThat(sets).isEqualTo(4);
}

@Test
void velocityLossOfExactly30KeepsSets() {
  // the edge case: your turn
}
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Rodada 4: a regra de fadiga. O ponto didático é o teste de borda (exatamente 30% tira série ou não?) e uma decisão de design: essa regra fica numa classe separada da regra de RPE.
> COMO CONDUZIR: PLANO (8 min, 2 pilotos).
> Passo 1: velocityLossAbove30RemovesOneSet. Fake it: return 4. Verde.
> Passo 2: a turma escreve a borda (30.0 → continua 5). Vermelho com o fake → implementação: velocityLossPct > 30 ? sets − 1 : sets.
> Passo 3 (bônus de borda): e se sets for 1? Math.max(1, sets − 1). Pergunte antes de mostrar.
> Passo 4: ReadinessRule com dois testes (nota 2 corta 5%, nota 4 mantém). Destaque o reúso de Loads.scale da rodada anterior.
> Os testes seguem given / when / then (o mesmo que Arrange / Act / Assert): os comentários marcam as três partes.
> ESTADO ESPERADO: próximo slide.
> 
> PERGUNTAS E RESPOSTAS.
> "Mesma classe do RPE ou outra?" → Outra. Se a fadiga entrar no RpeRule, os testes de RPE passam a precisar de dados de velocidade: acoplamento. Uma regra, uma razão para mudar.
> "> ou >=?" → O teste da borda decide e documenta. Sem ele, qualquer um dos dois "funciona" e ninguém sabe qual era a intenção.
> "E o SetLog enorme no teste?" → Cheiro real. Solução: um builder de teste (aSetLog().withVelocityLoss(35).build()). Anote para a refatoração.

### 35. Fadiga e prontidão

Rodada 4 · estado esperado · 4 testes verdes

```java
@Test
void velocityLossOfExactly30KeepsSets() {
  var last = new SetLog(SQUAT, 150, 5, 3, 8.0, 30.0);
  assertThat(FatigueRule.nextSets(last)).isEqualTo(5);
}

@Test
void lowReadinessCutsLoadBy5Percent() {
  assertThat(ReadinessRule.adjust(150.0, 2))
      .isEqualTo(142.5);
}

@Test
void goodReadinessKeepsLoad() {
  assertThat(ReadinessRule.adjust(150.0, 4))
      .isEqualTo(150.0);
}
```

```java
final class FatigueRule {
  static final double LIMIT_PCT = 30.0;

  static int nextSets(SetLog last) {
    return last.velocityLossPct() > LIMIT_PCT
        ? Math.max(1, last.sets() - 1)
        : last.sets();
  }
}

final class ReadinessRule {
  static double adjust(double kg, int score) {
    return score <= 2
        ? Loads.scale(kg, 0.95)
        : kg;
  }
}
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Gabarito da rodada 4: o teste da borda de 30%, a regra de prontidão e as duas classes de produção.
> COMO CONDUZIR: GABARITO da rodada 4 (mais o teste velocityLossAbove30RemovesOneSet do slide anterior). Valores conferidos: 150 × 0,95 = 142,5, já múltiplo de 2,5. Checkpoint: a borda de 30% existe como teste e a turma sabe dizer por que é > e não >=. Logo depois vem o slide de discussão (RPE × velocidade): ele usa estas duas regras e é o gancho para o motor final.

### 36. Duas regras aprovadas discordam. Quem decide?

Discussão em duplas · 2 min

Treino “fácil” pelo RPE, mas a velocidade caiu 40%. Leve a pergunta ao produto e escreva o teste que registra a resposta.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Pausa para discussão: duas regras aprovadas dão respostas opostas para o mesmo treino. A mensagem é que isso não é decisão técnica. O dev percebe o conflito (os testes ajudam a enxergar) e leva ao produto; a resposta vira um teste.
> COMO CONDUZIR: Mensagem central: conflito entre regras não é decisão técnica, é decisão de produto. O papel do dev é perceber o conflito (os testes ajudam a enxergar) e levar a pergunta com um exemplo concreto. Para a dinâmica, você faz o papel de produto e escolhe uma das respostas: (a) as regras se somam: sobe a carga pelo RPE e tira uma série pela fadiga; (b) a velocidade vence por ser medida; (c) sinais conflitantes mantêm tudo e geram um alerta. Qualquer uma vale, desde que vire um teste de aceitação com nome claro, por exemplo easySessionWithHighFatigueKeepsLoadAndDropsOneSet. No código de referência, a decisão adotada é "as regras se somam e o treinador é avisado" (SignalConflict + CoachNotifier, rodada 6). Na vida real: o mesmo acontece com "cupom de desconto" contra "preço mínimo", ou "frete grátis" contra "região remota".

### 37. A competição está chegando

Rodada 5 · 8 min · sealed + switch

```java
@Test
void taperVolumeIs70Percent() {
  assertThat(volumeFactor(Phase.of(2)))
      .isEqualTo(0.70);
}
```

Pergunta para a turma

O terra sai do plano na semana da prova. Como testar essa exceção?

```java
sealed interface Phase {
  record Accumulation()    implements Phase {}
  record Intensification() implements Phase {}
  record Taper()           implements Phase {}
  record MeetWeek()        implements Phase {}
  static Phase of(int weeks) { /* TDD */ }
}
static double volumeFactor(Phase p) {
  return switch (p) {
    case Accumulation _    -> 1.00;
    case Intensification _ -> 0.85;
    case Taper _           -> 0.70;
    case MeetWeek _        -> 0.50;
  };  // _ = unnamed pattern (Java 22+)
}
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Rodada 5: as fases por prazo até a competição, usando sealed interface e switch com padrões sem nome (_). O compilador garante que toda fase foi tratada: se alguém criar uma fase nova, o código para de compilar até ela ser tratada.
> COMO CONDUZIR: PLANO (9 min, 2 pilotos). Aqui você entrega o esqueleto da direita pronto (colar no projeto), porque o foco é o comportamento, não digitar records.
> Passo 1: taperVolumeIs70Percent. Vermelho porque Phase.of ainda não existe → fake it: return new Taper(). Verde (o switch já está pronto).
> Passo 2: teste parametrizado de Phase.of com 9, 8, 3, 2, 1 semanas, uma linha por vez. As bordas 8 e 3 são onde erram.
> Passo 3: a exceção do terra: meetWeekDropsHeavyDeadlift → Phase.allowsHeavy(phase, lift).
> Experimento (1 min, vale muito): adicione record Deload() implements Phase {} e veja o switch parar de compilar. O compilador vira teste.
> ESTADO ESPERADO: próximo slide.
> 
> PERGUNTAS E RESPOSTAS.
> "Por que sealed e não enum?" → Enum resolveria hoje. Sealed + record deixa cada fase carregar dados próprios no futuro (ex.: Taper(int daysOff)), e o switch continua exaustivo.
> "Que _ é esse no case?" → Padrão sem nome (JEP 456, final desde o 22): só importa o tipo. No Java 21 seria preview; lá, escreva case Taper t ->.
> "Por que não um default no switch?" → O default mataria a checagem de exaustividade: uma fase nova passaria sem aviso.

### 38. Fases e a exceção do terra

Rodada 5 · estado esperado · Java 24

```java
@ParameterizedTest
@CsvSource({"9,Accumulation", "8,Intensification",
            "3,Intensification", "2,Taper",
            "1,MeetWeek"})
void phaseByWeeksToMeet(int weeks, String name) {
  assertThat(Phase.of(weeks).getClass()
      .getSimpleName()).isEqualTo(name);
}

@Test
void meetWeekDropsHeavyDeadlift() {
  var meetWeek = new MeetWeek();
  assertThat(Phase.allowsHeavy(meetWeek, DEADLIFT))
      .isFalse();
}
```

```java
// inside sealed interface Phase
static Phase of(int weeks) {
  if (weeks <= 1) return new MeetWeek();
  if (weeks <= 2) return new Taper();
  if (weeks <= 8) return new Intensification();
  return new Accumulation();
}

static boolean allowsHeavy(Phase p, Lift lift) {
  return !(p instanceof MeetWeek
      && lift == Lift.DEADLIFT);
}
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Gabarito da rodada 5: a regra que escolhe a fase pelas semanas e a exceção do levantamento terra na semana da prova.
> COMO CONDUZIR: GABARITO da rodada 5. Checkpoint: a tabela de fases do slide de domínio está inteira coberta por testes, incluindo as bordas 8 e 3 semanas, e o terra sai na semana da prova. Se sobrar um minuto, faça o experimento do Deload e veja o switch parar de compilar. Pergunta rápida: "comparar pelo getSimpleName é frágil?" Sim, um pouco: renomear a classe quebra o teste. Alternativa: isInstanceOf(Taper.class), com um teste por linha. Deixe a turma escolher — as duas respostas são defensáveis.

### 39. O motor, montado

Entregue pronto antes da rodada 6 · as regras compostas

```java
final class RuleBasedEngine implements ProgressionEngine {
  public List<Prescription> nextWeek(WeekLog last, int weeksToMeet) {
    var phase = Phase.of(weeksToMeet);
    return last.sets().stream()
        .filter(s -> Phase.allowsHeavy(phase, s.lift()))
        .map(s -> prescribe(s, last.readiness()))
        .toList();
  }

  private Prescription prescribe(SetLog s, int readiness) {
    var load = RpeRule.next(s.loadKg(), s.rpe());
    load = ReadinessRule.adjust(load, readiness);
    var sets = FatigueRule.nextSets(s);
    return new Prescription(s.lift(), load, sets, s.reps());
  }
}
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: O estado final: uma classe que só junta as regras já testadas. Como cada regra tem os próprios testes, o motor precisa só de um teste de aceitação, que lê como o ticket.
> COMO CONDUZIR: Cole este código pronto (tag motor) antes da rodada 6: ele só compõe as regras que a turma já testou, e a rodada 6 vai usá-lo como colaborador REAL do serviço. Ele só compõe regras já testadas, então o teste de aceitação é um só. Exemplo pronto para ditar: dado SetLog(SQUAT, 150, 5, 3, 8.0, 35.0), prontidão 4 e 5 semanas até a prova, espera-se Prescription(SQUAT, 152.5, 4, 3). Conta: RPE 8 → 150 × 1,025 = 153,75 → arredonda para 152,5; prontidão 4 mantém; perda de 35% tira uma série (5 → 4).
> Pergunta provável: "e o volumeFactor da fase, não entra?" Resposta honesta: ainda não. O fator é relativo ao volume base do bloco, não à semana anterior — aplicar na semana passada cortaria volume em cascata. Decidir a base do bloco é um dos desafios para casa. Bom exemplo de regra de negócio que só aparece quando as peças se juntam.
> Pergunta provável: "stream().toList() funciona no 21?" Sim, existe desde o Java 16.

### 40. O serviço e as bordas

Rodada 6 · 10 min · dublês de teste

Objetivo

O serviço busca o último treino, pergunta as semanas até a prova, avisa o treinador se os sinais brigam e chama o motor.

Pergunta para a turma

Qual dublê usar para cada colaborador? E o motor?

```java
// ports: the edges of the system
interface TrainingLog {
  WeekLog lastWeek(String athleteId);
}
interface MeetCalendar {
  int weeksToMeet(String athleteId);
}
interface CoachNotifier {
  void alert(String athleteId, String message);
}

// SUT
new WeeklyPlanService(log, calendar, notifier, engine)
    .planFor("ana");
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: a última rodada de código sai das regras puras e chega às bordas do sistema. O WeeklyPlanService é o SUT: ele busca o último treino (TrainingLog), pergunta quantas semanas faltam para a prova (MeetCalendar), avisa o treinador quando RPE diz "fácil" mas a velocidade diz "cansado" (CoachNotifier) e entrega o resto ao motor. As três interfaces são as bordas: no mundo real seriam banco, calendário e push. No teste, cada uma vira um dublê diferente, e usamos os cinco tipos do Fowler.
> Esta rodada também fecha a discussão do slide de conflito: a decisão de produto adotada no código de referência é "as regras se somam e o treinador é avisado" (regra SignalConflict).
> PLANO (10 min, 2 pilotos). Entregue as três interfaces prontas; o foco é o teste.
> Passo 1: plansNextWeekFromLastWeekAndMeetDate com FAKE (InMemoryTrainingLog), STUB (athlete -> 5), DUMMY (notifier que lança erro) e o motor REAL. Primeiro vermelho: o serviço não existe.
> Passo 2: meetWeekFromCalendarDropsHeavyDeadlift: só troca o stub para 1. Mostre como o stub controla o cenário.
> Passo 3: conflictingSignalsAlertTheCoach com SPY (verificação de estado).
> Passo 4: o mesmo teste com MOCK do Mockito (verificação de comportamento). Compare os dois.
> ESTADO ESPERADO: próximo slide.
> PERGUNTAS E RESPOSTAS.
> "Por que o motor não é dublê?" Ele é puro, rápido e já tem testes. Fowler (clássico): use o objeto real quando é fácil. Mockar o motor faria o teste passar mesmo com as regras erradas.
> "Por que o dummy lança erro?" Para provar que, sem conflito, o treinador não é incomodado. Um dummy silencioso esconderia uma chamada indevida.
> "Spy ou mock, qual usar?" Os dois provam a regra. O spy é mais tolerante a refatoração; o mock descreve a interação exata. Aqui preferimos o spy.
> Dependência: mockito-core no pom. No Java 21+, o Mockito pode mostrar um aviso sobre carregar um agente dinâmico; é só um aviso.

### 41. Fixture, fake, stub, dummy

Rodada 6 · estado esperado · os cinco dublês num teste

```java
// FIXTURE
private InMemoryTrainingLog log;     // FAKE
private final ProgressionEngine engine
    = new RuleBasedEngine();     // REAL
private final CoachNotifier dummyNotifier
    = (athlete, msg) -> {       // DUMMY
      throw new AssertionError("never called");
    };
@BeforeEach
void setUp() { log = new InMemoryTrainingLog(); }
@Test
void meetWeekFromCalendarDropsHeavyDeadlift() {
  log.record("ana", squatAndDeadliftWeek());
  MeetCalendar meetWeek = athlete -> 1;  // STUB
  // ... planFor("ana") has only SQUAT
}
```

```java
// production
public List<Prescription> planFor(
    String athleteId) {
  var last = log.lastWeek(athleteId);
  last.sets().stream()
      .filter(SignalConflict::detect)
      .forEach(s -> notifier.alert(
          athleteId,
          "conflicting signals on "
          + s.lift()));
  return engine.nextWeek(last,
      calendar.weeksToMeet(athleteId));
}

// SignalConflict.detect:
// rpe <= 7 && velocityLoss > 30
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: o gabarito da rodada 6. À esquerda, a fixture da classe de teste: o fake (registro de treinos em memória), o motor real e o dummy (aviso ao treinador que falha se for chamado), recriados antes de cada teste pelo @BeforeEach; dentro do teste, o stub do calendário. À direita, o código de produção que esses testes guiaram: buscar o treino, avisar em caso de conflito e delegar ao motor. O spy e o mock desta rodada estão no slide "Verificar estado ou comportamento". No repositório: WeeklyPlanServiceTest.java, InMemoryTrainingLog.java e SpyCoachNotifier.java, tag rodada-6-colaboradores.
> COMO CONDUZIR: checkpoint: quatro testes verdes (fake+stub+dummy, stub da semana da prova, spy, mock) e a turma sabe dizer, para cada colaborador, qual dublê usou e por quê. Pergunta final: "algum teste desta rodada usa dublê do SUT?" Resposta: não; o WeeklyPlanService é sempre real. Isso responde à dúvida do fake it: dublê substitui o que o SUT usa, nunca o SUT.

### 42. Pausa: olhe as fronteiras

Ciclo primário · a cada hora · 4 min

Testes

JUnit e AssertJ falam só com as regras

Núcleo

```
Plates · RpeRule
FatigueRule · Phase
```

Java puro, sem framework

Bordas

App, planilha, sensor de velocidade, banco

Pergunta para a turma

Se amanhã a velocidade vier de um relógio, quais testes mudam?

> **Notas:** O QUE ESTE SLIDE QUER DIZER: O ciclo de uma hora: depois de muitos ciclos curtos, pare e olhe o todo. As regras (o núcleo) não conhecem JUnit, tela nem banco; tudo aponta para dentro. Por isso, trocar de onde vêm os dados (um relógio, uma planilha) não muda nenhuma regra nem nenhum teste de regra.
> COMO CONDUZIR: Depois de uma hora de ciclos curtos, "The Cycles of TDD" pede uma pausa para olhar o todo: as fronteiras de arquitetura. As setas mostram a regra de dependência que Uncle Bob defende: tudo aponta para o núcleo, e o núcleo não conhece JUnit, UI nem banco. Resposta esperada: nenhum teste das regras muda; só nasce um adaptador novo nas bordas, com os próprios testes. Se a turma disser "vários", é um sinal de que alguma regra está acoplada à entrada de dados — ótimo gancho para a refatoração.

### 43. Refatorar sem medo

Só com a barra verde

| Cheiro no código | Movimento (em código) |
|---|---|
| Números mágicos (2.5, 30, 0.95) | INCREMENT, LIMIT_PCT, LOW_READINESS_FACTOR |
| double para tudo | record Kg(BigDecimal value) com roundDown() dentro |
| Um if gigante com todas as regras | interface Rule { SetLog apply(SetLog s); } e uma lista |
| SetLog enorme em todo teste | aSetLog().withVelocityLoss(35).build() |
| Nome de teste que não explica | velocityLossAbove30RemovesOneSet() |

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Lista de cheiros que apareceram no código do dojo e como refatorar cada um. Refatorar é melhorar o código sem mudar o comportamento, sempre com a barra verde.
> COMO CONDUZIR: Refatoração não é uma fase no fim: acontece a cada verde (ciclo micro). Use este slide durante a pausa do ciclo primário para recapitular o que o time já refatorou e o que ficou como dívida. O record Kg resolveria de vez o bug de ponto flutuante da rodada 3 — mas note que o teste já protege o comportamento, então a troca pode ser feita com calma. Pergunte: "qual desses cheiros apareceu no nosso código hoje?" Resposta provável: o SetLog longo nos testes de fadiga.

---

## A lógica está robusta? Bordas, Right-BICEP, propriedades, mutação e rastreabilidade até o produto

### 44. A lógica está robusta?

Parte 4 · 10 min

Barra verde não basta: os testes precisam pegar um erro de verdade

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Divisória da parte 4. A pergunta-chave: testes verdes provam que a lógica está certa? Não totalmente; provam que os exemplos que escolhemos funcionam.
> COMO CONDUZIR: Transição: "temos 20 e poucos testes verdes. Isso prova que a lógica está certa?" Deixe a turma responder. A resposta honesta: prova que os exemplos que escolhemos funcionam. Agora vamos ver três formas de desconfiar dos próprios testes: bordas e inválidos, propriedades e mutação. E fechamos voltando ao produto: cada critério tem um teste?

### 45. Cinco camadas de confiança

Do mais barato ao mais rigoroso

1

**Exemplos**

Um critério, pelo menos um teste. Foi o dojo.

2

**Bordas**

Os dois lados de cada limite: 29,9%, 30% e 30,1%.

3

**Inválidos**

Carga negativa, RPE fora de 1 a 10: rejeitar com clareza.

4

**Propriedades**

Regras que valem para qualquer entrada, testadas com centenas de valores.

5

**Mutação**

Uma ferramenta estraga o código; algum teste precisa falhar.

Acima de tudo, o teste de aceitação: o ticket inteiro, de ponta a ponta, num teste do motor.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Cinco níveis de confiança, do mais barato ao mais rigoroso. Os níveis 1 a 3 vêm de graça quando você faz as perguntas certas ao produto. Os níveis 4 e 5 são ferramentas para desconfiar dos próprios testes.
> COMO CONDUZIR: As camadas 1 a 3 vêm de graça com TDD bem feito, se você fizer as perguntas certas ao produto (slide "Caçando ambiguidade"). As camadas 4 e 5 são ferramentas extras para desconfiar dos próprios testes: propriedades procuram entradas que você não imaginou; mutação procura testes que não verificam nada. Pergunte: "no nosso dojo, qual camada ficou mais fraca?" Provável resposta: inválidos — só testamos carga negativa, não RPE fora da escala. Ótimo item para a lista de testes.

### 46. O que testar: Right-BICEP

Langr, Hunt e Thomas · Pragmatic Unit Testing in Java with JUnit

| Letra | Pergunta | No nosso projeto |
|---|---|---|
| Right | O resultado está certo? | 101,2 kg vira 100 kg |
| B · Boundary | E nas bordas? | 30% exatos; 8 e 3 semanas |
| I · Inverse | Dá para conferir pelo inverso? | resultado ÷ 2,5 é um número inteiro |
| C · Cross-check | Outro caminho dá o mesmo? | conta em BigDecimal contra a tabela |
| E · Error | E quando dá errado? | carga negativa lança exceção |
| P · Performance | Está rápido o bastante? | a suíte roda em milissegundos |
| ZOM | Zero, um, muitos | semana com 0, 1 e 2 exercícios |

> **Notas:** O QUE ESTE SLIDE QUER DIZER: um checklist para responder "o que mais eu deveria testar?", do livro Pragmatic Unit Testing in Java with JUnit (Jeff Langr, com Andy Hunt e Dave Thomas; a 3ª edição, de 2024, já usa Java 21). Right-BICEP: o resultado certo (Right) e depois B de bordas, I de relação inversa, C de conferir por outro caminho, E de condições de erro e P de performance. A última linha, ZOM (zero, um, muitos), é uma heurística da 3ª edição para coleções: teste com nenhum item, com um e com vários.
> Para as bordas, o livro sugere outro acrônimo, CORRECT: Conformance (formato), Ordering (ordem), Range (faixa de valores), Reference (dependências externas), Existence (existe? nulo?), Cardinality (quantos) e Time (quando). O nosso "RPE fora de 1 a 10" é um caso de Range; "prontidão ausente" seria Existence.
> No projeto: a semana vazia (zero) está em emptyWeekGivesEmptyPlan, um exercício em targetSessionWithHighFatigueRaisesLoadAndDropsOneSet, e vários em meetWeekDropsHeavyDeadlift (EngineAcceptanceTest). A relação inversa e o cruzamento aparecem nos testes de propriedade.
> COMO CONDUZIR: percorra a tabela perguntando "temos teste para isso?". Resposta honesta: Right, Boundary, Error e ZOM, sim; Inverse e Cross-check, via propriedade; Performance só implicitamente. Liga direto com o slide "Cinco camadas de confiança".

### 47. Regras para qualquer entrada

Testes de propriedade · jqwik

Exemplo diz

101,2 kg vira 100 kg.

Propriedade diz

Para qualquer carga, o arredondado nunca passa dela e erra menos de uma anilha.

De onde vêm

Da pergunta ao produto: “o que nunca pode acontecer?”

```java
@Property
void roundDownStaysWithinOnePlate(
    @ForAll @DoubleRange(min = 0, max = 500) double kg) {
  double r = Plates.roundDown(kg);
  assertThat(r).isLessThanOrEqualTo(kg);
  assertThat(kg - r).isLessThan(Plates.INCREMENT);
}

@Property
void higherRpeNeverSuggestsMoreLoad(
    @ForAll @DoubleRange(min = 6, max = 10) double a,
    @ForAll @DoubleRange(min = 6, max = 10) double b) {
  Assume.that(a <= b);
  assertThat(RpeRule.next(100, a))
      .isGreaterThanOrEqualTo(RpeRule.next(100, b));
}
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Teste de propriedade: em vez de um exemplo (101,2 vira 100), você declara uma regra que vale para qualquer valor ("o arredondado nunca passa da carga") e a ferramenta testa centenas de valores aleatórios. Pega casos que ninguém imaginou.
> COMO CONDUZIR: Exemplos testam o que você imaginou; propriedades procuram o que você não imaginou. O jqwik roda no JUnit Platform, lado a lado com os testes Jupiter (dependência de teste: net.jqwik:jqwik). Por padrão ele gera 1000 entradas por propriedade e, quando acha uma falha, "encolhe" (shrinking) até o menor exemplo que quebra — que você pode copiar como teste de exemplo.
> Demonstração ao vivo (2 min): no factorFor do RpeRule, logo depois do primeiro if, adicione um bug: if (rpe < 7.5) return 1.10; Os testes de exemplo (RPE 7, 8, 9 e 10) continuam todos verdes, porque nenhum deles cai entre 7 e 7,5. A propriedade de monotonicidade falha: RPE 7,2 sugere mais carga que RPE 7. O jqwik encolhe e mostra um par concreto de valores. Depois desfaça.
> Pergunta provável: "substitui os testes de exemplo?" Não: exemplos documentam a regra para humanos e o produto; propriedades protegem as garantias gerais. Os dois juntos.
> Pergunta provável: "e a propriedade do roundDown não sofre com double?" Conferimos com uma varredura de 2 milhões de valores: ela passa. Se um dia falhar, o jqwik mostra o valor exato.

### 48. Quem testa os testes?

Testes de mutação · PIT

Como funciona

O PIT faz pequenas mudanças no código de produção. Se nenhum teste falha, o mutante sobreviveu: achamos um buraco.

Resumo

Cobertura mostra o que rodou. Mutação mostra o que foi verificado.

```
// original (FatigueRule)
return last.velocityLossPct() > LIMIT_PCT

// mutant: CONDITIONALS_BOUNDARY
return last.velocityLossPct() >= LIMIT_PCT

// without the 30% edge test: SURVIVED
// with velocityLossOfExactly30KeepsSets: KILLED

$ mvn test-compile \
    org.pitest:pitest-maven:mutationCoverage
```

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Teste de mutação responde à pergunta "quem testa os testes?". A ferramenta PIT altera o código de propósito, por exemplo trocando > por >=. Se nenhum teste falha, os testes não estavam verificando aquilo. Aqui, só o teste de borda de 30% pega a troca.
> COMO CONDUZIR: Este é o argumento final a favor de perguntar pela borda ao produto: sem o teste de exatamente 30%, o PIT troca > por >= e todos os testes continuam verdes — ou seja, o código poderia estar errado e ninguém saberia. Com o teste da borda, o mutante morre.
> Setup: plugin org.pitest:pitest-maven no pom, com a dependência pitest-junit5-plugin dentro do plugin para rodar testes JUnit 5. O relatório HTML sai em target/pit-reports e mostra, linha a linha, os mutantes mortos e sobreviventes.
> Demonstração (3 min): apague o teste velocityLossOfExactly30KeepsSets, rode o PIT e mostre o mutante sobrevivente. Restaure o teste e rode de novo.
> Pergunta provável: "preciso matar 100% dos mutantes?" Não. Alguns mutantes são equivalentes (mudam o código sem mudar o comportamento). Use o relatório para achar buracos nas regras importantes, não como meta numérica.
> Pergunta provável: "é lento?" Mais que os testes normais, porque roda a suíte várias vezes. Rode no CI ou antes de um merge, não a cada ciclo nano.

### 49. Critério, teste e código lado a lado

Fechando o ciclo com produto

| Critério de aceitação | Teste | Produção |
|---|---|---|
| Treino fácil (RPE ≤ 7) sobe 5% | easySessionIncreasesLoadBy5Percent | RpeRule |
| Perda de velocidade acima de 30% tira uma série | velocityLossAbove30RemovesOneSet | FatigueRule |
| Exatamente 30% não muda o volume | velocityLossOfExactly30KeepsSets | FatigueRule.LIMIT_PCT |
| Prontidão ≤ 2 corta 5% da carga | lowReadinessCutsLoadBy5Percent | ReadinessRule |
| Semana da prova sem terra pesado | meetWeekDropsHeavyDeadlift | Phase.allowsHeavy |
| Carga sempre em anilhas de 2,5 kg | roundDownStaysWithinOnePlate | Plates |

Produto lê a primeira coluna; o time mantém as outras duas. Critério sem teste é critério não entregue.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Fecha a aula voltando ao produto: cada critério do ticket aponta para um teste e para um pedaço do código. O produto lê a primeira coluna. Critério sem teste é critério não entregue.
> COMO CONDUZIR: Este é o fechamento da ideia central da aula: cada critério que o produto escreveu tem um teste com nome de regra de negócio, e cada teste aponta para uma parte pequena do código de produção. Isso responde três perguntas que aparecem em todo time: "isso já está pronto?" (o teste existe e está verde), "o que quebra se eu mudar isso?" (os testes da linha) e "por que o código faz isso?" (o critério da primeira coluna). Dica prática: use @DisplayName com o texto do critério em português, e o relatório de testes vira um documento que o produto consegue ler. Pergunta para a turma: "que critério do ticket ainda não tem linha nesta tabela?" Resposta: "perto da competição, treinar menos" só está parcialmente coberto — o fator de volume ainda não entra no motor (desafio para casa).

---

## Retro, desafios e referências

### 50. Três perguntas para fechar

Retro · 1 post-it por pergunta · 5 min

Manter

**O que funcionou?**

Ajustar

**O que doeu?**

Levar

**O que você faz na segunda-feira?**

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Retrospectiva rápida com post-its: o que manter, o que ajustar e o que cada um leva para o trabalho.
> COMO CONDUZIR: Volte às mãos levantadas no início: quem nunca tinha escrito teste antes do código — como foi? Leia alguns post-its em voz alta. Feche com a ideia central: o TDD não é sobre testes, é sobre dar passos pequenos com feedback rápido — como uma boa progressão de cargas.

### 51. Desafios para casa

Pratique como um kata

Seu ticket

Pegar um ticket real do seu time e passar pelo caminho: frase, perguntas, exemplos, testes

Robustez

Rodar o PIT no projeto do dojo e matar todo mutante que sobreviver

Java 24

Portar com _ e Stream Gatherers, sem quebrar nenhum teste

Clean Craftsmanship

Refazer os katas do cap. 2 e comparar os seus passos com os do livro

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Tarefas para continuar em casa. A principal é aplicar o método (frase, perguntas, exemplos, testes) a um ticket real do trabalho.
> COMO CONDUZIR: Deixe o repositório com a lista de testes pendentes no README — é um convite para continuar em casa. O desafio do Java 24 é um bom teste de refatoração: a suíte inteira precisa continuar verde depois da troca de versão.

### 52. Referências

Amadeu Cavalcante · 28/09/2026

**TDD e Java**

- Martin, R. C. The Cycles of TDD. blog.cleancoder.com (2014)
- Martin, R. C. Clean Code, cap. 9 (2008)
- Martin, R. C. The Clean Coder, cap. 5 (2011)
- Martin, R. C. Clean Craftsmanship, Parte I (2021)
- Beck, K. Test-Driven Development: By Example (2002)
- Deitel, P.; Deitel, H. Java How to Program, 12ª ed. (2025)
**Do produto à robustez**

- Adzic, G. Specification by Example (2011)
- Fowler, M. Mocks Aren't Stubs e TestDouble (martinfowler.com)
- jqwik: testes de propriedade no JUnit Platform (jqwik.net)
- PIT: testes de mutação para Java (pitest.org)
- Números do exemplo de treino: fontes no apêndice

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Referências para quem quiser se aprofundar: TDD e Java à esquerda, ferramentas e o método de produto à direita.
> COMO CONDUZIR: Artigo base: https://blog.cleancoder.com/uncle-bob/2014/12/17/TheCyclesOfTDD.html. Para Java e JVM, os Deitel são a referência didática; para os recursos específicos de cada versão (21 e 24), vale apontar também as JEPs no openjdk.org.

---

## Apêndice do facilitador: perguntas difíceis e a origem dos números do exemplo

### 53. Perguntas difíceis

Apêndice

Respostas curtas para quando a turma apertar

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Divisória do apêndice: slides de apoio para abrir só se a pergunta aparecer.
> COMO CONDUZIR: Slides de apoio: abra quando uma dessas perguntas aparecer durante as rodadas ou na retro. Não é para apresentar em sequência.

### 54. TDD na mão

Perguntas difíceis · sobre a prática

TDD não deixa tudo mais lento?

Digitar fica mais lento; entregar, não. Menos tempo no debugger e coragem para refatorar (The Clean Coder, cap. 5).

Preciso testar records e getters?

Não. Teste comportamento. Um record sem lógica já é coberto pelos testes das regras que o usam.

Posso testar método privado?

Teste pela interface pública. Se o privado pede teste próprio, talvez seja uma classe escondida.

Um assert por teste?

Um conceito por teste (Clean Code, cap. 9). Vários asserts sobre o mesmo resultado estão ok.

Teste que já nasce verde vale?

Não guiou código novo. Mantenha se documenta uma regra ou borda; apague se só repete outro teste.

Fake it não é trapaça?

É um passo seguro. O próximo teste força a generalização: é o ciclo milli em ação.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Respostas curtas para dúvidas comuns sobre a prática do TDD. Não apresente em sequência; abra quando alguém perguntar.
> COMO CONDUZIR: Respostas mais longas, se precisar. Lentidão: não prometa números; diga que o custo aparece na digitação e o ganho aparece em debug, regressões e refatoração. Getters: se alguém insistir, pergunte "que bug esse teste pegaria?". Privado: no nosso código, factorFor é privado e é coberto pelo teste parametrizado do RpeRule. Um assert: no Clean Code, Uncle Bob prefere minimizar asserts e testar um conceito por vez. Fake it: é a marcha mais baixa do Kent Beck; ninguém entrega fake — o próximo teste obriga a generalizar.

### 55. TDD no trabalho

Perguntas difíceis · no dia a dia

E código legado sem testes?

Antes de mexer, escreva testes que capturam o comportamento atual. Depois aplique o ciclo no trecho novo.

Mock em tudo?

Hoje foram zero mocks: as regras são puras. Mocks ficam nas bordas, como banco, rede e sensor.

Cobertura de 100% é a meta?

Cobertura alta é consequência do TDD, não meta de gestão. Número alto com teste fraco não protege nada.

Quando posso pular o TDD?

Num protótipo descartável para aprender algo. Depois, jogue fora e refaça com testes.

Onde fica o design, então?

Na lista de testes, no contrato e no ciclo primário. O resto emerge da refatoração.

Muda algo entre Java 21 e 24?

O ciclo e o JUnit são iguais. Muda o que a linguagem oferece, como o _ e os Stream Gatherers.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: Respostas curtas para dúvidas sobre TDD no dia a dia do trabalho: legado, mocks, cobertura, quando pular. Abra só se a pergunta aparecer.
> COMO CONDUZIR: Legado: a técnica tem nome, testes de caracterização (Michael Feathers, Working Effectively with Legacy Code). Mocks: se um teste de regra pede mock, a regra provavelmente conhece I/O demais — volte ao slide do ciclo primário. Cobertura: use como lanterna (onde não há teste?), não como meta. Protótipo: o erro comum é o protótipo virar produção; combine antes com o time que ele será descartado. Java: lembre o limite de bytecode — compilou com --release 24, não roda numa JVM 21.

### 56. O que a literatura diz

Apêndice · de onde vieram os números do exemplo

RPE por RIR

RPE 8 = 2 RIR

Escala de 1 a 10 ancorada em repetições na reserva: RPE 10 é falha, 9 deixa uma. Permite autorregular a carga do dia.

Zourdos et al., 2016 · Helms et al., 2016

Perda de velocidade

20% vs 40%

Parar a série com 20% de perda deu ganhos de força no agachamento semelhantes com bem menos repetições. Com 40%, mais hipertrofia e mais fadiga.

Pareja-Blanco et al., 2017

Polimento (taper)

−30 a −50%

Em 1 a 2 semanas: cortar volume, manter a intensidade (≥ 85% 1RM) e descansar de 2 a 7 dias antes da prova.

Travis et al., 2020 (revisão)

Usamos esses números para modelar a aula. Não é prescrição individual de treino.

> **Notas:** O QUE ESTE SLIDE QUER DIZER: De onde vieram os números do exemplo de treino. Só para quem perguntar; não é o foco da aula.
> COMO CONDUZIR: Pontos-chave. RPE/RIR: a precisão do atleta melhora com experiência, então iniciantes erram mais — isso justifica cruzar RPE com velocidade. Velocidade: Pareja-Blanco (2017) comparou parar a série com 20% vs 40% de perda de velocidade no agachamento por 8 semanas; o grupo de 20% ganhou força parecida (ou mais) com muito menos volume, e melhor salto; o de 40% teve mais hipertrofia. Taper: a revisão de Travis et al. (2020) sugere cortes pequenos a moderados de volume (30–50%) melhores que cortes grandes (acima de 50%), e o terra às vezes sai 1–2 semanas antes da prova. Pergunte: "alguém aqui já fez polimento? Como foi?"
