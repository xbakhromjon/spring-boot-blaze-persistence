/*
 * Copyright 2014 - 2023 Blazebit.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uz.xbakhromjon;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import uz.xbakhromjon.model.Cat;
import uz.xbakhromjon.model.Person;
import uz.xbakhromjon.repository.CatRepository;
import uz.xbakhromjon.repository.PersonRepository;
import uz.xbakhromjon.repository.PersonWithKittensViewRepository;
import uz.xbakhromjon.view.PersonWithKittens;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private PersonWithKittensViewRepository personWithKittensViewRepository;

    @Autowired
    private PersonRepository personRepository;


    @Override
    public void run(String... args) throws Exception {
        List<Cat> p1Cats = catRepository.saveAll(Set.of(new Cat(1, "name"),
                new Cat(2, "name"),
                new Cat(3, "name")));

        List<Cat> p2Cats = catRepository.saveAll(Set.of(
                new Cat(4, "name"),
                new Cat(5, "name"),
                new Cat(6, "name")));

        List<Cat> p3Cats = catRepository.saveAll(Set.of(
                new Cat(7, "name"),
                new Cat(8, "name"),
                new Cat(9, "name")));

        personRepository.saveAll(List.of(
                new Person(1, "Bahrom", new HashSet<>(p1Cats)),
                new Person(2, "Test", new HashSet<>(p2Cats)),
                new Person(3, "name", new HashSet<>(p3Cats))
        ));

        System.out.println("---findAll----");
        List<Person> all = personRepository.findAll();
        System.out.println("---findAll pageable----");
        Page<Person> allPage = personRepository.findAll(PageRequest.of(0, 2, Sort.Direction.ASC, "id"));

        Page<PersonWithKittens> withOwner = personWithKittensViewRepository.findAll(
                new Specification<Person>() {
                    @Override
                    public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, jakarta.persistence.criteria.CriteriaBuilder criteriaBuilder) {
                        return criteriaBuilder.like(root.<String>get("name"), "Bahrom");
                    }
                },
                PageRequest.of(0, 2, Sort.Direction.ASC, "id")
        );
        List<PersonWithKittens> content = withOwner.getContent();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}

