package com.example.reward.domain.member.repository;

import com.example.reward.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

	Optional<Member> findByLoginId(String loginId);

}
