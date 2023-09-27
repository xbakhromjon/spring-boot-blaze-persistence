package uz.xbakhromjon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.xbakhromjon.model.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {
}

