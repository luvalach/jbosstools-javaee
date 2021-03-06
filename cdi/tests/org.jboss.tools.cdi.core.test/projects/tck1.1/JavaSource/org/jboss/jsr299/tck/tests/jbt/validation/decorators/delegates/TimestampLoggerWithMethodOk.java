package org.jboss.jsr299.tck.tests.jbt.validation.decorators.delegates;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

@Decorator
public abstract class TimestampLoggerWithMethodOk extends Clazz<String> implements Logger {

	@Inject void setInt(@Delegate Clazz<Logger> logger) {
	}
}