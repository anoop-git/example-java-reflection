/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.jbariel.ex.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ReflectSingleton {

	private static final Logger log = LoggerFactory.getLogger(ReflectSingleton.class);

	private final static ReflectSingleton instance = new ReflectSingleton();

	public static ReflectSingleton instance() {
		return instance;
	}

	/**
	 * Hide default constructor - should use the #instance() method
	 */
	private ReflectSingleton() {
		super();
		log.error("How did we get here?");
	}

	// TODO
}
