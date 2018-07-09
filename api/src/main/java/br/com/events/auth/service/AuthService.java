package br.com.events.auth.service;

import br.com.events.auth.bean.Token;

public interface AuthService {

    Token auth(String username, String password);

}
