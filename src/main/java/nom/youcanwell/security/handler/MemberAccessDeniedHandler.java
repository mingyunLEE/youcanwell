package nom.youcanwell.security.handler;


import com.google.gson.Gson;
import nom.youcanwell.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MemberAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        errorSender(response,HttpStatus.FORBIDDEN);
    }

    private void errorSender(HttpServletResponse response, HttpStatus unauthorized) throws IOException {
        Gson gson = new Gson();
        ErrorResponse errorResponse = ErrorResponse.of(unauthorized);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(unauthorized.value());
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
    }
}
