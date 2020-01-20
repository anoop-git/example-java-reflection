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

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.jbariel.ex.reflect.AbstractPojoHasRangeExample;

public abstract class AbstractPojoHasRangeExampleTest<E extends AbstractPojoHasRangeExample<E>>
		extends ValidatedTest<E> {

	@Test
	public void testMinMaxValue0() {
		checkAllInRangePlusOneOutside(0, 1, 0);
	}

	@Test
	public void testMinMaxValue1() {
		checkAllInRangePlusOneOutside(-10, 10, 1);
	}

	@Test
	public void testMinMaxValue2() {
		checkAllInRangePlusOneOutside(-20, 20, 2);
	}

	@Test
	public void testMinMaxValue3() {
		checkAllInRangePlusOneOutside(-30, 30, 3);
	}

	protected void checkAllInRangePlusOneOutside(final int min, final int max, final int target) {
		E ex = getObjectToValidate();

		for (int i = min; i <= max; i++) {
			setTarget(ex, target, i);
			assertTrue(ex.isValid(), "In range should be valid");

		}

		setTarget(ex, target, min - 1);
		assertTrue(!ex.isValid(), "Below valid range should fail");

		setTarget(ex, target, max + 1);
		assertTrue(!ex.isValid(), "Above valid range should fail");

	}

	protected void setTarget(final E ex, final int target, final int value) {
		if (0 == target) {
			ex.setValue0(value);
		} else if (1 == target) {
			ex.setValue1(value);
		} else if (2 == target) {
			ex.setValue2(value);
		} else if (3 == target) {
			ex.setValue3(value);
		} else {
			childSetTarget(ex, target, value);
		}
	}

	/**
	 * Use to manage extended classes so we can utilize the if/else methods
	 */
	protected abstract void childSetTarget(final E ex, final int target, final int value);
}
