package pl.benzo.enzo.kmserver.token;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.benzo.enzo.kmserver.user.service.ImplUserDetailsService;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;


@Component
public class JwtFilter extends OncePerRequestFilter {


    private final Jwt jwt;
    private final ImplUserDetailsService implUserDetailsService;

    public JwtFilter(Jwt jwt, ImplUserDetailsService implUserDetailsService) {
        this.jwt = jwt;
        this.implUserDetailsService = implUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException, java.io.IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();
        if (!jwt.validateToken(token)) {
            chain.doFilter(request, response);
            return;
        }

        String crypto = jwt.extractToken(token);
        Authentication authentication = new UsernamePasswordAuthenticationToken(crypto, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}


