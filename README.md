# reward

선착순 보상 지급 프로젝트.
동시성 및 선착순 보장을 위해 rabbitmq 사용

# 기술스택
- java8, spring boot, rabbitmq
- db : h2, redis
- docker 
- front : vue

# 실행방법
루트 경로에서 아래 명령어 실행 후, localhost:8080/login 접속
```
./gradlew clean build
docker-compose up --build
```

# 주의사항
- h2-console 접속시, jdbc url -> jdbc:h2:mem:test 로 변경 필요
- 메모리 디비로 실행마다 디비는 리셋되나, 캐싱용 레디스는 지워지지 않아 재실행시 레디스 초기화 필요
- 프론트는 이미 빌드된 파일이지만, 재 빌드/반영이 필요한경우. 또는 동작이 안되는경우 아래 재시도
```
cd reward-project
npm install
npm run build

cd ..
./gradlew clean build
docker-compose up --build
```

