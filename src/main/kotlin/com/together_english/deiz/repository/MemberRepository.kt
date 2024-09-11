package com.together_english.deiz.repository

import com.together_english.deiz.model.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository : JpaRepository<Member, Long>{
    fun findByEmail(email: String): Optional<Member>
    fun findByNickname(nickname: String): Optional<Member>
}