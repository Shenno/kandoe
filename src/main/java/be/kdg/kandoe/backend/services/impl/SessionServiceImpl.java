
package be.kdg.kandoe.backend.services.impl;

import be.kdg.kandoe.backend.dom.content.Theme;
import be.kdg.kandoe.backend.dom.session.Session;
import be.kdg.kandoe.backend.persistence.api.SessionRepository;
import be.kdg.kandoe.backend.persistence.api.ThemeRepository;
import be.kdg.kandoe.backend.services.api.SessionService;
import be.kdg.kandoe.backend.services.exceptions.SessionServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by   Shenno Willaert
 * Date         28/02/2016
 * Project      kandoe
 * Package      be.kdg.kandoe.backend.services.impl
 */

@Service("sessionService")
@Transactional
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final ThemeRepository themeRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository, ThemeRepository themeRepository) {
        this.sessionRepository = sessionRepository;
        this.themeRepository = themeRepository;
    }

    @Override
    public Session addSession(Session session, int themeId) {
        Theme theme = this.themeRepository.findOne(themeId);
        session.setTheme(theme);
        return this.sessionRepository.save(session);
    }

    @Override
    public Session findSession(int sessionId) {
        return this.sessionRepository.findOne(sessionId);
    }


}

