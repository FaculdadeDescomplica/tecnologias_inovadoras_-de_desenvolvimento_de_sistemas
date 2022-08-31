# Descomplica

Projetos de exemplo para as aulas da faculdade Descomplica

## Volumes

Aqui nesta pasta há dois exemplos de um arquivos distintos que usaremos para criar um volume em um container que esteja usando uma imagem do nginx (que é um web server enxuto) e assim podermos manipular de fora do container esses arquivos para que sejam sensibilizamos dinamicamente ao chamarmos o endereço que a aplicação do nginx atenderá.

O seguinte comando cria um container simples à partir da imagem do Nginx, sem uso de volume nenhum:

`docker run --name NginxSemVolume -p 9080:80 -d nginx:latest`

Aqui ao acessar a aplicação no browser através do endereço localhost:9080, você verá uma página padrão que já vem com o Nginx, é uma página de boas vindas.

Se quisermos alterar essa página padrão de boas vindas, podemos criar um volume e anexá-lo ao container, fazendo da forma indicada abaixo nós conseguimos "dizer" ao Nginx para usar uma página nossa, ao invés da página de boas vindas padrão:

`docker run --name NginxComVolume -v /D/temp/docker:/usr/share/nginx/html -p 9180:80 -d nginx:latest`

Importante: no comando acima, eu indiquei no volume a pasta /D/temp/docker, significa que no meu Windows aqui efetivamente existe uma pasta chamada D:\temp\docker (em meu computador existe o drive D, se o seu não tiver, troque pela letra correspondente), e eu criei uma pasta chamada temp e dentro dela eu criei outra pasta chamada docker.

Importante 2: o arquivo index.html da pasta deste repositório deverá ser copiado para a pasta indicada no volume do comando acima, significa que na pasta D:\temp\docker, eu copiei o arquivo index.html pra dentro desta pasta, então existe um D:\temp\docker\index.html

No comando acima eu alterei a porta para 9180 para que você consiga ter os 2 containers rodando em paralelo e veja as diferenças entre chamar no browser por localhost:9080 (que aparecerá a página padrão do Nginx) e o localhost: 9180 (que aparecerá o conteúdo do nosso index.html que está em nosso volume).

Na montagem do volume, o que está depois dos : (dois pontos) é importante! Indica que toda vez que o Nginx precisar "olhar" para o local padrão das páginas (usr/share/nginx/html), ele na verdade estará olhando para o nosso D:\temp\docker, e é por isso que ele exibe a nossa página ao invés da página padrão.

Para você verificar que não precisa destruir e recriar o container para fazer qualquer alteração na index.html, faça o seguinte experimento:

Troque o conteúdo do arquivo D:\temp\docker\index.html por alguma outra coisa e dê um refresh no browser no endereço localhost:9180 e veja o que acontece.

Ou use o arquivo index_mexido.html deste repositório e faça o seguinte: Delete o arquivo index.html da pasta D:\temp\docker, copie o arquivo index_mexido.html e cole dentro da pasta D:\temp\docker e por último renomeie o arquivo D:\temp\docker\index_mexido.html para D:\temp\docker\index.html, dê um refresh no browser no endereço localhost:9180 e veja o que acontece.