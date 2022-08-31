# Descomplica

Projetos de exemplo para as aulas da faculdade Descomplica

## Dockerfile

Aqui nesta pasta há um exemplo de um arquivo Dockerfile bastante simples que nos possibilitará criar uma imagem do Docker para uso em quantos containers precisarmos/quisermos.

O nosso arquivo Dockerfile de exemplo possui esta estrutura:

FROM ubuntu:21.10

RUN apt-get update \
    && apt-get install -y nginx \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* \
    && echo "daemon off;" >> /etc/nginx/nginx.conf

EXPOSE 80
CMD ["nginx"]

Onde: 

FROM ubuntu:21.10

É a imagem base da imagem que estamos criando, significa que nossa imagem precisa de um "chassi" para construirmos em cima dele o que precisamos, em nosso exemplo simples nós utilizaremos uma versão "light" do Ubuntu Linux 21.10

RUN apt-get update \
    && apt-get install -y nginx \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* \
    && echo "daemon off;" >> /etc/nginx/nginx.conf

Pra quem conhece Linux, saberá que isso é um conjunto de comandos que serão executados na ordem em que se encontram, neste caso será executado em cima da nossa imagem base (Ubuntu Linux) e este comando indica o seguite:

apt-get update: traz a lista de atualizações dos pacotes disponíveis com base no que já está instalado
apt-get install -y nginx: faz o download do pacote do nginx e efetua a instalação
apt-get clean: apaga pacotes que não tiveram download concluído
rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*: exclui as pastas e arquivos presentes (pastas /var/lib/apt/lists/, /tmp/ e /var/tmp/)
echo "daemon off;" >> /etc/nginx/nginx.conf: cria um arquivo chamado nginx.conf na pasta /etc/nginx com o conteúdo: daemon off;

EXPOSE 80

Indica que todo container criado à partir da imagem que estamos construindo terá uma entrada especificamente na porta 80, isso indica que a aplicação (no nosso caso o nginx) terá que receber as requisições através desta porta. Não confundir com bind de porta externa, aqui estamos indicando que internamente o container responderá a aplicação nginx através desta porta.

CMD ["nginx"]

Tão logo o container desta imagem suba, será executado este comando no "bash" do Ubuntu Linux, este comando literalmente "liga" o nginx.

Para criar a imagem à partir deste Dockerfile, abra o Prompt de Comandos ou terminal Bash e digite o seguinte comando:

`docker build -t meunginx:latest .`

-t meunginx:latest: será a etiqueta da tua imagem, para criar containers à partir desta imagem recém criada, você deverá referenciar esta etiqueta.

O ponto final no comando é para indicar que o Dockerfile está na mesma pasta em que você está digitando o comando. Se o Dockerfile estiver em outra pasta, troque o ponto final pelo caminho completo de pastas onde o arquivo Dockerfile se encontra.

Para subir um container à partir desta imagem recém criada, você pode usar o seguinte comando:

`docker run --name MeuNginx -p 8080:80 -d meunginx:latest`

Neste comando você estará subindo um container apelidado de MeuNginx, com o bind de porta 8080 (que é a que você utilizará para abrir o site do Nginx digitando no browser localhost:8080) para a porta 80 (que é a que indicamos expor a aplicação na definição da imagem). O parâmetro -d indica que o container se manterá em execução e por último você usa a etiqueta da imagem recém criada (meunginx:latest).