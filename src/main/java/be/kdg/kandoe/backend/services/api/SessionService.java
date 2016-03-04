package be.kdg.kandoe.backend.services.api;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.dom.user.Organisation;

import java.util.List;

/**
 * Created by   Shenno Willaert
 * Date         28/02/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.backend.services.api
 */
public interface SessionService {
    // Session
    Session addSession(Session session, int themeId);
    Session findSession(int sessionId);

}
