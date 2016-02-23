package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.content.Card;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Len on 22-2-2016.
 */
public interface CardRepository extends JpaRepository<Card, Integer> {
}
