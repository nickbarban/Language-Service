package com.barban.service;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barban.model.Language;

public class Initializator {

	public static final Logger LOG = LoggerFactory.getLogger(Initializator.class);

	public void initializeSets(Object obj, Class<?>... dependencies) {
		List<Method> methods = getAllMethods(obj, dependencies);
		methods.stream().forEach(m -> {
			try {
				Hibernate.initialize(m.invoke(obj));
			} catch (Exception e) {
				LOG.info(String.format("Can't initialize %s by method: %s", obj.toString(), m.getName()));
			}
		});
	}

	private List<Method> getAllMethods(Object obj, Class<?>[] dependencies) {
		List<Method> methods = Stream.of(Language.class.getDeclaredMethods()).collect(Collectors.toList());
		for (Class<?> dependency : dependencies) {
			Method method = methods.stream().filter(new Predicate<Method>() {

				@Override
				public boolean test(Method m) {
					Class<?> returnType = m.getReturnType();
					if (returnType.isAssignableFrom(Set.class)) {
						ParameterizedType pType = (ParameterizedType) m.getGenericReturnType();
						Class<?> genericType = (Class<?>) pType.getActualTypeArguments()[0];
						return genericType.equals(dependency);
					} else {
						return false;
					}

				}
			}).findAny().get();
			methods.add(method);
		}
		return methods;

	}

}
