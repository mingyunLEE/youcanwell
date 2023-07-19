package nom.youcanwell.security.filter;

import com.google.gson.Gson;
import nom.youcanwell.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Exception exception = (Exception) request.getAttribute("exception");
        errorSender(response, HttpStatus.UNAUTHORIZED);
    }


    private void errorSender(HttpServletResponse response, HttpStatus unauthorized) throws IOException {
        Gson gson = new Gson();
        ErrorResponse errorResponse = ErrorResponse.of(unauthorized);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(unauthorized.value());
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
    }
}
