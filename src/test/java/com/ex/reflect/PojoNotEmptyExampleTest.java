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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

final class PojoNotEmptyExampleTest extends ValidatedTest<PojoNotEmptyExample> {

	@Override
	PojoNotEmptyExample getObjectToValidate() {
		return new PojoNotEmptyExample();
	}

	@Override
	Class<PojoNotEmptyExample> getClazz() {
		return PojoNotEmptyExample.class;
	}

	@Test
	public void testWillNotBeValidWhenEmpty() {
		PojoNotEmptyExample ex = getObjectToValidate();

		ex.setValidation(StringUtils.EMPTY);
		assertFalse(ex.isValid(), "Empty should be invalid");

		ex.setValidation("      ");
		assertFalse(ex.isValid(), "Lots of spaces should be invalid");

		ex.setValidation("1");
		assertTrue(ex.isValid(), "Single number should be valid (as a string)");

		ex.setValidation("a");
		assertTrue(ex.isValid(), "Single letter should be valid");

		ex.setValidation("    a    ");
		assertTrue(ex.isValid(), "Single letter surrounded by lots of spaces should be valid (as a string)");
	}
}
