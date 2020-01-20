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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.BiFunction;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Validated<S extends Validated<S>> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	protected S me() {
		return (S) this;
	}

	// reflect over all items and ensure validation
	public boolean isValid() {
		// the below only checks the primary class
		//
		// return
		// Arrays.asList(this.getClass().getDeclaredFields()).stream().noneMatch(this::fieldIsValid);
		//
		// vvvvv this uses recursion to check the full parent/child tree vvvvv
		return objectIsValid(this.getClass());
	}

	private boolean objectIsValid(Class<?> clazz) {
		return (null == clazz) ? true
				: Arrays.asList(clazz.getDeclaredFields()).stream().noneMatch(this::fieldIsValid)
						&& objectIsValid(clazz.getSuperclass());
	}

	@SuppressWarnings("deprecation")
	private boolean fieldIsValid(Field f) {
		boolean acc = f.isAccessible();
		f.setAccessible(true);
		log.trace("checking field: " + f);
		boolean isInvalid = validHasRange(f) || validNotEmpty(f) || validMatches(f);
		f.setAccessible(acc);
		return isInvalid;
	}

	private boolean validNotEmpty(Field f) {
		return validityWrapper(f, NotEmpty.class, String.class, (ann, val) -> StringUtils.trimToEmpty(val).isEmpty());
	}

	private boolean validHasRange(Field f) {
		return validityWrapper(f, HasRange.class, Integer.class, (ann, val) -> val < ann.min() || val > ann.max());
	}

	private boolean validMatches(Field f) {
		return validityWrapper(f, Matches.class, String.class,
				(ann, val) -> !StringUtils.trimToEmpty(val).matches(ann.value()));
	}

	private <E extends Annotation, T> boolean validityWrapper(Field f, Class<E> clazz, Class<T> validFieldType,
			BiFunction<E, T, Boolean> fnCheck) {
		boolean isInvalid = false;
		E ann = f.getAnnotation(clazz);
		if (null != ann) {
			log.debug("annotation: " + ann);
			try {
				Object objVal = f.get(this);
				if (null != objVal && validFieldType.isAssignableFrom(objVal.getClass())) {
					isInvalid = fnCheck.apply(ann, validFieldType.cast(objVal));
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return isInvalid;
	}
}
