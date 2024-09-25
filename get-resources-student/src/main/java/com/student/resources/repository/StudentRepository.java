package com.student.resources.repository;

import com.student.resources.entitys.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface StudentRepository extends MongoRepository<StudentEntity, UUID> {
}
