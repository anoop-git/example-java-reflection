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
package com.ex.reflect;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ValidatedTest<O extends Validated<O>> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Test
	public void testIsValidByDefault() {
		assertTrue(getObjectToValidate().isValid(), "Why is object not valid??");
		assertNotNull(getObj(), "Was unable to get a newInstance - check logs for exception");
		assertTrue(getObj().isValid(), "Why is the newInstance object not valid??");
	}

	abstract O getObjectToValidate();

	abstract Class<O> getClazz();

	O getObj() {
		O obj = null;
		try {
			obj = getClazz().getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return obj;
	}

}
