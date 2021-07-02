
package com.example.library.repository;

import com.example.library.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    @Query(value = "select * from membership where user_id=?1 and ?2 between start_date and end_date", nativeQuery = true)
    public Membership getMembershipIdByUserId(long membershipId, String date);
}
