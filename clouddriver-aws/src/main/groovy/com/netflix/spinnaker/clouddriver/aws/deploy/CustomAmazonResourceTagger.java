/*
 * Copyright 2021 Armory, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.clouddriver.aws.deploy;

import java.util.*;
import java.util.stream.Collectors;

public class CustomAmazonResourceTagger implements AmazonResourceTagger {
  private final Map<String, String> tags;

  public CustomAmazonResourceTagger(Map<String, String> tags) {
    this.tags = tags;
  }

  @Override
  public Collection<Tag> volumeTags(String serverGroupName) {
    return this.tags.entrySet().stream()
        .map(t -> Tag.of(t.getKey(), t.getValue()))
        .collect(Collectors.toList());
  }
}
