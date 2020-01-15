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

public abstract class AbstractPojoHasRangeExample<S extends AbstractPojoHasRangeExample<S>> extends Validated<S> {

	private int notValidated;

	@HasRange(min = 0, max = 1)
	private int value0;

	@HasRange(min = -10, max = 10)
	private int value1;

	@HasRange(min = -20, max = 20)
	private int value2;

	@HasRange(min = -30, max = 30)
	private int value3;

	public S withNotValidated(int notvalidated) {
		setNotValidated(notvalidated);
		return me();
	}

	public S withValue0(int value0) {
		setValue0(value0);
		return me();
	}

	public S withValue1(int value1) {
		setValue1(value1);
		return me();
	}

	public S withValue2(int value2) {
		setValue2(value2);
		return me();
	}

	public S withValue3(int value3) {
		setValue3(value3);
		return me();
	}

	public int getNotValidated() {
		return this.notValidated;
	}

	public int getValue0() {
		return this.value0;
	}

	public int getValue1() {
		return this.value1;
	}

	public int getValue2() {
		return this.value2;
	}

	public int getValue3() {
		return this.value3;
	}

	public void setNotValidated(int notValidated) {
		this.notValidated = notValidated;
	}

	public void setValue0(int val) {
		this.value0 = val;
	}

	public void setValue1(int val) {
		this.value1 = val;
	}

	public void setValue2(int val) {
		this.value2 = val;
	}

	public void setValue3(int val) {
		this.value3 = val;
	}

}
