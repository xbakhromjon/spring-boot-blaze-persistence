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

package uz.xbakhromjon.repository;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;
import uz.xbakhromjon.model.Cat;

import java.util.Comparator;

@EntityView(Cat.class)
public interface CatSimpleView {

    @IdMapping
    Integer getId();

    String getName();


    class DefaultComparator implements Comparator<CatSimpleView> {

        @Override
        public int compare(CatSimpleView o1, CatSimpleView o2) {
            return Integer.compare(o1.getId(), o2.getId());
        }
    }
}
