/*
 * Copyright 2000-2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.project.model.impl.sdk;

import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.project.model.JpsSdkManager;
import org.jetbrains.jps.model.library.JpsLibrary;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nik
 */
public class JpsSdkManagerImpl extends JpsSdkManager {
  private final Map<JpsLibrary, Sdk> mySdks = new HashMap<JpsLibrary, Sdk>();

  @Override
  public Sdk getSdk(JpsLibrary library) {
    return mySdks.get(library);
  }
}
