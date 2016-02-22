package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.user.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by   Shenno Willaert
 * Date         22/02/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.backend.persistence.api
 */
public interface OrganisationRepository extends JpaRepository<Organisation, Integer>, JpaSpecificationExecutor<Organisation>, OrganisationRepositoryCustom {
}
