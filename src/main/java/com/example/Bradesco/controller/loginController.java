package com.example.Bradesco.controller;

import java.math.BigDecimal;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Bradesco.model.Cliente;
import com.example.Bradesco.model.Conta;
import com.example.Bradesco.repository.ClienteRepository;
import com.example.Bradesco.repository.ContaRepository;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class LoginController {

    // o modificador final indica que a variável não pode ser alterada após a inicialização
    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    // construtor que recebe o repositório de contas como parâmetro
    public LoginController(ContaRepository contaRepository, 
                           ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }
    

    // Método que mapeia a URL raiz ("/") para o método login()
    @GetMapping("/")
    public String login() {
        return "login";
    }


    // Método que mapeia a URL "/login" para realizar o login
    @GetMapping("/login")
    public String realizarLogin(@RequestParam Integer agencia, @RequestParam Integer numero_conta, @RequestParam String senha, Model model) {
        Conta conta = contaRepository.LoginAgenciaConta(agencia, numero_conta, senha);
    
        if (conta != null) {
            model.addAttribute("conta", conta);
            model.addAttribute("cliente", conta.getCliente());
            return "/index";
        }else{
            model.addAttribute("erro", "Dados da conta inválidos");
            return "/login";
        }
    }


    @GetMapping("/registrar")
    public String realizarRegistro(@RequestParam String param) {
        return new String();
    }


    @GetMapping("/clienteNovo")
    public String novoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("conta", new Conta());
        return "clienteNovo";
    }

    @PostMapping("/clienteNovo")
    public String cadastrarCliente(@ModelAttribute Cliente cliente,
                                   @ModelAttribute Conta conta, 
                                   Model model,
                                   RedirectAttributes redirectAttributes) {

        cliente.setStatus(Cliente.status.ATIVO);
        clienteRepository.save(cliente);

        conta.setAgencia(1001);

        Integer maiorNumeroConta = contaRepository.maiorNumero();
        if (maiorNumeroConta == null) {
            maiorNumeroConta = 1000001;
        }
        conta.setNumero_conta(maiorNumeroConta + 1);

        conta.setData_abertura(java.time.LocalDate.now());

        conta.setSaldo(BigDecimal.ZERO);

        conta.setStatus(Conta.status.ATIVA);

        conta.setCliente(cliente);

        contaRepository.save(conta);

        redirectAttributes.addAttribute("agencia", conta.getAgencia());
        redirectAttributes.addAttribute("numero_conta", conta.getNumero_conta());

        System.out.print(cliente.getDataNascimento());
        
        return "redirect:/loginForm";

    }

    @GetMapping("/loginForm")
    public String loginForm(@RequestParam (required = false) Integer agencia,
                            @RequestParam (required = false) Integer numero_conta, 
                            Model model) {
        
        model.addAttribute("agencia", agencia);
        model.addAttribute("numero_conta", numero_conta);

        return "login";

    }
    
    
    
    
    
    

}
