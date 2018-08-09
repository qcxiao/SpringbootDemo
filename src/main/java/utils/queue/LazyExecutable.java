package utils.queue;

public interface LazyExecutable<T extends BaseRequest> {
	public void lazyExecute(T request);
}