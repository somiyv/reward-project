package com.example.reward.domain.reward.enums;

import lombok.Getter;

@Getter
public enum SortType {

	asc, desc;


	public boolean isAsc() {
		return this.equals(asc);
	}
}
