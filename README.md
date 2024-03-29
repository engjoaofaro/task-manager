[![Github Actions Status for engjoaofaro/task-manager](https://github.com/engjoaofaro/task-manager/workflows/Build/badge.svg)](https://github.com/engjoaofaro/task-manager/actions) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=engjoaofaro_task-manager&metric=alert_status)](https://sonarcloud.io/dashboard?id=engjoaofaro_task-manager) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![Open issues](https://img.shields.io/github/issues/engjoaofaro/task-manager)](https://github.com/engjoaofaro/task-manager/issues)

[![Tag](https://img.shields.io/github/v/tag/engjoaofaro/task-manager)](https://github.com/engjoaofaro/task-manager/tags)

# TASK-MANAGER

Projeto desenvolvido para fins de testes e avaliação.

O projeto consiste em uma API <b>TODO-LIST</b> de gerenciamento de tarefas.

### Tecnologias utilizadas
* Java 11
* Database H2
* Keycloak (Gerenciamento de segurança)
* Spring boot
* Swagger hub
* Amazon EC2 (Deploy aplicação)

## AUTHOR

João Paulo Bremgartner Faro, Engenheiro de computação e especialista em Segurança computacional.

<email>joaobremgartner@gmail.com</email>
<br>
<email>contato@joaofaro.eng.br</email>

## INSTRUÇÕES IMPORTANTES

Com a proposta de solução para modernização de sistemas utilizei para o papel de servidor de <b>Autenticação</b> e <b>Autorização</b> o projeto OpenSource chamado [Keycloak](https://www.keycloak.org). 

Lá é onde se concentra as informações relacionadas ao token e informações importantes a respeito do usuário do sistema (<b>Principal</b>) e as roles utilizadas. Desta forma consegui ter um código mais conciso e clean no que diz respeito a estas funcionalidades.

Importante ressaltar que o projeto <code>task-manager</code> não depende da instalação prévia do Keycloak, pois já existe um <i>standalone</i> rodando em uma máquina (EC2) minha na Amazon (A mesma utilizada para consulta aos endpoints deste projeto) e este projeto já está apontando para o endereço dela para a aquisição do <code>token</code> (<b>Ver arquivo application.yml</b>). 

Caso queira instalar localmente o keycloack para saber como foi configurado os <code>usuários</code> e <code>roles</code> estou disponibilizando um link da pasta completa no [S3](https://engjoaofaro.s3-sa-east-1.amazonaws.com/keycloak/keycloak-11.0.3.zip) ao qual já está tudo configurado. Após fazer o download, extrair e a partir da pasta <code>/bin</code> executar o seguinte comando:
```bash
sh standalone.sh
```
Depois seguir para a página: http://localhost:8080/auth e digitar user e pass <b>admin:admin</b> respectivamente.

OBS: Caso faça local não esquecer de mudar o apontamento no arquivo <code>application.yml</code> para <i>localhost</i>.

Para mais informações a respeito do keycloak, escrevi um [artigo](https://joaofaro.eng.br/2020/07/09/spring-boot-garantindo-a-seguranca-de-apis-rest-com-keycloak-e-spring-boot/) recentemente em meu <code>blog pessoal</code> de como configurar e integrar com o Spring boot.

Usuários criados durante os testes(E permanecem lá):
* common-user1 (role_user)
* common-user2 (role_user)
* itau-unibanco (role_super_user)

Senha é a mesma para todos os usuários: <code>@(3lg@</code>

* client_id: task-manager-app
* client_secret: 0bf3bd99-2c54-4d6a-85cb-68db54944ddc


OBS: Ao startar pela primeira vez o servidor do <b>keycloak</b>, como é um diretório copiado da minha máquina, talvez acuse um <i>NoSuchFileException</i> por causa do file de log que ele grava, mas pode seguir sem problemas(Na segunda chamada não irá mais acontecer).

Verificar exemplos de requisições nos testes abaixo.

## REQUISITOS
* JAVA 11

## INSTALAÇÃO
* Realizar o clone do projeto aqui no github.

## CONTEXTO PARA TESTES DIRETO NA AMAZON

* AUTH: http://54.94.75.4:8080/auth/realms/itau/protocol/openid-connect/token
* TASK: http://54.94.3.4:8000/task-manager/tasks

## DOCUMENTAÇÃO

* Documentação da API no SWAGGER: https://app.swaggerhub.com/apis-docs/joaofaro/task-manager/1.0.0
* Documentação do Java doc no projeto.

## EXEMPLOS DE USO E TESTES
* AUTH:
  
```bash
curl -X POST 'http://localhost:8080/auth/realms/itau/protocol/openid-connect/token' \
 --header 'Content-Type: application/x-www-form-urlencoded' \
 --data-urlencode 'grant_type=password' \
 --data-urlencode 'client_id=task-manager-app' \
 --data-urlencode 'client_secret=0bf3bd99-2c54-4d6a-85cb-68db54944ddc' \
 --data-urlencode 'username=common-user1' \
 --data-urlencode 'password=@(3lg@' -v
 ```

 * CREATE TASK

```bash
curl -v -X POST -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJkZzdTcGFPRF8zV0dhUWFwS2JIMUJHeHBWMDFZaEp6SXVSSm9jOEZsMEQwIn0.eyJleHAiOjE2MDU0OTc1NjQsImlhdCI6MTYwNTQ5NzI2NCwianRpIjoiZGZkODM2MGMtZmQ5OC00Yzc0LThmMzYtMzc5MmViZTAyYTM5IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL2l0YXUiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiZDRjNzRhMDktYzZlNC00NWIxLTk3ZWUtNTA0OTliZjhhY2YxIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoidGFzay1tYW5hZ2VyLWFwcCIsInNlc3Npb25fc3RhdGUiOiJlNmJlNzUxYS00Nzk5LTQ2OGMtYTZlNi04YjhiNzY5NTBjNWUiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6ODA4MCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsImFwcC11c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsidGFzay1tYW5hZ2VyLWFwcCI6eyJyb2xlcyI6WyJ1c2VyIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicHJlZmVycmVkX3VzZXJuYW1lIjoiY29tbW9uLXVzZXIxIn0.KS8ZRHd8Z3QEx2d5bAQ9BaOzS_mLwJ_Y_-img_LJ6kwGK4Vl79gI3GwFblydWKRVeM_7LHXMsGUEcJlmQ9TEJz_zJQ2Qzocf01uHSgbNdOO22lBKiC9ej6PgtDBZsld_SCN8sdVPCAoBiRPESETWyRCIdIdxlc0YbIZgc3uMdutvAMgi1DaOKiVEIjf9t8TTKyQTj1nJdr18SF2ngV4d5oDan3CX-8Tmq-4s70KLc4fa60om5huS6-Eoa6q8n63QZWXl6iWjuomgEmHlffCZeePBldNEvi6vDfb8vycxBBV8gJ9RoNRot9VrjqeFx1t8LK4dvVrKoNRak2OwvllgoQ' -H "Content-type: application/json" -d '{
    "shortDescription": "Teste 3", 
    "description": "Teste 3 detalhado"
    "shortDescription": "Teste 3", 
    "description": "Teste 3 mais detalhado"
}' 'http://localhost:8000/task-manager/task'
```

* GET ALL TASKS FOR USER
  
```bash
curl -v -X GET -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJkZzdTcGFPRF8zV0dhUWFwS2JIMUJHeHBWMDFZaEp6SXVSSm9jOEZsMEQwIn0.eyJleHAiOjE2MDU0OTc5ODIsImlhdCI6MTYwNTQ5NzY4MiwianRpIjoiMmQ2MTE2MWEtOTM3MC00MDU0LWEwOWItMWFlMDY0MTIzODQ1IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL2l0YXUiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiZDRjNzRhMDktYzZlNC00NWIxLTk3ZWUtNTA0OTliZjhhY2YxIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoidGFzay1tYW5hZ2VyLWFwcCIsInNlc3Npb25fc3RhdGUiOiIwNDE2YTAzZi01ODhkLTQ5YjMtYWU5NC0zOTAzMGNlNjdlZjUiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6ODA4MCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsImFwcC11c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsidGFzay1tYW5hZ2VyLWFwcCI6eyJyb2xlcyI6WyJ1c2VyIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicHJlZmVycmVkX3VzZXJuYW1lIjoiY29tbW9uLXVzZXIxIn0.G5ryFaFSINaXY1uxfZ7-lcwV7S5zh5BCk7uGfvlv0XaKA3pjBFH8sa-hf0wDIvsqbwZteXqw3VSEFG2j7ffeuLBdV18aP3CrWTrl5xfLbdF6oLo-PQkepHJoYTZdT4mnRYcZRRLtRpcuKRU18XBBX7nkLWydrse7XEBN1TeU6pPyOBD2X0-lgzllVDCA536Zixf8sCCmUl3KsWiXb9TWujfq42zaW-OvWZkwEWWdwfEH4cb8JWXXVHZQmcjK9jcGUA7F_lOSSEN0-YTCTDqWJAB9O1d6EfobHPx2-OQAeVEkOVTIxqEiV6kYsteuBePhkKVHFoaBRFiDZM5ksxihkg' 'http://localhost:8000/task-manager/tasks'
```

* GET ALL TASKS WITH STATUS

```bash
curl -v -X GET -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJkZzdTcGFPRF8zV0dhUWFwS2JIMUJHeHBWMDFZaEp6SXVSSm9jOEZsMEQwIn0.eyJleHAiOjE2MDU0OTgzODcsImlhdCI6MTYwNTQ5ODA4NywianRpIjoiZDM5M2U5MjUtNjRlZi00NWM3LWEwZWEtMTgxZmI4NGE5ZjA1IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL2l0YXUiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiZDRjNzRhMDktYzZlNC00NWIxLTk3ZWUtNTA0OTliZjhhY2YxIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoidGFzay1tYW5hZ2VyLWFwcCIsInNlc3Npb25fc3RhdGUiOiJhYmE4OTYxOC1iMTQwLTRkYTEtOTE1NS1jNWI4ZGVlNjc2MDgiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6ODA4MCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsImFwcC11c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsidGFzay1tYW5hZ2VyLWFwcCI6eyJyb2xlcyI6WyJ1c2VyIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicHJlZmVycmVkX3VzZXJuYW1lIjoiY29tbW9uLXVzZXIxIn0.PbMIzPo_SyI1A9tBeVKfKwDYgfnKrPgodrEqmEUyjyiCirI3I5SMNp63aXGs7JowPIuZ3e-eO5B6WnHjkocNoI31R87rRotUziH2H72JuGH6t3cdNqYe-6y7iLeMv0oFKJeGgBHh0p_chVkTouFFufwXho1s4ZmO9yGzCm7PC_sLvZBXWk1_W8QTahC_nNGc2ehCRqDozx0kUrtBtbtA3UBK3Sjy24hl81-wLqhf5pn-Lh5JxuYJ5Opk_n4_evXUrnI3gt-Sn2dOznWvvk0pOpmUMgnU0cUQFzrhKPY789xaYKnuAsiAOgulbUBIfid6D10m_IF2t-_xR_cSpjw5YQ' 'http://localhost:8000/task-manager/tasks?status=pending'
```

* GET TASK

```bash
curl -v -X GET -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJkZzdTcGFPRF8zV0dhUWFwS2JIMUJHeHBWMDFZaEp6SXVSSm9jOEZsMEQwIn0.eyJleHAiOjE2MDU0OTgzODcsImlhdCI6MTYwNTQ5ODA4NywianRpIjoiZDM5M2U5MjUtNjRlZi00NWM3LWEwZWEtMTgxZmI4NGE5ZjA1IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL2l0YXUiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiZDRjNzRhMDktYzZlNC00NWIxLTk3ZWUtNTA0OTliZjhhY2YxIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoidGFzay1tYW5hZ2VyLWFwcCIsInNlc3Npb25fc3RhdGUiOiJhYmE4OTYxOC1iMTQwLTRkYTEtOTE1NS1jNWI4ZGVlNjc2MDgiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6ODA4MCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsImFwcC11c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsidGFzay1tYW5hZ2VyLWFwcCI6eyJyb2xlcyI6WyJ1c2VyIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicHJlZmVycmVkX3VzZXJuYW1lIjoiY29tbW9uLXVzZXIxIn0.PbMIzPo_SyI1A9tBeVKfKwDYgfnKrPgodrEqmEUyjyiCirI3I5SMNp63aXGs7JowPIuZ3e-eO5B6WnHjkocNoI31R87rRotUziH2H72JuGH6t3cdNqYe-6y7iLeMv0oFKJeGgBHh0p_chVkTouFFufwXho1s4ZmO9yGzCm7PC_sLvZBXWk1_W8QTahC_nNGc2ehCRqDozx0kUrtBtbtA3UBK3Sjy24hl81-wLqhf5pn-Lh5JxuYJ5Opk_n4_evXUrnI3gt-Sn2dOznWvvk0pOpmUMgnU0cUQFzrhKPY789xaYKnuAsiAOgulbUBIfid6D10m_IF2t-_xR_cSpjw5YQ' 'http://localhost:8000/task-manager/task/1'
```

* UPDATE TASK

```bash
curl -v -X PUT -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJkZzdTcGFPRF8zV0dhUWFwS2JIMUJHeHBWMDFZaEp6SXVSSm9jOEZsMEQwIn0.eyJleHAiOjE2MDU0OTgzODcsImlhdCI6MTYwNTQ5ODA4NywianRpIjoiZDM5M2U5MjUtNjRlZi00NWM3LWEwZWEtMTgxZmI4NGE5ZjA1IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL2l0YXUiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiZDRjNzRhMDktYzZlNC00NWIxLTk3ZWUtNTA0OTliZjhhY2YxIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoidGFzay1tYW5hZ2VyLWFwcCIsInNlc3Npb25fc3RhdGUiOiJhYmE4OTYxOC1iMTQwLTRkYTEtOTE1NS1jNWI4ZGVlNjc2MDgiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6ODA4MCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsImFwcC11c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsidGFzay1tYW5hZ2VyLWFwcCI6eyJyb2xlcyI6WyJ1c2VyIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicHJlZmVycmVkX3VzZXJuYW1lIjoiY29tbW9uLXVzZXIxIn0.PbMIzPo_SyI1A9tBeVKfKwDYgfnKrPgodrEqmEUyjyiCirI3I5SMNp63aXGs7JowPIuZ3e-eO5B6WnHjkocNoI31R87rRotUziH2H72JuGH6t3cdNqYe-6y7iLeMv0oFKJeGgBHh0p_chVkTouFFufwXho1s4ZmO9yGzCm7PC_sLvZBXWk1_W8QTahC_nNGc2ehCRqDozx0kUrtBtbtA3UBK3Sjy24hl81-wLqhf5pn-Lh5JxuYJ5Opk_n4_evXUrnI3gt-Sn2dOznWvvk0pOpmUMgnU0cUQFzrhKPY789xaYKnuAsiAOgulbUBIfid6D10m_IF2t-_xR_cSpjw5YQ' 'http://localhost:8000/task-manager/task/2/completed'
```

* DELETE TASK

```bash
curl -v -X DELETE -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJkZzdTcGFPRF8zV0dhUWFwS2JIMUJHeHBWMDFZaEp6SXVSSm9jOEZsMEQwIn0.eyJleHAiOjE2MDU0OTg3NTMsImlhdCI6MTYwNTQ5ODQ1MywianRpIjoiNzlmZWUxMDctN2E2Yi00MjdmLTgxZTgtOGYzZDU5YzViYmQ5IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL2l0YXUiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiZDRjNzRhMDktYzZlNC00NWIxLTk3ZWUtNTA0OTliZjhhY2YxIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoidGFzay1tYW5hZ2VyLWFwcCIsInNlc3Npb25fc3RhdGUiOiJlMzFiMmMxYS02MGRjLTQxOTQtOGY2Yy02OGRlOWUzNGUwMjUiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6ODA4MCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsImFwcC11c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsidGFzay1tYW5hZ2VyLWFwcCI6eyJyb2xlcyI6WyJ1c2VyIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwicHJlZmVycmVkX3VzZXJuYW1lIjoiY29tbW9uLXVzZXIxIn0.NPzOLCncf8kN7Lm5v1JnSSXLpW9RtvzHdpkm5Vjt1nnBZNxc9h8pKqp5xANzA0WMjmViYLbh9GnZvd6HB21khKEm8DZ0tx4zK9aAgCy7y8_oJ44wrTLh8a-4WAlIesXxo_f53rz3Acm9terWhZD4HpjY3Igo_eMSBG0TqlSEjpdGk6OVCdF7DGSKcadmXSm8AInFh-T_11mv0x_nnXHsOT0CNdrvkD4bAQbUXiE3T1YUPF9UgIo-_sQZQvcADI3s2N7vtq_5A4FK7ASCZBqX1NGOBnceH-KmoayxxQrT3LYY91oXEXPAOzQE-l4H026tLcnmf_YsTOT1tY5owmI90A' 'http://localhost:8000/task-manager/task/1'
```

* Se preferir disponibilizo a [collection](https://www.getpostman.com/collections/5a7d2657e838a33d4253) no Postman.

<b>OBS: A API tem todas as funcionalidades e restrições descritas no CASE.</b>

## LICENSE
[MIT](https://choosealicense.com/licenses/mit/)
