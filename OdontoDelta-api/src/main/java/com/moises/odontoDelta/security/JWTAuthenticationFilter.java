package com.moises.odontoDelta.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moises.odontoDelta.domain.enums.Permissao_usuario;
import com.moises.odontoDelta.helper.JsonHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moises.odontoDelta.dto.CredenciaisDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
    
    private JWTUtil jwtUtil;

    private class ResponseBody{
        private Integer userCodigo;
        private String userNome;
        private String userNomeCompleto;
        private String token;
        private boolean isAdmin;

        public ResponseBody(Integer userCodigo, String userNome, String userNomeCompleto,
                            String token, boolean isAdmin){
            this.userCodigo = userCodigo;
            this.userNome = userNome;
            this.userNomeCompleto = userNomeCompleto;
            this.token = token;
            this.isAdmin = isAdmin;
        }
    }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
    	setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
	
	@Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {

		try {
			CredenciaisDTO creds = new ObjectMapper()
	                .readValue(req.getInputStream(), CredenciaisDTO.class);
	
	        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getNome(), creds.getSenha(), new ArrayList<>());
	        
	        Authentication auth = authenticationManager.authenticate(authToken);
	        return auth;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        Integer codigo = ((UserSS) auth.getPrincipal()).getCodigo();
		String nome = ((UserSS) auth.getPrincipal()).getUsername();
		String nomeCompleto = ((UserSS) auth.getPrincipal()).getNomeCompleto();
        String token = "Bearer " + jwtUtil.generateToken(nome);
        Boolean isAdmin = ((UserSS) auth.getPrincipal()).isAdmin();

        ResponseBody responseBody = new ResponseBody(codigo, nome, nomeCompleto, token, isAdmin);
        PrintWriter out = res.getWriter();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        out.print(JsonHelper.toJson(responseBody));
        out.flush();
	}
	
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
		 
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {
            response.setStatus(400);
            response.setContentType("application/json"); 
            response.getWriter().append(json());
        }
        
        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                + "\"status\": 400, "
                + "\"error\": \"Nao autorizado\", "
                + "\"message\": \"Nome ou senha invalidos!\", "
                + "\"path\": \"/login\"}";
        }
    }
}