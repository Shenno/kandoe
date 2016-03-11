package be.kdg.kandoe.backend.persistence.api;

import be.kdg.kandoe.backend.dom.session.AsynchronousSession;
import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.User;

import java.util.List;

/**
 * @author: Evelien
 * @versionon 1.0 11-3-201614:12
 */
public interface SessionRepositoyCustom {

    List<Session> findSessionByUserId(int userId);
}
