package br.com.alura.orcamentoFamiliar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.com.alura.orcamentoFamiliar.config.security.DadosTokenJWT;
import br.com.alura.orcamentoFamiliar.config.security.TokenService;
import br.com.alura.orcamentoFamiliar.controller.vo.DadosAutenticacaoVO;
import br.com.alura.orcamentoFamiliar.modelo.Usuario;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

      @Autowired
      private AuthenticationManager manager;
      
      @Autowired
      private TokenService tokenService;
      
      @PostMapping
      public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoVO dados) {
          var authenticationToken = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha());
          var authentication = manager.authenticate(authenticationToken);

          var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

          return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
      }
}
