package org.omnifaces.utils.function;

/**
 * Functional helper-interface to allow for use of closeable resources that don't implement AutoCloseable in a try-with-resources statement.
 *
 * <p>
 * This interface can be used with any object instance that doesn't implement AutoCloseable, but does have a close or similar method that needs to be
 * called to free up resources. This functional interface is usable for any close method that throws a more specific exception than AutoCloseable
 * defines. For methods that don't throw any exceptions, see @link{ExceptionLessAutoCloseable}.
 * </p>
 *
 *
 * <h3>Example:</h3>
 * <code>
 *     CloseableResource resource = ...
 *     try (ThrowingAutoCloseable&lt;IOException&gt; eac = resource::close) {
 *         // Use resource
 *     }
 *     catch(IOException e) {
 *         // Handle exception
 *     }
 * </code>
 *
 */
@FunctionalInterface
public interface ThrowingAutoCloseable<E extends Exception> extends AutoCloseable {

	@Override
	void close() throws E;

}
