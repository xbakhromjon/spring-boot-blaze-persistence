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

package uz.xbakhromjon.sample;

import ntityManager;
import uz.xbakhromjon.model.Cat;
import uz.xbakhromjon.model.Person;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractSampleTest {

    @Autowired
    protected EntityManager em;

    @Before
    public void init() {
        Person p1 = new Person("P1");
        Person p2 = new Person("P2");
        Person p3 = new Person("P3");
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);

        Cat c1 = new Cat("C1", 1, p2);
        Cat c2 = new Cat("C2", 2, p2);
        Cat c3 = new Cat("C3", 4, p2);

        Cat c4 = new Cat("C4", 6, p3);

        Cat c5 = new Cat("C5", 8, null);
        Cat c6 = new Cat("C6", 7, null);

        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        em.persist(c4);
        em.persist(c5);
        em.persist(c6);

        c1.setMother(c3);
        c3.getKittens().add(c1);

        c1.setFather(c5);
        c5.getKittens().add(c1);

        c2.setMother(c3);
        c3.getKittens().add(c2);

        c2.setFather(c6);
        c6.getKittens().add(c2);

        c4.setFather(c6);
        c6.getKittens().add(c4);
    }
}