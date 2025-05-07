package by.aston.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @Column(columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private  UUID id;
    @Column(unique = true, nullable = false)
    private  String username;
    @Column(nullable = false)
    private  String password;
    @Column(nullable = false)
    private  String email;

}
