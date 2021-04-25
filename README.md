# Kanban Board
칸반보드를 제작한 프로젝트입니다. <br>
기본적으로 로그인 가능한 ID/PW는 `woghd8754@naver.com / jaehong123` 입니다. <br>
로그인 후 기본적으로 보이는 페이지 내 좌측의 **사이드바**에는 멤버가 가입한 프로젝트들이 보여집니다. <br> 그리고 사이드바의 우측에는 선택된 프로젝트내에 존재하는 ISSUE들이 보여집니다. ISSUE들은 BACKLOG / IN PROGRESS /DONE 세 가지 상태를 가집니다. <br>
`Add a card...`를 선택함으로써 해당 프로젝트에 ISSUE를 추가할 수 있고, <br>
각 ISSUE의 제목을 선택함으로써 ISSUE의 내용을 수정할 수 있고, <br>
ISSUE 위에 존재하는 휴지통 아이콘을 선택함으로써 ISSUE를 제거할 수 있습니다.
<iframe width="640" height="360" src="https://youtu.be/euQXlNSvJ3Q" frameborder="0" gesture="media" allowfullscreen=""></iframe>

# How to Start
1. Use IntelliJ IDE and Run KanbanApplication

or

2.
```
./gradlew build
java -jar .\build\libs\kanban-0.0.1-SNAPSHOT.jar
```

# Used Dependencies
- Java 11
- Spring boot starter web (default with Spring MVC, Tomcat)
- Spring boot starter data jpa (default with Hibernate, Spring Data)
- Thymeleaf
- H2 Database
- Lombok

# UML Diagram

# TODO
- [ ] EPIC - TASK의 관계 설정
- [ ] API 기능 제공
- [x] Spring Data JPA 적용 (Project, ProjectMember)
- [ ] Exception 정의 및 적절한 사용
