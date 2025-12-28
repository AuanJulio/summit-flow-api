package com.summitflow.repository;

import com.summitflow.entity.Talk;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalkRepository extends JpaRepository<Talk, Long> {

    @Override
    @EntityGraph(attributePaths = {"speakers", "tracks"})
    List<Talk> findAll();

    @Query("SELECT DISTINCT t FROM Talk t JOIN t.tracks tr WHERE tr.id IN ?1")
    List<Talk> findByTracksQuery(List<Long> tracks);

}
