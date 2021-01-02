/*
 * Copyright 2021 ThoughtWorks, Inc.
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

package parts

import cd.go.contrib.plugins.configrepo.groovy.dsl.Pipelines

return new Pipelines().pipeline('pipe2') {
  group = 'group1'
  labelTemplate = 'foo-1.0-${COUNT}'
  lockBehavior = 'lockOnFailure'
  template = 'template'
  timer {
    onlyOnChanges = true
    spec = '0 15 10 * * ? *'
  }
  trackingTool {
    link = 'http://your-trackingtool/yourproject/${ID}'
    regex = ~/evo-(\d+)/
  }
}
