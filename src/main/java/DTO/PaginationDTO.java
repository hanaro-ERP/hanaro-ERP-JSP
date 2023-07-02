package DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PaginationDTO extends EditDTO{
	private int page;
	private int count;

}
