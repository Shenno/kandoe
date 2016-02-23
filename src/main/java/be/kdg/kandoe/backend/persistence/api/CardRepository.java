package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.content.Card;
import be.kdg.kandoe.backend.dom.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Len on 22-2-2016.
 */
public interface CardRepository extends JpaRepository<Card, Integer>,JpaSpecificationExecutor<Card>, CardRepositoryCustom {
}
