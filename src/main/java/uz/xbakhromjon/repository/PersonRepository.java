package uz.xbakhromjon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.xbakhromjon.model.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @EntityGraph(attributePaths = {"kittens"})
    List<Person> findAll();

    @EntityGraph(attributePaths = {"kittens"})
    Page<Person> findAll(Pageable pageable);
}
