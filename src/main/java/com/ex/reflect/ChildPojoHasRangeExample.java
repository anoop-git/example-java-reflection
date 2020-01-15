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

final class ChildPojoHasRangeExample extends AbstractPojoHasRangeExample<ChildPojoHasRangeExample> {

	public ChildPojoHasRangeExample() {
		super();
	}

	@HasRange(min = -40, max = 40)
	private int value4;

	public ChildPojoHasRangeExample withValue4(int value4) {
		setValue4(value4);
		return me();
	}

	public int getValue4() {
		return this.value4;
	}

	public void setValue4(int value4) {
		this.value4 = value4;
	}

}
