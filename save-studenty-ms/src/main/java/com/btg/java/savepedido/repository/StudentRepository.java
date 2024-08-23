package com.btg.java.savepedido.repository;

import com.btg.java.savepedido.entitys.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface StudentRepository extends MongoRepository<StudentEntity, UUID> {
}
