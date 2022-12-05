# scjr1-integrations
## Projeto de Drones - Integrations & Development Tools

O nosso objetivo nesse trabalho é apresentar como podemos realizar projetos complexos com mais facilidade, escalabilidade, flexibilidade e resiliência.

À seguir, segue o vídeo do nosso integrante Thiago Dias ensinando a como configurar o projeto corretamente e como a estrutura do mesmo está trabalhando.

-> https://youtu.be/sUFioQvtJWA

Utilizando os conceitos de producer e consumer, o nosso projeto consiste na criação de uma ou várias instâncias de drones onde o mesmo receberá informações que serão geradas à cada 10s (dez segundos) sobre a latitude, longitude, temperatura e a umidade do ambiente, onde essas informações serão gravadas dentro de um arquivo log para o usuário poder visualizar desde as mais antigas informações que foram geradas quanto as mais recentes, assim possibilitando o controle do usuário sob suas ações.

### Quais são as etapas para a criação dos drones?

Possuímos um micro serviço onde será instanciado um ou mais drones que receberão informações à cada 10s (dez segundos) sobre a latitude, longitude, temperatura e a umidade do ambiente. Não existindo limitações sobre quantas instâncias de drones podem ser criadas. Essas informações são enviadas o nosso Producer.

O producer recebe todas as informações geradas pelo drone via API e as coloca numa fila de produção previamente configurada.

O consumer também receberá todas as inforamações que serão geradas pelo drone via payload e irá escrever todas as informações recebidas num log que poderá ser visualizado pelo usuário. 
