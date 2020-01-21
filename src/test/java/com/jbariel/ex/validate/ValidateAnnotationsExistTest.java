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
package com.jbariel.ex.validate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Example to show checking for an annotation based on a package (or set of
 * packages).
 * 
 * @see PubReqAnn
 * 
 * @author jarrettbariel
 *
 */
public class ValidateAnnotationsExistTest {

	private static final Logger logger = LoggerFactory.getLogger(ValidateAnnotationsExistTest.class);
	private final Logger log = ValidateAnnotationsExistTest.logger;

	private static final String classExtension = ".class";
	private static final String baseValidatePackage = "com.jbariel.ex.validate";
	private static final String baseControllerPackage = baseValidatePackage + ".controller";
	private static final String baseServicePackage = baseValidatePackage + ".service";

	private static final List<Class<? extends Object>> basePkgClasses = new ArrayList<>();

	@BeforeAll
	public static void setupPackageClassSets() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		assertNotNull("Cannot act with a null classloader!", loader);
		Enumeration<URL> resources = null;
		try {
			resources = loader.getResources(baseValidatePackage.replace('.', '/'));
		} catch (IOException e) {
			fail("Failed to get resources with exception: '" + e.getMessage() + "'");
			e.printStackTrace();
		}
		assertNotNull("Should have setup resources - should not be null!", resources);
		List<File> dirs = new ArrayList<>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			if (!resource.getPath().contains("/test-classes/")) {
				dirs.add(new File(resource.getFile()));
			}
		}
		assertFalse("Should have found at least one directory!", dirs.isEmpty());
		dirs.stream().forEach(d -> basePkgClasses.addAll(getAllClasses(d)));

	}

	private static List<Class<? extends Object>> getAllClasses(final File dir) {
		Class<?> tmpClazz = null;
		List<Class<? extends Object>> tmpClasses = new ArrayList<>();
		if (null != dir && dir.exists()) {
			for (File tmpFile : dir.listFiles()) {
				if (tmpFile.isDirectory()) {
					tmpClasses.addAll(getAllClasses(tmpFile));
				} else if (tmpFile.isFile()) {
					tmpClazz = getClassFromFilePath(tmpFile.getAbsolutePath());
					if (null != tmpClazz) {
						logger.info("    Adding class: '" + tmpClazz.getName() + "'");
						tmpClasses.add(tmpClazz);
					}
				}
			}
		}
		return tmpClasses;
	}

	private static Class<? extends Object> getClassFromFilePath(String filePath) {
		Class<? extends Object> clazz = null;
		if (StringUtils.trimToEmpty(filePath).endsWith(classExtension)) {
			String pkgPath = filePath.replace('/', '.');
			try {
				clazz = Class.forName(
						pkgPath.substring(pkgPath.indexOf(baseValidatePackage), pkgPath.lastIndexOf(classExtension)));
			} catch (ClassNotFoundException e) {
				fail("Could not find class : "
						+ pkgPath.substring(pkgPath.indexOf(baseValidatePackage), pkgPath.lastIndexOf(classExtension)));
				e.printStackTrace();
			}
			assertNotNull("Should have seen a CNF exception!", clazz);
			if (clazz.isAnnotation()) {
				clazz = null;
			}
		}
		return clazz;
	}

	class ErrMsg {
		String clazzName;
		String methodName;
		String annName;

		ErrMsg(String clazz, String mthd, String ann) {
			this.clazzName = clazz;
			this.methodName = mthd;
			this.annName = ann;
		}

		@Override
		public String toString() {
			return "[" + clazzName + "#" + methodName + "] is missing the " + annName + " annotation";
		}
	}

//	private Reflections reflectorForPackage(String basePkg) {
//		return new Reflections(basePkg, new TypeAnnotationsScanner(), new SubTypesScanner(true),
//				new MethodAnnotationsScanner());
//	}

	@Test
	public void placeholder() {
		assertTrue("Just checking", true);
	}

//	@Disabled
	@Test
	public void confirmPassWhenAllControllerPublicMethodsHaveAnnotation() {
		final List<ErrMsg> errors = validateWithReflectorAndAnnotation(baseControllerPackage, PubReqAnn.class);
		assertTrue("All controllers should be correct!", errors.isEmpty());
	}

//	@Disabled
	@Test
	public void confirmFailWhenAServicePublicMethodDoesNotHaveAnnotation() {
		final List<ErrMsg> errors = validateWithReflectorAndAnnotation(baseServicePackage, PubReqAnn.class);
		assertFalse("A service should be have a failure!", errors.isEmpty());
		assertEquals(1, errors.size(), "There should only be one failed method");
		log.error(errors.get(0).toString());
	}

//	@Disabled
	@Test
	public void confirmFailWhenAServicePublicMethodDoesNotHaveAnnotationButControllersDo() {
		final List<ErrMsg> errors = validateWithReflectorAndAnnotation(baseValidatePackage, PubReqAnn.class);
		assertFalse("A service should be have a failure!", errors.isEmpty());
		assertEquals(1, errors.size(), "There should only be one failed method");
		log.error(errors.get(0).toString());
	}

	private <E extends Annotation> List<ErrMsg> validateWithReflectorAndAnnotation(String rfltPkg, Class<E> annClazz) {
//		return validateWithReflectorAndAnnotation(reflectorForPackage(rfltPkg), annClazz);
//	}
//
//	private <E extends Annotation> List<ErrMsg> validateWithReflectorAndAnnotation(Reflections rflt,
//			Class<E> annClazz) {
		final List<ErrMsg> errors = new ArrayList<>();

//		rflt.getSubTypesOf(Object.class).stream().forEach(c -> {
//		new Reflections(rfltPkg, new SubTypesScanner(true)).getSubTypesOf(Object.class).stream().forEach(c -> {
		basePkgClasses.stream().filter(clazz -> clazz.getName().contains(rfltPkg)).forEach(pkgClazz -> {
			log.info("Checking class: '" + pkgClazz.getSimpleName() + "'...");
			Arrays.asList(pkgClazz.getDeclaredMethods()).stream().filter(m -> Modifier.isPublic(m.getModifiers()))
					.forEach(m -> {
						log.info("     Checking method: '" + m.getName() + "'...");
						if (null == m.getAnnotation(annClazz)) {
							errors.add(new ErrMsg(pkgClazz.getName(), m.getName(), annClazz.getSimpleName()));
						}
					});
		});

		assertNotNull("Should never return a null list!", errors);
		return errors;
	}

}
