package com.example.kanban;

import com.example.kanban.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }


    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService {
        private final EntityManager em;

        public void dbInit() {
            Member member = createMember("Jaehong PARK", "jaehong123", "woghd8754@naver.com", "01068797666");
            em.persist(member);
            Member member1 = createMember("Minsu Kim", "abcdefghijk", "minsu@kakao.com", "01012345678");
            em.persist(member1);
            Member member2 = createMember("Gildong Hong", "gogildong", "dooly123@gmail.com", "01077777777");
            em.persist(member2);

            Project project = createProject(member, "Main Project");
            em.persist(project);
            Project project1 = createProject(member1, "Era of new types");
            em.persist(project1);

            ProjectMember projectMember = mapProjectMember(member1, project);
            em.persist(projectMember);
            ProjectMember projectMember2 = mapProjectMember(member2, project);
            em.persist(projectMember2);
            ProjectMember projectMember3 = mapProjectMember(member, project1);
            em.persist(projectMember3);

            Task task1 = createTask(member, project, "Job Meeting", "Job meeting with Naver corp' will be held via Internet on Thu 29th April. \n There's not enough time to ready. Pleas! plz!! do your One-hundred percent Jay.");
            task1.setTaskStatus(TaskStatus.IN_PROGRESS);
            em.persist(task1);
            Task task = createTask(member, project, "Task 1", "This issue should be done till tomorrow!");
            task.setTaskStatus(TaskStatus.DONE);
            em.persist(task);
            Task task2 = createTask(member1, project, "Make Restful API", "Prepare APIs for this project. It should be satisfy Restful conditions and return appropriate exceptions when user sends invalid or malicious request! ");
            em.persist(task2);
            Task task3 = createTask(member2, project, "Key feature", "Here are a list of tasks that we highly recommend you have a go at:\n" +
                    "\n" +
                    "Explore the customer portal and see what your customers see\n" +
                    "Create a new request and assign it to yourself\n" +
                    "View the queue, edit an issue description, or add a label\n");
            em.persist(task3);
            Task task4 = createTask(member, project, "Key feature 2", "Comment on an issue\n" +
                    "Try out your email channel\n" +
                    "Play with reports");

            Task task5 = createTask(member, project1, "Job Meeting(Urgent)", "Job meeting with Naver corp' will be held via Internet on Thu 29th April. \n There's not enough time to ready. Pleas! plz!! do your One-hundred percent Jay.");
            task1.setTaskStatus(TaskStatus.IN_PROGRESS);
            em.persist(task1);

            Task task6 = createTask(member1, project1, "Explore Sample Project", "Jira Service Desk comes with sample data to help you explore and learn how to use key features. \n \n" +
                    "When you create a sample project, it gets populated with issues that new team members can use to learn about concepts like queues, SLAs, and generate reports like the one below without fear of affecting any real work. ");
            em.persist(task6);
        }

        private Task createTask(Member member, Project project, String title, String description) {
            Task task = new Task();
            task.setReporter(member);
            task.setTaskStatus(TaskStatus.BACKLOG);
            task.setTitle(title);
            task.setDescription(description);
            task.setDueDate(new Date());
            task.setProject(project);

            return task;
        }

        private ProjectMember mapProjectMember(Member member1, Project project) {
            ProjectMember projectMember = new ProjectMember();
            projectMember.setProject(project);
            projectMember.setMember(member1);
            return projectMember;
        }

        private Project createProject(Member member, String name) {
            Project project = new Project();
            project.setOwner(member);
            project.setName(name);
            return project;
        }

        private Member createMember(String name, String password, String email, String phoneNumber) {
            Member member = new Member();
            member.setName(name);
            member.setPassword(Integer.toString(Objects.hash(password)));
            member.setEmail(email);
            member.setPhoneNumber(phoneNumber);
            return member;
        }

    }
}
