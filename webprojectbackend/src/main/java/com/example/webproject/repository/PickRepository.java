package com.example.webproject.repository;

import com.example.webproject.model.Pick;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PickRepository extends JpaRepository<Pick,Long> {

    @Query("select pick from Pick pick where pick.status = 'null' ")
    List<Pick> getAllPicksWithStatusNull();

}
