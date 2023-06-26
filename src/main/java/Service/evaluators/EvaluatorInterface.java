package Service.evaluators;

public interface EvaluatorInterface<T> {
	T getDTO();

	int evaluate();
}
