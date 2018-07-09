package br.com.events.auth.service.impl;

import br.com.events.auth.bean.Token;
import br.com.events.auth.dao.UserDAO;
import br.com.events.auth.entity.UserEntity;
import br.com.events.auth.exception.ForbiddenException;
import br.com.events.auth.service.AuthService;
import br.com.events.auth.service.JWTService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final JWTService jwtService;
    private final UserDAO userDAO;

    @Autowired
    public AuthServiceImpl(JWTService jwtService, UserDAO userDAO) {
        this.jwtService = jwtService;
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public Token auth(String username, String password) {

        final String sha1 = DigestUtils.sha1Hex(password);

        UserEntity entity = userDAO.findByUsernameAndPassword(username, sha1);

        if (entity == null) {
            throw new ForbiddenException("Forbidden: No Authorization.");
        }

        String token = jwtService.generate(String.valueOf(entity.getId()));

        return new Token(token);
    }
}
