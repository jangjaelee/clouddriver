/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.clouddriver.aws.edda

import com.netflix.spinnaker.kork.retrofit.exceptions.SpinnakerRetrofitErrorHandler
import retrofit.RestAdapter
import retrofit.converter.Converter

import java.util.regex.Pattern

class EddaApiFactory {
  private Converter eddaConverter

  EddaApiFactory(Converter eddaConverter) {
    this.eddaConverter = eddaConverter
  }

  public EddaApi createApi(String endpointTemplate, String region) {
    if (endpointTemplate) {
      return new RestAdapter.Builder()
        .setConverter(eddaConverter)
        .setEndpoint(endpointTemplate.replaceAll(Pattern.quote('{{region}}'), region))
        .setErrorHandler(SpinnakerRetrofitErrorHandler.getInstance())
        .build()
        .create(EddaApi)
    }
    return null
  }
}
