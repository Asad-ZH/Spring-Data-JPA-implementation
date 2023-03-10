package com.mylearning.education_class_database;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class EducationClassDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationClassDatabaseApplication.class, args);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.tutorialspoint.swaggerdemo")).build();
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, StudentIdCardRepository studentIdCardRepository) {
        return args -> {
            Faker faker = new Faker();

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@amigoscode.edu", firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));

            student.addBook(
                    new Book("Clean Code", LocalDateTime.now().minusDays(4)));


            student.addBook(
                    new Book("Think and Grow Rich", LocalDateTime.now()));


            student.addBook(
                    new Book("Spring Data JPA", LocalDateTime.now().minusYears(1)));

            StudentIdCard studentIdCard = new StudentIdCard(
                    faker.number().toString(),
                    student);

//            student.enrolToCourse(
//                    new Course("ipdc","course1")
//            );
//            student.enrolToCourse(
//                    new Course("gd","course2")
//            );
//            student.enrolToCourse(
//                    new Course("mis","course3")
//            );

            student.addEnrollment(new Enrolment(
                    new EnrolmentId(142L, 1423L),
                    student,
                    new Course("Computer Science", "IT"),
                    LocalDateTime.now()
            ));

            student.setStudentIdCard(studentIdCard);

            studentRepository.save(student);

            studentRepository.findById(2L)
                    .ifPresent(s -> {
                        System.out.println("fetch book lazy...");
                        List<Book> books = student.getBooks();
                        books.forEach(book -> {
                            System.out.println(
                                    s.getFirstName() + " borrowed " + book.getBookName());
                        });
                    });

        };
    }

//    private void sorting(StudentRepository studentRepository) {
//        Sort sort = Sort.by("firstName").descending().and(Sort.by("age").ascending());
//        studentRepository.findAll(sort).forEach(Student -> System.out.println(Student.getFirstName() + " " + Student.getAge()));
//    }

    private void GenerateRandomNumbers(StudentRepository studentRepository) {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@amigoscode.edu", firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));



        }
    }

}
