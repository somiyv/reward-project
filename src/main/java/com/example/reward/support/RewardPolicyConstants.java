package com.example.reward.support;

import com.example.reward.utils.DateUtils;
import com.google.common.base.Joiner;

public class RewardPolicyConstants {

	public static final String REWARD_TITLE = "매일 00시 00분 00초 선착순 10명 100 포인트 지급!!!"; // 보상 제목
	public static final String REWARD_DESCRIPTION = " 보상지급방식은사용자가받기를누를때지급하게 됩니다.\n" // 보상내용
			+ "• 선착순 10 명에게 100 포인트의 보상이 지급 되며 10 명 이후에는 지급되지 않아야 합니다.\n"
			+ "• 3일 연속,5일 연속,10일 연속 보상을 받는 경우 300 포인트, 500 포인트, 1,000 포인트를 추가 보상 받게 됩니다.\n"
			+ "• 추가 보상은 10일 까지 이어지며 그 이후 연속 보상 횟수는 1 회 부터 다시 시작 됩니다.";
	public static final int DEFAULT_POINT = 100; // 기본 지급 포인트
	public static final int THREE_DAYS_POINT = 300; // 3일 연속 지급 포인트
	public static final int FIVE_DAYS_POINT = 500; // 5일 연속 지급 포인트
	public static final int TEN_DAYS_POINT = 1000; // 10일 연속 지급 포인트

	public static final int MAXIMUM_REWARDS_DAYS = 10; // 최대 보상 일수

	// 오늘의 보상받은 수를 체크할 날짜 키
	public static final String TODAY_REWARDS_KEY = Joiner.on(":").join("reward", DateUtils.today());
}
