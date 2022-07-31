/*
 * Copyright 2022 Thoughtworks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cd.go.contrib.plugins.configrepo.groovy.meta;

import jakarta.validation.constraints.NotEmpty;

public class Encrypted implements ConfigProperty {

    @NotEmpty
    private String key;

    private String value;

    public Encrypted(@NotEmpty String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean is(String key) {
        return key.equals(this.key);
    }

    @Override
    public String value() {
        return value;
    }
}
