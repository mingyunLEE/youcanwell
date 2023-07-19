package nom.youcanwell.security.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nom.youcanwell.security.dto.LoginDto;
import nom.youcanwell.security.service.OauthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
public class OauthController {
    private final OauthService oauthservice;
    @GetMapping("/oauth/{provider}")
    public ResponseEntity Oauth2login(@PathVariable String provider, @RequestParam String code) throws JsonProcessingException {
        LoginDto loginDto = oauthservice.login(provider, code);

        return new ResponseEntity<>(loginDto, HttpStatus.ACCEPTED);
    }
}
