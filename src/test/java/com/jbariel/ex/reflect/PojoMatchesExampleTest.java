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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import com.jbariel.ex.reflect.PojoMatchesExample;

final class PojoMatchesExampleTest extends ValidatedTest<PojoMatchesExample> {

	@Override
	PojoMatchesExample getObjectToValidate() {
		return new PojoMatchesExample();
	}

	@Override
	Class<PojoMatchesExample> getClazz() {
		return PojoMatchesExample.class;
	}

	@Test
	public void testRegExPatternMatching() {
		assertFalse(StringUtils.EMPTY.matches("[0-9]{3}"), "Simple digit (non)matcher to empty string");
		assertTrue("123".matches("[0-9]{3}"), "Simple digit matcher");
	}

	@Test
	public void testMatchesActuallyCompute() {
		PojoMatchesExample ex = getObjectToValidate();

		ex.setDoMatch(StringUtils.EMPTY);
		assertFalse(ex.isValid(), "Empty should be invalid");

		ex.setDoMatch("      ");
		assertFalse(ex.isValid(), "Lots of spaces should be invalid");

		ex.setDoMatch("1");
		assertFalse(ex.isValid(), "Single number should be invalid");

		ex.setDoMatch("a");
		assertFalse(ex.isValid(), "Single letter (lowercase) should be invalid");

		ex.setDoMatch("A");
		assertFalse(ex.isValid(), "Single letter (uppercase) should be invalid");

		ex.setDoMatch("ABC");
		assertFalse(ex.isValid(), "Triple letter (uppercase) should be invalid");

		ex.setDoMatch("123");
		assertTrue(ex.isValid(), "Triple number should be valid");

		ex.setDoMatch("    123    ");
		assertTrue(ex.isValid(), "Triple number surrounded by lots of spaces should be valid (as a string)");
	}
}
