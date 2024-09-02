package com.example.goldClub.security;

import com.example.goldClub.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.mockito.Mockito.*;

//esta prueba unitaria asegura que el filtro JwtAuthenticationFilter procesa correctamente una solicitud con un token JWT válido, 
//extrayendo el token, validándolo, obteniendo el nombre de usuario del token, cargando los detalles del usuario correspondiente y, 
//finalmente, permitiendo que la solicitud continúe a través de la cadena de filtros.

public class JwtAuthenticationFilterTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public JwtAuthenticationFilterTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoFilterInternal() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        String token = "testToken";
        String username = "test@example.com";
        UserDetails userDetails = mock(UserDetails.class);

        when(jwtService.extractToken(request)).thenReturn(token);
        when(jwtService.isTokenValid(token)).thenReturn(true);
        when(jwtService.extractUsername(token)).thenReturn(username);
        when(usuarioService.loadUserByUsername(username)).thenReturn(userDetails);

        jwtAuthenticationFilter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);
    }
}
