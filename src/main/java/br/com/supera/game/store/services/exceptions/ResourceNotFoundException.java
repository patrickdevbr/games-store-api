package br.com.supera.game.store.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Class<?> objClass, Object id) {
		super("Resource " + objClass.getSimpleName() + " not found. Id: " + id);
	}

	public ResourceNotFoundException(Class<?> objClass, String additionalMsg) {
		super("Resource " + objClass.getSimpleName() + " not found. " + additionalMsg);
	}

}
