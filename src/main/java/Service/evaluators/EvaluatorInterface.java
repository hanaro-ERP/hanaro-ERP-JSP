package Service.evaluators;

import DTO.CreditScoringDTO;

public interface EvaluatorInterface<T> {

	T getTarget(CreditScoringDTO creditScoringDTO);

	int calculateScore(CreditScoringDTO creditScoringDTO);

}
