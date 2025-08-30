package com.oocl.training.Repository;

import com.oocl.training.Entitiy.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTodoRepository extends JpaRepository<Todo, Integer> {

}
