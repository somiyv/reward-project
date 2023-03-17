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

# 테스트 방법
1. 메인페이지에 등록된 아이디로 로그인합니다.
id : member1 , pw : member1
id : member2 , pw : member2
id : member3 , pw : member3
id : member4 , pw : member4
id : member5 , pw : member5
id : member6 , pw : member6
id : member7 , pw : member7
id : member8 , pw : member8
id : member9 , pw : member9
id : member10 , pw : member10
id : member11 , pw : member11
id : member12 , pw : member12
2. 조회 또는 보상지급 버튼을 눌러 보상을 확인합니다.
3. 리스트에서 눌러 상세 데이터를 조회합니다.

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

