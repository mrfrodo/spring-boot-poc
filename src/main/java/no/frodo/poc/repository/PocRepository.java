package no.frodo.poc.repository;

import no.frodo.poc.model.Poc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PocRepository extends JpaRepository<Poc, Long> {

}