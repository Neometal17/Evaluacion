package pe.com.user.administrator.application.service;

import lombok.RequiredArgsConstructor;
import pe.com.user.administrator.application.port.in.GenerateTokenUseCase;
import pe.com.user.administrator.application.port.out.TokenProviderPort;

@RequiredArgsConstructor
public class TokenProviderService implements GenerateTokenUseCase {

    private final TokenProviderPort tokenProviderPort;

    @Override
    public String generateToken(String userName) {
        return tokenProviderPort.generateToken(userName);
    }


}
